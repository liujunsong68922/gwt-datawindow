package com.liu.webdw.client.datawindow.model.viewmodel.ui ;

import com.google.gwt.user.client.ui.Panel;
import com.google.gwt.user.client.ui.TextArea;
import com.liu.webdw.client.*;
import com.liu.webdw.client.datawindow.common.GolbalENV;

import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.JTextArea;
import java.awt.Container;

/**
 * �Զ����JPanel��,�����򻯴����Ǩ�ƹ���
 * 
 * @author liujunsong
 * 
 */
public class MyJTextArea extends TextArea {
	public void ReadMe() {
		System.out
				.println("My Create JSlider,It has the same interface like VB");
		System.out.println(GolbalENV.JWebDWInfo);
	}

	/**
	 * ����һ��ArrayList�Ĺ��캯��,�ڴ����˶����Ժ�,���Լ����뵽���ArrayList����
	 * 
	 * @param targetControls
	 */
	public MyJTextArea(String stext,String name,ArrayList targetControls,Panel parent) {
		super.setText(stext);
		super.setName(name);
		//super.setLayout(null);
		//super.setBorder(BorderFactory.createEtchedBorder());
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

	public boolean Locked = false;
	
	public void Refresh() {
		Left = super.getAbsoluteLeft();
		Top = super.getAbsoluteTop();
		Width = super.getOffsetWidth();
		Height = super.getOffsetHeight();
		Name = super.getName();
		Text = super.getText();
		Locked = ! super.isEnabled();
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
	
	public void Text(String stext){
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
	
	public void Locked(boolean b){
		//super.setEditable(! b);
		Refresh();
	}
}
