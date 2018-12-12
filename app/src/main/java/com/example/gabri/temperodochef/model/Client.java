package com.example.gabri.temperodochef.model;

public class Client {
    String clienteId;
    String clienteNome;
    String clienteFone;
    String clienteEndereco;

    public Client(){

    }

    public Client(String clienteId, String clienteNome, String clienteFone, String clienteEndereco) {
        this.clienteId = clienteId;
        this.clienteNome = clienteNome;
        this.clienteFone = clienteFone;
        this.clienteEndereco = clienteEndereco;
    }

    public String getClienteId() {
        return clienteId;
    }

    public String getClienteNome() {
        return clienteNome;
    }

    public String getClienteFone() {
        return clienteFone;
    }

    public String getClienteEndereco() {
        return clienteEndereco;
    }
}
