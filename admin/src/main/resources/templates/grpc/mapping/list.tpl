<#include "../../common/header.tpl">

<div class="layui-body">
    <!-- 内容主体区域 -->
    <div class="content-head">
        <span class="layui-breadcrumb">
          <a href="">GRPC</a>
          <a href="">接口</a>
          <a><cite>列表</cite></a>
        </span>
        <div class="head-container right">
            <a href="/mock_server/grpc/mapping/info.html?groupID=${groupID}&groupName=${groupName}">
                <svg class="icon">
                    <use xlink:href="#icon-xinzeng1"></use>
                </svg>
            </a>
        </div>
        <div class="clear"></div>
    </div>
    <div style="/* padding: 15px; */" id="item-list" lay-even></div>
</div>


<script>

    layui.use('table', function () {
        var table = layui.table;

        table.render({
            elem: '#item-list'
            , url: '/mock_server/grpc/mapping/list.ajax'
            , method: "post"
            , contentType: "application/json"
            , where: {groupID: ${groupID}}
            , cellMinWidth: 80 //全局定义常规单元格的最小宽度，layui 2.2.1 新增
            , cols: [[
            <#list fields as field >
                {field:'${field.fieldName}', title: '${field.fieldName}' <#if 'requestID' == field.fieldName || 'groupID' == field.fieldName || 'isActive' == field.fieldName || 'sortIndex' == field.fieldName >, width: 88</#if> },
            </#list>
            {fixed: 'right', align:'center', toolbar: '#barDemo', title:'管理'} //这里的toolbar值是模板元素的选择器
        ]]
        ,
        parseData : function (res) {

            if (res.code != SUCCESS_CODE) {
                layer.msg(res.message)
            } else {
                // layui
                res.code = 0
            }

            var data = {
                "code": res.code, //解析接口状态
                "msg": res.message, //解析提示文本
                "count": res.data.total, //解析数据长度
                "data": res.data.list //解析数据列表
            };
            return data;
        }
        // ,response : {
        //     statusCode: 200
        // }
    })
        ;
    });

    function deleteItem(requestID, name) {
        msg = "确定要删除<span style='color:red'>" + name + "</span>?"
        layer.confirm(msg, function (index) {
            ajaxPost('/mock_server/grpc/mapping/delete.ajax', {requestID:requestID}, function (response) {
                location.reload();
            })
            layer.close(index);
        })
    }

    function cloneItem(requestID, name) {
        msg = "确定要克隆<span style='color:red'>" + name + "</span>?"
        layer.confirm(msg, function (index) {
            ajaxPost('/mock_server/grpc/mapping/clone.ajax', {requestID:requestID}, function (response) {
                location.reload();
            })
            layer.close(index);
        })
    }


</script>

<script type="text/html" id="barDemo">
    <a class="layui-btn layui-btn-xs layui-bg-green" lay-event="edit"
       href="/mock_server/grpc/mapping/info.html?requestID={{d.requestID}}">
        <i class="layui-icon layui-icon-edit" title="编辑"></i>
    </a>
    <a class="layui-btn layui-btn-xs" lay-event="edit"
       href="javascript:cloneItem({{d.requestID}}, '{{d.memo.replace(/["\']/g, "")}}');">克隆</a>

<a class="layui-btn layui-btn-danger layui-btn-xs" lay-event="del" href="javascript:deleteItem({{d.requestID}}, '{{d.memo.replace(/["\']/g, "")}}');">
<i class="layui-icon layui-icon-delete" title="删除"></i>
</a>

</script>




<#include "../../common/footer.tpl">