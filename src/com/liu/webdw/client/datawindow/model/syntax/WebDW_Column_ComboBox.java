package com.liu.webdw.client.datawindow.model.syntax;

//'ComboBox
public class WebDW_Column_ComboBox {
	public int limit = 0; // 
	public String allowedit = ""; // yes /no
	public String case1 = ""; //case1
	public String useasborder = ""; // yes/no

	public WebDW_Column_ComboBox Clone() {

		WebDW_Column_ComboBox newOne = new WebDW_Column_ComboBox();
		newOne.limit = limit; // 
		newOne.allowedit = allowedit; // yes /no
		newOne.case1 = case1; // case1
		newOne.useasborder = useasborder; // '�Ƿ���ʾ��ͷ yes/no
		return newOne;
	}


}
