package com.liu.webdw.client.datawindow.model.syntax;

import com.google.gwt.xml.client.Element;
import com.liu.webdw.client.datawindow.common.GolbalENV;
import com.google.gwt.xml.client.Document;

/**
 * WebDW Syntax Define file.
 * 
 * @author admin
 * 
 */

public class WebDWSyntaxVO {
	public void ReadMe() {
		System.out.println("WebDW Syntax Class, accoring to PB7.0 DwSyntax");
		System.out.println(GolbalENV.JWebDWInfo);
	}

	public WebDW_DataWindow datawindow = new WebDW_DataWindow();
	public WebDW_Header header = new WebDW_Header();
	public WebDW_Summary summary = new WebDW_Summary();
	public WebDW_Footer footer = new WebDW_Footer();
	public WebDW_Detail detail = new WebDW_Detail();
	public WebDW_Table table = new WebDW_Table();
	public WebDW_Text text[] = new WebDW_Text[101];
	public WebDW_Column column[] = new WebDW_Column[101];
	public WebDW_Line lineinfo[] = new WebDW_Line[101];
	public String column_dddw_syntax[] = new String[101];
	public String column_dddw_data[] = new String[101];
	public String SelectSQL = "";

	public WebDWSyntaxVO() {
		int i = 0;
		for (i = 0; i < 101; i++) {
			text[i] = new WebDW_Text();
		}
		for (i = 0; i < 101; i++) {
			column[i] = new WebDW_Column();
		}
		for (i = 0; i < 101; i++) {
			lineinfo[i] = new WebDW_Line();
		}
		for (i = 0; i < 101; i++) {
			column_dddw_syntax[i] = "";
		}
		for (i = 0; i < 101; i++) {
			column_dddw_data[i] = "";
		}
	}

	public WebDWSyntaxVO Clone() {
		WebDWSyntaxVO newOne = new WebDWSyntaxVO();
		newOne.datawindow = datawindow.Clone();
		newOne.header = header.Clone();
		newOne.summary = summary.Clone();
		newOne.footer = footer.Clone();
		newOne.detail = detail.Clone();
		newOne.table = table.Clone();
		int i = 0;
		for (i = 0; i < 101; i++) {
			newOne.text[i] = text[i].Clone();
		}
		for (i = 0; i < 101; i++) {
			newOne.column[i] = column[i].Clone();
		}
		for (i = 0; i < 101; i++) {
			newOne.lineinfo[i] = lineinfo[i].Clone();
		}
		for (i = 0; i < 101; i++) {
			newOne.column_dddw_syntax[i] = column_dddw_syntax[i];
		}
		for (i = 0; i < 101; i++) {
			newOne.column_dddw_data[i] = column_dddw_data[i];
		}
		return newOne;
	}

	/**
	 * Warning:Test only,Donot Use This Function in Program!!!
	 * 
	 * @return
	 */
	int getColumnNumber() {
		for (int i = 1; i <= 100; i++) {
			if (column[i].Name.length() == 0) {
				return i - 1;
			}
		}
		return 100;
	}
}
