package com.project.SpringEMS.service;

import com.project.SpringEMS.entity.PortfolioEntity;
import com.project.SpringEMS.repository.PortfolioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class PortfolioService {

    private final PortfolioRepository portfolioRepository;
    @Autowired
    public PortfolioService(PortfolioRepository portfolioRepository) {
        this.portfolioRepository = portfolioRepository;
    }

    public List<PortfolioEntity> getPortfoliosByClientId(Long clientId) {
        return portfolioRepository.findByClient_ClientId(clientId);
    }

    public PortfolioEntity rebalancePortfolio(int portfolioId) {
        Optional<PortfolioEntity> optional = portfolioRepository.findById(portfolioId);
        if (optional.isPresent()) {
            PortfolioEntity p = optional.get();
            p.setAllocationSummary("{\"Stocks\":50, \"Bonds\":30, \"Cash\":20}");
            p.setLastUpdated(new Date());
            return portfolioRepository.save(p);
        }
        return null;
    }

    public String getPortfolioPerformance(int portfolioId) {
        return "Portfolio " + portfolioId + " has gained 12.5% in the past year.";
    }
    public void savePortfolio(PortfolioEntity portfolio) {
        portfolio.setLastUpdated(new Date()); // auto set timestamp
        portfolioRepository.save(portfolio);
    }

    public PortfolioEntity getPortfolioById(Long portfolioId) {
        return portfolioRepository.findById(portfolioId.intValue())
                .orElseThrow(() -> new RuntimeException("Portfolio not found with id: " + portfolioId));
    }

    public PortfolioEntity rebalancePortfolio(Long portfolioId) {
        PortfolioEntity portfolio = getPortfolioById(portfolioId);

        // Example logic for rebalancing - you can update this as needed
        portfolio.setTotalValue(portfolio.getTotalValue() * 1.05); // Simulate a 5% growth
        portfolio.setAllocationSummary("Rebalanced: 50% Stocks, 30% Bonds, 20% Cash");
        portfolio.setLastUpdated(Date.from(LocalDateTime.now().atZone(ZoneId.systemDefault()).toInstant()));

        return portfolioRepository.save(portfolio);
    }

    public List<PortfolioEntity> getAllPortfolios() {
        return portfolioRepository.findAll();
    }
}