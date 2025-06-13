package br.com.cote.coteemprestimos.entities;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "emprestimos")
public class Emprestimo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "dataEmprestimo", nullable = false)
    private LocalDate dataEmprestimo;

    @Column(name = "moeda",nullable = false)
    private String moeda;

    @Column(name = "valor",nullable = false)
    private double valor;

    @Column(name = "taxaConversao", nullable = false)
    private Double taxaConversao;

    @Column(name = "dataVencimento", nullable = false)
    private LocalDate dataVencimento;

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getDataEmprestimo() {
        return dataEmprestimo;
    }

    public void setDataEmprestimo(LocalDate dataEmprestimo) {
        this.dataEmprestimo = dataEmprestimo;
    }

    public String getMoeda() {
        return moeda;
    }

    public void setMoeda(String moeda) {
        this.moeda = moeda;
    }

    public Double getTaxaConversao() {
        return taxaConversao;
    }

    public void setTaxaConversao(Double taxaConversao) {
        this.taxaConversao = taxaConversao;
    }

    public LocalDate getDataVencimento() {
        return dataVencimento;
    }

    public void setDataVencimento(LocalDate dataVencimento) {
        this.dataVencimento = dataVencimento;
    }
}
