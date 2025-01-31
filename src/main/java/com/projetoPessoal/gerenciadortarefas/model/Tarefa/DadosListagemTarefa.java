package com.projetoPessoal.gerenciadortarefas.model.Tarefa;

import com.projetoPessoal.gerenciadortarefas.model.Usuario.Usuario;

import java.time.LocalDateTime;

public record DadosListagemTarefa(
        Long id,
        String titulo,
        String descricao,
        Status status,
        Prioridade prioridade,
        LocalDateTime prazo
) {

    public DadosListagemTarefa(Tarefa tarefa) {
        this(
                tarefa.getId(),
                tarefa.getTitulo(),
                tarefa.getDescricao(),
                tarefa.getStatus(),
                tarefa.getPrioridade(),
                tarefa.getPrazo()
        );
    }


}
