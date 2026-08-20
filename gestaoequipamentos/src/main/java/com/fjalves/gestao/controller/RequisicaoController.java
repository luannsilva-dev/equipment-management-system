package com.fjalves.gestao.controller;

import com.fjalves.gestao.model.Equipamento;
import com.fjalves.gestao.model.Requisicao;
import com.fjalves.gestao.model.Utilizador;
import com.fjalves.gestao.repository.EquipamentoRepository;
import com.fjalves.gestao.repository.RequisicaoRepository;
import com.fjalves.gestao.repository.UtilizadorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/requisicoes")
@CrossOrigin(origins = "*")
public class RequisicaoController {

    @Autowired
    private RequisicaoRepository requisicaoRepository;

    @Autowired
    private EquipamentoRepository equipamentoRepository;

    @Autowired
    private UtilizadorRepository utilizadorRepository;

    // Listar todo o histórico de requisições
    @GetMapping
    public List<Requisicao> listarTodas() {
        return requisicaoRepository.findAll();
    }

    // efetuar Requisição (Regra de Negócio: Bloquear se indisponível)
    @PostMapping
    public ResponseEntity<?> criarRequisicao(@RequestBody Requisicao novaReq) {
        
        Equipamento equipamento = equipamentoRepository.findById(novaReq.getEquipamentoId()).orElse(null);
        Utilizador utilizador = utilizadorRepository.findById(novaReq.getUtilizadorId()).orElse(null);

        if (equipamento == null || utilizador == null) {
            return ResponseEntity.badRequest().body("Equipamento ou Utilizador não encontrado!");
        }

        // Regra de Ouro do Enunciado: Verificar se está Disponível
        if (!"Disponível".equalsIgnoreCase(equipamento.getEstado())) {
            return ResponseEntity.badRequest().body("O equipamento não pode ser requisitado porque está: " + equipamento.getEstado());
        }

        // Atualizar dados da requisição
        novaReq.setNomeEquipamento(equipamento.getNome());
        novaReq.setNomeUtilizador(utilizador.getNome());
        novaReq.setDataRequisicao(LocalDate.now());
        novaReq.setEstadoRequisicao("ATIVA");

        // Regra do Enunciado: Mudar estado do Equipamento para "Requisitado"
        equipamento.setEstado("Requisitado");
        equipamentoRepository.save(equipamento);

        Requisicao salva = requisicaoRepository.save(novaReq);
        return ResponseEntity.ok(salva);
    }

    // efetuar Devolução (Regra de Negócio: Voltar para Disponível)
    @PutMapping("/{id}/devolucao")
    public ResponseEntity<?> devolverEquipamento(@PathVariable String id) {
        Requisicao requisicao = requisicaoRepository.findById(id).orElse(null);

        if (requisicao == null) {
            return ResponseEntity.badRequest().body("Requisição não encontrada!");
        }

        if ("DEVOLVIDA".equals(requisicao.getEstadoRequisicao())) {
            return ResponseEntity.badRequest().body("Este equipamento já foi devolvido anteriormente!");
        }

        // Atualizar estado da requisição
        requisicao.setDataDevolucaoEfetiva(LocalDate.now());
        requisicao.setEstadoRequisicao("DEVOLVIDA");
        requisicaoRepository.save(requisicao);

        // Regra do Enunciado: Voltar o equipamento para "Disponível"
        Equipamento equipamento = equipamentoRepository.findById(requisicao.getEquipamentoId()).orElse(null);
        if (equipamento != null) {
            equipamento.setEstado("Disponível");
            equipamentoRepository.save(equipamento);
        }

        return ResponseEntity.ok("Equipamento devolvido com sucesso e atualizado para Disponível!");
    }
}