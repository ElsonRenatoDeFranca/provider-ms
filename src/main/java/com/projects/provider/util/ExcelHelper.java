package com.projects.provider.util;

import com.projects.provider.model.Budget;
import com.projects.provider.model.Provider;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class ExcelHelper {
    public static String TYPE = "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet";
    private static String PROVIDERS_SHEET = "Providers Info";
    private static String BUDGET_SHEET = "Budget Info";

    public static boolean hasExcelFormat(MultipartFile file) {
        return TYPE.equals(file.getContentType());
    }

    public static List<Provider> excelToProvider(InputStream is) {
        try {
            Workbook workbook = new HSSFWorkbook(is);
            Sheet sheet = workbook.getSheet(PROVIDERS_SHEET);
            Iterator<Row> rows = sheet.iterator();
            List<Provider> providers = new ArrayList<Provider>();
            int rowNumber = 0;
            while (rows.hasNext()) {
                Row currentRow = rows.next();
                // skip header
                if (rowNumber == 0) {
                    rowNumber++;
                    continue;
                }
                Iterator<Cell> cellsInRow = currentRow.iterator();
                Provider provider = new Provider();
                int cellIdx = 0;
                while (cellsInRow.hasNext()) {
                    Cell currentCell = cellsInRow.next();
                    switch (cellIdx) {
                        case 0:
                            provider.setProviderId(currentCell.getStringCellValue());
                            break;
                        case 1:
                            provider.setProviderName(currentCell.getStringCellValue());
                            break;
                        default:
                            break;
                    }
                    cellIdx++;
                }
                providers.add(provider);
            }
            workbook.close();
            return providers;
        } catch (IOException e) {
            throw new RuntimeException("fail to parse Excel file: " + e.getMessage());
        }
    }

    public static List<Budget> excelToBudget(InputStream is) {
        try {
            Workbook workbook = new HSSFWorkbook(is);
            Sheet sheet = workbook.getSheet(BUDGET_SHEET);
            Iterator<Row> rows = sheet.iterator();
            List<Budget> providers = new ArrayList<Budget>();
            int rowNumber = 0;
            while (rows.hasNext()) {
                Row currentRow = rows.next();
                // skip header
                if (rowNumber == 0) {
                    rowNumber++;
                    continue;
                }
                Iterator<Cell> cellsInRow = currentRow.iterator();
                Budget budget = new Budget();
                int cellIdx = 0;
                while (cellsInRow.hasNext()) {
                    Cell currentCell = cellsInRow.next();
                    switch (cellIdx) {
                        case 0:
                            budget.setBudgetId(currentCell.getStringCellValue());
                            break;
                        case 1:
                            budget.setCostCenter(currentCell.getStringCellValue());
                            break;
                        default:
                            break;
                    }
                    cellIdx++;
                }
                providers.add(budget);
            }
            workbook.close();
            return providers;
        } catch (IOException e) {
            throw new RuntimeException("fail to parse Excel file: " + e.getMessage());
        }
    }
}
