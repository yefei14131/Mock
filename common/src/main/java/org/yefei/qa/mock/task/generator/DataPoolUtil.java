package org.yefei.qa.mock.task.generator;

import java.util.HashMap;

/**
 * @author yefei
 * @date: 2020/6/27
 */
public class DataPoolUtil {
    public static HashMap[] newDataPool( HashMap globalDataPool, HashMap... dataPool) {
        HashMap[] newDataPool = new HashMap[dataPool.length+1];
        for (int i = 0; i < dataPool.length; i++) {
            newDataPool[i] = dataPool[i];
        }
        newDataPool[newDataPool.length-1] = globalDataPool;
        return newDataPool;
    }
}
