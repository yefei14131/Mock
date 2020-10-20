var saveGlobalVarSuccess;

$(function () {


    ////////////////////////// 匹配规则
    //添加全局变量事件
    $(document).on("click", ".global-var-btn-add", addGlobalVar)

    //删除全局变量事件
    $(document).on("click", ".global-var-btn-remove", function () {
        removeGlobalVar($(this))
    })

    //保存全局变量事件
    $(document).on("click", ".global-var-btn-save", function () {
        saveGlobalVar()

        setTimeout(function () {
            if (saveGlobalVarSuccess) {
                queryGlobalVar(requestID, protocol)
            } else {
                saveGlobalVarSuccess = true
            }

        }, 500)

    })

})

function queryGlobalVar(requestID, protocol) {
    ajaxPost("/mock_server/mapping/global_var/list.ajax", {
        requestID: requestID,
        protocol: protocol
    }, function (response) {
        $(".mapping-global-var .layui-colla-content-item").children().remove();

        for (var i in response.data.list) {
            var itemData = response.data.list[i];

            var $form = buildGlobalVarItem(itemData)

            buildFormItem($(".mapping-global-var .layui-colla-content-item"), $form, 'global-var-btn-remove')

            form.render('select');
        }

        $(".mapping-global-var .operator.layui-form-item:first").addClass("layui-hide")
    })
}


function addGlobalVar() {
    var data = {'varName': '', 'varValue': '', 'protocol': protocol, 'requestID': requestID};

    var $form = buildGlobalVarItem(data)
    buildFormItem($(".mapping-global-var .layui-colla-content-item"), $form, 'global-var-btn-remove')

    form.render('select');
    $(".mapping-global-var .operator.layui-form-item:first").addClass("layui-hide")
}

function removeGlobalVar($btn) {
    msg = "确定要删除?"
    window.layer.confirm(msg, function (index) {

        if ($btn.prev().find("[name=globalVarID]").length > 0) {
            var globalVarID = $btn.prev().find("[name=globalVarID]").val()

            ajaxPost("/mock_server/mapping/global_var/delete.ajax", {globalVarID: globalVarID}, function (response) {
                $btn.prev().remove()
                $btn.next().remove()
                $btn.remove();
            })
        } else {

            $btn.prev().remove()
            $btn.next().remove()
            $btn.remove();
        }

        window.layer.close(index);
    })
}


function saveGlobalVar(callback) {

    function doSave() {
        $(".mapping-global-var .layui-colla-content-item .layui-form[editing=true]").each(function (i, item) {

            var data = $(item).serializeObject();
            if (!!requestID) {
                data['requestID'] = requestID;
            }

            ajaxPost("/mock_server/mapping/global_var/save.ajax", data, function (response) {

                layer.msg(response.message);
                $(item).attr('editing', 'false')
            }, function () {
                saveGlobalVarSuccess = false;
            })
        })

    }

    if (!requestID || requestID <= 0) {
        saveMapping(function () {
            doSave();
        })
    } else {
        doSave()
    }

}


function buildGlobalVarItem(data) {

    var $form = $("<form class=\"layui-form global-var-form\" editing=\"false\" ></form>")
    for (var fieldName in data) {
        if ('updateTime' == fieldName) {
            continue;
        }

        var fieldValue = data[fieldName]

        $formItem = $(" <div class=\"layui-form-item\"></div>")
        $formItem.append($("<label class=\"layui-form-label\">/label>").text(fieldName))

        $block = $("<div class=\"layui-input-block\"></div>")


        if ('protocol' == fieldName) {
            var $field = genInputField(fieldName, fieldValue)

        } else {
            var $field = genInputField(fieldName, fieldValue)
        }

        if (['globalVarID', 'protocol', 'requestID'].indexOf(fieldName) > -1) {
            $formItem.addClass("layui-hide")
        }

        $block.append($field)
        $formItem.append($block)

        $form.append($formItem).after($("<button class=\"layui-btn-xs layui-btn layui-btn-radius layui-btn-danger global-var-btn-remove\"><i class=\"layui-icon\">&#xe640;</i> </button>"))
            .after($("<hr class=\"layui-bg-blue\">"))

    }

    return $form

}
