package br.com.rodarte.testejava.utils;

import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.util.JRLoader;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class PdfReportBuilder {

    private final JasperReport jasperReport;
    private final Map<String, Object> parameters = new HashMap<>();
    private JRDataSource datasource;

    private PdfReportBuilder(String jasperFileName) {
        try {
            final var jasperFilePath = String.format("/reports/jasper/%s.jasper", jasperFileName);
            final var jasperStream = this.getClass().getResourceAsStream(jasperFilePath);
            this.jasperReport = (JasperReport) JRLoader.loadObject(jasperStream);
        } catch (JRException e) {
            throw new IllegalArgumentException(e);
        }
    }

    public static PdfReportBuilder newReport(String jasperFileName) {
        return new PdfReportBuilder(jasperFileName);
    }

    public PdfReportBuilder parameters(Map<String, Object> parameters) {
        this.parameters.putAll(parameters);
        return this;
    }

    public PdfReportBuilder addParameter(String name, Object value) {
        this.parameters.put(name, value);
        return this;
    }

    public PdfReportBuilder addImageParameter(String name, String imageName) {
        final var imageFilePath = String.format("/reports/images/%s", imageName);
        final var imageStream = this.getClass().getResourceAsStream(imageFilePath);
        this.parameters.put(name, imageStream);
        return this;
    }

    public PdfReportBuilder datasource(Collection<?> datasourceCollection) {
        this.datasource = new JRBeanCollectionDataSource(datasourceCollection);
        return this;
    }

    public byte[] build() {
        try (final var outputStream = new ByteArrayOutputStream()) {
            final var jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, datasource);
            JasperExportManager.exportReportToPdfStream(jasperPrint, outputStream);
            return outputStream.toByteArray();
        } catch (JRException | IOException e) {
            throw new IllegalArgumentException(e);
        }
    }

}