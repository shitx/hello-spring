<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();	
%>
<!DOCTYPE html>
<html lang="zh-cn">
<head>
	<title>秒杀详情页</title>
	<!-- 静态包含 -->
	<%@include file="common/head.jsp"%>
</head>
<body>
	<div class="container">
		<div class="panel panel-default text-center">
			<div class="panel-heading">
				<h1> ${seckill.name}</h1>
			</div>
			<div class="panel-body">
				<h2 class="text-danger">
					<!-- 显示时间图标 -->
					<span class="glyphicon glyphicon-time"></span>
					<!-- 展示倒计时 -->
					<span class="glyphicon" id="secKill-box"></span>
				</h2>
			</div>
		</div>	
	</div>
	<!-- 登陆弹出层，输入电话 -->
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
							<input type="text" name="killPhone" id="killPhoneKey"
								placeholder="填手机号" class="form-control"/>
						</div>
					</div>
				</div>
				<div class="modal-footer">
				<!-- 验证信息 -->
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

<!-- 交互逻辑 -->
<script src="<%=path%>/resource/script/secKill.js" type="text/javascript"></script>
<script type="text/javascript">
	$(function(){
		//使用EL表达式传入参数
		secKill.detail.init({
			path: "/hello-spring",
			secKillId : ${seckill.secKillId},
			startTime : ${seckill.startTime.time},
			endTime : ${seckill.endTime.time}
		});
	});
</script>
</html>