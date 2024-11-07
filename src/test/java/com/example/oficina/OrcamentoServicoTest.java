package com.example.oficina;



import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

import java.util.Arrays;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.example.oficina.modelo.Cliente;
import com.example.oficina.modelo.Orcamento;
import com.example.oficina.modelo.Peca;
import com.example.oficina.modelo.Servico;
import com.example.oficina.repositorio.ClienteRepositorio;
import com.example.oficina.repositorio.OrcamentoReposistorio;
import com.example.oficina.repositorio.PecaRepositorio;
import com.example.oficina.repositorio.ServicoRepositorio;
import com.example.oficina.servico.OrcamentoServico;

public class OrcamentoServicoTest {

    @InjectMocks
    private OrcamentoServico orcamentoServico;

    @Mock
    private OrcamentoReposistorio orcamentoReposistorio;

    @Mock
    private ClienteRepositorio clienteRepositorio;

    @Mock
    private ServicoRepositorio servicoRepositorio;

    @Mock
    private PecaRepositorio pecaRepositorio;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testCadastrarOrcamentoComPecasEServicos() {
        Cliente cliente = new Cliente();
        cliente.setId(1L);

        Peca peca = new Peca();
        peca.setId(1L);
        peca.setPrecoPeca(100.0);

        Servico servico = new Servico();
        servico.setId(1L);
        servico.setPrecoServico(200.0);

        when(clienteRepositorio.findById(1L)).thenReturn(Optional.of(cliente));
        when(pecaRepositorio.findById(1L)).thenReturn(Optional.of(peca));
        when(servicoRepositorio.findById(1L)).thenReturn(Optional.of(servico));
        when(orcamentoReposistorio.save(any(Orcamento.class))).thenReturn(new Orcamento());

        Orcamento orcamento = orcamentoServico.cadastrarOrcamento(1L, Arrays.asList(1L), Arrays.asList(1L));

        assertNotNull(orcamento);
        assertEquals(1, orcamento.getPecas().size());
        assertEquals(1, orcamento.getServicos().size());
        assertEquals(300.0, orcamento.getValorTotal());
        assertEquals(270.0, orcamento.getValorDescontado());
    }

    @Test
    public void testCadastrarOrcamentoComPecas() {
        Cliente cliente = new Cliente();
        cliente.setId(1L);

        Peca peca = new Peca();
        peca.setId(1L);
        peca.setPrecoPeca(100.0);

        when(clienteRepositorio.findById(1L)).thenReturn(Optional.of(cliente));
        when(pecaRepositorio.findById(1L)).thenReturn(Optional.of(peca));
        when(orcamentoReposistorio.save(any(Orcamento.class))).thenReturn(new Orcamento());

        Orcamento orcamento = orcamentoServico.cadastrarOrcamento(1L, Arrays.asList(1L));

        assertNotNull(orcamento);
        assertEquals(1, orcamento.getPecas().size());
        assertEquals(100.0, orcamento.getValorTotal());
        assertEquals(100.0, orcamento.getValorDescontado());
    }

    @Test
    public void testCadastrarOrcamentoComServicos() {
        Cliente cliente = new Cliente();
        cliente.setId(1L);

        Servico servico = new Servico();
        servico.setId(1L);
        servico.setPrecoServico(200.0);

        when(clienteRepositorio.findById(1L)).thenReturn(Optional.of(cliente));
        when(servicoRepositorio.findById(1L)).thenReturn(Optional.of(servico));
        when(orcamentoReposistorio.save(any(Orcamento.class))).thenReturn(new Orcamento());

        Orcamento orcamento = orcamentoServico.cadastrarOrcamento(Arrays.asList(1L), 1L);

        assertNotNull(orcamento);
        assertEquals(1, orcamento.getServicos().size());
        assertEquals(200.0, orcamento.getValorTotal());
        assertEquals(200.0, orcamento.getValorDescontado());
    }

    @Test
    public void testCalcularDesconto() {
        double precoTotal = 100.0;
        double valorDescontado = orcamentoServico.chamarDesconto(precoTotal);
        assertEquals(90.0, valorDescontado);
    }
}

