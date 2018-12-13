import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class CSVFileBuilder {

    List<LangStruct> langStructs = new ArrayList<>();

    public void addLangStructs(LangStruct langStruct) {
        langStructs.add(langStruct);
    }

    public void build(boolean showUntranslatable) {
        XSSFWorkbook workbook = new XSSFWorkbook();
        XSSFSheet sheet = workbook.createSheet("Strings");

//        Object[][] datatypes = {
//                {"Datatype", "Type", "Size(in bytes)"},
//                {"int", "Primitive", 2},
//                {"float", "Primitive", 4},
//                {"double", "Primitive", 8},
//                {"char", "Primitive", 1},
//                {"String", "Non-Primitive", "No fixed size"}
//        };

        int rowNum = 0;
        System.out.println("Creating excel");

        Row row = sheet.createRow(rowNum++);
        int colNum = 0;
        Cell cell = row.createCell(colNum++);
        cell.setCellValue("Key");
        for (LangStruct langStruct : langStructs) {
            cell = row.createCell(colNum++);
            cell.setCellValue(langStruct.getLang());
        }

        LangStruct primaryStruct = langStructs.get(0);
        for (Map.Entry<String, String> entry : primaryStruct.getStringMap().entrySet()) {
            String key = entry.getKey();

            if(!showUntranslatable) {
                Boolean translatable = primaryStruct.getTranslatableMap().get(key);
                if (translatable != null && !translatable)
                    continue;
            }

            row = sheet.createRow(rowNum++);
            colNum = 0;

            cell = row.createCell(colNum++);
            cell.setCellValue(key);

            cell = row.createCell(colNum++);
            cell.setCellValue(entry.getValue());

            for (int i = 1; i < langStructs.size(); i++) {
                LangStruct langStruct = langStructs.get(i);

                cell = row.createCell(colNum++);

                String value = langStruct.getStringMap().get(key);
                if (value != null)
                    cell.setCellValue(value);

            }
        }


        try {
            FileOutputStream outputStream = new FileOutputStream("strings.xlsx");
            workbook.write(outputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println("Done");
    }
}
