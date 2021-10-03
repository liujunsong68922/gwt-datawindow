package com.liu.webdw.client.datawindow.model.syntax;

//
public class WebDW_Table_Retrieve {
	public WebDW_Table_Retrive_PBSelect pbselect = new WebDW_Table_Retrive_PBSelect();

	public WebDW_Table_Retrieve Clone() {
		WebDW_Table_Retrieve newOne = new WebDW_Table_Retrieve();
		newOne.pbselect = pbselect.Clone();
		return newOne;
	}
}

