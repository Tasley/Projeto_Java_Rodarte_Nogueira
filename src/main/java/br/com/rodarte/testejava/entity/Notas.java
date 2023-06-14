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

    public String nome;
    public char sexo;

    @Column(name="data_nascimento")
    @DateTimeFormat(pattern="dd/MM/yyyy")
    public LocalDate dataNascimento;

    @Column(name="nota_trimestre_um")
    public Float notaTrimestreUm;

    @Column(name="nota_trimestre_dois")
    public Float notaTrimestreDois;

    @Column(name="nota_trimestre_tres")
    public Float notaTrimestreTres;

}