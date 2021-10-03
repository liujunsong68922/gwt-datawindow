package com.liu.webdw.client.datawindow.model.syntax;

//
public class WebDW_Table_Retrieve_PBSelect_Order {
	public String Name = "";// 
	public String Asc = "";// yes/no

	public WebDW_Table_Retrieve_PBSelect_Order Clone() {

		WebDW_Table_Retrieve_PBSelect_Order newOne = new WebDW_Table_Retrieve_PBSelect_Order();
		newOne.Name = Name;
		newOne.Asc = Asc;
		return newOne;
	}
}
