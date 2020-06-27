package org.yefei.qa.mock.config;

//import com.sun.istack.internal.Nullable;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.ClassPathScanningCandidateComponentProvider;
import org.springframework.core.type.classreading.MetadataReader;
import org.springframework.core.type.classreading.MetadataReaderFactory;
import org.springframework.core.type.filter.TypeFilter;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

/**
 * @author: yefei
 * @date: 2018/10/10 18:59
 */
@Component
@Slf4j
public class BeanScanner {

    private ConcurrentHashMap<String, Bean> beanMap;

    public BeanScanner(){
        if (beanMap == null) {
            try {
                updateBeanMap();
            } catch (Exception e) {
                log.error(ExceptionUtils.getStackTrace(e));
            }
        }
    }


    public synchronized Bean getBean(String classSimpleName) {

        if (classSimpleName == null) {
            return null;
        }

        return beanMap.get(classSimpleName);
    }


    public synchronized List<BeanField> getBeanFields(String classSimpleName) {

        if (classSimpleName == null) {
            return null;
        }

        return beanMap.get(classSimpleName).getBeanFieldList();
    }

    public List<BeanField> getBeanFields(String classSimpleName, String... ignoreFieldNames) {

        List<BeanField> beanFields = getBeanFields(classSimpleName);

        if (ignoreFieldNames.length == 0 || beanFields == null){
            return beanFields;
        }

        //过滤要忽略的字段
        return beanFields.stream().filter( beanField -> !Arrays.asList(ignoreFieldNames).contains(beanField.getFieldName()))
                .collect(Collectors.toList());

    }



    @Data
    public class BeanField {
        private String fieldName;
        private String fieldType;

        BeanField(String fieldName, String fieldType){
            this.fieldName = fieldName;
            this.fieldType = fieldType;
        }
    }

    @Data
    public class Bean {
        private String classSimpleName;
        private String className;
        List<BeanField> beanFieldList;
    }

    public class ClassNameFilter implements TypeFilter {
        @Override
        public boolean match(MetadataReader metadataReader, MetadataReaderFactory metadataReaderFactory) {
            return true;
        }
    }

    private void updateBeanMap() throws Exception {
        beanMap = new ConcurrentHashMap();
        ClassPathScanningCandidateComponentProvider scanner =
                new ClassPathScanningCandidateComponentProvider(false);

        scanner.addIncludeFilter(new ClassNameFilter());

        for (BeanDefinition bd : scanner.findCandidateComponents("org.yefei.qa.mock.model*")) {

            Class aClass = Class.forName(bd.getBeanClassName());
            String simpleName = aClass.getSimpleName();

            Bean bean = new Bean();
            bean.setClassSimpleName(simpleName);
            bean.setClassName(aClass.getName());

            List<BeanField> beanFields = new ArrayList<>();

            Arrays.stream(aClass.getDeclaredFields()).forEach(field -> {
                String fieldName = field.getName();
                Class<?> fieldType = field.getType();
                String fieldTypeName = fieldType.getSimpleName();

                beanFields.add(new BeanField(fieldName, fieldTypeName));
            });
            bean.setBeanFieldList(beanFields);
            beanMap.put(simpleName, bean);
        }

    }
//
//    public static void main(String[] args) {
//        BeanScanner beanScanner = new BeanScanner();
//        List<BeanField> beanFields = beanScanner.getBeanFields("TblSystemConfig", "configID", "updateTime");
//        System.out.println(beanFields);
//    }

}
