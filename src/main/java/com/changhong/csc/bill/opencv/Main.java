package com.changhong.csc.bill.opencv;

import java.awt.image.BufferedImage;
import java.util.ArrayList;

import org.opencv.core.Mat;

public class Main {

	public static void main(String[] args) {
		m1();
//		m2();
	}

	private static void m2() {
		System.out.println("---------------------------------------------");
		System.load("/opt/opencv/opencv-3.0.0/lib/libopencv_java300.so");
		Mat mat=new Mat();
		System.out.println("---------------------------------------------");
		System.err.println(mat);
		
	}

	private static void m1() {
		String path = "http://10.4.68.7:9999/group1/M00/00/52/CgREB1ggHXGAegoJAAGtkX17jZE290.png";
//		String path = "C:\\Users\\p'c\\Desktop\\CgREB1ggHXGAegoJAAGtkX17jZE290.png";
//		System.out.println("================www================");
//		System.out.println(System.getProperty("java.library.path"));
//		System.out.println("================www================");
		OpenCVLib lib = new OpenCVLib();
		System.out.println("OenCVLib初始化。。。");
		ArrayList<RectModel> info = lib.getInfo(path);
		System.out.println(info);
		ArrayList<BufferedImage> imageList = lib.cut(path, info);
		System.out.println(imageList);
	}

}

