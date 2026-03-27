package service;

import model.Pagamento;

import java.util.Random;

public class SistemaPagamentoExternoSingleton implements SistemaPagamentoExterno{
    private static SistemaPagamentoExternoSingleton instance;
    private Random random;

    // private para evitar instanciação direta
    private SistemaPagamentoExternoSingleton() {
        this.random = new Random();
        System.out.println("SistemaPagamentoExternoSingleton: Instância criada.");
    }

    /**
     * Metodo estático para obter a única instância da classe (Singleton).
     * @return A única instância de SistemaPagamentoExternoSingleton.
     */
    public static synchronized SistemaPagamentoExternoSingleton getInstance(){
        if(instance == null){
            instance = new SistemaPagamentoExternoSingleton();
        }
        return instance;
    }

    @Override
    public boolean processarPagamento(Pagamento pagamento) {
        System.out.println("\n--- Processando Pagamento Externo ---");
        System.out.println("Pedido ID: " + pagamento.getPedido().getIdPedido());
        System.out.println("Valor: " + String.format("%.2f", pagamento.getValorPago()));
        System.out.println("Método: " + pagamento.getMetodoPagamento());

        // Simula um atraso no processamento e uma chance de falha
        try {
            Thread.sleep(1000); // 1 segundo
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            System.err.println("Interrupção durante o processamento do pagamento.");
            pagamento.setStatusPagamento("FALHA_INTERNA");
            return false;
        }

        // 80% de chance de sucesso, 20% de chance de falha
        boolean sucesso = random.nextInt(100) < 80;

        if (sucesso){
            String idTransacao = "TRANS_" + System.currentTimeMillis(); // gera numero aleatorio
            pagamento.setStatusPagamento("APROVADO");
            pagamento.setIdTransacaoExterna(idTransacao);
            System.out.println("Status: APROVADO. ID Transação Externa: " + idTransacao);
            return true;
        } else {
            pagamento.setStatusPagamento("RECUSADO");
            System.out.println("Status: RECUSADO.");
            return false;
        }
    }
}
