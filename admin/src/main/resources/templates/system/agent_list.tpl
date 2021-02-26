<#include "../common/header.tpl">

<div class="layui-body">
    <!-- 内容主体区域 -->
    <div class="content-head">
        <span class="layui-breadcrumb">
            <a><cite>系统</cite></a>
            <a><cite>Agent在线列表</cite></a>
        </span>
    </div>
    <hr>
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
        return date.getFullYear() + "-" + fillZero(date.getMonth() + 1) + "-" + fillZero(date.getDate()) + " " + fillZero(date.getHours()) + ":" + fillZero(date.getMinutes()) + ":" + fillZero(date.getSeconds())
    }

    layui.use('table', function () {
        var table = layui.table;

        table.render({
            elem: '#item-list'
            , url: '/mock_server/system/agent/list.ajax'
            , method: "post"
            , contentType: "application/json"
            // ,page: true
            // ,limit: 20
            //,cellMinWidth: 80 //全局定义常规单元格的最小宽度，layui 2.2.1 新增
            , cols: [[
                {field: 'serviceName', title: '服务名称'},
                {field: 'agentName', title: '实例名称'},
                {field: 'ip', title: '实例IP'},
                {field: 'isActive', title: '启用', width: 80, edit: true},
                {field: 'firstRequestTime', title: '注册时间'},
                {field: 'lastRequestTime', title: '最后请求时间'}

                // {fixed: 'right', align:'center', toolbar: '#barDemo', title:'管理', width: 88} //这里的toolbar值是模板元素的选择器

            ]]
            , parseData: function (res) {

                if (res.code != SUCCESS_CODE) {
                    layer.msg(res.message)
                } else {
                    // layui
                    res.code = 0
                }

                for (var i in res.data.list) {
                    dateItem = res.data.list[i]
                    dateItem['firstRequestTime'] = dateFormat(new Date(dateItem['firstRequestTime']))
                    dateItem['lastRequestTime'] = dateFormat(new Date(dateItem['lastRequestTime']))
                    dateItem['updateTime'] = dateFormat(new Date(dateItem['updateTime']))
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
        });

        table.on('edit', function (obj) { //注：edit是固定事件名，test是table原始容器的属性 lay-filter="对应的值"
            console.log(obj.value); //得到修改后的值
            console.log(obj.field); //当前编辑的字段名
            console.log(obj.data); //所在行的所有相关数据

            if (obj.field != 'isActive') {
                // 只允许修改 isActive
                return;
            }

            if (obj.value == 'false' || obj.value == 'true') {

                var ID = obj.data.id
                saveActive(ID, obj.value);

            } else {
                layer.msg("无效值，只能是true或者false");
            }

        });
    });

    function saveActive(ID, active) {
        ajaxPost('/mock_server/system/agent/active.ajax', {ID: ID, isActive: active}, function (response) {
            if (active == 'true') {
                layer.msg('已启用')
            } else {
                layer.msg('已禁用')
            }
        })
    }


    function viewItem(traceID, requestPath) {

        ajaxPost('/mock_server/system/log/event/list.ajax', {traceID: traceID}, function (response) {
            response.data.list.forEach(function (item) {

                var title = dateFormat(new Date(item['updateTime'])) + "    " + item['eventName']
                var content = item['eventDesc'] ? item['eventDesc'] : "<br/><br/>"

                var $template = $(".layui-timeline .layui-timeline-item.template");
                var $item = $template.clone().removeClass("template").addClass("real")

                $item.show().find(".layui-timeline-title").text(title)
                $item.find(".log-event-content").text(content)

                $(".layui-timeline").show().append($item)
            })

            layer.open({
                type: 1
                , content: $(".layui-timeline")
                , title: ['运行日志：' + requestPath, 'font-size:24px;font-family: inherit;']
                , area: 'auto'
                , end: function () {
                    $(".layui-timeline").hide().find(".real.layui-timeline-item").remove()
                }
                , maxWidth: 1200
            })
        })

    }


</script>

<script type="text/html" id="barDemo">
    <a class="layui-btn layui-btn-xs" lay-event="edit"
       href="javascript:viewItem('{{d.traceID}}', '{{d.requestPath}}');">查看</a>

    <!-- 这里同样支持 laytpl 语法，如： -->
</script>


<#include "../common/footer.tpl">