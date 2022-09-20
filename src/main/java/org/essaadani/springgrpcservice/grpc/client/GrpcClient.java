package org.essaadani.springgrpcservice.grpc.client;

import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import io.grpc.stub.StreamObserver;
import org.essaadani.springgrpcservice.grpc.stub.Bank;
import org.essaadani.springgrpcservice.grpc.stub.BankServiceGrpc;

import java.io.IOException;

public class GrpcClient {
    public static void main(String[] args) throws IOException {
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


        BankServiceGrpc.BankServiceStub bankServiceStub=BankServiceGrpc.newStub(managedChannel);
        bankServiceStub.convertCurrency(request, new StreamObserver<Bank.ConvertCurrencyResponse>() {
            @Override
            public void onNext(Bank.ConvertCurrencyResponse response) {
                System.out.println("************************NON BLOCKING RESULT********************");
                System.out.println("From :"+response.getCurrencyFrom());
                System.out.println("To :"+ response.getCurrencyTo());
                System.out.println("Result: "+ response.getConversionResult()+" MAD");
            }

            @Override
            public void onError(Throwable throwable) {
                System.out.println(throwable.getMessage());
            }

            @Override
            public void onCompleted() {
                System.out.println("ECHANGE ENDDD!!");
            }
        });
        System.in.read();
    }
}
