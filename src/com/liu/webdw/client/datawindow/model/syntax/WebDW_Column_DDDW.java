package com.liu.webdw.client.datawindow.model.syntax;

public class WebDW_Column_DDDW  {
	public String Name = ""; // '
	public String DisplayColumn = ""; //
	public String DataColumn = ""; // '
	public int PercentWidth = 0; // 
	public int Lines = 0;
	public int limit = 0;
	public String allowedit = "";
	public String useasborder = "";
	public String case1 = "";
	public String vscrollbar = ""; // 

	public WebDW_Column_DDDW Clone() {

		WebDW_Column_DDDW newOne = new WebDW_Column_DDDW();
		newOne.Name = Name; // 
		newOne.DisplayColumn = DisplayColumn; // 
		newOne.DataColumn = DataColumn; // 
		newOne.PercentWidth = PercentWidth; //
		newOne.Lines = Lines;
		newOne.limit = limit;
		newOne.allowedit = allowedit;
		newOne.useasborder = useasborder;
		newOne.case1 = case1;
		newOne.vscrollbar = vscrollbar; // 

		return newOne;
	}


}
