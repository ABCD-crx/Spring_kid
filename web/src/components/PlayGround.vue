<template>
    <div class="playground">
        <GameMap />

        <div class="marked_user" v-if="$store.state.pk.a_id === parseInt($store.state.user.id)">
            您在左下角(WASD或↑ ↓ ← → 控制方向)
        </div>
        <div class="marked_user" v-if="$store.state.pk.b_id === parseInt($store.state.user.id)">
            您在右上角(WASD或↑ ↓ ← → 控制方向)
        </div>
    </div>
</template>

<script>
import GameMap from './GameMap.vue'

export default {
    components:{
        GameMap,
    },
}
</script>

<style scoped>
div.playground{
    width: 60vw;
    height: 70vh;
    /* background: white; */
    margin: 60px auto;
}
div.marked_user {
    text-align: center;
    color:aliceblue;
    font-weight: bold;
    font-size: 25px;
    padding-top: 5vh;
    
}
</style>
