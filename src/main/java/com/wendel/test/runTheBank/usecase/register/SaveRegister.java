package com.wendel.test.runTheBank.usecase.register;

import com.wendel.test.runTheBank.domain.Register;

public interface SaveRegister {
    void execute(Register register);
}
