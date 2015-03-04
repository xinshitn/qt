<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib uri="/struts-tags" prefix="s" %>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<base href="<%=basePath%>">

<title>清汀驿站</title>
<meta name="viewport" content="width=device-width, initial-scale=1.0, minimum-scale=1.0, maximum-scale=1.0, user-scalable=no" />

<script type="text/javascript" src="http://res.wx.qq.com/open/js/jweixin-1.0.0.js"></script>
<script type="text/javascript" src="scripts/jquery-2.1.3.min.js"></script>
<script type="text/javascript" src="scripts/wxjssdk.js"></script>

<script type="text/javascript">
	$(function(){
		//http://www.xcwljy.cn/images/pixel.gif
	    $("#bgimg").attr("src","http://bcs.duapp.com/qt-resources/background/cureArticle.jpg");
	});
</script>
<style type="text/css">
	.d1{
		position:absolute; 
		width:100%;
		height:100%;
		text-align:center;
		background-position:center;
		z-index:99;
	} 
</style>

</head>
<body>
	<div class="d1">
		${article}
	</div>
	<%@ include file="/bottom.jsp"%>
</body>
</html>
