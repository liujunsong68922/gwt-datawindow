package com.liu.webdw.client.datawindow.model.viewmodel.ui;

import com.google.gwt.user.client.ui.AbsolutePanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.Panel;
import com.google.gwt.user.client.ui.SimplePanel;
import com.liu.webdw.client.*;
import com.liu.webdw.client.datawindow.common.GolbalENV;

import java.util.ArrayList;


/**
 *
 * 
 * @author liujunsong
 * 
 */
public class MyJLabel extends Label {
	private Label label = this;
	private Panel parentobj;

	public MyJLabel(String s1, String name,	Panel parent) {
		super();
		label.setText(s1);
		label.setTitle(name);
		//Refresh();
		//targetControls.add(this);
		parent.add(label);
		this.parentobj = parent;
	}

	public int Left;

	public int Top;

	public int Width;

	public int Height;

	public String Name;

	public String Text;

	public String Tag = "";

	public void Refresh() {
		Left = label.getAbsoluteLeft();
		Top = label.getAbsoluteTop();
		Width = label.getOffsetWidth();
		Height = label.getOffsetHeight();
		Name = label.getTitle();
		//Text = super.getText();
		Text = label.getText();
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
		label.setTitle(sname);
		Refresh();
	}


	public void setBounds(int x, int y, int width, int height) {
		AbsolutePanel pp = (AbsolutePanel)parentobj;
		pp.setWidgetPosition(label, x, y);
		label.setWidth(width+"px");
		label.setHeight(height+"px");
		//super.setBounds(x, y, width, height);
		Refresh();
	}

	//public void setBounds(java.awt.Rectangle rect) {
		//super.setBounds(rect);
		//Refresh();
	//}
}
