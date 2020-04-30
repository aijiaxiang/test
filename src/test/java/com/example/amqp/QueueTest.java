package com.example.amqp;

import com.example.amqpfanoutprovider.FanoutSender;
import com.example.amqptopicprovider.OrderSender;
import com.example.amqptopicprovider.ProductSender;
import com.example.amqptopicprovider.UserSender;
import com.example.helloworld.HelloworldApplication;
import com.example.rabbitdurableprovider.DurableSender;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * Author:aijiaxiang
 * Date:2020/4/26
 * Description:
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = HelloworldApplication.class)
public class QueueTest {

    @Autowired
    private UserSender userSender;

    @Autowired
    private ProductSender productSender;

    @Autowired
    private OrderSender orderSender;

    @Autowired
    private FanoutSender fanoutSender;

    @Autowired
    private DurableSender durableSender;

    /**
     * 测试消息队列
     */
    @Test
    public void test1() throws InterruptedException {

            fanoutSender.send("hello");


    }

    @Test
    public void test3() throws InterruptedException {
        int flag = 0;
        while (true){
            flag++;
            Thread.sleep(2000);
            durableSender.send("hello--"+flag);

        }


    }

    @Test
    public void test2(){
        userSender.send("usersend");
        productSender.send("productsend");
        orderSender.send("ordersend");
    }
}
