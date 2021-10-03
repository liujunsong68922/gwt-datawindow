package com.liu.webdw.client.datawindow.model.syntax;

//'detail define
public class WebDW_Detail {
	public int height = 0;
	public int color = 0;
	public WebDW_Detail Clone() {
		WebDW_Detail newOne = new WebDW_Detail();
		newOne.height = height;
		newOne.color = color;
		return newOne;
	}
}