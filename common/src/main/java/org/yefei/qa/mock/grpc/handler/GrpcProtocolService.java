package org.yefei.qa.mock.grpc.handler;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.yefei.qa.mock.dao.AbstructGrpcInterfaceJarDao;
import org.yefei.qa.mock.model.gen.pojo.TblGrpcInterfaceJar;

import java.util.List;


/**
 * @author: yefei
 * @date: 2018/12/2 23:03
 */
@Slf4j
public class GrpcProtocolService {

    @Autowired
    private AbstructGrpcInterfaceJarDao grpcInterfaceJarDao;

    @Autowired
    private GrpcInterfaceKeeper grpcInterfaceKeeper;

    @Autowired
    private IGrpcServiceProvider grpcServiceProvider;

    private static final int RETRY = 3;

    public void loadAllGrpcInterface() {
        grpcInterfaceKeeper.mkdirIfNotExists();
        List<TblGrpcInterfaceJar> jars = grpcInterfaceJarDao.queryAllGrpcInterface();
        grpcServiceProvider.clear();
        for (TblGrpcInterfaceJar jar : jars) {
            reloadGrpcInterface(jar);
        }
    }

    public void reloadGrpcInterface(TblGrpcInterfaceJar jar) {
        if (jar == null)
            return;
        if (!doLoad(jar)) {
            retry(jar);
        }
        log.info("从jar包扫描grpc类完成");
    }

    private boolean doLoad(TblGrpcInterfaceJar jar) {
        if (!grpcInterfaceKeeper.downloadJar(jar)) {
            log.info("下载grpc jar包失败");
            return false;
        }
        return grpcServiceProvider.updateItem(grpcInterfaceKeeper.localJarPath(jar));
    }

    private void retry(TblGrpcInterfaceJar jar) {
        new Thread(new Runnable() {
            @SneakyThrows
            @Override
            public void run() {
                for (int i = 0; i < RETRY; i++) {
                    Thread.sleep(3000);
                    log.info("正在重试下载...  {}, {}", i, jar.getJarUrl());
                    if (doLoad(jar)) {
                        break;
                    }
                }
            }
        }).start();
    }


    public void init() {
        loadAllGrpcInterface();
    }

}
