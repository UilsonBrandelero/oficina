package com.example.oficina.servico;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.oficina.modelo.Cliente;
import com.example.oficina.modelo.EnderecoCliente;
import com.example.oficina.repositorio.ClienteRepositorio;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class ClienteServico {

    private final ClienteRepositorio clienteRepositorio;
    private final EnderecoClienteServico enderecoClienteServico;

    public Cliente salvarCliente(Cliente cliente) {
        //Capta o endereco fornecido pelo usuario 
        EnderecoCliente enderecoCliente = cliente.getEnderecoCliente();
        //Salva o endereco do usuario
        EnderecoCliente enderecoCadastardo = enderecoClienteServico.salvarEnderecoCliente(enderecoCliente);
        //Set o endereço com id para o usuario
        cliente.setEnderecoCliente(enderecoCadastardo);
        //Salva o cliente
        return clienteRepositorio.save(cliente);
    }

    public List<Cliente> listarClientes() {
        return clienteRepositorio.findAll();
    }

    public Cliente editarCliente(Long idCliente, Cliente clienteEditado) {
        Cliente cliente = clienteRepositorio.findById(idCliente).get();
        if (cliente != null) {
            
            clienteEditado.setId(idCliente);
            salvarCliente(clienteEditado);
            return clienteEditado;
         }else {
            System.out.println("Erro ao editar cliente");
            return null;

        }

    }
    public void deletarCliente(Long id){
        Cliente cliente = clienteRepositorio.findById(id).get();
        EnderecoCliente endereco = enderecoClienteServico.buscarEndercoId(cliente.getEnderecoCliente().getId());
        clienteRepositorio.delete(cliente);
        enderecoClienteServico.deletarEndereco(endereco);
    }

}
