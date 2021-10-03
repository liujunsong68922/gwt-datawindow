package com.liu.webdw.client.datawindow.model.datamodel;

import com.google.gwt.user.client.Window;
import com.liu.webdw.client.datawindow.common.GolbalENV;
import com.liu.webdw.client.datawindow.common.VBInt;


public class CWebDWData extends GolbalENV {
	
	public void ReadMe(){
		System.out.println("WebDW�ؼ���Ӧ�����ݴ洢��������");
		System.out.println(JWebDWInfo);
	}
	public CWebDWData() {

		PrimaryBuffer.BufferType = 1; // 
		FilterBuffer.BufferType = 2; // 
		DeleteBuffer.BufferType = 3; // 
	}

	public CWebDWData_DataBuffer PrimaryBuffer = new CWebDWData_DataBuffer();// '��Buffer����

	public CWebDWData_DataBuffer FilterBuffer = new CWebDWData_DataBuffer();// '����Buffer����

	public CWebDWData_DataBuffer DeleteBuffer = new CWebDWData_DataBuffer();// 'ɾ��Buffer����

	public String errString = "";

	private String data_filter = "";// As String '���ݵĹ�����������

	private String data_sorter = "";// As String '���ݵ�������������

	public int AfterUpdate() {

		// '���DeleteBuffer
		DeleteBuffer.Init();

		int rowid = 0;
		VBInt iret = new VBInt(0);
		for (rowid = 1; rowid <= PrimaryBuffer.RowNumber; rowid++) {
			PrimaryBuffer.SetRowState(rowid, "normal", iret);
		}
		return 0;
	}

	// '����������ɾ��һ��
	// '���崦��ʽ�ǣ���primarybuffer��ɾ��һ�У���������ǰ��
	// '����һ�в��뵽deletebuffer��ȥ�������һ��

	public int DeleteRow(int rowid) {
		String sData = "";
		int newrowid;
		int colid;
		VBInt iret = new VBInt(0);

		sData = "";

		if (rowid > 0 && rowid <= PrimaryBuffer.RowNumber) {

			// '���Ƶ�ǰ����
			sData = PrimaryBuffer.GetRowString(rowid);
			newrowid = DeleteBuffer.InsertRow(0, sData);

			// '�����������ݵ�ԭʼ����
			for (colid = 1; colid <= PrimaryBuffer.ColNumber; colid++) {
				sData = PrimaryBuffer.GetOriginalItemString(rowid, colid, iret);
				if (iret.intvalue == -1) {
					return -1;
				}

				DeleteBuffer
						.SetOriginalItemString(newrowid, colid, sData, iret);

				if (iret.intvalue == -1) {
					return -1;
				}

				sData = PrimaryBuffer.GetRowState(rowid, iret);
				DeleteBuffer.SetRowState(newrowid, sData, iret);
			}

			// 'ɾ��ԭbuffer������
			PrimaryBuffer.DeleteRow(rowid);
		}
		return 0;
	}

	// '����������Webdwdata�����ṩһ��Eval������
	// 'ͨ�������������webdwui_parentdw��ת������
	// '֧�ֵĹ��ܰ���GetDataSorter,SetDataSorter,Sort
	public String Eval(String command, VBInt iret) {

		String arg1 = "";// As String

		if (command.equals("")) {// Then
			iret.intvalue = 0;
			return "0";// = 0
		}

		// '��һ���֣���Sort��ع��ܵ�֧��,����GetSort,SetSort,Sort
		if (VB_UCase(VB_Left(command, VB_Len("GetSort"))).equals(VB_UCase("GetSort"))) {// Then
			iret.intvalue = 0;
			return data_sorter;
		}

		if (VB_UCase(VB_Left(command, VB_Len("SetSort"))).equals(VB_UCase("SetSort"))) {// Then
			arg1 = getOneInputArg(command, iret);
			if (iret.intvalue == -1) {
				return "";
			}
			data_sorter = arg1;
			return "";
		}
		if (VB_UCase(VB_Left(command, VB_Len("Sort"))).equals(VB_UCase("Sort"))) {// Then
			long i = 0;
			i = PrimaryBuffer.Sort(data_sorter);
			if (i == -1) {// Then
				errString = PrimaryBuffer.errString;
			}
			return "";
		}

		// '���ڲ���֧�ֵ��������ͣ�ֱ�ӷ���ʧ��ʧ����Ϣ
		// UnknownCommand:
		iret.intvalue = -1;
		errString = "Unknown Command: " + command;
		return "";
	}

	// '�õ����е��ַ���
	public String GetAllData() {
		return PrimaryBuffer.GetAllData();
	}

	// '�õ��е����
	public int GetColIdByName(String colname) {
		return PrimaryBuffer.GetColIdByName(colname);
	}

	public int GetColumnNumber() {
		return GetColumnNumber(1);
	}

	// '�õ��е�����
	public int GetColumnNumber(int BufferType) {
		switch (BufferType) {
		case 1:
			return PrimaryBuffer.ColNumber;
		case 2:
			return FilterBuffer.ColNumber;
		case 3:
			return DeleteBuffer.ColNumber;
		default:
			return 0;
		}

	}

	// '�õ���ǰ��������ݹ�������
	public String GetDataFilter() {// As String
		return data_filter;
	}// End Function

	public String GetItemString(int row, int col) {
		return GetItemString(row, col, 1);
	}

	// '��������:�õ�ָ�����е�����
	// 'row:�к�
	// 'col:�к�
	// 'buffertype: ��ѡ��buffer���ͣ�Ĭ��Ϊ��������
	public String GetItemString(int row, int col, int BufferType) {
		VBInt iret = new VBInt(0);
		String sret = "";

		// '���ݲ�ͬ��buffertype�����в�ͬ����ĵ���
		switch (BufferType) {
		case 1:
			return PrimaryBuffer.GetItemString(row, col, iret);
		case 2:
			return FilterBuffer.GetItemString(row, col, iret);
		case 3:
			return DeleteBuffer.GetItemString(row, col, iret);
		}
		return "";
	}

	// '��������ַ����У���������ߵ�һ�����ţ��õ�����Ψһ��һ������
	// '������������������������ȥ�����ߵ�����
	// 'iret = 0 �����ɹ�
	// 'iret = -1 ����ʧ��
	private String getOneInputArg(String cmd, VBInt iret) {
		int pos1 = 0;// As Long '������
		int pos2 = 0;// As Long '������
		String stemp = "";// As String '��ʱ����

		pos1 = VB_InStr(cmd, "(");

		// 'pos2ָ���������һ��������
		for (pos2 = VB_Len(cmd); pos2 >= 1; pos2--) {// Step -1
			if (VB_Mid(cmd, pos2, 1).equals(")")) {// Then
				break;
			}
		}

		if (pos1 <= 0 || pos2 <= 0 || pos1 > pos2) {// Then
			iret.intvalue = -1;
			errString = "�����������ʧ��:" + cmd;
			return "";
		}// End If

		stemp = VB_Mid(cmd, pos1 + 1, pos2 - (pos1 + 1));
		stemp = VB_Trim(stemp);

		// 'ȥ��ǰ�������
		if (VB_Left(stemp, 1).equals("\"") && VB_Right(stemp, 1).equals("\"")
				&& VB_Len(stemp) > 1) {// Then
			stemp = VB_Mid(stemp, 2, VB_Len(stemp) - 2);
		}// End If

		// getOneInputArg = stemp
		iret.intvalue = 0;
		return stemp;

	}

	// '�õ��е�����
	public int GetRowCount() {
		return GetRowCount(1);

	}

	// '�õ��е�����
	public int GetRowCount(int BufferType) {
		switch (BufferType) {
		case 1:
			return PrimaryBuffer.RowNumber;
		case 2:
			return FilterBuffer.RowNumber;
		case 3:
			return DeleteBuffer.RowNumber;
		default:
			return 0;
		}
	}

	// '�õ�ָ���е�״̬����
	// '��DataArray�ж�ȡ����Ӧ�е�colnumber + 1�ı����ʹ�����״̬
	public String GetRowState(int row, VBInt iret) {
		return PrimaryBuffer.GetRowState(row, iret);
	}

	// '����DataBuffer�ĵ�ǰ״̬���õ�Ҫ���µ�SQL��䣬
	// '�˴����ص��Ƕ���SQL��䣬�����һ��String��
	// '���SQL���֮����chr(13)chr(10)�����зָ�
	// '���һ�в������س�����
	// '��PrimaryBuffer��DeleteBuffer�м������ݣ�FilterBuffer���漰
	// 'sTableName ���ݱ�����
	// 'iret ���ر�־λ0 ���� -1 ʧ��
	public String GetUpdateSql(String stablename, VBInt iret) {

		String sql1 = "";
		String sql2 = "";

		sql1 = PrimaryBuffer.GetAllUpdateSQL(stablename, iret);
		if (iret.intvalue == -1) {
			return "";
		}

		sql2 = DeleteBuffer.GetAllUpdateSQL(stablename, iret);
		if (iret.intvalue == -1) {
			return "";
		}

		if (!sql1.equals("")) {
			return sql1 + VB_Chr(13) + VB_Chr(10) + sql2;
		} else {
			return sql2;
		}
	}

	public int InitData(String sindata) {
		return InitData(sindata, "normal");
	}

	// '������������������ַ����ж�ȡ���ݣ����columnArray��lineArray
	// '����0 �ɹ�
	// '����-1 ʧ�ܣ�������Ϣ������errString��
	// 'sindata ��������
	// 'state ����״̬,��ѡ�Ĭ��Ϊnormal
	public int InitData(String sindata, String state) {
		
		PrimaryBuffer.InitData(sindata, state);

		String sColumn; // 'sColumnȡsindata��һ��,�����ж���
		sColumn = PrimaryBuffer.GetColumnString();
		
		//Window.alert("sColumn:"+sColumn);
		FilterBuffer.InitData(sColumn);
		DeleteBuffer.InitData(sColumn);
		return 0;

	}

	// '��primarybuffer�в���һ��,��һ�еı��Ϊnew
	public int InsertRow(int rowid, String sData) {
		int i = 0;
		i = PrimaryBuffer.InsertRow(rowid, sData);
		if (i == -1) {
			errString = PrimaryBuffer.errString;
		}
		return i;
	}

	// '����������������е����ݣ�ֻ���������Ľṹ
	public int ResetData() {

		PrimaryBuffer.ResetData();
		FilterBuffer.ResetData();
		DeleteBuffer.ResetData();
		return 0;

	}

	// '���õ�ǰ�����ݹ�������
	// '����0�������óɹ�
	public int SetDataFilter(String Filter) {// As Long
		data_filter = Filter;
		return 0;
	}

	public String SetItemString(int row, int col, String sData) {
		return SetItemString(row, col, sData, 1);
	}

	// '��������������ָ�����е�����
	// 'row: �к�
	// 'col: �к�
	// 'buffertype ��ѡ�Ĭ��Ϊ1
	public String SetItemString(int row, int col, String sData, int BufferType) {

		String sret = "";
		VBInt iret = new VBInt(0);

		if (BufferType == 1) {
			sret = PrimaryBuffer.SetItemString(row, col, sData, iret);
		}
		if (BufferType == 2) {
			sret = FilterBuffer.SetItemString(row, col, sData, iret);
		}

		if (BufferType == 3) {
			sret = DeleteBuffer.SetItemString(row, col, sData, iret);
		}
		return sret;

	}
	//'�õ�column()���ַ�����ʾ,��һ���������������ʼ������dw
	public String GetColumnString() {//As String
	    return PrimaryBuffer.GetColumnString();
	    
	}

}
