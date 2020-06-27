package org.yefei.qa.mock.enums;

/**
 * @author: yefei
 * @date: 2018/9/27 10:02
 */
public enum ScriptLanguageEnum {
    GROOVY("groovy", "groovy")
    ,JAVA_SCRIPT("javascript", "javascript")
    ;

    private String language;


    private String engineName;

    ScriptLanguageEnum(String language, String engineName){
        this.language = language;
        this.engineName = engineName;
    }

    public String getLanguage() {
        return language;
    }


    public String getEngineName(){
        return this.engineName;
    }
}
