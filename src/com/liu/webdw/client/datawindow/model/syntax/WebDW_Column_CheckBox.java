package com.liu.webdw.client.datawindow.model.syntax;

//CheckBox
public class WebDW_Column_CheckBox  {
	public String text = ""; // 
	public String on = ""; // ֵ
	public String off = ""; // ֵ
	public String scale1 = ""; // 
	public String threed = ""; // 

	public WebDW_Column_CheckBox Clone() {
		WebDW_Column_CheckBox newOne = new WebDW_Column_CheckBox();
		newOne.text = text; // 
		newOne.on = on; // ֵ
		newOne.off = off; // ֵ
		newOne.scale1 = scale1; // 
		newOne.threed = threed; // 
		return newOne;
	}


}
