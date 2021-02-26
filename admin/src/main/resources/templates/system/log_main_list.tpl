<#include "../common/header.tpl">

<div class="layui-body">
    <!-- 内容主体区域 -->
    <div class="content-head">
        <span class="layui-breadcrumb">
            <a><cite>系统</cite></a>
            <a><cite>调用日志</cite></a>
        </span>
        <!--<div class="layui-btn-container" style="padding: 15px 0px 0px 5px">-->
        <div class="head-container right">
            <form class="layui-form" action="">
                <div class="layui-form-item">
                    <label class="layui-form-label">关键字:</label>
                    <div class="layui-input-block">
                        <input type="text" name="keywords" placeholder="日志关键字" autocomplete="off" class="layui-input"
                               style="display: inline; width: auto;" value="${keywords}">
                        &nbsp;&nbsp;
                        <button class="layui-btn" lay-submit lay-filter="formDemo">搜索</button>
                    </div>
                </div>
            </form>
            <!--</div>-->
        </div>
    </div>
    <hr>
    <div style="/* padding: 15px; */" id="item-list" lay-even ></div>

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
        return i < 100 ?  "0" + fillZero(i) : "" + i;
    }

    function fillZero(i) {
        return i < 10 ? "0" + i  : "" + i;
    }

    function dateFormat(date) {
        return date.getFullYear() + "-" + fillZero(date.getMonth()+1) + "-" + fillZero(date.getDate()) + " " + fillZero(date.getHours()) + ":" + fillZero(date.getMinutes()) + ":" +  fillZero(date.getSeconds()) + "." + fillZeroPlus(date.getMilliseconds())
    }

    layui.use(['table', 'form'], function () {
        var table = layui.table;

        table.render({
            elem: '#item-list'
            ,url:'/mock_server/system/log/list.ajax'
            ,method:"post"
            ,contentType : "application/json"
            , where: {keywords: "${keywords}"}
            ,page: true
            ,limit: 20
            //,cellMinWidth: 80 //全局定义常规单元格的最小宽度，layui 2.2.1 新增
            ,cols: [[
            <#list fields as field >
                {field:'${field.fieldName}', title: '${field.fieldName}'<#if 'protocol' == field.fieldName>, width: 88</#if> },
            </#list>
                {fixed: 'right', align:'center', toolbar: '#barDemo', title:'管理', width: 88} //这里的toolbar值是模板元素的选择器

            ]]
            ,parseData : function (res) {

                if(res.code != SUCCESS_CODE){
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
        });
    });


    function viewItem(traceID, requestPath){

        ajaxPost('/mock_server/system/log/event/list.ajax', {traceID:traceID},function(response) {
            response.data.list.forEach(function(item) {

                var title = dateFormat( new Date(item['updateTime'])) + "    " + item['eventName']
                var content = item['eventDesc'] ? item['eventDesc'] : "<br/><br/>"

                var $template = $(".layui-timeline .layui-timeline-item.template");
                var $item = $template.clone().removeClass("template").addClass("real")

                $item.show().find(".layui-timeline-title").text(title)
                $item.find(".log-event-content").text(content)

                $(".layui-timeline").show().append($item)
            })

            layer.open({
                type: 1
                ,content: $(".layui-timeline")
                ,title: ['运行日志：'+requestPath, 'font-size:24px;font-family: inherit;']
                ,area: 'auto'
                ,end: function () {
                    $(".layui-timeline").hide().find(".real.layui-timeline-item").remove()
                }
                ,maxWidth: 1200
            })
        })

    }


</script>

    <script type="text/html" id="barDemo">
        <a class="layui-btn layui-btn-xs" lay-event="edit" href="javascript:viewItem('{{d.traceID}}', '{{d.requestPath}}');">查看</a>

        <!-- 这里同样支持 laytpl 语法，如： -->

    </script>




<#include "../common/footer.tpl">