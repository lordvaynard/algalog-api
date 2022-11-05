package com.ronie.algalogapi.domain.service;

import java.time.OffsetDateTime;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.ronie.algalogapi.domain.model.Cliente;
import com.ronie.algalogapi.domain.model.Entrega;
import com.ronie.algalogapi.domain.model.StatusEntrega;
import com.ronie.algalogapi.domain.repository.EntregaRepository;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class SolicitacaoEntregaService {
    private CatalogoClienteService catalogoClienteService;
    private EntregaRepository entregaRepository;

    @Transactional
    public Entrega solicitar(Entrega entrega){
        Cliente cliente = catalogoClienteService.buscar(entrega.getCliente().getId());
        
        entrega.setCliente(cliente);
        entrega.setStatus(StatusEntrega.PENDENTE);
        entrega.setDataPedido(OffsetDateTime.now());

        return entregaRepository.save(entrega);
    }
}
