<template>
  <NavBar/>
  <div id="v-content" v-bind:style="{minHeight: Height + 'px'}">
    <router-view />
  </div>
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

    data() {
      return {
       Height: 0
     }
    },
    mounted(){
      this.Height = document.documentElement.clientHeight - 120;  
      window.onresize = ()=> {this.Height = document.documentElement.clientHeight - 120}
    }
    
}
  
</script>

<style>
  body {
    background-image: url("@/assets/images/background.jpg");
    background-size: cover;
  }
</style>
