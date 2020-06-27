<#include "../common/header.tpl">
<div class="layui-body">
    <!-- 内容主体区域 -->
    <blockquote class="layui-elem-quote">
        根据orderKey判断IDC信息<br/>

        maven依赖在4.4及以上版本<br/>
        &lt;dependency&gt;<br/>
        &lt;groupId&gt;com.hualala.order&lt;/groupId&gt;<br/>
        &lt;artifactId&gt;order-route-key-gen&lt;/artifactId&gt;<br/>
        &lt;version&gt;4.4&lt;/version&gt;<br/>
        &lt;/dependency&gt;<br/>
    </blockquote>
    <form class="layui-form" action="" method="post" id="plan-form" style="padding: 15px;">
        <div class="layui-form-item">
            <label class="layui-form-label">orderKey</label>
            <div class="layui-input-block">
                <input name="orderKey" placeholder="6662865428299124186" class="layui-input"></input>
            </div>
        </div>

        <div class="layui-form-item">
            <div class="layui-input-block">
                <button class="layui-btn" lay-submit lay-filter="formDemo">走你</button>
                <button type="reset" class="layui-btn layui-btn-primary">重置</button>
            </div>
        </div>

        <div class="layui-form-item layui-hide-md layui-hide-lg form-item-result">
            <label class="layui-form-label">IDC信息</label>
            <div class="layui-input-block json-target idc-name">
                <!-- <div name="decode-log" ></div>
				<input name="idc-name"  readonly class="layui-input "></input> -->
            </div>
        </div>

        <div class="layui-form-item layui-hide-md layui-hide-lg form-item-result">
            <label class="layui-form-label">订单分库分表信息</label>
            <div class="layui-input-block json-target shard-info">
                <!-- <div name="decode-log" ></div>
				<input name="idc-name"  readonly class="layui-input "></input> -->
            </div>
        </div>

    </form>
</div>

<script>


    function getZeroFilledSuffix(shard_mod) {
        if (shard_mod < 10) {
            return '00' + shard_mod
        } else {
            return '0' + shard_mod
        }

    }


    layui.use(['form', 'layer'], function () {
        var form = layui.form;
        var layer = layui.layer


        //监听提交
        form.on('submit(formDemo)', function (data) {
            try {
                console.log(data)

                var orderKeyStr = !data.field.orderKey ? $("[name=orderKey]").attr('placeholder') : data.field.orderKey
                var orderKey = $.trim(orderKeyStr)

                var idcFlag = orderKey << 32 >>> 62
                var idcName = ""
                switch (idcFlag) {
                    case 0 :
                        idcName = "北京";
                        break;
                    case 1 :
                        idcName = "阿里";
                        break;
                    default :
                        idcName = "其他";
                }

                // $("[name=idc-name]").val(idcName)
                $(".idc-name").text(idcName)
                $('.form-item-result').removeClass(['layui-hide-lg', 'layui-hide-md'])


                $.post("shard.action", {orderKey: orderKey}, function (resp) {


                    dbModOnline = resp['data']['dbModOnline']
                    tblModOnline = resp['data']['tblModOnline']

                    dbModDohko = resp['data']['dbModDohko']
                    tblModDohko = resp['data']['tblModDohko']


                    shard_db_suffix_online = getZeroFilledSuffix(dbModOnline)
                    shard_tbl_suffix_online = getZeroFilledSuffix(tblModOnline)

                    shard_db_suffix_dohko = getZeroFilledSuffix(dbModDohko)
                    shard_tbl_suffix_dohko = getZeroFilledSuffix(tblModDohko)

                    "db_order_new_"
                    "tbl_order_master_"

                    tblOnline = "db_order_new_" + shard_db_suffix_online + "." + "tbl_order_master_" + shard_tbl_suffix_online
                    tblDohko = "db_order_new_" + shard_db_suffix_dohko + "." + "tbl_order_master_" + shard_tbl_suffix_dohko


                    dataSourceIndexOnline = Math.floor(dbModOnline / 16) + 1
                    dataSourceIndexDohko = dbModDohko + 1

                    $(".shard-info").html("")
                    $(".shard-info").append($("<li>").text("线上：\t" + tblOnline + " ; \t数据源：" + dataSourceIndexOnline))
                    $(".shard-info").append($("<li>").text("Dohko：\t" + tblDohko + " ; \t数据源：" + dataSourceIndexDohko))

                }, 'json')

            } catch (e) {
                console.log(e)
                layer.msg('解析异常！')
            } finally {
                return false;
            }
        });
    });


</script>

<#include "../common/footer.tpl">