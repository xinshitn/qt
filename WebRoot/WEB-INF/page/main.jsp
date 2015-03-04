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
	wx.config({
		debug: false, // 开启调试模式,调用的所有api的返回值会在客户端alert出来，若要查看传入的参数，可以在pc端打开，参数信息会通过log打出，仅在pc端时才会打印。
		appId: '${result.appId}', // 必填，公众号的唯一标识
		timestamp: '${result.timestamp}', // 必填，生成签名的时间戳
		nonceStr: '${result.nonceStr}', // 必填，生成签名的随机串
		signature: '${result.signature}',// 必填，签名，见附录1
		jsApiList: ['scanQRCode','hideOptionMenu','hideMenuItems','hideAllNonBaseMenuItem'] // 必填，需要使用的JS接口列表，所有JS接口列表见附录2
	});
	function readyDo(){}
	$(function(){
		//防止页面滚动
		document.addEventListener('touchmove',function(e){touch(e,'');}, false);
		//触屏按下事件，替代click();
		document.getElementById('but1').addEventListener('touchstart',function(e){touch(e,'but1');}, false);
		document.getElementById('but1').addEventListener('touchend',function(e){touch(e,'but1');}, false);
		document.getElementById('but2').addEventListener('touchstart',function(e){touch(e,'but2');}, false);
		document.getElementById('but2').addEventListener('touchend',function(e){touch(e,'but2');}, false);
		document.getElementById('but3').addEventListener('touchstart',function(e){touch(e,'but3');}, false);
		document.getElementById('but3').addEventListener('touchend',function(e){touch(e,'but3');}, false);
	});
	//触摸后调用的方法
	function touch(event,butId){
		var e = event || window.event;
		switch(e.type){
            case "touchstart":
            	e.preventDefault();
                $("#"+butId).attr("src","<%=basePath%>image/btOn.png");
                break;
            case "touchend":
                $("#"+butId).attr("src","<%=basePath%>image/btnull.png");
                doButton(butId);
                break;
            case "touchmove":
                e.preventDefault();
                break;
        }
	}
    //微信扫一扫接口
    function barCode(){
    	wx.scanQRCode({
		    needResult: 1, // 默认为0，扫描结果由微信处理，1则直接返回扫描结果，
		    scanType: ["qrCode","barCode"], // 可以指定扫二维码还是一维码，默认二者都有
		    success: function (res) {
		    	var result = res.resultStr; // 当needResult 为 1 时，扫码返回的结果
		    	dealDarCode(result);
			}
		});
    }
    
    //处理二维码扫描结果
    function dealDarCode(result){
    	location.href="myQt_index/qrMsg?qrurl="+result;
    }
    
    function doButton(option){
    	switch(option){
            case "but1":
                location.href='myQt_index/buildQrPage?openId=${openId}';
                break;
            case "but2":
                barCode();
                break;
            case "but3":
                location.href='myQt_index/gps?openId=${openId}';
                break;
        }
    }
    
    //按钮变换处理
    function show(butId){
    	$("#"+butId).attr("src","<%=basePath%>image/btOn.png");
    }
    function unshow(butId){
    	$("#"+butId).attr("src","<%=basePath%>image/btnull.png");
    }
  
	function test(){
		alert('此按键暂未开通功能');
	}
</script>
<style type="text/css">
	.d1{
		background:url("<%=basePath%>image/bacBut.png") no-repeat;
		position:absolute; 
		top:50%;
		left:50%;
		margin:-55px 0 0 -147px;
		width:294px;
		height:110px;
		text-align:center;
		background-position:center;
	} 
</style>

</head>
<body>
	<div class="d1">
		<img id="but1" style="cursor:pointer" onclick="doButton('but1');"
			src="<%=basePath%>image/btnull.png"/><img id="but2" style="cursor:pointer" onclick="barCode();"
				src="<%=basePath%>image/btnull.png"/><img id="but3" style="cursor:pointer" onclick="doButton('but3');"
					src="<%=basePath%>image/btnull.png"/>
	</div>
	
	<%@ include file="/bottom.jsp"%>
</body>
</html>
