package com.example.amqptopicprovider;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * Author:aijiaxiang
 * Date:2020/4/26
 * Description:模拟商品服务发送消息
 */
@Component
public class ProductSender {

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
        //参数1：队列名称
        //参数2：消息
        // this.amqpTemplate.convertAndSend("hello-queue",msg);

        //向消息队列发送消息
        //参数1：交换器名称
        //参数2：路由键
        //参数3：消息
        this.amqpTemplate.convertAndSend(exChange,"product.log.debug","product.log.debug-"+msg);
        this.amqpTemplate.convertAndSend(exChange,"product.log.info","product.log.info-"+msg);
        this.amqpTemplate.convertAndSend(exChange,"product.log.warn","product.log.warn-"+msg);
        this.amqpTemplate.convertAndSend(exChange,"product.log.error","product.log.error-"+msg);

    }
}
