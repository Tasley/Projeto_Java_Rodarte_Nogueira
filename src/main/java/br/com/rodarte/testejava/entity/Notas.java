package br.com.rodarte.testejava.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@Entity
@Table(name = "t_notas")
public class Notas {

    @jakarta.persistence.Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "identificacao", nullable = false)
    private Long id;

    private String nome;
    private char sexo;

    @Column(name="data_nascimento")
    private LocalDate dataDeNascimento;

    private Float notaTrimestreUm;
    private Float notaTrimestreDois;
    private Float notaTrimestreTres;

}