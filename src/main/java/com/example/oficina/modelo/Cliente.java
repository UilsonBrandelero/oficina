package com.example.oficina.modelo;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import lombok.Data;

@Data
@Entity
@JsonIgnoreProperties("orcamentos")
public class Cliente {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String nomeCliente;
    private String telefoneCliente;
    @OneToOne
    private EnderecoCliente enderecoCliente;
    @OneToMany(mappedBy = "cliente")
    private List<Orcamento> orcamentos;

}
