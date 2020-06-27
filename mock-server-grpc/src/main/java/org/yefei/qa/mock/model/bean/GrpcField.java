package org.yefei.qa.mock.model.bean;

import lombok.Data;

import java.util.List;

/**
 * @author: yefei
 * @date: 2018/11/10 11:02
 */
@Data
public class GrpcField {

    private String fieldName;

    private String fieldType;

    private List<GrpcField> subFields;


}
