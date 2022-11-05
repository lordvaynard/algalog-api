package com.ronie.algalogapi.api.model.input;

import org.hibernate.validator.constraints.NotBlank;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class DestinatarioInput {
    
    @NotBlank
    private String nome;

    @NotBlank
    private String logradouro;

    @NotBlank
    private String numero;

    @NotBlank
    private String complemento;

    @NotBlank
    private String bairro;
}
