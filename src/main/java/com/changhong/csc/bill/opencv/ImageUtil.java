package com.changhong.csc.bill.opencv;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferByte;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.opencv.core.Core;
import org.opencv.core.CvType;
import org.opencv.core.Mat;
import org.opencv.imgproc.Imgproc;

public class ImageUtil {
	//匹配一次
	private static Pattern oncePattern = Pattern.compile("([2zZ]{1}[0o]{1}[0o1lT]{1}[0-9oT]{1}|"
			+ "发票|元整|报销|凭证|出租车|公司|电话|日期|卡号|无效|有效|出口|入口|收费|时间|现金|专用|收据|活动|用户|车型|金额|找零|"
			+ "方式|道路|平整|限乘|当日|当次|等座|欢迎|光临|菜品|兑奖|"
			+ "中国|"
			+ "河南|"
			+ "郑州|洛阳|焦作|商丘|信阳|周口|鹤壁|安阳|濮阳|驻马店|"
			+ "南阳|开封|漯河|许昌|新乡|济源|灵宝|偃师|邓州|登封|三门峡|"
			+ "新郑|禹州|巩义|永城|长葛|义马|林州|项城|汝州|荥阳|"
			+ "平顶山|卫辉|辉县|舞钢|新密|孟州|沁阳|郏县|"
			+ "安徽|"
			+ "合肥|亳州|芜湖|马鞍山|池州|黄山|滁州|安庆|"
			+ "淮南|淮北|蚌埠|宿州|宣城|六安|阜阳|"
			+ "铜陵|明光|天长|宁国|界首|桐城|"
			+ "福建|"
			+ "福州|厦门|泉州|漳州|南平|三明|龙岩|莆田|"
			+ "宁德|建瓯|武夷山|长乐|福清|晋江|南安|福安|"
			+ "龙海|邵武|石狮|福鼎|建阳|漳平|永安|"
			+ "甘肃|"
			+ "兰州|白银|武威|金昌|平凉|张掖|嘉峪关|酒泉|"
			+ "庆阳|定西|陇南|天水|玉门|临夏|合作|敦煌|甘南州|"
			+ "贵州|"
			+ "贵阳|安顺|遵义|六盘水|兴义|都匀|凯里|毕节|清镇|"
			+ "铜仁|赤水|仁怀|福泉|"
			+ "海南|"
			+ "海口|三亚|万宁|文昌|儋州|琼海|东方|五指山|"
			+ "河北|"
			+ "石家庄|保定|唐山|邯郸|邢台|沧州|衡水|廊坊|承德|迁安|"
			+ "鹿泉|秦皇岛|南宫|任丘|叶城|辛集|涿州|定州|晋州|霸州|"
			+ "黄骅|遵化|张家口|沙河|三河|冀州|武安|河间|深州|新乐|"
			+ "泊头|安国|双滦|高碑店|"
			+ "黑龙江|"
			+ "哈尔滨|伊春|牡丹江|大庆|鸡西|鹤岗|绥化|齐齐哈尔|"
			+ "黑河|富锦|虎林|密山|佳木斯|双鸭山|海林|铁力|北安|"
			+ "五大连池|阿城|尚志|五常|安达|七台河|绥芬河|双城|"
			+ "海伦|宁安|讷河|穆棱|同江|肇东|"
			+ "湖北|"
			+ "武汉|荆门|咸宁|襄樊|荆州|黄石|宜昌|随州|"
			+ "鄂州|孝感|黄冈|十堰|枣阳|老河口|恩施|仙桃|"
			+ "天门|钟祥|潜江|麻城|洪湖|汉川|赤壁|松滋|"
			+ "丹江口|武穴|广水|石首|大冶|枝江|应城|宜城|"
			+ "当阳|安陆|宜都|利川|"
			+ "湖南|"
			+ "长沙|郴州|益阳|娄底|株洲|衡阳|湘潭|"
			+ "岳阳|常德|邵阳|永州|张家界|怀化|浏阳|"
			+ "醴陵|湘乡|耒阳|沅江|涟源|常宁|吉首|"
			+ "冷水江|临湘|汨罗|武冈|韶山|安化|湘西州"
			+ "吉林|"
			+ "长春|吉林|通化|白城|四平|辽源|松原|白山|"
			+ "集安|梅河口|双辽|延吉|九台|桦甸|榆树|蛟河|"
			+ "磐石|大安|德惠|洮南|龙井|珲春|公主岭|图们|"
			+ "舒兰|和龙|临江|敦化|"
			+ "江苏|"
			+ "南京|无锡|常州|扬州|徐州|苏州|连云港|盐城|"
			+ "淮安|宿迁|镇江|南通|泰州|兴化|东台|常熟|"
			+ "江阴|张家港|通州|宜兴|邳州|海门|大丰|溧阳|"
			+ "泰兴|昆山|启东|江都|丹阳|吴江|靖江|扬中|"
			+ "新沂|仪征|太仓|姜堰|高邮|金坛|句容|灌南|"
			+ "江西|"
			+ "南昌|赣州|上饶|宜春|景德镇|新余|九江|萍乡|"
			+ "抚州|鹰潭|吉安|丰城|樟树|德兴|瑞金|井冈山|"
			+ "高安|乐平|南康|贵溪|瑞昌|东乡|广丰|信州|三清山"
			+ "辽宁|"
			+ "沈阳|葫芦岛|大连|盘锦|鞍山|铁岭|本溪|丹东|"
			+ "抚顺|锦州|辽阳|阜新|调兵山|朝阳|海城|北票|"
			+ "盖州|凤城|庄河|凌源|开原|兴城|新民|大石桥|"
			+ "东港|北宁|瓦房店|普兰店|凌海|灯塔|营口|"
			+ "青海|"
			+ "西宁|格尔木|德令哈|"
			+ "山东|"
			+ "济南|青岛|威海|潍坊|菏泽|济宁|莱芜|东营|"
			+ "烟台|淄博|枣庄|泰安|临沂|日照|德州|聊城|"
			+ "滨州|乐陵|兖州|诸城|邹城|滕州|肥城|新泰|"
			+ "胶州|胶南|即墨|龙口|平度|莱西|"
			+ "山西|"
			+ "太原|大同|阳泉|长治|临汾|晋中|运城|忻州|"
			+ "朔州|吕梁|古交|高平|永济|孝义|侯马|霍州|"
			+ "介休|河津|汾阳|原平|潞城|"
			+ "陕西|"
			+ "西安|咸阳|榆林|宝鸡|铜川|渭南|汉中|安康|"
			+ "商洛|延安|韩城|兴平|华阴|"
			+ "四川|"
			+ "成都|广安|德阳|乐山|巴中|内江|宜宾|南充|"
			+ "都江堰|自贡|泸州|广元|达州|资阳|绵阳|眉山|"
			+ "遂宁|雅安|阆中|攀枝花|广汉|绵竹|万源|华蓥|"
			+ "江油|西昌|彭州|简阳|崇州|什邡|峨眉山|邛崃|双流|"
			+ "云南|"
			+ "昆明|玉溪|大理|曲靖|昭通|保山|丽江|临沧|楚雄|"
			+ "开远|个旧|景洪|安宁|宣威|"
			+ "浙江|"
			+ "杭州|宁波|绍兴|温州|台州|湖州|嘉兴|金华|舟山|"
			+ "衢州|丽水|余姚|乐清|临海|温岭|永康|瑞安|慈溪|"
			+ "义乌|上虞|诸暨|海宁|桐乡|兰溪|龙泉|建德|富德|"
			+ "富阳|平湖|东阳|嵊州|奉化|临安|江山|"
			+ "台湾|"
			+ "台北|台南|台中|高雄|桃源|"
			+ "广东|"
			+ "广州|深圳|珠海|汕头|佛山|韶关|湛江|肇庆|江门|茂名|惠州|梅州|汕尾|河源|阳江|清远|东莞|中山|潮州|揭阳|云浮|"
			+ "广西|"
			+ "南宁|贺州|玉林|桂林|柳州|梧州|北海|钦州|百色|"
			+ "防城港|贵港|河池|崇左|来宾|东兴|桂平|北流|"
			+ "岑溪|合山|凭祥|宜州|"
			+ "内蒙古|"
			+ "呼和浩特|呼伦贝尔|赤峰|扎兰屯|鄂尔多斯|乌兰察布|"
			+ "巴彦淖尔|二连浩特|霍林郭勒|包头|乌海|阿尔山|"
			+ "乌兰浩特|锡林浩特|根河|满洲里|额尔古纳|牙克石|"
			+ "临河|丰镇|通辽|"
			+ "宁夏|"
			+ "银川|固原|石嘴山|青铜峡|中卫|吴忠|灵武|"
			+ "西藏|"
			+ "拉萨|日喀则|"
			+ "新疆|维吾尔|"
			+ "乌鲁木齐|石河子|喀什|阿勒泰|阜康|库尔勒|阿克苏|"
			+ "阿拉尔|哈密|克拉玛依|昌吉|奎屯|米泉|和田|"
			+ "香港|"
			+ "澳门)");
	
	//匹配两次
	private static Pattern twicePattern = Pattern.compile("([0-9oT]+\\.[0-9oT]+)");
	
	public static Image toBufferedImage(Mat m) {
		int type = BufferedImage.TYPE_BYTE_GRAY;
		if (m.channels() > 1) {
			type = BufferedImage.TYPE_3BYTE_BGR;
		}
		int bufferSize = m.channels() * m.cols() * m.rows();
		byte[] b = new byte[bufferSize];
		m.get(0, 0, b); // get all the pixels
		BufferedImage image = new BufferedImage(m.cols(), m.rows(), type);
		final byte[] targetPixels = ((DataBufferByte) image.getRaster()
				.getDataBuffer()).getData();
		System.arraycopy(b, 0, targetPixels, 0, b.length);

		return image;
	}
	
	public static Mat toMat(BufferedImage image) {
		try {
			byte[] targetPixels = ((DataBufferByte) image.getRaster()
					.getDataBuffer()).getData();
			Mat m;
			int type = image.getType();
			if(type == BufferedImage.TYPE_BYTE_GRAY) {
				m = new Mat(image.getHeight(), image.getWidth(), CvType.CV_8UC1);
			} else if(type == BufferedImage.TYPE_3BYTE_BGR) {
				m = new Mat(image.getHeight(), image.getWidth(), CvType.CV_8UC3);
			} else if(type == BufferedImage.TYPE_4BYTE_ABGR) {
				m = new Mat(image.getHeight(), image.getWidth(), CvType.CV_8UC3);
				//提取rgb通道数据，丢弃alpha通道数据，因为opencv有些接口只支持rgb3通道图像
				int bufferSize = 3 * m.cols() * m.rows();
				byte[] b = new byte[bufferSize];
				for(int i = 0, j = 0, n = 0, len = targetPixels.length; i < len; i++) {
					n = i % 4;
					if(n != 0) {
						b[j] = targetPixels[i];
						j++;
					}
				}
				targetPixels = b;
			} else {
				m = new Mat(image.getHeight(), image.getWidth(), CvType.CV_8UC3);
			}
			m.put(0, 0, targetPixels); // put all the pixels to mat
			return m;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public static Mat sobel(Mat src_gray) {
		Imgproc.medianBlur(src_gray, src_gray, 5);
		// ///////////////////////// Sobe l////////////////////////////////////
		// / Generate grad_x and grad_y
		Mat grad_x = new Mat(), grad_y = new Mat();
		Mat abs_grad_x = new Mat(), abs_grad_y = new Mat();
		// / Gradient X
		Imgproc.Sobel(src_gray, grad_x, CvType.CV_16S, 1, 0, 3, 1, 0,
				Core.BORDER_DEFAULT);
//		Imgproc.Scharr(src_gray, grad_x, CvType.CV_16S, 1, 0, 1, 0,
//				Core.BORDER_DEFAULT);
		Core.convertScaleAbs(grad_x, abs_grad_x);
		// / Gradient Y
		Imgproc.Sobel(src_gray, grad_y, CvType.CV_16S, 0, 1, 3, 1, 0,
				Core.BORDER_DEFAULT);
//		Imgproc.Scharr(src_gray, grad_y, CvType.CV_16S, 0, 1, 1, 0,
//				Core.BORDER_DEFAULT);
		Core.convertScaleAbs(grad_y, abs_grad_y);
		// / Total Gradient (approximate)
		Core.addWeighted(abs_grad_x, 0.5, abs_grad_y, 0.5, 0, src_gray);
		return src_gray;
	}
	
	public static boolean isUpright(String fileName) {
		String formatMessage 	= null;
		ProcessBuilder builder 	= null;
		Process process 		= null;
		String name = fileName.substring(0, fileName.indexOf("."));
		boolean isUpright = true;
		
		try {
			//create get stream messages command.
			ArrayList<String> command = new ArrayList<String>(0);
			command.add("tesseract");
			command.add(PathConst.DEFAULT_DEMO_FOLDER_PATH + fileName);
			command.add(PathConst.DEFAULT_DEMO_FOLDER_PATH + name);
			command.add("-l");
			command.add("chi_sim");
			
			System.out.println("run cmd: " + command.toString());  
			
			builder = new ProcessBuilder(command);  
            builder.redirectErrorStream(true);  
            process = builder.start();  
            
	        InputStream in = process.getInputStream();
            InputStreamReader inReader = new InputStreamReader(in, "UTF-8");
            BufferedReader reader = new BufferedReader(inReader);
            String line = null;
            StringBuffer sb = new StringBuffer();
            while ((line = reader.readLine()) != null) {
            	sb.append(line + "\n");
            }
            reader.close();
            
            formatMessage = new String(sb);
            System.out.println(formatMessage);  
            
            process.waitFor();
		} catch (Exception e) { 
			e.printStackTrace();
			return isUpright;
        } finally {
			process.destroy();
        }
		
		try {
			String outputFileName = name + ".txt";
			String filePath = PathConst.DEFAULT_DEMO_FOLDER_PATH + outputFileName;
			FileInputStream in = new FileInputStream(filePath);
			InputStreamReader inReader = new InputStreamReader(in, "UTF-8");
            BufferedReader reader = new BufferedReader(inReader);
            String line = null;
            StringBuffer sb = new StringBuffer();
            while ((line = reader.readLine()) != null) {
            	sb.append(line + "\n");
            }
            reader.close();
            
            String ocrContent = sb.toString();
            System.out.println("ocrContent : " + ocrContent); 
	        Matcher matcher1 = oncePattern.matcher(ocrContent);
	        Matcher matcher2 = twicePattern.matcher(ocrContent);
	        isUpright = matcher1.find() || (matcher2.find() && matcher2.find());
	        
            File file = new File(filePath);
            if(file.exists() && file.isFile()) {
            	file.delete();
            }
		} catch (Exception e) {
			isUpright = true;
			e.printStackTrace();
		}
		System.out.println("isUpright : " + isUpright);  
		return isUpright;
	}
}
