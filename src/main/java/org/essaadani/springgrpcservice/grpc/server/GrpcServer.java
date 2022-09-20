package org.essaadani.springgrpcservice.grpc.server;

import io.grpc.Server;
import io.grpc.ServerBuilder;
import org.essaadani.springgrpcservice.grpc.service.BankGRPCServiceImpl;

import java.io.IOException;

public class GrpcServer {
    public static void main(String[] args) throws IOException, InterruptedException {
        Server server = ServerBuilder.forPort(9999)
                .addService(new BankGRPCServiceImpl())
                .build();

        server.start();
        server.awaitTermination();
    }
}
