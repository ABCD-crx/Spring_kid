import { GameObejct } from "./GameObject";
import { Cell } from "./Cell";

export class Sanke extends GameObejct {
    constructor( info, gamemap) {
        super();

        this.id = info.id;
        this.color = info.color;
        this.gamemap = gamemap;

        // 存放蛇的身体，cells[0] 存放蛇头
        this.cells = [new Cell(info.r, info.c)];
        // 下一步的目标位置
        this.next_cell = null;

        // 蛇的速度 每秒5个格子
        this.speed = 5;
        // 下一步指令 -1没有指令 0、1、2、3 表示上右下左
        this.direction  = -1;
        // 蛇的状态 idle表示静止 move表示正在移动 die表示死亡
        this.status = "idle" 

        this.dr = [-1, 0, 1, 0];    // 4个方向行的偏移量
        this.dc = [0, 1, 0, -1];    // 4个方向列的偏移量

        this.step = 0;      // 表示回合数
        this.eps = 1e-2;    //允许的误差
    }

    start() {

    }

    set_direction(d) {
        this.direction = d;
    }


    // 将蛇的状态变为走下一步
    next_step() {
        const d = this.direction;
        this.next_cell = new Cell(this.cells[0].r + this.dr[d], this.cells[0].c + this.dc[d]);
        this.direction = -1; //清空操作
        this.status = "move";
        this.step ++ ;

        const k = this.cells.length;
        for (let i = k; i > 0; i--) {
            this.cells[i] = JSON.parse(JSON.stringify(this.cells[i - 1]));
        }
    }
    
    update_move() {
        const dx = this.next_cell.x - this.cells[0].x;
        const dy = this.next_cell.y - this.cells[0].y;
        const distance = Math.sqrt(dx * dx + dy * dy);
        
        if (distance < this.eps) {
            // 目标点成为新的头结点 添加一个新蛇头
            this.cells[0] = this.next_cell;
            this.next_cell = null;
            this.status = "idle"
        } else {
            // 每两帧之间走的距离
            const move_distance = this.speed * this.timedelta / 1000;
            this.cells[0].x += move_distance * dx / distance;
            this.cells[0].y += move_distance * dy / distance;
        }

    }

    update() {
        if (this.status === 'move') {
            this.update_move();
        }
        this.render();
    }

    render() {
        const L = this.gamemap.L;
        const ctx = this.gamemap.ctx;

        ctx.fillStyle = this.color;
        // 画圆 ctx.beginPath(); 圆弧 ctx.arc();
        for (const cell of this.cells) {
            ctx.beginPath();
            ctx.arc(cell.x * L, cell.y * L, L / 2, 0, Math.PI * 2);
            ctx.fill();
        }
    }
}