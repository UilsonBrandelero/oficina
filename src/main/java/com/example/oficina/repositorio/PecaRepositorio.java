package com.example.oficina.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.oficina.modelo.Peca;

public interface PecaRepositorio extends JpaRepository<Peca, Long> {

}
