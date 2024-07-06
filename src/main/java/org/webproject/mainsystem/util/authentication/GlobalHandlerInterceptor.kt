package org.webproject.mainsystem.util.authentication

import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component
import org.springframework.web.method.HandlerMethod
import org.springframework.web.servlet.HandlerInterceptor
import org.webproject.grpcserver.proto.Models.AuthenticationResponse
import org.webproject.mainsystem.model.dao.AdminDao
import org.webproject.mainsystem.model.dao.CustomerDao
import org.webproject.mainsystem.model.dao.RepairManDao
import org.webproject.mainsystem.repository.AdminRepository
import org.webproject.mainsystem.repository.CustomerRepository
import org.webproject.mainsystem.repository.RepairManRepository
import org.webproject.mainsystem.service.rpc.SsoValidationClientService
import org.webproject.responsewrapper.custom.const.DEVICE_MODEL_HEADER
import org.webproject.responsewrapper.custom.const.TOKEN_HEADER
import org.webproject.responsewrapper.custom.exception.DefaultSupportedException
import org.webproject.responsewrapper.enumModel.USERTYPE
import org.webproject.responsewrapper.enumModel.USERTYPE.*
import java.util.*


@Component
class GlobalHandlerInterceptor @Autowired constructor(
    private val ssoRpcService: SsoValidationClientService,
    private val adminRepository: AdminRepository,
    private val customerRepository: CustomerRepository,
    private val repairManRepository: RepairManRepository
    ) :
    HandlerInterceptor {


    override fun preHandle(request: HttpServletRequest, response: HttpServletResponse, handler: Any): Boolean {
        val deviceModel = request.getHeader(DEVICE_MODEL_HEADER) ?: return false
        if (handler is HandlerMethod) {
            request.getHeader(TOKEN_HEADER)?.let { itToken ->
                val result = ssoRpcService.getUserIdFromToken(itToken, deviceModel)

                if (!result.isInitialized || result.userId.isNullOrBlank()) return false

                val tokenInfo = convertToTokenInfo(result, deviceModel)

                when(tokenInfo.type) {
                    ADMIN -> {
                        if(!adminRepository.existsById(tokenInfo.userId))
                        adminRepository.save(
                            AdminDao().apply {
                                id = tokenInfo.userId
                                firstname = tokenInfo.firstname
                                lastname = tokenInfo.lastname
                                email = result.email
                            }
                        )
                    }
                    CUSTOMER -> {
                        if(!customerRepository.existsById(tokenInfo.userId))
                            customerRepository.save(
                                CustomerDao().apply {
                                    id = tokenInfo.userId
                                    firstname = tokenInfo.firstname
                                    lastname = tokenInfo.lastname
                                    email = result.email
                                }
                            )
                    }
                    REPAIRMAN -> {
                        if(!repairManRepository.existsById(tokenInfo.userId))
                            repairManRepository.save(
                                RepairManDao().apply {
                                    id = tokenInfo.userId
                                    firstname = tokenInfo.firstname
                                    lastname = tokenInfo.lastname
                                    email = result.email
                                }
                            )
                    }
                }

                request.setAttribute("TokenInfo",tokenInfo)

                return true
            } ?: return false
        }

        return true;

    }

    private fun checkHashingIntegrity(hashData: String, nonce: Long, body: String): Boolean {

        return true
    }

    data class TokenInfoModel(
        val userId: UUID,
        val deviceModel: String,
        val type: USERTYPE,
        val firstname: String,
        val lastname: String
    )

    fun convertToTokenInfo(model: AuthenticationResponse, deviceModel: String): TokenInfoModel {
        return TokenInfoModel(
            UUID.fromString(model.userId),
            deviceModel,
            USERTYPE.returnUserType(model.type) ?: throw DefaultSupportedException("User Type is Not supported"),
            model.firstname,
            model.lastname,
        )

    }

}