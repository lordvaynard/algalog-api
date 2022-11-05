package com.ronie.algalogapi.domain.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.validation.groups.Default;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotBlank;

import com.ronie.algalogapi.domain.ValidationGroups;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Getter
@Setter
@Entity //entidade no banco
public class Cliente {
    // @NotNull(groups = ValidationGroups.ClienteId.class) //Grupo de validação criado, não precisa passar o campo na validação padrão //não precisa devido a representação model input
    @EqualsAndHashCode.Include
    @Id //chave primaria
    @GeneratedValue(strategy = GenerationType.IDENTITY) //valor gerado auto incremento
    private Long id;

    @NotBlank(groups = Default.class) //Grupo de validação padrão, todas validações ja tem essa validação por default, não precisa colocar
    @Email
    @Size(max = 255)
    private String email;

    @NotBlank
    @Size(max = 60)
    private String nome;

    @NotBlank
    @Size(max = 20)
    @Column(name="fone")
    private String telefone;
 

}
