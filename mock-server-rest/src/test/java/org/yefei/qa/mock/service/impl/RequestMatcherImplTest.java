package org.yefei.qa.mock.service.impl;

import com.alibaba.fastjson.JSON;
import junit.framework.TestCase;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.yefei.qa.mock.cache.MappingRulesDetailCache;
import org.yefei.qa.mock.cache.RestRequestMappingCache;
import org.yefei.qa.mock.model.bean.RecordedRequest;
import org.yefei.qa.mock.model.gen.pojo.TblMappingRulesDetail;
import org.yefei.qa.mock.model.gen.pojo.TblRestRequestMapping;

import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;


/**
 * @author yefei
 * @date: 2020/5/15
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class RequestMatcherImplTest {

    @Autowired
    @InjectMocks
    private RequestMatcherImpl requestMatcher;

    @InjectMocks
    private RequestMatcherImpl requestMatcher2;

    @Mock
    private RestRequestMappingCache restRequestMappingCache;

    @Mock
    private MappingRulesDetailCache mappingRulesDetailCache;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);

    }


    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void loadRecordedRequestList() {
        // 准备参数
        String groupCode = "test";
        String path = "/test";

        int requestID = 666;

        String mappings = "[{\"groupCode\":\"test\",\"groupID\":16,\"isActive\":true,\"memo\":\"正则匹配3\",\"path\":\"/regex\",\"requestID\":" + requestID + ",\"responseBody\":\"{\\\"code\\\":200,\\\"msg\\\":\\\"beautiful-222\\\"}\",\"responseDelay\":0,\"responseType\":\"json\",\"sortIndex\":99,\"updateTime\":1589283541000}]";
        JSON.parseArray(mappings, TblMappingRulesDetail.class);

        String rules = "[{\"compareWay\":\"regex\",\"operator\":\"&&\",\"parentID\":0,\"protocol\":\"http\",\"requestID\":" + requestID + ",\"rulesDetailID\":36,\"sortIndex\":99,\"updateTime\":1588130175000,\"variableName\":\"shopID\",\"variableSource\":\"parameter\",\"variableValue\":\"123|234\"}]";

        List<TblMappingRulesDetail> mockRulesList = JSON.parseArray(rules, TblMappingRulesDetail.class);
        List<TblRestRequestMapping> mockMappingList = JSON.parseArray(mappings, TblRestRequestMapping.class);

        // 设置mock规则
        when(restRequestMappingCache.queryTblRestRequestMapping(any(), any())).thenReturn(mockMappingList);
        when(mappingRulesDetailCache.queryRestMappingRulesDetailsByRequestID(requestID)).thenReturn(mockRulesList);


        // 执行用例，测试requestMatcher.loadRecordedRequestList方法
        List<RecordedRequest> recordedRequests = requestMatcher.loadRecordedRequestList(groupCode, path);

        // 断言返回结果
        TestCase.assertNotNull(recordedRequests);
        TestCase.assertTrue(recordedRequests.size() == 1);

        RecordedRequest recordedRequest = recordedRequests.get(0);

        // 断言映射关系
        TestCase.assertNotNull("匹配映射不存在", recordedRequest.getTblRestRequestMapping());
        TestCase.assertEquals("响应结果不正确", recordedRequest.getTblRestRequestMapping().getResponseBody(), "{\"code\":200,\"msg\":\"beautiful-222\"}");

        // 断言匹配规则
        TestCase.assertNotNull("匹配规则不存在", recordedRequest.getHitCondition());
        TestCase.assertNotNull("匹配规则不存在", recordedRequest.getHitCondition().getSubConditions());
        TestCase.assertNotNull("匹配规则不存在", recordedRequest.getHitCondition().getSubConditions().size());
        TblMappingRulesDetail matchRules = recordedRequest.getHitCondition().getSubConditions().get(0).getRules();
        TestCase.assertEquals("匹配规则应该为正则匹配", matchRules.getCompareWay(), "regex");
        TestCase.assertEquals("匹配规则的期望结果不正确", matchRules.getVariableValue(), "123|234");

    }

    @Test
    public void matchRecordedRequest() {
        System.out.println();
    }
}
