package com.example.agenda.model;

public class Compromisso {
    private String nome;
    private String descricao;
    private String data;
    private String hora;


    public Compromisso(String nome, String descricao, String data, String hora){
        this.nome = nome;
        this.descricao = descricao;
        this.data = data;
        this.hora = hora;
    }

    public String getNome(){
        return nome;
    }

    public String getDescricao(){
        return descricao;
    }

    public String getData(){
        return data;
    }

    public String getHora(){
        return hora;
    }

    public void setNome(String nome){
        this.nome = nome;
    }

    public void setDescricao(String descricao){
        this.descricao = descricao;
    }

    public void setData(String data){
        this.data = data;
    }

    public void setHora(String hora){
        this.hora = hora;
    }

    public String imprimeCompromisso() {
        return "Compromisso{" +
                "nome='" + nome + '\'' +
                ", descrição='" + descricao + '\'' +
                ", data='" + data + '\'' +
                ", hora='" + hora + '\'' +
                '}';
    }
}
