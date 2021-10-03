package com.liu.webdw.client.datawindow.model.viewmodel;

import java.util.ArrayList;

import com.google.gwt.event.dom.client.ChangeEvent;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.ChangeListener;
import com.google.gwt.user.client.ui.ClickListener;
import com.google.gwt.user.client.ui.Panel;
import com.google.gwt.user.client.ui.Widget;
import com.liu.webdw.client.datawindow.common.VBInt;
import com.liu.webdw.client.datawindow.model.viewmodel.ui.MyJCheckBox;
import com.liu.webdw.client.datawindow.view.CWebDWUI_ParentDW;

public class WebDW_DynamicCheckBox extends MyJCheckBox {
	private CWebDWUI_ParentDW parentdw;
	
	public CWebDWUI_ParentDW getParentdw() {
		return parentdw;
	}

	public void setParentdw(CWebDWUI_ParentDW parentdw) {
		this.parentdw = parentdw;
	}

	class DymaCheckBox_checkListerner implements ChangeListener {
		public void stateChanged(ChangeEvent e) {
			// '����ѡ���ť�Ĳ���
			// 'MsgBox "change", , myTextBox.name
			int iret = 0;// As Long
			VBInt rowid = new VBInt(0);// As Long
			VBInt colid = new VBInt(0);// As Long

			iret = parentdw.GetRowIdColumnId(Name, rowid, colid);// '�õ��кź��к�
			if (iret == 0) {// Then
				Refresh();
				System.out.println("You enter checkbox listener. ");
				if (Value) {// = 1 Then 'ѡ��״̬
					parentdw.DW_SetItem(rowid.intvalue, colid.intvalue,
							parentdw.local_webdw().column[colid.intvalue].checkbox.on);
				} else {// 'δѡ��״̬
					parentdw.DW_SetItem(rowid.intvalue, colid.intvalue,
							parentdw.local_webdw().column[colid.intvalue].checkbox.off);
				}
			}

		}

		@Override
		public void onChange(Widget sender) {
			// TODO Auto-generated method stub
			
		}
	}

	public WebDW_DynamicCheckBox(String s1, String name,
			ArrayList targetControls, Panel parent) {

		super(s1, name, targetControls, parent);
		this.addClickListener(new ClickListener() {

			@Override
			public void onClick(Widget sender) {
				Window.alert("You clicked DynamicCheckBox:"+sender.getTitle());
				
			}});
		//this.addChangeListener(new DymaCheckBox_checkListerner());
	}
}




