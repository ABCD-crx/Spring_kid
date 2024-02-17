package com.kob.backend.service.user.account.qqLogin;

import com.alibaba.fastjson2.JSONObject;

/**
 * Author: chen
 * Time: 2024/2/16 17:35
 */
public interface WebService {
    JSONObject applyCode();
    JSONObject receiveCode(String code, String state);
}
