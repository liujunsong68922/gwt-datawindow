package com.liu.webdw.client.datawindow.view.model;

import java.util.ArrayList;

public class CViewModel_Detail{
	//detail 信息由每一行构成
	private ArrayList<CViewModel_Detail_Row> rows = new ArrayList<CViewModel_Detail_Row>();

	public ArrayList<CViewModel_Detail_Row> getRows() {
		return rows;
	}

	public void setRows(ArrayList<CViewModel_Detail_Row> rows) {
		this.rows = rows;
	}
}
