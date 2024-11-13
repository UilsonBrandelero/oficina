package com.example.oficina.modelo;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import jakarta.persistence.CollectionTable;
import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MapKeyJoinColumn;
import lombok.Data;

@Entity
@Data

public class Orcamento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Cliente cliente;

    @ElementCollection
    @CollectionTable(name = "peca_quantidade",
            joinColumns = @JoinColumn(name = "pecas_orcamento_id"))
    @MapKeyJoinColumn(name = "peca_id")
    @Column(name = "quantidade")
    private Map<Peca, Integer> pecasQuantidade = new HashMap<>();

    @ManyToMany
    @JoinTable(
            name = "orcamento_servico",
            joinColumns = @JoinColumn(name = "orcamento_id"),
            inverseJoinColumns = @JoinColumn(name = "servico_id")
    )
    private List<Servico> servicos;

    private LocalDateTime dataOrcamento;
    private double valorTotal;
    private double valorDescontado;
}
