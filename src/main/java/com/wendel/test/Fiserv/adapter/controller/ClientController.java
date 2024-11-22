package com.wendel.test.Fiserv.adapter.controller;

import com.wendel.test.Fiserv.adapter.controller.request.ClientRequest;
import com.wendel.test.Fiserv.adapter.controller.response.ClientResponse;
import com.wendel.test.Fiserv.adapter.gateway.util.ClientValidate;
import com.wendel.test.Fiserv.usecase.client.CreateClient;
import com.wendel.test.Fiserv.usecase.client.DeleteClient;
import com.wendel.test.Fiserv.usecase.client.GetClient;
import com.wendel.test.Fiserv.usecase.client.UpdateClient;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/client")
public class ClientController {
    private final CreateClient createClient;
    private final GetClient getClient;
    private final DeleteClient deleteClient;
    private final UpdateClient updateClient;
    private final ClientValidate clientValidate;

    public ClientController(CreateClient createClient, GetClient getClient, DeleteClient deleteClient, UpdateClient updateClient, ClientValidate clientValidate) {
        this.createClient = createClient;
        this.getClient = getClient;
        this.deleteClient = deleteClient;
        this.updateClient = updateClient;
        this.clientValidate = clientValidate;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.OK)
    public ClientResponse createClient(
            @RequestBody ClientRequest clientRequest) {
        clientValidate.validate(clientRequest);
        return createClient.execute(clientRequest);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public ClientResponse getClient(
            @RequestParam String cpf) {
        return getClient.execute(cpf);
    }


    @DeleteMapping
    @ResponseStatus(HttpStatus.OK)
    public void deleteClient(
            @RequestParam String cpf) {
        deleteClient.execute(cpf);
    }


    @PutMapping
    @ResponseStatus(HttpStatus.OK)
    public ClientResponse updateClient(
            @RequestBody ClientRequest clientRequest) {
        clientValidate.validate(clientRequest);
        return updateClient.execute(clientRequest);
    }
}
