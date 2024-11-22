package com.wendel.test.Fiserv.account;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.wendel.test.Fiserv.adapter.controller.ClientController;
import com.wendel.test.Fiserv.adapter.gateway.util.ClientValidate;
import com.wendel.test.Fiserv.usecase.client.CreateClient;
import com.wendel.test.Fiserv.usecase.client.DeleteClient;
import com.wendel.test.Fiserv.usecase.client.GetClient;
import com.wendel.test.Fiserv.usecase.client.UpdateClient;
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
    @Mock
    private UpdateClient updateClient;

    private final ObjectMapper objectMapper = new ObjectMapper();

    @Before
    public void setup() {
        createClient = Mockito.mock(CreateClient.class);
        clientController = new ClientController(createClient, getClient, deleteClient, updateClient, clientValidate);
    }

}
