package com.example.oficina.controle;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.oficina.modelo.Peca;
import com.example.oficina.servico.PecaServico;

import lombok.AllArgsConstructor;
@RestController
@RequestMapping("/peca")
@AllArgsConstructor

public class PecaControle {

    public final PecaServico pecaServico;

    @PostMapping("/cadastrar_peca")
    public Peca cadastarPeca(@RequestBody Peca peca){
        return pecaServico.cadastarPeca(peca);

    }
    @GetMapping("listar_pecas")
    public List<Peca> listarPecas(){
        return pecaServico.listarPecas();
    }
    @GetMapping("/buscar_peca_id/{id}")
    public Peca buscarPecaId(@PathVariable Long id){
        return pecaServico.buscaPecaId(id);

    }
    @PutMapping("/editar_peca")
    public Peca editarPeca(@RequestParam Long id, @RequestBody Peca pecaEditada){
        return pecaServico.editarPeca(id, pecaEditada);
    }
    @PutMapping("/adicionar_peca_estoque")
    public Peca adicionarPecaEstoque(@RequestParam Long idPeca, @RequestParam int quantidadeAdicionar){
        return  pecaServico.adiconarEstoquePeca(idPeca, quantidadeAdicionar);
    }
    @DeleteMapping("/deletar_peca")
    public void deletarPeca(@RequestParam Long id){
        pecaServico.deletarPeca(id);
    }
    }

