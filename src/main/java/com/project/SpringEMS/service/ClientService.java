package com.project.SpringEMS.service;

import com.project.SpringEMS.entity.ClientEntity;
import com.project.SpringEMS.repository.ClientRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClientService {


    private final ClientRepository clientRepository;
    @Autowired
    public ClientService(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }
    public List<ClientEntity> getAllClients() {
        return clientRepository.findAll();
    }

    public void saveClient(ClientEntity client) {
        clientRepository.save(client);
    }

    public ClientEntity getClientById(Long clientId) {
        return clientRepository.findById(Math.toIntExact(clientId))
                .orElseThrow(() -> new IllegalArgumentException("Client not found with id: " + clientId));
    }
}
