<template>
     <p style="background:none;height: 5%;bottom: 0px;width: 100%;text-align: center;">
        <a class="beian" 
            href="https://beian.miit.gov.cn/#/Integrated/index"
            target="_blank">陕ICP备2024027990号
        </a>
        &nbsp;&nbsp;
        <img src="@/assets/images/police_beian.png">
        <a class = "beian" 
            href="https://beian.mps.gov.cn/#/query/webSearch?code=61011102000609" 
            rel="noreferrer" target="_blank">陕公网安备61011102000609号</a>
    </p>
</template>

<script>
</script>


<style scoped>
.beian:link{
    color: black;
}

.beian:hover{
    color: #409EFF;
    text-decoration: underline; 
}

.beian:active {
    color: blue;
}

.beian {
    color: #000;
    /* 去掉下划线 */
    text-decoration: none; 
}
</style>