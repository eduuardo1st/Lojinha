package service;

import model.Pagamento;

public class SistemaPagamentoExternoSingleton implements SistemaPagamentoExterno {

    private static SistemaPagamentoExternoSingleton instance;

    private SistemaPagamentoExternoSingleton() {
        System.out.println("SistemaPagamentoExternoSingleton: Instância criada.");
    }

    public static synchronized SistemaPagamentoExternoSingleton getInstance() {
        if (instance == null) {
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

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            System.err.println("Interrupção durante o processamento do pagamento.");
            pagamento.setStatusPagamento("FALHA_INTERNA");
            return false;
        }

        String idTransacao = "TRANS_" + System.currentTimeMillis();
        pagamento.setStatusPagamento("APROVADO");
        pagamento.setIdTransacaoExterna(idTransacao);

        System.out.println("Status: APROVADO. ID Transação Externa: " + idTransacao);

        return true;
    }
}