package com.projetoPessoal.gerenciadortarefas.model.Endereco;


import jakarta.persistence.Embeddable;
import jakarta.persistence.Embedded;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Service;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Embeddable
public class Endereco {
    private String logradouro;
    private String bairro;
    private String cep;
    private String numero;
    private String complemento;
    private String cidade;
    private String uf;

    public void atualizarInformacoes(DadosEndereco dados){
        if(dados.logradouro() != null){
            this.logradouro = logradouro;
        }

        if(dados.complemento() != null){
            this.complemento = logradouro;
        }
        if(dados.bairro() != null){
            this.bairro = logradouro;
        }
        if(dados.cep() != null){
            this.cep = logradouro;
        }
        if(dados.cidade() != null){
            this.cidade = logradouro;
        }
        if(dados.uf() != null){
            this.uf = logradouro;
        }
        if (dados.numero() != null){
            this.numero = numero;
        }

    }




}
