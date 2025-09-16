package com.example.projeto.model;

import jakarta.persistence.*;

@Entity
@Table(name = "veiculos")
public class Veiculo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String modelo;
    private Integer anoFabricacao;
    private String placa;

    public Veiculo() {
    }

    public Veiculo(Long id, String modelo, Integer anoFabricacao, String placa) {
        this.id = id;
        this.modelo = modelo;
        this.anoFabricacao = anoFabricacao;
        this.placa = placa;
    }

    public Long getId() {
        return id;
    }

    public String getModelo() {
        return modelo;
    }

    public Integer getAnoFabricacao() {
        return anoFabricacao;
    }

    public String getPlaca() {
        return placa;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public void setAnoFabricacao(Integer anoFabricacao) {
        this.anoFabricacao = anoFabricacao;
    }

    public void setPlaca(String placa) {
        this.placa = placa;
    }
}