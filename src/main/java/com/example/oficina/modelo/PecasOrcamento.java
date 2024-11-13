package com.example.oficina.modelo;

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

//     @ManyToOne
//     @JoinColumn(name="orcamento_id")
//     private Orcamento orcamento;

// @ElementCollection
// @CollectionTable(name="peca_quantidade", 
// joinColumns = @JoinColumn(name="pecas_orcamento_id"))
// @MapKeyJoinColumn(name = "peca_id")
// @Column(name="quantidade")
// private Map<Peca,Integer> pecasQuantidade = new HashMap<>();
}
