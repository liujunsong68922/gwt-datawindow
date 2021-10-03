package com.liu.webdw.client.datawindow.model.viewmodel.ui;

import java.util.ArrayList;

import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Panel;
import com.liu.webdw.client.datawindow.common.GolbalENV;

public class MyJButton extends Button {
	public MyJButton(String s1,String name, ArrayList targetControls,Panel parent) {
		super(s1);
		super.setText(name);
		Refresh();
		targetControls.add(this);
		parent.add(this);
	}	

	public int Left;

	public int Top;

	public int Width;

	public int Height;

	public String Name;

	public String Text;

	public String Tag = "";

	public void Refresh() {
		Left = super.getAbsoluteLeft();
		Top = super.getAbsoluteTop();
		Width = super.getOffsetWidth();
		Height = super.getOffsetWidth();
		Name = super.getTitle();
		Text = super.getText();
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
		//super.setName(sname);
		super.setTitle(sname);
		Refresh();
	}

	public void Text(String stext) {
		super.setText(stext);
		Refresh();
	}

	public void setBounds(int x, int y, int width, int height) {
		//super.setBounds(x, y, width, height);
		Refresh();
	}

	public void setBounds(java.awt.Rectangle rect) {
		//super.setBounds(rect);
		Refresh();
	}
}
