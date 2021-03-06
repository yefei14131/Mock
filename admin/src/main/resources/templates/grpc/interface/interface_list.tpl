<#include "../../common/header.tpl">

<div class="layui-body">
    <!-- 内容主体区域 -->
    <div class="content-head">
        <span class="layui-breadcrumb">
          <a><cite>Interface列表</cite></a>
        </span>
        <div class="head-container right">
            <a href="/mock_server/system/grpc/interface/info.html">
                <svg class="icon">
                    <use xlink:href="#icon-xinzeng1"></use>
                </svg>
            </a>
        </div>
        <div class="clear"></div>
    </div>
    <div style="/* padding: 15px; */" id="item-list" lay-even></div>

</div>

<ul class="layui-timeline" style="margin: 20px; width:80%; ">
    <li class="layui-timeline-item template" style="display: none;">
        <i class="layui-icon layui-timeline-axis">&#xe63f;</i>
        <div class="layui-timeline-content layui-text">
            <h3 class="layui-timeline-title">8月18日</h3>
            <p class="log-event-content" style="overflow: auto;">

            </p>
        </div>
    </li>
</ul>

<script>

    function fillZeroPlus(i) {
        return i < 100 ? "0" + fillZero(i) : "" + i;
    }

    function fillZero(i) {
        return i < 10 ? "0" + i : "" + i;
    }

    function dateFormat(date) {
        return date.getFullYear() + "-" + fillZero(date.getMonth() + 1) + "-" + fillZero(date.getDate()) + " " + fillZero(date.getHours()) + ":" + fillZero(date.getMinutes()) + ":" + fillZero(date.getSeconds()) + "." + fillZeroPlus(date.getMilliseconds())
    }


    function deleteItem(itemID, name) {
        msg = "确定要删除<span style='color:red'>" + name + "</span>?"
        layer.confirm(msg, function (index) {
            ajaxPost('/mock_server/system/grpc/interface/delete.ajax', {itemID:itemID}, function (response) {
                location.reload();
            })
            layer.close(index);
        })
    }

    layui.use('table', function () {
        var table = layui.table;

        table.render({
            elem: '#item-list'
            , url: '/mock_server/system/grpc/interface/list.ajax'
            , method: "post"
            , contentType: "application/json"
            , page: true
            , limit: 20
            //,cellMinWidth: 80 //全局定义常规单元格的最小宽度，layui 2.2.1 新增
            , cols: [[
            <#list fields as field >
                {field:'${field.fieldName}', title: '${field.fieldName}'<#if 'itemID' == field.fieldName || 'isActive' == field.fieldName || 'sortIndex' == field.fieldName >, width: 88</#if> },
            </#list>
                {fixed: 'right', align:'center', toolbar: '#barDemo', title:'管理', width: 88} //这里的toolbar值是模板元素的选择器
            ]]
            ,
        parseData : function (res) {

            if (res.code != SUCCESS_CODE) {
                layer.msg(res.message)
            } else {
                // layui
                res.code = 0
            }

            for (var i in res.data.list) {
                dateItem = res.data.list[i]
                var d = new Date(dateItem['requestTime'])
                dateItem['requestTime'] = dateFormat(d)
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

</script>

<script type="text/html" id="barDemo">
    <a class="layui-btn layui-btn-xs layui-bg-green" lay-event="edit"
       href="/mock_server/system/grpc/interface/info.html?itemID={{d.itemID}}">
        <i class="layui-icon layui-icon-edit" title="编辑"></i>
    </a>

    <a class="layui-btn layui-btn-danger layui-btn-xs" lay-event="del"
       href="javascript:deleteItem({{d.itemID}}, '{{d.artifactID}}');">
        <i class="layui-icon layui-icon-delete" title="删除"></i>
    </a>
</script>

<#include "../../common/footer.tpl">