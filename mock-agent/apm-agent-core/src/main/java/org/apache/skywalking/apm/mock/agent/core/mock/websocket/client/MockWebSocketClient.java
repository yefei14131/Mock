package org.apache.skywalking.apm.mock.agent.core.mock.websocket.client;

import org.apache.skywalking.apm.mock.agent.core.logging.api.ILog;
import org.apache.skywalking.apm.mock.agent.core.logging.api.LogManager;
import org.apache.skywalking.apm.mock.agent.core.mock.websocket.MessageReceiver;
import org.apache.skywalking.apm.mock.agent.core.mock.websocket.MockServerAdminWebSocketClient;
import org.apache.skywalking.apm.mock.websocket.client.WebSocketClient;
import org.apache.skywalking.apm.mock.websocket.handshake.ServerHandshake;

import java.net.URI;
import java.net.URISyntaxException;
import java.nio.ByteBuffer;
import java.util.Iterator;

/**
 * @author yefei
 * @date: 2020/5/11
 */
public class MockWebSocketClient extends WebSocketClient {
    private static final ILog logger = LogManager.getLogger(MockServerAdminWebSocketClient.class);

    public MockWebSocketClient(String url) throws URISyntaxException {
        super(new URI(url));
    }

    @Override
    public void onOpen(ServerHandshake shake) {
        logger.info("websocket握手...");
        for (Iterator<String> it = shake.iterateHttpFields(); it.hasNext(); ) {
            String key = it.next();
            logger.info(key + ":" + shake.getFieldValue(key));
        }
    }

    @Override
    public void onMessage(String message) {
        logger.info("接收到websocket消息：STRING, 暂不处理");

    }

    @Override
    public void onMessage(ByteBuffer buffer) {
        logger.info("接收到websocket消息, byte[]");
        MessageReceiver.receive(buffer.array());
    }

    @Override
    public void onClose(int code, String reason, boolean remote) {
        logger.info("websocket关闭... {}, {}, {} ", code, reason, remote);
    }

    @Override
    public void onError(Exception e) {
        logger.error("websocket异常", e);
    }

    @Override
    public void send(String message) {
        try {
            logger.debug("发送websocket消息: {}", message);
            super.send(message);
        } catch (Exception e) {
            logger.error("发送websocket消息异常", e);
        }
    }


}
