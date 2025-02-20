package br.insper.loja.compra;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Document
public class Compra {

    @Id
    private String id;
    private String usuario;
    private String nome;
    private List<String> produtos = new ArrayList<>();
    private LocalDateTime dataCompra;
    private double totalPreco; // Novo atributo para o preço total da compra

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public List<String> getProdutos() {
        return produtos;
    }

    public void setProdutos(List<String> produtos) {
        this.produtos = produtos;
    }

    public LocalDateTime getDataCompra() {
        return dataCompra;
    }

    public void setDataCompra(LocalDateTime dataCompra) {
        this.dataCompra = dataCompra;
    }

    public double getTotalPreco() {
        return totalPreco;
    }

    public void setTotalPreco(double totalPreco) {
        this.totalPreco = totalPreco;
    }
}
