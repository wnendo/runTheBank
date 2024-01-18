package com.wendel.test.runTheBank.domain.enuns;

public enum TransactionStatus {
    EXECUTED, REVOKED, CANCELED;
    public static TransactionStatus getTransactionStatusByString(String string){
        switch (string) {
            case "EXECUTED":
                return EXECUTED;
            case "REVOKED":
                return REVOKED;
            default:
                return CANCELED;
        }
    }
}
