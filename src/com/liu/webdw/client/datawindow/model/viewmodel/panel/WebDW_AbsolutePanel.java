package com.liu.webdw.client.datawindow.model.viewmodel.panel ;

import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.AbsolutePanel;
import com.google.gwt.user.client.ui.Panel;
import com.google.gwt.user.client.ui.Widget;
import com.liu.webdw.client.*;
import com.liu.webdw.client.datawindow.common.GolbalENV;

import java.util.ArrayList;
import java.util.Iterator;


public class WebDW_AbsolutePanel extends AbsolutePanel {
	private Panel parent;
	
	//封装currentRow这个数据到这里来
	private int currentRow = 0;//

	//封装所有动态创建的UI元素在里面
	private ArrayList<Widget> childControls = new ArrayList<Widget>();//
	
	
	public int getCurrentRow() {
		return currentRow;
	}

	public void setCurrentRow(int currentRow) {
		this.currentRow = currentRow;
	}

	
	public ArrayList<Widget> getChildtControls() {
		return childControls;
	}

	public WebDW_AbsolutePanel(String name,ArrayList targetControls,Panel parent) {
		super();
		super.setTitle(name);

		if(targetControls != null) {
			targetControls.add(this);
		}
		
		if(parent != null) {
			parent.add(this);
		}
		
		this.parent = parent;
	}


	public void setBounds(int x, int y, int width, int height) {
		
		this.setWidth(width+"px");
		this.setHeight(height+"px");
		if(this.parent instanceof AbsolutePanel) {
			AbsolutePanel pp = (AbsolutePanel) this.parent;
			pp.setWidgetPosition(this, x, y);
		}
		//super.setBounds(x, y, width, height);
		//Refresh();
	}

	@Override
	public Iterator<Widget> iterator() {
		return super.iterator();
	}

	@Override
	public boolean remove(Widget child) {
		super.remove(child);
		return false;
	}

	public void setWidth(int width) {
		super.setWidth(width+"px");
	}

	public void setHeight(int height) {
		super.setHeight(height+"px");
	}

	public void removeObjectList( ) {
		Window.alert("MyAbsolutePanel.enter removeObjectList,size:"+childControls.size());
		for(Object child : childControls) {
			Widget childwidget = (Widget)child;
			try {
				super.remove(childwidget);
				//Window.alert("remove");
			}catch(Exception e) {
				e.printStackTrace();
				consoleLog(e.toString());
			}
		}
		childControls.clear();
	}
	
	public native void consoleLog(String message) /*-{
	//alert(message);
	console.log( "MyAbsolutPanel:" + message );
	}-*/;

	public String getName() {
		//return this.Name;
		return super.getTitle();
	}

}
