package com.example.oficina.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.oficina.modelo.EnderecoCliente;

public interface EnderecoRepositorio extends JpaRepository<EnderecoCliente, Long>{

}
