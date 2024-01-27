import { GameObejct } from "./GameObject";
import { Wall } from "./Wall";

export class GameMap extends GameObejct {
    constructor(ctx, parent){ //ctx 画布 parent画布的复元素用来动态修改长宽
        super();

        this.ctx = ctx;
        this.parent = parent;
        this.L = 0;     //每一个格子的绝对距离

        this.rows = 13;
        this.cols = 13;

        this.inner_walls_count = 40;
        this.walls = [];
    }

    // Flood Fill 算法判断是否联通
    cheak_connectivity(g, sx, sy, tx, ty) {
        if(sx == tx && sy == ty) return true;
        g[sx][sy] = true;

        let dx = [-1, 0, 1, 0], dy = [0, 1, 0, -1];
        for (let i = 0; i < 4; i++) {
            let x = sx + dx[i], y = sy + dy[i];
            if(!g[x][y] && this.cheak_connectivity(g, x, y, tx, ty)) {
                return true;
            }
        }

        return false;
    }


    // 创建墙障碍物
    create_walls(){
        // 每个格子是否为墙 状态数组
        const g = [];
        for ( let r = 0; r < this.rows; r++ ) {
            g[r] = [];
            for( let c = 0; c < this.cols; c++ ){
                g[r][c] = false;
            }
        }

        // 给四周加上障碍物
        for ( let r = 0; r < this.rows; r++){
            g[r][0] = g[r][this.cols - 1] = true;
        }
        for( let c = 0; c < this.cols; c++) {
            g[0][c] = g[this.cols - 1][c] = true;
        }

        // 创建随机障碍物   对称放置保证公平
        for ( let i = 0; i < this.inner_walls_count / 2; i++){
            for ( let j = 0; j < 1000; j++ ) {
                let r = parseInt(Math.random() * this.rows);
                let c = parseInt(Math.random() * this.cols);
                if(g[r][c] || g[c][r] ) continue;

                // 左上和右下不可被覆盖
                if(r == this.rows - 2 && c == 1 || r == 1 && c == this.cols - 2 ) continue;

                g[r][c] = g[c][r] = true;
                break;
            }
        }


        const copy_g = JSON.parse(JSON.stringify(g));
        if( !this.cheak_connectivity(copy_g, this.rows - 2, 1, 1, this.cols - 2) ) return false;

        for( let r = 0; r < this.rows; r++) {
            for (let c = 0; c < this.cols; c++) {
                if( g[r][c] ) {
                    this.walls.push(new Wall(r, c, this));
                }
            }
        }


        return true;
    }

    start() {
        for(let i = 0; i < 1000; i++) {
            if(this.create_walls()){
                break;
            }
        }
    }
    
    // 每一帧之前更新边长
    update_size() {
        // clientWidth求div的长宽
        // 取整 防止为浮点数 出现像素丢失
        this.L =parseInt( Math.min(this.parent.clientWidth / this.cols, this.parent.clientHeight / this.rows) );
        this.ctx.canvas.width = this.L * this.cols;
        this.ctx.canvas.height = this.L * this.rows;
    }

    update() {
        this.update_size();
        this.render();
    }

    //渲染 把当前游戏对象画在地图上
    render() {      
        // 调用canvas api
        const color_even = "#AAD752" , color_odd ="#A2D048";
        for (let r = 0; r < this.rows; r++) {
            for(let c = 0; c < this.cols; c++) {
                if((r + c) % 2 == 0) {
                    this.ctx.fillStyle = color_even;
                } else {
                    this.ctx.fillStyle = color_odd;
                }
                this.ctx.fillRect(c * this.L, r * this.L, this.L, this.L);
            }
        }
       
    }
}