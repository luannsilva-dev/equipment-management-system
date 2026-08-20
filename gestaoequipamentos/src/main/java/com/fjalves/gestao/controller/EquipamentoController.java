package com.fjalves.gestao.controller;

import com.fjalves.gestao.model.Equipamento;
import com.fjalves.gestao.repository.EquipamentoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/equipamentos")
@CrossOrigin(origins = "*") 
public class EquipamentoController {

    @Autowired
    private EquipamentoRepository repository;

    // GET: Listar todos os equipamentos
    @GetMapping
    public List<Equipamento> listarTodos() {
        return repository.findAll();
    }

    //Rota POST: Criar um novo equipamento
    @PostMapping
    public Equipamento criar(@RequestBody Equipamento equipamento) {
        return repository.save(equipamento);
    }

    //Rota PUT: Editar um equipamento existente por ID
    @PutMapping("/{id}")
    public ResponseEntity<Equipamento> editar(@PathVariable String id, @RequestBody Equipamento dadosNovos) {
        return repository.findById(id)
                .map(equipamento -> {
                    equipamento.setNome(dadosNovos.getNome());
                    equipamento.setTipo(dadosNovos.getTipo());
                    equipamento.setMarca(dadosNovos.getMarca());
                    equipamento.setNumeroSerie(dadosNovos.getNumeroSerie());
                    equipamento.setEstado(dadosNovos.getEstado());
                    equipamento.setObservacoes(dadosNovos.getObservacoes());
                    Equipamento atualizado = repository.save(equipamento);
                    return ResponseEntity.ok(atualizado);
                })
                .orElse(ResponseEntity.notFound().build());
    }

    // 4. Rota DELETE: Eliminar um equipamento por ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable String id) {
        if (repository.existsById(id)) {
            repository.deleteById(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}