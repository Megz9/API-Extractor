package Utils;

public class RowData {
	private String i_o;
	private String fieldName;
	private String type;
	private String allowedValues;
	private String mandatory;
	
	
	
	
	public RowData(String i_o,String fieldName,String type, String allowedValues, String mandatory)  {
		this.i_o=i_o;
		this.type=type;
		this.fieldName=fieldName;
		try{
			
			this.allowedValues=allowedValues;
			if(this.allowedValues == "") throw new BlankCellException("This is a blank cell, which allows all types.");
				
		}
		catch(BlankCellException ex) {
			
			this.allowedValues="ALL";
		}
		this.mandatory=mandatory;
	}
	@Override
	public String toString() {
		return "RowData [i_o=" + i_o +  ", type=" + type + ", allowedValues="
				+ allowedValues + ", mandatory=" + mandatory +  "]";
	}

	public String getI_o() {
		return i_o;
	}
	public String getType() {
		return type;
	}
	public String getAllowedValues() {
		return allowedValues;
	}
	public String getMandatory() {
		return mandatory;
	}
	public String getFieldName() {
		return fieldName;
	}
	public String[] splitFieldName() {
		
		this.fieldName = (this.fieldName.charAt(0)=='/') ? this.fieldName.replaceFirst("/","") : this.fieldName;
		
		return (this.fieldName.split("/"));
		
	}
	
	
}
