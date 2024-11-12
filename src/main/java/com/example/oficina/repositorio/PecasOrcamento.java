package com.example.oficina.repositorio;

import com.example.oficina.modelo.Orcamento;
import com.example.oficina.modelo.Peca;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;
@Entity
@Data

public class PecasOrcamento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Orcamento orcamento;
    private Peca peca;
    private int quantidadePecas;
}
