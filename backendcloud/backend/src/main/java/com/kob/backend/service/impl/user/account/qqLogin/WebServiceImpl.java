package com.kob.backend.service.impl.user.account.qqLogin;

import com.alibaba.fastjson2.JSONObject;
import com.kob.backend.service.user.account.qqLogin.WebService;
import org.springframework.stereotype.Service;

/**
 * Author: chen
 * Time: 2024/2/16 17:41
 */
@Service
public class WebServiceImpl implements WebService {
    @Override
    public JSONObject applyCode() {
        return null;
    }

    @Override
    public JSONObject receiveCode(String code, String state) {
        return null;
    }
}
