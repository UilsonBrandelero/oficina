package com.example.oficina.repositorio;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.oficina.modelo.Orcamento;

public interface OrcamentoReposistorio extends JpaRepository<Orcamento, Long> {

    @Query(value = "SELECT * FROM orcamento  WHERE cliente_id = :idCliente", nativeQuery = true)
    List<Orcamento> buscarOrcamentosPorCliente(@Param("idCliente") Long idCliente);

}
