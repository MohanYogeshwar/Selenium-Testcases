package io.testing.tables.datatables;



import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.function.BiConsumer;



import org.apache.commons.collections4.map.HashedMap;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Row.MissingCellPolicy;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.google.gson.Gson;

import io.cucumber.core.logging.Logger;
import io.cucumber.core.logging.LoggerFactory;



public class ExcelDataReader implements IDataReader {
    private final ExcelConfiguration config;
    private Logger logger = LoggerFactory.getLogger(ExcelDataReader.class);



   public ExcelDataReader(ExcelConfiguration config) {
        this.config = config;
    }
    // 1. To get the instance of work book



   private XSSFWorkbook getWorkBook() throws InvalidFormatException, IOException {
        return new XSSFWorkbook(new File(config.getFileLocation()));
    }



   // 2. Get the sheet using the work book object



   private XSSFSheet getSheet(XSSFWorkbook workBook) {
        return workBook.getSheet(config.getSheetName());
    }



   // 3. To get the header from the excel file



   private List<String> getHeaders(XSSFSheet sheet) {
        List<String> headers = new ArrayList<String>();
        XSSFRow row = sheet.getRow(0);
        row.forEach((cell) -> {
            headers.add(cell.getStringCellValue());
        });
        return Collections.unmodifiableList(headers);
    }



   /**
     *
     * every row --> Map(header, column value)
     * first_name    last_name    email    gender    city
     * Shurlocke    Chapleo    schapleo0@usa.gov    Male    Watuweri --> Map(first_name=Shurlocke, last_name=Chapleo, email=schapleo0@usa.gov...)
     * Hali    Allery    hallery1@blogtalkradio.com    Female    KokemÃ¤ki --> Map(first_name=Hali, last_name=Allery, email=hallery1@blogtalkradio.com...)
     *
     * List<Map<String, String>> data
     */
    public List<Map<String, String>> getAllRows() {
        List<Map<String, String>> data = new ArrayList<Map<String, String>>();
       try (XSSFWorkbook workBook = getWorkBook()) {
    	   XSSFSheet sheet = getSheet(workBook);
    	    data = getData(sheet);
            
        } catch (Exception e) {
            logger.error(e, () -> {
                return String.format("Not able to read the excel %s from location %s", config.getFileName(),
                        config.getFileLocation());
            });
            return Collections.emptyList();
        }
        return Collections.unmodifiableList(data);
   }

   private List<Map<String, String>> getData(XSSFSheet sheet) {
        List<Map<String, String>> data = new ArrayList<Map<String, String>>();
        List<String> headers = getHeaders(sheet);
        //int ColumsCount=headers.size();
        //System.out.println("No of columns "+ headers.size()); 	

        //System.out.println("No of Rows " + sheet.getLastRowNum());
        //System.out.println("Get Data is called");
       for (int i = 1; i <= sheet.getLastRowNum(); i++) {
    	   	//System.out.println("Row "+i);
            //Map<String, String> rowMap = new HashedMap<String, String>();
    	   	HashMap<String, String> rowMap = new HashMap<String, String>();
            XSSFRow row = sheet.getRow(i);
            String ColValues[]=rowToString(row,headers.size()-1);
            //Row map - New
            for(int hi=0;hi<ColValues.length;hi++) {
            	//System.out.println("header " + headers.get(hi) + " value " +ColValues[hi]);
            	rowMap.put(headers.get(hi), ColValues[hi]);
            }
            data.add(rowMap);
        }
       return Collections.unmodifiableList(data);
     }
    
//        for (int i = 1; i <= sheet.getLastRowNum(); i++) {
//        	Map<String, String> rowMap = new HashedMap<String, String>(); 
//        	XSSFRow row = sheet.getRow(i);
//        
//        	forEachWithCounter(row, (index, cell) -> {
//  	  
//        	System.out.println("COl index " + cell.getColumnIndex() + "index "+ index);
//  		   switch (cell.getCellType())
//      		{
//      		case NUMERIC : 
//      			rowMap.put(headers.get(index), cell.getNumericCellValue()+"");break;
//      		case STRING : 
//      			rowMap.put(headers.get(index), cell.getStringCellValue());break;
//      		default : break;
//  		}
//                  
//  });data.add(rowMap);
//       }
  
   
//Function to handle empty
   public String[] rowToString(Row row,int ColCnt)
   {
       Iterator<Cell> cells = row.cellIterator() ;
       
       String[] data1 = new String[row.getLastCellNum()] ;
       int colCount=ColCnt;
       int previousCell = 0 ;
       Cell cell = cells.next() ;
       int currentCell = cell.getColumnIndex();
       while (true)
       {
   	        if (previousCell == currentCell)
   	        {
   	            switch (cell.getCellType()) {
   	                case NUMERIC:
   	                    data1[previousCell] = cell.getNumericCellValue()+"" ;
   	                    break;
   	                case STRING:
   	                    data1[previousCell] = cell.getStringCellValue() ;
   	                    break;

   	                    case BLANK:
   	                        data1[previousCell] = "";
   	                        break;
   	                    default : break;
   	
   	            }//switch
   		            if(cells.hasNext()){
   		                cell = cells.next() ;
   		                currentCell = cell.getColumnIndex();
   		            } else {
   		                break ;
   		            }
   	
   	        } //main if
   	        else {
   	            data1[previousCell] = "";
   	        }
           previousCell++ ;
           
       	}//While
       
       	String temp[]=new String[colCount+1];
       	
       
       	for(int i=0;i<data1.length;i++)temp[i]=data1[i];
       	int incr=data1.length-1;
      		int RemCount=colCount-(previousCell);
     		
   		if (RemCount>0)
   		{
   			
   			for(int i=0;i<RemCount;i++)
   			{
   				incr++;
   				temp[incr]="";
   				
   			}
   		}
   		return temp ;
   }

   
   private Map<String, String> getData(XSSFSheet sheet, int rowIndex) {
        List<String> headers = getHeaders(sheet);
        Map<String, String> rowMap = new HashedMap<String, String>();
        XSSFRow row = sheet.getRow(rowIndex);
        forEachWithCounter(row, (index, cell) -> {
        	//System.out.println(cell.getStringCellValue());
            rowMap.put(headers.get(index), cell.getStringCellValue());
        });
       forEachWithCounter(row, (i,j) -> {
            
        });
        
        return Collections.unmodifiableMap(rowMap);
    }



   public Map<String, String> getASingleRow() {
        Map<String, String> data = new HashedMap<String, String>();
       try (XSSFWorkbook workBook = getWorkBook()) {
            XSSFSheet sheet = getSheet(workBook);
            data = getData(sheet, config.getIndex());
        } catch (Exception e) {
            logger.error(e, () -> {
                return String.format("Not able to read the excel %s from location %s", config.getFileName(),
                        config.getFileLocation());
            });
            return Collections.emptyMap();
        }
        return Collections.unmodifiableMap(data);
    }

   private void forEachWithCounter(Iterable<Cell> source, BiConsumer<Integer, Cell> biConsumer) {
        int i = 0;
        for (Cell cell : source) {
        	biConsumer.accept(i, cell);
            i++;
        }
    }
//   private void forEachWithCounter(Iterable<Cell> source, BiConsumer<Integer, Cell> biConsumer) {
//       int i = 0;
//       for (Cell cell : source) {
//       	biConsumer.accept(i, cell);
//           i++;
//       }
//   }



}

