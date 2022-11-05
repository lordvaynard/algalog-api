package com.ronie.algalogapi.api.assembler;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import com.ronie.algalogapi.api.model.EntregaModel;
import com.ronie.algalogapi.api.model.input.EntregaInputModel;
import com.ronie.algalogapi.domain.model.Entrega;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Component
public class EntregaAssembler {
    public ModelMapper modelMapper; //Esta sendo configurado dentro da pasta de common

    public EntregaModel toModel(Entrega entrega){
        return modelMapper.map(entrega, EntregaModel.class);
    }

    public List<EntregaModel> toCollectionModel(List<Entrega> entregas) {
        return entregas.stream()
        .map(this::toModel)//conversão de stream entregas para entregas model
        .collect(Collectors.toList()); //conversão de stream em listagem
    }

    public Entrega toEntity(EntregaInputModel entregaInputModel){
        return modelMapper.map(entregaInputModel, Entrega.class);
    }
    
}
