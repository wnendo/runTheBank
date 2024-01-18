package com.wendel.test.runTheBank.usecase.transaction;

import com.wendel.test.runTheBank.adapter.controller.request.RegisterRequest;
import com.wendel.test.runTheBank.adapter.controller.request.TransactionRequest;
import com.wendel.test.runTheBank.adapter.controller.response.RegisterResponse;
import com.wendel.test.runTheBank.adapter.controller.response.TransactionResponse;

public interface CreateTransaction {
    TransactionResponse execute(TransactionRequest transactionRequest);
}
