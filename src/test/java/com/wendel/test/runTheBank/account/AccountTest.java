package com.wendel.test.runTheBank.account;


import static org.mockito.ArgumentMatchers.*;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.wendel.test.runTheBank.adapter.controller.AccountController;
import com.wendel.test.runTheBank.adapter.controller.request.AccountRequest;
import com.wendel.test.runTheBank.adapter.controller.response.AccountResponse;
import com.wendel.test.runTheBank.usecase.account.CreateAccount;
import com.wendel.test.runTheBank.usecase.account.GetAccount;
import java.lang.Exception;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

public final class AccountTest {
    private AccountController accountController;

    private CreateAccount createAccount;

    private GetAccount getAccount;

    private ObjectMapper objectMapper = new ObjectMapper();

    @Before
    public void setup() throws Exception {
        createAccount = Mockito.mock(CreateAccount.class);
        GetAccount getAccount = objectMapper.readValue("{\"dbGateway\":{\"registerRepository\":{},\"accountRepository\":{},\"transactionRepository\":{},\"registerMapperDomainAndEntity\":{\"encryptRequest\":{},\"decryptRequest\":{}},\"accountMapperDomainAndEntity\":{\"encryptRequest\":{},\"decryptRequest\":{}},\"transactionMapperDomainAndEntity\":{\"encryptRequest\":{},\"decryptRequest\":{}}},\"accountMapper\":{}}", GetAccount.class);
        accountController = new AccountController(createAccount, getAccount);
//        injectField(accountController, "createAccount", createAccount);
    }

    @Test
    public void testMethodCreateAccount() throws Exception {
        AccountResponse executeResult = objectMapper.readValue("{\"id\":null,\"registerId\":null,\"status\":null,\"accountNumber\":null,\"message\":\"[Error] while trying to CREATE account\",\"agency\":null,\"balance\":null}", AccountResponse.class);
        Mockito.when(createAccount.execute(any(AccountRequest.class))).thenReturn(executeResult);
        AccountRequest accountRequest = objectMapper.readValue("{\"registerId\":null,\"agency\":null,\"balance\":null,\"accountStatus\":null}", AccountRequest.class);
        executeResult = accountController.createAccount(accountRequest);
        AccountResponse executeResultExpected = objectMapper.readValue("{\"id\":null,\"registerId\":null,\"status\":null,\"accountNumber\":null,\"message\":\"[Error] while trying to CREATE account\",\"agency\":null,\"balance\":null}", AccountResponse.class);
        Assert.assertEquals(executeResultExpected, executeResult);
    }

    @Test
    public void testMethodGetRegister() throws Exception {
        String id = "1e2fbc00-7b90-40cd-aa10-9f0e002d8c9c";
        AccountResponse executeResult = objectMapper.readValue("{\"id\":\"1e2fbc00-7b90-40cd-aa10-9f0e002d8c9c\",\"registerId\":null,\"status\":null,\"accountNumber\":null,\"message\":\"Error while searching for account\",\"agency\":null,\"balance\":null}", AccountResponse.class);
        Mockito.when(getAccount.execute(eq(id))).thenReturn(executeResult);
        executeResult = accountController.getRegister(id);
        AccountResponse executeResultExpected = objectMapper.readValue("{\"id\":\"1e2fbc00-7b90-40cd-aa10-9f0e002d8c9c\",\"registerId\":null,\"status\":null,\"accountNumber\":null,\"message\":\"Error while searching for account\",\"agency\":null,\"balance\":null}", AccountResponse.class);
        Assert.assertEquals(executeResultExpected, executeResult);
    }
}
