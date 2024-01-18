package com.wendel.test.runTheBank.adapter.controller;

import com.wendel.test.runTheBank.adapter.controller.request.TransactionRequest;
import com.wendel.test.runTheBank.adapter.controller.response.TransactionResponse;
import com.wendel.test.runTheBank.adapter.gateway.util.TransactionValidate;
import com.wendel.test.runTheBank.usecase.transaction.CancelTransaction;
import com.wendel.test.runTheBank.usecase.transaction.CreateTransaction;
import com.wendel.test.runTheBank.usecase.transaction.GetTransaction;
import com.wendel.test.runTheBank.usecase.transaction.RevokeTransaction;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/transaction")
public class TransactionController {
    private final CreateTransaction createTransaction;
    private final GetTransaction getTransaction;
    private final TransactionValidate transactionValidate;
    private final RevokeTransaction revokeTransaction;
    private final CancelTransaction cancelTransaction;

    public TransactionController(CreateTransaction createTransaction, GetTransaction getTransaction, TransactionValidate transactionValidate, RevokeTransaction revokeTransaction, CancelTransaction cancelTransaction) {
        this.createTransaction = createTransaction;
        this.getTransaction = getTransaction;
        this.transactionValidate = transactionValidate;
        this.revokeTransaction = revokeTransaction;
        this.cancelTransaction = cancelTransaction;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.OK)
    public TransactionResponse createTransaction(
            @RequestBody TransactionRequest transactionRequest) {
        transactionValidate.validate(transactionRequest);
        return createTransaction.execute(transactionRequest);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public TransactionResponse getTransaction(
            @RequestParam String id) {
        return getTransaction.execute(id);
    }

    @PatchMapping
    @ResponseStatus(HttpStatus.OK)
    public TransactionResponse revokingTransaction(
            @RequestParam String id) {
        return revokeTransaction.execute(id);
    }
    @DeleteMapping
    @ResponseStatus(HttpStatus.OK)
    public TransactionResponse cancelTransaction(
            @RequestParam String id) {
        return cancelTransaction.execute(id);
    }
}
