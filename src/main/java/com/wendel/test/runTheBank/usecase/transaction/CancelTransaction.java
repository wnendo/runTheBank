package com.wendel.test.runTheBank.usecase.transaction;

import com.wendel.test.runTheBank.adapter.controller.response.TransactionResponse;

public interface CancelTransaction {
    TransactionResponse execute(String id);
}
