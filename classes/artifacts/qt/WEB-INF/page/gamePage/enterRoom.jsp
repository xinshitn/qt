<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>选择房间</title>
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
			var roomNum = $("#roomNum").val();
			var password = $("#password").val();
			var openId = $("#openId").val();
			$.post("myQt_index/enterRoom",{"password":password,"openId":openId,"roomNum":roomNum},function(data){
				if(!(data=="suc")){
					alert("无此房间号或密码错误");
					return;
				}
				location.href="myQt_index/playGamePage?openId="+openId;
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
  	<div style="width:100%;height:100%;" onclick="unshow();">
	  	<div class="d1">
	  		<div style="background:URL(<%=basePath%>image/input.png) no-repeat;width:258px;height:48px;">
		  		<input id="roomNum" type="text" style="background-color:transparent;width:245px;height:48px;border:0px;" placeholder="请输入房间号码"/>
	  		</div>
	  		<div style="background:URL(<%=basePath%>image/input.png) no-repeat;width:258px;height:48px;">
		  		<input id="password" type="text" style="background-color:transparent;width:245px;height:48px;border:0px;" placeholder="请输入房间密码"/>
	  		</div>
	  		<br>
	  		<input id="jr" type="button" style="background:URL(<%=basePath%>image/button.png) no-repeat;width:160px;border:0px;height:48px;cursor:pointer;" 
	  			 value="进  入" onclick="enter();"/>
		</div>
  	</div>
    <input type="hidden" id="openId" value="${openId}"/>
    <div class="bg">
		<img id="bgimg" src="http://bcs.duapp.com/qt-resources/background/background.jpg" width="100%" height="100%;">
    </div>
  </body>
  
</html>
