package com.wendel.test.runTheBank.usecase.transaction;

import com.wendel.test.runTheBank.adapter.controller.response.RegisterResponse;
import com.wendel.test.runTheBank.adapter.controller.response.TransactionResponse;
import com.wendel.test.runTheBank.domain.Transaction;

public interface GetTransaction {
    TransactionResponse execute(String id);
}
