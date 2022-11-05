package com.ronie.algalogapi.common;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.ronie.algalogapi.api.model.EntregaModel;
import com.ronie.algalogapi.domain.model.Entrega;

@Configuration
public class ModelMapperConfig {

    Entrega entrega;
    EntregaModel entregaModel;
    
    @Bean
    public ModelMapper modelMapper() {
    ModelMapper modelMapper = new ModelMapper();
    
    modelMapper.createTypeMap(Entrega.class, EntregaModel.class)
    .addMappings(mapper -> mapper.map(Entrega::getTaxa, EntregaModel::setTaxaFrete));
    
    return modelMapper;
    }
    
    }
    
    // .typeMap(entrega.class, entregaModel.class)
    //            .addMappings(mapper -> {
    //             mapper.map(entrega -> entrega.getTaxa(),
    //             entregaModel::setTaxaFrete);
    //            });

               
 // entregaModel.setTaxaFrete(entrega.getTaxa());

//alura
// modelMapper.addMappings(new PropertyMap<entrega, entregaModel>() 
// {@Override protected void configure() { 
// String taxa = source().getTaxa(); 
// map().setTaxaFrete(taxa); }} ); 

