<template>
    <NavBar/>
    <router-view />
  <Footer ></Footer>
</template>

<script>
  import NavBar from "./components/NavBar.vue"
  import Footer from "./components/FooterBar.vue"

  export default {
    name: "APP",

    components:{
    NavBar,
    Footer,
    },

  }
</script>

<style>
  body {
    background-image: url("@/assets/images/background.jpg");
    background-size: cover;
  }
</style>
