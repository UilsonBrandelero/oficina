package com.example.oficina.controle;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.oficina.modelo.Cliente;
import com.example.oficina.servico.ClienteServico;

@RestController
@RequestMapping("/cliente")
public class ClienteControle {

    public final ClienteServico clienteServico;

    public ClienteControle(ClienteServico clienteServico) {
        this.clienteServico = clienteServico;
    }

    @PostMapping("/cadastrar_cliente")
    public Cliente cadastrarCliente(@RequestBody Cliente cliente) {
        return clienteServico.salvarCliente(cliente);

      
    }
    @GetMapping("/listar_clientes")
    public List<Cliente> listarClientes(){
        return clienteServico.listarClientes();

    }
    @PutMapping("/editar_cliente")
    public Cliente editarCliente(@RequestParam Long id, @RequestBody Cliente cliente){
        return clienteServico.editarCliente(id, cliente);
    }
    @DeleteMapping("/deletar_cliente")
    public void deletarCliente(@RequestParam Long id){
        clienteServico.deletarCliente(id);
    }
}
