package com.example.dbarber.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.dbarber.services.ClienteService;
import com.example.dbarber.models.Cliente;

@RestController
@RequestMapping("/user")
@CrossOrigin("http://localhost:4200")
public class ClienteController {

    @Autowired
    private ClienteService cliService;

    @PostMapping("/save")
    public ResponseEntity criarUsuario(@RequestBody Cliente cliente){
        return ResponseEntity.ok(cliService.salvarCliente(cliente));
    }
    @PutMapping("/edit/{id}")
    public ResponseEntity editarUsuario(@PathVariable Long id, @RequestBody Cliente cliente){
        return ResponseEntity.ok(cliService.editarCliente(id,cliente));
    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> excluirUsuario(@PathVariable Long id){
        cliService.deletarCliente(id);
        return ResponseEntity.noContent().build();
    }
    @GetMapping("/{id}")
    public ResponseEntity dadosDeUsuario(@PathVariable Long id){
        return ResponseEntity.ok(cliService.findById(id));
    }

}
