package com.projects.provider.service;

import com.projects.provider.mapper.BudgetMapper;
import com.projects.provider.model.Budget;
import com.projects.provider.repository.BudgetRepository;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

@Service
public class BudgetService {

    private final BudgetRepository budgetRepository;
    private final BudgetMapper budgetMapper;

    private static final String PROVIDER_NOT_FOUND_EXCEPTION_MESSAGE = "Provider not found";
    private static final String PROVIDER_MISMATCH_EXCEPTION_MESSAGE = "Provider already exists";

    public BudgetService(BudgetRepository budgetRepository, BudgetMapper budgetMapper) {
        this.budgetRepository = budgetRepository;
        this.budgetMapper = budgetMapper;
    }

    public ResponseEntity<byte[]> generateExcelReports() throws IOException {
        var allBudgets = this.budgetRepository.findAll();
        int dataRowIndex = 1;

        HSSFWorkbook workbook = new HSSFWorkbook();
        HSSFSheet sheet = workbook.createSheet("Orçamento Info");
        HSSFRow row = sheet.createRow(0);

        row.createCell(0).setCellValue("Centro de custo");
        row.createCell(1).setCellValue("Descrição da demanda");

        for (Budget budgetEntityModel : allBudgets) {
            HSSFRow dataRow = sheet.createRow(dataRowIndex);
            dataRow.createCell(0).setCellValue(budgetEntityModel.getCostCenter());
            dataRow.createCell(1).setCellValue(budgetEntityModel.getRequestDescription());

            dataRowIndex++;
        }
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        workbook.write(stream);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.parseMediaType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet"));
        headers.setContentDispositionFormData("attachment", "orçamento.xlsx");

        workbook.close();
        stream.close();

        return ResponseEntity.ok()
                .headers(headers)
                .body(stream.toByteArray());
    }

    public void upload(MultipartFile file) throws IOException {
        Path tempDir = Files.createTempDirectory("");
        File tempFile = tempDir.resolve(file.getOriginalFilename()).toFile();
        Workbook workbook = WorkbookFactory.create(tempFile);
        Sheet sheet = workbook.getSheetAt(0);
        Stream<Row> rowStream = StreamSupport.stream(sheet.spliterator(), false);
        Row headerRow = rowStream.findFirst().get();
        //Stream<Cell> headerCells = StreamSupport.stream(row.spliterator(), false);

        rowStream.forEach(row -> {
            Stream<Cell> cellStream = StreamSupport.stream(row.spliterator(), false);

            List<String> cellVals = cellStream.map(cell -> {
                String cellVal = cell.getStringCellValue();
                return cellVal;
            }).collect(Collectors.toList());

            System.out.println(cellVals);
        });
    }

}
