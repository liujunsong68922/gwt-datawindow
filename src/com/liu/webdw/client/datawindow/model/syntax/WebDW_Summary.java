package com.liu.webdw.client.datawindow.model.syntax;

//'summary define
public class WebDW_Summary {
	public int height = 0;
	public int color = 0;
	public WebDW_Summary Clone() {
		WebDW_Summary newOne = new WebDW_Summary();
		newOne.height = height;
		newOne.color = color;
		return newOne;
	}
}

