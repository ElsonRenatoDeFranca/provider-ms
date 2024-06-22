package com.projects.provider.controller;

import com.projects.provider.dto.BudgetDTO;
import com.projects.provider.dto.ProviderDTO;
import com.projects.provider.exception.ResponseMessage;
import com.projects.provider.service.BudgetService;
import com.projects.provider.service.ExcelService;
import com.projects.provider.service.ProviderService;
import com.projects.provider.util.ExcelHelper;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;


@RestController
public class ExcelController {

    private final ExcelService excelService;
    private final ProviderService providerService;
    private final BudgetService budgetService;

    public ExcelController(ExcelService excelService, ProviderService providerService, BudgetService budgetService) {
        this.excelService = excelService;
        this.providerService = providerService;
        this.budgetService = budgetService;
    }

    @PostMapping(value = "providers/upload", produces = {APPLICATION_JSON_VALUE}, consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    @ResponseBody
    @Operation(summary = "upload all providers")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "upload all providers",
                    content = {@Content(mediaType = "application/json")}),
            @ApiResponse(responseCode = "503",
                    description = "The service is not available",
                    content = @Content)
    })
    public ResponseEntity<Object> uploadProvidersFile(@RequestPart("file") MultipartFile file) {
        var message = "";
        if (ExcelHelper.hasExcelFormat(file)) {
            try {
                excelService.saveProviders(file);
                message = "Uploaded the file providers successfully: " + file.getOriginalFilename();
                return ResponseEntity.status(HttpStatus.OK).body(new ResponseMessage(message));
            } catch (Exception e) {
                message = "Could not upload the providers file: " + file.getOriginalFilename() + "!";
                return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(new ResponseMessage(message));
            }
        }
        message = "Please upload an excel file!";
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ResponseMessage(message));
    }

    @PostMapping(value = "budget/upload", produces = {APPLICATION_JSON_VALUE}, consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    @ResponseBody
    @Operation(summary = "upload all providers")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "upload all providers",
                    content = {@Content(mediaType = "application/json")}),
            @ApiResponse(responseCode = "503",
                    description = "The service is not available",
                    content = @Content)
    })
    public ResponseEntity<Object> uploadBudgetFile(@RequestPart("file") MultipartFile file) {
        var message = "";
        if (ExcelHelper.hasExcelFormat(file)) {
            try {
                excelService.saveBudget(file);
                message = "Uploaded the budget file successfully: " + file.getOriginalFilename();
                return ResponseEntity.status(HttpStatus.OK).body(new ResponseMessage(message));
            } catch (Exception e) {
                message = "Could not upload the budget file: " + file.getOriginalFilename() + "!";
                return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(new ResponseMessage(message));
            }
        }
        message = "Please upload an excel file!";
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ResponseMessage(message));
    }

    @GetMapping(value = "providers/download", produces = {APPLICATION_JSON_VALUE})
    @ResponseBody
    @Operation(summary = "download all providers")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Download all providers",
                    content = {@Content(mediaType = "application/json")}),
            @ApiResponse(responseCode = "503",
                    description = "The service is not available",
                    content = @Content)
    })
    ResponseEntity<byte[]> downloadProviders() throws IOException {
        var allProviders = this.providerService.findAll();
        int dataRowIndex = 1;

        HSSFWorkbook workbook = new HSSFWorkbook();
        HSSFSheet sheet = workbook.createSheet("Providers Info");
        HSSFRow row = sheet.createRow(0);

        row.createCell(0).setCellValue("Provider-ID");
        row.createCell(1).setCellValue("Provider-Name");

        for (ProviderDTO providerDTO : allProviders) {
            HSSFRow dataRow = sheet.createRow(dataRowIndex);
            dataRow.createCell(0).setCellValue(providerDTO.getProviderId());
            dataRow.createCell(1).setCellValue(providerDTO.getProviderName());
            dataRowIndex++;
        }
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        workbook.write(stream);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.parseMediaType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet"));
        headers.setContentDispositionFormData("attachment", "sample.xlsx");

        workbook.close();
        stream.close();

        return ResponseEntity.ok()
                .headers(headers)
                .body(stream.toByteArray());
    }


    @GetMapping(value = "budget/download", produces = {APPLICATION_JSON_VALUE})
    @ResponseBody
    @Operation(summary = "download budget")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Download budgets",
                    content = {@Content(mediaType = "application/json")}),
            @ApiResponse(responseCode = "503",
                    description = "The service is not available",
                    content = @Content)
    })
    ResponseEntity<byte[]> downloadBudgets() throws IOException {
        var allBudgets = this.budgetService.findAll();
        int dataRowIndex = 1;

        HSSFWorkbook workbook = new HSSFWorkbook();
        HSSFSheet sheet = workbook.createSheet("Budget-info");
        HSSFRow row = sheet.createRow(0);

        row.createCell(0).setCellValue("budgetId");
        row.createCell(1).setCellValue("costCenter");

        for (BudgetDTO budgetDTO : allBudgets) {
            HSSFRow dataRow = sheet.createRow(dataRowIndex);
            dataRow.createCell(0).setCellValue(budgetDTO.getBudgetId());
            dataRow.createCell(1).setCellValue(budgetDTO.getCostCenter());
            dataRowIndex++;
        }
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        workbook.write(stream);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.parseMediaType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet"));
        headers.setContentDispositionFormData("attachment", "Budget-Sample-A.xlsx");

        workbook.close();
        stream.close();

        return ResponseEntity.ok()
                .headers(headers)
                .body(stream.toByteArray());
    }

}