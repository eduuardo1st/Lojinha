package model;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Pedido {
    private int idPedido;
    private Cliente cliente;
    private LocalDateTime dataPedido;
    private String status;
    private List<ItemPedido> itensPedido;
    private double valorTotal;

    public Pedido(int idPedido, Cliente cliente) {
        this.idPedido = idPedido;
        this.cliente = cliente;
        this.dataPedido = java.time.LocalDateTime.now();
        this.status = "PENDENTE";
        this.itensPedido = new ArrayList<>();
        this.valorTotal = 0.0;
    }

    public void adicionarItem(ItemPedido item){
        itensPedido.add(item);
        valorTotal = valorTotal + item.getPrecoUnitario(); //Atualiza o valor total
    }

    public int getIdPedido() {
        return idPedido;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public LocalDateTime getDataPedido() {
        return dataPedido;
    }

    public String getStatus() {
        return status;
    }

    public List<ItemPedido> getItensPedido() {
        return itensPedido;
    }

    public double getValorTotal() {
        return valorTotal;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Pedido{" +
                "idPedido=" + idPedido +
                ", cliente=" + cliente +
                ", dataPedido=" + dataPedido +
                ", status='" + status + '\'' +
                ", itensPedido=" + itensPedido +
                ", valorTotal=" + valorTotal +
                '}';
    }
}
