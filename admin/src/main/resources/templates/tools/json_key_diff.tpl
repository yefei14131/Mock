<#include "../common/header.tpl">

<div class="layui-body">
    <!-- 内容主体区域 -->
    <blockquote class="layui-elem-quote">
        根据两个json，对比出其中Key相同和差异的部分，并以json形式展示出来。
    </blockquote>
    <form class="layui-form" action="" method="post" id="plan-form" style="padding: 15px;">
        <div class="layui-form-item">
            <label class="layui-form-label">Json1</label>
            <div class="layui-input-block">
                <textarea name="json1" rows="6" placeholder="{username:'user', age:'18'}"
                          class="layui-textarea"></textarea>
            </div>
        </div>

        <div class="layui-form-item">
            <label class="layui-form-label">Json2</label>
            <div class="layui-input-block">
                <textarea name="json2" rows="6" placeholder="{username:'user', age:'20'}"
                          class="layui-textarea"></textarea>
            </div>
        </div>

        <div class="layui-form-item">
            <div class="layui-input-block">
                <button class="layui-btn" lay-submit lay-filter="formDemo">走你</button>
                <button type="reset" class="layui-btn layui-btn-primary">重置</button>
            </div>
        </div>

        <div class="layui-form-item layui-hide-md layui-hide-lg form-item-diff">
            <label class="layui-form-label">差异</label>
            <div class="layui-input-block json-target">
                <div name="diff"></div>
            </div>
        </div>

        <div class="layui-form-item layui-hide-md layui-hide-lg form-item-same">
            <label class="layui-form-label">相同</label>
            <div class="layui-input-block json-target">
                <div name="same"></div>
            </div>
        </div>
    </form>
</div>

<script>


    function DataHelper() {

        this.data = {
            diff: [],
            same: []
        }

        this.saveDiff = function (key1, key2) {

            this.data['diff'].push([key1, key2])

        }

        this.saveSame = function (key) {

            this.data['same'].push(key)

        }
    }

    function findDiff(json1, json2, dataHelper, keyPrev) {

        for (var key1 in json1) {
            var val1 = json1[key1]

            var key1Match = false
            for (var key2 in json2) {
                if (key2 == key1) {
                    key1Match = true
                    dataHelper.saveSame(keyPrev + key1)

                    var val2 = json2[key2]

                    if (typeof val1 == 'object' || typeof val2 == 'object') {
                        arguments.callee(val1, val2, dataHelper, keyPrev + key1 + ".")
                    }
                }
            }

            if (!key1Match) {
                dataHelper.saveDiff(keyPrev + key1, null);
            }
        }


        for (var key2 in json2) {
            var val2 = json2[key2]

            var key2Match = false
            for (var key1 in json1) {
                if (key1 == key2) {
                    key2Match = true
                    var val1 = json1[key1]

                }
            }

            if (!key2Match) {
                dataHelper.saveDiff(null, keyPrev + key2);
            }
        }


    }


    layui.use('form', function () {
        var form = layui.form;

        //监听提交
        form.on('submit(formDemo)', function (data) {
            try {
                console.log(data)

                var json1 = !data.field.json1 ? $("[name=json1]").attr('placeholder') : data.field.json1
                var json2 = !data.field.json2 ? $("[name=json2]").attr('placeholder') : data.field.json2

                var jsonIsOk = true

                try {
                    eval("(" + json1 + ")")
                } catch (e) {
                    jsonIsOk = false
                    layer.msg('json1格式不正确')
                }
                try {
                    eval("(" + json2 + ")")
                } catch (e) {
                    jsonIsOk = false
                    layer.msg('json2格式不正确')
                }

                if (!jsonIsOk) {
                    return
                }

                var dataHelper = new DataHelper();
                findDiff(eval("(" + json1 + ")"), eval("(" + json2 + ")"), dataHelper, "")

                var same = dataHelper.data.same
                var diff = dataHelper.data.diff

                var diffResult = new JSONFormat(JSON.stringify(diff), 4).toString();
                var sameResult = new JSONFormat(JSON.stringify(same), 4).toString();

                $("[name=diff]").html(diffResult)
                $("[name=same]").html(sameResult)

                $('.form-item-diff,.form-item-same').removeClass(['layui-hide-lg', 'layui-hide-md'])


            } catch (e) {
                console.log(e)
                layer.info('解析异常！')
            } finally {
                return false;
            }
        });
    });


</script>

<#include "../common/footer.tpl">