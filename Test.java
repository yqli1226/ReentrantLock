package ReentrantLock;

/**
 * @program: Leetcode
 * @description:
 * @author: Mr.Li
 * @create: 2021-03-12 21:44
 **/
public class Test extends Thread{
    public static ReentrantLock lock = new ReentrantLock();
    public static int i = 0;

    public Test(String name){
        super.setName(name);
    }

    @Override
    public void run() {
        for (int j = 0; j < 100; j++) {
            lock.lock();
            try{
                System.out.println(this.getName() + " " + i);
                i++;
            }finally{
                lock.unlock();
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        Test test1 = new Test("Thread1");
        Test test2 = new Test("Thread2");

        test1.start();
        test2.start();
        test1.join();
        test2.join();
        System.out.println(i);
    }
}
