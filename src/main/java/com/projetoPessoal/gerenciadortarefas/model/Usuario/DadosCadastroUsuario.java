package com.projetoPessoal.gerenciadortarefas.model.Usuario;

import com.projetoPessoal.gerenciadortarefas.model.Endereco.Endereco;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import org.springframework.cglib.core.Local;

import java.time.LocalDateTime;

public record DadosCadastroUsuario(
        @NotBlank String nome,
        @NotBlank @Email String email,
        @NotBlank String telefone,
        @NotBlank @Pattern(regexp = "\\d{3}\\.?\\d{3}\\.?\\d{3}\\-?\\d{2}") String cpf,
        @NotNull Endereco endereco





        ) {

}
