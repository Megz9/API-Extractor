package Utils;

import java.util.ArrayList;
import java.util.Iterator;

public class ParentList {
	private String parentName;
	private String p_i_o;
	private String p_type;
	private String p_allowedValues;
	private String p_mandatory;
	
	
	
	private ArrayList<String> childList=new ArrayList<String>();
	private ArrayList<String> childI_o=new ArrayList<String>();
	private ArrayList<String> childType=new ArrayList<String>();
	private ArrayList<String> childAllowedValues=new ArrayList<String>();
	private ArrayList<String> childMandatory=new ArrayList<String>();
	
	private ArrayList<String> childData=new ArrayList<String>();
	public ParentList(String parentName, String i_o, String type, String allowedValues, String mandatory) {
		this.parentName = parentName;
		this.p_i_o = i_o;
		this.p_type = type;
		this.p_allowedValues = allowedValues;
		this.p_mandatory = mandatory;
	}
	
	
	public String getP_i_o() {
		return p_i_o;
	}


	public String getP_type() {
		return p_type;
	}


	public String getP_allowedValues() {
		return p_allowedValues;
	}


	public String getP_mandatory() {
		return p_mandatory;
	}


	public ArrayList<String> getChildList() {
		return childList;
	}


	public ArrayList<String> getChildI_o() {
		return childI_o;
	}


	public ArrayList<String> getChildType() {
		return childType;
	}


	public ArrayList<String> getChildAllowedValues() {
		return childAllowedValues;
	}


	public ArrayList<String> getChildMandatory() {
		return childMandatory;
	}


	public ParentList(String parentName,String child, String i_o, String type, String allowedValues, String mandatory) {
		this.parentName = parentName;
		this.addChild(child,i_o, type, allowedValues, mandatory);
		}
	
	public void setI_o(String i_o) {
		this.p_i_o = i_o;
	}
	public void setType(String type) {
		this.p_type = type;
	}
	public void setAllowedValues(String allowedValues) {
		this.p_allowedValues = allowedValues;
	}
	public void setMandatory(String mandatory) {
		this.p_mandatory = mandatory;
	}
	public void setChildList(ArrayList<String> childList) {
		this.childList = childList;
	}
	@Override
	public String toString() {
		return parentName+":\n"+" i/o: " + p_i_o + "\n type: " + p_type + "\n Allowed Values: " + p_allowedValues + "\n mandatory: "+ p_mandatory;
	}
	public ArrayList<String> StringchildData(){
		String data;
		for(int i=0;i<childList.size();i++) {
			data=childList.get(i) +":\n  i/o: " + childI_o.get(i) + "\n  type: " + childType.get(i) 
			+ "\n  allowed Values: " + childAllowedValues.get(i) + "\n  Mandatory: "+ childMandatory.get(i)+"\n";
			childData.add(data);
		}
		return childData;
	}
	
	
	public String getParentName() {
		return parentName;
	}
	public void setParentName(String parentName) {
		this.parentName = parentName;
	}
	public void addChild(String x) {
		childList.add(x);		
	}
	public void addChild(String x, String i_o, String type, String allowed, String man ) {
		childList.add(x);
		childI_o.add(i_o);
		childType.add(type);
		childAllowedValues.add(allowed);
		childMandatory.add(man);
		
	}
	public boolean isChildFound(String child) {
		for(String x: childList) {
			if(x.equals(child)) return true;	
		}
		return false;
	}
	
	
	public void removeChild(String x) {
		childList.remove(x);
	}
	public void getAllChildren() {
		for(int i=0; i<childList.size(); i++) {

			System.out.println(childList.get(i));
			System.out.println( " I/O: " + childI_o.get(i));
			System.out.println( " T: " + childType.get(i) );
			System.out.println( " A:  " + childAllowedValues.get(i));
			System.out.println( " M:  " +childMandatory.get(i) + "!");
			
		}
	}
	
	
	public void print() {
		System.out.println("Parent: "+this.getParentName() + ": " + this.toString());
		
		System.out.print(" ");
		getAllChildren();
		System.out.println("");
	}

	
}
