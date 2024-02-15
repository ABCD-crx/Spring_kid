<template>
    <div class="playground">
        <GameMap />
        <slot></slot>
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
</style>
