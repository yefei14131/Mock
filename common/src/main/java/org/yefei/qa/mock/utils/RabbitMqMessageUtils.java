package org.yefei.qa.mock.utils;

import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.yefei.qa.mock.model.bean.AbstractSerializable;

/**
 * @author yefei
 * @date: 2020/4/17
 */
@Slf4j
public class RabbitMqMessageUtils<T> {
    public static <T> T formatMesage(Object message, Class<T> clazz) {
        T t;
        if (message instanceof AbstractSerializable) {
            return (T) message;

        }
        if (message instanceof String) {

            t = JSONObject.parseObject((String) message, clazz);
        } else if (message instanceof byte[]) {
            t = JSONObject.parseObject((byte[]) message, clazz);
        } else {
            log.error("不能识别的消息类型：{}", message.getClass());
            return null;
        }
        return t;
    }
}
