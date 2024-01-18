package com.wendel.test.runTheBank.usecase.account;

import com.wendel.test.runTheBank.adapter.controller.response.AccountResponse;
import com.wendel.test.runTheBank.domain.Account;

public interface SaveAccount {
    void execute(Account account);
}
