<#include "../../common/header.tpl">
<script src="/js/jquery.form.js"></script>
<div class="layui-body">
    <!-- 内容主体区域 -->
    <div class="content-head">
        <span class="layui-breadcrumb">
          <a href="">GRPC</a>
          <a href="javascript:history.back()">接口</a>
          <a><cite>编辑</cite></a>
        </span>
    </div>
    <hr>
    <form class="layui-form" action="/mock_server/grpc/mapping/save" method="post" id="item-form"
          style="padding: 15px;">

        <blockquote class="layui-elem-quote layui-quote-nm mapping-url-demon"><span>grpc服务地址：</span><span class="url"></span></blockquote>

        <input type="hidden" name="requestID" value="0">

        <#list fields as field >
        <#if 'groupID' == field.fieldName>
        <div class="layui-form-item layui-hide">
            <label class="layui-form-label">${field.fieldName}</label>
            <div class="layui-input-block">
                <input type="text" name="${field.fieldName}" required lay-verify="required"
                       placeholder="${field.fieldName}" autocomplete="off" class="layui-input" value="${groupID}">
            </div>
        </div>
        <#elseif 'serviceName' == field.fieldName >
        <div class="layui-form-item">
            <label class="layui-form-label">${field.fieldName}</label>
            <div class="layui-input-block">
                <select name="${field.fieldName}" lay-verify="required" lay-search id="grpc-service-select"
                        lay-filter="grpc-service-select">

                </select>
            </div>
        </div>
        <#elseif 'methodName' == field.fieldName >
        <div class="layui-form-item">
            <label class="layui-form-label">${field.fieldName}</label>
            <div class="layui-input-block">
                <select name="${field.fieldName}" lay-verify="required" lay-search id="grpc-method-select"
                        lay-filter="grpc-method-select">

                </select>
            </div>
        </div>
        <#else>
        <div class="layui-form-item">
            <label class="layui-form-label">${field.fieldName}</label>
            <#if 'isActive' == field.fieldName>
            <div class="layui-input-block">
                <select name="${field.fieldName}" lay-verify="">
                    <option value="true">有效</option>
                    <option value="false">无效</option>
                </select>
            </div>

            <#elseif 'responseType' == field.fieldName >
            <div class="layui-input-block">
                <select name="${field.fieldName}" lay-verify="">
                    <#list responseTypes as typeEnum >
                    <option value="${typeEnum.type}">${typeEnum.type}</option>
                </#list>
                </select>
            </div>
            <#elseif 'responseBody' == field.fieldName >
            <div class="layui-input-block">
                <textarea name="responseBody" class="layui-textarea"></textarea>

            </div>
            <#else>
            <div class="layui-input-block">
                <input type="text" name="${field.fieldName}" required lay-verify="required"
                       placeholder="${field.fieldName}" autocomplete="off"
                <#if 'sortIndex' == field.fieldName >
                    class="layui-input" value="99"
                <#else>
                    class="layui-input"
                </#if>>
        </div>
    </#if>
</div>
</#if>
</#list>
<div class="layui-form-item">
    <div class="layui-input-block">
        <button class="layui-btn" lay-submit lay-filter="mapping">保存</button>
        <button type="reset" class="layui-btn layui-btn-primary">重置</button>
    </div>
</div>
</form>

<fieldset class="layui-elem-field layui-field-title" style="margin-top: 30px;">
    <legend>扩展内容</legend>
</fieldset>

<div class="layui-collapse" lay-filter="collapse">
    <div class="layui-colla-item">
        <h2 class="layui-colla-title">脚本列表</h2>

        <div class="layui-colla-content mapping-scripts">
            <blockquote class="layui-elem-quote layui-quote-nm layui-bg-gray">
                脚本提供4个内置参数，类型为字典：<br>
                params（请求参数）、vars（自定义变量）、headers 、cookies。<br>
                在脚本里可以往变量 vars 中赋值，之后在命中规则、responseBody回调中使用。<br>
                变量生命周期为单次请求。
            </blockquote>

            <div class="layui-colla-content-item">

            </div>
            <button class="layui-btn layui-btn-xs layui-btn-radius script-btn-add">
                <i class="layui-icon">&#xe608;</i> 添加
            </button>
            <button class="layui-btn layui-btn-xs layui-btn-radius layui-btn-warm script-btn-save">
                <i class="layui-icon">&#xe608;</i> 保存
            </button>
        </div>
    </div>
    <div class="layui-colla-item">
        <h2 class="layui-colla-title">匹配条件</h2>
        <div class="layui-colla-content mapping-rules" editing="false">
            <blockquote class="layui-elem-quote layui-quote-nm layui-bg-gray">
                使用说明参考REST
                <br><br>

                匹配条件的执行顺序： sortIndex desc, rulesDetailID asc <br>
                如果当前页面修改了 sortIndex，保存之后请一定要刷新看下顺序和operator是否有问题！ <br>
            </blockquote>

            <div class="layui-colla-content-item">

            </div>

            <button class="layui-btn layui-btn-xs layui-btn-radius rules-btn-add">
                <i class="layui-icon">&#xe608;</i> 添加
            </button>
            <button class="layui-btn layui-btn-xs layui-btn-radius layui-btn-warm rules-btn-save">
                <i class="layui-icon">&#xe608;</i> 保存
            </button>
        </div>
    </div>
    <div class="layui-colla-item">
        <h2 class="layui-colla-title">全局变量添加</h2>
        <div class="layui-colla-content mapping-global-var" editing="false">
            <blockquote class="layui-elem-quote layui-quote-nm layui-bg-gray">
                使用说明参考REST
                <br><br>

                变量名和变量值都可以使用请求参数的变量
                变量有效期，暂时是30s，在代码里写死
            </blockquote>

            <div class="layui-colla-content-item">

            </div>

            <button class="layui-btn layui-btn-xs layui-btn-radius global-var-btn-add">
                <i class="layui-icon">&#xe608;</i> 添加
            </button>
            <button class="layui-btn layui-btn-xs layui-btn-radius layui-btn-warm global-var-btn-save">
                <i class="layui-icon">&#xe608;</i> 保存
            </button>
        </div>
    </div>
    <div class="layui-colla-item" lay-filter="jobs" editing="false">
        <h2 class="layui-colla-title">回调任务</h2>
        <div class="layui-colla-content mapping-jobs">
            <div class="layui-colla-content-item">

            </div>

            <button class="layui-btn layui-btn-xs layui-btn-radius job-btn-add">
                <i class="layui-icon">&#xe608;</i> 添加计划
            </button>
            <button class="layui-btn layui-btn-xs layui-btn-radius layui-btn-warm job-btn-save">
                <i class="layui-icon">&#xe608;</i> 保存
            </button>
        </div>
    </div>
</div>

</div>
<style>
    /*.mapping-rules .operator.layui-form-item:first-child
    {display:none} */
    .mapping-rules .operator.layui-form-item:first-child

    {visibility:hidden;}

</style>
<script src="/js/mapping_jobs.js"></script>
<script>

    var protocol = '${protocol}';
    var requestID = '${requestID}'
    var form;
    var scriptLanguageList = []
    var scriptFields = []
    var rulesFields = []

    var groupID = '${groupID}';
    var serviceName = '${serviceName}';
    var methodName = '${methodName}';

    var serverInfo;
    var saveScriptsSuccess = true;

    initGrpcServiceProvider(function () {
        var timer = setInterval( function () {
            if (typeof form != "undefined" ) {
                renderGrpcServiceSelect(Object.keys(grpcServiceProvider.data));
                clearInterval(timer)
                //渲染被编辑的mapping
                initMappingDetail();
            }
        }, 10)
    });

    function loadJs(url) {
        $("body").append($("<script>").attr("src", url))
    }

    //保存Mpping
    function saveMapping(callback) {

        var data = $("#item-form").serializeObject();
        ajaxPost("/mock_server/grpc/mapping/save.ajax", data, function (response) {
            layer.msg(response.message)
            if (data['requestID'] == 0) {
                $("[name=requestID]").val(response.data)
                requestID = response.data
                invokeFunc(callback)
            }

            var newServiceName = $("[name=serviceName]:first").val()
            var newMethodName = $("[name=methodName]:first").val()

            if (newServiceName != serviceName || newMethodName != methodName) {
                $(".mapping-scripts .layui-colla-content-item .layui-form").attr("editing", "true")
                serviceName = newServiceName
                methodName = newMethodName
                saveScripts();
            }
        })
    }

    function initMappingDetail() {

        if (requestID > 0) {
            // queryGrpcMapping
            ajaxPost("/mock_server/grpc/mapping/get.ajax", {requestID: requestID}, function (response) {

                renderGrpcServiceSelect(Object.keys(grpcServiceProvider.data));
                renderGrpcMethodSelect(grpcServiceProvider.getAllMethod(response.data.serviceName));
                // renderGrpcMethodSelectInMapping();

                for (var fieldName in response.data) {
                    var fieldValue = response.data[fieldName]
                    $('[name=' + fieldName + ']').val(fieldValue + '')

                    if (fieldName == 'isActive') {
                        $('[name=' + fieldName + ']').next(".layui-form-select").find("input").val(fieldValue ? "有效" : "无效")
                    }
                    if (fieldName == 'methodName') {
                        $('[name=' + fieldName + ']').next(".layui-form-select").find("input").val(fieldValue)
                    }
                    if (fieldName == 'serviceName') {
                        $('[name=' + fieldName + ']').next(".layui-form-select").find("input").val(fieldValue)
                    }
                }

                groupID = response.data.groupID
                serviceName = response.data.serviceName
                methodName = response.data.methodName

            })
        }
    }

    // 显示本次mock的请求实例
    function renderMockRequestDemo() {
        var hostname = "xxx.grpc.mock.com"
        var port = 6565

        if (!serverInfo || !serverInfo['host']) {
            console.log("没有配置mock grpc的请求域名, 使用默认域名")
        } else {
            hostname = serverInfo['host'];
            port = serverInfo['port'];
        }
        var url = hostname + ":" + port;
        $(".mapping-url-demon .url").html(url)
    }


    $(function () {

        layui.use(['layer', 'element', 'form'], function () {

            window.layer = layui.layer
            window.form = layui.form;
            window.element = layui.element;

            var jsList = ["/js/grpc_script.js", "/js/mapping_rules.js", "/js/mapping_task.js", "/js/mapping_global_var.js"]

            jsList.forEach(function (url) {
                loadJs(url)
            })


            //获取mock服务的配置
            ajaxPost("/mock_server/system/server/get.ajax", {protocol: protocol}, function (response) {
                serverInfo = response.data
                renderMockRequestDemo();
            })

            //获取支持的脚本语言类型
            ajaxPost("/mock_server/enum/language/list.ajax", {}, function (response) {
                scriptLanguageList = response.data.list
            })

            //获取脚本的字段
            ajaxPost("/mock_server/grpc/script/db/field.ajax", {}, function (response) {
                scriptFields = response.data.list
            })

            //获取匹配规则的字段
            ajaxPost("/mock_server/mapping/rules/db/field.ajax", {}, function (response) {
                rulesFields = response.data.list
            })


            // $(".script-btn-add").click(addScript)

            //////////////////////////  脚本
            //添加脚本事件
            $(document).on("click", ".script-btn-add", addScript)

            //删除脚本事件
            $(document).on("click", ".script-btn-remove", function () {
                removeScript($(this))
            })

            //保存脚本事件
            $(document).on("click", ".script-btn-save", function () {
                saveScripts()

                setTimeout(function () {
                    if (saveScriptsSuccess) {

                        queryScripts(groupID, serviceName, methodName)
                    } else {
                        saveScriptsSuccess = true
                    }
                }, 500)

            })


            form.on('select()', function (data) {
                $(data.elem).change()
            });

            form.on('select(grpc-service-select-task)', function (data) {
                renderGrpcMethodSelectInTask($(data.elem))
            });

            form.on('select(grpc-service-select)', function (data) {
                renderGrpcMethodSelect(grpcServiceProvider.getAllMethod($("#grpc-service-select").val()))
            });

            //保存 mapping
            form.on('submit(mapping)', function () {
                saveMapping()
                return false;
            });


            //注意：折叠面板 依赖 element 模块，否则无法进行功能性操作
            element.on('collapse(collapse)', function (data) {
                if (data.show) {

                    if ($(data.content).hasClass("mapping-scripts")) {
                        var groupID = $("[name=groupID]").val().trim()
                        var serviceName = $("[name=serviceName]").val().trim()
                        var methodName = $("[name=methodName]").val().trim()

                        queryScripts(groupID, serviceName, methodName)

                    } else if ($(data.content).hasClass("mapping-rules")) {
                        var requestID = $("[name=requestID]").val().trim()

                        queryRules(requestID, protocol);


                    } else if ($(data.content).hasClass("mapping-jobs")) {

                        var requestID = $("[name=requestID]").val().trim()

                        queryJobs(requestID, protocol);

                    } else if ($(data.content).hasClass("mapping-global-var")) {

                        var requestID = $("[name=requestID]").val().trim()

                        queryGlobalVar(requestID, protocol);
                    }
                }
            });


            grpcServiceProvider.loadData();
        })

    })

    ///////////////////////////////////

    function renderGrpcServiceSelect(list) {
        if (!!list) {
            $("#grpc-service-select option").remove()
            list.forEach(function (value) {
                $("#grpc-service-select").append($("<option>").attr("value", value).text(value))
            })
            $('#grpc-service-select').val(serviceName);
            form.render('select')

            renderGrpcMethodSelect(grpcServiceProvider.getAllMethod(serviceName))
        }
    }

    function renderGrpcMethodSelect(list) {
        if (!!list) {

            $("#grpc-method-select option:gt(0)").remove()
            list.forEach(function (value) {
                $("#grpc-method-select").append($("<option>").attr("value", value).text(value))
            })
            $('#grpc-method-select').val(methodName);

            form.render('select')
        }
    }

    function renderGrpcMethodSelectInMapping($serviceNameSelect) {
        var serviceName = $serviceNameSelect.val();
        var allMethodList = grpcServiceProvider.getAllMethod(serviceName)
        var $methodNameSelect = $serviceNameSelect.parents('layui-form-item:first').siblings().find('[name=methodName]')
        var $methodNameVal = $methodNameSelect.val();
        $methodNameSelect.children('option:gt(0)').remove();
        allMethodList.forEach(function (methodName) {
            $methodNameSelect.appd($("<option>").attr("value", methodName).text(methodName))
        })
        $methodNameSelect.val($methodNameVal)

        form.render('select')

        if (!!list) {

            $("#grpc-method-select option").remove()
            list.forEach(function (value) {
                $("#grpc-method-select").append($("<option>").attr("value", value).text(value))
            })
            if (typeof(methodName) != 'undefined' && methodName != null) {
                $('#grpc-method-select').val(methodName);
            }


            form.render('select')
        }
    }





</script>


<#include "../../common/footer.tpl">