package org.apache.skywalking.apm.mock.agent.core.mock.websocket;

import org.apache.skywalking.apm.mock.agent.core.boot.BootService;
import org.apache.skywalking.apm.mock.agent.core.boot.DefaultNamedThreadFactory;
import org.apache.skywalking.apm.mock.agent.core.conf.Config;
import org.apache.skywalking.apm.mock.agent.core.logging.api.ILog;
import org.apache.skywalking.apm.mock.agent.core.logging.api.LogManager;
import org.apache.skywalking.apm.mock.util.RunnableWithExceptionProtection;

import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.URISyntaxException;
import java.text.MessageFormat;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * @author yefei
 * @date: 2020/4/27
 */

public class MockServerAdminWebSocketClient implements BootService {
    private static final ILog logger = LogManager.getLogger(MockServerAdminWebSocketClient.class);

    private String url;
    private MockWebSocketClient client = null;

    @Override
    public void prepare() throws Throwable {

        String serviceName = Config.Agent.SERVICE_NAME;
        InetAddress ip4 = Inet4Address.getLocalHost();
        String hostAddress = ip4.getHostAddress();
        String hostName = ip4.getHostName();

        String mockServerHost = Config.Agent.MOCKSERVER_ADMIN_ADDR.replaceAll("http(s)?://", "");

        url = MessageFormat.format("ws://{3}/websocket/agent/{0}/{1}/{2}", serviceName, hostName, hostAddress, mockServerHost);
        logger.info(url);

    }

    @Override
    public void boot() {
        ScheduledExecutorService service = Executors.newSingleThreadScheduledExecutor(new DefaultNamedThreadFactory("MockServerAdminService"));
        service.scheduleAtFixedRate(
                new RunnableWithExceptionProtection(() -> {
                    keepAlive();
                }, (throwable) -> {
                    logger.error("unexpected exception.", throwable);
                })
                , 10, 30, TimeUnit.SECONDS);
    }

    @Override
    public void onComplete() {

    }

    @Override
    public void shutdown() {
        if (client.isOpen() && (!client.isClosing() && !client.isClosed())) {
            client.close();
        }
    }

    private void connect() {
        try {
            logger.info("开始连接websocket, {}", url);
            client = new MockWebSocketClient(url);
            client.connect();

        } catch (URISyntaxException e) {
            logger.error(e, "连接mockserver websocket出错");
        }
    }

    private void keepAlive() {
        if (client == null || !client.isOpen() || client.isClosed() || client.isClosing()) {
            logger.info("websocket连接失效，重新连接");
            connect();

        } else {
            // keep alive
            MessagePusher.keepAlive(client);
            logger.debug("websocket keepalive");
        }
    }

}
