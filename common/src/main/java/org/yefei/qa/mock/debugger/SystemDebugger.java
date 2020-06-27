package org.yefei.qa.mock.debugger;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.yefei.qa.mock.service.IRequestLogService;

import java.util.UUID;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author yefei
 * @date: 2019/12/2
 */
@Component
@Slf4j
public class SystemDebugger {

    @Autowired
    private IRequestLogService requestLogService;

    private ThreadLocal<Boolean> isUsed = new ThreadLocal<>();

    private ReentrantLock lock = new ReentrantLock();

    private Counter prevCounter = null;

    private Counter currentCounter = null;

    private final ThreadLocal<String> traceHolder = new ThreadLocal<>();

    public void init(String requestPath) {

        if (lock.tryLock() ) {
            Counter counter = Counter.getCounter();
            counter.increment();

            if (currentCounter == null) {
                currentCounter = counter;

            } else if (!currentCounter.equals(counter)) {

                if (counter.isPrevOneCounter(currentCounter)) {
                    prevCounter = currentCounter;
                } else {
                    prevCounter = null;
                }

                currentCounter = counter;
            }

            isUsed.set(true);
            lock.unlock();
        } else {
            isUsed.set(false);
        }

        if (this.isDebugging()){
            traceHolder.set(UUID.randomUUID().toString());
            requestLogService.addRequestLog(requestPath, traceHolder.get());
        } else {
            traceHolder.remove();
        }
    }


    public boolean isDebugging() {
        if (isUsed.get() == null || !isUsed.get()) {
            return false;
        }

        if (currentCounter == null) {
            return true;
        }

        if ( currentCounter.isOverLimit() ){
            return false;
        }

        if (prevCounter == null) {
            return true;
        }

        if (!currentCounter.isPrevOneCounter(prevCounter)) {
            return true;
        }

        if (!prevCounter.isOverLimit()) {
            return true;
        }

        return false;
    }

    public void addSystemLog(String eventName, String eventDesc) {
        if (this.isDebugging()) {
            requestLogService.addRequestEvent(traceHolder.get(), eventName, eventDesc);
        }
    }

    public void addSystemLog(String eventName, Object eventDesc) {
        if (this.isDebugging()) {
            try {
                requestLogService.addRequestEvent(traceHolder.get(), eventName, new ObjectMapper().writeValueAsString(eventDesc));
            } catch (JsonProcessingException e) {
                log.error("写系统日志时，序列化map异常", e);
            }
        }
    }

    public void addSystemLog(String traceID, String eventName, String eventDesc) {
        if (StringUtils.isEmpty(traceID)) {
            return;
        }
        requestLogService.addRequestEvent(traceID, eventName, eventDesc);
    }

    public String getTraceID () {
        return this.traceHolder.get();
    }
}
