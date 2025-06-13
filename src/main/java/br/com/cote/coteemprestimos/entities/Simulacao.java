package br.com.cote.coteemprestimos.entities;

public class Simulacao {

    private long numeroMeses;
    private double valorPagoVencimento;
    private double taxaConversao;

    public Simulacao(long numeroMeses, double valorPagoVencimento, double taxaConversao) {
        this.numeroMeses = numeroMeses;
        this.valorPagoVencimento = valorPagoVencimento;
        this.taxaConversao = taxaConversao;
    }

    public double getTaxaConversao() {
        return taxaConversao;
    }

    public void setTaxaConversao(double taxaConversao) {
        this.taxaConversao = taxaConversao;
    }

    public long getNumeroMeses() {
        return numeroMeses;
    }

    public double getValorPagoVencimento() {
        return valorPagoVencimento;
    }
}
