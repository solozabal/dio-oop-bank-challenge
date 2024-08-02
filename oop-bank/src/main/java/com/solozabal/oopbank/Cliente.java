package com.solozabal.oopbank;

public class Cliente {
    private String nome;
    private String email;
    private String senha;

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        // Add email validation here
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        // Add password validation here
        this.senha = senha;
    }
}