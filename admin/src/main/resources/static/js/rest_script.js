
function buildScriptItem(data){

    var $form = $("<form class=\"layui-form script-form\" editing=\"false\" ></form>")
    for ( var fieldName in data) {
        if('updateTime' == fieldName){
            continue;
        }

        var fieldValue = data[fieldName]

        $formItem = $(" <div class=\"layui-form-item\"></div>")
        $formItem.append($("<label class=\"layui-form-label\">/label>").text(fieldName))

        $block = $("<div class=\"layui-input-block\"></div>")

        if ('scriptContent' == fieldName) {
            var $field = genTextareaField( fieldName, fieldValue)

        }else if('isActive' == fieldName){
            var $field = genIsActiveField(fieldValue)

        }else if('scriptLanguage' == fieldName){
            var $field = genSelectField( scriptLanguageList, fieldName, fieldValue)

        }else{
            var $field = genInputField( fieldName, fieldValue)
        }

        if ([ 'scriptID', 'groupID', 'groupCode', 'path'].indexOf(fieldName) > -1){
            $formItem.addClass("layui-hide")
        }

        $block.append($field)
        $formItem.append($block)
        $form.append($formItem)

    }

    return $form

}


//查询所有脚本
function queryScripts(groupID, path) {
    ajaxPost("/mock_server/rest/script/list.ajax", {groupID: groupID, path:path}, function (response) {

        $(".mapping-scripts .layui-colla-content-item").children().remove();

        for ( var i in response.data.list ){

            var $form = buildScriptItem(response.data.list[i])

            buildFormItem($(".mapping-scripts .layui-colla-content-item"), $form, 'script-btn-remove')

            form.render('select');
        }

    })
}

function addScript() {
    var data = {};
    scriptFields.forEach(function (fieldItem) {
        var fieldName = fieldItem.fieldName
        data[fieldName] = ''
    })

    data['isActive'] = 'true'
    data['sortIndex'] = '99'
    data['groupID'] = groupID
    data['groupCode'] = groupCode
    data['path'] = path


    var $form = buildScriptItem(data)
    buildFormItem($(".mapping-scripts .layui-colla-content-item"), $form, 'script-btn-remove')

    form.render('select');
}

function removeScript($btn) {
    msg = "确定要删除?"
    window.layer.confirm(msg, function(index){

        if ( $btn.prev().find("[name=scriptID]").length > 0 ){
            var scriptID = $btn.prev().find("[name=scriptID]").val()

            ajaxPost("/mock_server/rest/script/delete.ajax", { scriptID: scriptID}, function (response) {
                $btn.prev().remove()
                $btn.next().remove()
                $btn.remove();
            })
        }else{

            $btn.prev().remove()
            $btn.next().remove()
            $btn.remove();
        }

        window.layer.close(index);
    })
}


function saveScripts() {

    $(".mapping-scripts .layui-colla-content-item .layui-form[editing=true]").each(function (i, item) {

        var data = $(item).serializeObject();
        if ( !!path ){
            data['path'] = path;
        }

        ajaxPost("/mock_server/rest/script/save.ajax", data, function (response) {

            $(item).attr('editing', 'false')
        },function () {
            saveScriptsSuccess = false;
        })
    })

}
