<template>
<nav class="navbar navbar-expand-lg navbar-dark bg-dark">
  <div class="container">
    <router-link class="navbar-brand" :to="{name: 'home'}">King of bots</router-link>
    <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarText" aria-controls="navbarText" aria-expanded="false" aria-label="Toggle navigation">
      <span class="navbar-toggler-icon"></span>
    </button>
    <div class="collapse navbar-collapse" id="navbarText">
      <ul class="navbar-nav me-auto mb-1 mb-lg-0">
          
        <li class="nav-item">
          <router-link class="nav-link" active-class="active" :to="{name:'pk_index'}">对战</router-link>
        </li>
        <li class="nav-item">
          <router-link class="nav-link" active-class="active" :to="{name:'record_index'}">对局</router-link>
        </li>
        <li class="nav-item">
          <router-link class="nav-link" active-class="active" :to="{name:'ranklist_index'}">排行榜</router-link>
        </li>
      </ul>
      <ul class="navbar-nav me-5" v-if="$store.state.user.is_login">
        <li class="nav-item dropdown">
          <a class="nav-link dropdown-toggle" href="#" role="button" data-bs-toggle="dropdown" aria-expanded="false">
            {{ $store.state.user.username }}
          </a>
          <ul class="dropdown-menu">
            <router-link class="dropdown-item" :to="{name:'user_bot_index'}">我的Bot</router-link>
            <li><hr class="dropdown-divider"></li>
            <li><a class="dropdown-item" href="#" @click="logout">退出</a></li>
          </ul>
        </li>
      </ul>

      <ul class="navbar-nav me-5" v-else>
        <li class="nav-item">
          <router-link class="nav-link" active-class="active" :to="{name: 'user_account_login'}" role="button">登录</router-link>
        </li>
        <li class="nav-item">
          <router-link class="nav-link" active-class="active" :to="{name: 'user_account_register'}" role="button">注册</router-link>
        </li>
      </ul>
    </div>
  </div>
</nav>
</template>

<script>
import { computed } from 'vue';
import { useRoute } from 'vue-router';
import { useStore } from 'vuex';

export default {
  setup(){
    const store = useStore();
    const route = useRoute();
    let route_name = computed(() => route.name)

    const logout = () => {
      store.dispatch("logout");
    }

    return{
      route_name,
      logout,
    }
  }
}

</script>


<style scoped>

</style>