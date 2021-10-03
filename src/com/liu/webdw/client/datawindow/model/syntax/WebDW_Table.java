package com.liu.webdw.client.datawindow.model.syntax;

//
public class WebDW_Table {
	public WebDW_Table_Column Columns[] = new WebDW_Table_Column[101]; // '���100��
	public WebDW_Table_Retrieve retrieve = new WebDW_Table_Retrieve(); // '���¶���retrieve�����������һ���ṹ
	public String update = "";
	public String updatewhere = "";
	public String updatekeyinplace = "";


	public WebDW_Table() {
		for (int i = 0; i < 101; i++) {
			Columns[i] = new WebDW_Table_Column();
		}
	}

	public WebDW_Table Clone() {
		WebDW_Table newOne = new WebDW_Table();
		for (int i = 0; i < 101; i++) {
			newOne.Columns[i] = Columns[i].Clone();
		}
		newOne.retrieve = retrieve.Clone();
		newOne.update = update;
		newOne.updatewhere = updatewhere;
		newOne.updatekeyinplace = updatekeyinplace;
		return newOne;
	}
}
