package com.example.oficina;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.example.oficina.repositorio.ClienteRepositorio;
import com.example.oficina.repositorio.OrcamentoReposistorio;
import com.example.oficina.repositorio.PecaRepositorio;
import com.example.oficina.repositorio.ServicoRepositorio;
import com.example.oficina.servico.OrcamentoServico;

class TestCalculoDesconto {

    
    @Mock
    private OrcamentoReposistorio orcamentoRepositorio;

    @Mock
    private ClienteRepositorio clienteRepositorio;

    @Mock
    private ServicoRepositorio servicoRepositorio;

    @Mock
    private PecaRepositorio pecaRepositorio;

    @InjectMocks
    private OrcamentoServico orcamentoServico;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }
    @Test
    public void testCalculaDesconto(){
        assertEquals(90, orcamentoServico.chamarDesconto(100));
    }

}
