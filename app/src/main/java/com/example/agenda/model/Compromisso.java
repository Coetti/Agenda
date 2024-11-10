package com.example.agenda.model;

public class Compromisso {
    private String descricao;
    private String data;
    private String hora;


    public Compromisso(String descricao, String data, String hora){
        this.descricao = descricao;
        this.data = data;
        this.hora = hora;
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
        return "Compromisso" + '\n' +
                descricao + '\n' +
                "Data: " + data +
                " Hora: " + hora + '\n';
    }
}
