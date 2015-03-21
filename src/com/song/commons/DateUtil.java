package com.song.commons;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtil {
	public static Date parseDate(String param, String f) {
		if ((param == null) || (param.trim().equals(""))) {
			return null;
		}
		DateFormat format = new SimpleDateFormat(f);
		Date date = null;
		try {
			date = format.parse(param);
		} catch (ParseException localParseException) {
		}
		return date;
	}

	public static Date parseDate(String param) {
		return parseDate(param, "yyyy-MM-dd HH:mm:ss");
	}

	public static String getTimeStr(Date date) {
		return getTimeStr(date, "yyyy-MM-dd HH:mm:ss");
	}

	public static String getTimeStr(Date date, String f) {
		if (date == null) {
			return null;
		}
		DateFormat format = new SimpleDateFormat(f);
		return format.format(date);
	}
}