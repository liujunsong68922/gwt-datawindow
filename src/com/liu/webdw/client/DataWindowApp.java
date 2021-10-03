package com.liu.webdw.client;

import com.liu.webdw.client.datawindow.CWebDWUI;
import com.liu.webdw.client.datawindow.model.viewmodel.panel.WebDW_AbsolutePanel;
import com.liu.webdw.shared.FieldVerifier;

import java.util.ArrayList;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.KeyCodes;
import com.google.gwt.event.dom.client.KeyUpEvent;
import com.google.gwt.event.dom.client.KeyUpHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.AbsolutePanel;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.ChangeListener;
import com.google.gwt.user.client.ui.ClickListener;
import com.google.gwt.user.client.ui.DialogBox;
import com.google.gwt.user.client.ui.DockPanel;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.ScrollPanel;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;

/**
 * Entry point classes define <code>onModuleLoad()</code>.
 */
public class DataWindowApp implements EntryPoint {
	/**
	 * The message displayed to the user when the server cannot be reached or
	 * returns an error.
	 */
	private static final String SERVER_ERROR = "An error occurred while "
			+ "attempting to contact the server. Please check your network " + "connection and try again.";

	/**
	 * Create a remote service proxy to talk to the server-side Greeting service.
	 */
	private final GreetingServiceAsync greetingService = GWT.create(GreetingService.class);

	private final WebDW_AbsolutePanel parentdw = new WebDW_AbsolutePanel("parentdw", null, null);

	private final ScrollPanel scrolldw = new ScrollPanel();

	private final CWebDWUI ui = new CWebDWUI();
	
	//d_products_grid,d_employee_list
	private String dwname="d_products";
	
	private final ListBox dwlist = new ListBox();
	/**
	 * This is the entry point method.
	 */
	public void onModuleLoad() {
		//构建一个数据窗口的下拉选择
		dwlist.addItem("d_products");
		dwlist.addItem("d_products_grid");
		dwlist.addItem("d_employee_list");
		dwlist.addItem("d_employee");
		
		dwlist.addChangeListener(new ChangeListener() {

			@Override
			public void onChange(Widget sender) {
				// TODO Auto-generated method stub
				dwname = dwlist.getSelectedItemText();
				greetingService.getDWDefine(dwname, new AsyncCallback() {

					@Override
					public void onFailure(Throwable caught) {
						Window.alert("call failed");
					}

					@Override
					public void onSuccess(Object result) {
						String sdesc = (String) result;
						consoleLog(sdesc);

						ui.DW_SetDataObject(parentdw,  sdesc);

					}
				});
			}});
		
		RootPanel.get("dwselectContainer").add(dwlist);
		
		parentdw.setWidth(1000);
		parentdw.setHeight(600);

		final Button sendButton = new Button("检索数据");
		sendButton.addClickListener(new ClickListener() {

			@Override
			public void onClick(Widget sender) {
				greetingService.getSelectSql(dwname, new AsyncCallback() {

					@Override
					public void onFailure(Throwable caught) {
						Window.alert("call failure on get SQL.");
					}

					@Override
					public void onSuccess(Object result) {
						String strsql = (String) result;
						Window.alert(strsql);
						consoleLog("sql:" + strsql);

						greetingService.getSelectData(dwname, strsql, "", new AsyncCallback() {

							@Override
							public void onFailure(Throwable caught) {
								Window.alert(" Call Failed on getSelectData.");
							}

							@Override
							public void onSuccess(Object result) {
								String sresult = (String) result;

								consoleLog(sresult);

								ui.SetData(sresult);
								ui.DrawDW();
							}
						});
					}

				});

			}
		});

		final Button insertButton = new Button("新建记录");
		insertButton.addClickListener(new ClickListener() {

			@Override
			public void onClick(Widget sender) {
				ui.DW_InsertRow(0);
				
			}
			
		});
		
		final TextBox nameField = new TextBox();
		nameField.setText("GWT User");
		final Label errorLabel = new Label();

		// We can add style names to widgets
		sendButton.addStyleName("sendButton");

		
		RootPanel.get("nameFieldContainer").add(nameField);
		RootPanel.get("sendButtonContainer").add(sendButton);
		RootPanel.get("insertButtonContainer").add(insertButton);
		RootPanel.get("errorLabelContainer").add(errorLabel);

		// scrolldw.setStyleName("scrollpanel");
		scrolldw.setWidth("800px");
		scrolldw.setHeight("500px");
		scrolldw.add(parentdw);

		DockPanel dock = new DockPanel();
		dock.setStyleName("dockpanel");
		HTML html = new HTML("<h3>说明：这是一个使用GWT技术绘制的数据窗口. </h3>");

		dock.add(html, DockPanel.NORTH);
		dock.add(scrolldw, DockPanel.CENTER);

		consoleLog("hello,message from datawindow app.");
		try {
			RootPanel.get("dw").add(dock);
		} catch (Exception e) {
			e.printStackTrace();
			Window.alert(e.getMessage());
		}
		
		greetingService.getDWDefine(dwname, new AsyncCallback() {

			@Override
			public void onFailure(Throwable caught) {
				Window.alert("call failed");
			}

			@Override
			public void onSuccess(Object result) {
				String sdesc = (String) result;
				consoleLog(sdesc);

				ui.DW_SetDataObject(parentdw,  sdesc);

			}
		});

	}

	public native void consoleLog(String message) /*-{
													//alert(message);
													console.log( "[DataWindowApp]:\r\n" + message );
													}-*/;

}
