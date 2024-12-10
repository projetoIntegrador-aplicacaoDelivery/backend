package com.generation.AplicacaoDelivery.controller;


import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.generation.AplicacaoDelivery.model.Usuario;
import com.generation.AplicacaoDelivery.repository.UsuarioRepository;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/usuario")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class UsuarioController {
    
    @Autowired
    private UsuarioRepository UsuarioRepository;
    
    @GetMapping
    public ResponseEntity<List<Usuario>> getAll(){
        return ResponseEntity.ok(UsuarioRepository.findAll());
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<Usuario> getById(@PathVariable Long id){
        return UsuarioRepository.findById(id)
            .map(resposta -> ResponseEntity.ok(resposta))
            .orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }
    
    @GetMapping("/nome/{nome}")
    public ResponseEntity<Optional<Usuario>> getByNome(@PathVariable String nome) {
        Optional<Usuario> usuarios = UsuarioRepository.findBynomeContainingIgnoreCase(nome);
        if (usuarios.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return ResponseEntity.ok(usuarios);
    }

    
    @PostMapping
    public ResponseEntity<Usuario> post(@Valid @RequestBody Usuario usuario){
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(UsuarioRepository.save(usuario));
    }
    
    @PutMapping
    public ResponseEntity<Usuario> put(@Valid @RequestBody Usuario usuario){
        return UsuarioRepository.findById(usuario.getId())
            .map(resposta -> ResponseEntity.status(HttpStatus.CREATED)
            .body(UsuarioRepository.save(usuario)))
            .orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }
    
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        Optional<Usuario> usuario = UsuarioRepository.findById(id);
        
        if(usuario.isEmpty())
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        
        UsuarioRepository.deleteById(id);              
    }

}