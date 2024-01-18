package com.wendel.test.runTheBank.domain.enuns;

public enum AccountStatus {
    ACTIVE, INACTIVE;

    public static AccountStatus getAccountStatusByString(String string){
        if (string.equals("ACTIVE")) {
            return ACTIVE;
        }
        return INACTIVE;
    }
}
