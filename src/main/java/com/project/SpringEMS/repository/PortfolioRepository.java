package com.project.SpringEMS.repository;

import com.project.SpringEMS.entity.PortfolioEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PortfolioRepository extends JpaRepository<PortfolioEntity,Integer> {
    List<PortfolioEntity> findByClient_ClientId(Long clientId);

    List<PortfolioEntity> findByClientClientId(Long clientId);

}    
