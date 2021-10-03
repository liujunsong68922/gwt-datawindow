package com.liu.webdw.client.datawindow.model.viewmodel;

import java.util.ArrayList;

import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.ClickListener;
import com.google.gwt.user.client.ui.Panel;
import com.google.gwt.user.client.ui.Widget;
import com.liu.webdw.client.datawindow.common.VBInt;
import com.liu.webdw.client.datawindow.model.viewmodel.ui.MyJTextField;
import com.liu.webdw.client.datawindow.view.CWebDWUI_ParentDW;

public class ChildDW_DymaTextField extends MyJTextField {
	private CWebDWUI_ParentDW parentdw;
	
	public CWebDWUI_ParentDW getParentdw() {
		return parentdw;
	}

	public void setParentdw(CWebDWUI_ParentDW parentdw) {
		this.parentdw = parentdw;
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

	public ChildDW_DymaTextField(String stext, String name,
			ArrayList targetControlsArg, Panel parent) {
		super(stext, name, targetControlsArg, parent);
		
		this.addClickListener(new ClickListener() {

			@Override
			public void onClick(Widget sender) {
				Window.alert("You click textfield."+sender.getTitle());
				
			}});
		//this.addActionListener(new ActionListener() {
		//	public void actionPerformed(ActionEvent e) {
				// ����ǵ���,�ж��Ƿ��������ݴ���
				//setCurrentRow();
		//	}
		//});

		// this.addKeyListener(new KeyListener() {
		// public void keyTyped(KeyEvent e) {
		// }
		//
		// public void keyPressed(KeyEvent e) {
		// }
		//
		// public void keyReleased(KeyEvent e) {
		// setCurrentRow();
		// }
		// });

//		this.addMouseListener(new MouseAdapter() {
//			public void mouseClicked(MouseEvent e) {
//				System.out.println("ChildDW mouse Click.");
//				// setCurrentRow();
//				// '���ı�������˫����ʱ���������ڸ����ڵĵ�ǰ�е�ǰ�еĶ�Ӧ��ֵ
//
//				int dataColId = 0;// As Long
//				String selectdata = "";// As String
//
//				MyInt rowid = new MyInt(0);
//				MyInt colid = new MyInt(0);
//				int iret = GetRowIdColumnId(Name, rowid, colid);// '�õ���ǰ�У���ǰ��
//				if (iret==-1){
//					return;
//				}
//				
//				MyInt pcolId = new MyInt(0);// As Long
//				MyInt prowId = new MyInt(0);// As Long
//				//int rowid = 0;// As Long
//				// '�����Ӵ��ڵĿؼ��Ͻ���˫���Ժ����ø����ݴ��ڵĶ�Ӧ���ݵ�����
//				if (parentDW != null && dataColumnName.length() > 0) {// > ""
//					// Then
//					dataColId = webdw
//							.GetColumnIdByColumnName(dataColumnName);// '�õ����������
//
//					//rowid = DW_GetRow();// '�õ���ǰ��
//
//					if (dataColId > 0 && rowid.intvalue > 0) {// Then
//						selectdata = DW_GetItemString(rowid.intvalue, dataColId);// '�õ�ѡ�������
//
//						parentDW.GetRowIdColumnId(parentControlName,
//								prowId, pcolId);// '��ȡ�����ڵ�����,����
//
//						if (prowId.intvalue > 0 && pcolId.intvalue > 0
//								&& Len(selectdata) > 0) {// Then
//							parentDW.DW_SetItem(prowId.intvalue,
//									pcolId.intvalue, selectdata);// '���ø���������
//							parentDW.DrawDW();// '���»���
//						}
//					}
//				}
//			}
//		});
	}
}

