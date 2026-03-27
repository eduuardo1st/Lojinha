package principal;

import model.Cliente;
import model.Pedido;
import model.Produto;
import service.LojinhaService;

public class Principal {

    // Método utilitário para imprimir devagar
    private static void printDevagar(String mensagem, int delayMs) {
        System.out.println(mensagem);
        try {
            Thread.sleep(delayMs);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    public static void main(String[] args) {
        printDevagar("Iniciando simulação da Lojinha Online...", 1500);

        LojinhaService lojinha = new LojinhaService();

        // 1. Listar clientes e produtos disponíveis
        printDevagar("\n--- Clientes Disponíveis ---", 1500);
        for (Cliente c : lojinha.listarClientes()) {
            printDevagar(c.toString(), 1000);
        }

        printDevagar("\n--- Produtos Disponíveis ---", 1500);
        for (Produto p : lojinha.listarProdutos()) {
            printDevagar(p.toString(), 1000);
        }

        // 2. Simular um cliente fazendo um pedido
        Cliente clienteAlice = lojinha.buscarCliente(1);
        if (clienteAlice != null) {
            printDevagar("\n--- Cliente " + clienteAlice.getNome() + " iniciando um pedido ---", 1500);
            Pedido pedidoAlice = lojinha.criarPedido(clienteAlice.getIdCliente());

            if (pedidoAlice != null) {
                // Adicionar itens ao pedido
                lojinha.adicionarItemAoPedido(pedidoAlice, 1, 1); // Smartphone X
                lojinha.adicionarItemAoPedido(pedidoAlice, 3, 2); // Fone Bluetooth

                printDevagar("\nPedido de " + clienteAlice.getNome() + ":", 1000);
                printDevagar(pedidoAlice.toString(), 1500);

                // 3. Processar pagamento
                printDevagar("\n--- Processando pagamento para o Pedido " + pedidoAlice.getIdPedido() + " ---", 1500);
                boolean sucessoPagamento = lojinha.finalizarPedidoEProcessarPagamento(pedidoAlice, "Cartão de Crédito");

                if (sucessoPagamento) {
                    printDevagar("Simulação de compra de Alice CONCLUÍDA com sucesso!", 1500);
                } else {
                    printDevagar("Simulação de compra de Alice FALHOU!", 1500);
                }
                printDevagar("Status final do pedido: " + pedidoAlice.getStatus(), 1500);
            }
        }

        // 4. Simular outro cliente fazendo um pedido com falha de estoque e pagamento
        Cliente clienteBruno = lojinha.buscarCliente(2);
        if (clienteBruno != null) {
            printDevagar("\n--- Cliente " + clienteBruno.getNome() + " iniciando um pedido ---", 1500);
            Pedido pedidoBruno = lojinha.criarPedido(clienteBruno.getIdCliente());

            if (pedidoBruno != null) {
                // Tentar adicionar um produto com estoque insuficiente
                lojinha.adicionarItemAoPedido(pedidoBruno, 2, 6); // Notebook Pro (estoque 5)
                lojinha.adicionarItemAoPedido(pedidoBruno, 4, 1); // Smartwatch Fit

                printDevagar("\nPedido de " + clienteBruno.getNome() + ":", 1000);
                printDevagar(pedidoBruno.toString(), 1500);

                // 5. Processar pagamento (pode falhar aleatoriamente)
                printDevagar("\n--- Processando pagamento para o Pedido " + pedidoBruno.getIdPedido() + " ---", 1500);
                boolean sucessoPagamentoBruno = lojinha.finalizarPedidoEProcessarPagamento(pedidoBruno, "Boleto Bancário");

                if (sucessoPagamentoBruno) {
                    printDevagar("Simulação de compra de Bruno CONCLUÍDA com sucesso!", 1500);
                } else {
                    printDevagar("Simulação de compra de Bruno FALHOU!", 1500);
                }
                printDevagar("Status final do pedido: " + pedidoBruno.getStatus(), 1500);
            }
        }

        // 6. Mostrar produtos após simulações
        printDevagar("\n--- Produtos Disponíveis Após Simulações ---", 1500);
        for (Produto p : lojinha.listarProdutos()) {
            printDevagar(p.toString(), 1000);
        }

        // 7. Mostrar todos os pedidos registrados
        printDevagar("\n--- Pedidos Registrados ---", 1500);
        for (Pedido p : lojinha.listarPedidos()) {
            printDevagar(p.toString(), 1000);
        }

        printDevagar("\nSimulação da Lojinha Online Finalizada.", 1500);
    }
}