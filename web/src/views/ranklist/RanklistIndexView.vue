<template>
    <ContentFiled>
        <table class="table table-striped table-hover">
            <thead>
                <tr>
                    <th>
                        <span>玩家</span>
                    </th>
                    <th>
                        <span>天梯分</span>
                    </th>
                </tr>
            </thead>
            <tbody>
                <tr v-for="user in users" :key="user.id" >
                    <td>
                        <img :src="user.photo" alt="" class="user-photo img-fluid">
                        &nbsp;
                        <span class="user-username">{{ user.username }}</span>
                    </td>
                    <td>
                        <span>{{ user.rating }}</span>
                    </td>
                </tr>
            </tbody>
        </table>
        <nav aria-label="...">
        <ul class="pagination" style="float: right; ">
            <li class="page-item" @click="click_page(-2)">
                <a class="page-link" href="#">前一页</a>
            </li>
            <li :class="'page-item ' + page.is_active" v-for="page in pages" :key="page.number" @click="click_page(page.number)">
                <a class="page-link" href="#">{{ page.number }}</a>
            </li>
            <li class="page-item" @click="click_page(-1)">
                <a class="page-link" href="#">后一页</a>
            </li>
        </ul>
        </nav>
    </ContentFiled>
</template>

<script>
import ContentFiled from '@/components/ContentFiled.vue'
import { useStore } from 'vuex';
import { ref } from 'vue';
import $ from 'jquery';

export default {
    name:'ranklist_index',
    components:{
        ContentFiled,
    },
    setup() {
        const store = useStore();
        let users = ref([]);
        let current_page = 1;
        let total_users = 0;
        let pages = ref([]);

        const click_page = page => {
            if (page === -2) page = current_page - 1;
            else if (page === -1) page = current_page + 1;
            let max_pages = parseInt(Math.ceil(total_users / 5));

            if(page >= 1 && page <= max_pages) {
                pull_page(page)
            }
        }
        
        // 计算分页前后两页是否存在
        const update_pages = () => {
            let max_pages = parseInt(Math.ceil(total_users / 5));
            let new_pages = [];
            for (let i = current_page - 2; i <= current_page + 2; i++) {
                if(i >= 1 && i <= max_pages) {
                    new_pages.push({
                        number: i,
                        is_active: i === current_page ? "active" : "",
                    });
                }
            }
            pages.value = new_pages;
        }
        
        const pull_page = page => {
            current_page = page;
            $.ajax({
                url: "http://127.0.0.1:3000/ranklist/getlist/",
                type: "GET",
                data: {
                    page
                },
                headers: {
                    'Authorization': "Bearer " + store.state.user.token,
                },
                success(resp) {
                    users.value = resp.users;
                    total_users = resp.users_count;
                    update_pages();
                },
                error(resp) {
                    console.log(resp);
                }

            })
        }
        pull_page(current_page);

        return {
            users,
            pages,
            click_page,
        }

    }
}
</script>

<style scoped>
img.user-photo {
    width: 8vh;
    border-radius: 50%;
}
span {
    font-weight: bold;
    font-size: 20px;
}
.table {
    text-align: center; 
    
}
</style>

