package com.ese.vt.slhydra;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;


public class XLSReader {
//	String xlsPath = "Z:\\LoadJobSLtoHydra\\";		//VT Hydra Server
//	String xlsPath = "/Users/hs5tff/Documents/SL_Hydra/";	//K_Nong Mac Book
//	String xlsPath = "C:\\Users\\hydadm\\Documents\\SL_Hydra\\";		//K_nong Mac Book windows 10 parallels
//	String xlsFile = "Export_to_Hydra.xls";
//    String datPath = "D:\\HYDRA1\\1\\inf_int\\interf\\";	//VT Hydra Server
//	String datPath = "/Users/hs5tff/Documents/SL_Hydra/";			//K_Nong Mac Book
//	String datPath = "C:\\Users\\hydadm\\Documents\\SL_Hydra\\";				//K_nong Mac Book windows 10 parallels
//    String datFile = "HY72PPS.dat";	
    String line = "";
    String xlsFile = "";
    String datFile = "";
    
    int sec = 0;
    
	public XLSReader(String xlsFile,String datFile) throws IOException {
		this.xlsFile = xlsFile;
		this.datFile = datFile;
	}

public void readData(){
	 DataFormatter dataFormatter = new DataFormatter();

     SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd");
     Date date = new Date();
     String nowDate = format.format(date);

     try  {
//     	Workbook workbook = WorkbookFactory.create(new File("/Users/hs5tff/Documents/SL_Hydra/Export_to_Hydra.xls"));
    	 xlsFile = xlsFile+"Reporting_"+nowDate+"-Job.xls";
         System.out.println(xlsFile);

      	Workbook workbook = WorkbookFactory.create(new File(xlsFile));
     	Sheet datatypeSheet = workbook.getSheetAt(0);
         Iterator<Row> iterator = datatypeSheet.iterator();
         
     	//BufferedWriter oos = new BufferedWriter(new FileWriter("/Users/hs5tff/Documents/SL_Hydra/HY72PPS.dat"));
//         Writer out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream("/Users/hs5tff/Documents/SL_Hydra/HY72PPS.dat"), "UTF8"));
         Writer out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(datFile), "UTF8"));
     	int i = 0;
     	while (iterator.hasNext()) {
     		i++;
         	String dataLine ="";
         	System.out.print(i+" ");
             Row currentRow = iterator.next();
             Iterator<Cell> cellIterator = currentRow.iterator();
             int col = 0;
             
             while (cellIterator.hasNext()) {
             	Cell cell = cellIterator.next();
                 String cellValue = dataFormatter.formatCellValue(cell);
                 dataLine += cellValue+"";
                 System.out.print(dataLine);
                 if(i == 2 && col == 19) {
                	 System.out.println();
                	 System.out.println("Actual Perimeter "+cellValue);
                     out.append(cellValue).append("\r\n");
                 }
                 col++;
                 
             }
             System.out.println();

 //            oos.write(dataLine);
 //            oos.newLine();
 //            out.append(dataLine).append("\r\n");

     	}
        out.flush();
		out.close();
     } catch (Exception e) {
         e.printStackTrace();
     }
     
	}
	
}
