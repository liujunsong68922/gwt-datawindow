package com.liu.webdw.client.datawindow.model.syntax;

//
public class WebDW_Table_Retrive_PBSelect {
	public String version = "";
	public String table[] = new String[11]; // 
	public String column[] = new String[101]; //
	public WebDW_Table_Retrieve_PBSelect_Join join[] = new WebDW_Table_Retrieve_PBSelect_Join[11];// 
	public WebDW_Table_Retrieve_PBSelect_Where where[] = new WebDW_Table_Retrieve_PBSelect_Where[11]; // 
	public WebDW_Table_Retrieve_PBSelect_Order order[] = new WebDW_Table_Retrieve_PBSelect_Order[11]; //

	public WebDW_Table_Retrive_PBSelect() {
		int i;
		for (i = 0; i < 11; i++) {
			table[i] = "";
		}
		for (i = 0; i < 101; i++) {
			column[i] = "";
		}
		for (i = 0; i < 11; i++) {
			join[i] = new WebDW_Table_Retrieve_PBSelect_Join();
		}
		for (i = 0; i < 11; i++) {
			where[i] = new WebDW_Table_Retrieve_PBSelect_Where();
		}
		for (i = 0; i < 11; i++) {
			order[i] = new WebDW_Table_Retrieve_PBSelect_Order();
		}

	}

	public WebDW_Table_Retrive_PBSelect Clone() {
		WebDW_Table_Retrive_PBSelect newOne = new WebDW_Table_Retrive_PBSelect();
		int i;
		for (i = 0; i < 11; i++) {
			newOne.table[i] = table[i];
		}
		for (i = 0; i < 101; i++) {
			newOne.column[i] = column[i];
		}
		for (i = 0; i < 11; i++) {
			newOne.join[i] = join[i].Clone();
		}
		for (i = 0; i < 11; i++) {
			newOne.where[i] = where[i].Clone();
		}
		for (i = 0; i < 11; i++) {
			newOne.order[i] = order[i].Clone();
		}

		return newOne;
	}
}
