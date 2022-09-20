package org.essaadani.springgrpcservice.grpc.client;

import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import org.essaadani.springgrpcservice.grpc.stub.Bank;
import org.essaadani.springgrpcservice.grpc.stub.BankServiceGrpc;

public class GrpcClient {
    public static void main(String[] args) {
        // connect to service
        ManagedChannel managedChannel = ManagedChannelBuilder
                .forAddress("localhost", 9999)
                .usePlaintext()
                .build();

        BankServiceGrpc.BankServiceBlockingStub blockingStub = BankServiceGrpc.newBlockingStub(managedChannel);

        // Request Builder
        Bank.ConvertCurrencyRequest.Builder builder = Bank.ConvertCurrencyRequest.newBuilder();
        builder.setCurrencyFrom("USD");
        builder.setCurrencyTo("MAD");
        builder.setAmount(3000);

        // Create Request
        Bank.ConvertCurrencyRequest request = builder.build();
        // consume service
        Bank.ConvertCurrencyResponse response =  blockingStub.convertCurrency(request);

        System.out.println("********************************************");
        System.out.println("From :"+response.getCurrencyFrom());
        System.out.println("To :"+ response.getCurrencyTo());
        System.out.println("Result: "+ response.getConversionResult()+" MAD");
    }
}
