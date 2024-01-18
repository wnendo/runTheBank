package com.wendel.test.runTheBank.adapter.gateway.db;

import com.wendel.test.runTheBank.domain.Account;
import com.wendel.test.runTheBank.domain.Register;
import com.wendel.test.runTheBank.domain.Transaction;

public interface DbGateway {

    void saveRegister(Register register);
    Register getRegister(String id);
    void saveAccount(Account account);
    Account getAccount(String id);
    void saveTransaction(Transaction transaction);
    Transaction getTransaction(String id);
}
