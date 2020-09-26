package lock;

import java.util.concurrent.locks.LockSupport;
/**
   * @author luoyuntian
   * @date 2020-09-26 15:40
   * @description 对ReentrantLock中的重要方法park进行说明，在调用park方法后进入睡眠，只有unpark才能唤醒线程
   */
public class FirstParkTest {
    public static void main(String[] args) {
        Thread t1 = new Thread(){
            @Override
            public void run() {
                testSyncMethod();
            }
        };
        t1.setName("t1");
        t1.start();
        System.out.println(Thread.currentThread().getName());
        LockSupport.unpark(t1);

    }

    public static void testSyncMethod(){
        System.out.println(Thread.currentThread().getName()+" "+"- 1");
        //睡眠，没有调用unpark之前是不会醒的
        LockSupport.park();
        System.out.println(Thread.currentThread().getName()+" "+"- 2");
    }
}
