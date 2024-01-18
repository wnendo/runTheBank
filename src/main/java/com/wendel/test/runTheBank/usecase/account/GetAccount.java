package com.wendel.test.runTheBank.usecase.account;

import com.wendel.test.runTheBank.adapter.controller.response.AccountResponse;
import com.wendel.test.runTheBank.adapter.controller.response.RegisterResponse;
import com.wendel.test.runTheBank.domain.Account;

public interface GetAccount {
    Account execute(String id);
}
