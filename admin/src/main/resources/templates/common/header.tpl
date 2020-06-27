<!DOCTYPE html>
<html>
<head>
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
  <title>MockServer后台</title>
  <link rel="icon" href="/images/favicon.ico" type="image/x-icon" />
  <link rel="stylesheet" href="/layui/css/layui.css">
  <link rel="stylesheet" href="/css/common.css">

  <script>var layer; </script>
  <script src="/js/jquery-v3.3.1.js"></script>
  <script src="/js/jquery.json.js"></script>
  <script src="/layui/layui.js"></script>
  <script src="/iconfont/iconfont.js"></script>
  <script src="/js/common.js"></script>

  <style type="text/css">
    .icon {
      width: 3em;
      height: 2em;
      vertical-align: -0.15em;
      fill: #009688;
      overflow: hidden;
    }
    .clear {
      clear: both;
    }
    .content-head {
      padding: 15px 0px 0px 5px
    }

    .head-container {
      display: inline-block;
      padding-right: 20px;
    }

    .head-container.right {
      float: right;
    }
    .head-container.left {
      float: left;
    }
    .layui-breadcrumb {
      margin-left: 20px;
    }


  </style>
</head>
<body class="layui-layout-body">
<div class="layui-layout layui-layout-admin">
  <div class="layui-header">
    <div class="layui-logo">MockServer后台</div>
    <!-- 头部区域（可配合layui已有的水平导航） -->
    <ul class="layui-nav layui-layout-left">
      <li class="layui-nav-item"><a href="/mock_server/rest/group/list.html">REST</a></li>
      <li class="layui-nav-item"><a href="/mock_server/grpc/group/list.html">GRPC</a></li>
      <li class="layui-nav-item"><a href="/mock_server/system/config.html">SYSTEM</a></li>
      <li class="layui-nav-item"><a href="/mock_server/tools/grpc_log_decoder.html">TOOLS</a></li>

      <!-- <li class="layui-nav-item">
        <a href="javascript:;">其它系统</a>
        <dl class="layui-nav-child">
          <dd><a href="">邮件管理</a></dd>
          <dd><a href="">消息管理</a></dd>
          <dd><a href="">授权管理</a></dd>
        </dl>
      </li>
       -->
    </ul>
    <!-- 
    <ul class="layui-nav layui-layout-right">
      <li class="layui-nav-item">
        <a href="javascript:;">
          <img src="http://t.cn/RCzsdCq" class="layui-nav-img">
          贤心
        </a>
        <dl class="layui-nav-child">
          <dd><a href="">基本资料</a></dd>
          <dd><a href="">安全设置</a></dd>
        </dl>
      </li>
      <li class="layui-nav-item"><a href="">退了</a></li>
    </ul>
     -->
  </div>
  
  <div class="layui-side layui-bg-black">
    <div class="layui-side-scroll">
      <!-- 左侧导航区域（可配合layui已有的垂直导航） -->
      <ul class="layui-nav layui-nav-tree"  lay-filter="test">


        <#list category as c >
          <li class="layui-nav-item layui-nav-itemed">
            <a class="" href="javascript:;">${c["title"]}</a>
              <#if c['sub'] ?? >
                <#list c["sub"] as sub>
                  <dl class="layui-nav-child">
                    <dd><a href="${sub["url"]}">${sub["title"]}</a></dd>
                  </dl>
                </#list>
              </#if>
          </li>
      </#list>

      <!--

      <li class="layui-nav-item layui-nav-itemed">
        <a class="" href="javascript:;">发布版本</a>
        <dl class="layui-nav-child">
          <dd><a href="/plan.php?act=list">发版计划</a></dd>
        </dl>
      </li>

      <li class="layui-nav-item">
        <a href="javascript:;">解决方案</a>
        <dl class="layui-nav-child">
          <dd><a href="javascript:;">列表一</a></dd>
          <dd><a href="javascript:;">列表二</a></dd>
          <dd><a href="">超链接</a></dd>
        </dl>
      </li>
      <li class="layui-nav-item"><a href="">云市场</a></li>

      <li class="layui-nav-item"><a href="/index.php">发布版本</a></li>
       -->
      </ul>
    </div>
  </div>
  <script>	
//JavaScript代码区域

layui.use('element', function(){
  window.element = layui.element;
});

//扩展内容表单字段变更，更新对应表单状态为editing
$(document).on("change selected", ".layui-colla-content-item .layui-form [name],.layui-colla-content-item .layui-form [sub-name]", function () {
    $(this).parents('.layui-form:first').attr('editing', 'true')
})


  </script>
  
