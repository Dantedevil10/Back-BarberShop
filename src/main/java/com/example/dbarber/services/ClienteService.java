package com.example.dbarber.services;

import com.example.dbarber.repository.ClienteRepository;

import jakarta.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.dbarber.models.Cliente;

@Service
public class ClienteService {

    @Autowired
    private ClienteRepository cliRepo;

    public Cliente salvarCliente(Cliente cliente){
        return cliRepo.save(cliente);
    }
    public Cliente editarCliente(Long id, Cliente novoCliente){
        return cliRepo.findById(id)
            .map(cliente -> {
                cliente.setName(novoCliente.getName());
                cliente.setEmail(novoCliente.getEmail());
                cliente.setPhone(novoCliente.getPhone());
                return cliRepo.save(cliente);
            }).orElseThrow(() -> new EntityNotFoundException("Cliente não encontrado"));
    }
    public Cliente findById(Long id) {
		return cliRepo.findById(id).orElse(null);
	}
    public void deletarCliente(Long id){
        Cliente cliente = cliRepo.findById(id)
        .orElseThrow(() -> new EntityNotFoundException("Cliente não encontrado"));
    
        cliRepo.delete(cliente);
    }
}
