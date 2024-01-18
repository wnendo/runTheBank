package com.wendel.test.runTheBank.usecase.register;

import com.wendel.test.runTheBank.adapter.controller.request.RegisterRequest;
import com.wendel.test.runTheBank.adapter.controller.response.RegisterResponse;

public interface CreateRegister {
    RegisterResponse execute(RegisterRequest registerRequest);
}
