<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>2048</title>
    <script type="text/javascript" src="scripts/jquery-2.1.3.min.js"></script>
    
	<script type="text/javascript">
		$(function (){
			var openId = "${openId}";
			var name = "${name}";
			if(name==null||name==""){
				location.href="myQt_index/2048Welcome?openId="+openId;
			}else{
				location.href="myQt_index/2048?openId="+openId;
			}
		});
	</script>
	<style type="text/css">
		.d1{
			position:absolute; 
			top:50%;
			left:50%;
			margin:-125px 0 0 -250px;
			width:500px;
			height:250px;
			text-align:center;
			background-position:center;
		} 
	</style>
  </head>
  
  <body>
  	<div class="d1">
  		<h1>载入中...</h1>
	</div>
  </body>
</html>
