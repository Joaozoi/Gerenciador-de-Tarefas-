package com.projetoPessoal.gerenciadortarefas.model.Usuario;


import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.projetoPessoal.gerenciadortarefas.model.Endereco.Endereco;
import com.projetoPessoal.gerenciadortarefas.model.Tarefa.Tarefa;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Setter
@Getter
@Table (name = "usuario")
@Entity (name = "Usuario")
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Size(max = 100)
    private String nome;

    @NotBlank
    @Size(max = 100)
    @Email
    private String email;

    @NotBlank
    @Size (min = 9)
    @Column(unique = true, nullable = false)
    private String telefone;

    @Embedded
    private Endereco endereco;

    @Pattern(regexp = "\\d{11}", message = "O CPF deve conter exatamente 11 dígitos numéricos")
    private String cpf;


    @NotBlank
    @Size(min = 6, max = 20)
    private String senha;

    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private LocalDateTime dataCriacao;


    @OneToMany(mappedBy = "usuario")
    @JsonManagedReference
    private List<Tarefa> tarefas = new ArrayList<>();



    @Column(name = "ativo", nullable = false)
    private boolean ativo;

    public Usuario(DadosCadastroUsuario dados) {
        this.ativo = true;
        this.nome = dados.nome();
        this.email = dados.email();
        this.telefone = dados.telefone();
        this.cpf = dados.cpf();
        this.endereco = dados.endereco();
    }

    public void atualizarInformacoes(DadosAtualizaUsuario dados){
        if(dados.nome() != null){
            this.nome = dados.nome();
        }
        if(dados.telefone() != null) {
            this.telefone = dados.telefone();
        }
        if (dados.email() != null){
            this.email = dados.email();
        }
        if(dados.endereco() != null){
            this.endereco.atualizarInformacoes(dados.endereco());
        }
    }

    public void inativar(){
        this.ativo = false;
    }
}


