package com.liu.webdw.client.datawindow.model.syntax;

//'table define
public class WebDW_Table_Column {
	public String type = ""; // 
	public String update = ""; // yes/no
	public String updatewhereclause = ""; // 'yes/no
	public String key = ""; // '
	public String Name = ""; // '
	public String dbname = ""; // '
	public String values = ""; // '
	public String validations = ""; // '

	public WebDW_Table_Column Clone() {
		WebDW_Table_Column newOne = new WebDW_Table_Column();
		newOne.type = type; // 
		newOne.update = update; // 
		newOne.updatewhereclause = updatewhereclause; // yes/no
		newOne.key = key; // 
		newOne.Name = Name; //
		newOne.dbname = dbname; // 
		newOne.values = values; // 
		newOne.validations = validations; //
		return newOne;
	}
}
