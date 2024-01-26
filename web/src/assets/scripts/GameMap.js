import { GameObejct } from "./GameObject";

export class GameMap extends GameObejct {
    constructor(ctx, parent){ //ctx 画布 parent画布的复元素用来动态修改长宽
        super();

        this.ctx = ctx;
        this.parent = parent;
        this.L = 0;     //每一个格子的绝对距离

        this.rows = 13;
        this.cols = 13;
    }

    start() {

    }
    
    // 每一帧之前更新边长
    updata_size() {
        // clientWidth求div的长宽
        this.L = Math.min(this.parent.clientWidth / this.cols, this.parent.clientHeight / this.rows);
        this.ctx.canvas.width = this.L * this.cols;
        this.ctx.canvas.height = this.L * this.rows;
    }

    updata() {
        this.update_size();
        this.render();
    }

    //渲染 把当前游戏对象画在地图上
    render() {      
        // 调用canvas api
        this.ctx.fillStyle = "green";
        this.ctx.fillRect(0,0,this.ctx.canvas.width, this.ctx.canvas.height);
    }
}