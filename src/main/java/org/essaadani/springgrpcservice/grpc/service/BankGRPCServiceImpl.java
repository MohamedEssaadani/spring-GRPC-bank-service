package org.essaadani.springgrpcservice.grpc.service;

import io.grpc.stub.StreamObserver;
import org.essaadani.springgrpcservice.grpc.stub.Bank;
import org.essaadani.springgrpcservice.grpc.stub.BankServiceGrpc;

public class BankGRPCServiceImpl extends BankServiceGrpc.BankServiceImplBase {
    @Override
    public void getBankAccount(Bank.GetBankAccountRequest request, StreamObserver<Bank.GetBankAccountResponse> responseObserver) {
        super.getBankAccount(request, responseObserver);
    }

    @Override
    public void getAccountsList(Bank.GetAccountsListRequest request, StreamObserver<Bank.GetAccountsListResponse> responseObserver) {
        super.getAccountsList(request, responseObserver);
    }

    @Override
    public void convertCurrency(Bank.ConvertCurrencyRequest request, StreamObserver<Bank.ConvertCurrencyResponse> responseObserver) {
      // get infos from request
       String from = request.getCurrencyFrom();
       String to = request.getCurrencyTo();
       double amount = request.getAmount();
       double result = amount*10.8;
       // prepare response
        Bank.ConvertCurrencyResponse response = Bank.ConvertCurrencyResponse
                .newBuilder()
                .setAmount(amount)
                .setCurrencyFrom(from)
                .setCurrencyTo(to)
                .setConversionResult(result)
                .build();

        // send response
        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }
}
