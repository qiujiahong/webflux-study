package com.nick.example.common;

import java.util.concurrent.Flow;
import java.util.concurrent.TimeUnit;

/**
 * 订阅者,发布不用实现，是jdk中自带的
 */
public class SomeSubscriber implements Flow.Subscriber {
    private Flow.Subscription subscription;

    // 当发布者第一次发布消息时会自动调用该方法
    @Override
    public void onSubscribe(Flow.Subscription subscription) {
        this.subscription = subscription;
        //设置订阅者首次向发布者（令牌）订阅消息的数量
        this.subscription.request(10);
    }

    //订阅者没接收一次消息数据，就会自动调用一次该方法
    //订阅者对数据的消费就发生在这里
    @Override
    public void onNext(Object item) {
        System.out.println("当前订阅者正在消费的消息为:" + item);
        try {
            TimeUnit.MICROSECONDS.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        //设置订阅者像发布者再次订阅10个消息
        //消费1条再订阅多条
        this.subscription.request(10);
        // if(xx){
        //        this.subscription.cancel();   //取消订阅
        //    }

    }

    //当订阅过程中出现异常时会自动调用
    @Override
    public void onError(Throwable throwable) {
        throwable.printStackTrace();
        //取消订阅消息
        this.subscription.cancel();
    }

    //当令牌中的所有消息全部消费完毕会自动调用该方法
    @Override
    public void onComplete() {
        System.out.println("所有消息消费完毕");
    }
}
