package com.liu.webdw.client.datawindow.model.syntax;

//'header define
public class WebDW_Header {
	public int height = 0;
	public int color = 0;
	public WebDW_Header Clone() {
		WebDW_Header newOne = new WebDW_Header();
		newOne.height = height;
		newOne.color = color;
		return newOne;
	}
}
