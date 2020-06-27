package org.yefei.qa.mock.model.bean.notice;

import lombok.Data;
import org.yefei.qa.mock.model.bean.AbstractSerializable;

import java.util.ArrayList;
import java.util.List;

/**
 * @author yefei
 * @date: 2020/4/10
 */
@Data
public class SystemNotice extends AbstractSerializable {
    private SystemNoticeType noticeType;
    private List<SystemNoticeConsumer> noticeConsumerList = new ArrayList<>();
    private String noticeContent;
}
