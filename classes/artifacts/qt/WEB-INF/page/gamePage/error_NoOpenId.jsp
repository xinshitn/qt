<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>ERROR</title>
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
  	<div class="d1">
  		<div class="list" id="cardShow" style="height:340px;width:310px;background:URL(<%=basePath%>image/dlbg.png) no-repeat;" onclick="showIndex();">
  			<img alt="" style="position:absolute;top:100%;right:0" onclick="out();" src="<%=basePath%>image/off.png">
			<div style="height:320px;width:252px;margin-top:10px;text-align:center;line-height: 320px">
				<span id="poislist">请从微信端登陆</span>
			</div>
		</div>
  		<br>
	</div>
  </body>
</html>
