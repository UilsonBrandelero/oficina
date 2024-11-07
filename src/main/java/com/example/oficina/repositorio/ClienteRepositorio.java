package com.example.oficina.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.oficina.modelo.Cliente;

public interface ClienteRepositorio extends JpaRepository<Cliente, Long>{

}
