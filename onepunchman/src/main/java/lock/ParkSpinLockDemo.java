package lock;

import java.util.Queue;
import java.util.concurrent.locks.LockSupport;

import static java.lang.Thread.sleep;

/**
   * @author luoyuntian
   * @date 2020-09-26 15:16
   * @description 通过park与自旋的配合，
   */
public class ParkSpinLockDemo {
    //通过volatile关键字来使得state的值对于各个线程可见
    private volatile int state = 0;

    Queue parkQueue;
    //上锁，对state值进行CA操作
    public void lock(){
        //如果state的值是1，着一直自旋，
        //如果state的值是0，则将state的值赋值为1
        while(!compareAndSet(0,state)){
            park();

            // 陷入睡眠
        }

        unLock();
    }


    //解锁，就是将state的值赋值为0
    public void unLock(){
        state = 0;
        lock_notify();
    }

    private boolean compareAndSet(int except,int newValue){
        //如果期望值和新的值相同,则将newValue赋值给state，并返回false，向下执行
        //如果期望值与新的值不同,则返回true，不向下执行
        if(except == newValue){
            state = newValue;
            return false;
        }else {
            return true;
        }
    }


    private void park(){
        parkQueue.add(Thread.currentThread());
        //当前线程阻塞，释放cpu
        releaseCpu();
    }

    private void unPark(Thread t){
        //唤醒等待线程
    }

    private void lock_notify(){
        //得到要唤醒的线程队列中的第一个线程
        Thread t =  (Thread) parkQueue.poll();
        //唤醒等待线程
        unPark(t);
    }




    private void releaseCpu(){

    }
}
