package lock;

import java.util.concurrent.locks.ReentrantLock;

/**
   * @author luoyuntian
   * @date 2020-09-26 14:32
   * @description 使用ReentrantLock控制两个线程的输出顺序
   */
public class FirstReentrantLockTest {

    static private ReentrantLock reentrantLock = new ReentrantLock(true);
    public static void main(String[] args) {
//        for(int i=0;i<100;i++){
//            Thread temp = new Thread(){
//                @Override
//                public void run() {
//                    testSyncMethod();
//                }
//            };
//            temp.setName("Thread-"+i);
//            temp.start();
//        }
        Thread t1 = new Thread(){
            @Override
            public void run() {
                testSyncMethod();
            }
        };
        t1.setName("t1");
        t1.start();
    }

    public static void testSyncMethod(){
        reentrantLock.lock();
        try{
            int random = (int)(100*Math.random());
            Thread.sleep(random);
            System.out.println(Thread.currentThread().getName()+" : "+random+"ms");
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            reentrantLock.unlock();
        }

    }

}
