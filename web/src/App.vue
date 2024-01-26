<template>
  <NavBar/>
  <router-view />
</template>

<script>
  import NavBar from "./components/NavBar.vue"

  export default {
    name: "APP",

    components:{
    NavBar,
},

  }
</script>

<style>
  body {
    background-image: url("@/assets/images/background.jpg");
    background-size: cover;
  }
</style>
