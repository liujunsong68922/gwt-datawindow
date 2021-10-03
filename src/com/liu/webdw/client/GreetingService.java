package com.liu.webdw.client;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

/**
 * The client-side stub for the RPC service.
 */
@RemoteServiceRelativePath("greet")
public interface GreetingService extends RemoteService {
  String greetServer(String name) throws IllegalArgumentException;
  
  String getDWDefine(String dwname);
  
  String getSelectSql(String dwname);
  
  String getSelectData(String dwname,String strsql,String args);
  
}
