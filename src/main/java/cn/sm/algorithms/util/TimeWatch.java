package cn.sm.algorithms.util;

/**
 * 计时器
 */
public class TimeWatch {

    private Long startTime;
    private Long endTime;

    public void start() {
        this.startTime = System.currentTimeMillis();
    }

    public void end() {
        this.endTime = System.currentTimeMillis();
    }

    public Long getTimeOut() {
        if (startTime != null && endTime != null) {
            return endTime - startTime;
        }
        return -1L;
    }
}
