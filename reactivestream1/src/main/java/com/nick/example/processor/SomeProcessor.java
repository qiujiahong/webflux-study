package com.nick.example.processor;

import java.util.concurrent.Flow;
import java.util.concurrent.SubmissionPublisher;
import java.util.concurrent.TimeUnit;

public class SomeProcessor extends SubmissionPublisher<String> implements Flow.Processor<Integer,String> {
    private Flow.Subscription subscription;

    // 当发布者第一次发布消息时会自动调用该方法
    @Override
    public void onSubscribe(Flow.Subscription subscription) {
        this.subscription = subscription;
        this.subscription.request(10);
    }

    //将大于50的过滤掉
    //将小于50的消息转化成为字符串string传递给订阅者
    @Override
    public void onNext(Integer item) {
        System.out.println("当前订阅者正在消费的消息为:" + item);

        if(item <50){
            this.submit("该消息处理完毕:"+item);
        }
        try {
            TimeUnit.MICROSECONDS.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        this.subscription.request(10);

    }


    @Override
    public void onError(Throwable throwable) {
        throwable.printStackTrace();
        this.subscription.cancel();
    }

    //当令牌中的所有消息全部处理完毕会自动调用该方法
    @Override
    public void onComplete() {
        System.out.println("所有消息消费完毕");
    }
}
