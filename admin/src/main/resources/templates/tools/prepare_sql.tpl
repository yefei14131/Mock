<#include "../common/header.tpl">

<div class="layui-body">
    <!-- 内容主体区域 -->
    <blockquote class="layui-elem-quote">
        根据prepareSql语句和参数，拼接出完整的sql语句，并计算出field与value的对应关系。<br/>
        适用于从日志中查询的sql和参数对应，？ 与 参数是按顺序对应的，没有校验参数个数是否正确，请自行确保<br/>
        批量insert会忽略掉后面的值
    </blockquote>
    <form class="layui-form" action="" method="post" id="plan-form" style="padding: 15px;">
        <div class="layui-form-item">
            <label class="layui-form-label">PrepareSql</label>
            <div class="layui-input-block">
                <textarea name="prepareSql" rows="8"
                          placeholder="update db_order_new_001.tbl_order_master_000 set parentOrderKey = ?, shopOrderKey = ?, channelKey = ?, channelOrderKey = ?, quoteID = ?, orderType = ?, orderSubType = ?, orderStatus = ?, orderState = ?, orderRemark = ?, groupID = ?, groupName = ?, brandID = ?, shopID = ?, shopName = ?, openID = ?, userID = ?, userName = ?, userMobile = ?, originTotalAmount = ?, promotionAmount = ?, foodAmount = ?, originTotalPoint = ?, discountTotalAmount = ?, appliedPromoteIDs = ?, promotionType = ?, dueTotalAmount = ?, dueTotalPoint = ?, paidTotalAmount = ?, paidTotalPoint = ?, refundTotalAmount = ?, refundTotalPoint = ?, invoiceTitle = ?, orderTime = ?, cardTypeID = ?, cardID = ?, cardNo = ?, serviceAmount = ?, cancelRemark = ?, clientType = ?, clientIP = ?, operator = ?, takeoutAddress = ? , takeoutConfirmRemark = ? , shopOrderFlowNo = ? , isAlreadyPaid = ? , takeoutDescribe = ? , shopImgUrl = ? , appID = ? , reviewed = ? , shopOpenID = ? , isApplyRefund = ? , checkoutType = ?, stdChannelKey = ?, action = ?, offlinePaidAmount = ?, tableName = ?, actionTime = now() where orderKey = ?"
                          class="layui-textarea"></textarea>
            </div>
        </div>

        <div class="layui-form-item">
            <label class="layui-form-label">Params</label>
            <div class="layui-input-block">
                <textarea name="params" rows="8"
                          placeholder="0(Long), 2018060519250713734950520013(String), HLL(String), (String), (String), 10(Integer), 41(Integer), 15(Integer), 15(Integer), (String), 1155(Long), (String), 0(Long), 76057177(Long), 性能测试店(String), oACwGs-fsOsUPUx6AR2j-GQPLQ6o(String), 1534552000(Long), 赵子云(String), 18310252061(String), 0.00(BigDecimal), 0.00(BigDecimal), 0.00(BigDecimal), 0.00(BigDecimal), 0.00(BigDecimal), 0(String), 0(String), 0.00(BigDecimal), 0.00(BigDecimal), 0.00(BigDecimal), 0.00(BigDecimal), 0.00(BigDecimal), 0.00(BigDecimal), (String), 201806051925(Long), (String), (String), (String), 0.00(BigDecimal), (String), (String), (String), (String), (String), (String), 0013(String), 0(Integer), (String), (String), (String), 0(Integer), (String), 0(Integer), 2(Integer), (String), 1(Integer), 0.00(BigDecimal), 24(String), 6563560049560525401(Long)"
                          class="layui-textarea"></textarea>
            </div>
        </div>

        <div class="layui-form-item">
            <div class="layui-input-block">
                <button class="layui-btn" lay-submit lay-filter="formDemo">走你</button>
                <button type="reset" class="layui-btn layui-btn-primary">重置</button>
            </div>
        </div>

        <div class="layui-form-item layui-hide-md layui-hide-lg form-sql">
            <label class="layui-form-label">sql</label>
            <div class="layui-input-block">
                <textarea name="sql" rows="8" placeholder="" class="layui-textarea"></textarea>
            </div>
        </div>

        <div class="layui-form-item layui-hide-md layui-hide-lg form-field-value">
            <label class="layui-form-label">Field-Value</label>
            <div class="layui-input-block json-target">
                <div name="Field-Value"></div>
            </div>
        </div>
    </form>
</div>

<script>
    //Demo
    layui.use('form', function () {
        var form = layui.form;

        //监听提交
        form.on('submit(formDemo)', function (data) {
            try {
                console.log(data)
                var paramsStr = !data.field.params ? $("[name=params]").attr('placeholder') : data.field.params
                var prepareSql = !data.field.prepareSql ? $("[name=prepareSql]").attr('placeholder') : data.field.prepareSql

                var sql = prepareSql
                var paramsArr = paramsStr.split(',')
                var params = []

                // 合并成sql
                paramsArr.map(function (item) {
                    var type = ""
                    var val = item.replace(/\((\w+?)\)/, function ($1, $2) {
                        type = $2
                        return ""
                    }).trim()

                    var replaceStr = ""
                    switch (type) {
                        case "Long":
                        case "Integer":
                        case "Double":
                        case "Float":
                            replaceStr = val
                            break;

                        default:
                            replaceStr = "'" + val + "'"
                    }
                    params.push(val)
                    sql = sql.replace(/\?/, replaceStr)

                })
                $("[name=sql]").val(sql)

                $(".form-sql").removeClass(['layui-hide-md', 'layui-hide-lg'])
                $(".form-field-value").removeClass(['layui-hide-md', 'layui-hide-lg'])


                // Fields-Values

                var fields = []
                var vals = []
                if (prepareSql.trim().indexOf('insert into') === 0) {
                    //insert 语句
                    var fieldMatches = prepareSql.match(/\([\w\.,\s]+?\)/m)
                    fieldsTmp = fieldMatches[0].replace(/\(|\)/g, '').split(',').map(function (item) {
                        return item.trim()
                    })

                    var valueMatches = prepareSql.replace(/.*?values?\s*?(\([\w\.,\?\(\)\s]+\))/gm, "$1")
                    values = valueMatches.replace(/\(|\)/g, '').split(',').map(function (item) {
                        return item.trim()
                    })

                    fields = []
                    values.forEach(function (item, i) {
                        if (item == "?") {
                            fields.push(fieldsTmp[i])
                        }
                    })


                } else if (prepareSql.trim().indexOf('update') === 0) {
                    //update语句
                    var fieldMatches = prepareSql.match(/([\w\.]+)\s?=\s?\?/g)
                    fields = fieldMatches.map(function (item) {
                        if (item.match(/\?$/)) {
                            vals.push('?')
                            return item.split('=')[0].trim()
                        }
                    })


                }

                var fieldMap = {}
                fields.forEach(function (field, i) {
                    fieldMap[field] = params[i]
                })

                $sql = "insert into tbl (" + fields.join(',') + ") values (" + vals.join(',') + ")"
                console.log($sql)


                var fieldMapResult = new JSONFormat(JSON.stringify(fieldMap), 4).toString();

                $('[name=Field-Value]').html(fieldMapResult)

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