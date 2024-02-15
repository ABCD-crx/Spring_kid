package com.kob.botrunningsystem.service.impl.utils;

import org.joor.Reflect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.UUID;
import java.util.function.Supplier;

/**
 * Author: chen
 * Time: 2024/2/6 0:34
 */
@Component
public class Consumer extends Thread {
    private Bot bot;
    private static RestTemplate restTemplate;

    private static String receiveBotMoveUrl = "http://127.0.0.1:3000/pk/receive/bot/move/";

    @Autowired
    public void setRestTemplate(RestTemplate restTemplate) {
        Consumer.restTemplate = restTemplate;
    }


    public void startTimeout(long timeout, Bot bot) {
        this.bot = bot;
        this.start();   // 开一个新线程执行 run

        try {
            this.join(timeout);    // 当前线程继续执行join 最多等待timeout的时间
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            this.interrupt();   //中断线程
        }
    }

    private String addUid(String code, String uid) {    // 在code中Bot类名后加uid
        int k = code.indexOf(" implements java.util.function.Supplier<Integer>");
        return code.substring(0, k) + uid + code.substring(k);
    }

    @Override
    public void run() {
        // 动态编译字符串  类重名只编译一次
        UUID uuid = UUID.randomUUID();
        String uid = uuid.toString().substring(0, 8);    //生成一个随机字符串 拼再类名后

//        BotInterface botInterface = Reflect.compile(
//                "com.kob.botrunningsystem.utils.Bot" + uid,
//                addUid(bot.getBotCode(), uid)
//
//        ).create().get();

        Supplier<Integer> botInterface = Reflect.compile(
                "com.kob.botrunningsystem.utils.Bot" + uid,
                addUid(bot.getBotCode(), uid)

        ).create().get();

        // 将结果 由 接口函数参数传递 优化成 文件传输
        // 文件传输有利于执行后续非java代码
        File file = new File("input.txt");
        try (PrintWriter fout = new PrintWriter(file)) {

            fout.println(bot.getInput());
            fout.flush();   // 清空缓冲区

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }


        Integer direction = botInterface.get();
//        Integer direction = botInterface.nextMove(bot.getInput());

        System.out.println("move: " + bot.getUserId() + " " + direction);

        MultiValueMap<String, String> data = new LinkedMultiValueMap<>();
        data.add("user_id", bot.getUserId().toString());
        data.add("direction", direction.toString());
        restTemplate.postForObject(receiveBotMoveUrl, data, String.class);
    }
}
