package org.apache.skywalking.apm.mock.agent.core.mock;

import com.google.gson.Gson;
import okhttp3.*;
import org.apache.skywalking.apm.mock.agent.core.boot.BootService;
import org.apache.skywalking.apm.mock.agent.core.boot.DefaultImplementor;
import org.apache.skywalking.apm.mock.agent.core.conf.Config;
import org.apache.skywalking.apm.mock.agent.core.logging.api.ILog;
import org.apache.skywalking.apm.mock.agent.core.logging.api.LogManager;
import org.yefei.qa.mock.model.bean.AgentMapingResponse;
import org.yefei.qa.mock.network.mapping.GrpcMappingAgentSimple;
import org.yefei.qa.mock.network.mapping.MappingFullMessage;
import org.yefei.qa.mock.network.mapping.MappingPatchMessage;
import org.yefei.qa.mock.network.mapping.RestMappingAgentSimple;

import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * @author yefei
 * @date: 2020/4/27
 */

@DefaultImplementor
public class MockServerAdminService implements BootService {

    private static final ILog logger = LogManager.getLogger(MockServerAdminService.class);
    private String mappingQueryAddr;
    private HashMap<String, List<RestMappingAgentSimple>> restMockingData = new HashMap<>();
    private HashSet<String> grpcMockingData = new HashSet<>();
    private OkHttpClient okHttpClient;
    private final int SUCCESS_CODE = 0;
    private boolean useAble = true;

    @Override
    public void prepare() throws Throwable {
        mappingQueryAddr = Config.Agent.MOCKSERVER_ADMIN_ADDR + "/api/mapping/list";
        okHttpClient = initOkHttp();
    }

    @Override
    public void boot() throws Throwable {}

    @Override
    public void onComplete() throws Throwable {

    }

    @Override
    public void shutdown() throws Throwable {

    }

    private OkHttpClient initOkHttp() {
        ConnectionPool connectionPool = new ConnectionPool(2, 10, TimeUnit.MINUTES);
        return new OkHttpClient.Builder()
                .readTimeout(30, TimeUnit.SECONDS)
                .connectionPool(connectionPool)
                .build();
    }


    private void updateMockData() {
        logger.info("updateMockData, {}", mappingQueryAddr);
        Call call = okHttpClient.newCall(new Request.Builder().url(mappingQueryAddr).build());
        Response response = null;
        byte[] bytes = null;
        try {
            response = call.execute();
            bytes = response.body().bytes();
            AgentMapingResponse agentMapingResponse = new Gson().fromJson(new String(bytes), AgentMapingResponse.class);
            logger.debug(new String(bytes));
            if (SUCCESS_CODE == agentMapingResponse.getCode()) {

                HashMap<String, List<RestMappingAgentSimple>> restData = new HashMap<>();
                agentMapingResponse.getData().getRest().forEach(restMapping -> {
                    String key = genRestKey(restMapping.getHost(), restMapping.getUri());
                    if (restData.containsKey(key)) {
                        restData.get(key).add(restMapping);
                    } else {
                        restData.put(key, Collections.singletonList(restMapping));
                    }
                });

                restMockingData = restData;

                HashSet<String> grpcData = new HashSet<>();
                agentMapingResponse.getData().getGrpc().forEach(grpcMappingAgentSimple -> {
                    grpcData.add(getFullMethod(grpcMappingAgentSimple));
                });
                grpcMockingData = grpcData;

            } else {
                logger.info("mock query mapping, response code error. {}", new String(bytes));
            }

        } catch (Exception e) {
            logger.error("mock query mapping error", e);
        } finally {
            try {
                if (response != null) {
                    response.close();
                }
            } catch (Exception e) {

            }
        }

    }

    public boolean isMocking() {
        return useAble;
    }

    /**
     * grpc 接口是否是mock状态
     *
     * @param fullName
     * @return
     */
    public boolean isGrpcMocking(String fullName) {
        return useAble && grpcMockingData.contains(fullName);
    }

    /**
     * http 接口是否是mock状态
     * @param host
     * @param uri
     * @return
     */
    public boolean isRestMocking(String host, String uri) {
        return useAble && restMockingData.containsKey(genRestKey(host, uri));
    }


    public List<RestMappingAgentSimple> getRestMappingAgentSimple(String host, String uri) {
        return restMockingData.get(genRestKey(host, uri));
    }

    private String genRestKey(String host, String uri) {
        return host + uri;
    }


    private String getFullMethod(GrpcMappingAgentSimple grpcMappingAgentSimple) {
        return grpcMappingAgentSimple.getServiceName() + "/" + grpcMappingAgentSimple.getMethodName();
    }


    public void updateIsAcive(boolean isActive) {
        logger.info("更新agent开关状态: {}", isActive);
        this.useAble = isActive;
    }

    public void updateFullApi(MappingFullMessage fullMessage) {
        logger.info("开始更新mock接口");
        List<GrpcMappingAgentSimple> grpc = fullMessage.getGrpc();

        if (grpc != null) {
            if (grpc.isEmpty()) {
                // 无可用接口, 清除原数据
                grpcMockingData.clear();
            } else {
                HashSet<String> grpcData = new HashSet<>();
                grpc.forEach(grpcMappingAgentSimple -> {
                    grpcData.add(getFullMethod(grpcMappingAgentSimple));
                });
                grpcMockingData = grpcData;
            }
        }

        List<RestMappingAgentSimple> rest = fullMessage.getRest();
        if (rest != null) {
            if (rest.isEmpty()) {
                // 无可用接口, 清除原数据
                grpcMockingData.clear();
            } else {
                HashMap<String, List<RestMappingAgentSimple>> restData = new HashMap<>();
                rest.forEach(restMapping -> {
                    String key = genRestKey(restMapping.getHost(), restMapping.getUri());
                    if (restData.containsKey(key)) {
                        restData.get(key).add(restMapping);
                    } else {
                        restData.put(key, Collections.singletonList(restMapping));
                    }
                });

                restMockingData = restData;
            }
        }

        logger.info("grpc mock 接口列表: {}", new Gson().toJson(grpcMockingData));
        logger.info("rest mock 接口列表: {}", new Gson().toJson(restMockingData));
    }

    public void updatePatchApi(MappingPatchMessage patchMessage) {

    }
}
