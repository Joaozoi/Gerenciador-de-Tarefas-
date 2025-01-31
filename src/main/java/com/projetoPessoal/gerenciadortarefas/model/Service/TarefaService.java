package com.projetoPessoal.gerenciadortarefas.model.Service;


import com.projetoPessoal.gerenciadortarefas.infra.Exception.ExceptionPersonalizada;
import com.projetoPessoal.gerenciadortarefas.model.Repository.TarefaRepository;
import com.projetoPessoal.gerenciadortarefas.model.Repository.UsuarioRepository;
import com.projetoPessoal.gerenciadortarefas.model.Tarefa.DadosAtualizaTarefa;
import com.projetoPessoal.gerenciadortarefas.model.Tarefa.DadosListagemTarefa;
import com.projetoPessoal.gerenciadortarefas.model.Tarefa.Tarefa;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;


@Service
public class TarefaService {

    @Autowired
    private TarefaRepository repository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private PrioridadeTarefaService prioridadeTarefaService;

    public Tarefa criarTarefa(Tarefa tarefa, Long usuarioId) throws ExceptionPersonalizada {
        if (usuarioId == null){
            throw new ExceptionPersonalizada("Usuário não informado para a tarefa");
        }
        var usuario = usuarioRepository.findById(usuarioId)
                .orElseThrow(() -> new ExceptionPersonalizada("Usuário não encontrado"));

            if(tarefa.getPrazo().isBefore(LocalDateTime.now()) && tarefa.getPrazo() == null){
                throw new ExceptionPersonalizada("Não é possivel criar uma tarefa com prazo ja vencido!");
            }

            prioridadeTarefaService.definirPrioridade(tarefa);
        tarefa.setUsuario(usuario);
        return repository.save(tarefa);
    }

    public Page<DadosListagemTarefa> listarTarefasPorUsuario(Long usuarioId, Pageable paginacao)throws ExceptionPersonalizada {
        var usuario = usuarioRepository.findById(usuarioId)
                .orElseThrow(() -> new ExceptionPersonalizada("Usuário não encontrado"));
        return repository.findByUsuarioIdAndAtivoTrue(usuarioId, paginacao)
                .map(DadosListagemTarefa::new);
    }


    public Tarefa atualizarTarefa(Long id, DadosAtualizaTarefa dados) throws ExceptionPersonalizada {
        var tarefa = repository.findById(id)
                .orElseThrow(() -> new ExceptionPersonalizada("Tarefa não encontrada"));



        tarefa.atualizarInformacoes(dados);

        tarefa.verificarConclusaoAutomatico();
        prioridadeTarefaService.definirPrioridade(tarefa);
        return repository.save(tarefa);
    }




    public void deletarTarefa(Long id) throws ExceptionPersonalizada {
        var tarefa = repository.findById(id)
                .orElseThrow(() -> new ExceptionPersonalizada("Tarefa não encontrada"));
        tarefa.inativar();
        repository.save(tarefa);
    }


}
