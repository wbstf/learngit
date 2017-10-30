
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%--引入jstl--%>
<%@include file="common/tag.jsp"%>
<!DOCTYPE html>
<html>
<head>
    <title>秒杀详情页</title>
    <%--静态包含页，直接包含进来--%>
    <%@include file="common/head.jsp"%>
</head>
<body>
<%--页面显示部分--%>
<div class="container">
    <div class="panel panel-default text-center">
        <div class="panel-heading">
            <h1>${seckill.name}</h1>
        </div>

        <div class="panel-body">
         <h2 class="text-danger">
            <%--显示time图标--%>
            <span class="glyphicon glyphicon-time"></span>
            <%--展示倒计时--%>
            <span class="glyphicon" id="seckill-box"></span>
         </h2>
        </div>
    </div>
</div>
<%--登录弹出层，输入电话--%>
<div id="killPhoneModal" class="modal fade">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <h3 class="modal-title text-center">
                    <span class="glyphicon glyphicon-phone"></span>
                </h3>
            </div>
            <div class="modal-body">
                <div class="row">
                    <div class="col-xs-8 col-xs-offset-2">
                        <input type="text" class="form-control" name="killPhone" id="killPhoneKey" placeholder="填写手机号嘛" >
                    </div>
                </div>
            </div>
            <div class="modal-footer">
                <%--验证信息--%>
                <span id="killPhoneMessage" class="glyphicon"></span>
                <button type="button" id="killPhoneBtn" class="btn btn-success">
                    <span class="glyphicon glyphicon-phone"></span>
                    Submit
                </button>
            </div>
        </div>
    </div>
</div>
</body>

<!-- jQuery文件。务必在bootstrap.min.js 之前引入 -->
<script src="https://cdn.bootcss.com/jquery/2.1.1/jquery.min.js"></script>

<!-- 最新的 Bootstrap 核心 JavaScript 文件 -->
<script src="https://cdn.bootcss.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
<%--使用cdn获取公共的js  http://www.bootcdn.cn/--%>
<%--jquery-cookie   jquery.countdown倒计时插件 --%>
<script src="https://cdn.bootcss.com/jquery-cookie/1.4.1/jquery.cookie.js"></script>
<script src="https://cdn.bootcss.com/jquery.countdown/2.2.0/jquery.countdown.min.js"></script>

<%--开始交互逻辑  不能省略结束标签的标签体 --%>
<script src="/resources/scripts/seckill.js" type="text/javascript"></script>
<script type="text/javascript">
    $(function () {
//        使用EL表达式传入参数
        seckill.detail.init({
            seckillId :${seckill.seckillId},
            startTime :${seckill.startTime.time},//毫秒
            endTime:${seckill.endTime.time},
        });
    });
</script>
</html>