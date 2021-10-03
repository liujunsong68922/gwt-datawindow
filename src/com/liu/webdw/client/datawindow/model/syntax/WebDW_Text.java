package com.liu.webdw.client.datawindow.model.syntax;

public class WebDW_Text {
	public String band = "";
	public int alignment = 0;
	public String text = "";
	public int border = 0;
	public int color = 0;
	public int x = 0;
	public int y = 0;
	public int height = 0;
	public int width = 0;
	public String Name = "";
	public WebDW_Font font = new WebDW_Font(); // '�¶����font����
	public int background_mode = 0;
	public int background_color = 0;
	public WebDW_Text Clone() {
		WebDW_Text newOne = new WebDW_Text();
		newOne.band = band;
		newOne.alignment = alignment;
		newOne.text = text;
		newOne.border = border;
		newOne.color = color;
		newOne.x = x;
		newOne.y = y;
		newOne.height = height;
		newOne.width = width;
		newOne.Name = Name;
		newOne.font = font.Clone(); // '�¶����font����
		newOne.background_mode = background_mode;
		newOne.background_color = background_color;
		return newOne;
	}
}
