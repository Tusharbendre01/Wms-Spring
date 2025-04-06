package com.project.SpringEMS.controller;

import com.project.SpringEMS.entity.ClientEntity;
import com.project.SpringEMS.service.ClientService;
import com.project.SpringEMS.service.PortfolioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/clients")
public class ClientController {

    private final ClientService clientService;

    @Autowired
    public ClientController(ClientService clientService) {
        this.clientService = clientService;
    }
    @Autowired
    private PortfolioService portfolioService; // Add this to inject PortfolioService

    @GetMapping("/{clientId}/portfolios")
    public String viewClientPortfolios(@PathVariable Long clientId, Model model) {
        ClientEntity client = clientService.getClientById(clientId); // assume this method exists
        model.addAttribute("client", client);
        model.addAttribute("portfolios", portfolioService.getPortfoliosByClientId(clientId));
        return "portfolios/list"; // Thymeleaf template to show portfolios for a client
    }

    // Display form
    @GetMapping("/new")
    public String showAddClientForm(Model model) {
        model.addAttribute("client", new ClientEntity());
        return "clients/form"; // adjust this if your view name is different
    }

    // ✅ This is what you're missing
    @PostMapping("/save")
    public String saveClient(@ModelAttribute("client") ClientEntity client) {
        clientService.saveClient(client); // make sure this method exists
        return "redirect:/clients"; // or wherever you want to redirect after saving
    }

    // View clients
    @GetMapping
    public String listClients(Model model) {
        model.addAttribute("clients", clientService.getAllClients());
        return "clients/list";
    }

}
