package com.ronie.algalogapi.domain.service;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.ronie.algalogapi.domain.exception.NegocioException;
import com.ronie.algalogapi.domain.model.Entrega;
import com.ronie.algalogapi.domain.model.StatusEntrega;
import com.ronie.algalogapi.domain.repository.EntregaRepository;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class FinalizacaoEntregaService {

    private EntregaRepository entregaRepository;
    private BuscaEntregaService buscaEntregaService;

    @Transactional
    public void finalizar(Long entregaId) {
        Entrega entrega = buscaEntregaService.buscar(entregaId);

        entrega.finalizar();
        entregaRepository.save(entrega);
    }
}
