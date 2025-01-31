package com.projetoPessoal.gerenciadortarefas.model.Usuario;

import com.projetoPessoal.gerenciadortarefas.model.Endereco.DadosEndereco;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;

public record DadosAtualizaUsuario(

        @NotNull Long id,
        String nome,
        String email,
        String telefone,
        @Valid DadosEndereco endereco



) {
}
