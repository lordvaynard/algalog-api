package com.ronie.algalogapi.domain.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotBlank;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Getter
@Setter
@Entity //entidade no banco
public class Cliente {

    @EqualsAndHashCode.Include
    @Id //chave primaria
    @GeneratedValue(strategy = GenerationType.IDENTITY) //valor gerado auto incremento
    private Long id;

    @NotBlank
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
