

/**
 * @Package com.wz.mine.thread.sync
 * @date: 2018/8/10 15:36
 * @Description TODO:(这里用一句话描述这个类的作用)
 */
public class LockTest {

    public static void main(String[] args) throws InterruptedException {
        final  TheLock thelock = new TheLock();
        while (true){
            new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        System.out.println("thelock init trylock");
                        thelock.tryLock();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }).start();
            new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        System.out.println("thelock init unLock");
                        thelock.unLock();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }).start();

            final TheLocks locks = new TheLocks();
            new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        System.out.println("TheLocks init trylock");
                        locks.tryLock();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }).start();
            new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        System.out.println("TheLocks init lock");
                        locks.lock();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }).start();
            new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        System.out.println("TheLocks init unLock");
                        locks.unLock();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }).start();

            Thread.sleep(32000);
        }


    }

}
