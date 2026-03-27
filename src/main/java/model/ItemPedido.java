package model;

public class ItemPedido {
   private int IdIntPedido;
   private Produto produto;
   private int quantidade;
   private double precoUnitario;

    public ItemPedido(int idIntPedido, Produto produto, int quantidade) {
        this.IdIntPedido = idIntPedido;
        this.produto = produto;
        this.quantidade = quantidade;
        this.precoUnitario = produto.getPreco();
    }

    public int getIdIntPedido() {
        return IdIntPedido;
    }

    public Produto getProduto() {
        return produto;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public double getPrecoUnitario() {
        return precoUnitario;
    }

    public double getSubtotal(){
        return quantidade * precoUnitario;
    }

    @Override
    public String toString() {
        return "ItemPedido{" +
                "IdIntPedido=" + IdIntPedido +
                ", produto=" + produto +
                ", quantidade=" + quantidade +
                ", precoUnitario=" + precoUnitario +
                '}';
    }
}
