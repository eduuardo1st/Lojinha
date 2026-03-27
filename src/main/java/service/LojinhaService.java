package service;


import model.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class LojinhaService {
    private Map<Integer, Cliente> clientes;
    private Map<Integer, Produto> produtos;
    private Map<Integer, Pedido> pedidos;
    private int proximoIdCliente = 1;
    private int proximoIdProduto = 1;
    private int proximoIdPedido = 1;
    private int proximoIdItemPedido = 1;
    private int proximoIdPagamento = 1;

    private SistemaPagamentoExternoSingleton sistemaPagamento;

    public LojinhaService() {
        this.clientes = new HashMap<>();
        this.produtos = new HashMap<>();
        this.pedidos = new HashMap<>();
        this.sistemaPagamento = SistemaPagamentoExternoSingleton.getInstance(); // Obtém a instância Singleton
        inicializarDadosEstaticos();
    }

    private void inicializarDadosEstaticos() {
        // Clientes estáticos
        adicionarCliente(new Cliente(proximoIdCliente++, "Alice Silva", "alice@email.com", "Rua A, 123", "(11) 98765-4321"));
        adicionarCliente(new Cliente(proximoIdCliente++, "Bruno Costa", "bruno@email.com", "Av. B, 456", "(21) 91234-5678"));

        // Produtos estáticos
        adicionarProduto(new Produto(proximoIdProduto++, "Smartphone X", "Smartphone de última geração", 2500.00, 10));
        adicionarProduto(new Produto(proximoIdProduto++, "Notebook Pro", "Notebook de alta performance", 5000.00, 5));
        adicionarProduto(new Produto(proximoIdProduto++, "Fone Bluetooth", "Fone de ouvido sem fio", 300.00, 20));
        adicionarProduto(new Produto(proximoIdProduto++, "Smartwatch Fit", "Relógio inteligente com monitor cardíaco", 800.00, 15));
    }

    public void adicionarCliente(Cliente cliente) {
        clientes.put(cliente.getIdCliente(), cliente);
    }

    public Cliente buscarCliente(int idCliente) {
        return clientes.get(idCliente);
    }

    public List<Cliente> listarClientes() {
        return new ArrayList<>(clientes.values());
    }

    public void adicionarProduto(Produto produto) {
        produtos.put(produto.getIdProduto(), produto);
    }

    public Produto buscarProduto(int idProduto) {
        return produtos.get(idProduto);
    }

    public List<Produto> listarProdutos() {
        return new ArrayList<>(produtos.values());
    }

    public Pedido criarPedido(int idCliente) {
        Cliente cliente = buscarCliente(idCliente);
        if (cliente == null) {
            System.out.println("Erro: Cliente com ID " + idCliente + " não encontrado.");
            return null;
        }
        Pedido pedido = new Pedido(proximoIdPedido++, cliente);
        pedidos.put(pedido.getIdPedido(), pedido);
        System.out.println("Pedido " + pedido.getIdPedido() + " criado para o cliente " + cliente.getNome() + ".");
        return pedido;
    }

    public boolean adicionarItemAoPedido(Pedido pedido, int idProduto, int quantidade) {
        Produto produto = buscarProduto(idProduto);
        if (pedido == null || produto == null) {
            System.out.println("Erro: Pedido ou Produto inválido.");
            return false;
        }
        if (produto.getEstoque() < quantidade) {
            System.out.println("Erro: Estoque insuficiente para o produto " + produto.getNomeProduto() + ". Disponível: " + produto.getEstoque());
            return false;
        }

        ItemPedido item = new ItemPedido(proximoIdItemPedido++, produto, quantidade);
        pedido.adicionarItem(item);
        produto.setEstoque(produto.getEstoque() - quantidade); // Reduz o estoque
        System.out.println("Adicionado " + quantidade + "x " + produto.getNomeProduto() + " ao Pedido " + pedido.getIdPedido() + ".");
        return true;
    }

    public boolean finalizarPedidoEProcessarPagamento(Pedido pedido, String metodoPagamento) {
        if (pedido == null || pedido.getItensPedido().isEmpty()) {
            System.out.println("Erro: Pedido vazio ou inválido.");
            return false;
        }

        System.out.println("\n--- Finalizando Pedido " + pedido.getIdPedido() + " ---");
        System.out.println("Valor Total: " + String.format("%.2f", pedido.getValorTotal()));

        Pagamento pagamento = new Pagamento(proximoIdPagamento++, pedido, metodoPagamento, pedido.getValorTotal());

        boolean pagamentoAprovado = sistemaPagamento.processarPagamento(pagamento);

        if (pagamentoAprovado) {
            pedido.setStatus("APROVADO");
            System.out.println("Pedido " + pedido.getIdPedido() + " APROVADO. Pagamento ID: " + pagamento.getIdPagamento());
            return true;
        } else {
            pedido.setStatus("RECUSADO");
            System.out.println("Pedido " + pedido.getIdPedido() + " RECUSADO. Pagamento ID: " + pagamento.getIdPagamento());
            // Reverter estoque em caso de falha de pagamento (opcional, dependendo da regra de negócio)
            for (ItemPedido item : pedido.getItensPedido()) {
                Produto produto = item.getProduto();
                produto.setEstoque(produto.getEstoque() + item.getQuantidade());
            }
            return false;
        }
    }

    public Pedido buscarPedido(int idPedido) {
        return pedidos.get(idPedido);
    }

    public List<Pedido> listarPedidos() {
        return new ArrayList<>(pedidos.values());
    }
}