package br.com.rodarte.testejava.service;

import br.com.rodarte.testejava.entity.Notas;
import br.com.rodarte.testejava.repository.NotasRepository;
import lombok.RequiredArgsConstructor;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class NotasService {

    private final NotasRepository repository;

    public void importarDados() {
        String excelFilePath = "Docs/Documentos_Base/Base Importação Teste RN.xlsx";

        Notas nota;
        try (FileInputStream inputStream = new FileInputStream(excelFilePath);
             Workbook workbook = new XSSFWorkbook(inputStream)) {

            Sheet sheet = workbook.getSheetAt(0);
            List<Notas> notas = new ArrayList<>();

            DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

            for (int i = 1; i <= sheet.getLastRowNum(); i++) {
                Row row = sheet.getRow(i);

                Long id = (long) row.getCell(0).getNumericCellValue();
                String nome = row.getCell(1).getStringCellValue();
                char sexo = row.getCell(2).getStringCellValue().charAt(0);
                LocalDate dataNascimento;
                if (row.getCell(3).getCellType() == CellType.NUMERIC) {
                    dataNascimento = row.getCell(3).getLocalDateTimeCellValue().toLocalDate();
                } else {
                    dataNascimento = LocalDate.parse(row.getCell(3).getStringCellValue(), dateFormatter);
                }
                float notaTrimestre1 = (float) row.getCell(4).getNumericCellValue();
                float notaTrimestre2 = (float) row.getCell(5).getNumericCellValue();
                float notaTrimestre3 = (float) row.getCell(6).getNumericCellValue();

                nota = new Notas();
                nota.setId(id);
                nota.setNome(nome);
                nota.setSexo(sexo);
                nota.setDataNascimento(dataNascimento);
                nota.setNotaTrimestreUm(notaTrimestre1);
                nota.setNotaTrimestreDois(notaTrimestre2);
                nota.setNotaTrimestreTres(notaTrimestre3);

                notas.add(nota);
            }

            repository.saveAll(notas);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void exportToExcel(String fileName) {
        try (Workbook workbook = new XSSFWorkbook();
             FileOutputStream fileOutputStream = new FileOutputStream(fileName)) {
            Sheet sheet = workbook.createSheet("Dados");

            List<NotasRepository.TabelaNotasDTO> dados = repository.tabelaOrganizada();

            Row headerRow = sheet.createRow(0);
            headerRow.createCell(0).setCellValue("Nome");
            headerRow.createCell(1).setCellValue("Média de Notas");
            headerRow.createCell(2).setCellValue("Idade");

            int rowNum = 1;
            for (NotasRepository.TabelaNotasDTO dado : dados) {
                Row row = sheet.createRow(rowNum++);
                row.createCell(0).setCellValue(dado.getNome());
                row.createCell(1).setCellValue(dado.getMedia());
                row.createCell(2).setCellValue(dado.getIdade());
            }

            workbook.write(fileOutputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}