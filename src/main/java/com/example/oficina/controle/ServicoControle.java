package com.example.oficina.controle;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.oficina.modelo.Servico;
import com.example.oficina.servico.ServicoServico;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/servico")
@AllArgsConstructor
public class ServicoControle {
    private final ServicoServico servicoServico;

    @PostMapping("/cadastrar_servico")
    public Servico cadastraServico(@RequestBody Servico servico){
        return servicoServico.cadastarServico(servico);

    }
    @GetMapping("/listar_servico")
    public List<Servico> listarServicos(){
        return servicoServico.listarServico();
    }
    @PutMapping("/editar_servico")
    public Servico editarServico(@RequestParam Long id, @RequestBody Servico servico){
        return servicoServico.editarServico(id, servico);

    }
    @DeleteMapping("/deletar_servico")
    public void deletarServico(@RequestParam Long id){
        servicoServico.deletarServico(id);
    }
}

