package br.com.rodarte.testejava.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

@Getter
@Setter
@Entity
@Table(name = "t_notas")
public class Notas {

    @jakarta.persistence.Id
    @Column(name = "identificacao", nullable = false)
    private Long id;

    private String nome;
    private char sexo;

    @Column(name="data_nascimento")
    @DateTimeFormat(pattern="dd/MM/yyyy")
    private LocalDate dataNascimento;

    @Column(name="nota_trimestre_um")
    private Float notaTrimestreUm;

    @Column(name="nota_trimestre_dois")
    private Float notaTrimestreDois;

    @Column(name="nota_trimestre_tres")
    private Float notaTrimestreTres;

}