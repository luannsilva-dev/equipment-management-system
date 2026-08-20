package com.fjalves.gestao.model;

import jakarta.persistence.*;

@Entity
@Table(name = "equipamentos")
public class Equipamento {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID) // Gera IDs únicos em texto automaticamente
    private String id;

    private String nome;
    private String tipo;
    private String marca;
    private String numeroSerie;
    private String estado;
    private String observacoes;

    // Construtores
    public Equipamento() {}

    public Equipamento(String nome, String tipo, String marca, String numeroSerie, String estado, String observacoes) {
        this.nome = nome;
        this.tipo = tipo;
        this.marca = marca;
        this.numeroSerie = numeroSerie;
        this.estado = estado;
        this.observacoes = observacoes;
    }

    // Getters e Setters
    public String getId() { return id; }
    public void setId(String id) { this.id = id; }

    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }

    public String getTipo() { return tipo; }
    public void setTipo(String tipo) { this.tipo = tipo; }

    public String getMarca() { return marca; }
    public void setMarca(String marca) { this.marca = marca; }

    public String getNumeroSerie() { return numeroSerie; }
    public void setNumeroSerie(String numeroSerie) { this.numeroSerie = numeroSerie; }

    public String getEstado() { return estado; }
    public void setEstado(String estado) { this.estado = estado; }

    public String getObservacoes() { return observacoes; }
    public void setObservacoes(String observacoes) { this.observacoes = observacoes; }
}