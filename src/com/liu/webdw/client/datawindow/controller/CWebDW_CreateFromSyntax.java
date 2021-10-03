package com.liu.webdw.client.datawindow.controller;

import java.util.ArrayList;

import com.liu.webdw.client.datawindow.common.GolbalENV;
import com.liu.webdw.client.datawindow.common.VBFunction;
import com.liu.webdw.client.datawindow.common.VBInt;
import com.liu.webdw.client.datawindow.model.syntax.WebDWSyntaxVO;
import com.liu.webdw.client.datawindow.model.syntax.WebDW_Column;

public class CWebDW_CreateFromSyntax extends GolbalENV {
	public void ReadMe() {
		System.out.println("This is WebDW Create Function,create a datawindow syntax object by String");
		System.out.println(JWebDWInfo);
	}

	public String errString = ""; //

	private WebDWSyntaxVO local_webdw = new WebDWSyntaxVO();// 'g_webdw������һ���ֲ������ˣ�������ȫ�ֱ�����

	public WebDWSyntaxVO GetLocalWebDW() {
		//Golbal.GG_webdw = local_webdw.Clone();
		return local_webdw;
	}

	public int Create(String dwString) {
		int iflag = 0;

		local_webdw = new WebDWSyntaxVO();

		errString = "";

		if (readWebDW01_Datawindow(dwString) == -1) {
			return -1;
		}

		if (readWebDW02_Header(dwString) == -1) {
			return -1;
		}

		if (readWebDW03_Summary(dwString) == -1) {
			return -1;
		}

		if (readWebDW04_Footer(dwString) == -1) {
			return -1;
		}

		if (readWebDW05_Detail(dwString) == -1) {
			return -1;
		}

		if (readWebDW06_Table(dwString) == -1) {
			return -1;
		}

		if (readWebDW07_Text(dwString) == -1) {
			return -1;
		}

		if (readWebDW08_Column(dwString) == -1) {
			return -1;
		}

		if (readWebDW09_Line(dwString) == -1) {
			return -1;
		}
		// 'return
		return 0;
	}

	private String getElementDesc(String inString, String eleName, int beginPos, VBInt findPos) {
		int iBeg;
		int leftPos;
		int iflag;

		int i;
		String s;

		findPos.intvalue = -1; // '��ʼ��findPos��ֵ��������ı䣬���صľ���-1

		iBeg = VB_InStr(beginPos, inString, eleName);
		if (iBeg <= 0) {
			return ""; // '���ؿ��ַ�������û���ҵ����Ԫ��
		}

		leftPos = VB_InStr(iBeg, inString, "("); // '�õ��������ŵ�λ��
		if (leftPos <= 0) {
			return ""; // '���ؿ��ַ�������û���ҵ����Ԫ��
		}

		iflag = 0; // 'ÿ�ҵ�һ��(��iflag++,�ҵ�һ��) iflag --
		for (i = leftPos + 1; i <= VBFunction.VB_Len(inString); i++) {
			s = VB_Mid(inString, i, 1); // 'ȡ��ǰ�ַ���

			if ((s.equals("(") || s.equals(")")) && VB_Mid(inString, i - 1, 1).equals("~")) { // '�����()����Ҫ�ж��ϸ��ַ��Ƿ���~,����ǲ�����
				continue;
			}

			if (s.equals("(")) {
				iflag = iflag + 1;
				continue; // '��������ѭ��
			}

			if (s.equals(")")) { // '��ǰֵΪ)ʱ��Ҫ�ж�iflag��ֵ
				if (iflag == 0) { // 'iflag=0�����Խ���ѭ��
					String s1 = VB_Mid(inString, leftPos, i - leftPos + 1);
					findPos.intvalue = leftPos; // '�ҵ���λ����leftPos
					return s1;
				} else {
					iflag = iflag - 1; // '����iflag��ȥ1
				}
			}
		}

		return "";
	}

	// '��������ַ������м��
	// '�����"��ͷ����"����,��ô��ɾ����ǰ�����������
	private String removeQuote(String strIn) {
		int ilen;
		ilen = VB_Len(strIn);

		if (strIn.equals("")) {
			return "";
		}
		if (VB_Left(strIn, 1).equals("\"") && VB_Right(strIn, 1).equals("\"")) {
			return VB_Mid(strIn, 2, ilen - 2);
		}
		return strIn;
	}

	// '��Ԫ�ر�ʾ���ַ������棬�����Ű���������
	// 'ȡ��ָ�������Ե�ʵ������ֵ
	// '����Ҳ������򷵻�һ�����ַ���
	// 'retFlag��һ����־�ַ���������0�����ҵ��ˣ�����-1����û�����ָ�����ƵĲ���
	// 'eleString ֻ��
	// 'paraName ֻ������Сд���У�
	// 'begPos ��ʼ���ҵ�
	// 'defValue �Ҳ���ʱ���Ĭ��ֵ
	// 'retFlag �������0����ɹ�����-1����ʧ��
	// 'sep �����ķָ���ţ������˷��Ŵ������

	private String getElementProp2(String eleString, String paraName, int begPos, String defValue, VBInt retFlag,
			String sep) {
		int iBeg;
		int iEnd;
		int ipos;
		int i;
		int iflag;
		String s;
		String svalue;

		retFlag.intvalue = -1;
		ipos = VB_InStr(begPos, eleString, paraName + "="); // '�ҵ��������ƵĿ�ʼ��
		if (ipos <= 0) { // '�Ҳ������˳�
			return defValue; // '����Ĭ��ֵ
		}

		iBeg = ipos + VB_Len(paraName + "="); // 'iBeg����ֵ�Ŀ�ʼ��
		iflag = 0;
		for (i = iBeg; i <= VB_Len(eleString); i++) {
			s = VB_Mid(eleString, i, 1);

			if (s.equals("\"")) { // '�����ǰ�ַ��������ţ���ô���ñ�־
				if (iflag == 0) {
					iflag = 1;
				} else {
					iflag = 0;
				}
				continue;
			}

			if (s.equals(sep)) { // '���s�ǽ������ţ���Ҫ����iFlag���ж�
				if (iflag == 0) { // '��������ַ����ڣ���ô���˳�
					svalue = VB_Mid(eleString, iBeg, i - iBeg);
					svalue = removeQuote(svalue); // 'ȥ����ͷ�ͽ�β������
					// getElementProp2 = svalue
					retFlag.intvalue = 0; // '��־�ɹ�����
					return svalue; // '�˳��˹���
				}
			}

		}

		return defValue; // '����Ĭ��ֵ
	}

	// '��Ԫ�ر�ʾ���ַ������棬�����Ű���������
	// 'ȡ��ָ�������Ե�ʵ������ֵ
	// '����Ҳ������򷵻�һ�����ַ���
	// 'retFlag��һ����־�ַ���������0�����ҵ��ˣ�����-1����û�����ָ�����ƵĲ���
	// 'eleString ֻ��
	// 'paraName ֻ������Сд���У�
	// 'begPos ��ʼ���ҵ�
	// 'defValue �Ҳ���ʱ���Ĭ��ֵ
	// 'retFlag �������0����ɹ�����-1����ʧ��
	private String getElementProp(String eleString, String paraName, int begPos, String defValue, VBInt retFlag) {
		String svalue;
		svalue = getElementProp2(eleString, paraName, begPos, defValue, retFlag, " ");
		return svalue;
	}

	// '������������������ַ����зֳɰ������ʵ��Ԫ�ص�array����
	// 'ֻ��ȡ���е�ָ�����Ͷ���
	private ArrayList getAllElement(String inString, String eletype) {
		ArrayList myarray = new ArrayList();
		String stext = "";
		VBInt ipos = new VBInt(0);

		// '�ֽ�dwString�������е�Ԫ��ȡ������������myarray��ȥ

		stext = getElementDesc(inString, eletype + "(", 1, ipos);
		while (ipos.intvalue > 0) {
			myarray.add(stext); // '�����ڼ���sText
			stext = getElementDesc(inString, eletype + "(", ipos.intvalue + 1, ipos);
		}

		return myarray;
	}

	// '����datawindow���ֵ�����
	// '����0����ɹ�
	// '����-1����ʧ��
	private int readWebDW01_Datawindow(String dwString) {
		String sDataWindow;
		VBInt ipos = new VBInt(0);
		VBInt iflag = new VBInt(0);

		// '�õ�datawindow�ı�ʾ
		sDataWindow = getElementDesc(dwString, "datawindow", 1, ipos);

		// '���������datawindow����,��Ϊ��������,ִֹͣ��
		if (ipos.intvalue < 0) {
			errString = "ERROR:readWebDW01_Datawindow: Cannot find datawindow";
			return -1;
		}

		// '�������datawindow����,��ô��������������
		// '��ʹû��,Ҳֱ������ȥ,��������,ֱ������һ��Ĭ��ֵ
		local_webdw.datawindow.unit = getElementProp(sDataWindow, "unit", 1, "0", iflag);
		local_webdw.datawindow.timer_interval = getElementProp(sDataWindow, "time_interval", 1, "0", iflag);
		local_webdw.datawindow.color = VB_toInt(getElementProp(sDataWindow, "color", 1, "0", iflag));
		local_webdw.datawindow.processiong = getElementProp(sDataWindow, "processiong", 1, "", iflag);
		local_webdw.datawindow.HTMLDW = getElementProp(sDataWindow, "HTMLDW", 1, "no", iflag);
		local_webdw.datawindow.print_documentname = getElementProp(sDataWindow, "print.documentname", 1, "", iflag);
		local_webdw.datawindow.print_orientation = VB_toInt(
				getElementProp(sDataWindow, "print.orientation", 1, "0", iflag));
		local_webdw.datawindow.print_margin_left = VB_toInt(
				getElementProp(sDataWindow, "print.margin.left", 1, "110", iflag));
		local_webdw.datawindow.print_margin_right = VB_toInt(
				getElementProp(sDataWindow, "print.margin.right", 1, "110", iflag));
		local_webdw.datawindow.print_margin_top = VB_toInt(
				getElementProp(sDataWindow, "print.margin.top", 1, "96", iflag));
		local_webdw.datawindow.print_margin_bottom = VB_toInt(
				getElementProp(sDataWindow, "print.margin.bottom", 1, "96", iflag));
		local_webdw.datawindow.print_paper_source = VB_toInt(
				getElementProp(sDataWindow, "print.paper.source", 1, "0", iflag));
		local_webdw.datawindow.print_paper_size = VB_toInt(getElementProp(sDataWindow, "print.paper.size", 1, "0", iflag));
		local_webdw.datawindow.print_prompt = getElementProp(sDataWindow, "print.prompt", 1, "no", iflag);
		local_webdw.datawindow.print_buttons = getElementProp(sDataWindow, "print.buttons", 1, "no", iflag);
		local_webdw.datawindow.print_preview_buttons = getElementProp(sDataWindow, "print.preview.buttons", 1, "no",
				iflag);
		local_webdw.datawindow.grid_lines = getElementProp(sDataWindow, "grid.lines", 1, "-1", iflag);
		return 0;
	}

	// '����header���ֵ�����
	// '����0����ɹ�
	// '����-1����ʧ��
	private int readWebDW02_Header(String dwString) {
		String sHeader = "";
		VBInt ipos = new VBInt(0);
		VBInt iflag = new VBInt(0);

		// '�õ�datawindow�ı�ʾ
		sHeader = getElementDesc(dwString, "header", 1, ipos);

		// '����Ҳ���header���壬���˳�
		if (ipos.intvalue < 0) {
			return 0;
		}

		local_webdw.header.height = VB_toInt(getElementProp(sHeader, "height", 1, "0", iflag));
		local_webdw.header.color = VB_toInt(getElementProp(sHeader, "color", 1, "0", iflag));
		return 0;

	}

	private int readWebDW03_Summary(String dwString) {
		String sSummary = "";
		VBInt ipos = new VBInt(0);
		VBInt iflag = new VBInt(0);

		// '�õ�summary�ı�ʾ
		sSummary = getElementDesc(dwString, "summary", 1, ipos);

		// '����Ҳ���summary���壬���˳�
		if (ipos.intvalue < 0) {
			errString = "ERROR:readWebDW03_Summary:���ݸ�ʽ���Ϸ�:�Ҳ���summary����(����)";
			return -1;
		}

		local_webdw.summary.height = VB_toInt(getElementProp(sSummary, "height", 1, "0", iflag));
		local_webdw.summary.color = VB_toInt(getElementProp(sSummary, "color", 1, "0", iflag));
		return 0;
	}

	private int readWebDW04_Footer(String dwString) {
		String sFooter = "";
		VBInt ipos = new VBInt(0);
		VBInt iflag = new VBInt(0);

		// '�õ�footer�ı�ʾ
		sFooter = getElementDesc(dwString, "footer", 1, ipos);

		// '����Ҳ���footer���壬���˳�
		if (ipos.intvalue < 0) {
			errString = "ERROR:readWebDW04_Footer:���ݸ�ʽ���Ϸ�:�Ҳ���footer����(����)";
			return -1;
		}

		local_webdw.footer.height = VB_toInt(getElementProp(sFooter, "height", 1, "0", iflag));
		local_webdw.footer.color = VB_toInt(getElementProp(sFooter, "color", 1, "0", iflag));
		return 0;
	}

	// '����detail���ֵ�����
	// '����0����ɹ�
	// '����-1����ʧ��
	private int readWebDW05_Detail(String dwString) {
		String sDetail = "";
		VBInt ipos = new VBInt(0);
		VBInt iflag = new VBInt(0);

		// '�õ�footer�ı�ʾ
		sDetail = getElementDesc(dwString, "detail", 1, ipos);

		// '����Ҳ���footer���壬���˳�
		if (ipos.intvalue < 0) {
			errString = "ERROR:readWebDW05_Detail:���ݸ�ʽ���Ϸ�:�Ҳ���detail����(����)";
			return -1;
		}
		local_webdw.detail.height = VB_toInt(getElementProp(sDetail, "height", 1, "0", iflag));
		local_webdw.detail.color = VB_toInt(getElementProp(sDetail, "color", 1, "0", iflag));
		return 0;
	}

	private int readWebDW06_Table(String dwString) {
		String stable = "";
		VBInt ipos = new VBInt(0);
		VBInt iflag = new VBInt(0);
		ArrayList columnArray = new ArrayList();
		Object obj;
		String stemp;
		int id = 0;

		// '�õ�footer�ı�ʾ
		stable = getElementDesc(dwString, "table", 1, ipos);

		// '����Ҳ���footer���壬���˳�
		if (ipos.intvalue < 0) {
			errString = "ERROR:readWebDW06_Table:���ݸ�ʽ���Ϸ�:�Ҳ���table����(����)";
			return -1;

		}

		// '�ȶ�ȡcolumn����
		columnArray = getAllElement(stable, "column=");
		id = 0;
		for (int i = 0; i < columnArray.size(); i++) {
			stemp = (String) columnArray.get(i);

			id = id + 1;
			if (id > 100) {
				errString = "ERROR:readWebDW06_Table:SELECT���г���100��!";
				return -1;
			}

			local_webdw.table.Columns[id].type = getElementProp(stemp, "type", 1, "", iflag);
			local_webdw.table.Columns[id].update = getElementProp(stemp, "update", 1, "yes", iflag);
			local_webdw.table.Columns[id].updatewhereclause = getElementProp(stemp, "updatewhereclause", 1, "yes",
					iflag);
			local_webdw.table.Columns[id].key = getElementProp(stemp, "key", 1, "", iflag);
			local_webdw.table.Columns[id].Name = getElementProp(stemp, "name", 1, "", iflag);
			local_webdw.table.Columns[id].dbname = getElementProp(stemp, "dbname", 1, "", iflag);
			local_webdw.table.Columns[id].values = getElementProp(stemp, "values", 1, "", iflag);

		}

		// '��ȡretrieve����ֵ
		// 'g_webdw.table.retrieve = getElementProp(stable, "retrieve", 1, "",
		// iflag)

		// 'update,updatewhere,updatekeyinplace
		local_webdw.table.update = getElementProp(stable, "update", 1, "", iflag);
		local_webdw.table.updatewhere = getElementProp(stable, "updatewhere", 1, "", iflag);
		local_webdw.table.updatekeyinplace = getElementProp(stable, "updatekeyinplace", 1, "", iflag);

		// '��retrieve�ַ��������Զ�ȡ����Ӧ�ı�����ȥ
		// 'retrieve���ڲ���һ������������һ���ṹ���ˡ�
		String strPBSelect = "";
		String Columns[] = new String[101]; // '����Ҫ��ȡ��column�����ƣ����column�����ݿ������
		String tables[] = new String[11]; // '����Ҫ��ȡ��table���ƣ����10��
		ArrayList temparray = new ArrayList();
		String tempobj = "";
		VBInt iret = new VBInt(0);

		String select_tablelist = ""; // 'tabel �Ӿ�
		String select_columnlist = ""; // 'column �Ӿ�
		String select_join = ""; // 'join �����Ӿ�
		String select_where = ""; // 'where�Ӿ�

		String stablename = "";
		int table_id = 0;
		String scolumnname = "";
		int column_id = 0;

		int join_id = 0;
		String join_left = "";
		String join_op = "";
		String join_right = "";

		int where_id = 0;
		String exp1 = "";
		String where_op = "";
		String exp2 = "";
		String logic = "";

		int order_id = 0;// As Long
		String order_name = "";// As String
		String order_asc = "";// As String

		strPBSelect = getElementProp(stable, "retrieve", 1, "", iflag); // '�ȶ�ȡretrieve���Գ���

		// '��ȡtable����
		temparray = getAllElement(strPBSelect, "TABLE"); // '�õ�TABLEԪ�صĶ���
		table_id = 0;
		for (int i = 0; i < temparray.size(); i++) {
			stemp = (String) temparray.get(i);
			stablename = getElementProp2(stemp, "NAME", 1, "", iret, ")"); // '����)��Ϊ�����ָ����

			if (iret.intvalue == -1) {
				continue;
			}

			table_id = table_id + 1;
			if (table_id > 10) {
				break;
			}
			local_webdw.table.retrieve.pbselect.table[table_id] = stablename; // '�洢tableName��ֵ

		}

		// '��ȡcolumn����
		temparray = getAllElement(strPBSelect, "COLUMN"); // '�õ�columnԪ�ض���
		column_id = 0;
		for (int i = 0; i < temparray.size(); i++) {
			stemp = (String) temparray.get(i);
			scolumnname = getElementProp2(stemp, "NAME", 1, "", iret, ")");

			if (iret.intvalue == -1) {
				continue;
			}

			column_id = column_id + 1;
			if (column_id > 100) {
				break;
			}
			local_webdw.table.retrieve.pbselect.column[column_id] = scolumnname; // '�洢column��NAME
		}

		// '��ȡjoin����
		temparray = getAllElement(strPBSelect, "JOIN "); // '�õ�JOINԪ�ض��壬������һ���ո�
		join_id = 0;
		for (int i = 0; i < temparray.size(); i++) {
			stemp = (String) temparray.get(i);
			join_left = getElementProp2(stemp, "LEFT", 1, "", iret, " ");
			if (iret.intvalue == -1) {
				continue;
			}

			join_op = getElementProp2(stemp, "OP", 1, "", iret, " ");
			if (iret.intvalue == -1) {
				continue;
			}

			join_right = getElementProp2(stemp, "RIGHT", 1, "", iret, " ");
			if (iret.intvalue == -1) {
				continue;
			}

			join_id = join_id + 1;
			if (join_id > 10) {
				break;
			}
			local_webdw.table.retrieve.pbselect.join[join_id].join_left = join_left;
			local_webdw.table.retrieve.pbselect.join[join_id].join_op = join_op;
			local_webdw.table.retrieve.pbselect.join[join_id].join_right = join_right;
		}

		// '��ȡwhere����
		temparray = getAllElement(strPBSelect, "WHERE"); // '�õ�WhereԪ�ض���
		where_id = 0;
		for (int i = 0; i < temparray.size(); i++) {
			stemp = (String) temparray.get(i);
			exp1 = getElementProp2(stemp, "EXP1", 1, "", iret, " ");
			if (iret.intvalue == -1) {
				continue;
			}

			where_op = getElementProp2(stemp, "OP", 1, "", iret, " ");
			if (iret.intvalue == -1) {
				continue;
			}

			exp2 = getElementProp2(stemp, "EXP2", 1, "", iret, " ");
			if (iret.intvalue == -1) {
				continue;
			}

			logic = getElementProp2(stemp, "LOGIC", 1, "", iret, " ");

			where_id = where_id + 1;
			if (where_id > 10) {
				break;
			}
			local_webdw.table.retrieve.pbselect.where[where_id].exp1 = exp1;
			local_webdw.table.retrieve.pbselect.where[where_id].op = where_op;
			local_webdw.table.retrieve.pbselect.where[where_id].exp2 = exp2;
			local_webdw.table.retrieve.pbselect.where[where_id].logic = logic;

		}

		// '��ȡorder����
		// Set temparray = getAllElement(strPBSelect, "ORDER") '�õ�orderԪ�ض���
		// order_id = 0
		// For Each tempobj In temparray
		// stemp = tempobj
		// order_name = getElementProp2(stemp, "NAME", 1, "", iret, " ")
		// If iret = -1 Then GoTo continue5
		//
		// order_asc = getElementProp2(stemp, "ASC", 1, "yes", iret, " ") 'Ĭ��Ϊ����
		// If iret = -1 Then GoTo continue5
		//
		// order_id = order_id + 1
		//
		// If order_id > 10 Then
		// Exit For
		// End If
		//
		// local_webdw.table.retrieve.pbselect.order(order_id).Name = order_name
		// local_webdw.table.retrieve.pbselect.order(order_id).Asc = order_asc
		// continue5:
		// Next

		// '��ȡorder����
		temparray = getAllElement(strPBSelect, "ORDER");// '�õ�orderԪ�ض���
		order_id = 0;
		for (int i = 0; i < temparray.size(); i++) {
			stemp = (String) temparray.get(i);
			order_name = getElementProp2(stemp, "NAME", 1, "", iret, " ");
			if (iret.intvalue == -1) {
				continue;
			}

			order_asc = getElementProp2(stemp, "ASC", 1, "yes", iret, " ");// 'Ĭ��Ϊ����
			if (iret.intvalue == -1) {
				continue;
			}

			order_id = order_id + 1;

			if (order_id > 10) {
				break;
			}

			local_webdw.table.retrieve.pbselect.order[order_id].Name = order_name;
			local_webdw.table.retrieve.pbselect.order[order_id].Asc = order_asc;
		}

		return 0;
	}

	// '�������е�text������
	// '����0����ɹ�
	// '����-1����ʧ��

	private int readWebDW07_Text(String dwString) {
		ArrayList textArray = new ArrayList();
		Object obj;
		String stemp = "";
		int id = 0;
		VBInt iret = new VBInt(0);

		textArray = getAllElement(dwString, "text");
		id = 0;
		// 'ѭ������
		for (int i = 0; i < textArray.size(); i++) {
			stemp = (String) textArray.get(i);
			if (VB_Len(stemp) < 10) {
				continue;
			}

			id = id + 1;

			if (id > 100) {
				errString = "ERROR:readWebDW07_Text:���ɶ�ȡ100����ǩ!";
				return -1;
			}

			local_webdw.text[id].band = getElementProp(stemp, "band", 1, "detail", iret);
			local_webdw.text[id].alignment = VB_toInt(getElementProp(stemp, "alignment", 1, "1", iret));
			local_webdw.text[id].text = getElementProp(stemp, "text", 1, "", iret);
			local_webdw.text[id].border = VB_toInt(getElementProp(stemp, "border", 1, "0", iret));
			local_webdw.text[id].color = VB_toInt(getElementProp(stemp, "color", 1, "0", iret));
			local_webdw.text[id].x = VB_toInt(getElementProp(stemp, "x", 1, "0", iret));
			local_webdw.text[id].y = VB_toInt(getElementProp(stemp, "y", 1, "0", iret));
			local_webdw.text[id].height = VB_toInt(getElementProp(stemp, "height", 1, "0", iret));
			local_webdw.text[id].width = VB_toInt(getElementProp(stemp, "width", 1, "0", iret));
			local_webdw.text[id].Name = getElementProp(stemp, "name", 1, "", iret);

			// '��������������
			local_webdw.text[id].font.face = getElementProp(stemp, "font.face", 1, "", iret);
			local_webdw.text[id].font.height = VB_toInt(getElementProp(stemp, "font.height", 1, "", iret));
			local_webdw.text[id].font.weight = VB_toInt(getElementProp(stemp, "font.weight", 1, "", iret));
			local_webdw.text[id].font.family = VB_toInt(getElementProp(stemp, "font.family", 1, "", iret));
			local_webdw.text[id].font.pitch = VB_toInt(getElementProp(stemp, "font.pitch", 1, "", iret));
			local_webdw.text[id].font.charset = VB_toInt(getElementProp(stemp, "font.charset", 1, "", iret));
			local_webdw.text[id].font.italic = VB_toInt(getElementProp(stemp, "font.italic", 1, "0", iret));
			local_webdw.text[id].font.underline = VB_toInt(getElementProp(stemp, "font.underline", 1, "0", iret));
			local_webdw.text[id].font.strikethrough = VB_toInt(getElementProp(stemp, "font.strikethrough", 1, "0", iret));

			// '��������ɫ����
			local_webdw.text[id].background_mode = VB_toInt(getElementProp(stemp, "background.mode", 1, "", iret));
			local_webdw.text[id].background_color = VB_toInt(getElementProp(stemp, "background.color", 1, "", iret));
		}
		return 0;
	}

	// '�������е�column������
	// '����0����ɹ�
	// '����-1����ʧ��
	private int readWebDW08_Column(String dwString) {
		ArrayList columnArray = new ArrayList();
		Object obj;
		String sColumn;
		int id;
		VBInt iret = new VBInt(0);
		WebDW_Column temp_webdw_column = new WebDW_Column();

		columnArray = getAllElement(dwString, "column");
		id = 0;
		// 'ѭ������
		for (int i = 0; i < columnArray.size(); i++) {
			sColumn = (String) columnArray.get(i);
			id = id + 1;

			if (id > 100) {
				errString = "ERROR:readWebDW08_Column:���ɶ�ȡ100����!";
				return -1;
			}

			temp_webdw_column = new WebDW_Column();
			temp_webdw_column.band = getElementProp(sColumn, "band", 1, "1", iret);
			temp_webdw_column.id = VB_toInt(getElementProp(sColumn, "id", 1, "0", iret));
			temp_webdw_column.alignment = VB_toInt(getElementProp(sColumn, "alignment", 1, "1", iret));
			temp_webdw_column.tabsequence = VB_toInt(getElementProp(sColumn, "tabsequence", 1, "0", iret));
			temp_webdw_column.border = VB_toInt(getElementProp(sColumn, "border", 1, "1", iret));
			temp_webdw_column.color = VB_toInt(getElementProp(sColumn, "color", 1, "0", iret));

			temp_webdw_column.x = VB_toInt(getElementProp(sColumn, "x", 1, "0", iret));
			temp_webdw_column.y = VB_toInt(getElementProp(sColumn, "y", 1, "0", iret));
			temp_webdw_column.height = VB_toInt(getElementProp(sColumn, "height", 1, "0", iret));
			temp_webdw_column.width = VB_toInt(getElementProp(sColumn, "width", 1, "0", iret));
			temp_webdw_column.format = getElementProp(sColumn, "format", 1, "", iret);
			temp_webdw_column.Name = getElementProp(sColumn, "name", 1, "", iret);
			temp_webdw_column.tag = getElementProp(sColumn, "tag", 1, "", iret);

			// '�����Ǳ༭���֧��
			temp_webdw_column.edit_limit = VB_toInt(getElementProp(sColumn, "edit.limit", 1, "0", iret));
			temp_webdw_column.edit_case = getElementProp(sColumn, "edit.case", 1, "any", iret);
			temp_webdw_column.edit_focusrectangle = getElementProp(sColumn, "edit.focusrectangle", 1, "no", iret);
			temp_webdw_column.edit_autoselect = getElementProp(sColumn, "edit.autoselect", 1, "no", iret);
			temp_webdw_column.edit_autohscroll = getElementProp(sColumn, "edit.autohscroll", 1, "yes", iret);

			// '����������֧��
			temp_webdw_column.font.face = getElementProp(sColumn, "font.face", 1, "", iret);
			temp_webdw_column.font.height = VB_toInt(getElementProp(sColumn, "font.height", 1, "", iret));
			temp_webdw_column.font.weight = VB_toInt(getElementProp(sColumn, "font.weight", 1, "", iret));
			temp_webdw_column.font.family = VB_toInt(getElementProp(sColumn, "font.family", 1, "", iret));
			temp_webdw_column.font.pitch = VB_toInt(getElementProp(sColumn, "font.pitch", 1, "", iret));
			temp_webdw_column.font.charset = VB_toInt(getElementProp(sColumn, "font.charset", 1, "", iret));
			temp_webdw_column.font.italic = VB_toInt(getElementProp(sColumn, "font.italic", 1, "0", iret));
			temp_webdw_column.font.underline = VB_toInt(getElementProp(sColumn, "font.underline", 1, "0", iret));
			temp_webdw_column.font.strikethrough = VB_toInt(getElementProp(sColumn, "font.strikethrough", 1, "0", iret));

			// '������background֧��
			temp_webdw_column.background_mode = VB_toInt(getElementProp(sColumn, "background.mode", 1, "", iret));
			temp_webdw_column.background_color = VB_toInt(getElementProp(sColumn, "background.color", 1, "", iret));

			// '�����ǵ�ѡ��ť��֧�ֶ���20090124
			temp_webdw_column.radiobuttons.Columns = VB_toInt(
					getElementProp(sColumn, "radiobuttons.columns", 1, "0", iret));

			// '������ѡ���ť��֧�ֶ���20090124
			temp_webdw_column.checkbox.text = getElementProp(sColumn, "checkbox.text", 1, "", iret);
			temp_webdw_column.checkbox.on = getElementProp(sColumn, "checkbox.on", 1, "", iret);
			temp_webdw_column.checkbox.off = getElementProp(sColumn, "checkbox.off", 1, "", iret);
			temp_webdw_column.checkbox.scale1 = getElementProp(sColumn, "checkbox.scale", 1, "", iret);
			temp_webdw_column.checkbox.threed = getElementProp(sColumn, "checkbox.threed", 1, "", iret);

			// '�����������б�༭����֧�ֶ���20090124
			temp_webdw_column.combobox.allowedit = getElementProp(sColumn, "ddlb.allowedit", 1, "", iret);
			temp_webdw_column.combobox.limit = VB_toInt(getElementProp(sColumn, "ddlb.limit", 1, "0", iret));
			temp_webdw_column.combobox.case1 = getElementProp(sColumn, "ddlb.case", 1, "", iret);
			temp_webdw_column.combobox.useasborder = getElementProp(sColumn, "ddlb.useasborder", 1, "", iret);

			// '����������ʽ���ݴ��ڵ�֧�ֶ���20090125(ţ���Ϧ֮ҹ)
			temp_webdw_column.dddw.allowedit = getElementProp(sColumn, "dddw.allowedit", 1, "", iret);
			temp_webdw_column.dddw.case1 = getElementProp(sColumn, "dddw.case", 1, "", iret);
			temp_webdw_column.dddw.DataColumn = getElementProp(sColumn, "dddw.datacolumn", 1, "", iret);
			temp_webdw_column.dddw.DisplayColumn = getElementProp(sColumn, "dddw.displaycolumn", 1, "", iret);
			temp_webdw_column.dddw.limit = VB_toInt(getElementProp(sColumn, "dddw.limit", 1, "0", iret));
			temp_webdw_column.dddw.Lines = VB_toInt(getElementProp(sColumn, "dddw.lines", 1, "0", iret));
			temp_webdw_column.dddw.Name = getElementProp(sColumn, "dddw.name", 1, "", iret);
			temp_webdw_column.dddw.PercentWidth = VB_toInt(getElementProp(sColumn, "dddw.percentwidth", 1, "100", iret));
			temp_webdw_column.dddw.useasborder = getElementProp(sColumn, "dddw.useasborder", 1, "", iret);
			temp_webdw_column.dddw.vscrollbar = getElementProp(sColumn, "dddw.vscrollbar", 1, "", iret);

			local_webdw.column[temp_webdw_column.id] = temp_webdw_column;
		}
		return 0;
	}

	private int readWebDW09_Line(String dwString) {
		ArrayList lineArray = new ArrayList();
		Object obj;
		String sline = "";
		int id = 0;
		VBInt iret = new VBInt(0);

		lineArray = getAllElement(dwString, "line");
		id = 0;
		// 'ѭ������
		for (int i = 0; i < lineArray.size(); i++) {
			sline = (String) lineArray.get(i);

			id = id + 1;

			if (id > 100) {

				errString = "ERROR:readWebDW09_Line:���ɶ�ȡ100������!";
				return -1;
			}

			local_webdw.lineinfo[id].band = getElementProp(sline, "band", 1, "detail", iret);
			local_webdw.lineinfo[id].x1 = VB_toInt(getElementProp(sline, "x1", 1, "0", iret));
			local_webdw.lineinfo[id].y1 = VB_toInt(getElementProp(sline, "y1", 1, "0", iret));
			local_webdw.lineinfo[id].x2 = VB_toInt(getElementProp(sline, "x2", 1, "0", iret));
			local_webdw.lineinfo[id].y2 = VB_toInt(getElementProp(sline, "y2", 1, "0", iret));
			local_webdw.lineinfo[id].Name = getElementProp(sline, "name", 1, "", iret);
			local_webdw.lineinfo[id].pen_style = getElementProp(sline, "pen.style", 1, "", iret);
			local_webdw.lineinfo[id].pen_width = getElementProp(sline, "pen.width", 1, "", iret);
			local_webdw.lineinfo[id].pen_color = getElementProp(sline, "pen.color", 1, "", iret);
			local_webdw.lineinfo[id].background_mode = getElementProp(sline, "background.mode", 1, "", iret);
			local_webdw.lineinfo[id].background_color = getElementProp(sline, "background.color", 1, "", iret);
		}
		return 0;
	}

}
