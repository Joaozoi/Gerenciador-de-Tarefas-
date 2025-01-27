package com.projetoPessoal.gerenciadortarefas.model.Tarefa.Repository;

import com.projetoPessoal.gerenciadortarefas.model.Tarefa.Tarefa;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TarefaRepository extends JpaRepository<Tarefa, Long> {
}
