package org.yefei.qa.mock.network.websocket.regist;

import lombok.Data;
import org.yefei.qa.mock.network.websocket.message.AbstractMessageUpData;

/**
 * @author yefei
 * @date: 2020/5/11
 */
@Data
public class KeepAliveMessage extends AbstractMessageUpData {
    private String accessToken;
}
