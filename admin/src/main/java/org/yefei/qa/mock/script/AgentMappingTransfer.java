package org.yefei.qa.mock.script;

import org.yefei.qa.mock.model.gen.pojo.TblRestRequestMapping;
import org.yefei.qa.mock.network.mapping.RestMappingAgentSimple;

/**
 * @author yefei
 * @date: 2020/5/12
 */
public class AgentMappingTransfer {

    public static void restMapping2AgentMapping(TblRestRequestMapping tblMapping, RestMappingAgentSimple simpleMapping, String host) {
        simpleMapping.setHost(host);
        simpleMapping.setUri(tblMapping.getPath());
        simpleMapping.setUriPrefix("/" + tblMapping.getGroupCode());
    }
}
