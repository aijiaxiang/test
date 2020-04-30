package com.example.helloworld.demo04;

/**
 * Author:aijiaxiang
 * Date:2020/3/10
 * Description:
 */
public class SynchronizedTest implements Runnable{
    //共享资源
    static int i =0;
    /**
     * synchronized 修饰实例方法
     */
    public  void increase(){
        i++;
    }
    @Override
    public synchronized void run(){
        for (int j =0 ; j<10000;j++){
            increase();
        }
    }

    public static void main(String[] args) throws InterruptedException {
        SynchronizedTest test = new SynchronizedTest();
        Thread t1 = new Thread(test);
        Thread t2 = new Thread(test);
        t1.start();
        t2.start();
        t1.join();
        t2.join();
        System.out.println(i);
    }

}
