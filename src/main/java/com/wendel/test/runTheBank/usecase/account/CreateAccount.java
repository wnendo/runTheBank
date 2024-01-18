package com.wendel.test.runTheBank.usecase.account;

import com.wendel.test.runTheBank.adapter.controller.request.AccountRequest;
import com.wendel.test.runTheBank.adapter.controller.request.RegisterRequest;
import com.wendel.test.runTheBank.adapter.controller.response.AccountResponse;

public interface CreateAccount {
    AccountResponse execute(AccountRequest accountRequest);
}
