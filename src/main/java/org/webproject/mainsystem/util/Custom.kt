package org.webproject.mainsystem.util

import jakarta.servlet.http.HttpServletRequest
import org.webproject.mainsystem.util.authentication.GlobalHandlerInterceptor
import org.webproject.responsewrapper.custom.exception.DefaultSupportedException

fun HttpServletRequest.getTokenInfoFromHeader(): GlobalHandlerInterceptor.TokenInfoModel{
    return getAttribute("TokenInfo") as? GlobalHandlerInterceptor.TokenInfoModel ?:
    throw DefaultSupportedException("You did not Token")
}