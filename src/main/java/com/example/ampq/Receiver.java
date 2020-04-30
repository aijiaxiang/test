//package com.example.ampq;
//
//import org.springframework.amqp.rabbit.annotation.RabbitListener;
//import org.springframework.stereotype.Component;
//
///**
// * Author:aijiaxiang
// * Date:2020/4/26
// * Description:消息接收者
// */
//@Component
//public class Receiver {
//
//    /**
//     * 接收消息的方法，采用消息队列监听机制
//     *
//     * 使用 @RabbitListener 注解标记方法，当监听到队列 hello-queue 中有消息时则会进行接收并处理
//     *
//     * @param msg
//     */
//    @RabbitListener(queues = "hello-queue")
//    public void process(String msg){
//        System.out.println("receiver："+msg);
//    }
//}
