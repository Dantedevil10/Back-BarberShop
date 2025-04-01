package com.example.dbarber.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.dbarber.dto.Login.LoginRequestDTO;
import com.example.dbarber.dto.Login.LoginResponseDTO;
import com.example.dbarber.models.Cliente;
import com.example.dbarber.repository.ClienteRepository;

@RestController
@RequestMapping("/auth")
@CrossOrigin("http://localhost:4200")
public class AuthController {

    @Autowired
    private ClienteRepository clientRepo;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequestDTO loginRequest) {
       
        Optional<Cliente> usuario = clientRepo.findByNameAndEmail(loginRequest.getName(), loginRequest.getEmail());
        if (usuario.isPresent()) {
            return ResponseEntity.ok(new LoginResponseDTO(usuario.get().getId(),usuario.get().getName()));
        }

        
        return ResponseEntity.status(401).body("Credenciais inválidas");
    }

}
