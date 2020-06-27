<#include "../common/header.tpl">
<script src="/js/jquery.form.js"></script>
<div class="layui-body">
    <!-- 内容主体区域 -->
	<div class="content-head">
        <span class="layui-breadcrumb">
            <a><cite>全局配置</cite></a>
        </span>
	</div>
	<hr>
	<div class="layui-collapse" lay-filter="collapse">
		<#list configs as config >
			<div class="layui-colla-item" lay-filter="${config.typeName}" >
				<h2 class="layui-colla-title">${config.typeName}</h2>
				<div class="layui-colla-content config-${config.typeName}" config-type="${config.typeID}">
					<div class="layui-colla-content-item">

					</div>

					<button class="layui-btn layui-btn-xs layui-btn-radius system-btn-add">
						<i class="layui-icon">&#xe608;</i> 添加
					</button>
					<button class="layui-btn layui-btn-xs layui-btn-radius layui-btn-warm system-btn-save">
						<i class="layui-icon">&#xe608;</i> 保存
					</button>
				</div>
			</div>
		</#list>
	</div>


</div>
<script src="/js/system.js"></script>
<script>
layui.use('layer', function(){
	window.layer = layui.layer
})

$(function () {


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


})







</script>


<#include "../common/footer.tpl">