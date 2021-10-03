package com.liu.webdw.client.datawindow.model.syntax;

//
public class WebDW_Table_Retrieve_PBSelect_Where {
	public String exp1 = "";
	public String op = "";
	public String exp2 = "";
	public String logic = "";
	public WebDW_Table_Retrieve_PBSelect_Where() {

	}

	public WebDW_Table_Retrieve_PBSelect_Where Clone() {
		WebDW_Table_Retrieve_PBSelect_Where newOne = new WebDW_Table_Retrieve_PBSelect_Where();
		newOne.exp1 = exp1;
		newOne.op = op;
		newOne.exp2 = exp2;
		newOne.logic = logic;
		return newOne;
	}
}
