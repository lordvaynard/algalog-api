package com.ronie.algalogapi.domain.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ronie.algalogapi.domain.exception.NegocioException;
import com.ronie.algalogapi.domain.model.Entrega;
import com.ronie.algalogapi.domain.model.Ocorrencia;
import com.ronie.algalogapi.domain.repository.EntregaRepository;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class RegistroOcorrenciaService {

    private BuscaEntregaService buscaEntregaService; // inseção dependencia repositorio de entrega

    
    @Transactional //não precisa de um método sabe pois ao final da transação o que foi feito pelo método adicionar ocorrencia vai ser gravado
    public Ocorrencia registrar(Long entregaId, String descricao) {
        Entrega entrega = buscaEntregaService.buscar(entregaId);

        return entrega.adicionarOcorrencia(descricao);
    }
}
