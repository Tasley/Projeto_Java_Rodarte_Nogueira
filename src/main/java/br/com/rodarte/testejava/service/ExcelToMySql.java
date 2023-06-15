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
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ExcelToMySql {

    private final NotasRepository notasRepository;

    public void importarDados() {
        String excelFilePath = "Docs/Base Importação Teste RN.xlsx";

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

                Notas nota = new Notas();
                nota.setId(id);
                nota.setNome(nome);
                nota.setSexo(sexo);
                nota.setDataNascimento(dataNascimento);
                nota.setNotaTrimestreUm(notaTrimestre1);
                nota.setNotaTrimestreDois(notaTrimestre2);
                nota.setNotaTrimestreTres(notaTrimestre3);

                notas.add(nota);
            }

            notasRepository.saveAll(notas);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}