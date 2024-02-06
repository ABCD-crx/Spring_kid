package com.kob.backend.service.record;

import com.alibaba.fastjson2.JSONObject;

/**
 * Author: chen
 * Time: 2024/2/6 15:56
 */
public interface GetRecordListService {
    // 传入一个页数 一页10条
    JSONObject getList(Integer page);
}
