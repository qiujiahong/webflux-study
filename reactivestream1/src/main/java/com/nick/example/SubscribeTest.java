package com.nick.example;

import java.util.Random;
import java.util.concurrent.SubmissionPublisher;
import java.util.concurrent.TimeUnit;

public class SubscribeTest {
    public static void main(String[] args) throws InterruptedException {
        //创建发布者(泛行是发布者生产消息类型)
        SubmissionPublisher<Integer> publisher = new SubmissionPublisher<>();
        //创建订阅者
        SomeSubscriber subscriber = new SomeSubscriber();
        //建立订阅关系
        publisher.subscribe(subscriber);
        //发布者生产并发送消息(生产300条消息)

        for (int i=0;i<300;i++){
            int item = new Random().nextInt(100);
            System.out.println("生产出第"+i+"条消息:"+item);
            //这个方法是阻塞方法，发布者缓存满时submit方法阻塞
            //因为发布者不具备无限缓存区
            publisher.submit(item);
        }
        //关闭发布者
        publisher.close();


        //防止消息没有消费完毕主线程退出
        TimeUnit.SECONDS.sleep(10);

    }
}
