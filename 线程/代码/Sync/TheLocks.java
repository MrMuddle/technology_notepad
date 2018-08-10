package com.wz.mine.thread.sync;

/**
 * @Package com.wz.mine.thread.sync
 * @author: wangzhao
 * @date: 2018/8/10 15:52
 * @Description TODO:(这里用一句话描述这个类的作用)
 */
public class TheLocks {

    private Object one=new Object();
    private Object two=new Object();
    private Object three=new Object();

    public void tryLock() throws InterruptedException {
        synchronized (one) {
            Thread.sleep(30000);
            System.out.println("TheLocks trylock");
        }

    }

    public void lock() throws InterruptedException {
        synchronized (two) {
            Thread.sleep(20000);
            System.out.println("TheLocks lock");
        }
    }

    public void unLock() throws InterruptedException {
        synchronized (three) {
            Thread.sleep(10000);
            System.out.println("TheLocks unLock");
        }
    }
}
