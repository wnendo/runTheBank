package com.wendel.test.runTheBank.transaction;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.wendel.test.runTheBank.adapter.controller.mapper.AccountMapper;
import com.wendel.test.runTheBank.adapter.controller.mapper.TransactionMapper;
import com.wendel.test.runTheBank.adapter.controller.request.TransactionRequest;
import com.wendel.test.runTheBank.adapter.controller.response.AccountResponse;
import com.wendel.test.runTheBank.adapter.controller.response.NotificationResponse;
import com.wendel.test.runTheBank.adapter.controller.response.TransactionResponse;
import com.wendel.test.runTheBank.domain.Account;
import com.wendel.test.runTheBank.domain.Transaction;
import com.wendel.test.runTheBank.usecase.account.GetAccount;
import com.wendel.test.runTheBank.usecase.notification.SendNotification;
import com.wendel.test.runTheBank.usecase.transaction.CreateTransaction;
import com.wendel.test.runTheBank.usecase.transaction.GetTransaction;
import com.wendel.test.runTheBank.usecase.transaction.SaveTransaction;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;

public final class TransactionTest {
    private CreateTransaction createTransaction;

    private SaveTransaction saveTransaction;
    private GetTransaction getTransaction;

    private GetAccount getAccount;

    private SendNotification sendNotification;

    private AccountMapper accountMapper;

    private TransactionMapper transactionMapper;

    private ObjectMapper objectMapper = new ObjectMapper();

    @Before
    public void setup() throws Exception {
        saveTransaction = Mockito.mock(SaveTransaction.class);
        getAccount = Mockito.mock(GetAccount.class);
        sendNotification = Mockito.mock(SendNotification.class);
        accountMapper = Mockito.mock(AccountMapper.class);
        transactionMapper = Mockito.mock(TransactionMapper.class);
//        createTransaction = new CreateTransaction();
    }

    @Test
    public void testMethodExecute() throws Exception {
        Account convertAccountResponseToAccountResult = objectMapper.readValue("{\"id\":\"ab59a96b-ace9-47a2-9187-e3515597e19c\",\"registerId\":\"578a7bda-e2b5-46df-ab5d-92e0e109b0ce\",\"accountNumber\":\"265\",\"agency\":\"321221\",\"balance\":1000.00,\"accountStatus\":\"ACTIVE\"}", Account.class);
        Mockito.when(accountMapper.convertAccountResponseToAccount(any(AccountResponse.class))).thenReturn(convertAccountResponseToAccountResult);

        String id = "ab59a96b-ace9-47a2-9187-e3515597e19c";
        AccountResponse accountResponse = objectMapper.readValue("{\"id\":\"ab59a96b-ace9-47a2-9187-e3515597e19c\",\"registerId\":\"578a7bda-e2b5-46df-ab5d-92e0e109b0ce\",\"status\":\"ACTIVE\",\"accountNumber\":\"265\",\"message\":null,\"agency\":\"321221\",\"balance\":1000.00}", AccountResponse.class);
        Mockito.when(getAccount.execute(eq(id))).thenReturn(accountResponse);

        NotificationResponse executeResult = objectMapper.readValue("{\"message\":null}", NotificationResponse.class);
        Mockito.when(sendNotification.execute(any(Transaction.class))).thenReturn(executeResult);

//        String id1 = "df094ea6-23cd-4b51-8996-6c75f9e128ad";
//        executeResult = objectMapper.readValue("{\"id\":\"df094ea6-23cd-4b51-8996-6c75f9e128ad\",\"registerId\":\"578a7bda-e2b5-46df-ab5d-92e0e109b0ce\",\"status\":\"ACTIVE\",\"accountNumber\":\"8361\",\"message\":null,\"agency\":\"3211\",\"balance\":1000.00}", AccountResponse.class);
//        Mockito.when(getAccount.execute(eq(id1))).thenReturn(executeResult);

        Transaction transaction = objectMapper.readValue("{\"id\":\"52add811-50e6-4432-8831-0d0373d6c8cb\",\"fromAccount\":\"ab59a96b-ace9-47a2-9187-e3515597e19c\",\"toAccount\":\"df094ea6-23cd-4b51-8996-6c75f9e128ad\",\"amount\":100,\"date\":\"2024-01-18T18:16:06.5346211\",\"status\":null}", Transaction.class);
        Mockito.when(transactionMapper.convertTransactionRequestToTransaction(any(TransactionRequest.class))).thenReturn(transaction);

        convertAccountResponseToAccountResult = objectMapper.readValue("{\"id\":\"df094ea6-23cd-4b51-8996-6c75f9e128ad\",\"registerId\":\"578a7bda-e2b5-46df-ab5d-92e0e109b0ce\",\"accountNumber\":\"8361\",\"agency\":\"3211\",\"balance\":1000.00,\"accountStatus\":\"ACTIVE\"}", Account.class);
        Mockito.when(accountMapper.convertAccountResponseToAccount(any(AccountResponse.class))).thenReturn(convertAccountResponseToAccountResult);

        TransactionRequest transactionRequest = objectMapper.readValue("{\"id\":null,\"fromAccount\":\"ab59a96b-ace9-47a2-9187-e3515597e19c\",\"toAccount\":\"df094ea6-23cd-4b51-8996-6c75f9e128ad\",\"amount\":100,\"date\":null}", TransactionRequest.class);
        TransactionResponse transactionResponse = createTransaction.execute(transactionRequest);
        TransactionResponse transactionResponseExpected = objectMapper.readValue("{\"id\":\"52add811-50e6-4432-8831-0d0373d6c8cb\",\"fromAccount\":null,\"toAccount\":null,\"amount\":null,\"date\":null,\"message\":\"Transaction was successful\",\"status\":null}", TransactionResponse.class);
        Assert.assertEquals(transactionResponseExpected, transactionResponse);
    }
}
