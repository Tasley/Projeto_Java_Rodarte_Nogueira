package br.com.rodarte.testejava.service;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

public class ExcelToMySql {

    public void importarDados() {
        String jdbcURL = "jdbc:mysql://localhost:3306/notas";
        String username = "root";
        String password = "28012011";

        String excelFilePath = "Docs/Base Importação Teste RN.xlsx";
        String tableName = "notas";

        try (Connection connection = DriverManager.getConnection(jdbcURL, username, password)) {
            FileInputStream fileInputStream = new FileInputStream(excelFilePath);
            Workbook workbook = new XSSFWorkbook(fileInputStream);
            Sheet sheet = workbook.getSheetAt(0);

            int batchSize = 20;
            int currentBatchSize = 0;

            StringBuilder query = new StringBuilder();
            query.append("INSERT INTO ").append(tableName).append(" (coluna1, coluna2, coluna3) VALUES (?, ?, ?)");

            PreparedStatement statement = connection.prepareStatement(query.toString());

            for (Row row : sheet) {
                Cell cell1 = row.getCell(0);
                Cell cell2 = row.getCell(1);
                Cell cell3 = row.getCell(2);

                statement.setString(1, cell1.getStringCellValue());
                statement.setString(2, cell2.getStringCellValue());
                statement.setString(3, cell3.getStringCellValue());

                statement.addBatch();

                currentBatchSize++;

                if (currentBatchSize % batchSize == 0) {
                    statement.executeBatch();
                }
            }

            statement.executeBatch();

            workbook.close();
            fileInputStream.close();

            System.out.println("Os dados foram adicionados com sucesso no MySQL.");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}