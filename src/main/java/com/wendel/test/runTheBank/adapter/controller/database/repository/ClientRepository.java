package com.wendel.test.runTheBank.adapter.controller.database.repository;

import com.wendel.test.runTheBank.adapter.controller.database.entity.ClientEntity;
import com.wendel.test.runTheBank.adapter.controller.response.ClientResponse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ClientRepository extends JpaRepository<ClientEntity, String> {

    Optional<ClientEntity> findByCpf(String cpf);

}
