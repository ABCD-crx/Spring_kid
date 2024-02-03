<template>
    <div class="result-board">
        <div class="result-board-text" v-if="$store.state.pk.loser === 'all'">
            平局
        </div>
        <div class="result-board-text" v-else-if="$store.state.pk.loser === 'A' && $store.state.pk.a_id === parseInt($store.state.user.id)">
            你输了
        </div>
        <div class="result-board-text" v-else-if="$store.state.pk.loser === 'B' && $store.state.pk.b_id === parseInt($store.state.user.id)">
            你输了
        </div>
        <div class="result-board-text" v-else>
            赢了
        </div>
        <div class="result-board-btn">
            <button type="button" class="btn btn-warning btn-lg" @click="restart">再来一局!</button>
        </div>
    </div>
</template>

<script>
import { useStore } from 'vuex';
import wait_photo from '@/assets/images/waiting.png';

export default {
    
    setup() {
        const store = useStore();

        const restart = () => {
            store.commit("updateStatus", "matching");
            store.commit("updateLoser", "none");
            store.commit("updateOpponent", {
                username: "我的对手",
                photo: wait_photo,
            })
        }

        return {
            restart,
        }
    }
}
</script>

<style scoped>
div.result-board {
    height: 35vh;
    width: 30vw;
    background-color:rgba(50, 50, 50, 0.5);
    position: absolute;
    top: 30vh;
    left:  35vw;

}
div.result-board-text {
    text-align: center;
    color: white;
    font-size: 50px;
    font-weight: 600;
    font-style: italic;
    padding-top: 5vh;
}
div.result-board-btn {
    padding-top: 7vh;
    text-align: center;
}
</style>