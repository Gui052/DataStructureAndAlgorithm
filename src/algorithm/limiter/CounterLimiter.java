package algorithm.limiter;

import java.util.concurrent.atomic.AtomicLong;

// 计速器 限速
public class CounterLimiter {

    private  static final Object lockObj = new Object();
    // 起始时间
    private static long startTime = System.currentTimeMillis();
    // 时间区间的时间间隔 ms
    private final static long interval = 1000;
    // 每秒限制数量
    private final static long maxCount = 2;
    //累加器
    private final static AtomicLong accumulator = new AtomicLong();

    // 计数判断, 是否超出限制
    private static boolean tryAcquire() {
        long nowTime = System.currentTimeMillis();
        //在时间区间之内
        if (nowTime < startTime + interval) {
            long count = accumulator.incrementAndGet();
            return count <= maxCount;
        } else {
            //在时间区间之外
            synchronized (lockObj) {
                // 再一次判断，防止重复初始化
                if (nowTime > startTime + interval) {
                    accumulator.set(0);
                    startTime = nowTime;
                }
            }
            return true;
        }
    }
}
