package org.yefei.qa.mock.network.websocket.message;

import lombok.Data;

import java.io.Serializable;

/**
 * @author yefei
 * @date: 2020/5/11
 */
@Data
public class MessageUp implements Serializable {

    private MessageUpType messageType;

    private AbstractMessageUpData messageData;

}
