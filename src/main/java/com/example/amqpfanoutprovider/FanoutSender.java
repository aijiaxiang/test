package com.example.amqpfanoutprovider;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * Author:aijiaxiang
 * Date:2020/4/26
 * Description:发送消息
 */
@Component
public class FanoutSender {

    @Autowired
    private AmqpTemplate amqpTemplate;

    //exChange 交换器
    @Value("${mq.config.exchange}")
    private String exChange;

    /**
     * 发送消息的方法
     * @param msg
     */
    public void send(String msg){
        //向消息队列发送消息
        //参数1：交换器名称
        //参数2：路由键,广播模式时（fanout交换器）没有路由键使用""空字符串代替
        //参数3：消息
        this.amqpTemplate.convertAndSend(exChange,"",msg);
        System.out.println("hh");

    }
}
