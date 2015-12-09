package nju.express.util;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateUtil {
	// ��ȡ���ڲ���date����һ��
	public static Date getNextDateByDate(Date date) {
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		c.add(Calendar.DATE, 1);
		return c.getTime();
	}

	// ������ת����yyyy-MM-dd�ĸ�ʽ
	public static String getStringByDate(Date date) {
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		return format.format(date);
	}

	/**
	 * ������ת����yyyy-MM-dd HH-mm-ss�ĸ�ʽ
	 * 
	 * @param date
	 * @return
	 */
	public static String getStringByDateTime(Date date) {
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		return format.format(date);
	}

	public static String getStringByTimeStamp(Timestamp timestamp) {
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		return format.format(timestamp);
	}

	public static Date getDateByDateTimeString(String string) {
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date date = null;
		try {
			date = format.parse(string);
		} catch (ParseException e) {

			e.printStackTrace();
		}
		return date;
	}

	public static Date getDateByDateString(String string) {
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		Date date = null;
		try {
			date = format.parse(string);
		} catch (ParseException e) {

			e.printStackTrace();
		}
		return date;
	}
}
