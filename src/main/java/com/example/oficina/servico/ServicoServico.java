package com.example.oficina.servico;


import java.util.List;

import org.springframework.stereotype.Service;

import com.example.oficina.modelo.Servico;
import com.example.oficina.repositorio.ServicoRepositorio;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class ServicoServico {
    private final ServicoRepositorio servicoRepositorio;

    public Servico cadastarServico (Servico servico){
        return servicoRepositorio.save(servico);
    }
    public List<Servico> listarServico(){
        return servicoRepositorio.findAll();

    }
    public Servico buscarServicoId(Long id){
        return servicoRepositorio.findById(id).get();

    }
    public Servico editarServico(Long id, Servico servicoEditado){
        Servico servico = servicoRepositorio.findById(id).get();
        if(servico != null){
           // servico  = servicoEditado;
            servicoEditado.setId(id);
            servicoRepositorio.save(servicoEditado);
            return servicoEditado;

        }else{
            System.out.println("Erro ao editar servico");
            return null;
        }
    }
    public void deletarServico(Long id){
        Servico servico = servicoRepositorio.findById(id).get();
        servicoRepositorio.delete(servico);
    }
}
