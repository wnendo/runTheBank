package com.wendel.test.Fiserv.adapter.controller.database.repository;

import com.wendel.test.Fiserv.adapter.controller.database.entity.ClientEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Repository
@Transactional
public interface ClientRepository extends JpaRepository<ClientEntity, String> {

    Optional<ClientEntity> findByCpf(String cpf);
    @Modifying
    @Query("update ClientEntity set deleted = true where cpf=:cpf")
    void updateClient(@Param("cpf") String cpf);

}
