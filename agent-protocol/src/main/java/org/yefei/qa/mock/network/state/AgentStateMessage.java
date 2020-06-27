package org.yefei.qa.mock.network.state;

import lombok.Data;
import org.yefei.qa.mock.network.websocket.message.AbstractMessageDownData;

/**
 * 全量更新接口
 * 如果为null，则表示没有对应的接口，需要清除agent本地缓存
 * 如果size == 0，则表示当前没有更新，agent不错处理，保持现状
 *
 * @author yefei
 * @date: 2020/5/11
 */
@Data
public class AgentStateMessage extends AbstractMessageDownData {

    private boolean isActive;

    public AgentStateMessage() {

    }

    public AgentStateMessage(boolean isActive) {
        this.isActive = isActive;
    }

}
