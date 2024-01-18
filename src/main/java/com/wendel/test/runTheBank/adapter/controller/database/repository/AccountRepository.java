package com.wendel.test.runTheBank.adapter.controller.database.repository;

import com.wendel.test.runTheBank.adapter.controller.database.entity.AccountEntity;
import com.wendel.test.runTheBank.adapter.controller.database.entity.RegisterEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountRepository extends JpaRepository<AccountEntity, String> {

}
