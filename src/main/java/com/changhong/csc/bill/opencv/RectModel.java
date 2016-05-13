package com.changhong.csc.bill.opencv;

public class RectModel {
	private double x = 0d;
	private double y = 0d;
	private double width = 0d;
	private double height = 0d;
	private double rotation = 0d;
	private double rightAngel = 0d;
	
	public RectModel() {}
	
	public RectModel(double x, double y, double width, double height, double rotation) {
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		this.rotation = rotation;
	}
	
	public RectModel(double x, double y, double width, double height, double rotation, double rightAngel) {
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		this.rotation = rotation;
		this.rightAngel = rightAngel;
	}

	public double getX() {
		return x;
	}

	public void setX(double x) {
		this.x = x;
	}

	public double getY() {
		return y;
	}

	public void setY(double y) {
		this.y = y;
	}

	public double getWidth() {
		return width;
	}

	public void setWidth(double width) {
		this.width = width;
	}

	public double getHeight() {
		return height;
	}

	public void setHeight(double height) {
		this.height = height;
	}

	public double getRotation() {
		return rotation;
	}

	public void setRotation(double rotation) {
		this.rotation = rotation;
	}

	public double getRightAngel() {
		return rightAngel;
	}

	public void setRightAngel(double rightAngel) {
		this.rightAngel = rightAngel;
	}
}
