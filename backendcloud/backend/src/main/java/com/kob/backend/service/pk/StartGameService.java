package com.kob.backend.service.pk;

/**
 * Author: chen
 * Time: 2024/2/5 16:06
 */
public interface StartGameService {
    // 处理匹配成功信息
    String startGame(Integer aId, Integer aBotId, Integer bId, Integer bBotId);
}
