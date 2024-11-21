package com.wendel.test.runTheBank.account;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.wendel.test.runTheBank.adapter.controller.ClientController;
import com.wendel.test.runTheBank.adapter.gateway.util.ClientValidate;
import com.wendel.test.runTheBank.usecase.client.CreateClient;
import com.wendel.test.runTheBank.usecase.client.DeleteClient;
import com.wendel.test.runTheBank.usecase.client.GetClient;
import org.junit.Before;
import org.mockito.Mock;
import org.mockito.Mockito;

public final class ClientTest {
    private ClientController clientController;
    @Mock
    private CreateClient createClient;
    @Mock
    private GetClient getClient;
    @Mock
    private DeleteClient deleteClient;
    @Mock
    private ClientValidate clientValidate;

    private final ObjectMapper objectMapper = new ObjectMapper();

    @Before
    public void setup() {
        createClient = Mockito.mock(CreateClient.class);
        clientController = new ClientController(createClient, getClient, deleteClient, clientValidate);
    }

}
