package com.wz.mine.thread.sync;


public class TheLock {


    public synchronized void tryLock() throws InterruptedException {
        Thread.sleep(20000);
        System.out.println("TheLock trylock");
    }

    public synchronized void lock() throws InterruptedException {
        Thread.sleep(15000);
        System.out.println("TheLock lock");
    }

    public synchronized void unLock() throws InterruptedException {
        Thread.sleep(10000);
        System.out.println("TheLock unLock");
    }
}
