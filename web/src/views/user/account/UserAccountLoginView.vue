<template>
    <ContentFiled>
        <div class="row justify-content-md-center" >
            <div class="col-3" >
                <form @submit.prevent="login">
                    <div class="mb-3">
                        <label for="username" class="username">用户名</label>
                        <input v-model="username" type="text" class="form-control" id="username" placeholder="请输入用户名">
                    </div>
                    <div class="mb-3">
                        <label for="password" class="password">密码</label>
                        <input v-model="password" type="password" class="form-control" id="password" placeholder="请输入密码">
                    </div>
                    <div class="error-message">{{ error_message }}</div>
                    <button type="submit" class="btn btn-primary">登录</button>
                </form>
            </div>
        </div>
    </ContentFiled>
</template>

<script>
import ContentFiled from '@/components/ContentFiled.vue'
import { useStore } from 'vuex';
import { ref } from 'vue';
import router from '@/router/index';

export default {
    name:'user_login',
    components:{
        ContentFiled,
    },
    setup() {
        const store = useStore();
        let username = ref('');
        let password = ref('');
        let error_message = ref('');




        const login = () => {
            error_message.value = "";
            store.dispatch("login", {
                username: username.value,
                password: password.value,
                success() {
                    store.dispatch("getinfo",{
                        success() {
                            router.push( {name: 'home'});
                        },
                        error() {
                            error_message = "注册未成功请稍后重试"
                        }
                    })
                },
                error() {
                    error_message.value = "用户名或密码输入错误"
                }
            })
        }

        return {
            username,
            password,
            error_message,
            login,
        }
    }
}
</script>

<style scoped>
button {
    width: 100%;
}
.error-message {
    color: red;
}
</style>
