package com.wendel.test.runTheBank.usecase.transaction;

import com.wendel.test.runTheBank.adapter.controller.request.TransactionRequest;
import com.wendel.test.runTheBank.adapter.controller.response.TransactionResponse;

public interface RevokeTransaction {
    TransactionResponse execute(String id);
}
