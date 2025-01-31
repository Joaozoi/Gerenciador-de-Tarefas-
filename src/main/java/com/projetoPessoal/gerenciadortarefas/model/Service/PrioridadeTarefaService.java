package com.projetoPessoal.gerenciadortarefas.model.Service;


import com.projetoPessoal.gerenciadortarefas.model.Tarefa.Prioridade;
import com.projetoPessoal.gerenciadortarefas.model.Tarefa.Tarefa;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.LocalDateTime;

@Service
public class PrioridadeTarefaService {

    public void definirPrioridade(Tarefa tarefa){
        long minutosRestantes = Duration.between(LocalDateTime.now(), tarefa.getPrazo()).toMinutes();

        if(minutosRestantes <= 10){
            tarefa.setPrioridade(Prioridade.URGENTE);
        } else if(minutosRestantes <= 1440){
            tarefa.setPrioridade(Prioridade.ALTA);
        } else if (minutosRestantes <= 100080){
            tarefa.setPrioridade(Prioridade.MEDIA);
        } else {
            tarefa.setPrioridade(Prioridade.BAIXA);
        }
    }
}
