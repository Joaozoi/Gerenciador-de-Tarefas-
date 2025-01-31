package com.projetoPessoal.gerenciadortarefas.model.Tarefa;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.projetoPessoal.gerenciadortarefas.model.Usuario.Usuario;
import jakarta.persistence.*;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;
import java.util.Optional;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "tarefa")
@Entity (name = "Tarefa")
@EntityListeners(AuditingEntityListener.class)
@EqualsAndHashCode(of = "id")
public class Tarefa {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "O título é obrigatório.")
    @Size(max = 100, message = "O título deve ter no máximo 100 caracteres.")
    private String titulo;

    @Size(max = 255, message = "A descrição deve ter no máximo 255 caracteres.")
    private String descricao;

    @Enumerated(EnumType.STRING)
    @NotNull(message = "O status é obrigatório.")
    private Status status;

    @CreatedDate
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private LocalDateTime dataCriacao;

    @LastModifiedDate
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private LocalDateTime ultimaAtualizacao;

    @ManyToOne
    @JoinColumn(name = "usuario_id", nullable = false)
    @JsonBackReference
    private Usuario usuario;

    @Enumerated(EnumType.STRING)
    private Prioridade prioridade;

    @Future(message = "O prazo deve ser uma data no futuro.")
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private LocalDateTime prazo;

    @JsonFormat(pattern = "dd-MM-yyyy'T'HH:mm:ss")
    private LocalDateTime dataLimite;

    private String concluidoPor;



    @Column(name = "ativo", nullable = false)
    private boolean ativo;


    public void inativar(){
        this.ativo = false;
    }

    public boolean isAtrasada() {
        return Optional.ofNullable(prazo)
                .map(p -> LocalDateTime.now().isAfter(p))
                .orElse(false);
    }


    public Tarefa(DadosCadastroTarefa dados){
        this.titulo = dados.titulo();
        this.descricao = dados.descricao();
        this.status = dados.status();
        this.prioridade = dados.prioridade();
        this.prazo = dados.prazo();
        this.usuario = usuario;
        this.ativo = true;
    }

    public void atualizarInformacoes(DadosAtualizaTarefa dados) {
        if (dados.titulo() != null && !dados.titulo().isBlank()) {
            this.titulo = dados.titulo();
        }
        if (dados.descricao() != null && !dados.descricao().isBlank()) {
            this.descricao = dados.descricao();
        }
        if (dados.status() != null) {
            this.status = dados.status();
        }
        if (dados.prioridade() != null) {
            this.prioridade = dados.prioridade();
        }
        if (dados.prazo() != null) {
            this.prazo = dados.prazo();
        }
        if (dados.concluidoPor() != null) {
            this.concluidoPor = dados.concluidoPor();
        }
    }


    public void concluirTarefa(DadosConclusaoTarefa dados){
        if(this.status != Status.CONCLUIDA){
            if(dados != null) {
                this.status = Status.CONCLUIDA;
                this.concluidoPor = dados.nome();
                this.ultimaAtualizacao = dados.dataConclusao();
            } else{
                this.status = Status.CONCLUIDA;
                this.concluidoPor = "Sistema";
                this.ultimaAtualizacao = LocalDateTime.now();
            }
        }
    }


    public void verificarConclusaoAutomatico() {
        if(dataLimite != null && LocalDateTime.now().isAfter(dataLimite) && this.status != Status.CONCLUIDA){
            this.concluirTarefa(null);
        }
    }
}
