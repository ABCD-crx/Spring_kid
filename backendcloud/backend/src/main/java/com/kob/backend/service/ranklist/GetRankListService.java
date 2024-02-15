package com.kob.backend.service.ranklist;

import com.alibaba.fastjson2.JSONObject;

/**
 * Author: chen
 * Time: 2024/2/7 1:06
 */
public interface GetRankListService {
    JSONObject getList(Integer page);
}
