package org.webproject.mainsystem.service.rpc;

import net.devh.boot.grpc.client.inject.GrpcClient;
import org.springframework.stereotype.Service;
import org.webproject.grpcserver.proto.AuthenticationServiceGrpc;
import org.webproject.grpcserver.proto.Models.*;

import java.util.UUID;

@Service
public class SsoValidationClientService {

    @GrpcClient("grpcServer")
    AuthenticationServiceGrpc.AuthenticationServiceBlockingStub syncClient;

    public AuthenticationResponse getUserIdFromToken(String token,String deviceModel) {
        AuthenticationRequest request = AuthenticationRequest.newBuilder().setToken(token).setDeviceModel(deviceModel).build();
        return syncClient.getUserAuthentication(request);
    }

}
