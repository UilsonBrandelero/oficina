package com.example.oficina.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.oficina.modelo.Orcamento;

public interface OrcamentoReposistorio extends JpaRepository<Orcamento, Long> {

    

}
