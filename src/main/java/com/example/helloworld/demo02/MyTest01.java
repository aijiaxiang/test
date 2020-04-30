package com.example.helloworld.demo02;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Author:aijiaxiang
 * Date:2020/3/4
 * Description:
 */
public class MyTest01 {
    public static void main(String[] args) {

    }

    private static void test1(){
        //1:使用工厂类获取线程池对象
        ExecutorService executorService = Executors.newFixedThreadPool(3);
        //2:提交任务
        for(int i=1;i<11;i++){
            executorService.submit(new MtRunnable(i));
        }
    }
    private static void test2(){
        //1:使用工厂类获取线程池对象
        ExecutorService executorService = Executors.newCachedThreadPool();
        //2:提交任务
        for(int i=1;i<11;i++){
            executorService.submit(new MtRunnable(i));
        }
    }

}
/*
* 任务类，包含了一个任务编号，在任务中，打印出是哪个线程正在执行任务
* */
class MtRunnable implements Runnable{
    private int id;

    public MtRunnable(int id) {
        this.id = id;
    }

    @Override
    public void run() {
        //获取线程名称，打印一句话
        String name = Thread.currentThread().getName();
        System.out.println(name+"执行了任务。。");
    }
}
