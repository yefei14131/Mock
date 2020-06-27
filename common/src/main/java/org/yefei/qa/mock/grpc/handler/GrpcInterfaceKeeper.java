package org.yefei.qa.mock.grpc.handler;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.yefei.qa.mock.config.GrpcConfig;
import org.yefei.qa.mock.model.gen.pojo.TblGrpcInterfaceJar;
import org.yefei.qa.mock.utils.OkHttpHelper;

import java.io.File;
import java.text.MessageFormat;

/**
 * @author yefei
 * @date: 2020/3/31
 */
@Component
@Slf4j
public class GrpcInterfaceKeeper {

    @Autowired
    private OkHttpHelper okHttpHelper;

    @Autowired
    private GrpcConfig grpcConfig;

    public boolean downloadJar(TblGrpcInterfaceJar jar) {
        String jarFileName = localJarPath(jar);
        File jarFile = new File(jarFileName);

        if (jarFile.exists() && jar.getUpdateTime().getTime() < jarFile.lastModified() && jarFile.length() > 0) {
            log.info("本地jar包已经存在，并且数据库更新时间小于文件更新时间，不需要更新jar包: {}", jarFileName);
            return true;
        }

        try {
            log.info("开始下载jar包: {}", jar.getJarUrl());
            //TODO 多个实例共用同一个网络磁盘，会竞争同一个jar包地址
            return okHttpHelper.download(jar.getJarUrl(), jarFile);
        } catch (Exception e) {
            log.info("下载jar包时异常", e);
            return false;
        }
    }

    public String localJarPath(TblGrpcInterfaceJar jar) {
        return MessageFormat.format("{0}/{1}.{2}.{3}.jar", grpcConfig.getCacheDir(), jar.getGroupID(), jar.getArtifactID(), jar.getVersion());
    }


    public void mkdirIfNotExists() {
        File file = new File(grpcConfig.getCacheDir());
        if (!file.exists()) {
            file.mkdirs();
        }
    }

}
