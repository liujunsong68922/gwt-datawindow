package com.liu.webdw.client.datawindow;

import com.liu.webdw.client.datawindow.common.GolbalENV;
import com.liu.webdw.client.datawindow.common.VBInt;
import com.liu.webdw.client.datawindow.model.viewmodel.panel.WebDW_AbsolutePanel;
import com.liu.webdw.client.datawindow.view.CWebDWUI_ParentDW;

import java.util.*;

/**
 * This is a proxy object on CWebDWUI_ParentDW
 * 
 * @author liujunsong
 *
 */
public class CWebDWUI {
	private CWebDWUI_ParentDW parentClassDW = null;//

	public CWebDWUI() {
		parentClassDW = new CWebDWUI_ParentDW();//
	}

	public String getErrString() {
		return parentClassDW.errString;
	}

	public int After_Update() {
		return parentClassDW.AfterUpdate();
	}

	public int DrawDW() {
		int iret2 = parentClassDW.DrawDW();
		return iret2;
	}

	public int DW_Create(String dwSyntax) {
		int iret2 = parentClassDW.DW_Create(dwSyntax);
		return iret2;
	}

	public int DW_DeleteRow(int rowid) {
		int iret2 = parentClassDW.DW_DeleteRow(rowid);
		return iret2;
	}

	public String DW_GetItemString(int rowid, int colid) {
		return parentClassDW.DW_GetItemString(rowid, colid);
	}

	public int DW_GetRow() {
		int iret2 = parentClassDW.DW_GetRow();
		return iret2;
	}

	public String DW_GetSQLPreview(VBInt iret1) {
		String strsql = parentClassDW.DW_GetSQLPreview(iret1);
		return strsql;
	}

	public int DW_InsertRow(int rowid) {
		int iret2 = parentClassDW.DW_InsertRow(rowid);
		return iret2;
	}

	public int DW_RowCount() {
		int iret2 = parentClassDW.DW_RowCount();
		return iret2;
	}

	public int DW_SetDataObject(WebDW_AbsolutePanel targetPictArg, String sUIDesc) {
		consoleLog("enter DW_SetDataObject");
		int iret2 = parentClassDW.DW_SetDataObject(targetPictArg, sUIDesc);
		return iret2;
	}

	public int DW_SetItem(int rowid, int colid, String sdata) {
		int iret2 = parentClassDW.DW_SetItem(rowid, colid, sdata);
		return iret2;
	}

	public int DW_SetRow(int rowid) {
		int iret2 = parentClassDW.DW_SetRow(rowid);
		return iret2;
	}

	public int DW_SetSQLSelect(String strsql) {
		return parentClassDW.DW_SetSQLSelect(strsql);
	}

	public int DW_Update() {
		int iret2 = parentClassDW.DW_Update();
		return iret2;
	}

	public int GetRowIdColumnId(String currentControlName, VBInt rowid, VBInt colid) {
		int iret2 = parentClassDW.GetRowIdColumnId(currentControlName, rowid, colid);
		return iret2;
	}

	public int SetData(String indata) {
		int iret2 = parentClassDW.SetData(indata);
		return iret2;
	}

	public int SetData(String indata, String datastate) {
		int iret2 = parentClassDW.SetData(indata, datastate);
		return iret2;
	}

	public String GetOutputData() {
		String s1 = parentClassDW.webdwData.GetColumnString();
		s1 = s1 + "\r\n" + parentClassDW.webdwData.GetAllData();
		return s1;
	}

	private void consoleLog(String message) {
		g_consoleLog("CWebDWUI", message);
	}

	public native void g_consoleLog(String title, String message) /*-{
																	//alert(message);
																	console.log( "["+ title + "]:" + message );
																	}-*/;
}
