package org.apache.skywalking.apm.mock.agent.core.mock;

import org.apache.skywalking.apm.mock.agent.core.boot.ServiceManager;
import org.apache.skywalking.apm.mock.agent.core.conf.Config;
import org.yefei.qa.mock.network.mapping.RestMappingAgentSimple;

import java.text.MessageFormat;
import java.util.List;

/**
 * @author yefei
 * @date: 2020/4/23
 */
public class MockManager {

    private static String restMockServerAddr = Config.Agent.MOCKSERVER_REST_ADDR;

    private static String grpcMockServerAddr = Config.Agent.MOCKSERVER_GRPC_ADDR;


    public static boolean isRestMocking(String host, String uri) {
        return ServiceManager.INSTANCE.findService(MockServerAdminService.class).isRestMocking(host, getPath(uri));
    }

    public static boolean isGrpcMocking(String fullMethoName) {
        return ServiceManager.INSTANCE.findService(MockServerAdminService.class).isGrpcMocking(fullMethoName);
    }

    public static boolean isMocking() {
        return ServiceManager.INSTANCE.findService(MockServerAdminService.class).isMocking();
    }

    /**
     * 生成mock服务的url
     *
     * @param host 被拦截的域名
     * @param uri
     * @return
     */
    public static String buildMockUrl(String host, String uri) {
        MockServerAdminService service = ServiceManager.INSTANCE.findService(MockServerAdminService.class);
        List<RestMappingAgentSimple> restMappingAgentSimple = service.getRestMappingAgentSimple(host, getPath(uri));

        return restMappingAgentSimple == null || restMappingAgentSimple.isEmpty() ? null : genMockUrl(restMappingAgentSimple.get(0), uri);
    }

    private static String genMockUrl(RestMappingAgentSimple mapping, String uri) {
        return MessageFormat.format("{0}{1}{2}", restMockServerAddr, mapping.getUriPrefix(), uri);
    }

    private static String getPath(String uri) {
        return uri.replaceAll("\\?.*$", "");
    }

}
