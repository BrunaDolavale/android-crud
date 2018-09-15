package com.example.user.brunadolavaleat.Activity.Entities;

import com.example.user.brunadolavaleat.DAO.ConfigFirebase;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.Exclude;

import java.util.HashMap;
import java.util.Map;

public class User {
    private String id;
    private String email;
    private String senha;
    private String nome;
    private String sobrenome;
    private String aniversario;
    private String sexo;

    public User (){

    }

    public void save() {
        DatabaseReference databaseReference = ConfigFirebase.getFirebase();
        databaseReference.child("user").child(String.valueOf(getId())).setValue(this);
    }

    @Exclude
    public Map<String, Object> toMap() {
        HashMap<String, Object> hashmapUser = new HashMap<>();

        hashmapUser.put("id", getId());
        hashmapUser.put("senha", getSenha());
        hashmapUser.put("sobrenome", getSobrenome());
        hashmapUser.put("nome", getNome());
        hashmapUser.put("aniversario", getAniversario());
        hashmapUser.put("sexo", getSexo());

        return hashmapUser;

    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getSobrenome() {
        return sobrenome;
    }

    public void setSobrenome(String sobrenome) {
        this.sobrenome = sobrenome;
    }

    public String getAniversario() {
        return aniversario;
    }

    public void setAniversario(String aniversario) {
        this.aniversario = aniversario;
    }

    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

}

