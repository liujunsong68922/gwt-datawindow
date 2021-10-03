package com.liu.webdw.client.datawindow.common;

import java.util.*;


public class VBFunction {
	
	/**
	 * VB's InStr function
	 * @param beginPos
	 * @param string1
	 * @param findString
	 * @return
	 */
	public static int VB_InStr(int beginPos, String string1, String findString) {
		int ipos;
		if (string1 == null || string1.equals("")) {
			return -1;
		}
		if (beginPos<1){
			beginPos=1;
		}
		ipos = string1.indexOf(findString, beginPos - 1);
		return ipos + 1;
	}

	public static int VB_InStr(String string1, String findString) {
		return VB_InStr(0, string1, findString);
	}

	public static int VB_Len(String inStr) {
		if (inStr == null) {
			return 0;
		}
		return inStr.length();
	}

	public static String VB_Mid(String str1, int pos, int length) {
		if (length <= 0) {
			return "";
		}
		return str1.substring(pos - 1, pos + length - 1);
	}

	public static String VB_Mid(String str1, int pos) {
		return str1.substring(pos - 1);
	}

	public String VB_Left(String instr, int i) {
		if (instr.length() >= i) {
			return instr.substring(0, i);
		} else {
			return instr;
		}
	}

	public String VB_Right(String instr, int i) {
		return instr.substring(instr.length() - i);
	}

	public static String VB_Chr(int i) {
		if (i == 9)
			return "\t";
		if (i == 13)
			return "\r";
		if (i == 10)
			return "\n";
		return "";
	}

	public int VB_toInt(String s) {
		try {
			return Integer.parseInt(s);
		} catch (Exception e) {
			// e.printStackTrace();
			// System.out.println("ERROR Data ToInt:*"+s+"*");
			return 0;
		}
	}

	public double VB_toDouble(String s) {
		try {
			return Double.parseDouble(s);
		} catch (Exception e) {
			// e.printStackTrace();
			// System.out.println("ERROR Data ToInt:*"+s+"*");
			return 0;
		}
	}

	public static String VB_UCase(String inStr) {
		return inStr.toUpperCase();
	}

	public String[] VB_Split(String inStr1, String sepStr) {
		// return inStr.split(sepStr);
		int arraynum = 0;
		String data[] = new String[1000];
		int i = 0;
		int tempi = 0;
		tempi = VB_InStr(i, inStr1, sepStr);
		while (tempi > 0) {
			if (i == 0) {
				// ��һ��Ԫ��,��1��ʼ����
				data[arraynum] = VB_Mid(inStr1, 1, tempi - i - 1);
			} else {
				data[arraynum] = VB_Mid(inStr1, i + sepStr.length(), tempi - i
						- sepStr.length());
			}
			arraynum++;
			i = tempi;
			tempi = VB_InStr(i+1, inStr1, sepStr);
		}
		if (i == 0) {
			data[arraynum] = inStr1;
		} else {
			data[arraynum] = VB_Mid(inStr1, i + sepStr.length());
		}
		arraynum++;
		String newdata[] = new String[arraynum];
		for (i = 0; i < arraynum; i++) {
			newdata[i] = data[i];
		}
		return newdata;
	}


	public int VB_UBound(String[] instr) {
		return instr.length - 1;
	}

	public String VB_Trim(String inStr) {
		return inStr.trim();
	}

	public String VB_Replace(String instr, String str1, String str2) {
		return instr.replaceAll(str1, str2);
	}

	public String VB_Now() {
		return new java.util.Date().toString();
	}

	public long VB_RGB(int r, int g, int b) {
		return r * 256 * 256 + g * 256 + b;
	}

	public int VB_Asc(String s) {
		if (s == null) {
			s = " ";
		}
		if (s.length() == 0) {
			s = " ";
		}
		return s.charAt(0);
	}

	public boolean VB_IsGreat(String s1, String s2) {
		if (s1.length() > 0 && s2.length() > 0) {
			if (s1.charAt(0) > s2.charAt(0)) {
				return true;
			}
			if (s1.charAt(0) < s2.charAt(0)) {
				return false;
			}
			s1 = s1.substring(1);
			s2 = s2.substring(1);
			return VB_IsGreat(s1, s2);
		}

		if (s1.length() > 0 && s2.length() == 0) {
			return true;
		}

		if (s1.length() == 0 && s2.length() > 0) {
			return false;
		}
		return true;
	}

	public boolean VB_IsNumeric(String s1) {
		try {
			double d = Double.parseDouble(s1);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	public double VB_Rnd(double d) {
		Random r = new Random();
		return r.nextDouble();
	}
	
	//static MessageJDialog d1 = null;
	public static void VB_MessageBox(String title,String info){
		//f1 = new JFrame();
		//d1 = new MessageJDialog(title,info,f1);
	}
	
	public static void VB_MessageBox(String sinfo){
		VB_MessageBox("JWebDW0.3",sinfo);
	}

	public static void VB_MsgBox(String stext,int iButton,String title){
		VB_MessageBox(title,stext);
	}
}
