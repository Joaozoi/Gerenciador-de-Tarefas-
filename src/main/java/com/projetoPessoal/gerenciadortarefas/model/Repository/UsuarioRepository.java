package com.projetoPessoal.gerenciadortarefas.model.Usuario.Repository;

import com.projetoPessoal.gerenciadortarefas.model.Usuario.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
}
