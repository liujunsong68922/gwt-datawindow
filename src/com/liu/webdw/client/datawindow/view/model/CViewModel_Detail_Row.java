package com.liu.webdw.client.datawindow.view.model;

import java.util.ArrayList;

public class CViewModel_Detail_Row{
	private int rowid;
	
	public int getRowid() {
		return rowid;
	}

	public void setRowid(int rowid) {
		this.rowid = rowid;
	}

	public ArrayList<CViewModel_Detail_Row_Column> getColumns() {
		return columns;
	}

	public void setColumns(ArrayList<CViewModel_Detail_Row_Column> columns) {
		this.columns = columns;
	}

	ArrayList<CViewModel_Detail_Row_Column> columns = new ArrayList<CViewModel_Detail_Row_Column>();
}