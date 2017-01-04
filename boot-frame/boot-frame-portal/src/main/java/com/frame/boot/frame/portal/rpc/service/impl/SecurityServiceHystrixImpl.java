package com.frame.boot.frame.portal.rpc.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.frame.boot.frame.portal.rpc.service.SecurityService;
import com.frame.common.frame.base.bean.ResponseBean;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

@Service
public class SecurityServiceHystrixImpl implements SecurityService {
    @Override
    public ResponseBean<JSONObject> find(@PathVariable("username") String username) {
        return ResponseBean.EXCEPTION("网络调用超时，请重试");
    }
}
