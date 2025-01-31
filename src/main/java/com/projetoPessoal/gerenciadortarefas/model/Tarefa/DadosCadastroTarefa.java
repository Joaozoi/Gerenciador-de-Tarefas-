package com.projetoPessoal.gerenciadortarefas.model.Tarefa;

import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.time.LocalDateTime;

public record DadosCadastroTarefa(
        @NotBlank String titulo,
        @Size(max = 255) String descricao,
        @NotNull Status status,
        Prioridade prioridade,
        @Future(message = "O prazo deve ser uma data no futuro.")LocalDateTime prazo,
        @NotNull Long usuarioId

        ) {

}
