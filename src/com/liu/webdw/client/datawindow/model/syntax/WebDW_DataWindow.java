package com.liu.webdw.client.datawindow.model.syntax;

public class WebDW_DataWindow {
	public String unit = "";
	public String timer_interval = "";
	public int color = 0;
	public String processiong = "";
	public String HTMLDW = "";
	public String print_documentname = "";
	public int print_orientation = 0;
	public int print_margin_left = 0;
	public int print_margin_right = 0;
	public int print_margin_top = 0;
	public int print_margin_bottom = 0;
	public int print_paper_source = 0;
	public int print_paper_size = 0;
	public String print_prompt = "";
	public String print_buttons = "";
	public String print_preview_buttons = "";
	public String grid_lines = "";
	
	public WebDW_DataWindow Clone() {
		WebDW_DataWindow newOne = new WebDW_DataWindow();
		newOne.unit = this.unit;
		newOne.timer_interval = this.timer_interval;
		newOne.color = this.color;
		newOne.processiong = processiong;
		newOne.HTMLDW = HTMLDW;
		newOne.print_documentname = print_documentname;
		newOne.print_orientation = print_orientation;
		newOne.print_margin_left = print_margin_left;
		newOne.print_margin_right = print_margin_right;
		newOne.print_margin_top = print_margin_top;
		newOne.print_margin_bottom = print_margin_bottom;
		newOne.print_paper_source = print_paper_source;
		newOne.print_paper_size = print_paper_size;
		newOne.print_prompt = print_prompt;
		newOne.print_buttons = print_buttons;
		newOne.print_preview_buttons = print_preview_buttons;
		newOne.grid_lines = grid_lines;

		return newOne;
	}
}
