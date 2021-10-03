package com.liu.webdw.client.datawindow.model.viewmodel;

import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.ClickListener;
import com.google.gwt.user.client.ui.Panel;
import com.google.gwt.user.client.ui.Widget;
import com.liu.webdw.client.datawindow.model.viewmodel.panel.WebDW_AbsolutePanel;
import com.liu.webdw.client.datawindow.model.viewmodel.ui.MyJLabel;
import com.liu.webdw.client.datawindow.view.CWebDWUI_ParentDW;

public class WebDW_DynamicLabel extends MyJLabel {
	private CWebDWUI_ParentDW parentdw;

	public CWebDWUI_ParentDW getParentdw() {
		return parentdw;
	}

	public void setParentdw(CWebDWUI_ParentDW parentdw) {
		this.parentdw = parentdw;
	}

	public WebDW_DynamicLabel(String s1, String name, WebDW_AbsolutePanel parent) {
		super(s1, name, parent);
		// add this label object to parent control list
		parent.getChildtControls().add(this);

		this.addClickListener(new ClickListener() {

			@Override
			public void onClick(Widget sender) {
				Window.alert("You clicked " + sender.getTitle());
				consoleLog("You clicked " + sender.getTitle());
			}
		});
	}

	public native void consoleLog(String message) /*-{
													//alert(message);
													console.log( "WebDW_DynamicLabel:" + message );
													}-*/;
}
