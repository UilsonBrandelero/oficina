package com.example.oficina.modelo;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

@Entity
@Data
public class EnderecoCliente {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    Long id;
    private String rua;
    private String bairro;
    private String cidade;
    private int numero;
    private String cep;
    
    


}
