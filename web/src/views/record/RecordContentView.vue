<template>
    <PlayGround > 
        <div class="marked_user">
            蓝色方A:&nbsp; {{ $store.state.record.a_username }} &nbsp;&nbsp;&nbsp;&nbsp; 红色方B:&nbsp; {{ $store.state.record.b_username }}
        </div>
    </PlayGround>
</template>

<script>

import PlayGround from '@/components/PlayGround.vue'

export default {
    name:'pk_index',
    components:{
        PlayGround,
    },
    setup() {
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
