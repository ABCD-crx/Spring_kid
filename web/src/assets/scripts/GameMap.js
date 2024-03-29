import { GameObejct } from "./GameObject";
import { Sanke } from "./Snake";
import { Wall } from "./Wall";

export class GameMap extends GameObejct {
    constructor(ctx, parent, store){ //ctx 画布 parent画布的复元素用来动态修改长宽
        super();

        this.ctx = ctx;
        this.parent = parent;
        this.store = store;
        this.L = 0;     //每一个格子的绝对距离

        this.rows = 13;
        this.cols = 14;

        this.inner_walls_count = 40;
        this.walls = [];

        this.snakes = [
            new Sanke({id: 0, color: "#4876ED", r: this.rows - 2, c: 1}, this),
            new Sanke({id: 1, color: "#F94847", r: 1, c: this.cols - 2}, this),
        ];
    }

    // 检测目标位置合法 身体和墙——碰撞检测
    check_valid(cell) {
        for (const wall of this.walls) {
            if (wall.r === cell.r && wall.c === cell.c) 
                return false;
        }

        for (const snake of this.snakes) {
            let k = snake.cells.length;
            if (!snake.check_tail_increasing()) { //蛇尾会前进的时候蛇尾不用判断
                k --;
            }
            for (let i = 0; i < k; i++) {
                if (snake.cells[i].r === cell.r && snake.cells[i].c === cell.c)
                return false
            }
        }


        return true;
    }

    // 判断两条下一回合是否准备好
    check_ready() {
        for (const snake of this.snakes) {
            if(snake.status !== "idle") return false;
            if(snake.direction === -1) return false;
        }
        return true;
    }


    // 地图创建已在后端完成

    // // Flood Fill 算法判断是否联通
    // check_connectivity(g, sx, sy, tx, ty) {
    //     if(sx == tx && sy == ty) return true;
    //     g[sx][sy] = true;

    //     let dx = [-1, 0, 1, 0], dy = [0, 1, 0, -1];
    //     for (let i = 0; i < 4; i++) {
    //         let x = sx + dx[i], y = sy + dy[i];
    //         if(!g[x][y] && this.check_connectivity(g, x, y, tx, ty)) {
    //             return true;
    //         }
    //     }

    //     return false;
    // }


    // // 创建墙障碍物
    create_walls(){
    //     // 每个格子是否为墙 状态数组
    //     const g = [];
    //     for ( let r = 0; r < this.rows; r++ ) {
    //         g[r] = [];
    //         for( let c = 0; c < this.cols; c++ ){
    //             g[r][c] = false;
    //         }
    //     }

    //     // 给四周加上障碍物
    //     for ( let r = 0; r < this.rows; r++){
    //         g[r][0] = g[r][this.cols - 1] = true;
    //     }
    //     for( let c = 0; c < this.cols; c++) {
    //         g[0][c] = g[this.rows - 1][c] = true;
    //     }

    //     // 创建随机障碍物   对称放置保证公平
    //     for ( let i = 0; i < this.inner_walls_count / 2; i++){
    //         for ( let j = 0; j < 1000; j++ ) {
    //             let r = parseInt(Math.random() * this.rows);
    //             let c = parseInt(Math.random() * this.cols);
    //             // 非正方形 采用中心对称
    //             if(g[r][c] || g[this.rows - 1 - r][this.cols - 1 - c] ) continue;

    //             // 左上和右下不可被覆盖
    //             if(r == this.rows - 2 && c == 1 || r == 1 && c == this.cols - 2 ) continue;

    //             g[r][c] = g[this.rows - 1 - r][this.cols - 1 - c] = true;
    //             break;
    //         }
    //     }

    //     const copy_g = JSON.parse(JSON.stringify(g));
    //     if( !this.check_connectivity(copy_g, this.rows - 2, 1, 1, this.cols - 2) ) return false;
        
        const g = this.store.state.pk.gamemap;

        for( let r = 0; r < this.rows; r++) {
            for (let c = 0; c < this.cols; c++) {
                if( g[r][c] ) {
                    this.walls.push(new Wall(r, c, this));
                }
            }
        }


    //     return true;
    }

    // 获取用户输入
    add_listening_events() {
        if (this.store.state.record.is_record) {    // 对局回放
            let k = 0;
            const a_steps = this.store.state.record.a_steps;
            const b_steps = this.store.state.record.b_steps;
            const loser = this.store.state.record.record_loser;
            const [snake0, snake1] = this.snakes;
            const interval_id = setInterval(() => {     //setInterval 定时器隔一段时间执行一次函数  
                if(k >= a_steps.length - 1) {
                    if (loser === "all" || loser === "A") {
                        snake0.status = "die";
                    }
                    if (loser === "all" || loser === "B") {
                        snake1.status = "die";
                    }
                    clearInterval(interval_id);
                } else {
                    snake0.set_direction(parseInt(a_steps[k]));
                    snake1.set_direction(parseInt(b_steps[k]));
                }
                k++;
            }, 300)

        } else {
            this.ctx.canvas.focus();

            this.ctx.canvas.addEventListener("keydown", e => {
                let d = -1;
                if (e.key === 'w') d = 0;
                else if (e.key === 'd') d = 1;
                else if (e.key === 's') d = 2;
                else if (e.key === 'a') d = 3;
                else if (e.key === 'ArrowUp') d = 0;
                else if (e.key === 'ArrowRight') d = 1;
                else if (e.key === 'ArrowDown') d = 2;
                else if (e.key === 'ArrowLeft') d = 3;
    
                if (d >= 0) {
                    this.store.state.pk.socket.send(JSON.stringify({
                        event: "move",
                        direction: d,
                    }));
                }
            });
        }
        
    }

    start() {
        // for(let i = 0; i < 1000; i++) {
        //     if(this.create_walls()){
        //         break;
        //     }
        // }
        this.create_walls();
        this.add_listening_events();
    }
    
    // 每一帧之前更新边长
    update_size() {
        // clientWidth求div的长宽
        // 取整 防止为浮点数 出现像素丢失
        this.L =parseInt( Math.min(this.parent.clientWidth / this.cols, this.parent.clientHeight / this.rows) );
        this.ctx.canvas.width = this.L * this.cols;
        this.ctx.canvas.height = this.L * this.rows;
    }
    
    // 让两条蛇进入下一回合
    next_step() {
        for (const snake of this.snakes) {
            snake.next_step();
        }
    }

    update() {
        this.update_size();
        if (this.check_ready()) {
            this.next_step();
        }
        this.render();
    }

    //渲染 把当前游戏对象画在地图上
    render() {      
        // 调用canvas api
        const color_even = "#E6D9CD" , color_odd ="#D0D5D7";
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