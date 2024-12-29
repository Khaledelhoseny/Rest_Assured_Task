package dataProvider;

import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.testng.annotations.DataProvider;

import java.io.File;
import java.io.FileInputStream;
import java.util.Arrays;

public class ExcelDataSupllier {

    @DataProvider(name="loginData")
    public Object[][]  getData() throws Exception {
        File excelFile = new File("D:\\RestAssuredTask\\data\\Untitled spreadsheet (1).xlsx");
        FileInputStream fis = new FileInputStream(excelFile);
        XSSFWorkbook workbook = new XSSFWorkbook(fis);
        XSSFSheet sheet = workbook.getSheet("Sheet1");
        int noOfRows = sheet.getPhysicalNumberOfRows();
        int noOfColumns = sheet.getRow(0).getLastCellNum();

        Object[][] data = new String[noOfRows-1][noOfColumns];
        for (int i = 0; i < noOfRows-1; i++) {
            for (int j = 0; j < noOfColumns; j++) {
                DataFormatter df = new DataFormatter();
                data[i][j] =  df.formatCellValue(sheet.getRow(i+1).getCell(j));
            }
        }
        workbook.close();
        fis.close();

        for (Object[] dataArr : data) {
            System.out.println(Arrays.toString(dataArr));
        }
        return data;
    }

}