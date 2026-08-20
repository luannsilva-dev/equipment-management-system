package com.fjalves.gestao.controller;

import com.fjalves.gestao.model.Utilizador;
import com.fjalves.gestao.repository.UtilizadorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/utilizadores")
@CrossOrigin(origins = "*")
public class UtilizadorController {

    @Autowired
    private UtilizadorRepository repository;

    @GetMapping
    public List<Utilizador> listarTodos() {
        return repository.findAll();
    }

    @PostMapping
    public Utilizador criar(@RequestBody Utilizador utilizador) {
        return repository.save(utilizador);
    }
}