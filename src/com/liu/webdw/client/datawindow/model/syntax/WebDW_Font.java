package com.liu.webdw.client.datawindow.model.syntax;

//'WebDW Font
public class WebDW_Font {
	public 	String face = "";
	public 	int height = 0;
	public 	int weight = 0;
	public 	int family = 0;
	public 	int pitch = 0;
	public 	int charset = 0;
	public 	int italic = 0;
	public 	int underline = 0;
	public 	int strikethrough = 0;

	public WebDW_Font Clone() {
		WebDW_Font newOne = new WebDW_Font();
		newOne.face = face;
		newOne.height = height;
		newOne.weight = weight;
		newOne.family = family;
		newOne.pitch = pitch;
		newOne.charset = charset;
		newOne.italic = italic;
		newOne.underline = underline;
		newOne.strikethrough = strikethrough;
		return newOne;
	}
}

