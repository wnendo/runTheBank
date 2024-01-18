package com.wendel.test.runTheBank.adapter.controller;

import com.wendel.test.runTheBank.adapter.controller.request.AccountRequest;
import com.wendel.test.runTheBank.adapter.controller.request.RegisterRequest;
import com.wendel.test.runTheBank.adapter.controller.response.AccountResponse;
import com.wendel.test.runTheBank.adapter.controller.response.RegisterResponse;
import com.wendel.test.runTheBank.domain.Account;
import com.wendel.test.runTheBank.usecase.account.CreateAccount;
import com.wendel.test.runTheBank.usecase.account.GetAccount;
import com.wendel.test.runTheBank.usecase.register.CreateRegister;
import com.wendel.test.runTheBank.usecase.register.GetRegister;
import io.unlogged.Unlogged;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/account")
public class AccountController {
    private final CreateAccount createAccount;
    private final GetAccount getAccount;

    public AccountController(CreateAccount createAccount, GetAccount getAccount) {
        this.createAccount = createAccount;
        this.getAccount = getAccount;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.OK)
    public AccountResponse createAccount(
            @RequestBody AccountRequest accountRequest) {
        return createAccount.execute(accountRequest);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public AccountResponse getRegister(
            @RequestParam String id) {
        return getAccount.execute(id);
    }
}
