<#include "../../common/header.tpl">
<script src="/js/jquery.form.js"></script>
<div class="layui-body">
    <!-- 内容主体区域 -->
    <div class="content-head">
        <span class="layui-breadcrumb">
          <a href="javascript:history.back()">GRPC</a>
          <a href="">分组</a>
          <a><cite>编辑</cite></a>
        </span>
    </div>
    <hr>
    <form class="layui-form" action="/mock_server/grpc/group/save" method="post" id="item-form" style="padding: 15px;">
        <input type="hidden" name="groupID" value="${groupID}">

        <#list fields as field >
        <div class="layui-form-item">
            <label class="layui-form-label ">${field.fieldName}</label>
            <#if 'isActive' == field.fieldName>
            <div class="layui-input-block">
                <select name="${field.fieldName}">
                    <option value="true">有效</option>
                    <option value="false">无效</option>
                </select>
            </div>

            <#else>
            <div class="layui-input-block">
                <input type="text" name="${field.fieldName}" <#if ('groupCode' == field.fieldName || 'groupName' ==
                field.fieldName) > required lay-verify="required"
            </#if>
            placeholder="${field.fieldName}" autocomplete="off" class="layui-input" <#if 'sortIndex' == field.fieldName> value="99" </#if> >
</div>
</#if>
</div>
</#list>
<div class="layui-form-item">
    <div class="layui-input-block">
        <button class="layui-btn" lay-submit lay-filter="formDemo">保存</button>
        <button type="reset" class="layui-btn layui-btn-primary">重置</button>
    </div>
</div>
</form>
</div>

<script>


    layui.use('form', function () {
        window.form = layui.form;

        //监听提交
        form.on('submit(formDemo)', function () {
            var data = $("#item-form").serializeObject();
            ajaxPost("/mock_server/grpc/group/save.ajax", data, function (response) {
                layer.msg(response.message)
                setTimeout(function () {
                    history.back()
                }, 2000)
            })
            return false;
        });
    });


    var groupID = ${groupID};


    $(function () {
        if (groupID > 0) {
            ajaxPost("/mock_server/grpc/group/get.ajax", {groupID: groupID}, function (response) {
                console.log(response.data)
                for (var fieldName in response.data) {
                    var fieldValue = response.data[fieldName]
                    $('[name=' + fieldName + ']').val(fieldValue + '')
                    if (fieldName == 'isActive') {
                        // $('[name='+ fieldName +']').next(".layui-form-select").find("input").val(fieldValue ? "有效" : "无效")
                        // window.form.render('select');
                    }
                }
            })
        }
    })


</script>


<#include "../../common/footer.tpl">