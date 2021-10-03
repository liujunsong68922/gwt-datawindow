package com.liu.webdw.client.datawindow.view.model;

import java.util.ArrayList;

import com.liu.webdw.client.datawindow.common.GolbalENV;
import com.liu.webdw.client.datawindow.common.VBInt;
import com.liu.webdw.client.datawindow.model.datamodel.CWebDWData;
import com.liu.webdw.client.datawindow.model.syntax.WebDWSyntaxVO;

/**
 * this model is generated by webdwsyntaxvo and datamodel
 * 
 * @author liujunsong
 *
 */
public class CViewModel extends GolbalENV {
	CViewModel_Header header = new CViewModel_Header();
	CViewModel_Detail detail = new CViewModel_Detail();

	/**
	 * 利用输入项目来合并生成一份输出对象
	 * 
	 * @param webdwsynataxvo
	 * @param webdwdata
	 */
	public void generateVM(WebDWSyntaxVO webdwsynataxvo, CWebDWData webdwData) {

		// step1:计算View的header部分
		this._DrawHeaderLabel(0, webdwsynataxvo);

		for (int rowid = 1; rowid <= webdwData.GetRowCount(); rowid++) {
			CViewModel_Detail_Row row = new CViewModel_Detail_Row();
			row.setRowid(rowid);
			//add new row object.
			this.detail.getRows().add(row);
			
			_DrawDetailLabel(rowid, webdwsynataxvo);//
			_DrawDetailColumn(rowid, webdwsynataxvo, webdwData);//
		}
	}

	private int _DrawHeaderLabel(int lineNum, WebDWSyntaxVO local_webdw) {
		consoleLog("enter DrawLabel");
		int id = 0;
		String sname = "";
		int top = 0;
		int beginRowid = 0;
		double convertRate = 0.4;
		beginRowid = 1;

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
			sname = "_" + lineNum + "_" + local_webdw.text[id].Name;

			CViewModel_Header_Label obj_label = new CViewModel_Header_Label(
					(int) ((local_webdw.text[id].x) * convertRate), (int) (top),
					(int) (local_webdw.text[id].width * convertRate),
					(int) (local_webdw.text[id].height * convertRate));

			obj_label.setName(sname);
			obj_label.setText(local_webdw.text[id].text);
			this.header.getLabels().add(obj_label);
		}
		return 0;
	}

	private int _DrawDetailLabel(int lineNum, WebDWSyntaxVO local_webdw) {
		consoleLog("enter DrawLabel");
		int id = 0;
		String sname = "";
		int top = 0;
		int beginRowid = 0;
		double convertRate = 0.4;
		beginRowid = 1;

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
			sname = "_" + lineNum + "_" + local_webdw.text[id].Name;

			CViewModel_Detail_Row_Column obj_label = new CViewModel_Detail_Row_Column(
					(int) ((local_webdw.text[id].x) * convertRate), (int) (top),
					(int) (local_webdw.text[id].width * convertRate),
					(int) (local_webdw.text[id].height * convertRate));

			obj_label.setName(sname);
			obj_label.setText(local_webdw.text[id].text);
			obj_label.setRowid(lineNum);
			obj_label.setColumnid(0);

			CViewModel_Detail_Row row = this.detail.getRows().get(lineNum-1);
			row.getColumns().add(obj_label);
		}
		return 0;
	}

	private int _DrawDetailColumn(int lineNum, WebDWSyntaxVO local_webdw, CWebDWData webdwData) {

		consoleLog("enter drawColumn,lineNum:" + lineNum);
		int id = 0;
		String sname = "";

		CViewModel_Detail_Row_Column obj_textfield = null;
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
		for (id = 1; id <= 100; id++) {
			if (local_webdw.column[id].Name.equals("")) {
				continue;
			}
			if (lineNum == 0) {
				continue;
			}
			top = (int) (local_webdw.column[id].y * convertRate) + (int) (local_webdw.header.height * convertRate)
					+ (int) (local_webdw.detail.height * convertRate) * (lineNum - beginRowid);
			if (top >= 0 && top <= local_webdw.header.height * convertRate
					&& !(local_webdw.column[id].band.equals("header"))) {
				continue;
			}
			sname = "__" + lineNum + "__" + local_webdw.column[id].Name;
			svalue = webdwData.GetItemString(lineNum, id);//

			obj_textfield = new CViewModel_Detail_Row_Column((int) ((local_webdw.column[id].x * convertRate)), (int) (top),
					(int) (local_webdw.column[id].width * convertRate),
					(int) (local_webdw.column[id].height * convertRate));
			obj_textfield.setName(sname);
			obj_textfield.setText(svalue);
			obj_textfield.setType("textfield");
			obj_textfield.setRowid(lineNum);
			obj_textfield.setColumnid(id);
			obj_textfield.setColumndata(svalue);

			//add this object to ViewModel
			CViewModel_Detail_Row row = detail.getRows().get(lineNum-1);
			row.getColumns().add(obj_textfield);
			
			String valuestring = "";
			valuestring = local_webdw.table.Columns[id].values;//
			obj_textfield.setValues(valuestring);
			
			if (valuestring.length() > 0 && local_webdw.column[id].radiobuttons.Columns > 0) {
				//this is a radiobutton defination.
				obj_textfield.setType("radiobutton");
			}

			if (valuestring.length() > 0 && local_webdw.column[id].checkbox.text.length() > 0) {
				sname = "__" + lineNum + "__" + local_webdw.column[id].Name + "__CheckBox";
				//this is a checkbox
				obj_textfield.setType("checkbox");
				obj_textfield.setCheckbox_value(GF_IF_Long(local_webdw.column[id].checkbox.on.equals(svalue), 1, 0) == 1);
			}

			if ((valuestring.length() > 0 && (local_webdw.column[id].combobox.allowedit.length() > 0))) {
				sname = "__" + lineNum + "__" + local_webdw.column[id].Name + "__ComboBox";
				obj_textfield.setText("combobox");

				String combovalues[] = VB_Split(valuestring, "/");
				for (int combo_id = 0; combo_id <= VB_UBound(combovalues); combo_id++) {
					if (combovalues[combo_id].equals("")) {
						break;// Exit For
					}

					int combotabpos = VB_InStr(1, combovalues[combo_id], VB_Chr(9));
					if (combotabpos > 0) {
						//String combo_display = VB_Mid(combovalues[combo_id], 1, combotabpos - 1);
						String combo_value = VB_Mid(combovalues[combo_id], combotabpos + 1);
						if (combo_value.equals(svalue)) {// = svalue Then
							obj_textfield.setSelectedIndex(combo_id);
						}
					}
				}
			}
		}
		return 0;
	}

	public native void consoleLog(String message) /*-{
													//alert(message);
													console.log( "CWebDWUI_ParentDW:" + message );
													}-*/;
	
	public native String toJsonStrig(Object obj) /*-{
	//alert(message);
	return JSON.stringify(obj);
	}-*/;

}