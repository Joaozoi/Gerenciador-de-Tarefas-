package com.projetoPessoal.gerenciadortarefas.model.Tarefa;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;


import java.time.LocalDateTime;

public record DadosAtualizaTarefa(

        @Size(max = 100)
        String titulo,

        @Size(max = 255)
        String descricao,

        Status status,

        Prioridade prioridade,

        LocalDateTime prazo,

        String concluidoPor



) {
}
