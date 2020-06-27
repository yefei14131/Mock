package org.yefei.qa.mock.network.websocket.regist;

import lombok.Data;
import org.yefei.qa.mock.network.websocket.message.AbstractMessageData;

/**
 * @author yefei
 * @date: 2020/5/11
 */
@Data
public class RegistMessage extends AbstractMessageData {

    private String serviceName;

    private String hostName;

    private String ip;

}
