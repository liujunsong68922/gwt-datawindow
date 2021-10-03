package com.liu.webdw.client.datawindow.view.model;

public class CViewModel_AbstractUIVO extends CViewModel_RectangleVO {

	public CViewModel_AbstractUIVO(int ileft, int itop, int iwidth, int iheight) {
		super(ileft, itop, iwidth, iheight);
		// TODO Auto-generated constructor stub
	}

	private String text;
	private String name;
	
	
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	
}
