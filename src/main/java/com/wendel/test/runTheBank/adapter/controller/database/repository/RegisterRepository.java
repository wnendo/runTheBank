package com.wendel.test.runTheBank.adapter.controller.database.repository;

import com.wendel.test.runTheBank.adapter.controller.database.entity.RegisterEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RegisterRepository extends JpaRepository<RegisterEntity, String> {

}
