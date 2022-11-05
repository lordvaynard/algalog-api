package com.ronie.algalogapi.domain.model;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import com.ronie.algalogapi.domain.exception.NegocioException;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
public class Entrega {

    @EqualsAndHashCode.Include
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // @Valid esta sendo validado no modelo de representação
    // @ConvertGroup(from = Default.class, to = ValidationGroups.ClienteId.class)
    // //Indica que a validação não sera a padrão, e sim somente do ClienteId ** não
    // precisa devido ao modelo de representação input
    // @NotNull
    @ManyToOne
    private Cliente cliente;

    // @Valid
    // @NotNull
    @Embedded
    private Destinatario destinatario;

    // @NotNull
    private BigDecimal taxa;

    @OneToMany(mappedBy = "entrega", cascade = CascadeType.ALL) // inverso da ocorrencia onde é many to one, mapeado por e citar a chave que
                                     // controla o mapeamento na outra ponta - cascateamento de ocorrencia
    private List<Ocorrencia> ocorrencias = new ArrayList<>();

    @Enumerated(EnumType.STRING)
    // @JsonProperty(access = Access.READ_ONLY) //não precisa devido a representação
    // de entrada
    private StatusEntrega status;

    // @JsonProperty(access = Access.READ_ONLY) //não precisa devido a representação
    // de entrada
    private OffsetDateTime dataPedido;

    // @JsonProperty(access = Access.READ_ONLY) //não precisa devido a representação
    // de entrada
    private OffsetDateTime dataFinalizacao;

    public Ocorrencia adicionarOcorrencia(String descricao) {
        Ocorrencia ocorrencia = new Ocorrencia();
        ocorrencia.setDescricao(descricao);
        ocorrencia.setDataRegistro(OffsetDateTime.now());
        ocorrencia.setEntrega(this);

        this.getOcorrencias().add(ocorrencia);

        return ocorrencia;
    }

    public void finalizar() {
        if(naoPodeSerFinalizada()){
            throw new NegocioException("Entrega não pode ser finalizada!");
        }
        setStatus((StatusEntrega.FINALIZADA));
        setDataFinalizacao(OffsetDateTime.now());
    }

    public boolean naoPodeSerFinalizada(){
        return !StatusEntrega.PENDENTE.equals(getStatus());
    }
}
