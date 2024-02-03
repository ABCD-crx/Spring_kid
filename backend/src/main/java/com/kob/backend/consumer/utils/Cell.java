package com.kob.backend.consumer.utils;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Author: chen
 * Time: 2024/2/3 18:25
 */

// 标记蛇的身体
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Cell {
    int x;
    int y;
}
