<template>
    <PlayGround v-if="$store.state.pk.status === 'playing' "/>
    <MatchGround v-if="$store.state.pk.status === 'matching' "/>
</template>

<script>

import PlayGround from '@/components/PlayGround.vue'
import MatchGround from '@/components/MatchGround.vue';
import { onMounted, onUnmounted } from 'vue';
import { useStore } from 'vuex';
import wait_photo from '@/assets/images/waiting.png';

export default {
    name:'pk_index',
    components:{
        PlayGround,
        MatchGround,
    },
    setup() {
        const store = new useStore();
        const socketUrl = `ws://127.0.0.1:3000/websocket/${store.state.user.token}/`;

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
                    
                }
            }

            socket.onclose = () => {
                console.log("closeconnect!");
            }
        });

        onUnmounted(() => {
            socket.close();
            store.commit("updateStatus", "matching");
        })
    }
}
</script>

<style scoped>
</style>
