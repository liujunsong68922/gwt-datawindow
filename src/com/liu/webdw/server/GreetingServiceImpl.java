package com.liu.webdw.server;

import com.liu.webdw.client.GreetingService;
import com.liu.webdw.shared.FieldVerifier;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;

/**
 * The server-side implementation of the RPC service.
 */
@SuppressWarnings("serial")
public class GreetingServiceImpl extends RemoteServiceServlet implements GreetingService {

	public String greetServer(String input) throws IllegalArgumentException {
		// Verify that the input is valid.
		if (!FieldVerifier.isValidName(input)) {
			// If the input is not valid, throw an IllegalArgumentException back to
			// the client.
			throw new IllegalArgumentException("Name must be at least 4 characters long");
		}

		String serverInfo = getServletContext().getServerInfo();
		String userAgent = getThreadLocalRequest().getHeader("User-Agent");

		// Escape data from the client to avoid cross-site script vulnerabilities.
		input = escapeHtml(input);
		userAgent = escapeHtml(userAgent);

		return "Hello, " + input + "!<br><br>I am running " + serverInfo + ".<br><br>It looks like you are using:<br>"
				+ userAgent;
	}

	/**
	 * Escape an html string. Escaping data received from the client helps to
	 * prevent cross-site script vulnerabilities.
	 * 
	 * @param html the html string to escape
	 * @return the escaped string
	 */
	private String escapeHtml(String html) {
		if (html == null) {
			return null;
		}
		return html.replaceAll("&", "&amp;").replaceAll("<", "&lt;").replaceAll(">", "&gt;");
	}

	@Override
	public String getDWDefine(String dwname) {
		String strsql = "select dw_define from t_datawindow where dw_name='" + dwname + "'";
		CommonDAO dao = new CommonDAO();
		Connection conn = dao.getConnection();
		Statement stat;
		try {
			stat = conn.createStatement();
			ResultSet rs = stat.executeQuery(strsql);
			if (rs.next()) {
				String sdef = rs.getString(1);
				return sdef;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return "";
	}

	@Override
	public String getSelectSql(String dwname) {
		String strsql = "select dw_selectsql from t_datawindow where dw_name='" + dwname + "'";
		CommonDAO dao = new CommonDAO();
		Connection conn = dao.getConnection();
		Statement stat;
		try {
			stat = conn.createStatement();
			ResultSet rs = stat.executeQuery(strsql);
			if (rs.next()) {
				String sdef = rs.getString(1);
				return sdef;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return "";
	}

	@Override
	public String getSelectData(String dwname, String strsql, String args) {
		String strsql1 = "select dw_selectsql_args from t_datawindow where dw_name='" + dwname + "'";
		
		CommonDAO dao = new CommonDAO();
		Connection conn = dao.getConnection();
		Statement stat;
		String strsqlargs = "";
		try {
			stat = conn.createStatement();
			ResultSet rs = stat.executeQuery(strsql1);
			if (rs.next()) {
				strsqlargs = rs.getString(1);
			}
			rs.close();
			stat.close();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// TODO:这里执行SQL语句的拼装工作
		// 输入：strsql,strsqlargs,args

		String strsql_new = this.mergeSqlAndArgs(strsql, strsqlargs, args);
		System.out.println(strsql_new);
		String strResult = "";
		try {
			stat = conn.createStatement();
			ResultSet rs2 = stat.executeQuery(strsql_new);

			int columncount = rs2.getMetaData().getColumnCount();
			System.out.println("---->ColumnCount:" + columncount);
			for (int i = 1; i <= columncount; i++) {
				if (strResult.length() == 0) {
					strResult += rs2.getMetaData().getColumnName(i);
				} else {
					strResult += "\t" + rs2.getMetaData().getColumnName(i);
				}
			}

			strResult += "\r\n";

			while (rs2.next()) {
				// TODO:��ȡÿһ�У���������ݵ�
				int colid = 1;
				String strrow = "";
				for (colid = 1; colid <= columncount; colid++) {
					String stext = rs2.getString(colid);
					if (stext == null) {
						stext = "";
					}
					if (strrow.length() == 0) {
						strrow += stext;
					} else {
						strrow += "\t" + stext;
					}
				}
				strResult += strrow + "\r\n";
			}

			rs2.close();
			stat.close();
			conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return strResult;

	}
	
	private String mergeSqlAndArgs(String strsql,String sqlargs,String args) {
		String str_ret_sql=strsql;
		//step1:check sqlargs
		if(sqlargs == null) {
			sqlargs ="";
		}
		System.out.println("sqlargs:"+sqlargs);
		String arg_key[] = sqlargs.split(",");
		if(arg_key.length==0) {
			//if not define args
			return strsql;
		}

		//step2:convert args to a HashMap
		if(args==null) {
			args ="";
		}
		HashMap argmap = new HashMap();
		String argvalue[] = args.split("&");
		for(String argval:argvalue) {
			//用=进行切分
			String data[]=argval.split("=");
			if(data.length>=1) {
				String key = data[0];
				String value = data.length>1 ? data[1]:"";
				argmap.put(key, value);
			}
		}
		
		//step2:check arg and replace it
		for(String arg:arg_key) {
			System.out.println("key:"+arg);
			String sval = (String) argmap.get(arg_key);
			if(sval==null) {
				sval="";
			}
			//字符串替换
			str_ret_sql = str_ret_sql.replaceAll("#"+arg, sval);
			str_ret_sql = str_ret_sql.replaceAll("\\@"+arg, sval);
		}
		
		return str_ret_sql;
	}
}
