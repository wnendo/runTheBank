package com.wendel.test.runTheBank.usecase.register;

import com.wendel.test.runTheBank.adapter.controller.response.RegisterResponse;
import com.wendel.test.runTheBank.domain.Register;

public interface GetRegister {
    RegisterResponse execute(String id);
}
