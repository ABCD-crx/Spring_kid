<template>
    <div class="container">
        <div class="row">
            <div class="col-3">
                <div class="card" >
                    <div class="card-body">
                        <img :src="$store.state.user.photo" alt="" style="width: 100%;">
                    </div>
                </div>
            </div>
            <div class="col-9">
                <div class="card">
                    <div class="card-header">
                        <span style="font-size: 130%;">我的Bot</span>
                        <button type="button" class="btn btn-dark float-end" data-bs-toggle="modal" data-bs-target="#add-bot-btn">
                            创建Bot
                        </button>
                        <!-- Modal -->
                        <div class="modal fade" id="add-bot-btn" tabindex="-1" >
                        <div class="modal-dialog modal-xl">
                            <div class="modal-content">
                            <div class="modal-header">
                                <h1 class="modal-title fs-5" id="add-bot">创建Bot</h1>
                                <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                            </div>
                            <div class="modal-body">
                                <div class="mb-3">
                                    <label for="add-bot-title" class="form-label">名称</label>
                                    <input v-model="botadd.title" type="text" class="form-control" id="add-bot-title" placeholder="请输入Bot名称">
                                </div>
                                <div class="mb-3">
                                    <label for="add-bot-description" class="form-label">简介</label>
                                    <textarea v-model="botadd.description" class="form-control" id="add-bot-description" rows="3" placeholder="请输入Bot简介"></textarea>
                                </div>
                                <div class="mb-3">
                                    <label for="add-bot-code" class="form-label">BOT代码</label>
                                    <VAceEditor v-model:value="botadd.content"  lang="c_cpp"
                                                theme="textmate" style="height: 300px" 
                                                :options="{
                                                    enableBasicAutocompletion: true, //启用基本自动完成
                                                    enableSnippets: true, // 启用代码段
                                                    enableLiveAutocompletion: true, // 启用实时自动完成
                                                    fontSize: 18, //设置字号
                                                    tabSize: 4, // 标签大小
                                                    showPrintMargin: false, //去除编辑器里的竖线
                                                    highlightActiveLine: true,
                                                }" >
                                    </VAceEditor>
                                </div>
                            </div>
                            <div class="modal-footer">
                                <div class="error-message">{{ botadd.error_message }}</div>
                                <button type="button" class="btn btn-primary" @click="add_bot">创建</button>
                                <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">取消</button>
                            </div>
                            </div>
                        </div>
                        </div>
                        
                    </div>
                    <div class="card-body">
                        <table class="table table-striped table-hover">
                            <thead>
                                <tr>
                                    <th>名称</th>
                                    <th>创建时间</th>
                                    <th>操作</th>
                                </tr>
                            </thead>
                            <tbody>
                                <tr v-for="bot in bots" :key="bot.id">
                                    <td>{{ bot.title }}</td>
                                    <td>{{ bot.createtime }}</td>
                                    <td>
                                        <button type="button" class="btn btn-primary" style="margin-right: 10px;" data-bs-toggle="modal" :data-bs-target="'#update-bot-modal' + bot.id">
                                            修改
                                        </button>
                                        <button type="button" class="btn btn-danger" @click="remove_bot(bot)">删除</button>

                                        <!-- Modal -->
                                        <div class="modal fade" :id="'update-bot-modal' + bot.id" tabindex="-1" >
                                        <div class="modal-dialog modal-xl">
                                            <div class="modal-content">
                                            <div class="modal-header">
                                                <h1 class="modal-title fs-5" id="update-bot-btn">修改Bot</h1>
                                                <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                                            </div>
                                            <div class="modal-body">
                                                <div class="mb-3">
                                                    <label for="update-bot-title" class="form-label">名称</label>
                                                    <input v-model="bot.title" type="text" class="form-control" id="update-bot-title" placeholder="请输入Bot名称">
                                                </div>
                                                <div class="mb-3">
                                                    <label for="update-bot-description" class="form-label">简介</label>
                                                    <textarea v-model="bot.description" class="form-control" id="update-bot-description" rows="3" placeholder="请输入Bot简介"></textarea>
                                                </div>
                                                <div class="mb-3">
                                                    <label for="update-bot-code" class="form-label">BOT代码</label>
                                                    <VAceEditor v-model:value="bot.content" 
                                                                lang="c_cpp" theme="textmate" style="height: 300px"
                                                                :options="{
                                                                    enableBasicAutocompletion: true, //启用基本自动完成
                                                                    enableSnippets: true, // 启用代码段
                                                                    enableLiveAutocompletion: true, // 启用实时自动完成
                                                                    fontSize: 18, //设置字号
                                                                    tabSize: 4, // 标签大小
                                                                    showPrintMargin: false, //去除编辑器里的竖线
                                                                    highlightActiveLine: true,
                                                                    theme: 'ace/theme/terminal',
                                                                }" />  
                                                </div>
                                            </div>
                                            <div class="modal-footer">
                                                <div class="error-message">{{ error_message }}</div>
                                                <button type="button" class="btn btn-primary" @click="update_bot(bot)">保存修改</button>
                                                <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">取消</button>
                                            </div>
                                            </div>
                                        </div>
                                        </div>
                                    </td>

                                </tr>
                            </tbody>

                        </table>

                    </div>
                </div>
            </div>
        </div>
    </div>
</template>

<script>
import $ from 'jquery';
import { ref , reactive} from 'vue';    //ref 绑定一个变量 reactive绑定一个对象
import { useStore } from 'vuex';
import { Modal } from 'bootstrap/dist/js/bootstrap';
import { VAceEditor } from 'vue3-ace-editor';
import ace from 'ace-builds';

// 添加语法高亮
import 'ace-builds/src-noconflict/mode-c_cpp';
import 'ace-builds/src-noconflict/mode-java';
import 'ace-builds/src-noconflict/mode-json';
import 'ace-builds/src-noconflict/theme-chrome';
import 'ace-builds/src-noconflict/ext-language_tools';
//编辑器主题
import 'ace-builds/src-noconflict/theme-terminal';

export default {
    name:'user_bot_index',

    components: {
        VAceEditor,
    },

    setup() {
        const content_default = "";

        ace.config.set(
            "basePath", 
            "https://cdn.jsdelivr.net/npm/ace-builds@" + require('ace-builds').version + "/src-noconflict/")

        const store = useStore();
        let bots = ref([]);
        let error_message = ref('');

        const botadd = reactive({
            title: "",
            description: "",
            content: content_default,
            error_message: "",
        });

        const refresh_bots = () => {
            $.ajax({
                url: "https://chenrx.top/api/user/bot/getlist/",
                type: "GET",
                headers: {
                    'Authorization': "Bearer " + store.state.user.token,
                },
                success(resp) {
                    bots.value = resp;
                },

            })
        }

        refresh_bots()

        const add_bot = () => {
            botadd.error_message = "";
            $.ajax({
                url: "https://chenrx.top/api/user/bot/add/",
                type: "POST",
                headers: {
                    'Authorization': "Bearer " + store.state.user.token,
                },
                data: {
                    title: botadd.title,
                    description: botadd.description,
                    content: botadd.content,
                },
                success(resp) {
                    if(resp.error_message === "success") {
                        botadd.title = "",
                        botadd.description = "",
                        botadd.content = content_default,
                        Modal.getInstance("#add-bot-btn").hide();
                        refresh_bots();
                    } else {
                         botadd.error_message = resp.error_message;
                    }

                }
            })
        }

        const remove_bot = (bot) => {
            $.ajax({
                url: "https://chenrx.top/api/user/bot/remove/",
                type: "POST",
                headers: {
                    'Authorization': "Bearer " + store.state.user.token,
                },
                data: {
                    bot_id: bot.id,
                },
                success(resp) {
                    if(resp.error_message === "success") {
                        refresh_bots();
                    } else {
                         botadd.error_message = resp.error_message;
                    }
                }
            })
        }

        const update_bot = (bot) => {
            error_message.value = "";
            $.ajax({
                url: "https://chenrx.top/api/user/bot/update/",
                type: "POST",
                headers: {
                    'Authorization': "Bearer " + store.state.user.token,
                },
                data: {
                    bot_id: bot.id,
                    title: bot.title,
                    description: bot.description,
                    content: bot.content,
                },
                success(resp) {
                    if(resp.error_message === "success") {
                        Modal.getInstance("#update-bot-modal" + bot.id).hide();
                        refresh_bots();
                    } else {
                        error_message.value = resp.error_message;
                    }

                }
            })
        }

        return {
            bots,
            botadd,
            error_message,
            add_bot,
            remove_bot,
            update_bot,
        }
    }

}
</script>

<style scoped>
.card {
    margin-top: 20px;
}
div.error-message{
    color: red;
}
</style>