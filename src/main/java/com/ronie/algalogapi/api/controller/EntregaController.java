package com.ronie.algalogapi.api.controller;

import java.util.List;

import javax.validation.Valid;

import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.ronie.algalogapi.api.assembler.EntregaAssembler;
import com.ronie.algalogapi.api.model.DestinatarioModel;
import com.ronie.algalogapi.api.model.EntregaModel;
import com.ronie.algalogapi.api.model.input.EntregaInputModel;
import com.ronie.algalogapi.domain.model.Entrega;
import com.ronie.algalogapi.domain.repository.EntregaRepository;
import com.ronie.algalogapi.domain.service.FinalizacaoEntregaService;
import com.ronie.algalogapi.domain.service.SolicitacaoEntregaService;

import lombok.AllArgsConstructor;

@AllArgsConstructor //Construtor de todos argumentos
@RestController
@RequestMapping("/entregas")
public class EntregaController {

    private EntregaRepository entregaRepository; //Injeção de dependencia
    private SolicitacaoEntregaService solicitacaoEntregaService;
    private FinalizacaoEntregaService finalizacaoEntregaService;
    private EntregaAssembler entregaAssembler; 

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public EntregaModel solicitar(@Valid @RequestBody EntregaInputModel entregaInputModel) {
        Entrega novaEntrega = entregaAssembler.toEntity(entregaInputModel);

        Entrega entregaSolicitada = solicitacaoEntregaService.solicitar(novaEntrega);
        return entregaAssembler.toModel(entregaSolicitada);
    }

    @PutMapping("/{entregaId}/finalizacao")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void finalizar(@PathVariable Long entregaId){
        finalizacaoEntregaService.finalizar(entregaId);
    }

    @GetMapping
    public List<EntregaModel> listar(){
            return entregaAssembler.toCollectionModel(entregaRepository.findAll());
    }

    @GetMapping("/{entregaId}")
    public ResponseEntity<EntregaModel> buscar(@PathVariable Long entregaId){//entrega aqui é a mesma classe para Representation Model e Domain Model
        return entregaRepository.findById(entregaId)
                .map(entrega -> ResponseEntity.ok(entregaAssembler.toModel(entrega))) //Caso encontrado, retorna o objeto entrega
                .orElse(ResponseEntity.notFound().build()); //caso não encontrado retorna o status not found                    
    }
}
