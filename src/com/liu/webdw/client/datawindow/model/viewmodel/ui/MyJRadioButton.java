package com.liu.webdw.client.datawindow.model.viewmodel.ui ;

import com.google.gwt.user.client.ui.AbsolutePanel;
import com.google.gwt.user.client.ui.Panel;
import com.google.gwt.user.client.ui.RadioButton;
import com.liu.webdw.client.*;
import com.liu.webdw.client.datawindow.common.GolbalENV;

import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.JRadioButton;
import java.awt.Container;


public class MyJRadioButton extends RadioButton {
	private Panel parentobj;
	
	private String strvalue;
	
	
	public String getStrvalue() {
		return strvalue;
	}

	public void setStrvalue(String strvalue) {
		this.strvalue = strvalue;
	}

	public MyJRadioButton(String s1, String svalue,String name, ArrayList targetControls,
			Panel parent) {
		super(name);
		super.setHTML(s1);
		super.setName(name);
		super.setTitle(name);
		this.strvalue = svalue;
		Refresh();
		targetControls.add(this);
		parent.add(this);
		this.parentobj = parent;
	}

	public int Left;

	public int Top;

	public int Width;

	public int Height;

	public String Name;

	public String Text;

	public String Tag = "";

	public boolean Value = false;

	public boolean Enabled = false;
	public void Refresh() {
		Left = super.getAbsoluteLeft();
		Top = super.getAbsoluteTop();
		Width = super.getOffsetWidth();
		Height = super.getOffsetHeight();
		Name = super.getName();
		Text = super.getText();
		Value = super.isChecked();
		Enabled = super.isEnabled();
	}

	public void Left(int xvalue) {
		//super.setBounds(xvalue, Top, Width, Height);
		Refresh();
	}

	public void Top(int yvalue) {
		//super.setBounds(Left, yvalue, Width, Height);
		Refresh();
	}

	public void Width(int wvalue) {
		//super.setBounds(Left, Top, wvalue, Height);
		Refresh();
	}

	public void Height(int hvalue) {
		//super.setBounds(Left, Top, Width, hvalue);
		Refresh();
	}

	public void Name(String sname) {
		super.setName(sname);
		Refresh();
	}

	public void Text(String stext) {
		super.setText(stext);
		Refresh();
	}

	public void Value(boolean invalue) {
		super.setChecked(invalue);
	}
	
	public void Enabled(boolean invalue){
		super.setEnabled(invalue);
	}

	public void setBounds(int x, int y, int width, int height) {
		super.setWidth(width+"px");
		super.setHeight(height+"px");
		if(this.parentobj instanceof AbsolutePanel) {
			AbsolutePanel pp = (AbsolutePanel) parentobj;
			pp.setWidgetPosition(this, x, y);
		}
	}

}
