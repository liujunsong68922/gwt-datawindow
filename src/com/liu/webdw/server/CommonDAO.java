package com.liu.webdw.server;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class CommonDAO {
	  // MySQL 8.0 ���°汾 - JDBC �����������ݿ� URL
    static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";  
    //static final String DB_URL = "jdbc:mysql://localhost:3306/webdw2.0";
    static final String DB_URL = "jdbc:mysql://webdw.vicp.net:3306/webdw_db";
 
    static final String USER = "webdw";
    static final String PASS = "webdw";
    
    public Connection getConnection() {
        Connection conn = null;
        Statement stmt = null;
        try{
            // ע�� JDBC ����
            Class.forName(JDBC_DRIVER);
        
            // ������
            System.out.println("�������ݿ�...");
            conn = DriverManager.getConnection(DB_URL,USER,PASS);
        
            return conn;
        }catch(Exception e) {
        	e.printStackTrace();
        	return null;
        }
    }
}
