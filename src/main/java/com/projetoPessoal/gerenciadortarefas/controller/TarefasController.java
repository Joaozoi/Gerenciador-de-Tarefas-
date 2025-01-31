package com.projetoPessoal.gerenciadortarefas.controller;


import com.projetoPessoal.gerenciadortarefas.infra.Exception.ExceptionPersonalizada;
import com.projetoPessoal.gerenciadortarefas.model.Service.TarefaService;
import com.projetoPessoal.gerenciadortarefas.model.Tarefa.*;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import org.springframework.web.util.UriComponentsBuilder;


@RestController
@RequestMapping("tarefa")
public class TarefasController {

    @Autowired
    private TarefaService service;

    @GetMapping("/{id}")
    public ResponseEntity<Page<DadosListagemTarefa>> buscarPorId(@PathVariable Long id, Pageable paginacao) {

        try {
            Page<DadosListagemTarefa> tarefas = service.listarTarefasPorUsuario(id, paginacao);

            Page<DadosListagemTarefa> paginaSemPaginacao = new PageImpl<>(tarefas.getContent());

            return ResponseEntity.ok(paginaSemPaginacao);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(Page.empty());
        } catch (ExceptionPersonalizada e) {
            throw new RuntimeException(e);
        }
    }

    @PostMapping
    @Transactional
    public ResponseEntity criarTarefa(@RequestBody @Valid DadosCadastroTarefa dados, UriComponentsBuilder uriBuilder) throws ExceptionPersonalizada {
        var usuarioId = dados.usuarioId();

        var tarefa = new Tarefa(dados);
        tarefa = service.criarTarefa(tarefa, usuarioId);

        var uri = uriBuilder.path("/tarefa/{id}").buildAndExpand(tarefa.getId()).toUri();

        return ResponseEntity.created(uri).body(new DadosDetalhamentoTarefa(tarefa));

    }

    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity<DadosDetalhamentoTarefa> atualizarTarefa(@PathVariable Long id,  @RequestBody  @Valid  DadosAtualizaTarefa dados) throws ExceptionPersonalizada {
        var tarefa = service.atualizarTarefa(id, dados);


        return ResponseEntity.ok(new DadosDetalhamentoTarefa(tarefa));
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity<Void> deletarTarefa(@PathVariable Long id) throws ExceptionPersonalizada {
        service.deletarTarefa(id);

        return ResponseEntity.noContent().build();
    }




}
