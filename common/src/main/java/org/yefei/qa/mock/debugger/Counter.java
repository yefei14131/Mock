package org.yefei.qa.mock.debugger;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.apache.commons.lang3.time.FastDateFormat;

import java.text.ParseException;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author yefei
 * @date: 2019/12/2
 */

@Data
@Slf4j
public class Counter {

    private static Counter currentCounter = null;
    private static final long DIFF = 60000; // counter 之间的差值， 1分钟 60000ms
    private final int limit = 20;
    private AtomicInteger count = new AtomicInteger();
    private Long time;

    static Counter getCounter(){
        try {

            String pattern = "yyyy-MM-dd HH:mm";
            FastDateFormat fastDateFormat = FastDateFormat.getInstance(pattern);
            long currentTime = fastDateFormat.parse(DateFormatUtils.format(System.currentTimeMillis(), pattern)).getTime();

            if (currentCounter == null) {
                currentCounter = new Counter(currentTime);

            } else if ( currentCounter.getTime() != currentTime ) {
                currentCounter = new Counter(currentTime);
            }
        } catch (ParseException e) {
            log.error("get hit counter error", e);
        }
        return currentCounter;
    }

    public Counter(long time) {
        this.time = time;
    }

    public void increment() {
        count.incrementAndGet();
    }

    public boolean isOverLimit() {
        return count.get() > limit;
    }

    @Override
    public int hashCode() {
        return time.hashCode();
    }

    @Override
    public boolean equals (Object o){
        if (o == null) {
            return false;
        }
        if (! (o instanceof Counter)) {
            return false;
        }

        return this.time == ((Counter) o).getTime();
    }

    /**
     *  计算传入的counter是否是前一个时间段的counter
     * @param prevCounter
     * @return
     */
    public boolean isPrevOneCounter(Counter prevCounter) {
        if (prevCounter == null) {
            return false;
        }

        if (this.time - prevCounter.getTime() == DIFF) {
            return true;
        }
        return false;
    }
}
