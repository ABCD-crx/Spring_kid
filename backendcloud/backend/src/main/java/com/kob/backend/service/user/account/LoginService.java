package com.kob.backend.service.user.account;

import java.util.Map;

/**
 * Author: chen
 * Time: 2024/1/29 16:14
 */
public interface LoginService {
    Map<String, String> getToken(String username, String password);
}
