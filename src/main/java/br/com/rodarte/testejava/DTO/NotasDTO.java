package br.com.rodarte.testejava.DTO;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class NotasDTO {

    private Long id;
    private String nome;
    private char sexo;
    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate dataNascimento;
    private Float notaTrimestreUm;
    private Float notaTrimestreDois;
    private Float notaTrimestreTres;

}
