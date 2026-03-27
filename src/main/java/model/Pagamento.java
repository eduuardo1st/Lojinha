package model;

import java.time.LocalDateTime;

public class Pagamento {
    private int idPagamento;
    private Pedido pedido;
    private String metodoPagamento;
    private double valorPago;
    private String statusPagamento;
    private LocalDateTime dataPagamento;
    private String idTransacaoExterna;

    public Pagamento(int idPagamento, Pedido pedido, String metodoPagamento, double valorPago) {
        this.idPagamento = idPagamento;
        this.pedido = pedido;
        this.metodoPagamento = metodoPagamento;
        this.valorPago = valorPago;
        this.dataPagamento = LocalDateTime.now();
        this.statusPagamento = "PENDENTE";
    }

    public int getIdPagamento() {
        return idPagamento;
    }

    public Pedido getPedido() {
        return pedido;
    }

    public String getMetodoPagamento() {
        return metodoPagamento;
    }

    public double getValorPago() {
        return valorPago;
    }

    public String getStatusPagamento() {
        return statusPagamento;
    }

    public LocalDateTime getDataPagamento() {
        return dataPagamento;
    }

    public String getIdTransacaoExterna() {
        return idTransacaoExterna;
    }

    public void setStatusPagamento(String statusPagamento) {
        this.statusPagamento = statusPagamento;
    }

    public void setIdTransacaoExterna(String idTransacaoExterna) {
        this.idTransacaoExterna = idTransacaoExterna;
    }

    @Override
    public String toString() {
        return "Pagamento{" +
                "idPagamento=" + idPagamento +
                ", pedido=" + pedido +
                ", metodoPagamento='" + metodoPagamento + '\'' +
                ", valorPago=" + valorPago +
                ", statusPagamento='" + statusPagamento + '\'' +
                ", dataPagamento=" + dataPagamento +
                ", idTransacaoExterna='" + idTransacaoExterna + '\'' +
                '}';
    }
}
