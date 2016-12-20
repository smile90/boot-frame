package com.frame.common.frame.base.params;

import java.util.Date;

import com.frame.common.frame.base.bean.RequestBean;

public class DateParam extends RequestBean {

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
        final StringBuffer sb = new StringBuffer("DateParam{");
        sb.append("beginTime=").append(beginTime);
        sb.append(", endTime=").append(endTime);
        sb.append("} ");
        sb.append(super.toString());
        return sb.toString();
    }

}
