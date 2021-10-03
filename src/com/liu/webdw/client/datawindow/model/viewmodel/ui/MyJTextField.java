package com.liu.webdw.client.datawindow.model.viewmodel.ui ;

import com.google.gwt.user.client.ui.AbsolutePanel;
import com.google.gwt.user.client.ui.Panel;
import com.google.gwt.user.client.ui.TextBox;
import com.liu.webdw.client.*;
import com.liu.webdw.client.datawindow.common.GolbalENV;

import java.util.ArrayList;


/**
 * �Զ����JPanel��,�����򻯴����Ǩ�ƹ���
 * 
 * @author liujunsong
 * 
 */
public class MyJTextField extends TextBox {
	private Panel parentobj;
	
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
	public MyJTextField(String stext,String name,ArrayList targetControls,Panel parent) {
		super.setText(stext);
		super.setName(name);
		super.setTitle(name);
		this.parentobj = parent;

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
		this.Left = x;
		this.Top = y;
		this.Width = width;
		this.Height = height;
		
		AbsolutePanel pp = (AbsolutePanel)parentobj;
		pp.setWidgetPosition(this, x, y);
		super.setWidth(width+"px");
		super.setHeight(height+"px");
		//super.setBounds(x, y, width, height);
		Refresh();
	}
	
	public void Locked(boolean b){
		//super.setEditable(! b);
		Refresh();
	}

	public int getLeft() {
		return Left;
	}

	public void setLeft(int left) {
		Left = left;
	}

	public int getTop() {
		return Top;
	}

	public void setTop(int top) {
		Top = top;
	}

	public int getWidth() {
		return Width;
	}

	public void setWidth(int width) {
		Width = width;
	}

	public int getHeight() {
		return Height;
	}

	public void setHeight(int height) {
		Height = height;
	}

	public String getName() {
		return Name;
	}

	public void setName(String name) {
		super.setTitle(name);
		Name = name;
	}

	public String getText() {
		return Text;
	}

	public void setText(String text) {
		super.setText(text);
		Text = text;
	}

	public String getTag() {
		return Tag;
	}

	public void setTag(String tag) {
		Tag = tag;
	}

	public boolean isLocked() {
		return Locked;
	}

	public void setLocked(boolean locked) {
		Locked = locked;
	}
	
	public int getX() {
		return Left;
	}
	
	public int getY() {
		return Top;
	}
	
}
