package org.yefei.qa.mock.service.impl;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.yefei.qa.mock.cache.GlobalDataPool;
import org.yefei.qa.mock.cache.GlobalVarCache;
import org.yefei.qa.mock.enums.ProtocolEnum;
import org.yefei.qa.mock.model.gen.pojo.TblMappingGlobalVar;
import org.yefei.qa.mock.utils.VariableManager;

import java.util.HashMap;
import java.util.List;

/**
 * @author yefei
 * @date: 2020/10/21
 */
@Service
public class GlobalDataService {

    @Autowired
    private GlobalVarCache globalVarCache;

    @Autowired
    private GlobalDataPool globalDataPool;

    public void saveGlobalVars(int requestID, ProtocolEnum protocol, HashMap userDefined, HashMap params) {
        List<TblMappingGlobalVar> globalVarList = globalVarCache.getGlobalVarList(requestID, protocol.getProtocol());
        globalVarList.forEach(globalVar -> {
            String varName = VariableManager.replaceContent(globalVar.getVarName(), userDefined, params);
            String varValue = VariableManager.replaceContent(globalVar.getVarValue(), userDefined, params);
            if (StringUtils.isNotBlank(varName)) {
                globalDataPool.updateCache(varName, varValue);
            }
        });
    }

    public HashMap<String, Object> getGlobalVars() {
        return globalDataPool.getAll();
    }
}
