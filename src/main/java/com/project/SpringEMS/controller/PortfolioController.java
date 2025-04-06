package com.project.SpringEMS.controller;

import com.project.SpringEMS.entity.ClientEntity;
import com.project.SpringEMS.entity.PortfolioEntity;
import com.project.SpringEMS.service.PortfolioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@Controller
@RequestMapping("/portfolios")
public class PortfolioController {

    private final PortfolioService portfolioService;
    @Autowired
    public PortfolioController(PortfolioService portfolioService) {
        this.portfolioService = portfolioService;
    }

    @GetMapping("/clients/{clientId}/portfolios")
    public String viewPortfoliosByClient(@PathVariable Long clientId, Model model) {
        List<PortfolioEntity> portfolios = portfolioService.getPortfoliosByClientId(clientId);
        model.addAttribute("portfolios", portfolios);
        model.addAttribute("clientId", clientId);
        return "portfolios/client-portfolios"; // create this Thymeleaf view
    }


    @GetMapping("/performance/{id}")
    public String getPortfolioPerformance(@PathVariable("id") Long portfolioId, Model model) {
        PortfolioEntity portfolio = portfolioService.getPortfolioById(portfolioId);
        model.addAttribute("portfolio", portfolio);
        return "portfolios/performance"; // this should be the Thymeleaf file name
    }

    @GetMapping("/rebalance/{id}")
    public String getRebalancedPortfolio(@PathVariable("id") Long portfolioId, Model model) {
        PortfolioEntity rebalancedPortfolio = portfolioService.rebalancePortfolio(portfolioId);
        model.addAttribute("portfolio", rebalancedPortfolio);
        return "portfolios/rebalanced"; // again, matches Thymeleaf file name
    }


    @GetMapping("/new/{clientId}")
    public String showCreatePortfolioForm(@PathVariable Long clientId, Model model) {
        PortfolioEntity portfolio = new PortfolioEntity();
        ClientEntity client = new ClientEntity();
        client.setClientId(clientId); // minimal info needed
        portfolio.setClient(client);

        model.addAttribute("portfolio", portfolio);
        return "portfolios/add-portfolio";
    }

    @PostMapping("/save")
    public String savePortfolio(@ModelAttribute("portfolio") PortfolioEntity portfolio) {
        portfolio.setLastUpdated(new java.util.Date());
        portfolioService.savePortfolio(portfolio);

        Long clientId = portfolio.getClient().getClientId();
        return "redirect:/clients/" + clientId + "/portfolios";
    }

    @GetMapping("/all")
    public String viewAllPortfolios(Model model) {
        List<PortfolioEntity> portfolios = portfolioService.getAllPortfolios();
        model.addAttribute("portfolios", portfolios);
        return "portfolios";
    }


}
