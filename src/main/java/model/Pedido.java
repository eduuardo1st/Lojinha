package model;

public class Pedido {
    private String dataCriacao;
    private String status;
    private Double valorTotal;


    public String getDataCriacao() {
        return dataCriacao;
    }

    public void setDataCriacao(String dataCriacao) {
        this.dataCriacao = dataCriacao;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Double getValorTotal() {
        return valorTotal;
    }

    public void setValorTotal(Double valorTotal) {
        this.valorTotal = valorTotal;
    }

    public Pedido(String dataCriacao, String status, Double valorTotal) {
        this.dataCriacao = dataCriacao;
        this.status = status;
        this.valorTotal = valorTotal;
    }

    @Override
    public String toString() {
        return "Pedido = " +
                "dataCriacao='" + dataCriacao + '\'' +
                ", status='" + status + '\'' +
                ", valorTotal=" + valorTotal;
    }
}
