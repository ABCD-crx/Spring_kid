package com.kob.backend.consumer.utils;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

/**
 * Author: chen
 * Time: 2024/2/3 12:23
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Player {
    private Integer id;
    private Integer botId;      // bot_id = -1 表示御驾亲征 否则表示Ai
    private String botCode;
    private Integer sx;
    private Integer sy;
    private List<Integer> steps;     //记录每一回合的方向

    // 检测当前回合蛇的长度是否增加
    // 10回合只能比增加 之后两回合增加一次
    private boolean check_tail_increasing(int step) {
        if (step <= 10) return true;
        return step % 3 == 1;
    }

    public List<Cell> getCells() {
        List<Cell> res = new ArrayList<>();

        int[] dx = {-1, 0, 1, 0};
        int[] dy = {0, 1, 0, -1};
        int x = sx, y = sy;
        int step = 0;
        res.add(new Cell(x, y));
        for (int d: steps) {
            x += dx[d];
            y += dy[d];
            res.add(new Cell(x, y));
            step ++;
            if ( !check_tail_increasing(step)) {
                res.remove(0);  // 长度不增加删除蛇尾
            }
        }
        return res;
    }



    public String getStepsString() {
        StringBuilder res = new StringBuilder();
        for (int d: steps) {
            res.append(d);
        }
        return res.toString();
    }
}
