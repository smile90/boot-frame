package com.frame.common.frame.base.param;

import java.io.Serializable;
import java.util.Date;

public class DateParam implements Serializable {

	private static final long serialVersionUID = 1L;

	private Date beginTime;
	private Date endTime;
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

}
