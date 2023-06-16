package br.com.rodarte.testejava.services;

import br.com.rodarte.testejava.repository.NotasRepository;
import br.com.rodarte.testejava.service.NotasService;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

class NotasServiceTest {

    private NotasService sut;
    private NotasRepository repository;

    @BeforeEach
    void inicializarMocks() {
        this.repository = Mockito.mock(NotasRepository.class);
        this.sut = new NotasService(repository);
    }

    @Test
    @DisplayName("Deve importar dados do excel e chamar save all do repository")
    void deveImportarDadosDoExcelEChamarSaveAllDoRepository() {
        String excelFilePath = "Docs/Documentos_Base/Base Importação Teste RN.xlsx";

        Workbook workbook = new XSSFWorkbook();
        Sheet sheet = workbook.createSheet();
        Row headerRow = sheet.createRow(0);
        headerRow.createCell(0).setCellValue("ID");
        headerRow.createCell(1).setCellValue("Nome");
        headerRow.createCell(2).setCellValue("Sexo");
        headerRow.createCell(3).setCellValue("Data Nascimento");
        headerRow.createCell(4).setCellValue("Nota Trimestre 1");
        headerRow.createCell(5).setCellValue("Nota Trimestre 2");
        headerRow.createCell(6).setCellValue("Nota Trimestre 3");

        Row dataRow = sheet.createRow(1);
        dataRow.createCell(0).setCellValue(1);
        dataRow.createCell(1).setCellValue("Teste");
        dataRow.createCell(2).setCellValue("M");
        dataRow.createCell(3).setCellValue("01/01/2000");
        dataRow.createCell(4).setCellValue(7.5);
        dataRow.createCell(5).setCellValue(8.0);
        dataRow.createCell(6).setCellValue(9.5);

        Mockito.when(repository.saveAll(Mockito.any())).thenReturn(new ArrayList<>());

        sut.importarDados();

        Mockito.verify(repository, Mockito.times(1)).saveAll(Mockito.any());
    }

    @Test
    @DisplayName("Deve exportar os dados para uma planilha excel")
    void deveExportarOsDadosParaUmaPlanilhaExcel() throws Exception {
        NotasRepository.TabelaNotasDTO tabelaNotasDTO1 = Mockito.mock(NotasRepository.TabelaNotasDTO.class);
        NotasRepository.TabelaNotasDTO tabelaNotasDTO2 = Mockito.mock(NotasRepository.TabelaNotasDTO.class);

        Mockito.when(tabelaNotasDTO1.getNome()).thenReturn("João");
        Mockito.when(tabelaNotasDTO1.getMedia()).thenReturn(7.5);
        Mockito.when(tabelaNotasDTO1.getIdade()).thenReturn(20);

        Mockito.when(tabelaNotasDTO2.getNome()).thenReturn("Maria");
        Mockito.when(tabelaNotasDTO2.getMedia()).thenReturn(8.0);
        Mockito.when(tabelaNotasDTO2.getIdade()).thenReturn(22);

        List<NotasRepository.TabelaNotasDTO> dados = new ArrayList<>();
        dados.add(tabelaNotasDTO1);
        dados.add(tabelaNotasDTO2);

        Mockito.when(repository.tabelaOrganizada()).thenReturn(dados);

        NotasService notasService = new NotasService(repository);

        String fileName = "relatorio-alunos.xlsx";

        notasService.exportToExcel(fileName);

        Workbook workbook = WorkbookFactory.create(new File(fileName));
        Sheet sheet = workbook.getSheetAt(0);

        Row headerRow = sheet.getRow(0);
        Assertions.assertEquals("Nome", headerRow.getCell(0).getStringCellValue());
        Assertions.assertEquals("Média de Notas", headerRow.getCell(1).getStringCellValue());
        Assertions.assertEquals("Idade", headerRow.getCell(2).getStringCellValue());

        Row dataRow1 = sheet.getRow(1);
        Assertions.assertEquals("João", dataRow1.getCell(0).getStringCellValue());
        Assertions.assertEquals(7.5, dataRow1.getCell(1).getNumericCellValue());
        Assertions.assertEquals(20, dataRow1.getCell(2).getNumericCellValue());

        Row dataRow2 = sheet.getRow(2);
        Assertions.assertEquals("Maria", dataRow2.getCell(0).getStringCellValue());
        Assertions.assertEquals(8.0, dataRow2.getCell(1).getNumericCellValue());
        Assertions.assertEquals(22, dataRow2.getCell(2).getNumericCellValue());

        workbook.close();
    }
}