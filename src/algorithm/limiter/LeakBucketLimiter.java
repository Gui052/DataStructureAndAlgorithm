package algorithm.limiter;

import java.util.concurrent.atomic.AtomicInteger;

// 漏桶 限流
public class LeakBucketLimiter {

    // 计算的起始时间
    private static long lastOutTime = System.currentTimeMillis();
    // 流出速率 每秒 2 次
    private final static int leakRate = 2;
    // 桶的容量
    private final static int capacity = 20;
    //剩余的水量
    private final static AtomicInteger water = new AtomicInteger(0);

    //返回值说明：
    // false 没有被限制到
    // true 被限流
    public static synchronized boolean isLimit() {
        // 如果是空桶，就当前时间作为漏出的时间
        if (water.get() == 0) {
            lastOutTime = System.currentTimeMillis();
            water.addAndGet(1);
            return false;
        }
        // 执行漏水   计算 当前时间和上一次漏水时间间隔中应该有多少水应该漏掉
        int waterLeaked = ((int)((System.currentTimeMillis() - lastOutTime) / 1000)) * leakRate;
        // 计算剩余水量
        int waterLeft = water.get() - waterLeaked;
        water.set(Math.max(0, waterLeft));
        // 重新更新leakTimeStamp
        lastOutTime = System.currentTimeMillis();
        // 尝试加水,并且水还未满 ，放行
        if ((water.get()) < capacity) {
            water.addAndGet(1);
            return false;
        } else {
            // 水满，拒绝加水， 限流
            return true;
        }
    }
}
