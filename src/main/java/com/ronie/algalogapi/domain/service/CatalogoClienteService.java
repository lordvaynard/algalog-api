package com.ronie.algalogapi.domain.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ronie.algalogapi.domain.exception.NegocioException;
import com.ronie.algalogapi.domain.model.Cliente;
import com.ronie.algalogapi.domain.repository.ClienteRepository;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service //Onde são implementadas as regras de negócio
public class CatalogoClienteService {
    
    private ClienteRepository clienteRepository;

    @Transactional //metodo executado dentro de uma transação (commit)
    public Cliente salvar(Cliente cliente){
        boolean emailEmUso = clienteRepository.findByEmail(cliente.getEmail())
                .stream()
                .anyMatch(clienteExistente -> !clienteExistente .equals(cliente));//se diferente vai dar true

        if(emailEmUso) {
            throw new NegocioException("Já existe um outro cliente cadastrado com esse e-mail");
        }
        return clienteRepository.save(cliente);
    }

    @Transactional
    public void excluir(Long clienteId){
        clienteRepository.deleteById(clienteId);
    }
}
