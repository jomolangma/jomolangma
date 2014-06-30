package com.jomolangma.app.mybatis.basic;

import java.util.Calendar;
import java.util.Date;

public class TimePeriod extends BaseDomain {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * 开始时间
	 */
	private Date beginTime;
	/**
	 * 结束时间
	 */
	private Date endTime;
	
	public TimePeriod() {
	}
	
	public TimePeriod(Date beginTime,Date endTime) {
		setBeginTime(beginTime);
		setEndTime(endTime);
	}

	public Date getBeginTime() {
		return beginTime;
	}

	public void setBeginTime(Date beginTime) {
		this.beginTime = beginTime;
	}

	public Date getEndTime() {
		return endTime;
	}

	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}	
	
	/**
	 * 时间段是否有效,只有beginTime != null && endTime != null && beginTime.before(endTime)时才有效
	 * @return true-时间段有效,false-时间段无效
	 */
	public boolean isInvalid() {
		return beginTime == null || endTime == null || beginTime.after(endTime);
	}
	
	private static TimePeriod createTimePeriod(int year, int month, int date,
			int field) {
		Calendar c1 = Calendar.getInstance();
		c1.set(year, month, date, 0, 0, 0);
		c1.set(Calendar.MILLISECOND, 0);
		Calendar c2 = Calendar.getInstance();
		c2.setTime(c1.getTime());
		c2.add(field, 1);
		c2.add(Calendar.MILLISECOND, -1);
		return new TimePeriod(c1.getTime(),c2.getTime());
	}
	
	/**
	 * 返回指定日期的时间段,如2011-07-01的时间段为2011-07-01 00:00:00:000~2011-07-01 23:59:59:999
	 * @param year 年份
	 * @param month 月份
	 * @param date 日期
	 * @return 指定日期的时间段
	 */
	public static TimePeriod createDailyTimePeriod(int year, int month, int date) {
		return createTimePeriod(year,month - 1,date,Calendar.DAY_OF_MONTH);
	}
	
	/**
	 * 返回指定月份的时间段,如2011-07的时间段为2011-07-01 00:00:00:000~2011-07-31 23:59:59:999
	 * @param year 年份
	 * @param month 月份
	 * @return 指定月份的时间段
	 */
	public static TimePeriod createMonthlyTimePeriod(int year, int month) {
		return createTimePeriod(year, month - 1, 1, Calendar.MONTH);
	}
	
	/**
	 * 返回指定年份的时间段,如2011年的时间段为2011-01-01 00:00:00:000~2011-12-31 23:59:59:999
	 * @param year 年份
	 * @return 指定年份的时间段
	 */
	public static TimePeriod createYearlyTimePeriod(int year) {
		return createTimePeriod(year, Calendar.JANUARY, 1, Calendar.YEAR);
	}

}
