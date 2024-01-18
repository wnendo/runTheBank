package com.wendel.test.runTheBank.adapter.controller;

import com.wendel.test.runTheBank.adapter.controller.request.RegisterRequest;
import com.wendel.test.runTheBank.adapter.controller.response.RegisterResponse;
import com.wendel.test.runTheBank.adapter.gateway.util.RegisterValidate;
import com.wendel.test.runTheBank.usecase.register.CreateRegister;
import com.wendel.test.runTheBank.usecase.register.GetRegister;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/register")
public class RegisterController {
    private final CreateRegister createRegister;
    private final GetRegister getRegister;
    private final RegisterValidate registerValidate;

    public RegisterController(CreateRegister createRegister, GetRegister getRegister, RegisterValidate registerValidate) {
        this.createRegister = createRegister;
        this.getRegister = getRegister;
        this.registerValidate = registerValidate;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.OK)
    public RegisterResponse createRegister(
            @RequestBody RegisterRequest registerRequest) {
        registerValidate.validate(registerRequest);
        return createRegister.execute(registerRequest);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public RegisterResponse getRegister(
            @RequestParam String id) {
        return getRegister.execute(id);
    }
}
