// 存放所有游戏对象
// 所有游戏对象 第一帧执行 start() 后面帧执行updata()
const GAME_OBJECTS =[];

export class GameObejct {
    constructor() {
        GAME_OBJECTS.push(this);
        this.timedelta = 0;         // 这一帧执行的时刻距离上一帧执行的时间间隔
        this.has_called_start = false;

    }


    start() {   //只执行一次

    }

    updata() {  // 每一帧执行一次，除了第一帧

    }

    on_destroy() {  //删除之前执行

    }

    destroy() { //删除对象
        this.on_destroy();

        for (let i in GAME_OBJECTS) {
            const obj = GAME_OBJECTS[i];
            if(obj === this) {
                GAME_OBJECTS.splice(i);
                break;
            }
        }
    }
}



//requestAnimationFrame() 在下一帧浏览器渲染之前执行一遍 设置回调 以达到每一帧都执行

let last_timestamp;     // 上一次执行的时刻
const step = timestamp => {
    for (let obj of GAME_OBJECTS) { // of遍历值 in遍历下标
        if (!obj.has_called_start){
            obj.has_called_start = true;
            obj.start();
        } else {
            obj.timedelta = timestamp - last_timestamp;
            obj.updata();
        }
    }

    last_timestamp = timestamp;
    requestAnimationFrame(step)
}

requestAnimationFrame(step) // 第一帧开始