package com.liu.webdw.client.datawindow.view;

import java.util.*;
import com.google.gwt.user.client.Window;
import com.liu.webdw.client.datawindow.common.GolbalENV;
import com.liu.webdw.client.datawindow.common.VBInt;
import com.liu.webdw.client.datawindow.controller.CWebDW;
import com.liu.webdw.client.datawindow.model.datamodel.CWebDWData;
import com.liu.webdw.client.datawindow.model.syntax.WebDWSyntaxVO;
import com.liu.webdw.client.datawindow.model.viewmodel.ChildDW_DymaTextField;
import com.liu.webdw.client.datawindow.model.viewmodel.WebDW_DynamicCheckBox;
import com.liu.webdw.client.datawindow.model.viewmodel.WebDW_DynamicComboBox;
import com.liu.webdw.client.datawindow.model.viewmodel.WebDW_DynamicLabel;
import com.liu.webdw.client.datawindow.model.viewmodel.WebDW_DynamicRadioButton;
import com.liu.webdw.client.datawindow.model.viewmodel.WebDW_DynamicTextField;
import com.liu.webdw.client.datawindow.model.viewmodel.panel.WebDW_AbsolutePanel;
import com.liu.webdw.client.datawindow.view.model.CViewModel;

public class CWebDWUI_ParentDW extends GolbalENV {
	// syntax vo model,this is input
	public CWebDW webdw = new CWebDW();
	
	// webdw data vo model,this is input
	public CWebDWData webdwData = new CWebDWData();
	
	// errString,this is output information
	public String errString = "";
	
	// target Panel.this is output target AbsolutePanel.
	// webdw will draw new UI Element on this targetPanel.
	// webdw using AbsolutePanel ,
	// so that you can adjust widget's position handly.
	WebDW_AbsolutePanel targetPanel;//

	public WebDWSyntaxVO local_webdw() {
		return webdw.getLocalWebDW();
	}

	/**
	 * constructor()
	 */
	public CWebDWUI_ParentDW() {

	}

	public native void consoleLog(String message) /*-{
													//alert(message);
													console.log( "CWebDWUI_ParentDW:" + message );
													}-*/;

	private int _DrawColumn(int lineNum) {
		consoleLog("enter drawColumn,lineNum:" + lineNum);
		int id = 0;
		String sname = "";
		//MyJTextField obj_textfield = null;
		WebDW_DynamicTextField obj_textfield = null;
		int top = 0;
		String svalue = "";
		int beginRowid = 0;
		double convertRate = 0.4;
		String rowstate = "";
		beginRowid = 1;
		VBInt iret = new VBInt(0);
		rowstate = webdwData.GetRowState(lineNum, iret);
		if (iret.intvalue == -1) {
			return -1;
		}
		WebDWSyntaxVO local_webdw = this.local_webdw();
		for (id = 1; id <= 100; id++) {
			if (local_webdw.column[id].Name.equals("")) {
				continue;
			}
			if (lineNum == 0) {
				continue;
			}
			//Window.alert("y:"+local_webdw.column[id].y);
			top = (int) (local_webdw.column[id].y * convertRate) + (int) (local_webdw.header.height * convertRate)
					+ (int) (local_webdw.detail.height * convertRate) * (lineNum - beginRowid);
			if (top >= 0 && top <= local_webdw.header.height * convertRate
					&& !(local_webdw.column[id].band.equals("header"))) {
				continue;
			}
			sname = targetPanel.getName() + "__" + lineNum + "__" + local_webdw.column[id].Name;
			svalue = webdwData.GetItemString(lineNum, id);//
			ArrayList targetControls = targetPanel.getChildtControls();
			obj_textfield = new WebDW_DynamicTextField("", sname, targetControls, targetPanel);
			//Window.alert("top1:"+top);
			obj_textfield.setBounds((int) ((local_webdw.column[id].x * convertRate)),
					(int) (top),
					(int) (local_webdw.column[id].width * convertRate),
					(int) (local_webdw.column[id].height * convertRate));
			obj_textfield.setText(svalue);
			String valuestring = "";
			valuestring = local_webdw.table.Columns[id].values;//

			WebDW_DynamicRadioButton radioobj;
			WebDW_AbsolutePanel frameObj;
			String value[];
			int radioid = 0;
			String radioValue = "";
			String radioDisplay = "";
			int tabpos;
			if (valuestring.length() > 0 && local_webdw.column[id].radiobuttons.Columns > 0) {
				sname = targetPanel.getName() + "__" + lineNum + "__" + local_webdw.column[id].Name + "__" + "Frame";
				obj_textfield.setVisible(false);
				
				frameObj = new WebDW_AbsolutePanel(sname, targetControls, targetPanel);

				frameObj.setBounds(obj_textfield.getX(), obj_textfield.getY(), obj_textfield.getWidth() - (int) (10 * convertRate), obj_textfield.getHeight());

				value = VB_Split(valuestring, "/");
				for (radioid = 0; radioid <= VB_UBound(value); radioid++) {
					if (value[radioid].length() == 0) {
						break;
					}

					tabpos = VB_InStr(1, value[radioid], VB_Chr(9));// 
					if (tabpos > 0) {
						radioDisplay = VB_Left(value[radioid], tabpos - 1);
						radioValue = VB_Mid(value[radioid], tabpos + 1, VB_Len(value[radioid]) - tabpos);
					} else {
						radioDisplay = "";
						radioValue = "";
						break;//
					}
					//
					//sname = targetPanel.getName() + "__" + lineNum + "__" + local_webdw.column[id].Name + "__" + radioValue;
					sname = targetPanel.getName() + "__" + lineNum + "__" + local_webdw.column[id].Name ;
					//Window.alert("radioDisplay:"+radioDisplay);
					//Window.alert("sname:"+sname);
					radioobj = new WebDW_DynamicRadioButton(radioDisplay,radioValue, sname, targetControls, frameObj);
					
					//radioobj.Tag = radioValue;// 
					
					radioobj.setBounds((int) (10 * convertRate), (int) ((30 + 60 * radioid) * convertRate),
							(int) (obj_textfield.getWidth() - 40 * convertRate), (int) (50 * convertRate));
					radioobj.Value(radioValue.equals(svalue));
					if ((local_webdw.column[id].tabsequence == 32766) && !(rowstate.equals("new"))) {
						radioobj.Enabled(false);
					}
				}
			}

			WebDW_DynamicCheckBox myCheckBox;
			if (valuestring.length() > 0 && local_webdw.column[id].checkbox.text.length() > 0) {
				obj_textfield.setVisible(false);
				sname = targetPanel.getName() + "__" + lineNum + "__" + local_webdw.column[id].Name + "__CheckBox";

				myCheckBox = new WebDW_DynamicCheckBox(local_webdw.column[id].checkbox.text, sname, targetControls,
						targetPanel);

				//myCheckBox.Top(obj_textfield.Top);
				//myCheckBox.Left(obj_textfield.Left);
				//myCheckBox.Width(obj_textfield.Width);
				//myCheckBox.Height(obj_textfield.Height);
				myCheckBox.setBounds(obj_textfield.Left, obj_textfield.Top, obj_textfield.Width, obj_textfield.Height);
				
				myCheckBox.Value(GF_IF_Long(local_webdw.column[id].checkbox.on.equals(svalue), 1, 0) == 1);
				if ((local_webdw.column[id].tabsequence == 32766) && !(rowstate.equals("new"))) {
					myCheckBox.Enabled(false);
				}
				
			}

			WebDW_DynamicComboBox myComboBox;
			String combovalues[] = new String[1];
			String combostring;
			int combotabpos;
			String combo_display;
			String combo_value;
			int combo_id;

			if ((valuestring.length() > 0 && (local_webdw.column[id].combobox.allowedit.length() > 0))) {
				sname = targetPanel.getName() + "__" + lineNum + "__" + local_webdw.column[id].Name + "__ComboBox";
				//close original textfield
				obj_textfield.setVisible(false);
				
				myComboBox = new WebDW_DynamicComboBox(sname, targetControls, targetPanel);
				myComboBox.setBounds(obj_textfield.Left, obj_textfield.Top , obj_textfield.Width, obj_textfield.Height);
				
				combovalues = VB_Split(valuestring, "/");
				for (combo_id = 0; combo_id <= VB_UBound(combovalues); combo_id++) {
					if (combovalues[combo_id].equals("")) {
						break;// Exit For
					}

					combotabpos = VB_InStr(1, combovalues[combo_id], VB_Chr(9));
					if (combotabpos > 0) {
						combo_display = VB_Mid(combovalues[combo_id], 1, combotabpos - 1);
						combo_value = VB_Mid(combovalues[combo_id], combotabpos + 1);
						myComboBox.addItem(combo_display);// combo_id
						if (combo_value.equals(svalue)) {// = svalue Then
							myComboBox.setSelectedIndex(combo_id);
						}
					}
				}
			}
		}
		return 0;
	}

	private int _DrawLabel(int lineNum) {
		consoleLog("enter DrawLabel");
		int id = 0;
		String sname = "";
		WebDW_DynamicLabel obj_label = null;
		int top = 0;
		int beginRowid = 0;
		double convertRate = 0.4;
		beginRowid = 1;

		WebDWSyntaxVO local_webdw = this.local_webdw();
		for (id = 1; id <= 100; id++) {
			if (local_webdw.text[id].Name.equals("")) {
				return 0;
			}
			if (lineNum == 0 && (!local_webdw.text[id].band.equals("header"))) {// '����ͷ����band��Ϊheader,�˳�
				continue;
			}
			if (lineNum > 0 && (!local_webdw.text[id].band.equals("detail"))) {// '����ϸ�ڣ�band��Ϊdetail,�˳�
				continue;
			}
			if (local_webdw.text[id].band.equals("header")) {
				top = (int) (local_webdw.text[id].y * convertRate);
			}
			if (local_webdw.text[id].band.equals("detail")) {
				top = (int) (local_webdw.text[id].y * convertRate) + (int) (local_webdw.header.height * convertRate)
						+ (int) (local_webdw.detail.height * convertRate) * (lineNum - beginRowid);
			}
			sname = targetPanel.getName() + "_" + lineNum + "_" + local_webdw.text[id].Name;
			
			//step1:create dynamic label
			//this label will response on click event.
			obj_label = new WebDW_DynamicLabel("", sname, targetPanel);
			
			//step2:set dynamic label bound
			obj_label.setBounds((int) ((local_webdw.text[id].x) * convertRate), (int) (top),
					(int) (local_webdw.text[id].width * convertRate),
					(int) (local_webdw.text[id].height * convertRate));
			
			//step3.set text value of label
			obj_label.setText(local_webdw.text[id].text);
		}
		return 0;
	}

	public int DrawDW() {
		ArrayList targetControls = targetPanel.getChildtControls();
		consoleLog("enter DrawDW");
		if (targetControls == null || targetPanel == null) {
			errString = "Please Call SetDataObject First";
			if (targetControls == null) {
				Window.alert("targetControls is null");
			}
			if (targetPanel == null) {
				Window.alert("targetPict is null");
			}
			Window.alert(errString);
			return -1;
		}
		// remove all project.
		targetPanel.removeObjectList();
		int rowid = 0;//
		int iret = 0;//
		int i = 0;//
		int beginRowid = 1;//

		iret = _DrawLabel(0);
		iret = _DrawColumn(0);
		consoleLog("rowcount:" + webdwData.GetRowCount());
		for (rowid = beginRowid; rowid <= webdwData.GetRowCount(); rowid++) {
			iret = _DrawLabel(rowid);//
			iret = _DrawColumn(rowid);//
		}
		
		//step3. test new CViewModel function.
		CViewModel vm = new CViewModel();
		vm.generateVM(this.local_webdw(), webdwData);
		
		String s1 = vm.toJsonStrig(vm);
		Window.alert(s1);
		consoleLog(s1);
		
		return 0;
	}

	public int AfterUpdate() {
		return webdwData.AfterUpdate();
	}

	public int DW_Create(String dwSyntax) {
		WebDWSyntaxVO local_webdw = this.local_webdw();

		int iret = 0;// As Long
		iret = webdw.CreateBySyntaxString(dwSyntax);

		if (iret == -1) {
			errString = webdw.errString;
			local_webdw = new WebDWSyntaxVO();
			return -1;
		} else {
			local_webdw = webdw.getLocalWebDW();
			return 0;
		}
	}

	public int DW_DeleteRow(int rowid) {
		if (targetPanel == null) {
			errString = "Please Call SetDataObject First.";
			return -1;
		}
		if (rowid <= 0) {
			return 0;
		}
		int iret = 0;// As Long
		iret = webdwData.DeleteRow(rowid);
		if (iret == -1) {
			errString = webdwData.errString;
			return -1;
		}
		if (targetPanel.getCurrentRow() > webdwData.GetRowCount()) {
			targetPanel.setCurrentRow(webdwData.GetRowCount());
		}
		DrawDW();
		return 0;
	}

	public String DW_GetItemString(int rowid, int colid) {
		return webdwData.GetItemString(rowid, colid);
	}

	public int DW_GetRow() {
		if (targetPanel == null) {
			errString = "Please Call SetDataObject First.";
			return -1;
		}

		return targetPanel.getCurrentRow();
	}

	public String DW_GetSQLPreview(VBInt iret) {
		WebDWSyntaxVO local_webdw = this.local_webdw();
		if (targetPanel == null) {
			iret.intvalue = -1;
			errString = "Please Call SetDataObject First.";
			return "";
		}
		String stable = "";//
		if (!local_webdw.table.retrieve.pbselect.table[2].equals("")) {// 'Ŀǰ��֧�ֵ�������Ƕ���˳�
			iret.intvalue = 0;
			return "";
		}
		if (local_webdw.table.retrieve.pbselect.table[1].equals("")) {// '�����һ������Ϊ�գ��˳�
			iret.intvalue = -1;
			errString = "ERROR: no table define";
			return "";
		}
		stable = local_webdw.table.retrieve.pbselect.table[1];
		stable = VB_Replace(stable, "~" + "\"", "");
		return webdwData.GetUpdateSql(stable, iret);
	}

	public int DW_InsertRow(int rowid) {
		if (targetPanel == null) {
			errString = "Please Call SetDataObject First.";
			return -1;
		}
		String emptystring = "";// As String
		int colid = 0;// As Long
		int colNum = 0;// As Long
		colNum = webdwData.GetColumnNumber();
		emptystring = "";
		for (colid = 1; colid <= colNum; colid++) {
			if (emptystring.equals("")) {
				emptystring = " ";
			} else {
				emptystring = emptystring + VB_Chr(9) + "";
			}
		}
		int iret = 0;// As Long
		iret = webdwData.InsertRow(rowid, emptystring);
		if (iret == -1) {
			errString = webdwData.errString;
		}
		DrawDW();
		return iret;
	}

	public int DW_RowCount() {
		if (targetPanel == null) {
			errString = "Please Call SetDataObject First.";
			return -1;
		}
		return webdwData.GetRowCount();
	}

	public int DW_SetDataObject(WebDW_AbsolutePanel targetPictArg, String sUIDesc) {
		consoleLog("enter DW_SetDataObject");
		double convertRate = 0;// As Double
		try {
			if (targetPictArg == null) {
				errString = "Cannot set targetPict,targetPictArg is null.";
				throw new Exception("error");
			}
			targetPanel = targetPictArg;
			
			int iret = 0;
			String columnString = "";// As String
			iret = webdw.CreateBySyntaxString(sUIDesc);
			if (iret == -1) {//
				Window.alert(webdw.errString);
				errString = webdw.errString;
				throw new Exception("error");
			}
			columnString = webdw.GetColumnDefineString();
			consoleLog("columnString:" + columnString);
			iret = webdwData.InitData(columnString);//
			if (iret == -1) {
				errString = webdwData.errString;
				Window.alert(errString);
				throw new Exception("error");
			}
			int maxwidth = 0;// As Long
			maxwidth = (int) (webdw.getMaxWidth() * convertRate);

			targetPanel.setCurrentRow(0);
			DrawDW();//

			return 0;
		} catch (Exception e) {
			e.printStackTrace();
			return -1;
		}
	}

	public int DW_SetItem(int rowid, int colid, String sdata) {
		WebDWSyntaxVO local_webdw = this.local_webdw();
		int iret = 0;// As Long
		String strinfo = "";// As String
		String coltype = "";// As String
		String stemp = "";// As String
		int ColLength = 0;// As Long
		int ipos = 0;// As Long
		int ipos2 = 0;// As Long
		if (local_webdw.column[colid].edit_limit > 0) {
			if (GF_GetDBlength(sdata) > local_webdw.column[colid].edit_limit) {// Then
				VB_MsgBox(strinfo, 1, "WebDW Error");
				return -1;
			}
		} else {
			coltype = local_webdw.table.Columns[colid].type;
			if (VB_InStr(coltype, "char") > 0) {// Then
				ipos = VB_InStr(coltype, "(");
				if (ipos > 0) {// Then
					ColLength = VB_toInt(VB_Mid(coltype, ipos + 1, VB_Len(coltype) - ipos - 1));// '�ַ����Ŀ�ȶ���,ͬ���ݿ��еĳ��ȶ���,pb�Զ�����ά��
					if (GF_GetDBlength(sdata) > ColLength) {// Then
						VB_MsgBox(strinfo, 0, "WebDW Error");
						return -1;
					}
				}
			}
		}

		coltype = local_webdw.table.Columns[colid].type;// '��������
		if ((VB_InStr(coltype, "number") > 0) || (VB_InStr(coltype, "int") > 0) || (VB_InStr(coltype, "long") > 0)
				|| (VB_InStr(coltype, "decimal") > 0)) {// Then
			if (!VB_IsNumeric(sdata)) {// Then
				VB_MsgBox(strinfo, 0, "WebDW Error");
				return -1;
			}
		}

		if (coltype.equals("long") || coltype.equals("int") || coltype.equals("integer")
				|| coltype.equals("smallint")) {// Then
			ipos = VB_InStr(sdata, ".");
			if (ipos > 0) {// Then
				stemp = VB_Mid(sdata, ipos + 1);
				stemp = VB_Trim(stemp);
				if (stemp.length() > 0) {// <> "" Then

					VB_MsgBox(strinfo, 0, "WebDW Error");
					return -1;
				}
			}
		}
		iret = VB_toInt(webdwData.SetItemString(rowid, colid, sdata));
		if (iret == -1) {
			errString = webdwData.errString;
		}
		return iret;
	}

	public int DW_SetRow(int rowid) {
		if (rowid > 0 && rowid <= DW_RowCount()) {
			targetPanel.setCurrentRow(rowid);
			return 1;//
		} else {
			return -1;//
		}
	}

	public int DW_SetSQLSelect(String strsql) {
		WebDWSyntaxVO local_webdw = this.local_webdw();
		local_webdw.SelectSQL = strsql;
		return 0;
	}

	public int DW_Update() {
		if (targetPanel == null) {
			errString = "Please Call SetDataObject First.";
			return -1;
		}
		String strsql = "";// As String
		VBInt iret = new VBInt(0);
		strsql = DW_GetSQLPreview(iret);
		consoleLog("strsql:" + strsql);
		// TODO:将这个String返回回去，让调用者来发起一个RPC调用
		// 在RPC调用完成以后，重新检索并刷新数据
		String cmds[] = new String[1];// ) As String
		cmds = VB_Split(strsql, "" + VB_Chr(13) + VB_Chr(10));
		if (iret.intvalue == -1) {
			return -1;
		}
		webdwData.AfterUpdate();
		return 0;//
	}

	public int GetRowIdColumnId(String currentControlName, VBInt rowid, VBInt colid) {
		if (currentControlName.equals("")) {
			return -1;
		}
		int pos1 = 0;// As Long
		int pos2 = 0;// As Long
		int pos3 = 0;// As Long
		pos1 = VB_InStr(1, currentControlName, "__");//
		if (pos1 <= 0) {// Then
			return -1;
		}
		pos2 = VB_InStr(pos1 + 1, currentControlName, "__");//
		if (pos2 <= 0) {// Then
			return -1;
		}
		pos3 = VB_InStr(pos2 + 1, currentControlName, "__");//
		rowid.intvalue = VB_toInt(VB_Mid(currentControlName, pos1 + 2, pos2 - pos1 - 2));//
		String columnName = "";// As String
		if (pos3 > 0) {
			columnName = VB_Mid(currentControlName, pos2 + 2, pos3 - pos2 - 2);//
		} else {
			columnName = VB_Mid(currentControlName, pos2 + 2);//
		}
		colid.intvalue = webdw.GetColumnIdByColumnName(columnName);//
		return 0;
	}

	public int SetData(String indata) {
		return SetData(indata, "normal");
	}

	public int SetData(String indata, String datastate) {
		consoleLog("enter setdata");
		if (targetPanel == null) {
			errString = "Please Call SetDataObject() first!";
			return -1;
		}
		int iret = webdwData.InitData(indata, datastate);
		if (iret == -1) {
			errString = webdwData.errString;
			return -1;
		}
		if (webdwData.GetRowCount() > 0 && targetPanel.getCurrentRow() == 0) {
			targetPanel.setCurrentRow(1);
		} else {
			targetPanel.setCurrentRow(0);
		}
		return 0;
	}


}