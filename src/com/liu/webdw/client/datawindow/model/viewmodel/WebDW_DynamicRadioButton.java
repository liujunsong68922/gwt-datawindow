package com.liu.webdw.client.datawindow.model.viewmodel;

import java.util.ArrayList;

import com.google.gwt.event.dom.client.ChangeEvent;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.ChangeListener;
import com.google.gwt.user.client.ui.ClickListener;
import com.google.gwt.user.client.ui.Panel;
import com.google.gwt.user.client.ui.Widget;
import com.liu.webdw.client.datawindow.common.VBInt;
import com.liu.webdw.client.datawindow.model.viewmodel.ui.MyJRadioButton;
import com.liu.webdw.client.datawindow.view.CWebDWUI_ParentDW;

public 	class WebDW_DynamicRadioButton extends MyJRadioButton {
	CWebDWUI_ParentDW parentdw;
	
	public CWebDWUI_ParentDW getParentdw() {
		return parentdw;
	}

	public void setParentdw(CWebDWUI_ParentDW parentdw) {
		this.parentdw = parentdw;
	}

	class DymaRadioButton_radioListerner implements ChangeListener {
		public void stateChanged(ChangeEvent e) {
			int iret = 0;// As Long
			VBInt rowid = new VBInt(0);// As Long
			VBInt colid = new VBInt(0);// As Long
			Refresh();
			if (!Value) {
				return;
			}
			System.out.println("You enter radiobutton:" + Name
					+ "  Tag is:" + Tag);
			iret = parentdw.GetRowIdColumnId(Name, rowid, colid);// '�õ��кź��к�
			if (iret == 0) {// Then
				parentdw.DW_SetItem(rowid.intvalue, colid.intvalue, Tag);// '��tag����ȡ��ֵ�����ñ���
			}

		}

		@Override
		public void onChange(Widget sender) {
			// TODO Auto-generated method stub
			
		}
	}

	public WebDW_DynamicRadioButton(String s1,String svalue, String name,
			ArrayList targetControls, Panel parent) {
		
		super(s1, svalue, name, targetControls, parent);
		this.addClickListener(new ClickListener() {

			@Override
			public void onClick(Widget sender) {
				if(sender instanceof WebDW_DynamicRadioButton) {
					WebDW_DynamicRadioButton rb = (WebDW_DynamicRadioButton) sender;
					Window.alert("You clicked DynamicRadioButton: "+rb.getStrvalue());
				}
				
			}});
	}
}
