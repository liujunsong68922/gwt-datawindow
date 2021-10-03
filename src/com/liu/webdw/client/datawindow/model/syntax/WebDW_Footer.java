package com.liu.webdw.client.datawindow.model.syntax;

//'footer define
public class WebDW_Footer {
	public int height = 0;
	public int color = 0;
	public WebDW_Footer Clone() {
		WebDW_Footer newOne = new WebDW_Footer();
		newOne.height = height;
		newOne.color = color;
		return newOne;
	}
}
