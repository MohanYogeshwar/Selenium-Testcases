package io.testing.tables.datatables;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;

import org.apache.poi.common.usermodel.Hyperlink;
import org.apache.poi.common.usermodel.HyperlinkType;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.CreationHelper;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFHyperlink;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class HyperLink {
   public static void main(String[] args) throws Exception {
      XSSFWorkbook workbook = new XSSFWorkbook(); 
      XSSFSheet spreadsheet = workbook.createSheet("Hyperlinks");
      XSSFCell cell;
      CreationHelper createHelper = workbook.getCreationHelper();
      XSSFCellStyle hlinkstyle = workbook.createCellStyle();
      XSSFFont hlinkfont = workbook.createFont();
      hlinkfont.setUnderline(XSSFFont.U_SINGLE);
      hlinkfont.setColor(IndexedColors.BLUE.index);
      hlinkstyle.setFont(hlinkfont);

      //URL Link
      cell = spreadsheet.createRow(1).createCell((short) 1);
      cell.setCellValue("URL Link");
      XSSFHyperlink link = (XSSFHyperlink)createHelper.createHyperlink(HyperlinkType.URL);
//      link.setAddress("http://www.tutorialspoint.com/");
//      cell.setHyperlink((XSSFHyperlink) link);
//      cell.setCellStyle(hlinkstyle);

      //Hyperlink to a file in the current directory
      cell = spreadsheet.createRow(2).createCell((short) 1);
      cell.setCellValue("File Link");
      link = (XSSFHyperlink)createHelper.createHyperlink(HyperlinkType.FILE);
      link.setAddress("img1.png");
      cell.setHyperlink(link);
      cell.setCellStyle(hlinkstyle);


   
      
      FileOutputStream out = new FileOutputStream(new File("hyperlink.xlsx"));
      workbook.write(out);
      out.close();
      System.out.println("hyperlink.xlsx written successfully");
   }
}

