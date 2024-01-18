package com.wendel.test.runTheBank.usecase.transaction;

import com.wendel.test.runTheBank.adapter.controller.request.TransactionRequest;
import com.wendel.test.runTheBank.adapter.controller.response.TransactionResponse;
import com.wendel.test.runTheBank.domain.Transaction;

public interface SaveTransaction {
    void execute(Transaction transaction);
}
