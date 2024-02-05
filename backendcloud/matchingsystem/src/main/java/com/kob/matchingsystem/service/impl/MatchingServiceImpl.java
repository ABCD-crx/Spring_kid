package com.kob.matchingsystem.service.impl;

import com.kob.matchingsystem.service.MatchingService;
import com.kob.matchingsystem.service.impl.utils.MatchingPool;
import org.springframework.stereotype.Service;

/**
 * Author: chen
 * Time: 2024/2/4 14:45
 */

@Service
public class MatchingServiceImpl implements MatchingService {
    public final static MatchingPool matchingPool = new MatchingPool();

    @Override
    public String addPlayer(Integer userId, Integer rating, Integer botId) {
        System.out.println("addPlayer: " + userId + " " + rating + " " + botId);
        matchingPool.addPlayer(userId, rating, botId);
        return "add Player success";
    }

    @Override
    public String removePlayer(Integer userId) {
        System.out.println("removePlayer: " + userId);
        matchingPool.removePlayer(userId);
        return "remove Player success";
    }
}
