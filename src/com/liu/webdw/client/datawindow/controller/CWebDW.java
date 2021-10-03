package com.liu.webdw.client.datawindow.controller;

import com.liu.webdw.client.datawindow.common.GolbalENV;
import com.liu.webdw.client.datawindow.model.syntax.WebDWSyntaxVO;

public class CWebDW extends GolbalENV {

	public String dwString = "";

	public String errString = "";

	private CWebDW_CreateFromSyntax reader = new CWebDW_CreateFromSyntax();// '����һ����ȡ������

	private WebDWSyntaxVO local_webdw = new WebDWSyntaxVO();// 'local_webdw������һ���ֲ������ˣ�������ȫ�ֱ�����

	public WebDWSyntaxVO getLocalWebDW() {
		return local_webdw;
	}

	public int CreateBySyntaxString(String inString) {
		int iret = 0;// As Long
		iret = reader.Create(inString);

		dwString = inString;
		this.local_webdw = this.reader.GetLocalWebDW();

		if (iret == -1) {
			errString = reader.errString;
		}
		return iret;
	}


	public String GetColumnDefineString() {
		String strcol = "";// As String
		int colid = 0;// As Long
		strcol = "";
		for (colid = 1; colid <= 100; colid++) {// 'g_webdw���������100���У��˴����ɶ�ȡcolumn�������ȡtable.column
			if (local_webdw.table.Columns[colid].Name.equals("")) {
				break;
			}

			if (strcol.equals("")) {
				strcol = strcol + local_webdw.table.Columns[colid].Name;
			} else {
				strcol = strcol + VB_Chr(9)
						+ local_webdw.table.Columns[colid].Name;
			}
		}

		return strcol; // '�����ַ���
	}

	
//	'���ݸ�����columnname�����㷵�ص��б��(1 based)
//	'����local_webdw������õ�
	public int GetColumnIdByColumnName(String colname){
	    int colid =0;
	    for(colid = 1;colid<=100;colid++){
	        if( VB_UCase(colname).equals(VB_UCase(local_webdw.table.Columns[colid].Name))){
	            return colid;
	        }
	    }
	    return -1;
	}

//	'�õ����������ȣ��������������ú����������λ�õ���Ϣ
	public long getMaxWidth() {
	    int i = 0;//As Long
	    long imax = 0;// As Long
	    imax = 0;
	    for( i = 1 ;i<= 100;i++){
	        if( local_webdw.text[i].Name.equals("")){
	            break;
	        }
	        
	        if( local_webdw.text[i].x + local_webdw.text[i].width > imax){
	            imax = local_webdw.text[i].x + local_webdw.text[i].width;
	        }
	    }
	    
	    for( i = 1 ;i<= 100;i++){
	        if(local_webdw.column[i].Name.equals("")){
	            break;
	        }
	        
	        if( local_webdw.column[i].x + local_webdw.column[i].width > imax){
	            imax = local_webdw.column[i].x + local_webdw.column[i].width;
	        }
	    }
	    
	    for( i = 1 ;i<= 100;i++){
	        if( local_webdw.lineinfo[i].Name.equals("")){
	            break;
	        }
	        
	        if( local_webdw.lineinfo[i].x1 > imax){
	        	imax = local_webdw.lineinfo[i].x1;
	        }
	        if( local_webdw.lineinfo[i].x2 > imax){
	        	imax = local_webdw.lineinfo[i].x2;
	        }
	    }
	    
	    return imax;
	}

}
