package com.example.oficina.servico;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.oficina.modelo.Peca;
import com.example.oficina.repositorio.PecaRepositorio;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class PecaServico {

    private final PecaRepositorio pecaRepositorio;

    public Peca cadastarPeca(Peca peca) {
        return pecaRepositorio.save(peca);
    }

    public List<Peca> listarPecas() {
        return pecaRepositorio.findAll();
    }

    public Peca buscaPecaId(Long idPeca) {
        return pecaRepositorio.findById(idPeca).get();

    }

    public Peca editarPeca(Long idPeca, Peca pecaEditada) {
        Peca peca = pecaRepositorio.findById(idPeca).get();
        if (peca != null) {
            peca = pecaEditada;
            pecaEditada.setId(idPeca);
            pecaRepositorio.save(pecaEditada);
            return pecaEditada;

        } else {
            System.out.println("Erro ao editar peca");
            return null;
        }
    }

    public void deletarPeca(Long idPeca) {
        Peca peca = pecaRepositorio.findById(idPeca).get();
        pecaRepositorio.delete(peca);
    }

    public Peca alterarQuantidadeEstoque(Long idPeca, int quantidaParaSubtrair) {
        Peca peca = pecaRepositorio.findById(idPeca)
                .orElseThrow(() -> new IllegalArgumentException("Erro ao buscar peca para atualizar a quantidade"));

        if (peca != null) {
            peca.setQuantidadeEstoque(peca.getQuantidadeEstoque() - quantidaParaSubtrair);
            return pecaRepositorio.save(peca);

        } else {
            System.out.println("Erro ao atualizar quantidade da peca " + peca.getNomePeca());
            return null;
        }

    }
    public Peca adiconarEstoquePeca(Long idPeca, int quantidadeAdiconar){
        Peca peca = pecaRepositorio.findById(idPeca)
        .orElseThrow(()->new IllegalArgumentException("Erro ao buscar Peca para adicionar estoque"));

        if(peca != null){
            peca.setQuantidadeEstoque(peca.getQuantidadeEstoque() + quantidadeAdiconar);
            return pecaRepositorio.save(peca);
        }else{
            System.out.println("Erro ao adicionar pecas ao estoque");
            return null;
        }
    }

}
