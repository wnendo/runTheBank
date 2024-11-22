package com.wendel.test.runTheBank.adapter.controller;

import com.wendel.test.runTheBank.adapter.controller.request.AddressRequest;
import com.wendel.test.runTheBank.adapter.controller.response.AddressFromViaCep;
import com.wendel.test.runTheBank.adapter.controller.response.AddressResponse;
import com.wendel.test.runTheBank.adapter.controller.response.ClientResponse;
import com.wendel.test.runTheBank.adapter.gateway.util.AddressValidate;
import com.wendel.test.runTheBank.usecase.address.CreateAddress;
import com.wendel.test.runTheBank.usecase.address.FindNewAddress;
import com.wendel.test.runTheBank.usecase.address.GetAddress;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/address")
public class AddressController {
    private final GetAddress getAddress;
    private final CreateAddress createAddress;
    private final AddressValidate addressValidate;
    private final FindNewAddress findNewAddress;

    public AddressController(GetAddress getAddress, CreateAddress createAddress, AddressValidate addressValidate, FindNewAddress findNewAddress) {
        this.getAddress = getAddress;
        this.createAddress = createAddress;
        this.addressValidate = addressValidate;
        this.findNewAddress = findNewAddress;
    }


    @PostMapping
    @ResponseStatus(HttpStatus.OK)
    public AddressResponse createAddress(
            @RequestBody AddressRequest addressRequest) {
        addressValidate.validate(addressRequest);
        return createAddress.execute(addressRequest);
    }
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public AddressResponse getAddress(
            @RequestParam String id) {
        return getAddress.execute(id);
    }


    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    @RequestMapping("/find")
    public AddressFromViaCep findNewAddress(
            @RequestParam String zipcode) {
        return findNewAddress.execute(zipcode);
    }

}
