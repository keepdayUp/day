package com.mashibing.designpattern;

import  java.util.concurrent.Flow;
import java.util.concurrent.SubmissionPublisher;

public class Java9FlowObserver {

    public static void main(String[] args) {

        observer();
    }
    public static void observer(){
        SubmissionPublisher publisher = new SubmissionPublisher();
        Flow.Subscriber subscriber = new Flow.Subscriber<String> (){
            private Flow.Subscription subscription;
            @Override
            public void onSubscribe(Flow.Subscription subscription) {
                System.out.println("开始订阅");
                this.subscription = subscription;
                subscription.request(1);
            }

            @Override
            public void onNext(String item) {
                System.out.println("订阅者收到消息"+item);
                subscription.request(1);
            }

            @Override
            public void onError(Throwable throwable) {
                System.out.println("订阅消息出错");
                subscription.cancel();
            }

            @Override
            public void onComplete() {
                System.out.println("订阅接收完成");
            }
        };
        publisher.subscribe(subscriber);

        for (int i = 0; i < 5; i++) {
            publisher.submit("第"+i+"条消息");
        }
        publisher.close();
        try{
            Thread.currentThread().join(2000);
        }catch (Exception e){

        }

    }

}
