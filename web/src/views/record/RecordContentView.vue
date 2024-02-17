<template>
    <PlayGround > 
        <div class="marked_user">
            蓝色方:&nbsp; 
            <img :src="$store.state.record.a_photo" alt="" class="record-user-photo img-fluid">
            {{ $store.state.record.a_username }} 
            &nbsp;&nbsp;&nbsp;&nbsp; 
            红色方:&nbsp; 
            <img :src="$store.state.record.b_photo" alt="" class="record-user-photo img-fluid">
            {{ $store.state.record.b_username }}
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
    padding-top: 4vh;
    
}

img.record-user-photo {
    width: 7vh;
    border-radius: 50%;
}
</style>
