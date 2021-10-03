package com.liu.webdw.client.datawindow.model.datamodel;

import com.google.gwt.user.client.Window;
import com.liu.webdw.client.datawindow.common.GolbalENV;
import com.liu.webdw.client.datawindow.common.VBInt;

public class CWebDWData_DataBuffer extends GolbalENV {
	public void ReadMe(){
		System.out.println("WebDW DataBuffer Impletment.");
		System.out.println(JWebDWInfo);
	}
	public CWebDWData_DataBuffer() {
		Init();
	}

	public int BufferType; // 1. Primary 2. Filter 3.Delete

	public String Columns[]; // 

	String DataArray[]; // '

	public String errString; // 

	public int RowNumber; // 

	public int ColNumber; // 

	public int ColLength; //


	public int DeleteRow(int delRowId) {
		// '�ж�delrowid�ĺϷ���
		if (delRowId <= 0 || delRowId > RowNumber) {
			errString = "invalid delete row:" + delRowId;
			return -1;
		}

		int colid = 0; // '�к�
		int rowid = 0; // '�к�

		for (rowid = delRowId; rowid <= RowNumber - 1; rowid++) {
			for (colid = 1; colid <= ColLength; colid++)
				// '������һ�е�����,�����ƶ�״̬
				DataArray[(rowid - 1) * ColLength + colid] = DataArray[rowid
						* ColLength + colid];
		}

		rowid = RowNumber;
		for (colid = 1; colid <= ColLength; colid++) {// '���һ�е��������,������״̬
			DataArray[(rowid - 1) * ColLength + colid] = "";
		}

		RowNumber = RowNumber - 1; // 
		return 0;
	}


	private String GenerateInsertSQL(String stablename, int rowid, VBInt iret) {
		if (BufferType != 1) {
			iret.intvalue = -1;
			errString = "Wrong Buffer Type for Insert:" + BufferType;
			return "";
		}

		if (stablename.equals("")) {
			iret.intvalue = -1;
			errString = "Empty tableName";
			return "";

		}

		String state;
		state = GetRowState(rowid, iret);

		String scollist = "";
		String strsql = "";
		int colid = 0;

		if (iret.intvalue == -1) { // '��ȡ��״̬��������
			return "";
		}

		if (! state.equals("new")) { // '�����½�״̬
			iret.intvalue = -1;
			errString = "Wrong Row State for Insert:" + state;
			return "";
		} else { // '���½�״̬
			scollist = GetColumnList();
			if (scollist.equals("")) {
				iret.intvalue = -1;
				errString = "Empty Column List";
				return "";
			}
			strsql = "Insert Into " + stablename + "(" + scollist + ") Values(";

			for (colid = 1; colid <= ColNumber; colid++) {
				if (colid < ColNumber) {
					strsql = strsql + "'" + GetItemString(rowid, colid, iret)
							+ "',";
				} else {
					strsql = strsql + "'" + GetItemString(rowid, colid, iret)
							+ "')";
				}

				if (iret.intvalue == -1) { // '�����ж�
					return "";
				}
			}
		}
		return strsql;

	}

	public String GetRowState(int row, VBInt iret) {
		// '
		if (row < 1 || row > RowNumber) {
			iret.intvalue = -1;
			errString = "Invalid row:" + row;
			return "";
		}

		iret.intvalue = 0;
		return DataArray[row * ColLength];// '���ÿ��5�У���11�����ݾʹ�����״̬
	}


	private String GetColumnList() {
		int colid = 0;
		String slist = "";
		slist = "";
		for (colid = 0; colid <= ColNumber - 1; colid++) {
			if (colid < ColNumber - 1) {
				slist = slist + Columns[colid] + " , ";
			} else {
				slist = slist + Columns[colid];
			}
		}

		return slist;
	}

	// '�õ�ָ�����е�����
	// 'row �к�
	// 'col �к�
	// 'iret ����ֵ
	public String GetItemString(int row, int col, VBInt iret) {
		// '�ж��е�׼ȷ��
		if (row < 1 || row > RowNumber) {
			iret.intvalue = -1;
			errString = "Invalid row:" + row;
			return "";
		}
		// '�ж��е�׼ȷ��
		if (col < 1 || col > ColNumber) {
			iret.intvalue = -1;
			errString = "Invalid col:" + col;
			return "";
		}

		// '���ؽ������,GetItemStringֻ�ܷ��ص�ǰֵ��Ҫ�õ�ԭʼֵ��Ҫ������������
		iret.intvalue = 0;
		return DataArray[(row - 1) * ColLength + col];

	}

	// '��ĳһ�б�ʾ��һ����׼���ַ���������֮����chr(9)�ָ�
	// '����кŷǷ������ؿ��ַ���
	public String GetRowString(int rowid) {
		if (rowid <= 0 || rowid > RowNumber) {
			errString = "Invalid row:" + rowid;
			return "";
		}

		int colid = 0;// '�к�
		String sret;// '�����ַ���
		sret = "";
		for (colid = 1; colid <= ColNumber; colid++) {
			if (colid < ColNumber) {
				sret = sret + DataArray[(rowid - 1) * ColLength + colid]
						+ VB_Chr(9);
			} else {
				sret = sret + DataArray[(rowid - 1) * ColLength + colid];
			}
		}

		// '�����ַ���
		return sret;
	}

	// '�����е����ݶ���ϳ�һ���ַ�������
	public String GetAllData() {
		String sret = "";
		int rowid = 0;

		sret = "";
		for (rowid = 1; rowid <= RowNumber; rowid++) {
			if (sret.equals("")) {
				sret = GetRowString(rowid);
			} else {
				sret = sret + VB_Chr(13) + VB_Chr(10) + GetRowString(rowid);
			}
		}
		return sret;

	}

	// '�õ������еĸ���SQL������
	public String GetAllUpdateSQL(String stablename, VBInt iret) {
		int rowid = 0;
		String strsql = "";
		String sql1 = "";

		strsql = "";
		for (rowid = 1; rowid <= RowNumber; rowid++) {
			sql1 = GetUpdateSql(stablename, rowid, iret);
			if (iret.intvalue == -1) {
				return "";
			}

			if (!sql1.equals("")) {
				if (strsql.equals("")) {
					strsql = sql1;
				} else {
					strsql = strsql + VB_Chr(13) + VB_Chr(10) + sql1;
				}
			}
		}

		return strsql;
	}

	// '������������������������-1����û�ҵ�
	// '���ص�����Ŵ�1��ʼ����������ź�����ŵ����⣬��Ҫ���¹滮��ͳһ����
	// '���ڴ����е����
	public int GetColIdByName(String colname) {
		int colid = 0;
		for (colid = 0; colid <= ColNumber - 1; colid++) {
			if (VB_UCase(Columns[colid]).equals(VB_UCase(colname))) {
				return colid + 1;
			}
		}
		return -1;
	}

	// '�����е���ţ��õ��е�����
	public String GetColumnName(int colid) {
		if (colid <= ColNumber && colid >= 0) {
			return Columns[colid - 1];
		} else {
			return "";
		}
	}

	// '�õ�column()���ַ�����ʾ,��һ���������������ʼ������dw
	public String GetColumnString() {
		String sret = "";
		int colid = 0;
		for (colid = 1; colid <= ColNumber; colid++) {
			if (colid < ColNumber) {
				sret = sret + Columns[colid - 1] + VB_Chr(9);
			} else {
				sret = sret + Columns[colid - 1];
			}
		}
		return sret;

	}

	// '�õ������ָ�����е�����
	// 'row �к�
	// 'col �к�
	// 'iret ����ֵ
	public String GetOriginalItemString(int row, int col, VBInt iret) {
		// '�ж��е�׼ȷ��
		if (row < 1 || row > RowNumber) {
			iret.intvalue = -1;
			errString = "Invalid row:" + row;
			return "";
		}

		// '�ж��е�׼ȷ��
		if (col < 1 || col > ColNumber) {
			iret.intvalue = -1;
			errString = "Invalid col:" + col;
			return "";
		}

		// '���ؽ������,GetOriginalItemStringֻ�ܷ���ԭʼֵ
		iret.intvalue = 0;
		return DataArray[(row - 1) * ColLength + ColNumber + col];

	}

	// '��ĳһ�е�ԭʼ���ݱ�ʾ��һ����׼���ַ���������֮����chr(9)�ָ�
	// '����кŷǷ������ؿ��ַ���
	public String GetOriginalRowString(int rowid) {
		if (rowid < 1 || rowid > RowNumber) {
			errString = "Invalid row:" + rowid;
			return "";
		}

		int colid = 0;// '�к�
		String sret = "";// '�����ַ���
		sret = "";
		for (colid = 1; colid <= ColNumber; colid++) {
			if (colid < ColNumber) {
				sret = sret
						+ DataArray[(rowid - 1) * ColLength + ColNumber + colid]
						+ VB_Chr(9);
			} else {
				sret = sret
						+ DataArray[(rowid - 1) * ColLength + ColNumber + colid];
			}
		}

		// '�����ַ���
		return sret;
	}

	// '����rowid,���ɸ��º�ɾ��ʱ����Ҫ��Where�־�
	private String GetSetClause(int rowid, VBInt iret) {
		String strSet = "";
		int colid = 0;
		String sData = "";

		strSet = " Set ";
		for (colid = 0; colid <= ColNumber - 1; colid++) {
			sData = GetItemString(rowid, colid + 1, iret);
			if (iret.intvalue == -1) {
				return "";
			}

			if (colid == 0) {
				strSet = strSet + Columns[colid] + " = " + "'" + sData + "' ";
			} else {
				strSet = strSet + " , " + Columns[colid] + " = " + "'" + sData
						+ "' ";
			}
		}

		return strSet;
	}

	// '�õ�ָ��һ�е�Update SQL���
	// '����� new,����һ��Insert���(����primarybuffer��)
	// '����� modify,����һ��Update���(����primarybuffer��)
	// '����� modify,normal ,����һ��Delete���(����deleteBuffer��)
	// 'sTableName Ҫ���µ����ݱ�����
	// 'rowid Ҫ���µ��к�
	// 'iret ���ر�־λ 0 ���� -1 ��������
	public String GetUpdateSql(String stablename, int rowid, VBInt iret) {
		String state = "";
		String strSet = "";
		String strWhere = "";

		// 'part1 primarybuffer�Ĵ���,����״̬����Update��Insert
		if (BufferType == 1) {
			state = GetRowState(rowid, iret);
			if (iret.intvalue == -1) {
				return "";
			}

			// '������������
			if (state.equals("new")) {
				String Sql = GenerateInsertSQL(stablename, rowid, iret);
				if (iret.intvalue == -1) {
					return "";
				}
				return Sql;
			}

			// '������������
			if (state.equals("modify")) {
				strSet = GetSetClause(rowid, iret);
				if (iret.intvalue == -1) {
					return "";
				}

				strWhere = GetWhereClause(rowid, iret);
				if (iret.intvalue == -1) {
					return "";
				}

				return "Update " + stablename + strSet + strWhere;
			}

			return ""; // '����״̬�²�����SQL���,ֱ�ӷ���

		}

		// 'part2 filterBuffer�Ĵ��� //'Ŀǰ�ݲ�����
		if (BufferType == 2) {
			iret.intvalue = 0;
			return ""; // 'Ŀǰ�ݲ�����
		}

		// 'part3 deleteBuffer�Ĵ���
		if (BufferType == 3) {
			state = GetRowState(rowid, iret);// '�����ǰ״̬��new,������
			if (!state.equals("new")) {

				strWhere = GetWhereClause(rowid, iret);
				if (iret.intvalue == -1) {
					return "";
				}

				return " Delete " + stablename + strWhere;
			}
		}

		return "";

	}

	// '����rowid,���ɸ��º�ɾ��ʱ����Ҫ��Where�־�
	private String GetWhereClause(int rowid, VBInt iret) {
		String strWhere = "";
		int colid = 0;
		String sData = "";
		String colwhere = "";

		strWhere = "";
		for (colid = 0; colid <= ColNumber - 1; colid++) {
			sData = GetOriginalItemString(rowid, colid + 1, iret);
			if (iret.intvalue == -1) {
				return "";
			}

			if (sData.equals("")) {// '���ַ�������ֵ����
				colwhere = Columns[colid] + " is null ";
			} else {
				colwhere = Columns[colid] + " = '" + sData + "'";
			}

			if (strWhere.equals("")) {
				strWhere = colwhere;
			} else {
				strWhere = strWhere + " AND " + colwhere;
			}

		}
		if (!strWhere.equals("")) {
			return "Where " + strWhere;
		} else {
			return "";
		}
	}

	// '���Ա��ʼ��
	public int Init() {
		Columns = new String[1];
		DataArray = new String[1001];
		errString = "";
		RowNumber = 0;
		ColNumber = 0;
		ColLength = 0;
		return 0;
	}

	public int InitData(String sindata) {
		return InitData(sindata, "normal");
	}

	// '������������������ַ����ж�ȡ���ݣ����columnArray��lineArray
	// '��һ�����е������������������Ƕ�Ӧ������
	// '���ﲻ����Nullֵ�����ݿ��ϵ�nullֵ��ת��Ϊ���ַ���
	//
	// '����0 �ɹ�
	// '����-1 ʧ�ܣ�������Ϣ������errString��
	// 'sindata �������ݣ��ַ�����ʾ
	// 'state ����״̬��Ĭ��Ϊ"normal"
	public int InitData(String sindata, String state) {
		Init(); // '�������ʼ���ķ���

		String sDataArray[]; // 'ԭʼ���ݰ��зֽ�õ�������
		String vline; // 'ԭʼ����ÿһ��
		String sline = ""; // 'ת�����ַ�����ԭʼ����ÿһ��

		String sdarray[];// '�м������ÿһ�зֽ�������е�����

		int lineId;// 'ԭʼ���ݵ��кţ�0������⣬�����������
		int colid; // '�е����

		DataArray = new String[1]; // '�����ԭʼ����

		sDataArray = VB_Split(sindata, "" + VB_Chr(13) + VB_Chr(10));// '���ûس����Ž��зֽ�
		lineId = 0; // 'ԭʼ���ݵ��к�
		//Window.alert("507,sDataArray.length:"+sDataArray.length);
		
		for (int i = 0; i < sDataArray.length; i++) {
			sline = sDataArray[i];// '����һ��,ת���ַ���
			//Window.alert("line 511:"+sline);
			if (sline.equals("")) {// '�������У��˳�
				break;
			}

			if (lineId == 0) {
				Columns = VB_Split(sline, VB_Chr(9)); // '��chr(9)���ֽ����,�浽columns����
				//Window.alert("Columns.length:"+ Columns.length);
				ColNumber = VB_UBound(Columns) + 1; // '�е�����,split���ص�������zero-based.
				ColLength = ColNumber * 2 + 1; // 'ÿһ�еĳ��ȣ�����colnumber*2��1
			} else {
				if (VB_Trim(sline).equals("")) { // '�������У��˳�ѭ��
					break;
				}

				sdarray = VB_Split(sline, VB_Chr(9)); // '��chr(9)���ֽ���,�ֽ�ɶ��������
				//Window.alert("line 526:"+sdarray.length);
				
				//Window.alert("d1:"+UBound(sdarray));
				//Window.alert("d2:"+UBound(Columns));
				
				if (VB_UBound(sdarray) != VB_UBound(Columns)) { // '����������Ƿ��㹻�����ݲ����򱨴���
					errString = "CWebDWData.ReadData dataformat error, column not enough:"
							+ lineId;
					Window.alert(errString);
					return -1;
				}

				RowNumber = lineId; // '�洢��ǰ����

				if (VB_UBound(DataArray) < RowNumber * ColLength) {
					// ReDim Preserve DataArray(UBound(DataArray) + 1000) //
					// 'һ�η���1000���ռ䣬�����η���
					String temp[] = new String[DataArray.length + 1000];
					for (int j = 1; j < DataArray.length; j++) {
						temp[j] = DataArray[j];
					}
					for (int j = DataArray.length; j < DataArray.length + 1000; j++) {
						temp[j] = "";
					}
					DataArray = temp;
				}

				for (colid = 1; colid <= ColNumber; colid++) {
					// '��nullֵ�����⴦��:����̨���ص�NULL���һ�����ַ���
					if (VB_UCase(sdarray[colid - 1]).equals("NULL")) {
						sdarray[colid - 1] = "";
					}
					DataArray[(lineId - 1) * ColLength + colid] = sdarray[colid - 1]; // '�е����ݴ洢
					DataArray[(lineId - 1) * ColLength + ColNumber + colid] = sdarray[colid - 1]; // '�ٴ洢һ����Ϊ����
				}
				DataArray[lineId * ColLength] = state; // 'initData������������״̬
			}
			lineId = lineId + 1;
		}
		return 0;
	}

	// '������������DataArray�в���һ�м�¼�����м�¼���ַ�������ʾ
	// 'rowid:Ҫ����ĵ�ǰ�кţ������Ժ���һ�����ݽ�����ǰ�У����rowid=0�������׷��
	// 'sline:���ַ�����ʾ��һ�м�¼
	// '����ֵ��>0 ��������Ժ���кţ�-1����ʧ�ܣ�������Ϣ��errString��
	public int InsertRow(int insertid, String sline) {
		String data[];
		int colid = 0;
		int rowid = 0;

		if (insertid < 0 || insertid > RowNumber) {
			errString = "Invalid rowid:" + insertid;
			return -1;
		}

		data = VB_Split(sline, VB_Chr(9));

		if (VB_UBound(data) != ColNumber - 1) {
			errString = "�����������к�Ҫ�󲻷���:" + sline;
			return -1;
		}

		// '�ж��Ƿ���Ҫ��չ�洢����
		if (VB_UBound(DataArray) < (RowNumber + 1) * ColLength) {
			// ReDim Preserve DataArray(UBound(DataArray) + 1000) //
			// 'һ�η���1000���ռ䣬�����η���
			String temp[] = new String[DataArray.length + 1000];
			for (int j = 1; j < DataArray.length; j++) {
				temp[j] = DataArray[j];
			}
			for (int j = DataArray.length; j < DataArray.length + 1000; j++) {
				temp[j] = "";
			}
			DataArray = temp;
		}

		RowNumber = RowNumber + 1;

		if (insertid > 0) {
			// '���ƶ���������,����ƶ�һ��,������״̬
			for (rowid = RowNumber - 1; rowid >= insertid + 1; rowid = rowid - 1) {
				for (colid = 1; colid <= ColLength; colid++) {
					DataArray[rowid * ColLength + colid] = DataArray[(rowid - 1)
							* ColLength + colid];
				}
			}
			rowid = insertid;
		} else {
			rowid = RowNumber;
		}

		for (colid = 1; colid <= ColNumber; colid++) {
			DataArray[(rowid - 1) * ColLength + colid] = "";// '��ʼ��
		}

		// '���Ʋ�����һ�е�����
		for (colid = 1; colid <= ColNumber; colid++) {
			DataArray[(rowid - 1) * ColLength + colid] = data[colid - 1];
		}
		DataArray[rowid * ColLength] = "new";// 'Ĭ������£�����Ϊnew,��������ܻ��޸����ֵ
		return rowid;

	}

	// 
	public int ResetData() {
		DataArray = new String[1000];
		RowNumber = 0;
		return 0;
	}


	public String SetItemString(int row, int col, String sData, VBInt iret) {
		// '�ж��е�׼ȷ��
		if (row < 1 || row > RowNumber) {
			iret.intvalue = -1;
			errString = "Invalid row:" + row;
			return "";
		}

		// '�ж��е�׼ȷ��
		if (col < 1 || col > ColNumber) {
			iret.intvalue = -1;
			errString = "Invalid col:" + col;
			return "";
		}

		iret.intvalue = 0;
		DataArray[(row - 1) * ColLength + col] = sData;// 'ֻ�����õ�ǰֵ����ʷ�����޷�����

		if (DataArray[row * ColLength].equals("normal")) {// 'ֻ�е���ǰ״̬Ϊnormalʱ���޸�Ϊ����
			DataArray[row * ColLength] = "modify";// '��һ��״̬Ϊ����
		}

		return "";
	}


	public String SetOriginalItemString(int row, int col, String sData, VBInt iret) {
		// '�ж��е�׼ȷ��
		if (row < 1 || row > RowNumber) {
			iret.intvalue = -1;
			errString = "Invalid row:" + row;
			return "";
		}

		// '�ж��е�׼ȷ��
		if (col < 1 || col > ColNumber) {
			iret.intvalue = -1;
			errString = "Invalid col:" + col;
			return "";
		}

		iret.intvalue = 0;
		DataArray[(row - 1) * ColLength + ColNumber + col] = sData;// '������ʷ����

		iret.intvalue = 0;
		return "";
	}


	public String SetRowState(int row, String state, VBInt iret) {

		if (row < 1 || row > RowNumber) {
			iret.intvalue = -1;
			errString = "Invalid row:" + row;
			return "";
		}

		iret.intvalue = 0;
		DataArray[row * ColLength] = state;
		return "";

	}

	private long Sort_SwapLine(int row1, int row2) {
		// '�����ж������������ȷ��
		// '����������������ô�Ͳ����в�����
		if (!(row1 > 0 && row1 <= RowNumber && row2 > 0 && row2 <= RowNumber)) {
			return -1;
		}

		int colid = 0;// As Long
		String stemp = "";// As String
		for (colid = 1; colid <= ColLength; colid++) {
			int id = (int) ((row1 - 1) * ColLength) + colid;
			stemp = DataArray[id];
			DataArray[(int) ((row1 - 1) * ColLength) + colid] = DataArray[(row2 - 1)
					* ColLength + colid];
			DataArray[(int) ((row2 - 1) * ColLength) + colid] = stemp;
		}
		return 0;
	}


	private long Sort_Compare(String sorter, int row1, int row2) {
		if (sorter.equals("")) {
			errString = "Compare argument is empty";
			return -1;
		}

		String sorts[] = new String[1];
		int sortid = 0; // As Long
		int sortcolid = 0;// As Long '�����ֶ����
		String sortcoltype = "";// As String '�����ֶ�����A ���� D ����,�����ַ�һ�ɰ�������
		String coldatatype = "";// As String '�е��������Ͷ���
		int pos1 = 0;// As Long '#��λ��
		int pos2 = 0;// As Long '�ո��λ��
		String sdata1 = "";// As String
		String sdata2 = "";// As String

		sorts = VB_Split(sorter, ",");// 'һ�����������зֳɶ����������
		for (sortid = 0; sortid <= VB_UBound(sorts); sortid++) {
			pos1 = VB_InStr(sorts[sortid], "#");
			if (pos1 <= 0) {
				// Sort_Compare = -1
				errString = "Compare argument error";
				return -1;
			}

			pos2 = VB_InStr(pos1, sorts[sortid], " ");// '�ҵ���һ���ո��λ��
			if (pos2 <= 0) {
				// Sort_Compare = -1
				errString = "Compare argument error";
				return -1;
			}

			if (pos2 == pos1 + 1) {
				// Sort_Compare = -1
				errString = "Compare argument error";
			}

			sortcolid = VB_toInt(VB_Mid(sorts[sortid], pos1 + 1, pos2 - (pos1 + 1)));
			sortcoltype = VB_UCase(VB_Trim(VB_Mid(sorts[sortid], pos2 + 1)));

			if (sortcolid < 1 || sortcolid > ColNumber) {
				// Sort_Compare = -1
				errString = "Compare argument error";
				return -1;
			}

			if (sortcoltype.equals("")) {
				sortcoltype = "A";
			} else if ((!(sortcoltype.equals("A")) && (!(sortcoltype
					.equals("D"))))) {
				// Sort_Compare = -1
				errString = "Comapre argument error";
				return -1;
			}

			// '�ж�sortcolid���������ͣ�������ַ�����ֱ�ӱȽ�
			// '�������ֵ�ͣ�ת������ֵ���бȽ�
			// '�����ͬ����ô�Ϳ���ֱ�ӷ����˳������������һ��ѭ�����Ƚ���һ���еĴ�С
			sdata1 = DataArray[(row1 - 1) * ColLength + sortcolid];
			sdata2 = DataArray[(row2 - 1) * ColLength + sortcolid];

			double v1;// As Double
			double v2;// As Double
			if (VB_IsNumeric(sdata1) && VB_IsNumeric(sdata2)) {// Then
				// '�������������ֵ�͵����ݣ���Ƚ�
				v1 = VB_toDouble(sdata1);
				v2 = VB_toDouble(sdata2);
				if (v1 > v2) {
					if (sortcoltype.equals("A")) {// Then
						// Sort_Compare = row2
						// Exit Function
						return row2;
					} else {
						// Sort_Compare = row1
						// Exit Function
						return row1;
					}
				} else if (v1 < v2) {// Then
					if (sortcoltype.equals("A")) {// = "A" Then
						// Sort_Compare = row1
						// Exit Function
						return row1;
					} else {
						// Sort_Compare = row2
						// Exit Function
						return row2;
					}// End If
				}
				// '������ȣ�����ѭ��
			} else
			// '������ֵ��ֱ�ӱȽ�
			if (VB_IsGreat(sdata1, sdata2)) {// Then
				if (sortcoltype.equals("A")) {// = "A" Then
					// Sort_Compare = row2
					// Exit Function
					return row2;
				} else {
					// Sort_Compare = row1
					// Exit Function
					return row1;
				}
			} else if (VB_IsGreat(sdata2, sdata1)) {// Then
				if (sortcoltype.equals("A")) {// = "A" Then
					// Sort_Compare = row1
					// Exit Function
					return row1;
				} else {
					// Sort_Compare = row2
					// Exit Function
					return row2;
				}// End If
			}// End If
			// '������ȣ�����ѭ��
		}// End If

		return row1;// '������ȣ����ص�һ��
	}


	private long Sort_GetMinLine(String sorter, int beginrow, int endrow) {
		if (!(beginrow > 0 && beginrow <= RowNumber && endrow > 0
				&& endrow <= RowNumber && beginrow <= endrow)) {
			// Sort_GetMinLine = -1
			// Exit Function
			return -1;
		}// End If

		int rowid = 0;// As Long
		int irow = 0;// As Long

		irow = beginrow;
		for (rowid = beginrow + 1; rowid <= endrow; rowid++) {
			irow = (int) Sort_Compare(sorter, irow, rowid);
			if (irow == -1) {// Then
				// Sort_GetMinLine = -1
				// Exit Function
				return -1;
			}// End If
		}

		return irow;
	}

	public long Sort(String sorter) {
		if (sorter.equals("")) {
			// Sort = 0
			// Exit Function
			return 0;
		}// End If

		int rowid = 0;// As Long
		int minrowid = 0;// As Long
		for (rowid = 1; rowid <= RowNumber; rowid++) {
			minrowid = (int) Sort_GetMinLine(sorter, rowid, RowNumber);// '�õ��ӵ�ǰ�п�ʼ��С���к�

			if (minrowid == -1) {// Then '������С��ʧ��
				// Sort = -1 '����ʧ��
				// Exit Function
				return -1;
			}// End If

			if (minrowid > rowid) {// Then '�ǵ�ǰ��
				Sort_SwapLine(rowid, minrowid);// '��������
			}// End If
		}// Next

		return 0;
	}

}
