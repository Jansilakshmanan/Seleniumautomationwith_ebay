package com.ebaysearch.utilitylayer;

import com.ebaysearch.baselayer.Base;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.JavascriptExecutor;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Random;

public class Testutil extends Base {
    String testdata_path="C:\\Users\\user\\Desktop\\backup 12112019\\jansi_javafiles_2\\selenium_assignment\\src\\main\\java\\com\\ebaysearch\\Testdata\\Testdata_ebay.xlsx";


    public void scrolldownwithjs(int X,int Y)
    {
         String jsscript=String.format("window.scrollBy(%d,%d)",X,Y);
        JavascriptExecutor js=(JavascriptExecutor)driver;
        js.executeScript(jsscript);
    }
    public Object[][] readexceldata() throws IOException {
        FileInputStream ip=new FileInputStream(testdata_path);
        Workbook book=new XSSFWorkbook(ip);
        Sheet sheet=book.getSheetAt(0);
        int rowno=sheet.getLastRowNum();
        int colno=sheet.getRow(0).getLastCellNum();
        Object[][] data=new Object[rowno][colno];
        for(int i=0;i<rowno;i++)
        {
            for(int j=0;j<colno;j++)
            {
                data[i][j]=sheet.getRow(i+1).getCell(j).toString();

            }
        }

        return data;
    }
    public int randomnogenerator(int upperbound)
    {
        Random ran=new Random();
        int ran_no=ran.nextInt(upperbound);

        return ran_no;
    }
}
