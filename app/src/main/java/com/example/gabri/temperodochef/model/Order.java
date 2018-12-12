package com.example.gabri.temperodochef.model;

public class Order {

    private String orderId;
    private String opcao;
    private String tamanho;
    private String formaPgto;
    private String troco;

    public Order(){

    }

    public Order(String orderId, String opcao, String tamanho, String formaPgto, String troco) {
        this.orderId = orderId;
        this.opcao = opcao;
        this.tamanho = tamanho;
        this.formaPgto = formaPgto;
        this.troco = troco ;
    }

    public String getOrderId() {
        return orderId;
    }

    public String getOpcao() {
        return opcao;
    }

    public String getTamanho() {
        return tamanho;
    }

    public String getFormaPgto() {
        return formaPgto;
    }

    public String getTroco() {
        return troco;
    }
}
