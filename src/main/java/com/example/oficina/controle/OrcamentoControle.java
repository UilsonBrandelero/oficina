package com.example.oficina.controle;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
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

    @PostMapping("/cadastrar_orcamento_completo")
    public Orcamento cadastarOrcamento(@RequestParam Long idCliente, @RequestParam List<Long> idPecas,@RequestParam List<Integer> quantidadePecas, @RequestParam List<Long> idServicos) {
        return orcamentoServico.cadastrarOrcamento(idCliente, idPecas,quantidadePecas, idServicos);

    }

    @PostMapping("/cadastrar_orcamento_pecas")
    public Orcamento cadastarOrcamentoPecas(@RequestParam Long idCliente, @RequestParam List<Long> idPecas, @RequestParam List<Integer> quantidadePecas) {
        return orcamentoServico.cadastrarOrcamentoPecas(idCliente, idPecas, quantidadePecas);

    }

    @PostMapping("/cadastrar_orcamento_servicos")
    public Orcamento cadastarOrcamentoServicos(@RequestParam Long idCliente, @RequestParam List<Long> idServicos) {
        return orcamentoServico.cadastrarOrcamentoServicos(idCliente, idServicos);

    }

    @GetMapping("/listar_orcamentos")
    public List<Orcamento> listarOrcamentos() {
        return orcamentoServico.listarOrcamentos();
    }

    @GetMapping("/buscar_orcamento_id/{id}")
    public Orcamento buscarOrcamentoPorId(@PathVariable Long id) {
        return orcamentoServico.buscaOrcamentoPorId(id);
    }

    @GetMapping("/buscar_orcamento_cliente/{idCliente}")
    public List<Orcamento> buscarOrcamentoPorCliente(@PathVariable Long idCliente) {
        return orcamentoServico.buscarOrcamentoPorCliente(idCliente);
    }

    @PutMapping("/editar_pecas_orcamento")
    public Orcamento editarPecasOrcamento(@RequestParam Long idOrcamento, @RequestParam List<Long> idPecas, @RequestParam List<Integer> quantidade) {
        return orcamentoServico.editarPecasOrcamento(idOrcamento, idPecas, quantidade);
    }

    @PutMapping("/editar_servicos_orcamento")
    public Orcamento editarServicosOrcamento(@RequestParam Long idOrcamento, @RequestParam List<Long> idServicos) {
        return orcamentoServico.editarServicosOrcamento(idOrcamento, idServicos);

    }

    @GetMapping("/total_devido_por_cliente/{idCliente}")
    public String calculaTotalDevidoPorCliente(@PathVariable Long idCliente) {
        return orcamentoServico.calculaTotalGastoPorCliente(idCliente);

    }

}
