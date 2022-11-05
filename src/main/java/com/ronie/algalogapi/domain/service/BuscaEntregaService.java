package com.ronie.algalogapi.domain.service;

import org.springframework.stereotype.Service;

import com.ronie.algalogapi.domain.exception.EntidadeNaoEncontradaException;
import com.ronie.algalogapi.domain.model.Entrega;
import com.ronie.algalogapi.domain.repository.EntregaRepository;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class BuscaEntregaService {

    private EntregaRepository entregaRepository;
    
    public Entrega buscar (Long entregaId){
        return entregaRepository.findById(entregaId)
                .orElseThrow(() -> new EntidadeNaoEncontradaException("Entrega não encontrada"));        
    }
}
