package com.projetoPessoal.gerenciadortarefas.model.Tarefa;

import com.projetoPessoal.gerenciadortarefas.model.Usuario.DadosDetalhamentoUsuario;
import com.projetoPessoal.gerenciadortarefas.model.Usuario.DadosListagemUsuario;
import com.projetoPessoal.gerenciadortarefas.model.Usuario.Usuario;
import org.springframework.cglib.core.Local;

import java.time.LocalDateTime;

public record DadosDetalhamentoTarefa(Long id,
                                      String titulo,
                                      String descricao,
                                      Status status,
                                      Prioridade prioridade,
                                      LocalDateTime prazo,
                                      LocalDateTime dataLimite,
                                      DadosListagemUsuario usuario) {


    public DadosDetalhamentoTarefa(Tarefa tarefa){
        this(
                tarefa.getId(),
                tarefa.getTitulo(),
                tarefa.getDescricao(),
                tarefa.getStatus(),
                tarefa.getPrioridade(),
                tarefa.getPrazo(),
                tarefa.getDataLimite(),
                new DadosListagemUsuario(tarefa.getUsuario())
        );

    }
}
