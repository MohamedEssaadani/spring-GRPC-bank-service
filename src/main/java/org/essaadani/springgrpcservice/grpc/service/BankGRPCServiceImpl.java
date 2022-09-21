package org.essaadani.springgrpcservice.grpc.service;

import io.grpc.stub.StreamObserver;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import net.devh.boot.grpc.server.service.GrpcService;
import org.essaadani.springgrpcservice.entities.Currency;
import org.essaadani.springgrpcservice.grpc.stub.Bank;
import org.essaadani.springgrpcservice.grpc.stub.BankServiceGrpc;
import org.essaadani.springgrpcservice.repository.CurrencyRepository;
import org.springframework.beans.factory.annotation.Autowired;

@GrpcService
public class BankGRPCServiceImpl extends BankServiceGrpc.BankServiceImplBase {
    @Autowired
    CurrencyRepository currencyRepository;

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

       // get currencies from db
        Currency currencyFrom = currencyRepository.findByName(from);
        Currency currencyTo = currencyRepository.findByName(to);

        // calculate result
       double result = amount*(currencyFrom.getPrice() / currencyTo.getPrice());

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
