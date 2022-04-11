package algorithm.limiter;


import java.util.concurrent.atomic.AtomicInteger;

// 令牌桶 限速
public class TokenBucketLimiter {
    // 上一次令牌发放时间
    public long lastTime = System.currentTimeMillis();
    // 桶的容量
    public int capacity = 20;
    // 令牌生成速度 /s
    public int rate = 2;
    // 当前令牌数量
    public AtomicInteger tokens = new AtomicInteger(0);

    //返回值说明：
    // false 没有被限制到
    // true 被限流
    public synchronized boolean isLimited(int applyCount) {
        long now = System.currentTimeMillis();
        //时间间隔,单位为 ms
        long gap = now - lastTime;

        //计算时间段内的令牌数
        int reverse_permits = (int) (gap * rate / 1000);
        int all_permits = tokens.get() + reverse_permits;
        // 当前令牌数
        tokens.set(Math.min(capacity, all_permits));

        if (tokens.get() < applyCount) {
            // 若拿不到令牌,则拒绝
            return true;
        } else {
            // 还有令牌，领取令牌
            tokens.getAndAdd(-applyCount);
            lastTime = now;
            return false;
        }

    }
}

