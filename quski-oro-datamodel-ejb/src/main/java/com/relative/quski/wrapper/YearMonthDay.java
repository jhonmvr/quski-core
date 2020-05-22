package com.relative.quski.wrapper;

import java.io.Serializable;

public class YearMonthDay implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private Long year;
	private Long month;
	private Long day;
	
	public YearMonthDay(Long year, Long month, Long day) {
		super();
		this.year = year;
		this.month = month;
		this.day = day;
	}
	
	public Long getYear() {
		return year;
	}
	public void setYear(Long year) {
		this.year = year;
	}
	public Long getMonth() {
		return month;
	}
	public void setMonth(Long month) {
		this.month = month;
	}
	public Long getDay() {
		return day;
	}
	public void setDay(Long day) {
		this.day = day;
	}
	
}
