package com.liu.webdw.client.datawindow.model.viewmodel;

import java.util.ArrayList;

import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.ClickListener;
import com.google.gwt.user.client.ui.Panel;
import com.google.gwt.user.client.ui.Widget;
import com.liu.webdw.client.datawindow.model.viewmodel.ui.MyJComboBox;

public 	class WebDW_DynamicComboBox extends MyJComboBox {
	public WebDW_DynamicComboBox(String name, ArrayList targetControls,
			Panel parent) {
		super(name, targetControls, parent);
		super.setTitle(name);
		this.addClickListener(new ClickListener() {

			@Override
			public void onClick(Widget sender) {
				Window.alert("You Clicked DynamicCombobox:"+sender.getTitle());
				
			}});
		// '�����ѡ���ʱ��������Ӧ�ֶ�����

		//this.addActionListener(new ActionListener() {
		//	public void actionPerformed(ActionEvent e) {
		//		int iret = 0;// As Long
		//		MyInt rowid = new MyInt(0);// As Long
		//		MyInt colid = new MyInt(0);// As Long
		//		String svalue = "";// As String '����ؼ����������ֵ
		//		int pos1 = 0;// As Long
		//		int pos2 = 0;// As Long
		//		int pos3 = 0;// As Long
		//		String allData[] = new String[1];

		//		iret = GetRowIdColumnId(Name, rowid, colid);
		//		if (iret == 0) {// Then

		//			allData = Split(
		//					local_webdw.table.Columns[colid.intvalue].values,
		//					"/");
		//			Refresh();
		//			if (ListIndex >= 0) {// Then
		//				svalue = allData[ListIndex];
		//				pos1 = InStr(1, svalue, Chr(9));
		//				if (pos1 > 0) {// Then
		//					DW_SetItem(rowid.intvalue, colid.intvalue, Mid(
		//							svalue, pos1 + 1));
		//				}
		//			} else {
		//
		//			}
		//		}
		//	}
		//});
	}
}

