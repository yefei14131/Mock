<#include "../../common/header.tpl">
<script src="/js/jquery.form.js"></script>
<div class="layui-body">
    <!-- 内容主体区域 -->
	<div class="content-head">
		<span class="layui-breadcrumb">
		  <a href="">HTTP</a>
		  <a href="javascript:history.back()">接口</a>
		  <a><cite>编辑</cite></a>
		</span>
	</div>
	<hr>
	<form class="layui-form rest-mapping-detail" action="/mock_server/rest/mapping/save" method="post" id="item-form" >

		<blockquote class="layui-elem-quote layui-quote-nm mapping-url-demon"><span>请求url示例：</span><span class="url"></span></blockquote>

		<input type="hidden" name="requestID" value="0">
	  <#list fields as field >
		<#if 'groupID' == field.fieldName>
			<div class="layui-form-item layui-hide">
				<label class="layui-form-label">${field.fieldName}</label>
				<div class="layui-input-block">
					<input type="text" name="${field.fieldName}" required  lay-verify="required" placeholder="${field.fieldName}" autocomplete="off" class="layui-input" value="${groupID}" >
				</div>
			</div>
		<#elseif 'groupCode' == field.fieldName >
			<div class="layui-form-item layui-hide">
				<label class="layui-form-label">${field.fieldName}</label>
				<div class="layui-input-block">
					<input type="text" name="${field.fieldName}" required  lay-verify="required" placeholder="${field.fieldName}" autocomplete="off" class="layui-input" value="${groupCode}" >
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
							<option value="${typeEnum.type}" >${typeEnum.type}</option>
						</#list>
						</select>
					</div>
				<#elseif 'responseBody' == field.fieldName >
					<div class="layui-input-block">
						<textarea name="responseBody" class="layui-textarea"></textarea>

					</div>
				<#else>
					<div class="layui-input-block">
						<input type="text" name="${field.fieldName}" required  lay-verify="required" placeholder="${field.fieldName}" autocomplete="off"
						<#if 'sortIndex' == field.fieldName >
							class="layui-input" value="99"
						<#else>
							class="layui-input"
						</#if>
						>
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
		<div class="layui-colla-item" >
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
					提供临时全局变量存储：<br>
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
		<div class="layui-colla-item" lay-filter="jobs" editing="false" >
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
	/*.mapping-rules .operator.layui-form-item:first-child {display:none}*/
	.mapping-rules .operator.layui-form-item:first-child {visibility:hidden;}

</style>
<script>



var protocol = '${protocol}';
var requestID = '${requestID}'
var form;
var scriptLanguageList = []
var scriptFields = []
var rulesFields = []

var groupID = '${groupID}';
var groupCode = '${groupCode}';
var path = '';
var tmpPath = '';

var serverInfo;

var saveScriptsSuccess = true;


function loadJs(url){
	$("body").append($("<script>").attr("src", url))
}

//保存Mpping
function saveMapping(callback){

    var data = $("#item-form").serializeObject();
    ajaxPost("/mock_server/rest/mapping/save.ajax", data, function (response) {
        layer.msg(response.message)
        if (data['requestID'] == 0){
            $("[name=requestID]").val(response.data)
            requestID = response.data
			invokeFunc(callback)
        }

        var newPath =  $("[name=path]:first").val()
        if(newPath != path){
            $(".mapping-scripts .layui-colla-content-item .layui-form").attr("editing", "true")
            path = tmpPath = newPath
            saveScripts();
        }
    })
}

// 显示本次mock的请求实例
function renderMockRequestDemo() {
    var protocol = "http"
	var hostname = "xxx.rest.mock.com"
	var port = 8081

	if (!serverInfo || !serverInfo['host']) {

	} else {
        hostname = serverInfo['host'];
        port = serverInfo['port'];
	}


	// protocol :// hostname[:port] / path / [;parameters][?query]#fragment
	if (port === 443) {
        protocol = "https";
	}

	var urlArr = [];
	urlArr.push(protocol);
	urlArr.push("://");
	urlArr.push(hostname);

	if (port !== 443 && port !== 80) {
        urlArr.push(":");
        urlArr.push(port);
	}
	urlArr.push("/");
	urlArr.push(groupCode);
    urlArr.push(tmpPath);

	var url = urlArr.join("");
	$(".mapping-url-demon .url").html(url)
}

$(document).on("change", "[name=path]", function(){
    tmpPath = $("[name=path]").val()
    renderMockRequestDemo();
})


$(function () {

    layui.use(['layer', 'element', 'form'], function(){

        window.layer = layui.layer
        window.form = layui.form;
        window.element = layui.element;

        var jsList = ["/js/rest_script.js", "/js/mapping_rules.js", "/js/mapping_jobs.js", "/js/mapping_task.js", "/js/mapping_global_var.js"]

        jsList.forEach(function (url) {
            loadJs(url)
        })

        if (requestID > 0){
			(function queryMapping() {
				ajaxPost("/mock_server/rest/mapping/get.ajax", {requestID: requestID}, function (response) {
					for ( var fieldName in response.data){
						var fieldValue = response.data[fieldName]
						$('[name='+ fieldName +']').val(fieldValue + '')

						if (fieldName == 'isActive'){
							$('[name='+ fieldName +']').next(".layui-form-select").find("input").val(fieldValue ? "有效" : "无效")
						}

					}

					groupID = response.data.groupID
					groupCode = response.data.groupCode
					path = tmpPath = response.data.path

					renderMockRequestDemo()
				})
            })();
        }

        //获取mock服务的配置
        ajaxPost("/mock_server/system/server/get.ajax", {protocol: protocol}, function (response) {
            serverInfo = response.data
            renderMockRequestDemo()
        })

        //获取支持的脚本语言类型
        ajaxPost("/mock_server/enum/language/list.ajax", {}, function (response) {
            scriptLanguageList = response.data.list
        })

        //获取脚本的字段
        ajaxPost("/mock_server/rest/script/db/field.ajax", {}, function (response) {
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

                    queryScripts(groupID, path)
                }else{
                    saveScriptsSuccess = true
                }
            }, 500)

        })


        form.on('select(grpc-service-select-task)', function (data) {
            renderGrpcMethodSelectInTask($(data.elem))
        });

        form.on('select()', function(data){
            $(data.elem).change()
        });

        //保存 mapping
        form.on('submit(mapping)', function(){
            saveMapping()
            return false;
        });

        //注意：折叠面板 依赖 element 模块，否则无法进行功能性操作
        element.on('collapse(collapse)', function(data){
            if(data.show) {

                if ($(data.content).hasClass("mapping-scripts")) {
                    var groupID = $("[name=groupID]").val().trim()
                    var path = $("[name=path]").val().trim()

                    queryScripts(groupID, path)

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
    })

})




///////////////////////////////////


</script>


<#include "../../common/footer.tpl">