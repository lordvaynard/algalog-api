package com.ronie.algalogapi.api.model;
//o nome do arquivo poderia ser representationModel ou Response ou Output

import java.math.BigDecimal;
import java.time.OffsetDateTime;

import com.ronie.algalogapi.domain.model.StatusEntrega;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EntregaModel {
    private Long id;
    private ClienteResumoModel cliente; //Mudando o modelo de representação da API sem modificar a entidade do banco de dados
    private DestinatarioModel destinatario; //Classe criada para retornar o destinatario direto na API
    private BigDecimal taxaFrete;
    private StatusEntrega status;
    private OffsetDateTime dataPedido;
    private OffsetDateTime dataFinalizacao;    
}
