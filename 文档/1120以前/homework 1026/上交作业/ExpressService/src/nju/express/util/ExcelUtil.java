package nju.express.util;

import java.io.File;
import java.lang.reflect.Field;
import java.util.ArrayList;

import jxl.Sheet;
import jxl.Workbook;
import jxl.write.Label;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;

/**
 * @author 徐亚帆
 * @time 2015年10月17日下午8:17:30
 * 
 *       利用反射我们可以不用写具体的列名，而是从类中获取，对于许多需要导入导出的需求 极其方便
 */
public class ExcelUtil {
	/**
	 * @param ar
	 *            需要导出的集合
	 * @param str
	 *            保存的路径
	 */
	public static void excleOut(ArrayList ar, String str) {
		WritableWorkbook book = null;
		try {
			book = Workbook.createWorkbook(new File(str));
			WritableSheet sheet = book.createSheet("sheet1", 0);
			for (int i = 0; i < ar.size(); i++) {
				Object ob = ar.get(i);
				/**
				 * 感觉反射是更厉害的泛型 只有在运行时才知道传入的是什么对象
				 */
				Class cl = ob.getClass();
				Field[] fields = cl.getDeclaredFields();
				for (int j = 0; j < fields.length; j++) {
					fields[j].setAccessible(true);
					Label label = new Label(j, i, String.valueOf(fields[j].get(ob)));
					sheet.addCell(label);
				}
			}
			book.write();
			System.out.println("success");
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				book.close();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	public static ArrayList excleIn(Class cl, String str) {
		ArrayList ar = new ArrayList();
		Workbook book = null;
		try {
			book = Workbook.getWorkbook(new File(str));
			Sheet sheet = book.getSheet(0);
			for (int i = 0; i < sheet.getRows(); i++) {
				Object ob = cl.newInstance();
				Field[] fields = cl.getDeclaredFields();
				for (int j = 0; j < fields.length; j++) {
					fields[j].setAccessible(true);
					String value = sheet.getCell(j, i).getContents();
					if (fields[j].getType().toString().equals("class java.lang.String")) {
						fields[j].set(ob, value);
					} else if (fields[j].getType().toString().equals("int")) {
						fields[j].setInt(ob, Integer.valueOf(value));
					} else if (fields[j].getType().toString().equals("double")) {
						fields[j].setDouble(ob, Double.valueOf(value));
					}
				}
				ar.add(ob);
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			book.close();
		}

		return ar;
	}

	public static void printarrs(ArrayList<?> ar) {
		for (int i = 0; i < ar.size(); i++) {
			System.out.println(ar.get(i).toString());
		}
	}

}
