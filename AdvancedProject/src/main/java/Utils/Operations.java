package Utils;

import java.util.ArrayList;

import org.apache.poi.ss.usermodel.Workbook;


public class Operations {
	
	String excelPath; 
	
	
	
	public  ArrayList<RowData> arrOfRowObjects=new ArrayList<RowData>();  //contains new objects
	private  ArrayList<String> row=new ArrayList<String>();  //contains new objects
	 

	public ArrayList<ParentList> parentsList=new ArrayList<ParentList>();

	
	public ArrayList<ParentList> getParentsList() {
		return parentsList;
	}

	public  ArrayList<RowData> getArrOfRowObjects() {
		return arrOfRowObjects;
	}
	
	public void storeRowData(int rowNum, String excelPath){
		ExcelUtils rows=new ExcelUtils(excelPath);
		row =rows.getRowData(rowNum);
		RowData field= new RowData(row.get(0),row.get(1),row.get(2),row.get(3),row.get(4));
		arrOfRowObjects.add(field);
		//return arrOfRowObjects;
		}
	public void setArrList(ArrayList<RowData> hash, String excelPath) {/// using 2D ArrayList and classes
		String[] splitPath;
		int index = -1;
		for(RowData s : hash) {
			boolean parentFound=false;
			splitPath = s.splitFieldName();

			int splitPathLen=splitPath.length;
			if(s.getType().equalsIgnoreCase("string")) {  
				parentFound=false;
				if(splitPathLen == 1) {   //lone field
 
					for(ParentList p:parentsList) {
						if((p.getParentName().equals(splitPath[0])) ) { //if already stored  
							parentFound=true;
							break;
						}
						}if(parentFound==false) {
							parentsList.add(new ParentList(splitPath[0],s.getI_o(),s.getType(),s.getAllowedValues(),s.getMandatory()));
						
					}
				}else if(splitPathLen > 1) {
					for(int i =0; i<splitPathLen-1; i++){
						parentFound=false;
						for(ParentList p:parentsList) {
							if((p.getParentName().equals(splitPath[i])) ) { //see if is already stored  
								parentFound=true;
								index=parentsList.indexOf(p); //index in array list parentsList
								break;
								}
							}if(parentFound==true ) { //if it is stored 
								if(i!=(splitPathLen-1)) {
									if(parentsList.get(index).isChildFound(splitPath[i+1])==false)
										{
										parentsList.get(index).addChild(splitPath[i+1],s.getI_o(),s.getType(),s.getAllowedValues(),s.getMandatory());
											}

								}
							
							}else if(parentFound==false ) { //if it is not stored 
							if(i!=(splitPathLen-1)) {
								parentsList.add(new ParentList(splitPath[i],splitPath[i+1],s.getI_o(),s.getType(),s.getAllowedValues(),s.getMandatory()));
							}
						
						}
						
				
					}
				}
			}else if(!(s.getType().equalsIgnoreCase("string"))) {  

					parentFound=false;
					if(splitPathLen == 1) {   //lone field
						for(ParentList p:parentsList) {
							if((p.getParentName().equals(splitPath[0])) ) { //if already stored  
								parentFound=true;
								break;
							}
						}if(parentFound==false) {
							parentsList.add(new ParentList(splitPath[0],s.getI_o(),s.getType(),s.getAllowedValues(),s.getMandatory()));
							
						}
						
					}else if(splitPathLen > 1) {
						for(int i =0; i<splitPathLen; i++){
							parentFound=false;
							for(ParentList p:parentsList) {
								if((p.getParentName().equals(splitPath[i])) ) { //see if is already stored  
									parentFound=true;
									index=parentsList.indexOf(p); //index in array list parentsList
									break;
									}
								}if(parentFound==true ) { //if it is stored 
									if(i!=(splitPathLen-1)) {
										if(parentsList.get(index).isChildFound(splitPath[i+1])==false)
											{
											parentsList.get(index).addChild(splitPath[i+1],s.getI_o(),s.getType(),s.getAllowedValues(),s.getMandatory());
											
											}
									}
								}else if(parentFound==false ) { //if it is not stored 
									if(i!=(splitPathLen-1)) {
										parentsList.add(new ParentList(splitPath[i],splitPath[i+1],s.getI_o(),s.getType(),s.getAllowedValues(),s.getMandatory()));
									}else if(i==(splitPathLen-1))parentsList.add(new ParentList(splitPath[i],s.getI_o(),s.getType(),s.getAllowedValues(),s.getMandatory()));
								}
									}
								}
						}
					}
				}
	

}
