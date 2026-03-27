package service;

import model.Pagamento;

public interface SistemaPagamentoExterno {
    boolean processarPagamento(Pagamento pagamento);
}
