package com.liu.webdw.client;

import com.google.gwt.user.client.rpc.AsyncCallback;

/**
 * The async counterpart of <code>GreetingService</code>.
 */
public interface GreetingServiceAsync {
  void greetServer(String input, AsyncCallback<String> callback)
      throws IllegalArgumentException;
  
  void getDWDefine(String dwname,AsyncCallback<String> callback);

  void getSelectSql(String dwname,AsyncCallback<String> callback);
  
  void getSelectData(String dwname,String strsql,String args,AsyncCallback<String> callback);

}
