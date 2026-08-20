package com.fjalves.gestao.model;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "requisicoes")
public class Requisicao {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    private String equipamentoId;
    private String nomeEquipamento;
    private String utilizadorId;
    private String nomeUtilizador;
    private LocalDate dataRequisicao;
    private LocalDate dataPrevistaDevolucao;
    private LocalDate dataDevolucaoEfetiva;
    private String estadoRequisicao; // "ATIVA" ou "DEVOLVIDA"

    // Construtores
    public Requisicao() {}

    public Requisicao(String equipamentoId, String nomeEquipamento, String utilizadorId, String nomeUtilizador, LocalDate dataPrevistaDevolucao) {
        this.equipamentoId = equipamentoId;
        this.nomeEquipamento = nomeEquipamento;
        this.utilizadorId = utilizadorId;
        this.nomeUtilizador = nomeUtilizador;
        this.dataRequisicao = LocalDate.now();
        this.dataPrevistaDevolucao = dataPrevistaDevolucao;
        this.estadoRequisicao = "ATIVA";
    }

    // Getters e Setters
    public String getId() { return id; }
    public void setId(String id) { this.id = id; }

    public String getEquipamentoId() { return equipamentoId; }
    public void setEquipamentoId(String equipamentoId) { this.equipamentoId = equipamentoId; }

    public String getNomeEquipamento() { return nomeEquipamento; }
    public void setNomeEquipamento(String nomeEquipamento) { this.nomeEquipamento = nomeEquipamento; }

    public String getUtilizadorId() { return utilizadorId; }
    public void setUtilizadorId(String utilizadorId) { this.utilizadorId = utilizadorId; }

    public String getNomeUtilizador() { return nomeUtilizador; }
    public void setNomeUtilizador(String nomeUtilizador) { this.nomeUtilizador = nomeUtilizador; }

    public LocalDate getDataRequisicao() { return dataRequisicao; }
    public void setDataRequisicao(LocalDate dataRequisicao) { this.dataRequisicao = dataRequisicao; }

    public LocalDate getDataPrevistaDevolucao() { return dataPrevistaDevolucao; }
    public void setDataPrevistaDevolucao(LocalDate dataPrevistaDevolucao) { this.dataPrevistaDevolucao = dataPrevistaDevolucao; }

    public LocalDate getDataDevolucaoEfetiva() { return dataDevolucaoEfetiva; }
    public void setDataDevolucaoEfetiva(LocalDate dataDevolucaoEfetiva) { this.dataDevolucaoEfetiva = dataDevolucaoEfetiva; }

    public String getEstadoRequisicao() { return estadoRequisicao; }
    public void setEstadoRequisicao(String estadoRequisicao) { this.estadoRequisicao = estadoRequisicao; }
}