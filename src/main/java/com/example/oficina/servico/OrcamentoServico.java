package com.example.oficina.servico;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.example.oficina.modelo.Cliente;
import com.example.oficina.modelo.Orcamento;
import com.example.oficina.modelo.Peca;
import com.example.oficina.modelo.Servico;
import com.example.oficina.repositorio.ClienteRepositorio;
import com.example.oficina.repositorio.OrcamentoReposistorio;
import com.example.oficina.repositorio.PecaRepositorio;
import com.example.oficina.repositorio.ServicoRepositorio;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor

public class OrcamentoServico {

    private final OrcamentoReposistorio orcamentoReposistorio;
    private final ClienteRepositorio clienteRepositorio;
    private final ServicoRepositorio servicoRepositorio;
    private final PecaRepositorio pecaRepositorio;

    public Orcamento cadastrarOrcamento(Long idCliente, List<Long> idPecas, List<Integer> quantidadePecas, List<Long> idServicos) {
        Cliente cliente = clienteRepositorio.findById(idCliente)
                .orElseThrow(() -> new IllegalArgumentException("Erro ao buscar cliente"));;
        Map<Peca, Integer> pecasQuantidades = new HashMap<>();
        List<Servico> servicos = new ArrayList<>();
        double precoTotal = 0;

        if (cliente != null && idPecas != null && idServicos != null && quantidadePecas != null && quantidadePecas.size() == idPecas.size()) {

            for (int i = 0; i < idPecas.size(); i++) {
                Peca peca = pecaRepositorio.findById(idPecas.get(i))
                        .orElseThrow(() -> new IllegalArgumentException("Erro ao buscar peca"));
                Integer quantidade = quantidadePecas.get(i);
                precoTotal += peca.getPrecoPeca() * quantidade;
                pecasQuantidades.put(peca, quantidade);
            }
            for (Long s : idServicos) {
                Servico servico = servicoRepositorio.findById(s).
                        orElseThrow(() -> new IllegalArgumentException("Erro ao buscar Servico"));;
                servicos.add(servico);
                precoTotal += servico.getPrecoServico();

            }
            Orcamento orcamento = new Orcamento();
            orcamento.setCliente(cliente);
            orcamento.setServicos(servicos);
            orcamento.setPecasQuantidade(pecasQuantidades);
            orcamento.setDataOrcamento(LocalDateTime.now());
            orcamento.setValorTotal(precoTotal);
            orcamento.setValorDescontado(calculaDescosto(precoTotal));

            return orcamentoReposistorio.save(orcamento);
        } else {
            System.out.println("Erro ao realizar orcamento");
            return null;
        }

    }

    public Orcamento cadastrarOrcamentoPecas(Long idCliente, List<Long> idPecas, List<Integer> quantidadePecas) {
        Cliente cliente = clienteRepositorio.findById(idCliente).orElseThrow(() -> new IllegalArgumentException("Erro ao buscar cliente"));
        Map<Peca, Integer> pecasQuantidade = new HashMap<>();
        double precoTotal = 0;

        if (cliente != null && idPecas != null && quantidadePecas.size() == idPecas.size()) {

            for (int i = 0; i < idPecas.size(); i++) {
                Peca peca = pecaRepositorio.findById(idPecas.get(i))
                        .orElseThrow(() -> new IllegalArgumentException("Erro ao buscar peca"));
                Integer quantidade = quantidadePecas.get(i);
                precoTotal += peca.getPrecoPeca() * quantidade;
                pecasQuantidade.put(peca, quantidade);

            }
            Orcamento orcamento = new Orcamento();
            orcamento.setCliente(cliente);
            orcamento.setPecasQuantidade(pecasQuantidade);
            orcamento.setDataOrcamento(LocalDateTime.now());
            orcamento.setValorTotal(precoTotal);
            orcamento.setValorDescontado(precoTotal);

            return orcamentoReposistorio.save(orcamento);

        } else {
            System.out.println("Erro ao realizar orcamento");
            return null;
        }
    }

    public Orcamento cadastrarOrcamentoServicos(Long idCliente, List<Long> idServicos) {
        Cliente cliente = clienteRepositorio.findById(idCliente)
                .orElseThrow(() -> new IllegalArgumentException("Erro ao buscar cliente"));
        List<Servico> servicos = new ArrayList<>();
        double precoTotal = 0;

        if (cliente != null && idServicos != null) {

            for (Long p : idServicos) {
                Servico servico = servicoRepositorio.findById(p)
                        .orElseThrow(() -> new IllegalArgumentException("Erro ao buscar Servico"));
                servicos.add(servico);
                precoTotal += servico.getPrecoServico();
            }
            Orcamento orcamento = new Orcamento();
            orcamento.setCliente(cliente);
            orcamento.setServicos(servicos);
            orcamento.setDataOrcamento(LocalDateTime.now());
            orcamento.setValorTotal(precoTotal);
            orcamento.setValorDescontado(precoTotal);

            return orcamentoReposistorio.save(orcamento);

        } else {
            System.out.println("Erro ao realizar orcamento");
            return null;
        }
    }

    public List<Orcamento> listarOrcamentos() {
        return orcamentoReposistorio.findAll();
    }

    public Orcamento buscaOrcamentoPorId(Long id) {
        return orcamentoReposistorio.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Erro ao buscar orcamento"));
    }

    public List<Orcamento> buscarOrcamentoPorCliente(Long idCliente) {
        return orcamentoReposistorio.buscarOrcamentosPorCliente(idCliente);

    }

    public Orcamento editarServicosOrcamento(Long idOrcamento, List<Long> idServicos) {
        List<Servico> servicos = new ArrayList<>();

        Orcamento orcamento = orcamentoReposistorio.findById(idOrcamento)
                .orElseThrow(() -> new IllegalArgumentException("Erro ao buscar Orcamento"));
        if (orcamento != null) {
            for (Long s : idServicos) {
                Servico servico = servicoRepositorio.findById(s)
                        .orElseThrow(() -> new IllegalArgumentException("Erro ao buscar Servico"));
                servicos.add(servico);
            }
            orcamento.setServicos(servicos);
            return orcamentoReposistorio.save(orcamento);

        } else {
            System.out.println("Erro ao editar pecas do Orcamento");
            return null;
        }
    }

    public Orcamento editarPecasOrcamento(Long idOrcamento, List<Long> idPecas, List<Integer> quantidadePecas) {
       Map<Peca, Integer> pecasQuantidade = new HashMap<>();
        Orcamento orcamento = orcamentoReposistorio.findById(idOrcamento)
                .orElseThrow(() -> new IllegalArgumentException("Erro ao buscar Orcamento"));
        if (orcamento != null && idPecas.size() == quantidadePecas.size()) {
            for (int i = 0; i < 0 ; i++) {
                Peca peca = pecaRepositorio.findById(idPecas.get(i))
                        .orElseThrow(() -> new IllegalArgumentException("Erro ao buscar Peca"));
                //Integer
            }
            //orcamento.setPecas(pecas);
            return orcamentoReposistorio.save(orcamento);
        } else {
            System.out.println("Erro ao editar Pecas do Orcamento");
            return null;
        }

    }

    public void deletarOrcamento(Long id) {
        Orcamento orcamento = orcamentoReposistorio.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Erro ao buscar orcamento"));
        orcamentoReposistorio.delete(orcamento);
    }

    public double calculaDescosto(double precoTotal) {
        return precoTotal * 0.9;
    }

    public double chamarDesconto(double precoTotal) {
        return this.calculaDescosto(precoTotal);
    }

    public String calculaTotalGastoPorCliente(Long idCliente) {
        List<Orcamento> orcamentosCliente = this.buscarOrcamentoPorCliente(idCliente);
        String nomeCliente = clienteRepositorio.findById(idCliente)
                .orElseThrow(() -> new IllegalArgumentException("Erro ao buscar Cliente")).getNomeCliente();
        double totalDevido = 0;
        if (orcamentosCliente != null) {
            for (Orcamento orcamento : orcamentosCliente) {
                totalDevido += orcamento.getValorDescontado();
            }
            return "O cliente " + nomeCliente + " deve um total de R$ " + totalDevido;

        } else {
            System.out.println("Erro ao buscar orcamentos do cliente para calcular total devido");
            return null;
        }

    }

}
