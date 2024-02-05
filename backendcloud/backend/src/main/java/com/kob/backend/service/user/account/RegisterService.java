package com.kob.backend.service.user.account;

import java.util.Map;

/**
 * Author: chen
 * Time: 2024/1/29 16:16
 */
public interface RegisterService {
    Map<String, String> register(String username, String password, String confirmedPassword);
}
