<#include "../../common/header.tpl">

<div class="layui-body">
    <!-- 内容主体区域 -->
    <div class="content-head">
        <span class="layui-breadcrumb">
            <a href="">GRPC</a>
            <a href="">分组</a>
            <a><cite>列表</cite></a>
        </span>
        <div class="head-container right">
            <a href="/mock_server/grpc/group/info.html">
                <svg class="icon">
                    <use xlink:href="#icon-xinzeng1"></use>
                </svg>
            </a>
            <svg class="icon group-import" aria-hidden="true" id="group-import">
                <use xlink:href="#icon-daoru"></use>
            </svg>

            <svg class="icon group-export icon-unvaliable" aria-hidden="true" id="group-export">
                <use xlink:href="#icon-daochu"></use>
            </svg>
        </div>
        <div class="clear"></div>
    </div>
    <hr>
    <div style="/* padding: 15px; */" id="group-list" lay-even lay-filter="group-list"></div>

</div>

<style>
    thead th:first-child {
        cursor: pointer;
    }

    .icon-unvaliable {
        fill: #ccc
    }

</style>
<script>

    layui.use(['table', 'upload', 'layer'], function () {
        var table = layui.table;
        var upload = layui.upload;
        var layer = layui.layer;

        //执行实例
        var uploadInst = upload.render({
            elem: '#group-import' //绑定元素
            , url: '/mock_server/grpc/group/import.ajax' //上传接口
            , accept: 'file'
            // ,exts: 'data'
            , done: function (res) {
                //上传完毕回调
                if (res.code == SUCCESS_CODE) {
                    layer.msg("导入成功")
                } else {
                    layer.msg(res.messge)
                }
            }
            , error: function () {
                //请求异常回调
                layer.msg("网络异常")
            }
        });

        table.render({
            id: 'group-list'
            , elem: '#group-list'
            , url: '/mock_server/grpc/group/list.ajax'
            , method: "post"
            , contentType: "application/json"
            , cellMinWidth: 80 //全局定义常规单元格的最小宽度，layui 2.2.1 新增
            , page: true //开启分页
            , limit: 20
            , LAY_CHECKED: true  //是否全选状态
            , cols: [[
                {field:'groupID', type:'checkbox', title: '全选', width:60}
                ,{field:'groupName', title: 'groupName',
                   templet: '<div><a href="/mock_server/grpc/mapping/list.html?groupID={{d.groupID}}&groupName={{d.groupName}}">{{d.groupName}}</a></div>'}
                ,{field:'sortIndex', title: 'sortIndex', width:100}
                ,{field:'isActive', title: 'isActive', width:100}

            ,{fixed: 'right', align:'center', toolbar: '#oprateBar', title:'操作', width:200 } //这里的toolbar值是模板元素的选择器

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


        table.on('checkbox(group-list)', function (obj) {
            // console.log(obj); //当前是否选中状态
            // console.log(obj.checked); //当前是否选中状态
            // console.log(obj.data); //选中行的相关数据
            // console.log(obj.type); //如果触发的是全选，则为：all，如果触发的是单选，则为：one

            var checkStatus = table.checkStatus('group-list')
            //
            // console.log(checkStatus.data) //获取选中行的数据
            // console.log(checkStatus.data.length) //获取选中行数量，可作为是否有选中行的条件
            // console.log(checkStatus.isAll ) //表格是否全选

            if (checkStatus.data.length > 0) {
                setExportButton(true)
            } else {
                setExportButton(false)
            }
        });


        // 导出
        $(".group-export").click(function (event) {
            if ($(this).data("enable") == true) {
                var checkStatus = table.checkStatus('group-list')
                if (checkStatus.data.length > 0) {
                    var groupIDArr = []
                    checkStatus.data.forEach(function (item) {
                        groupIDArr.push(item.groupID)
                    })

                    downloadGroupData(groupIDArr.join(','))
                }
                console.log("enable")
            } else {
                console.log("disable")
            }
        })

    });


    //设置导出按钮的颜色和是否可用
    function setExportButton(enable) {
        if (enable) {
            $(".group-export").removeClass("icon-unvaliable").data("enable", true)
            $(".group-import").addClass("icon-unvaliable").data("enable", false)
        } else {
            $(".group-export").addClass("icon-unvaliable").data("enable", false)
            $(".group-import").removeClass("icon-unvaliable").data("enable", true)
        }
    }

    function deleteItem(groupID, name) {
        msg = "确定要删除<span style='color:red'>" + name + "</span>?"
        layer.confirm(msg, function (index) {
            ajaxPost('/mock_server/grpc/group/delete.ajax', {groupID:groupID}, function (response) {
                location.reload();
            })
            layer.close(index);
        })
    }

    function downloadGroupData(groupIDs) {
        var url = "/mock_server/grpc/group/export.htm?groupIDs=" + groupIDs
        window.open(url)
    }

</script>

<script type="text/html" id="checkboxBar">
    <input type="checkbox" name="" title="" data-id="{{d.groupID}}" lay-skin="primary">
    <!-- 这里同样支持 laytpl 语法，如： -->
</script>

<script type="text/html" id="oprateBar">
    <a class="layui-btn layui-btn-xs" lay-event="detail"
       href="/mock_server/grpc/mapping/list.html?groupID={{d.groupID}}&groupName={{d.groupName}}">
        <i class="layui-icon layui-icon-list layui-bg-green" title="接口列表"></i>
    </a>
    <a class="layui-btn layui-btn-xs layui-bg-green" lay-event="edit"
       href="/mock_server/grpc/group/info.html?groupID={{d.groupID}}">
        <i class="layui-icon layui-icon-edit" title="编辑"></i>
    </a>

    <a class="layui-btn layui-btn-danger layui-btn-xs" lay-event="del"
       href="javascript:deleteItem({{d.groupID}}, '{{d.groupName}}');">
        <i class="layui-icon layui-icon-delete" title="删除"></i>
    </a>

    <!-- 这里同样支持 laytpl 语法，如： -->

</script>


<#include "../../common/footer.tpl">