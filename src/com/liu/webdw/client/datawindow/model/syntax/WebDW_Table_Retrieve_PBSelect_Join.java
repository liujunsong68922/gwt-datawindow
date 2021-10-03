package com.liu.webdw.client.datawindow.model.syntax;

//'
public class WebDW_Table_Retrieve_PBSelect_Join {
	public String join_left = "";
	public String join_op = "";
	public String join_right = "";

	public WebDW_Table_Retrieve_PBSelect_Join() {

	}

	public WebDW_Table_Retrieve_PBSelect_Join Clone() {
		WebDW_Table_Retrieve_PBSelect_Join newOne = new WebDW_Table_Retrieve_PBSelect_Join();
		newOne.join_left = join_left;
		newOne.join_op = join_op;
		newOne.join_right = join_right;
		return newOne;
	}
}
