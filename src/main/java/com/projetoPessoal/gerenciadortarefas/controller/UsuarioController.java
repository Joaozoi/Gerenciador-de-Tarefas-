package com.projetoPessoal.gerenciadortarefas.controller;


import com.projetoPessoal.gerenciadortarefas.infra.Exception.ExceptionPersonalizada;
import com.projetoPessoal.gerenciadortarefas.model.Service.UsuarioService;
import com.projetoPessoal.gerenciadortarefas.model.Usuario.*;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("usuario")
public class UsuarioController {

    @Autowired
    private UsuarioService service;

    @PostMapping
    @Transactional
    public ResponseEntity cadastrar(@RequestBody @Valid DadosCadastroUsuario dados, UriComponentsBuilder uriBuilder){
        var usuario = new Usuario(dados);
        service.criarUsuario(usuario);

        var uri = uriBuilder.path("/usuario/{id}").buildAndExpand(usuario.getId()).toUri();

        return ResponseEntity.created(uri).body(new DadosDetalhamentoUsuario(usuario));
    }


    @GetMapping
    public ResponseEntity<Page<DadosListagemUsuario>> listar(@PageableDefault (page = 0, size =10, sort={"nome"})Pageable paginacao){
        var page = service.listar(paginacao);
        return  ResponseEntity.ok(page);
    }


    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity atualizar(@PathVariable Long id, @RequestBody @Valid DadosAtualizaUsuario dados) throws ExceptionPersonalizada {
        var usuario = service.atualizarUsuario(id, dados);
        return ResponseEntity.ok(new DadosDetalhamentoUsuario(usuario));
    }


    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity deletar(@PathVariable Long id) throws ExceptionPersonalizada {
        service.deletar(id);
        return  ResponseEntity.noContent().build();
    }




}
