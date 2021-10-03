package com.liu.webdw.client.datawindow.model.syntax;

public class WebDW_Column {
	public String band = "";
	public int id = 0;
	public int alignment = 0;
	public int tabsequence = 0;
	public int border = 0;
	public int color = 0;
	public int x = 0;
	public int y = 0;
	public int height = 0;
	public int width = 0;
	public String format = "";
	public String Name = "";
	public String tag = "";
	public int edit_limit = 0;
	public String edit_case = "";
	public String edit_focusrectangle = "";
	public String edit_autoselect = "";
	public String edit_autohscroll = "";
	public WebDW_Font font = new WebDW_Font(); // 
	public int background_mode = 0;
	public int background_color = 0;
	public WebDW_Column_RadioButtons radiobuttons = new WebDW_Column_RadioButtons(); // 
	public WebDW_Column_CheckBox checkbox = new WebDW_Column_CheckBox(); // 
	public WebDW_Column_ComboBox combobox = new WebDW_Column_ComboBox(); // 
	public WebDW_Column_DDDW dddw = new WebDW_Column_DDDW(); // 

	public WebDW_Column Clone() {
		WebDW_Column newOne = new WebDW_Column();
		newOne.band = band;
		newOne.id = id;
		newOne.alignment = alignment;
		newOne.tabsequence = tabsequence;
		newOne.border = border;
		newOne.color = color;
		newOne.x = x;
		newOne.y = y;
		newOne.height = height;
		newOne.width = width;
		newOne.format = format;
		newOne.Name = Name;
		newOne.tag = tag;
		newOne.edit_limit = edit_limit;
		newOne.edit_case = edit_case;
		newOne.edit_focusrectangle = edit_focusrectangle;
		newOne.edit_autoselect = edit_autoselect;
		newOne.edit_autohscroll = edit_autohscroll;
		newOne.font = font.Clone(); // 
		newOne.background_mode = background_mode;
		newOne.background_color = background_color;
		newOne.radiobuttons = radiobuttons.Clone(); //
		newOne.checkbox = checkbox.Clone(); //
		newOne.combobox = combobox.Clone(); //
		newOne.dddw = dddw.Clone(); //
		return newOne;
	}


}
