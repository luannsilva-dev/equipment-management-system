package com.fjalves.gestao.model;

import jakarta.persistence.*;

@Entity
@Table(name = "utilizadores")
public class Utilizador {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    private String nome;
    private String email;
    private String departamento;
    private String contacto;

    // Construtores
    public Utilizador() {}

    public Utilizador(String nome, String email, String departamento, String contacto) {
        this.nome = nome;
        this.email = email;
        this.departamento = departamento;
        this.contacto = contacto;
    }

    // Getters e Setters
    public String getId() { return id; }
    public void setId(String id) { this.id = id; }

    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getDepartamento() { return departamento; }
    public void setDepartamento(String departamento) { this.departamento = departamento; }

    public String getContacto() { return contacto; }
    public void setContacto(String contacto) { this.contacto = contacto; }
}