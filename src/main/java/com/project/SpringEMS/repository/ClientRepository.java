package com.project.SpringEMS.repository;

import com.project.SpringEMS.entity.ClientEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClientRepository extends JpaRepository<ClientEntity,Integer> {
}
