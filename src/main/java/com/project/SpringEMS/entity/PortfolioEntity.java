package com.project.SpringEMS.entity;

import jakarta.persistence.*;

import java.util.Date;

@Entity
public class PortfolioEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int portfolioId;

    @ManyToOne
    @JoinColumn(name = "client_id", nullable = false)
    private ClientEntity client;

    private Double totalValue;

    @Column(columnDefinition = "TEXT")
    private String allocationSummary;

    @Temporal(TemporalType.TIMESTAMP)
    private Date lastUpdated;

    public int getPortfolioId() { return portfolioId; }
    public void setPortfolioId(int portfolioId) { this.portfolioId = portfolioId; }

    public ClientEntity getClient() { return client; }
    public void setClient(ClientEntity client) { this.client = client; }

    public Double getTotalValue() { return totalValue; }
    public void setTotalValue(Double totalValue) { this.totalValue = totalValue; }

    public String getAllocationSummary() { return allocationSummary; }
    public void setAllocationSummary(String allocationSummary) { this.allocationSummary = allocationSummary; }

    public Date getLastUpdated() { return lastUpdated; }
    public void setLastUpdated(Date lastUpdated) { this.lastUpdated = lastUpdated; }
}
