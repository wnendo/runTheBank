package com.wendel.test.Fiserv.adapter.config.properties;

import com.wendel.test.Fiserv.adapter.gateway.util.AddressValidate;
import com.wendel.test.Fiserv.adapter.gateway.util.CpfCnpjValidator;
import com.wendel.test.Fiserv.adapter.gateway.util.ClientValidate;
import okhttp3.OkHttpClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ApiConfig {

    @Bean
    public OkHttpClient okHttpClient(){
        return new OkHttpClient.Builder().build();
    }
    @Bean
    public ClientValidate clientValidate(){
        return new ClientValidate(new CpfCnpjValidator());
    }
    @Bean
    public AddressValidate addressValidate(){
        return new AddressValidate();
    }
    @Bean
    public CpfCnpjValidator cpfCnpjValidator(){
        return new CpfCnpjValidator();
    }
}
