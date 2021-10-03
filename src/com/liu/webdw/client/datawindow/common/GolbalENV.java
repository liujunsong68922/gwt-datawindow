package com.liu.webdw.client.datawindow.common;

//import javax.swing.JComponent;
//import javax.swing.JTextField;

import com.google.gwt.user.client.ui.Panel;
import com.google.gwt.user.client.ui.Widget;
import com.liu.webdw.client.datawindow.model.syntax.WebDWSyntaxVO;
import com.liu.webdw.client.datawindow.model.viewmodel.ui.MyJTextField;

import java.util.List;

public class GolbalENV extends VBFunction {
	public static final String JWebDWInfo = "    Author: Liujunsong  \r\n"
			+ "    E_Mail: liujunsong@aliyun.com  \r\n"
			+ "    http://webdw.vicp.net  \r\n"
			+ "    Info:If You Can See SourceCode and find bug in it  \r\n"
			+ "    Please contract me.  \r\n";

	public static final String AUTHOR_EMAIL = "liujunsong@aliyun.com";
	public static final String Product_WebSite = "http://webdw.vicp.net";
	
	public void ReadMe() {
		System.out.println(JWebDWInfo);
		System.out.println(AUTHOR_EMAIL);
	}

	public GolbalENV() {
		
	}

	public static Widget GF_GetObjectByName(List targetControls,
			String objName) {
		Widget vobj = null;
		for (int i = 0; i < targetControls.size(); i++) {
			vobj = (Widget) targetControls.get(i);
			if (vobj == null) {
				continue;
			}
			if (vobj.getTitle() == null) {
				continue;
			}
			if (VB_UCase(vobj.getTitle()).equals(VB_UCase(objName))) {
				return vobj;
			}
		}
		return null;
	}



	public double GF_GetConvertRate(List targetControls) {
		double convertRate=0.5;

		return convertRate;
	}


	public long GF_GetDBlength(String sdata) {
		int i = 0;
		long ilen = 0;
		String stemp = "";
		ilen = 0;
		for (i = 1; i <= VB_Len(sdata); i++) {
			stemp = VB_Mid(sdata, i, 1);
			if (VB_Asc(stemp) < 128 && VB_Asc(stemp) > 0) {
				ilen = ilen + 1;
			} else {
				ilen = ilen + 2;
			}

		}
		return ilen;
	}

	public String GF_IF(boolean ifClause, String YesValue, String NoValue) {
		if (ifClause) {
			return YesValue;
		} else {
			return NoValue;
		}
	}

	public int GF_IF_Long(boolean ifClause, int YesValue, int NoValue) {
		if (ifClause) {
			return YesValue;
		} else {
			return NoValue;
		}
	}

	public int GF_GetAlignType(int intype) {
		if (intype == 0) {
			return 0;
		}
		if (intype == 1) {
			return 1;
		}
		if (intype == 2) {
			return 2;
		}
		return 0;
	}

	public void log(String s) {
		g_consoleLog("----> ",s);
	}
	
	public native void g_consoleLog(String title,String message) /*-{
	//alert(message);
	console.log( "["+ title + "]:" + message );
}-*/;

}
