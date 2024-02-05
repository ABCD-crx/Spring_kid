package com.kob.matchingsystem.service;

/**
 * Author: chen
 * Time: 2024/2/4 14:42
 */
public interface MatchingService {
    // 在匹配池中加入一名玩家
    String addPlayer(Integer userId, Integer rating, Integer botId);
    // 在匹配池中删除一名玩家
    String removePlayer(Integer userId);
}
