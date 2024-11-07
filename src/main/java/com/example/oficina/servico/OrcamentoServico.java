package com.example.oficina.servico;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

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

    public Orcamento cadastrarOrcamento(Long idCliente, List<Long> idPecas, List<Long> idServicos) {
        Cliente cliente = clienteRepositorio.findById(idCliente)
        .orElseThrow(() -> new IllegalArgumentException("Erro ao buscar cliente"));;
        List<Peca> pecas = new ArrayList<>();
        List<Servico> servicos = new ArrayList<>();
        double precoTotal = 0;
        if (cliente != null && idPecas != null && idServicos != null) {

            for (Long p : idPecas) {
                Peca peca = pecaRepositorio.findById(p)
                        .orElseThrow(() -> new IllegalArgumentException("Erro ao buscar peca"));
                pecas.add(peca);
                precoTotal += peca.getPrecoPeca();
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
            orcamento.setPecas(pecas);
            orcamento.setDataOrcamento(LocalDateTime.now());
            orcamento.setValorTotal(precoTotal);
            orcamento.setValorDescontado(calculaDescosto(precoTotal));

            return orcamentoReposistorio.save(orcamento);
        } else {
            System.out.println("Erro ao realizar orcamento");
            return null;
        }

    }

    public Orcamento cadastrarOrcamento(Long idCliente, List<Long> idPecas) {
        Cliente cliente = clienteRepositorio.findById(idCliente).orElseThrow(() -> new IllegalArgumentException("Erro ao buscar cliente"));
        List<Peca> pecas = new ArrayList<>();
        double precoTotal = 0;

        if (cliente != null && idPecas != null) {

            for (Long p : idPecas) {
                Peca peca = pecaRepositorio.findById(p)
                        .orElseThrow(() -> new IllegalArgumentException("Erro ao buscar peca"));
                pecas.add(peca);
                precoTotal += peca.getPrecoPeca();
            }
            Orcamento orcamento = new Orcamento();
            orcamento.setCliente(cliente);
            orcamento.setPecas(pecas);
            orcamento.setDataOrcamento(LocalDateTime.now());
            orcamento.setValorTotal(precoTotal);
            orcamento.setValorDescontado(precoTotal);
            
            return orcamentoReposistorio.save(orcamento);
             

        }else{
            System.out.println("Erro ao realizar orcamento");
            return null;
        }
    }
    public Orcamento cadastrarOrcamento(List<Long> idServicos, Long idCliente ) {
        Cliente cliente = clienteRepositorio.findById(idCliente)
        .orElseThrow(() -> new IllegalArgumentException("Erro ao buscar cliente"));
        List<Servico> servicos = new ArrayList<>();
        double precoTotal = 0;

        if (cliente != null && idServicos != null) {

            for (Long p : idServicos) {
                Servico servico = servicoRepositorio.findById(p)
                        .orElseThrow(() -> new IllegalArgumentException("Erro ao buscar peca"));
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
             

        }else{
            System.out.println("Erro ao realizar orcamento");
            return null;
        }
    }
    
    
    public List<Orcamento> listarOrcamentos(){
        return orcamentoReposistorio.findAll();
    }
    
    public Orcamento buscaOrcamentoPorId(Long id){
        return orcamentoReposistorio.findById(id)
        .orElseThrow(()-> new IllegalArgumentException("Erro ao buscar orcamento"));
    }
    public Orcamento editarOrcamento(Long idOrcamento, Orcamento orcamentoEditado){
        Orcamento orcamento = orcamentoReposistorio.findById(idOrcamento)
        .orElseThrow(()-> new IllegalArgumentException("Erro ao buscar Orcamento"));
        return null;
    }
    public void deletarOrcamento(Long id){
        Orcamento orcamento = orcamentoReposistorio.findByIdWithDetails(id);
        //.orElseThrow(()-> new IllegalArgumentException("Erro ao buscar orcamento"));
        orcamentoReposistorio.delete(orcamento);
    }

    private double calculaDescosto(double precoTotal) {
        return precoTotal * 0.9;
    }
    public double chamarDesconto(double precoTotal){
        return this.calculaDescosto(precoTotal);
    }

}
