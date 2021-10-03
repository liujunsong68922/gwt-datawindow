package com.liu.webdw.client.datawindow.model.viewmodel.ui;

import java.util.ArrayList;

import com.google.gwt.user.client.ui.AbsolutePanel;

//import javax.swing.BorderFactory;
//import javax.swing.JComboBox;
//import java.awt.Container;

import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.Panel;
import com.liu.webdw.client.*;
import com.liu.webdw.client.datawindow.common.GolbalENV;

public class MyJComboBox extends ListBox {
	Panel parentPanel = null;
	
	public MyJComboBox(String name, ArrayList targetControls,Panel parent) {
		super.setName(name);
		Refresh();
		targetControls.add(this);
		parent.add(this);
		this.parentPanel = parent;
	}	

	public String Text;

	public String Tag = "";

	public boolean Enabled;
	
	public int ListIndex = 0;
	public void Refresh() {
		try{
		Text = super.getSelectedItemText();
		}catch(Exception e){
			Text="";
		}
		Enabled = super.isEnabled();
		ListIndex = super.getSelectedIndex();
	}


	public void Name(String sname) {
		super.setName(sname);
		Refresh();
	}

	public void Text(String stext) {
		//super.setText(stext);
		Refresh();
	}
	public void Enabled(boolean invalue){
		super.setEnabled(invalue);
	}
	public void setBounds(int x, int y, int width, int height) {
		super.setWidth(width+"px");
		super.setHeight(height+"px");
		
		if (this.parentPanel instanceof AbsolutePanel) {
			AbsolutePanel pp = (AbsolutePanel)this.parentPanel;
			pp.setWidgetPosition(this, x, y);
		}

	}

}
