package br.com.rodarte.testejava.controller;

import br.com.rodarte.testejava.DTO.NotasDTO;
import br.com.rodarte.testejava.converters.NotasConverter;
import br.com.rodarte.testejava.repository.NotasRepository;
import br.com.rodarte.testejava.service.NotasService;
import jakarta.annotation.security.PermitAll;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    private final NotasService service;
    private final NotasConverter converter;

    @GetMapping("/todos")
    public List<NotasDTO> listaCompleta() {
        return notasRepository.findAll().stream().map(converter::entityToDTO).toList();
    }

    @PatchMapping("/extrair-excel")
    public ResponseEntity<String> importarDados() {
        service.importarDados();
        return  ResponseEntity.status(HttpStatus.OK).body("Dados extraídos com sucesso!");
    }

    @GetMapping("/tabela-organizada")
    public List<NotasRepository.TabelaNotasDTO> tabelaOrganizada() {
        return notasRepository.tabelaOrganizada();
    }

    @GetMapping("/exportar-excel")
    public ResponseEntity<String> exportarParaExcel() {
        service.exportToExcel("Docs/Export da tabela/relatorio-alunos.xlsx");
        return ResponseEntity.status(HttpStatus.OK).body( "Dados exportados para o arquivo 'relatorio-alunos.xlsx'");
    }
}