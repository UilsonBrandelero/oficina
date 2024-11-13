package com.example.oficina.modelo;

import java.time.LocalDateTime;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.Data;

@Entity
@Data

public class Orcamento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Cliente cliente;

   @OneToMany(mappedBy= "orcamento", cascade= CascadeType.ALL)
    private List<PecasOrcamento> pecasOrcamentos;

    @ManyToMany
    @JoinTable(
        name = "orcamento_servico",
        joinColumns=@JoinColumn(name="orcamento_id"),
        inverseJoinColumns = @JoinColumn(name="servico_id")
    )
    private List<Servico> servicos;

    private LocalDateTime dataOrcamento;
    private double valorTotal;
    private double valorDescontado;
}
