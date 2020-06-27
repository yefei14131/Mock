package org.yefei.qa.mock.utils;

import lombok.extern.slf4j.Slf4j;
import okhttp3.*;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.concurrent.TimeUnit;

@Component
@Slf4j
public class OkHttpHelper {

    final static int timeout = 60;
    private static OkHttpClient okHttpClient;
    private static final int HTTP_SUCCESS_CODE = 200;

    @PostConstruct
    void init() {
        ConnectionPool connectionPool = new ConnectionPool(10, 10, TimeUnit.MINUTES);
        okHttpClient = new OkHttpClient.Builder()
                .readTimeout(timeout, TimeUnit.SECONDS)
                .connectionPool(connectionPool)
                .build();
    }


    public byte[] getResponseByte(String url) throws IOException {

        Request request = new Request.Builder().url(url)
                .build();
        Call call = okHttpClient.newCall(request);

        Response response = call.execute();
        byte[] bytes = response.body().bytes();
        response.close();
        return bytes;

    }


    public boolean download(String url, File file) {
        Request request = new Request.Builder().url(url)
                .build();
        Call call = okHttpClient.newCall(request);

        Response response = null;
        FileOutputStream fileOutputStream = null;
        try {

            if (!file.exists()) {
                file.createNewFile();
            }

            fileOutputStream = new FileOutputStream(file);
            response = call.execute();

            if (response.code() != HTTP_SUCCESS_CODE) {
                log.error("下载jar包时，http状态码不为:{}, {}", HTTP_SUCCESS_CODE, url);
                return false;
            }

            InputStream inStream = response.body().byteStream();
            int byteread = 0;
            byte[] buffer = new byte[1204];
            while ((byteread = inStream.read(buffer)) != -1) {
                fileOutputStream.write(buffer, 0, byteread);
            }
            log.info("下载jar包完成");
            return true;
        } catch (IOException e) {
            log.error("curl error", e);
            return false;

        } finally {
            if (response != null) {
                response.close();
            }

            if (fileOutputStream != null) {
                try {
                    fileOutputStream.close();

                } catch (IOException e) {
                    log.error("下载jar包关闭文件流时出错", e);
                }
            }

        }
    }

}
