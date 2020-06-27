
var protocolList = []
var variableSourceList = []
var compareWayList = []
var operatorList = []
var saveRulesSuccess;

$(function(){

    //protocolList
    ajaxPost("/mock_server/enum/protocol/list.ajax", {}, function (response) {
        protocolList = response.data.list
    })

    //variableSourceList
    ajaxPost("/mock_server/enum/variableSource/list.ajax", {}, function (response) {
        variableSourceList = response.data.list
    })

    //compareWayList
    ajaxPost("/mock_server/enum/compare/list.ajax", {}, function (response) {
        compareWayList = response.data.list
    })

    //operatorList
    ajaxPost("/mock_server/enum/operator/list.ajax", {}, function (response) {
        operatorList = response.data.list
    })





    ////////////////////////// 匹配规则
    //添加规则事件
    $(document).on("click", ".rules-btn-add", addRules)

    //删除规则事件
    $(document).on("click", ".rules-btn-remove", function () {
        removeRules($(this))
    })

    //保存规则事件
    $(document).on("click", ".rules-btn-save", function () {
        saveRules()

        setTimeout(function () {
            if (saveRulesSuccess){
                queryRules(requestID, protocol)
            }else{
                saveRulesSuccess = true
            }

        }, 500)

    })

})

function queryRules(requestID, protocol) {
    ajaxPost("/mock_server/mapping/rules/list.ajax", { requestID: requestID, protocol:protocol }, function (response) {
        $(".mapping-rules .layui-colla-content-item").children().remove();

        for ( var i in response.data.list ){
            var itemData = response.data.list[i];
            delete itemData['variableSource'];
            var $form = buildRulesItem(itemData)

            buildFormItem($(".mapping-rules .layui-colla-content-item"), $form, 'rules-btn-remove')

            form.render('select');
        }

        $(".mapping-rules .operator.layui-form-item:first").addClass("layui-hide")
    })
}




function addRules() {
    var data = {};
    rulesFields.forEach(function (fieldItem) {
        var fieldName = fieldItem.fieldName
        data[fieldName] = ''
    })

    data['parentID'] = '0'
    data['sortIndex'] = '99'
    data['requestID'] = requestID
    data['protocol'] = protocol
    data['compareWay'] = '字符串相等'
    // data['variableSource'] = 'parameter'


    var $form = buildRulesItem(data)
    buildFormItem($(".mapping-rules .layui-colla-content-item"), $form, 'rules-btn-remove')

    form.render('select');
    $(".mapping-rules .operator.layui-form-item:first").addClass("layui-hide")
}

function removeRules($btn) {
    msg = "确定要删除?"
    window.layer.confirm(msg, function(index){

        if ( $btn.prev().find("[name=rulesDetailID]").length > 0 ){
            var rulesDetailID = $btn.prev().find("[name=rulesDetailID]").val()

            ajaxPost("/mock_server/mapping/rules/delete.ajax", { rulesDetailID: rulesDetailID}, function (response) {
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


function saveRules(callback) {

    function doSave(){
        $(".mapping-rules .layui-colla-content-item .layui-form[editing=true]").each(function (i, item) {

            var data = $(item).serializeObject();
            if ( !!requestID ){
                data['requestID'] = requestID;
            }

            ajaxPost("/mock_server/mapping/rules/save.ajax", data, function (response) {

                layer.msg(response.message);
                $(item).attr('editing', 'false')
            },function () {
                saveRulesSuccess = false;
            })
        })

    }

    if(!requestID || requestID <= 0){
        saveMapping(function(){
            doSave();
        })
    }else{
        doSave()
    }

}






function buildRulesItem(data){

    var $form = $("<form class=\"layui-form rules-form\" editing=\"false\" ></form>")
    for ( var fieldName in data) {
        if('updateTime' == fieldName){
            continue;
        }

        var fieldValue = data[fieldName]

        $formItem = $(" <div class=\"layui-form-item\"></div>")
        $formItem.append($("<label class=\"layui-form-label\">/label>").text(fieldName))

        $block = $("<div class=\"layui-input-block\"></div>")


        if ( 'protocol' == fieldName ) {
            var $field = genInputField( fieldName, fieldValue)

        }else if('variableSource' == fieldName){
            var $field = genSelectField(variableSourceList, fieldName, fieldValue)

        }else if('compareWay' == fieldName){
            var $field = genSelectField(compareWayList, fieldName, fieldValue)

        }else if('operator' == fieldName){
            if (!fieldValue){
                fieldValue = "&&"
            }
            var $field = genSelectField(operatorList, fieldName, fieldValue)
            $formItem.addClass("operator")

        }else{
            var $field = genInputField(fieldName, fieldValue)
        }

        if ([ 'rulesDetailID', 'requestID', 'parentID', 'protocol'].indexOf(fieldName) > -1){
            $formItem.addClass("layui-hide")
        }

        $block.append($field)
        $formItem.append($block)

        $form.append($formItem).after($("<button class=\"layui-btn-xs layui-btn layui-btn-radius layui-btn-danger rules-btn-remove\"><i class=\"layui-icon\">&#xe640;</i> </button>"))
            .after($("<hr class=\"layui-bg-blue\">"))

    }

    return $form

}
