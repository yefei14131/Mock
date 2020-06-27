
<#include "../common/header.tpl">

<div class="layui-body">
    <!-- 内容主体区域 -->
     <blockquote class="layui-elem-quote">
         GRPC日志里中文被转码的情况，使用此工具解码<br/>
     </blockquote>
	<form class="layui-form" action="" method="post" id="plan-form"  style="padding: 15px;">
		<div class="layui-form-item">
			<label class="layui-form-label">日志</label>
			<div class="layui-input-block">
			  <textarea name="orgLog"  rows="12"  placeholder="\346\224\266\351\244\220\344\272\272\351\232\220\347\247\201\345\217\267 13164291192_3195\357\274\214\346\211\213\346\234\272\345\217\267 189****9050" class="layui-textarea"></textarea>
			</div>
		</div>

		<div class="layui-form-item">
			<div class="layui-input-block">
			  <button class="layui-btn" lay-submit lay-filter="formDemo">走你</button>
			  <button type="reset" class="layui-btn layui-btn-primary">重置</button>
			</div>
		</div>

		<div class="layui-form-item layui-hide-md layui-hide-lg form-item-result">
			<label class="layui-form-label">解码后的</label>
			<div class="layui-input-block json-target">
                <!-- <div name="decode-log" ></div> -->
				<textarea name="decode-log"  rows="12"  placeholder="" class="layui-textarea "></textarea>
			</div>
		</div>

	</form>
</div>

<script>



layui.use(['form', 'layer'], function(){
	var form = layui.form;
	var layer = layui.layer


    // 八进制转十进制
    function octal2Decimal(octalStr){
        var decimal = 0;

        for (var i=0, len=octalStr.length; i<len; i++){
            var octalByte = len - 1 - i;
            decimal += Math.pow(8,octalByte) * parseInt(octalStr[i])
        }

        return decimal;
    }

    function decodeUtf8(bytes) {
        var encoded = "";
        for (var i = 0; i < bytes.length; i++) {
            encoded += '%' + bytes[i].toString(16);
        }
        return decodeURIComponent(encoded);
    }


    function grpcLogDecode(log){

        return log.replace(/(\\\d{3}){3}/g, function(octalStr){
            try {
                var octalArr = octalStr.split("\\")	//八进制数组

                decimalArr = []
                octalArr.forEach(function(item, i){
                    if (item.length > 0) {
                        var decimal = octal2Decimal(item)	//八进制转十进制
                        decimalArr.push(decimal)
                    };
                })
                return decodeUtf8(decimalArr)
            }catch (e){
                console.log(e)
                layer.msg('解码异常：'+ octalStr)
                console.log('解码异常：'+ octalStr)
                return octalStr;
            }
        })
    }


    //监听提交
	form.on('submit(formDemo)', function(data){
	    try {
	        console.log(data)

            var orgLog = !data.field.orgLog ? $("[name=orgLog]").attr('placeholder') : data.field.orgLog

            var result = grpcLogDecode(orgLog)
            // $("[name=decode-log]").html(result)
            $("[name=decode-log]").val(result)

            $('.form-item-result').removeClass(['layui-hide-lg','layui-hide-md'])


        }catch (e){
	        console.log(e)
			layer.msg('解析异常！')
        }finally {
            return false;
        }
	});
});



</script>
<#include "../common/footer.tpl">
