package com.projetoPessoal.gerenciadortarefas.model.Repository;

import com.projetoPessoal.gerenciadortarefas.model.Tarefa.Status;
import com.projetoPessoal.gerenciadortarefas.model.Tarefa.Tarefa;

import com.projetoPessoal.gerenciadortarefas.model.Usuario.Usuario;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface TarefaRepository extends JpaRepository<Tarefa, Long> {
    Page<Tarefa> findByAtivoTrue(Pageable paginacao);

    Page<Tarefa> findByUsuarioIdAndAtivoTrue(Long id, Pageable pageable);


    @Query("SELECT t FROM Tarefa t WHERE t.ativo = true")
    List<Tarefa> findAllAtivas();

}
