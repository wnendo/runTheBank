package com.wendel.test.runTheBank.adapter.controller;

import com.wendel.test.runTheBank.adapter.controller.response.AddressResponse;
import com.wendel.test.runTheBank.usecase.address.GetAddress;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/address")
public class AddressController {
    private final GetAddress getAddress;

    public AddressController(GetAddress getAddress) {
        this.getAddress = getAddress;
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public AddressResponse getRegister(
            @RequestParam String id) {
        return getAddress.execute(id);
    }

}
