<template>
    <ContentFiled>
        <h3>Snake of Bot</h3>
        <div class="card">
            <div class="card-body">
                <h4 class="card-title">游戏规则</h4>
                <p class="card-text">
                    确切地说，这并不是贪吃蛇。 与传统单人贪吃蛇不同的是，本贪吃蛇为双人对战，每回合玩家同时做出决策控制自己的蛇。
                </p>
                <p class="card-text">玩家在13*14的网格中操纵一条蛇(蛇是一系列坐标构成的有限不重复有顺序的序列，序列中相邻坐标均相邻，
                    即两坐标的x轴坐标或y轴坐标相同，坐标从0开始，x轴代表行，y轴代表列，序列中最后一个坐标代表蛇头)，
                    玩家只能控制蛇头的朝向(上、下、左、右)来控制蛇。蛇以恒定的速度前进(前进即为序列末尾插入蛇头指向方向下一格坐标，并删除序列头坐标)。
                    蛇的初始位置在网格中的左下角(地图位置[11,1])与右上角(地图位置[1,12])，初始长度为1格。与传统贪吃蛇不同，本游戏在网格中并没有豆子，但蛇会自动长大(长大即为不删除序列头坐标的前进)，
                    前10回合每回合长度增加1，从第11回合开始，每3回合长度增加1。
                </p>
                <p class="card-text">地图为13*14的网格，由1*1的草地与障碍物构成。</p>
                <p class="card-text">蛇头在网格外、障碍物、自己蛇的身体(即序列重复)、对方蛇的身体(即与对方序列有相同坐标)，或非法操作均判定为死亡。任何一条蛇死亡时，游戏结束。若蛇同时死亡，判定为平局，否则先死的一方输，另一方赢。</p>
            </div>
        </div>

        <div class="card" style="margin-top: 2vh;">
            <div class="card-body">
                <h4 class="card-title">游戏交互方式</h4>
                <p class="card-text">若玩家选择亲自出马，玩家可直接通过'w'、'a'、's'、'd'键或↑↓←→方向键控制己方蛇的运动方向。</p>
                <p class="card-text">若玩家选择派出Bot出战，则Bot代码每回合需从标准输入读入一个字符串，并在标准输出中输出一个数字代表己方蛇运动方向。</p>
                <p class="card-text">输入给定一个字符串，表示当前局势，输出在此局势下您所操纵蛇的最佳运动方向。每回合，我们将运行一遍您的代码，并根据代码输出的运动方向作为本回合您的蛇的运动方向。</p>
                <p class="card-text">输入格式: &lt;初始地图信息&gt;#&lt;自己的蛇的初始x轴坐标&gt;#&lt;自己的蛇的初始y轴坐标&gt;#(&lt;自己的蛇自开始以来每回合的运动方向&gt;)#&lt;对手的蛇的初始x轴坐标&gt;#&lt;对手的蛇的初始y轴坐标&gt;#(&lt;对手的蛇自开始以来每回合的运动方向&gt;)</p>
                <p class="card-text">输出格式: 一个整数，代表运动方向。0代表向上，1代表向右，2代表向下，3代表向左。</p>
                <p class="card-text">输入样例： 11111111111111100011010000011001000000000110001100001001100000001000011001000000000110000100100001100000000010011000010000000110010000110001100000000010011000001011000111111111111111#11#1#(011101210010)#1#12#(233322233033) </p>
                <p class="card-text">输出样例: <br>3</p>
                <p class="card-text"> 样例解释：<br> 对于输入，分别拆解。<br> 地图信息：11111111111111100011010000011001000000000110001100001001100000001000011001000000000110000100100001100000000010011000010000000110010000110001100000000010011000001011000111111111111111<br> 
                    二维地图信息被按行压缩成一维，地图位置[i,j](0≤i≤12, 0≤j≤13)信息在第i*14+j位，1表示该位置为障碍物，0表示为草地。<br> 自己的蛇的初始x轴坐标：11。<br> 自己的蛇的初始y轴坐标：1。<br> 自己的蛇自开始以来每回合的运动方向：011101210010。<br> 对手的蛇的初始x轴坐标：1。<br> 
                    对手的蛇的初始y轴坐标：12。<br> 对手的蛇自开始以来每回合的运动方向：233322233033。<br> 对于这样的局势，输出为3，即向左运动。 
                </p>
            </div>
        </div>
        <div class="card" style="margin-top: 2vh;">
            <div class="card-body">
                <h5 class="card-title" style="margin-bottom: 1vh;">样例程序（Java版本）</h5>
                <div class=" ace_editor ace_hidpi ace-tm" style="font-size: 16px; height: 1900px;">
                    <VAceEditor  v-model:value="content" lang="c_cpp"
                        theme="textmate" style="height: 1900px" 
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
        </div>
        <div class="card" style="margin-top: 2vh;">
            <div class="card-body">
                <h5 class="card-title" style="margin-bottom: 1vh;">input.txt文件存储信息格式</h5>
                <div class=" ace_editor ace_hidpi ace-tm" style="font-size: 16px; height: 180px;">
                    <VAceEditor  v-model:value="content_input" lang="c_cpp"
                        theme="textmate" style="height: 180px" 
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
        </div>

    </ContentFiled>
</template>

<script>
import ContentFiled from '@/components/ContentFiled.vue'
import { VAceEditor } from 'vue3-ace-editor';

// 添加语法高亮
import 'ace-builds/src-noconflict/mode-c_cpp';
import 'ace-builds/src-noconflict/mode-java';
import 'ace-builds/src-noconflict/mode-json';
import 'ace-builds/src-noconflict/theme-chrome';
import 'ace-builds/src-noconflict/ext-language_tools';

export default {
    name:'rules_index',
    components:{
        ContentFiled,
        VAceEditor,
    },  
    setup() {
        const content = 
            "package com.kob.botrunningsystem.utils;\n\n" + 
            "import java.io.File;\n" +  
            "import java.io.FileNotFoundException;\n" +  
            "import java.util.ArrayList;\n" +  
            "import java.util.List;\n" +  
            "import java.util.Scanner;\n\n" +  
            "// 前端模块代码接口\n" +  
            "public class Bot implements java.util.function.Supplier<Integer> {\n" +  
            "    static class Cell {\n" +  
            "        public int x, y;\n" +  
            "        public Cell(int x, int y) {\n" +  
            "            this.x = x;\n" +  
            "            this.y = y;\n" +  
            "        }\n" +  
            "    }\n\n" +  
            "    private boolean check_tail_increasing(int step) {  // 检验当前回合，蛇的长度是否增加\n" +  
            "        if (step <= 10) return true;\n" +  
            "        return step % 3 == 1;\n" +  
            "    }\n\n" +  
            "    public List<Cell> getCells(int sx, int sy, String steps) {\n" +  
            "        steps = steps.substring(1, steps.length() - 1);\n" +  
            "        List<Cell> res = new ArrayList<>();\n\n" +  
            "        int[] dx = {-1, 0, 1, 0}, dy = {0, 1, 0, -1};\n" +  
            "        int x = sx, y = sy;\n" +  
            "        int step = 0;\n" +  
            "        res.add(new Cell(x, y));\n" +  
            "        for (int i = 0; i < steps.length(); i ++ ) {\n" +  
            "            int d = steps.charAt(i) - '0';\n" +  
            "            x += dx[d];\n" +  
            "            y += dy[d];\n" +  
            "            res.add(new Cell(x, y));\n" +  
            "            if (!check_tail_increasing( ++ step)) {\n" +  
            "                res.remove(0);\n" +  
            "            }\n" +  
            "        }\n" +  
            "        return res;\n" +  
            "    }\n\n" +  
            "    public Integer nextMove(String input) {\n" +  
            "        String[] strs = input.split(\"#\");\n" +  
            "        int[][] g = new int[13][14];\n" +  
            "        for (int i = 0, k = 0; i < 13; i ++ ) {\n" +  
            "            for (int j = 0; j < 14; j ++, k ++ ) {\n" +  
            "                if (strs[0].charAt(k) == '1') {\n" +  
            "                    g[i][j] = 1;\n" +  
            "                }\n" +  
            "            }\n" +  
            "        }\n\n" +  
            "        int aSx = Integer.parseInt(strs[1]), aSy = Integer.parseInt(strs[2]);\n" +  
            "        int bSx = Integer.parseInt(strs[4]), bSy = Integer.parseInt(strs[5]);\n\n" +  
            "        List<Cell> aCells = getCells(aSx, aSy, strs[3]);\n" +  
            "        List<Cell> bCells = getCells(bSx, bSy, strs[6]);\n\n" +  
            "        for (Cell c: aCells) g[c.x][c.y] = 1;\n" +  
            "        for (Cell c: bCells) g[c.x][c.y] = 1;\n\n" +  
            "        int[] dx = {-1, 0, 1, 0}, dy = {0, 1, 0, -1};\n" +  
            "        for (int i = 0; i < 4; i ++ ) {\n" +  
            "            int x = aCells.get(aCells.size() - 1).x + dx[i];\n" +
            "            int y = aCells.get(aCells.size() - 1).y + dy[i];\n" +  
            "            if (x >= 0 && x < 13 && y >= 0 && y < 14 && g[x][y] == 0) {\n" +  
            "                return i;\n" +  
            "            }\n" +  
            "        }\n" +  
            "        // 如果四个方向都不行，则随机选择一个方向\n" +  
            "        return (int)(Math.random() * 4);\n" +  
            "    }\n" +  
            "\n" +  
            "    //文件读写(input.txt文件中存着这局游戏的地图和两条蛇的起始坐标和走的方向，\n" +
            "    //以便可以还原出每一条蛇，详情看下方input.txt每个字段以#隔开蛇的方向存在（）括号)\n" +
            "    @Override\n" +  
            "    public Integer get() {\n" +  
            "        File file = new File(\"input.txt\");\n" +  
            "        try {\n" +  
            "            Scanner sc = new Scanner(file);\n" +  
            "            return nextMove(sc.next());\n" +  
            "        } catch (FileNotFoundException e) {\n" +  
            "            e.printStackTrace();\n" +  
            "        }\n" +  
            "        return null;\n" +  
            "    }\n" +  
            "}\n"
        ;

        const content_input = 
                'getMapString()+"#"+\n' +
                '    me.getSx()+"#"+\n' +
                '    me.getSy()+"#"+\n' +
                '    "("+me.getStepsString()+")#"+\n' +
                '    you.getSx()+"#"+\n' +
                '    you.getSy()+"#"+\n' +
                '    "("+you.getStepsString()+")";\n'
        ;

        return {
            content,
            content_input
        }
    }
}
</script>

<style scoped>

h4 {
    font-weight: bold;
}
h5 {
    font-weight: bold;
}
</style>
