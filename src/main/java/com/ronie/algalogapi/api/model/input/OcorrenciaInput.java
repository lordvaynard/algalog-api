package com.ronie.algalogapi.api.model.input;

import org.hibernate.validator.constraints.NotBlank;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OcorrenciaInput {

    @NotBlank
    private String descricao;
}
