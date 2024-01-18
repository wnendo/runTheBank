package com.wendel.test.runTheBank.adapter.config.properties;

import com.wendel.test.runTheBank.adapter.gateway.util.CpfCnpjValidator;
import com.wendel.test.runTheBank.adapter.gateway.util.RegisterValidate;
import com.wendel.test.runTheBank.adapter.gateway.util.TransactionValidate;
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
    public TransactionValidate transactionValidate(){
        return new TransactionValidate();
    }
    @Bean
    public RegisterValidate registerValidate(){
        return new RegisterValidate(new CpfCnpjValidator());
    }
    @Bean
    public CpfCnpjValidator cpfCnpjValidator(){
        return new CpfCnpjValidator();
    }
}
