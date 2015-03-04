<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>是什么呢</title>
	<meta name="viewport" content="width=device-width, initial-scale=1" />
	<script type="text/javascript" src="scripts/jquery-2.1.3.min.js"></script>
	<script type="text/javascript">
		function readyDo(){}
	</script>
	<style type="text/css">
		.d1{
			position:absolute; 
			top:50%;
			left:50%;
			margin:-22px 0 0 -126px;
			width:252px;
			height:44px;
			text-align:center;
			background-position:center;
		} 
	</style>
  </head>
  <body onclick="unshow();">
	<div class="d1">
  		<div style="background:URL(<%=basePath%>image/inputbg.png) no-repeat;width:252px;height:44px;">
	  		<input id="info" type="text" style="background-color:transparent;width:245px;height:44px;border:0px;" disabled="disabled" value="${info}"/>
  		</div>
	</div>
	
	<%@ include file="/bottom.jsp"%>
  </body>
</html>
