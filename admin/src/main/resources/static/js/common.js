
!function(){
    // "use strict"
    //回调函数 的调用方法  invokeFunc("funcName"[, arg1, arg2]) 或   invokeFunc(func[, arg1, arg2])
    function invokeFunc(func){
        var argsArr = []
        for(var i in arguments){
            if(i > 0){
                argsArr.push(arguments[i])
            }
        }
        switch(typeof func){
            case "function" :
                func.apply(window, argsArr);
                break;
            case "string"	:
                window[func].apply(window, argsArr);
                break;
        }
    }
    window.invokeFunc = invokeFunc;
}();

(function($){

    $.fn.serializeObject=function(){
        var serializeObj={};
        $(this.serializeArray()).each(function(){
            serializeObj[this.name]=this.value;
        });
        return serializeObj;
    };
})(jQuery);

var SUCCESS_CODE = 0;
function ajaxPost(url, data, success, fail) {
    $.ajax({
        url : url
        ,type: "post"
        ,data: JSON.stringify(data)
        ,contentType : 'application/json'
        ,dataType: 'json'
        ,success: function (response) {
            if(response.code == SUCCESS_CODE){

                if(typeof success == "function"){
                    success(response);
                }

            }else{
                layer.msg(response.message)
                if(typeof fail == "function"){
                    fail(response);
                }
            }
        }
    })
}



//生成isActive字段的下拉框
function genIsActiveField(isActive){
    var $select = $("<select></select>").attr('name', 'isActive')
    $select.append($("<option value='true'>有效</option>")).append($("<option value='false'>无效</option>")).val(isActive === '' ? 'true' : isActive + '')
    return $select;
}


//生成Sub-isActive字段的下拉框
function genSubIsActiveField(isActive){
    var $select = $("<select></select>").attr('sub-name', 'isActive')
    $select.append($("<option value='true'>有效</option>")).append($("<option value='false'>无效</option>")).val(isActive === '' ? 'true' : isActive + '')
    return $select;
}



//生成下拉框
function genSelectField(options, fieldName, fieldValue){
    var $select = $("<select></select>").attr('name', fieldName)
    $select.append($("<option>请选择</option>"))

    options.forEach(function (item) {
        $select.append($("<option value=''></option>").attr('value', item['value']).text(item['display']))
    })
    return fieldValue ? $select.val(fieldValue) : $select;

}


//生成Sub下拉框
function genSubSelectField(options, fieldName, fieldValue){
    var $select = $("<select></select>").attr('sub-name', fieldName)
    $select.append($("<option>请选择</option>"))

    options.forEach(function (item) {
        $select.append($("<option value=''></option>").attr('value', item['value']).text(item['display']))
    })
    return fieldValue ? $select.val(fieldValue) : $select;

}


//生成文本框
function genTextareaField(fieldName, fieldValue){
    return $("<textarea placeholder=\"请输入内容\" class=\"layui-textarea\"></textarea>").attr("name", fieldName).val(fieldValue)
}
//生成Sub-文本框
function genSubTextareaField(fieldName, fieldValue){
    return $("<textarea placeholder=\"请输入内容\" class=\"layui-textarea\"></textarea>").attr("sub-name", fieldName).val(fieldValue)
}

//生成Input
function genInputField(fieldName, fieldValue){
    return $("<input type=\"text\" name=\"\" placeholder=\"请输入\" autocomplete=\"off\" class=\"layui-input\">").attr("name", fieldName).val(fieldValue)
}

//生成Sub-Input
function genSubInputField(fieldName, fieldValue){
    return $("<input type=\"text\" placeholder=\"请输入\" autocomplete=\"off\" class=\"layui-input\">").attr("sub-name", fieldName).val(fieldValue)
    // return $("<input type=\"text\" placeholder=\"请输入\" autocomplete=\"off\" class=\"layui-input\">").attr("sub-name", fieldName).attr('value', fieldValue)
}


//扩展内容的表单列表添加分割线和删除按钮
function buildFormItem($formContainer, $form, removeBtnClass){
    $formContainer.append($form)
        .append($("<button class=\"layui-btn-xs layui-btn layui-btn-radius layui-btn-danger "+ removeBtnClass +"\"><i class=\"layui-icon\">删除</i> </button>"))
        .append($("<hr class=\"layui-bg-blue\">"))

}


