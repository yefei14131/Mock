package org.yefei.qa.mock.grpc.handler;

/**
 * @author yefei
 * @date: 2020/4/29
 */
public interface IGrpcServiceProvider {

    boolean updateItem(String localJarFilePath);

    void clear();

}
