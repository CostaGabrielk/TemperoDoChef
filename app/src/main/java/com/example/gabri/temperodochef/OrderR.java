package com.example.gabri.temperodochef;

public class OrderR {

    private String orderId, opcao, tamanho, formaPgto, troco;

    public OrderR() {

    }

    public OrderR(String orderId, String opcao, String tamanho, String formaPgto, String troco) {
        this.orderId = orderId;
        this.opcao = opcao;
        this.tamanho = tamanho;
        this.formaPgto = formaPgto;
        this.troco = troco;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getOpcao() {
        return opcao;
    }

    public void setOpcao(String opcao) {
        this.opcao = opcao;
    }

    public String getTamanho() {
        return tamanho;
    }

    public void setTamanho(String tamanho) {
        this.tamanho = tamanho;
    }

    public String getFormaPgto() {
        return formaPgto;
    }

    public void setFormaPgto(String formaPgto) {
        this.formaPgto = formaPgto;
    }

    public String getTroco() {
        return troco;
    }

    public void setTroco(String troco) {
        this.troco = troco;
    }
}
