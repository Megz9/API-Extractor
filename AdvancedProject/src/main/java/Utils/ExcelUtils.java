package Utils;

import java.io.IOException;
import java.util.ArrayList;

import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelUtils {
	private static XSSFWorkbook workbook;
	private static XSSFSheet sheet;
	
	public ExcelUtils(String excelPath, int sheetnum)  {
		try{
			 workbook =new XSSFWorkbook(excelPath);
			 sheet = workbook.getSheetAt(sheetnum);
		}catch (Exception ex) {
			System.out.println(ex.getCause());
			System.out.println(ex.getMessage());
			ex.printStackTrace();
		}
	}
	public ExcelUtils(String excelPath) {
		try{
			 workbook =new XSSFWorkbook(excelPath);
			 sheet = workbook.getSheetAt(0);
		}catch (Exception ex) {
			System.out.println(ex.getCause());
			System.out.println(ex.getMessage());
			ex.printStackTrace();
		}
	}
	public ExcelUtils(String excelPath, String sheetName) {
		try{
			 workbook =new XSSFWorkbook(excelPath);
			 sheet = workbook.getSheet(sheetName);
		}catch (Exception ex) {
			System.out.println(ex.getCause());
			System.out.println(ex.getMessage());
			ex.printStackTrace();
		}
	}
	public void closeWorkbook() {
		try {
			workbook.close();
		} catch (IOException ex) {
			// TODO Auto-generated catch block
			System.out.println(ex.getCause());
			System.out.println(ex.getMessage());
			ex.printStackTrace();
		}
	}
	public Object getCellData(int rowIndex, int colIndex) { 
		
		try{
			DataFormatter formatter = new DataFormatter();
			Object cellVal =formatter.formatCellValue(sheet.getRow(rowIndex).getCell(colIndex));
			return cellVal;
		}catch(NullPointerException e) {
			
			
			return "null";
		}
	}
	public int getRowCount() {  //get number of rows
		
		int rowCount =sheet.getPhysicalNumberOfRows(); 
		
		return rowCount;
	}
	public void printArrayList(ArrayList<String> list) {  //print all elements of array list
		for(String j:list) {
			System.out.println(j);
		}

	}
	public ArrayList<String> getColData(int colIndex) {
		int x=0;
		ArrayList <String> list =new ArrayList<String>();
		for(int i=0;i<this.getRowCount();i++) {
	
			if(((String)this.getCellData(i-1, 1)).equals("Field Name")) {
				while((String)this.getCellData(i+x, 0) != "null") {
					list.add((String)this.getCellData(i+x, colIndex));
					x++;
				}
				//break;		
			}
		}
	return list;
	}
	public ArrayList<String> getRowData(int rowIndex) {
		
		ArrayList <String> list =new ArrayList<String>();
		for(int i=0;i<5;i++) {
				list.add((String)this.getCellData(rowIndex, i));
		}
	return list;
	}
	
	public int  getTableIndexStartWithAPi(String Api) { 
		String restOPMapping="rest operation mapping";
		int tableStartingIndex=0;
		for(int i=0;i<getRowCount();i++) {
			if(getCellData(i, 0).toString().toLowerCase().contains(restOPMapping)) {
				if( getCellData(i, 0).toString().contains(Api) ) {
				while((String)this.getCellData(i+tableStartingIndex, 0) != null) {
					tableStartingIndex++;
					if(getCellData((i+tableStartingIndex),0).toString().toLowerCase().equals("i/o")) {
						
						return (++tableStartingIndex+i);
				}
				}
			}	
			}
		}return -1;  
	}
	////returning array
	public ArrayList <Integer>  getTableIndexStarts() {
		
		String i_o="i/o";
		ArrayList<Integer> list =new ArrayList<Integer>();

		for(int i=0;i<getRowCount();i++) {
			if(getCellData(i, 0).toString().toLowerCase().contains(i_o)) {
					if(getCellData((i),0).toString().toLowerCase().equals("i/o")) {
						{
							list.add(++i);
							
						}

					}
			}
		}return list;  
	}
	
	public int numOfTables() {
		ArrayList<Integer> list=this.getTableIndexStarts();
		return (list.size());
	}

	

}
