package com.wendel.test.runTheBank.adapter.controller;

import com.wendel.test.runTheBank.adapter.controller.request.RegisterRequest;
import com.wendel.test.runTheBank.adapter.controller.request.TransactionRequest;
import com.wendel.test.runTheBank.adapter.controller.response.RegisterResponse;
import com.wendel.test.runTheBank.adapter.controller.response.TransactionResponse;
import com.wendel.test.runTheBank.adapter.gateway.util.TransactionValidate;
import com.wendel.test.runTheBank.usecase.register.CreateRegister;
import com.wendel.test.runTheBank.usecase.register.GetRegister;
import com.wendel.test.runTheBank.usecase.transaction.CreateTransaction;
import com.wendel.test.runTheBank.usecase.transaction.GetTransaction;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/transaction")
public class TransactionController {
    private final CreateTransaction createTransaction;
    private final GetTransaction getTransaction;
    private final TransactionValidate transactionValidate;

    public TransactionController(CreateTransaction createTransaction, GetTransaction getTransaction, TransactionValidate transactionValidate) {
        this.createTransaction = createTransaction;
        this.getTransaction = getTransaction;
        this.transactionValidate = transactionValidate;
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
}
