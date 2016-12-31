package com.frame.common.frame.base.params;

import com.frame.common.frame.base.bean.RequestBean;

import java.util.Date;

public class DateBean extends RequestBean {

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

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("DateBean{");
        sb.append("beginTime=").append(beginTime);
        sb.append(", endTime=").append(endTime);
        sb.append("} ");
        sb.append(super.toString());
        return sb.toString();
    }

}
