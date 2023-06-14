package br.com.rodarte.utils;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.util.CellRangeAddress;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.Objects;
import java.util.stream.IntStream;

public class XlsxWriter {

    public static final String CONTENT_TYPE = "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet";
    private static final short DEFAULT_ROW_HEIGHT = 400;

    private final Workbook workbook;
    private CellStyle currentCellStyle;
    private Sheet currentSheet;
    private Row currentRow;
    private int rowIndex;
    private int cellIndex;
    private boolean closed = false;

    private XlsxWriter() {
        this.workbook = new HSSFWorkbook();
    }

    public static XlsxWriter getInstance() {
        return new XlsxWriter();
    }

    public XlsxWriter createSheet(String sheetName) {
        this.currentSheet = workbook.createSheet(sheetName);
        this.currentSheet.setDefaultRowHeight(DEFAULT_ROW_HEIGHT);
        this.rowIndex = 0;
        this.cellIndex = 0;
        return this;
    }

    public CellStyle createCellStyle() {
        return this.workbook.createCellStyle();
    }

    public XlsxWriter createRow() {
        return createRow((CellStyle) null);
    }

    public XlsxWriter createRow(CellStyle style) {
        this.cellIndex = 0;
        this.currentCellStyle = style;
        this.currentRow = currentSheet.createRow(rowIndex++);
        return this;
    }

    public XlsxWriter createRow(CellStyleType cellStyleType) {

        CellStyle cellStyle = null;
        if (cellStyleType == CellStyleType.HEADER) {
            cellStyle = createCellStyle();
            cellStyle.setFillForegroundColor(IndexedColors.GREY_25_PERCENT.getIndex());
            cellStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
            cellStyle.setAlignment(HorizontalAlignment.CENTER);
            cellStyle.setVerticalAlignment(VerticalAlignment.CENTER);
        } else if (cellStyleType == CellStyleType.NORMAL) {
            cellStyle = createCellStyle();
            cellStyle.setAlignment(HorizontalAlignment.CENTER);
            cellStyle.setVerticalAlignment(VerticalAlignment.CENTER);
        }

        return createRow(cellStyle);
    }

    private Cell createCell() {
        Cell cell = currentRow.createCell(cellIndex++);
        if (Objects.nonNull(currentCellStyle)) {
            cell.setCellStyle(currentCellStyle);
        }
        return cell;
    }

    public void createEmptyCell() {
        this.createCell();
    }

    public void createEmptyCells(int quantity) {
        IntStream.range(0, quantity).forEach(x -> this.createEmptyCell());
    }

    public void createCell(double value) {
        this.createCell().setCellValue(value);
    }

    public void createCell(LocalDateTime value) {
        this.createCell().setCellValue(Date.from(value.atZone(ZoneId.systemDefault()).toInstant()));
    }

    public void createCell(LocalDate value) {
        this.createCell().setCellValue(Date.from(value.atStartOfDay(ZoneId.systemDefault()).toInstant()));
    }

    public void createCell(RichTextString value) {
        this.createCell().setCellValue(value);
    }

    public void createCell(String value) {
        this.createCell().setCellValue(value);
    }

    public void createCell(boolean value) {
        this.createCell().setCellValue(value);
    }

    private void autosizeColumns() {
        int numberOfSheets = workbook.getNumberOfSheets();
        for (int sheetIndex = 0; sheetIndex < numberOfSheets; sheetIndex++) {
            Sheet sheet = workbook.getSheetAt(sheetIndex);
            if (sheet.getPhysicalNumberOfRows() > 0) {
                Row row = sheet.getRow(sheet.getFirstRowNum());
                int lastCellNum = row.getLastCellNum();
                for (int currentCellIndex = 0; currentCellIndex < lastCellNum; currentCellIndex++) {
                    sheet.autoSizeColumn(currentCellIndex);
                }
            }
        }
    }
    public byte[] build() throws IOException {
        if (this.closed) {
            throw new IllegalStateException("Writer is already closed!");
        }
        this.autosizeColumns();
        try (ByteArrayOutputStream bos = new ByteArrayOutputStream()) {
            workbook.write(bos);
            workbook.close();
            this.closed = true;
            return bos.toByteArray();
        }
    }

    public void mergeCells(
            int startInclusiveRow, int endExclusiveRow, int startInclusiveCell, int endExclusiveCell
    ) {
        CellRangeAddress mergedCell =
                new CellRangeAddress(startInclusiveRow, endExclusiveRow, startInclusiveCell, endExclusiveCell);
        currentSheet.addMergedRegion(mergedCell);
    }

    public int getCurrentRowIndex() {
        return rowIndex;
    }

    public int getCurrentCellIndex() {
        return cellIndex;
    }

    public enum CellStyleType {
        HEADER, NORMAL
    }

}