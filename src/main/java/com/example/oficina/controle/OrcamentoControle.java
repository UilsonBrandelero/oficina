package com.example.oficina.controle;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.oficina.modelo.Orcamento;
import com.example.oficina.servico.OrcamentoServico;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/orcamento")
@AllArgsConstructor
public class OrcamentoControle {

    private final OrcamentoServico orcamentoServico;

    @PostMapping("/cadastrar_orcamento")
    public Orcamento cadastarOrcamento(@RequestParam Long idCliente, @RequestParam List<Long> idPecas, @RequestParam List<Long> idServicos) {
        return orcamentoServico.cadastrarOrcamento(idCliente, idPecas, idServicos);

    }

    @PostMapping("/cadastrar_orcamento_pecas")
    public Orcamento cadastarOrcamentoPecas(@RequestParam Long idCliente, @RequestParam List<Long> idPecas) {
        return orcamentoServico.cadastrarOrcamento(idCliente, idPecas);

    }

    @PostMapping("/cadastrar_orcamento_servicos")
    public Orcamento cadastarOrcamentoServicos(@RequestParam Long idCliente, @RequestParam List<Long> idServicos) {
        return orcamentoServico.cadastrarOrcamento(idCliente, idServicos);

    }

    @GetMapping("/listar_orcamentos")
    public List<Orcamento> listarOrcamentos() {
        return orcamentoServico.listarOrcamentos();
    }
    @GetMapping("/buscar_orcamento_id/{id}")
    public Orcamento buscarOrcamentoPorId(@PathVariable Long id){
        return orcamentoServico.buscaOrcamentoPorId(id);
    }
    @PutMapping("/editar_orcamento")
    public Orcamento editarOrcamento (@RequestParam Long id, @RequestBody Orcamento orcamento){
        return orcamentoServico.buscaOrcamentoPorId(id);
    }
}
