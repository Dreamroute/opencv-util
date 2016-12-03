package com.changhong.csc.bill.opencv;

import java.awt.image.BufferedImage;
import java.util.ArrayList;

/**
 * OpenCV测试代码，用于测试OpenCV环境是否配置好<br>
 * 测试步骤：<br>
 * 		1、修改OpenCVLib对opencv本地文件的加载，Linux/Windows二选一，注释掉其中一个<br>
 * 		2、使用eclipse导出     Runnable JAR file     类型<br>
 * 
 * @author Administrator
 *
 */
public class Main {
	
	public static final String LINUX = "/myfolder/opencv_tools/opencv-3.0.0/build/share/OpenCV/java/libopencv_java300.so";
	public static final String WINDOWS = "D:/opencv/build/java/x64/opencv_java300.dll";

	public static void main(String[] args) {
		m1();
	}


	private static void m1() {
		try {
			String path = "http://10.4.68.7:9999/group1/M00/00/52/CgREB1ggHXGAegoJAAGtkX17jZE290.png";
			OpenCVLib lib = new OpenCVLib();
			System.out.println("OenCVLib初始化。。。");
			ArrayList<RectModel> info = lib.getInfo(path);
			System.out.println(info);
			ArrayList<BufferedImage> imageList = lib.cut(path, info);
			System.out.println(imageList);
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.err.println("成功。。。");
	}

}

