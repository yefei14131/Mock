package org.yefei.qa.mock.model.bean.callback;

import lombok.Data;
import org.yefei.qa.mock.model.bean.AbstractSerializable;

/**
 * @author: yefei
 * @date: 2018/9/9 12:42
 */
@Data
public class CallbackNotifyBean extends AbstractSerializable {

    private String notifyKey;    //

    private boolean isSuccess;

    private String message;

}
