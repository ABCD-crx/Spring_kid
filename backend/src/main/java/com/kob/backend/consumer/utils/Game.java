package com.kob.backend.consumer.utils;

import java.util.Random;

/**
 * Author: chen
 * Time: 2024/2/2 18:27
 */
public class Game {
    final private Integer rows;
    final private Integer cols;
    final private Integer inner_walls_count;

    final private int[][] g;
    final private static int[] dx = {-1, 0, 1, 0}, dy = {0, 1, 0, -1};

    public Game(Integer rows, Integer cols, Integer inner_walls_count) {
        this.rows = rows;
        this.cols = cols;
        this.inner_walls_count = inner_walls_count;
        this.g = new int[rows][cols];
    }

    public int[][] getG() {
        return g;
    }

    // Flood Fill 算法判断是否联通
    //检查地图联通性
    private boolean check_connectivity(int sx, int sy, int tx, int ty) {
        if (sx == tx && sy == ty) return true;
        g[sx][sy] = 1;

        for (int i = 0; i < 4; i++) {
            int x = sx + dx[i];
            int y = sy + dx[i];
            if (x >= 0 && x < this.rows && y >= 0 && y < this.cols && g[x][y] == 0) {
                if (check_connectivity(x, y, tx, ty)) {
                    g[sx][sy] = 0;
                    return true;
                }
            }
        }
        g[sx][sy] = 0;  // g在后端是共享的使用完需要恢复现场
        return false;
    }

    // 生成地图
    private boolean draw() {
        for ( int i = 0; i < this.rows; i++ ) {
            for( int j = 0; j < this.cols; j++ ){
                g[i][j] = 0;
            }
        }
        // 给四周加上障碍物
        for (int i = 0; i < this.rows; i++) {
            g[i][0] = g[i][this.cols - 1] = 1;
        }
        for (int j = 0; j < this.cols; j++) {
            g[0][j] = g[this.rows - 1][j] = 1;
        }

        // 创建随机障碍物   对称放置保证公平
        Random random = new Random();
        for (int i = 0; i < this.inner_walls_count / 2; i++) {
            for (int j = 0; j < 1000; j++) {
                int r = random.nextInt(this.rows);
                int c = random.nextInt(this.cols);
                // 非正方形 采用中心对称
                if (g[r][c] == 1 || g[this.rows - 1 - r][this.cols - 1 - c] == 1)
                    continue;
                // 左上和右下不可被覆盖
                if (r == this.rows - 2 && c == 1 || r == 1 && c == this.cols - 2)
                    continue;
                g[r][c] = g[this.rows - 1 - r][this.cols - 1 - c] = 1;
                break;
            }
        }

        return check_connectivity(this.rows - 2, 1, 1, this.cols - 2);
    }

    public void createMap() {
        for (int i = 0; i < 1000; i++) {
            if (draw()) {
                break;
            }
        }
    }
}
