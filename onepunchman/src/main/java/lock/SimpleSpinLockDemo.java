package lock;
/**
   * @author luoyuntian
   * @date 2020-09-26 15:04
   * @description 实现一个简单的自旋锁，此方法会使得不能拿到锁的线程一直自旋，使得cpu飙高
   */
public class SimpleSpinLockDemo {
    //通过volatile关键字来使得state的值对于各个线程可见
    private volatile int state = 0;


    //上锁，对state值进行CA操作
    public void lock(){
        //如果state的值是1，着一直自旋，
        //如果state的值是0，则将state的值赋值为1
        while(!compareAndSet(0,state)){
        }
    }


    //解锁，就是将state的值赋值为0
    public void unLock(){
        state = 0;
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


}
