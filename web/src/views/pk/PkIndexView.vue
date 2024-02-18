<template>
    <PlayGround v-if="$store.state.pk.status === 'playing' ">
        <div class="marked_user" v-if="$store.state.pk.a_id === parseInt($store.state.user.id)">
            您是蓝方在左下角(WASD或↑ ↓ ← → 控制方向)
        </div>
        <div class="marked_user" v-if="$store.state.pk.b_id === parseInt($store.state.user.id)">
            您是红方在右上角(WASD或↑ ↓ ← → 控制方向)
        </div>
    </PlayGround>
    <MatchGround v-if="$store.state.pk.status === 'matching' "/>
    <ResultBoard v-if="$store.state.pk.loser != 'none' "/>
</template>

<script>

import PlayGround from '@/components/PlayGround.vue'
import MatchGround from '@/components/MatchGround.vue';
import ResultBoard from '@/components/ResultBoard.vue'
import { onMounted, onUnmounted } from 'vue';
import { useStore } from 'vuex';
import wait_photo from '@/assets/images/waiting.png';

export default {
    name:'pk_index',
    components:{
        PlayGround,
        MatchGround,
        ResultBoard,
    },
    setup() {
        const store = new useStore();
        const socketUrl = `ws://127.0.0.1:3000/websocket/${store.state.user.token}/`;

        store.commit("updateIsRecord", false);

        let socket = null;
        onMounted(() => {
            store.commit("updateOpponent", {
                username: "我的对手",
                photo: wait_photo,
            })
            socket = new WebSocket(socketUrl);

            socket.onopen = () => {
                console.log("connected!");
                store.commit("updateSocket", socket);
            }

            socket.onmessage = msg => {
                const data = JSON.parse(msg.data);
                if (data.event === "success-matching") {    //匹配成功
                    store.commit("updateOpponent", {
                        username: data.opponent_username,
                        photo: data.opponent_photo,
                    });
                    setTimeout(() => {
                        store.commit("updateStatus", "playing");
                    }, 2000);
                    store.commit("updateGame", data.game);
                } else if (data.event === "move") {
                    console.log(data);
                    const game = store.state.pk.gameObject;
                    const [snake0, snake1] = game.snakes;
                    snake0.set_direction(data.a_direction);
                    snake1.set_direction(data.b_direction);
                } else if (data.event === "result"){
                    console.log(data);
                    const game = store.state.pk.gameObject;
                    const [snake0, snake1] = game.snakes;

                    if (data.loser === "all" || data.loser === "A") {
                        snake0.status = "die";
                    }
                    if (data.loser === "all" || data.loser === "B") {
                        snake1.status = "die";
                    }
                    store.commit("updateLoser", data.loser);
                }
            }

            socket.onclose = () => {
                console.log("closeconnect!");
            }
        });

        onUnmounted(() => {
            socket.close();
            store.commit("updateStatus", "matching");
            store.commit("updateLoser", "none");
        })
    }
}
</script>

<style scoped>
div.marked_user {
    text-align: center;
    color:aliceblue;
    font-weight: bold;
    font-size: 30px;
    padding-top: 5vh;
    
}
</style>
