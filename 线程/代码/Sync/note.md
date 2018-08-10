声明方法使用synchronized，以及使用synchronized(this){}代码块，
会导致当前线程锁定同步方法的类，同类下的其他同步方法将等待执行。

Object o1 = new Object();
Object o2 = new Object();
Object o3 = new Object();
synchronized(o1){}
synchronized(o2){}
synchronized(o3){}
如上同步方法块不影响多线程同步方法的调用。

实例对象同步方法，类静态同步方法无区别。
