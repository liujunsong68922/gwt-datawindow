package com.liu.webdw.client.datawindow.view.model;

public class CViewModel_RectangleVO {
	private int left;
	private int top;
	private int width;
	private int height;
	
	public CViewModel_RectangleVO(int ileft, int itop, int iwidth, int iheight) {
		this.left = ileft;
		this.top = itop;
		this.width = iwidth;
		this.height = iheight;
	}

	public int getLeft() {
		return left;
	}

	public void setLeft(int left) {
		this.left = left;
	}

	public int getTop() {
		return top;
	}

	public void setTop(int top) {
		this.top = top;
	}

	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	
}
