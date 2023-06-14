package br.com.rodarte.testejava.controller;

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
    private NotasRepository notasRepository;
    @Autowired
    private ExcelToMySql excelToMySql;

    @GetMapping("/todos")
    public List<Notas> listaCompleta() {
        return notasRepository.findAll();
    }

    @PatchMapping("/extrair-excel")
    public void importarDados() {
        excelToMySql.importarDados();
    }

    @GetMapping("/tabela-organizada")
    public List<Notas> tabelaOrganizada() {
        return notasRepository.tabelaOrganizada();
    }
}