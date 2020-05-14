<%--  Created by sibingmao  Date: 2020/2/26 15:59 --%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>疫情数据录入</title>
    <link rel="stylesheet" href="./input_background_files/style.css">
    <jsp:include page="bootstrap_common.jsp"></jsp:include>
    <link rel="stylesheet" type="text/css" href="bootstrap/datepicker/bootstrap-datepicker3.css">
</head>
<body>
<div class="col-md-3">
    <a href="${pageContext.request.contextPath}/epidemic.jsp" class="btn btn-primary">主页</a>
    <p style="display: inline">当前用户：<span class="label label-info">${loginUser.userName}</span></p>
    <a href="${pageContext.request.contextPath}/user/logout" class="btn btn-warning">退出登录</a>
    <br><br><br><br>
<div class="input-group date" id="datepicker" data-date-format="yyyy-mm-dd">
    <div class="input-group-addon">数据日期</div>
    <input class="form-control" size="16" type="text" value="" readonly id="dataDate">
    <div class="input-group-addon"><span class="add-on glyphicon glyphicon-calendar"></span></div>
</div>
    <br><br><br><br>
    <button type="button" class="btn btn-success" id="btnSubmit">提交 <span class="glyphicon glyphicon-log-in"></span></button>
</div>

<div class="container">
        <div class="col-md-9">
            <div class="row">
                <table class="table table-striped table-hover table-bordered">
                    <thead>
                    <tr>
                        <th>省份</th>
                        <th>确诊人数</th>
                        <th>疑似人数</th>
                        <th>隔离人数</th>
                        <th>治愈人数</th>
                        <th>死亡人数</th>
                    </tr>
                    </thead>
                    <tbody id="body1">
                    <tr>
                        <td>湖北</td>
                        <td><input type="text" name="affirmed" size="4" maxlength="4" class="form-control"></td>
                        <td><input type="text" name="suspected" size="4" maxlength="4" class="form-control"></td>
                        <td><input type="text" name="isolated" size="4" maxlength="4" class="form-control"></td>
                        <td><input type="text" name="cured" size="4" maxlength="4" class="form-control"></td>
                        <td><input type="text" name="dead" size="4" maxlength="4" class="form-control"></td>
                    </tr>
                    </tbody>
                </table>
            </div>
            <div class="row">
                <div id="msg"></div>
            </div>
        </div>
    </div>
</div>
<script src="${pageContext.request.contextPath}/bootstrap/js/jquery-1.11.2.js"></script>
<script src="${pageContext.request.contextPath}/bootstrap/js/bootstrap.js"></script>
<script type="text/javascript"
        src="${pageContext.request.contextPath}/bootstrap/datepicker/bootstrap-datepicker.js"></script>
<script type="text/javascript"
        src="${pageContext.request.contextPath}/bootstrap/datepicker/bootstrap-datepicker.zh-CN.min.js"></script>
<script type="text/javascript">
    var provinces = null;
    $(function () {
        //设置日期输入框的初始值和取值范围
        var datepicker = $("#datepicker");
        datepicker.datepicker({
            language: 'zh-CN',
            autoclose: true
        });
        var current = new Date();
        datepicker.datepicker("setDate", current);
        var date1 = new Date();
        date1.setDate(current.getDate() - 7);
        datepicker.datepicker("setStartDate", date1);
        datepicker.datepicker("setEndDate", current);
        //给日期选择框设置事件处理函数
        datepicker.datepicker().on("changeDate", loadProvinceList);
        //装载省份列表
        loadProvinceList();
        //给提交按钮绑定事件处理函数
        $("#btnSubmit").click(checkAndSubmitData);
    });

    function checkAndSubmitData() {
        var valid = true;
        var affirmed = $("input[name=affirmed]");
        var suspected = $("input[name=suspected]");
        var isolated = $("input[name=isolated]");
        var cured = $("input[name=cured]");
        var dead = $("input[name=dead]");

        affirmed.each(function (index, element) {
            if (isNaN(Number(element.value))) {
                valid = false;
            }
        });
        if (valid) {
            //提交
            var dataArray = [];
            for (var i = 0; i < provinces.length; i++) {
                var obj = {};
                obj.provinceId = provinces[i].provinceId;
                obj.affirmed = affirmed.get(i).value;
                obj.suspected = suspected.get(i).value;
                obj.isolated = isolated.get(i).value;
                obj.cured = cured.get(i).value;
                obj.dead = dead.get(i).value;
                dataArray.push(obj);
            }
            //
            var date = $("#dataDate").val();
            var data = {};
            data.date = date;
            data.array = dataArray;
            //
            $.ajax({
                url: "${pageContext.request.contextPath}/epidemicData/ajax/input",
                type: "POST",
                contentType: "application/json",
                data: JSON.stringify(data),
                dataType: 'json',
                success: function (resp) {
                   if(resp.code<0){
                       alert(resp.msg);
                   }else{
                       fillProvinceToTable(resp.data);
                   }
                }
            });

        } else {
            alert("请检查你的输入，确保输入有效的数值!");
        }
    }

    function loadProvinceList() {
        //获取当前日期框中的值
        var date = $("#dataDate").val();
        //从服务器获取取还没有录入数据的省份列表
        $.get("${pageContext.request.contextPath}/province/ajax/noDataList", {date: date}, function (resp) {
            if (resp.code < 0) {
                alert(resp.msg);
            } else {
                fillProvinceToTable(resp.data);
            }
        }, "json");
    }

    function fillProvinceToTable(array) {
        //清空消息
        $("#msg").html("");
        //清空表格
        var tbody1 = $("#body1");
        tbody1.empty();
        if (array && array.length > 0) {
            provinces = array;
            //填充到table中
            $.each(array, function (index, province) {
                var tr = $("<tr>");
                var td = $("<td>");
                td.text(province.provinceName);
                tr.append(td);

                td = $("<td>");
                td.html('<input type="text" name="affirmed" size="4" maxlength="4" class="form-control" value="0">');
                tr.append(td);

                td = $("<td>");
                td.html('<input type="text" name="suspected" size="4" maxlength="4" class="form-control" value="0">');
                tr.append(td);

                td = $("<td>");
                td.html('<input type="text" name="isolated" size="4" maxlength="4" class="form-control" value="0">');
                tr.append(td);

                td = $("<td>");
                td.html('<input type="text" name="cured" size="4" maxlength="4" class="form-control" value="0">');
                tr.append(td);

                td = $("<td>");
                td.html('<input type="text" name="dead" size="4" maxlength="4" class="form-control" value="0">');
                tr.append(td);

                tbody1.append(tr);
            });
        } else {
            $("#msg").html("本日所有省份都已经录入了数据!");
        }
    }
</script>
</body>
</html>
