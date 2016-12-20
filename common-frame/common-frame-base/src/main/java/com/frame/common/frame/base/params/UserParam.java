package com.frame.common.frame.base.params;

import com.frame.common.frame.base.bean.RequestBean;

public class UserParam extends RequestBean {

    private static final long serialVersionUID = 1L;

    private String userType;
    private String userNo;
    public String getUserType() {
        return userType;
    }
    public void setUserType(String userType) {
        this.userType = userType;
    }
    public String getUserNo() {
        return userNo;
    }
    public void setUserNo(String userNo) {
        this.userNo = userNo;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("UserParam{");
        sb.append("userType='").append(userType).append('\'');
        sb.append(", userNo='").append(userNo).append('\'');
        sb.append('}');
        return sb.toString();
    }
}