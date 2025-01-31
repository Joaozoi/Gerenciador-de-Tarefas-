package com.projetoPessoal.gerenciadortarefas.model.Usuario;

import com.projetoPessoal.gerenciadortarefas.model.Endereco.DadosEndereco;
import com.projetoPessoal.gerenciadortarefas.model.Tarefa.Tarefa;

public record DadosListagemUsuario(Long id, String nome, String email, String cpf, DadosEndereco endereco) {
    public DadosListagemUsuario(Usuario usuario){
        this(usuario.getId(), usuario.getNome(), usuario.getEmail(), usuario.getCpf(), new DadosEndereco(usuario.getEndereco()));
    }


}
