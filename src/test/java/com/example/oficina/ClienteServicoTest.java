package com.example.oficina;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import org.mockito.MockitoAnnotations;

import com.example.oficina.modelo.Cliente;
import com.example.oficina.modelo.EnderecoCliente;
import com.example.oficina.repositorio.ClienteRepositorio;
import com.example.oficina.servico.ClienteServico;
import com.example.oficina.servico.EnderecoClienteServico;

public class ClienteServicoTest {

    @InjectMocks
    private ClienteServico clienteServico;

    @Mock
    private ClienteRepositorio clienteRepositorio;

    @Mock
    private EnderecoClienteServico enderecoClienteServico;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testSalvarCliente() {
        Cliente cliente = new Cliente();
        EnderecoCliente enderecoCliente = new EnderecoCliente();
        cliente.setEnderecoCliente(enderecoCliente);

        EnderecoCliente enderecoCadastrado = new EnderecoCliente();
        when(enderecoClienteServico.salvarEnderecoCliente(enderecoCliente)).thenReturn(enderecoCadastrado);
        when(clienteRepositorio.save(cliente)).thenReturn(cliente);

        Cliente resultado = clienteServico.salvarCliente(cliente);

        assertNotNull(resultado);
        verify(enderecoClienteServico).salvarEnderecoCliente(enderecoCliente);
        verify(clienteRepositorio).save(cliente);
    }

    @Test
    public void testListarClientes() {
        List<Cliente> clientes = Arrays.asList(new Cliente(), new Cliente());
        when(clienteRepositorio.findAll()).thenReturn(clientes);

        List<Cliente> resultado = clienteServico.listarClientes();

        assertEquals(2, resultado.size());
        verify(clienteRepositorio).findAll();
    }

    @Test
    public void testEditarCliente() {
        Long idCliente = 1L;
        Cliente cliente = new Cliente();
        Cliente clienteEditado = new Cliente();
        when(clienteRepositorio.findById(idCliente)).thenReturn(Optional.of(cliente));

        Cliente resultado = clienteServico.editarCliente(idCliente, clienteEditado);

        assertNotNull(resultado);
        verify(clienteRepositorio).findById(idCliente);
        verify(clienteRepositorio).save(clienteEditado);
    }

    @Test
    public void testDeletarCliente() {
        Long idCliente = 1L;
        Cliente cliente = new Cliente();
        EnderecoCliente enderecoCliente = new EnderecoCliente();
        cliente.setEnderecoCliente(enderecoCliente);
        when(clienteRepositorio.findById(idCliente)).thenReturn(Optional.of(cliente));
        when(enderecoClienteServico.buscarEndercoId(enderecoCliente.getId())).thenReturn(enderecoCliente);

        clienteServico.deletarCliente(idCliente);

        verify(clienteRepositorio).delete(cliente);
        verify(enderecoClienteServico).deletarEndereco(enderecoCliente);
    }
}