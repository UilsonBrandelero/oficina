package com.example.oficina.util;

import java.util.List;

import com.example.oficina.modelo.Orcamento;
import com.example.oficina.servico.OrcamentoServico;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class CalculaDivida {

    private final OrcamentoServico orcamentoServico;

    public double calculaTotalGastoPorCliente(Long idCliente) {
        List<Orcamento> orcamentosCliente = orcamentoServico.buscarOrcamentoPorCliente(idCliente);
        double totalDevido = 0;
        if (orcamentosCliente != null) {
            for (Orcamento orcamento : orcamentosCliente) {
                totalDevido += orcamento.getValorDescontado();
            }
            return totalDevido;

        } else {
            System.out.println("Erro ao buscar orcamentos do cliente para calcular total devido");
            return 0;
        }

    }
}
