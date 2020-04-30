package com.example.rabbitackprovider;

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
public class AckSender {

    @Autowired
    private AmqpTemplate amqpTemplate;

    //exChange 交换器
    @Value("${mq.config.exchange}")
    private String exChange;

    //routingkey 路由键
    @Value("${mq.config.queue.error.routing.key}")
    private String routingKey;
    /**
     * 发送消息的方法
     * @param msg
     */
    public void send(String msg){
        //向消息队列发送消息
        //参数1：交换器名称
        //参数2：路由键
        //参数3：消息
        this.amqpTemplate.convertAndSend(exChange,routingKey,msg);
        //认为制造报错
        int a = 2/0;

    }
}
