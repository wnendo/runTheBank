package com.wendel.test.Fiserv.adapter.controller.database.repository;

import com.wendel.test.Fiserv.adapter.controller.database.entity.AddressEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public interface AddressRepository extends JpaRepository<AddressEntity, String> {

}
