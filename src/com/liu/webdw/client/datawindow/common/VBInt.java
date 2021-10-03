package com.liu.webdw.client.datawindow.common ;


/**
 * enclose int value in a class,
 * then you can get call result value from it.
 * @author Lenovo
 *
 */
public class VBInt extends GolbalENV{
	public void ReadMe(){
		
		System.out.println(JWebDWInfo);
	}
	public int intvalue = 0;

	public VBInt(int i) {
		intvalue = i;
	}
}
