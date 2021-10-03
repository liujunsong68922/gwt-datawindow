package com.liu.webdw.client.datawindow.model.viewmodel;

import java.util.ArrayList;

import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.ClickListener;
import com.google.gwt.user.client.ui.Panel;
import com.google.gwt.user.client.ui.Widget;
import com.liu.webdw.client.datawindow.common.VBInt;
import com.liu.webdw.client.datawindow.model.viewmodel.ui.MyJTextField;
import com.liu.webdw.client.datawindow.view.CWebDWUI_ParentDW;

public class WebDW_DynamicTextField extends MyJTextField {
	CWebDWUI_ParentDW parentdw ;
	
	public CWebDWUI_ParentDW getParentdw() {
		return parentdw;
	}

	public void setParentdw(CWebDWUI_ParentDW parentdw) {
		this.parentdw = parentdw;
	}

	private void updateTextData() {
		System.out.println("enter.");
		Refresh();
		// 'Ϊ�����ظ�������Ч���ܵ��ã����tag="reenter"����ɶҲ����
		if (Tag.equals("reenter")) {
			Tag = "";
			return;
		}
		VBInt rowid = new VBInt(0);
		VBInt colid = new VBInt(0);
		int iret = parentdw.GetRowIdColumnId(Name, rowid, colid);// '�õ���ǰ�У���ǰ��
		if (iret == 0) {
			iret = parentdw.DW_SetItem(rowid.intvalue, colid.intvalue, Text);// '��������
			// If iret = -1 Then
			// 'myTextBox.tag = "reenter" '���������־
			// 'myTextBox.text = DW_GetItemString(rowid, colid)
			// 'myTextBox.tag = ""
			// End If
		}
	}

	private void setCurrentRow() {
		VBInt rowid = new VBInt(0);
		VBInt colid = new VBInt(0);
		int iret = parentdw.GetRowIdColumnId(Name, rowid, colid);// '�õ���ǰ�У���ǰ��
		if (iret == 0) {
			iret = parentdw.DW_SetRow(rowid.intvalue);
			// If iret = -1 Then
			// 'myTextBox.tag = "reenter" '���������־
			// 'myTextBox.text = DW_GetItemString(rowid, colid)
			// 'myTextBox.tag = ""
			// End If
		}

	}

	public WebDW_DynamicTextField(String stext, String name,
			ArrayList targetControlsArg, Panel parent) {
		super(stext, name, targetControlsArg, parent);
		
		this.addClickListener(new ClickListener() {

			@Override
			public void onClick(Widget sender) {
				Window.alert("[Dynamic_TextField]You clicked on :"+ sender.getTitle());
				consoleLog("[Dynamic_TextField]You clicked on :"+ sender.getTitle());
			}});


//		this.addMouseListener(new MouseAdapter() {
//			/* (non-Javadoc)
//			 * @see java.awt.event.MouseAdapter#mouseClicked(java.awt.event.MouseEvent)
//			 */
//			public void mouseClicked(MouseEvent e) {
//				// setCurrentRow();
//
//				// '�����ı����ϵ���ʱ�����õ�ǰ��
//				// 'δ������Ԫ�ض���ı༭��������е����ؼ�����
//				int iret = 0;// As Long
//				MyInt rowid = new MyInt(0);// As Long
//				MyInt colid = new MyInt(0);// As Long
//				String sdata = "";// As String
//				Refresh();
//				iret = GetRowIdColumnId(Name, rowid, colid);
//				if (iret == 0) {// Then
//					if (rowid.intvalue != currentRow) {// Then
//						currentRow = rowid.intvalue;
//					}
//				}
//
//				sdata = DW_GetItemString(rowid.intvalue, colid.intvalue);
//				if ((!local_webdw.column[colid.intvalue].format
//						.equals("[general]") && IsNumeric(sdata))) {// Then
//					Tag = "reenter";
//					Text(sdata);// '��ʾ��ǰ���ݵ�δ��ʽ������
//					Tag = "";
//				}
//
//				// '�����ǰ���趨�ı༭����ǵ������ݴ���
//				if (local_webdw.column[colid.intvalue].dddw.Name.length() > 0
//						&& local_webdw.column[colid.intvalue].dddw.DataColumn
//								.length() > 0
//						&& local_webdw.column[colid.intvalue].dddw.DisplayColumn
//								.length() > 0
//						&& local_webdw.column_dddw_syntax[colid.intvalue]
//								.length() > 0
//						&& local_webdw.column_dddw_data[colid.intvalue]
//								.length() > 0) {
//
//					// '����һ���ж��������ж�tabsequance����ֵ
//					if (Locked) {// Then
//						// GoTo end_of_sub
//					} else {
//
//						// '��ʱ���ټ��������ݴ��ڵ�����
//						iret = childDW
//								.DW_SetDataObject(
//										targetControls,
//										childPict,
//										targetPict,
//										local_webdw.column_dddw_syntax[colid.intvalue],
//										False, True);
//						if (iret == -1) {// Then
//							MsgBox(childDW.errString, 0,
//									"childdw setdataobject error");
//							return;
//						}
//						iret = childDW
//								.SetData(local_webdw.column_dddw_data[colid.intvalue]);
//						if (iret == -1) {// Then
//							MsgBox(childDW.errString, 0,
//									"childdw setdata error");
//							return;
//						}
//
//						childDW.parentControlName ( Name);// '���ø����ڵĿؼ�����
//						childDW.dataColumnName ( local_webdw.column[colid.intvalue].dddw.DataColumn);// '��������������
//
//						childPict.Left(Left);
//						childPict.Top(Top + Height);
//						childPict.setVisible(True);
//						childPict.Tag(Name);// '��childpict��tag�ֶ���ʱ�洢�����ڵĵ�ǰ�ؼ���
//						childDW.DrawDW();
//
//						// Dim obj As vscrollbar
//
//						//MyJScrollBar obj = (MyJScrollBar) GF_GetObjectByName(
//						//		targetControls, childPict.Name
//						//				+ "_VScroll_Line");
//						//if (obj != null) {// Not obj Is Nothing Then
//						//	obj.Left(childPict.Left + childPict.Width);
//						//	obj.Top(childPict.Top);
//						//}
//
//						// 'myTextBox.Enabled = False
//					}
//				}
//			}
//		});
	}
	 public native void consoleLog( String message) /*-{
	  	//alert(message);
	  	console.log( "me:" + message );
	  }-*/;	
}
