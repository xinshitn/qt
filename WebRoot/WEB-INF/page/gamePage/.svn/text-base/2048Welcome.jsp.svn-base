<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>欢迎进入2048</title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0, minimum-scale=1.0, maximum-scale=1.0, user-scalable=no" />

	<script type="text/javascript" src="http://res.wx.qq.com/open/js/jweixin-1.0.0.js"></script>
	<script type="text/javascript" src="scripts/jquery-2.1.3.min.js"></script>
	<script type="text/javascript" src="scripts/wxjssdk.js"></script>
	<link type="text/css" rel="stylesheet" media="all" href="css/main/menu.css" />
	<script type="text/javascript">
		$(function(){
			//防止页面滚动
			document.addEventListener('touchmove',function(e){touch(e);}, false);
		});
		//触摸后调用的方法
		function touch(event){
			var e = event || window.event;
			e.preventDefault();
		}
		function enter(){
			var name = $("#name").val();
			var openId = $("#openId").val();
			$.post("myQt_index/addSswd",{"name":name,"openId":openId},function(data){
				if(data=="noName"){alert("昵称不能为空");return;}
				if(!(data=="suc")){
					alert("连接错误，请重试");
					return;
				}
				location.href="myQt_index/2048?openId="+openId;
			});
		}
	</script>
	<style type="text/css">
		.d1{
			position:absolute; 
			top:50%;
			left:50%;
			margin:-48px 0 0 -129px;
			width:258px;
			height:100px;
			text-align:center;
			background-position:center;
		} 
	</style>
  </head>
  <body>
  	<div style="width:100%;height:100%;">
	  	<div class="d1">
	  		<div style="background:URL(<%=basePath%>image/input.png) no-repeat;width:258px;height:48px;">
		  		<input id="name" type="text" style="background-color:transparent;width:245px;height:48px;border:0px;" placeholder="第一次游戏，请设置昵称"/>
	  		</div>
	  		<br>
	  		<input id="jr" type="button" style="background:URL(<%=basePath%>image/button.png) no-repeat;width:160px;border:0px;height:48px;cursor:pointer;" 
	  			 value="进入游戏" onclick="enter();"/><br>
		</div>
  	</div>
    <input type="hidden" id="openId" value="${openId}"/>
    <div class="bg">
		<img id="bgimg" src="http://bcs.duapp.com/qt-resources/background/background.jpg" width="100%" height="100%;">
    </div>
  </body>
  
</html>
