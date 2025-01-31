package com.projetoPessoal.gerenciadortarefas.model.Repository;

import com.projetoPessoal.gerenciadortarefas.model.Tarefa.Tarefa;
import com.projetoPessoal.gerenciadortarefas.model.Usuario.Usuario;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;


public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

    Page<Usuario> findByAtivoTrue(Pageable paginacao);

    boolean existsById(Long id);


    @Query("SELECT u FROM Usuario u WHERE u.ativo = true")
    List<Tarefa> findAllAtivas();


}
