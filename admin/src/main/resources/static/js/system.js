
var configFields = []
var configDataFields = {}

function buildConfigData(dataStr) {
    var data = JSON.parse(dataStr)

    var $container = $("<div class='sub-form'></div>")

    for ( var fieldName in data) {

        var fieldValue = data[fieldName]

        $formItem = $(" <div class=\"layui-form-item\"></div>")
        $formItem.append($("<label class=\"layui-form-label\">/label>").text(fieldName))

        $block = $("<div class=\"layui-input-block\"></div>")

        var $field = genSubInputField( fieldName, fieldValue)

        $block.append($field)
        $formItem.append($block)
        $container.append($formItem)

    }

    return $container;
}


function addConfig(type){
    var configDataField = configDataFields[type];
    var configData = {}
    configDataField.forEach(function (item) {
        configData[item['fieldName']] = ''
    })

    var config = {}
    configFields.forEach(function (item) {
        config[item['fieldName']] = ''
    })

    config['configType'] = type;
    config['configData'] = JSON.stringify(configData);

    var $form = buildConfigItem(config)
    buildFormItem($(".layui-colla-content[config-type=" + type + "] .layui-colla-content-item"), $form, "config-btn-remove")
}

function buildConfigItem(data){

    var $form = $("<form class=\"layui-form \" editing=\"false\" ></form>")
    for ( var fieldName in data) {

        if('updateTime' == fieldName){
            continue;
        }

        var fieldValue = data[fieldName]

        if('configData' == fieldName){
            var $subForm = buildConfigData(fieldValue)
            $form.append($subForm)
            continue;
        }


        $formItem = $(" <div class=\"layui-form-item\"></div>")
        $formItem.append($("<label class=\"layui-form-label\">/label>").text(fieldName))

        $block = $("<div class=\"layui-input-block\"></div>")

        var $field = genInputField( fieldName, fieldValue)

        $block.append($field)
        $formItem.append($block)
        $form.append($formItem)

        if ([ 'configType', 'configID' ].indexOf(fieldName) > -1){
            $formItem.addClass("layui-hide")
        }
    }

    return $form

}


function queryConfigFields() {

    ajaxPost("/mock_server/system/db/field.ajax", {}, function (response) {
        configFields = response.data.list
    })


}


//查询配置
function queryConfigs(type) {
    ajaxPost("/mock_server/system/list.ajax", {type: type}, function (response) {
        $(".layui-colla-content[config-type=" + type + "] .layui-colla-content-item").children().remove()

        for ( var i in response.data.list){
            var config = response.data.list[i]
            var $form = buildConfigItem(config)

            buildFormItem($(".layui-colla-content[config-type=" + type + "] .layui-colla-content-item"), $form, "config-btn-remove")
        }
    })
}

$(function () {

    //注意：折叠面板 依赖 element 模块，否则无法进行功能性操作
    layui.use('element', function(){
        var element = layui.element;

        //折叠面板

        element.on('collapse(collapse)', function(data){
            if(data.show) {

                var type = $(data.content).attr("config-type");
                queryConfigs(type)
            }
        });

    })


    //添加配置项
    $(document).on("click", ".system-btn-add", function () {
        var self = this;
        var type = $(self).parent("[config-type]:eq(0)").attr("config-type");

        ~(function (initConfig) {
            if (type in configDataFields){
                initConfig()
            }else{
                ajaxPost("/mock_server/system/type/field.ajax", {type: type}, function (response) {
                    configDataFields[type] = response.data.list
                    initConfig()
                })
            }

        })(function(){
            addConfig(type)
        })
    })


    //保存配置项
    $(document).on("click", ".system-btn-save", function () {
        var self = this;
        var type = $(self).parent("[config-type]:eq(0)").attr("config-type");
        var saveSuccess = true;

        ~(function(buildConfigData){
            $(self).prevAll(".layui-colla-content-item").find(".layui-form[editing=true]").each(function (i, form) {
                var configData = buildConfigData($(form));

                var data = $(form).serializeObject();
                data['configData'] = JSON.stringify(configData)

                ajaxPost("/mock_server/system/save.ajax", data, function (response) {
                    layer.msg(response.msg ? response.msg : "保存成功")
                    $(form).attr("editing", "false")

                },function () {
                    saveSuccess = false
                })

            })

            setTimeout(function () {
                if (saveSuccess){
                    queryConfigs(type)
                }

            }, 500)
        })(function($form){
            var configData = {}
            $form.find("[sub-name]").each(function (i, item) {
                configData[$(item).attr('sub-name')] = $(item).val()
            })

            return configData;
        })


    })




    //初始化配置字段
    queryConfigFields()

})