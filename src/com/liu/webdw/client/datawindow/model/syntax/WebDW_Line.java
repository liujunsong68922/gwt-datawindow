package com.liu.webdw.client.datawindow.model.syntax;

public class WebDW_Line {
	public  String band = "";
	public int x1 = 0;
	public int y1 = 0;
	public int x2 = 0;
	public int y2 = 0;
	public String Name = "";
	public String pen_style = "";
	public String pen_width = "";
	public String pen_color = "";
	public String background_mode = "";
	public String background_color = "";

	public WebDW_Line Clone() {
		WebDW_Line newOne = new WebDW_Line();
		newOne.band = band;
		newOne.x1 = x1;
		newOne.y1 = y1;
		newOne.x2 = x2;
		newOne.y2 = y2;
		newOne.Name = Name;
		newOne.pen_style = pen_style;
		newOne.pen_width = pen_width;
		newOne.pen_color = pen_color;
		newOne.background_mode = background_mode;
		newOne.background_color = background_color;
		return newOne;
	}
}
