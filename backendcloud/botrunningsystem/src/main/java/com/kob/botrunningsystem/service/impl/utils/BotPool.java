package com.kob.botrunningsystem.service.impl.utils;

import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Author: chen
 * Time: 2024/2/6 0:07
 */
public class BotPool extends Thread{

    private final ReentrantLock lock = new ReentrantLock();
    // 条件变量
    private final Condition condition = lock.newCondition();
    private final Queue<Bot> bots = new LinkedList<>();

    public void addBot(Integer userId, String botCode, String input) {
        lock.lock();
        try {
            bots.add(new Bot(userId, botCode, input));
            condition.signalAll();     // 唤醒线程
        } finally {
            lock.unlock();
        }
    }

    // 目前只支持java代码执行 借助jooR
    // 如果想支持其他语言 需要借助docker 再java执行终端命令 在docker中跑 跑完返回结果就可
    private void consume(Bot bot) {
        // 在consume 再开一个线程 方便操作 每个代码的执行时间 一但超时自动终止

        Consumer consumer = new Consumer();
        consumer.startTimeout(2000, bot);
    }

    @Override
    public void run() {     //就是实现了一个消息队列
        // 如果线程为空 则阻塞线程 一旦有任务则立即唤醒
        while (true) {
            lock.lock();
            if (bots.isEmpty()) {
                try {
                    condition.await();      // 阻塞线程
                } catch (InterruptedException e) {
                    e.printStackTrace();
                    lock.unlock();
                    break;
                }
            } else {
                Bot bot = bots.remove();    //取出并删除队头
                lock.unlock();
                consume(bot);      // 比较耗时，可能执行几秒中
            }
        }
    }
}
