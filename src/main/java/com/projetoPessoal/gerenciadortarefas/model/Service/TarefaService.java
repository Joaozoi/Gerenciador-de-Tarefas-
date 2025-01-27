package com.projetoPessoal.gerenciadortarefas.model.Tarefa.Service;


import com.projetoPessoal.gerenciadortarefas.infra.Exception.ExceptionPersonalizada;
import com.projetoPessoal.gerenciadortarefas.model.Tarefa.Repository.TarefaRepository;
import com.projetoPessoal.gerenciadortarefas.model.Tarefa.Tarefa;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;

@Service
public class TarefaService {

    @Autowired
    private TarefaRepository repository;

    public Tarefa criarTarefa(Tarefa tarefa){
        return repository.save(tarefa);
    }

    public List<Tarefa> listarTarefa(){
        return repository.findAll();
    }

    public Optional<Tarefa> buscarPorId(Long id) {
        return repository.findById(id);
    }

    public Tarefa atualizarTarefa(Long id, Tarefa tarefaAtualizada) throws ExceptionPersonalizada {
       Tarefa tarefaExistente = repository.findById(id)
               .orElseThrow(()-> new ExceptionPersonalizada( "Tarefa não encontrada"));

        tarefaExistente.setTitulo(tarefaAtualizada.getTitulo());
        tarefaExistente.setDescricao(tarefaAtualizada.getDescricao());
        tarefaExistente.setStatus(tarefaAtualizada.getStatus());
        tarefaExistente.setPrazo(tarefaAtualizada.getPrazo());
        tarefaExistente.setPrioridade(tarefaAtualizada.getPrioridade());
        tarefaExistente.setConcluidoPor(tarefaAtualizada.getConcluidoPor());

        return  repository.save(tarefaExistente);

    }


    public void deletarTarefa(Long id) {
        var tarefa = repository.getReferenceById(id);
        tarefa.inativar();

    }


}
