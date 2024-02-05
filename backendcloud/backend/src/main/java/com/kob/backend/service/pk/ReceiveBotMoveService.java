package com.kob.backend.service.pk;

/**
 * Author: chen
 * Time: 2024/2/6 1:32
 */
public interface ReceiveBotMoveService {
    // 处理bot代码执行结果
    String receiveBotMove(Integer userId, Integer direction);
}
