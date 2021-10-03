package com.liu.webdw.client.datawindow.model.syntax;

//RadioButtons
public class WebDW_Column_RadioButtons  {
	public int Columns = 0; // 
	public WebDW_Column_RadioButtons Clone() {
		WebDW_Column_RadioButtons newOne = new WebDW_Column_RadioButtons();
		newOne.Columns = Columns;
		return newOne;
	}

}

