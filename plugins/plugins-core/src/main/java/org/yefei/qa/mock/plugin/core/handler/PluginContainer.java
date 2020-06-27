package org.yefei.qa.mock.plugin.core.handler;

import org.yefei.qa.mock.plugin.core.define.AbstractRestServerPlugin;

import java.util.List;
import java.util.Vector;

/**
 * @author yefei
 * @date: 2020/6/27
 */
public class PluginContainer {

    private static Vector<AbstractRestServerPlugin> restServerPluginList = new Vector();

    public static void regist(AbstractRestServerPlugin plugin) {
        restServerPluginList.add(plugin);
    }

    public static List<AbstractRestServerPlugin> plugins() {
        return restServerPluginList;
    }

}
