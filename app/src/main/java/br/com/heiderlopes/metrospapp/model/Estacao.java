package br.com.heiderlopes.metrospapp.model;

import com.google.gson.annotations.SerializedName;

import java.util.Date;

public class Estacao {

    private String nome;
    private String endereco;
    private double latitude;
    private double longitude;
    @SerializedName("capacidade_passageiro_hora_pico")
    private int capacidadePassageiroHoraPico;
    @SerializedName("area_construida_m_2")
    private double areaConstruidaM2;
    @SerializedName("inauguracao")
    private String dataInauguracao;
    private String urlImagem;

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public int getCapacidadePassageiroHoraPico() {
        return capacidadePassageiroHoraPico;
    }

    public void setCapacidadePassageiroHoraPico(int capacidadePassageiroHoraPico) {
        this.capacidadePassageiroHoraPico = capacidadePassageiroHoraPico;
    }

    public double getAreaConstruidaM2() {
        return areaConstruidaM2;
    }

    public void setAreaConstruidaM2(double areaConstruidaM2) {
        this.areaConstruidaM2 = areaConstruidaM2;
    }

    public String getDataInauguracao() {
        return dataInauguracao;
    }

    public void setDataInauguracao(String dataInauguracao) {
        this.dataInauguracao = dataInauguracao;
    }

    public String getUrlImagem() {
        return urlImagem;
    }

    public void setUrlImagem(String urlImagem) {
        this.urlImagem = urlImagem;
    }
}
