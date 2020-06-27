package org.yefei.qa.mock.websocket;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.yefei.qa.mock.config.ApplicationContextStaticProvider;

import javax.websocket.*;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.text.MessageFormat;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author yefei
 * @date: 2020/5/9
 */
@Component
@ServerEndpoint("/websocket/agent/{serviceName}/{agentName}/{ip}")
@Slf4j
@Data
public class WebSocketServer {

    private static AtomicInteger cnt = new AtomicInteger();
    private static ConcurrentHashMap<String, WebSocketServer> webSocketSet = new ConcurrentHashMap<>();
    private String serviceName;
    private String agentName;
    private String ip;

    //与某个客户端的连接会话，需要通过它来给客户端发送数据
    private Session session;

    public static String genRoutingKey(String serviceName, String agentName, String ip) {
        return MessageFormat.format("{0}::{1}::{2}", serviceName, ip, agentName);
    }

    public static String genRoutingKey(WebSocketServer webSocketServer) {
        return genRoutingKey(webSocketServer.getServiceName(), webSocketServer.getAgentName(), webSocketServer.getIp());
    }

    @OnOpen
    public void onOpen(Session session, @PathParam("serviceName") String serviceName, @PathParam("agentName") String agentName, @PathParam("ip") String ip) {
        this.serviceName = serviceName;
        this.agentName = agentName;
        this.ip = ip;

        this.session = session;
        webSocketSet.put(genRoutingKey(this), this);

        log.info("websocket connect cnt: {}", cnt.incrementAndGet());
        log.info("websocket connect:   " + genRoutingKey(this));

        try {
            // 保存连接信息
            WebSocketReceiver webSocketReceiver = ApplicationContextStaticProvider.getBean(WebSocketReceiver.class);
            webSocketReceiver.agentRegist(this);

            // 推送接口列表
            WebSocketMappingPusher webSocketMappingPusher = ApplicationContextStaticProvider.getBean(WebSocketMappingPusher.class);
            webSocketMappingPusher.pushAllMapping(this);
        } catch (Exception e) {
            log.error("websocket 连接后业务处理异常", e);
        }

    }

    @OnMessage
    public void onMessage(byte[] message, Session session) {
        try {
            WebSocketReceiver webSocketReceiver = ApplicationContextStaticProvider.getBean(WebSocketReceiver.class);
            webSocketReceiver.receive(this, message);
        } catch (Exception e) {
            log.error("websocket onMessage error", e);
        }
    }

    @OnError
    public void onError(Session session, Throwable error) {
        log.error("websocket error", error);
    }

    @OnClose
    public void onClose() {
        log.info("websocket close");
        webSocketSet.remove(genRoutingKey(this));
    }

    public void sendMessage(String message) {
        try {
            log.info("开始推送websocket消息");
            this.session.getBasicRemote().sendText(message);
        } catch (Exception e) {
            doSendError(e);
        }
    }

    private void doSendError(Throwable e) {

        log.error("发送websocket消息异常", e);
        try {
            // 推送异常则关闭连接，让客户端重连，目的是保持数据的一致性
            this.session.close();
        } catch (IOException e1) {
            // 关闭失败，连接可以已经删除，移除缓存
            webSocketSet.remove(genRoutingKey(this));
        }
    }

    public void sendMessage(byte[] message) {
        try {
            log.info("开始推送websocket消息, send buffer");
            ByteBuffer buffer = ByteBuffer.wrap(message);
            session.getBasicRemote().sendBinary(buffer);
        } catch (Exception e) {
            doSendError(e);
        }
    }


    public static void sendMessageToAll(String msg) {
        for (Map.Entry<String, WebSocketServer> stringWebSocketServerEntry : webSocketSet.entrySet()) {
            WebSocketServer socket = stringWebSocketServerEntry.getValue();
            socket.sendMessage(msg);
        }
    }

    public static void sendMessage(String msg, String routingKey) {
        if (webSocketSet.containsKey(routingKey)) {
            webSocketSet.get(routingKey).sendMessage(msg);
        }
    }

    public static void sendMessageToAll(byte[] msg) {
        for (Map.Entry<String, WebSocketServer> stringWebSocketServerEntry : webSocketSet.entrySet()) {
            WebSocketServer socket = stringWebSocketServerEntry.getValue();
            socket.sendMessage(msg);
        }
    }

    public static void sendMessage(byte[] msg, String routingKey) {
        if (webSocketSet.containsKey(routingKey)) {
            webSocketSet.get(routingKey).sendMessage(msg);
        }
    }

}
