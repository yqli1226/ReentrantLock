package Semaphore;

import java.lang.reflect.Executable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

/**
 * @program: Leetcode
 * @description: 信号量 多个线程同时访问  一次性哪一个许可的情况
 * @author: Mr.Li
 * @create: 2021-03-12 17:27
 **/
public class Test {
    //请求数量
    private static final int threadCount = 550;

    public static void main(String[] args) {
        //创建固定线程数量的线程池
        ExecutorService threadPool = Executors.newFixedThreadPool(300);
        //一次允许执行的线程数
        final Semaphore semaphore = new Semaphore(20);

        for (int i = 0; i < threadCount; i++) {
            final int threadnum = i;
            threadPool.execute(()->{
                try{
                    semaphore.acquire();//获得许可，一次可运行线程数位20
                    test(threadnum);
                    semaphore.release();//释放许可
                }catch (InterruptedException e){
                    e.printStackTrace();
                }
            });
        }
        threadPool.shutdown();
        System.out.println("finish");
    }

    public static void test(int threadnum) throws InterruptedException {
        Thread.sleep(1000);
        System.out.println("Threadnum" + threadnum);
        Thread.sleep(1000);
    }
}
