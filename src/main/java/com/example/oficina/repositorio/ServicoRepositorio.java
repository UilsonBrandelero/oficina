package com.example.oficina.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.oficina.modelo.Servico;

public interface ServicoRepositorio extends  JpaRepository<Servico, Long>{

   
}
