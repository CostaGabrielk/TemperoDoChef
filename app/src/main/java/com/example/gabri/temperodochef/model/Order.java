package com.example.gabri.temperodochef.model;

public class Order {

    private String orderId;
    private String opcao;
    private String tamanho;
    private String formaPgto;
    private String troco;

    public Order(){

    }

    public Order( String orderId, String opcao, String tamanho, String formaPgto, String troco) {
        this.orderId = orderId;
        this.opcao = opcao;
        this.tamanho = tamanho;
        this.formaPgto = formaPgto;
        this.troco = troco ;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getOrderId() {
        return orderId;
    }


    public void setOpcao(String opcao) {
        this.opcao = opcao;
    }

    public String getOpcao() {
        return opcao;
    }

    public void setTamanho(String tamanho) {
        this.tamanho = tamanho;
    }

    public String getTamanho() {
        return tamanho;
    }

    public void setFormaPgto(String formaPgto) {
        this.formaPgto = formaPgto;
    }


    public String getFormaPgto() {
        return formaPgto;
    }

    public void setTroco(String troco) {
        this.troco = troco;
    }

    public String getTroco() {
        return troco;
    }
}
