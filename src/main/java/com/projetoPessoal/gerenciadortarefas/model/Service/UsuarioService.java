package com.projetoPessoal.gerenciadortarefas.model.Service;


import com.projetoPessoal.gerenciadortarefas.infra.Exception.ExceptionPersonalizada;
import com.projetoPessoal.gerenciadortarefas.model.Repository.UsuarioRepository;
import com.projetoPessoal.gerenciadortarefas.model.Usuario.DadosAtualizaUsuario;
import com.projetoPessoal.gerenciadortarefas.model.Usuario.DadosDetalhamentoUsuario;
import com.projetoPessoal.gerenciadortarefas.model.Usuario.DadosListagemUsuario;
import com.projetoPessoal.gerenciadortarefas.model.Usuario.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository repository;

    public void criarUsuario(Usuario usuario){
        repository.save(usuario);

    }

    public List<Usuario> obterTodosUsuarios(){
        return repository.findAll();
    }

    public Page<DadosListagemUsuario> listar(Pageable paginacao){
        return repository.findByAtivoTrue(paginacao).map(DadosListagemUsuario::new);
    }

    public Usuario atualizarUsuario (Long id, DadosAtualizaUsuario dados) throws ExceptionPersonalizada {
        var usuario = repository.findById(id)
                .orElseThrow(() -> new ExceptionPersonalizada("Usuario não encontrado"));
        usuario.atualizarInformacoes(dados);

        return repository.save(usuario);
    }


    public DadosDetalhamentoUsuario detalhar(Long id ){
        var usuario = repository.getReferenceById(id);

        return new DadosDetalhamentoUsuario(usuario);
    }

    public void deletar(Long id) throws ExceptionPersonalizada {
        var usuario = repository.findById(id)
                .orElseThrow(() -> new ExceptionPersonalizada("Usuário não encotrado"));
        usuario.inativar();

        repository.save(usuario);

    }

}
