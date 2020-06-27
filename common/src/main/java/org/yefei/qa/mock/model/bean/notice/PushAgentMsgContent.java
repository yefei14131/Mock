package org.yefei.qa.mock.model.bean.notice;

import lombok.Data;
import org.yefei.qa.mock.model.bean.AbstractSerializable;

/**
 * @author yefei
 * @date: 2020/4/10
 */
@Data
public class PushAgentMsgContent extends AbstractSerializable {
    private String routingKey;
    private byte[] msg;
}
