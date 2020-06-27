package org.yefei.qa.mock.network.websocket.message;

import lombok.Data;

import java.io.Serializable;

/**
 * @author yefei
 * @date: 2020/5/11
 */
@Data
public class MessageDown implements Serializable {

    private MessageDownType messageType;

    private AbstractMessageDownData messageData;
}
