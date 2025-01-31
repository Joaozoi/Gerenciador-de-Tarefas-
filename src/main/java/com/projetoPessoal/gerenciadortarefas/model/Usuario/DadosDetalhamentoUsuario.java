package com.projetoPessoal.gerenciadortarefas.model.Usuario;

import com.projetoPessoal.gerenciadortarefas.model.Endereco.Endereco;

public record DadosDetalhamentoUsuario(Long id, String nome, String email, String cp, String telefone, Endereco endereco) {
    public DadosDetalhamentoUsuario(Usuario usuario) {
        this(
                usuario.getId(),
                usuario.getNome(),
                usuario.getEmail(),
                usuario.getCpf(),
                usuario.getTelefone(),
                usuario.getEndereco()

        );
    }

}
