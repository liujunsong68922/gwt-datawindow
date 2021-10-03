package com.liu.webdw.client.datawindow.view.model;

public class CViewModel_Detail_Row_Column extends CViewModel_AbstractUIVO {
	private int rowid;
	private int columnid;
	private int columnname;
	private String columndata;
	private String type;
	private String values;
	
	public CViewModel_Detail_Row_Column(int ileft, int itop, int iwidth, int iheight) {
		super(ileft, itop, iwidth, iheight);
		// TODO Auto-generated constructor stub
	}

	
	//the following is used to describe special column type text.
	//textbox part
	
	//combobox part
	private boolean checkbox_value;

	private int selectedIndex;


	public int getSelectedIndex() {
		return selectedIndex;
	}
	public void setSelectedIndex(int selectedIndex) {
		this.selectedIndex = selectedIndex;
	}
	public boolean isCheckbox_value() {
		return checkbox_value;
	}
	public void setCheckbox_value(boolean checkbox_value) {
		this.checkbox_value = checkbox_value;
	}
	public int getRowid() {
		return rowid;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public void setRowid(int rowid) {
		this.rowid = rowid;
	}
	public int getColumnid() {
		return columnid;
	}
	public void setColumnid(int columnid) {
		this.columnid = columnid;
	}
	public int getColumnname() {
		return columnname;
	}
	public void setColumnname(int columnname) {
		this.columnname = columnname;
	}
	public String getColumndata() {
		return columndata;
	}
	public void setColumndata(String columndata) {
		this.columndata = columndata;
	}


	public String getValues() {
		return values;
	}


	public void setValues(String values) {
		this.values = values;
	}
	
	
}
