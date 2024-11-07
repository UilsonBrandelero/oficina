package com.example.oficina.servico;

import org.springframework.stereotype.Service;

import com.example.oficina.modelo.EnderecoCliente;
import com.example.oficina.repositorio.EnderecoRepositorio;
@Service
public class EnderecoClienteServico {
private final EnderecoRepositorio enderecoRepositorio;

public EnderecoClienteServico(EnderecoRepositorio enderecoRepositorio) {
    this.enderecoRepositorio = enderecoRepositorio;
}
public EnderecoCliente salvarEnderecoCliente(EnderecoCliente endereco){
    return enderecoRepositorio.save(endereco);
}
public EnderecoCliente buscarEndercoId(Long idEndereco){
    return enderecoRepositorio.findById(idEndereco).get();

}
public void deletarEndereco(EnderecoCliente enderecoDelete){
    enderecoRepositorio.delete(enderecoDelete);
}
}
