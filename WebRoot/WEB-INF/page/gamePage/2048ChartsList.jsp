<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>2048-排行榜</title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0, minimum-scale=1.0, maximum-scale=1.0, user-scalable=no" />

	<script type="text/javascript" src="http://res.wx.qq.com/open/js/jweixin-1.0.0.js"></script>
	<script type="text/javascript" src="scripts/jquery-2.1.3.min.js"></script>
	<script type="text/javascript" src="scripts/wxjssdk.js"></script>
	<link type="text/css" rel="stylesheet" media="all" href="css/main/menu.css" />
	<script type="text/javascript">
		$(function(){
			//防止页面滚动
			document.addEventListener('touchmove',function(e){touch(e);}, false);
			catchList();
		});
		//触摸后调用的方法
		function touch(event){
			var e = event || window.event;
			e.preventDefault();
		}
		//获取人员列表
		function catchList(){
			var tbody = "";
			$.post("myQt_index/chartsList",function(data){
				$.each(data, function (n, value) {
					var key_value = value.split(",");
					trs = "<dt style='position:relative;background:URL(image/dtbg.png) no-repeat;height:41px;line-height:41px;margin-left:5px' "
						+ ">&nbsp;" + key_value[0]
					+ "<span style='color:white;position:absolute;right:10px'>"+key_value[1]+"</span></dt>";  
				tbody += trs;
				});
				$("#poislist").append(tbody);
			});
		}
		//返回游戏
		function enter(){
			var openId = $("#openId").val();
			location.href="myQt_index/2048?openId="+openId;
		}
	</script>
	<style type="text/css">
		.d1{
			position:absolute; 
			top:10%;
			left:50%;
			margin:0 0 0 -126px;
			width:252px;
			height:44px;
			text-align:center;
			background-position:center; 
		}
	</style>
  </head>
  <body>
  	<div style="width:100%;height:100%;">
	  	<div class="d1">
	  		<span style="color: white;">排行榜</span>
	  		<div class="list" style="height:340px;width:310px;overflow:auto;background:URL(<%=basePath%>image/dlbg.png) no-repeat;">
				<div style="height:320px;width:310px;overflow:auto;margin-top:10px;text-align:left;">
					<dl id="poislist">
					</dl>
				</div>
			</div>
	  		<br>
	  		<input id="ks" type="button" style="background:URL(<%=basePath%>image/button.png) no-repeat;width:160px;border:0px;height:48px;cursor:pointer;" 
	  			 value="返回游戏" onclick="enter();"/>
		</div>
  	</div>
    <input type="hidden" id="openId" value="${openId}"/>
    <div class="bg">
		<img id="bgimg" src="http://bcs.duapp.com/qt-resources/background/background.jpg" width="100%" height="100%;">
    </div>
  </body>
  
</html>
