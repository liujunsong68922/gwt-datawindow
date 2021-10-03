package com.liu.webdw.client.datawindow.model.viewmodel.ui;

import java.util.ArrayList;

import com.google.gwt.user.client.ui.AbsolutePanel;

//import javax.swing.BorderFactory;
//import javax.swing.JCheckBox;
//import java.awt.Container;

import com.google.gwt.user.client.ui.CheckBox;
import com.google.gwt.user.client.ui.Panel;
import com.liu.webdw.client.*;
import com.liu.webdw.client.datawindow.common.GolbalENV;

public class MyJCheckBox extends CheckBox {
	private Panel parentobj;

	public MyJCheckBox(String s1, String name, ArrayList targetControls, Panel parent) {
		super(s1);
		super.setName(name);
		super.setName(name);
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
		// super.setBounds(xvalue, Top, Width, Height);
		Refresh();
	}

	public void Top(int yvalue) {
		// super.setBounds(Left, yvalue, Width, Height);
		Refresh();
	}

	public void Width(int wvalue) {
		// super.setBounds(Left, Top, wvalue, Height);
		Refresh();
	}

	public void Height(int hvalue) {
		// super.setBounds(Left, Top, Width, hvalue);
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
		// super.setSelected(invalue);
		super.setChecked(invalue);
	}

	public void Enabled(boolean invalue) {
		super.setEnabled(invalue);
	}

	// public void setBounds(int x, int y, int width, int height) {
	// super.setBounds(x, y, width, height);
	// Refresh();
	// }

	// public void setBounds(java.awt.Rectangle rect) {
	// //super.setBounds(rect);
	// Refresh();
	// }

	public void setBounds(int x, int y, int width, int height) {
		this.Left = x;
		this.Top = y;
		this.Width = width;
		this.Height = height;

		if (this.parentobj instanceof AbsolutePanel) {
			AbsolutePanel pp = (AbsolutePanel) parentobj;
			pp.setWidgetPosition(this, x, y);
		}
		super.setWidth(width + "px");
		super.setHeight(height + "px");

		// super.setBounds(x, y, width, height);
		Refresh();
	}

}
