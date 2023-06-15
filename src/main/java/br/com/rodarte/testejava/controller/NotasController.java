package br.com.rodarte.testejava.controller;

import br.com.rodarte.testejava.DTO.NotasDTO;
import br.com.rodarte.testejava.converters.NotasConverter;
import br.com.rodarte.testejava.entity.Notas;
import br.com.rodarte.testejava.repository.NotasRepository;
import br.com.rodarte.testejava.service.ExcelToMySql;
import jakarta.annotation.security.PermitAll;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/notas")
@RequiredArgsConstructor
@PermitAll
public class NotasController {

    @Autowired
    private final NotasRepository notasRepository;
    @Autowired
    private final ExcelToMySql excelToMySql;
    private final NotasConverter converter;

    @GetMapping("/todos")
    public List<NotasDTO> listaCompleta() {
        return notasRepository.findAll().stream().map(converter::entityToDTO).toList();
    }

    @PatchMapping("/extrair-excel")
    public void importarDados() {
        excelToMySql.importarDados();
    }

    @GetMapping("/tabela-organizada")
    public List<NotasRepository.TabelaNotasDTO> tabelaOrganizada() {
        return notasRepository.tabelaOrganizada();
    }
}