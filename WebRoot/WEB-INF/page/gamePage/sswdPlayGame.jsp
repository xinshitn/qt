<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>谁是卧底-游戏中</title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0, minimum-scale=1.0, maximum-scale=1.0, user-scalable=no" />

	<script type="text/javascript" src="http://res.wx.qq.com/open/js/jweixin-1.0.0.js"></script>
	<script type="text/javascript" src="scripts/jquery-2.1.3.min.js"></script>
	<script type="text/javascript" src="scripts/wxjssdk.js"></script>
	<link type="text/css" rel="stylesheet" media="all" href="css/main/menu.css" />
	<script type="text/javascript">
		var openId = "${openId}";
		var cardNum = "";
		var cardName = "等待房主开始...";
		var cardIndex = "";
		$(function(){
			//防止页面滚动
			document.addEventListener('touchmove',function(e){touch(e,'');}, false);
			//触屏按下事件，替代click();
			document.getElementById('cardInfo').addEventListener('touchstart',function(e){touch(e,'cardInfo');}, false);
			if('${roomOwner}'==openId){
				document.getElementById('gh').addEventListener('touchstart',function(e){touch(e,'gh');}, false);
			}
			document.getElementById('hr').addEventListener('touchstart',function(e){touch(e,'hr');}, false);
			document.getElementById('tp').addEventListener('touchstart',function(e){touch(e,'tp');}, false);
			//获取卡片信息
			catchCardInfo();
			if("${roomOwner}"!=openId){
				self.setInterval("checkCard()",1500);
			}
		});
		//触摸后调用的方法
		function touch(event,butId){
			var e = event || window.event;
			switch(e.type){
	            case "touchstart":
	            	e.preventDefault();
	            	//判断是哪个按钮执行事件
	            	if(butId=="cardInfo"){
		            	var ctype = $("#cardInfo").attr("ctype");
		            	if(ctype=="1"){
		            		showIndex();
		            		$("#cardInfo").attr("ctype","2");
		            	}else{
		            		showCard();
		            		$("#cardInfo").attr("ctype","1");
		            	}
	            	}else if(butId=="gh"){
	            		var ctype = $("#gh").attr("ctype");
	            		if(ctype=="1"){
	            			$("#gh").attr("ctype","2");
		            		$("#poislist").html("更换中，请稍后...");
		            		changeCard();
	            		}
	            	}else if(butId=="hr"){//显示玩家列表
	            		location.href="myQt_index/sswdShowUsersListPage?openId="+openId;
	            	}else if(butId=="tp"){
	            		vote();
	            	}
	                break;
	            case "touchend":
	                break;
	            case "touchmove":
	                e.preventDefault();
	                break;
	        }
		}
		//获取卡片信息
		function catchCardInfo(){
			var roomnum = "${roomNum}";
			if(roomnum==null||roomnum=="0"){
				location.href = "myQt_index/sswd?openId="+openId;
			}
			$.post("myQt_index/myCard",{"openId":openId},function(data){
				if(data==null||data==""){
					data = "请等待房主开始";
				}
				cardName = data;
				$("#gh").attr("ctype","1");
				showCard();
			});
			$.post("myQt_index/myCardIndex",{"openId":openId},function(data){
				cardIndex = data;
			});
		}
		//显示卡片词汇
		function showCard(){
			$("#poislist").html(cardName);
			//$("#cardShow").attr("onclick","showIndex()");
		}
		//显示序号
		function showIndex(){
			$("#poislist").html(cardIndex);
			//$("#cardShow").attr("onclick","showCard()");
		}
		//更换卡片
		function changeCard(){
			$.post("myQt_index/playGame",{"roomNum":"${roomNum}"},function(data){
				if(!data=="suc"){
					alert("连接错误，请重试");
					return;
				}
				catchCardInfo();
			});
		}
		//检查卡片是否更换
		function checkCard(){
			$.post("myQt_index/checkCardNum",{"openId":openId},function(data){
				if(data==null||data==""){
					$("#poislist").html("请等待房主开始");return;
				}
				if(cardNum!=data){
					catchCardInfo();
					cardNum = data;
				}
			});
		}
		function vote(){
			alert("功能开发中");return;
			var openId = $("#openId").val();
			var roomNum = "${roomNum}";
			//TODO 接收地址待定
			location.href="myQt_index/sswdUsersList?openId="+openId+"&roomNum="+roomNum;
		}
		//退出房间
		function out(){
			var rn = "";
			var info = "确定退出房间？";
			if("${roomOwner}"==openId){
				rn = "${roomNum}";
				info += "您是房主，将解散房间..";
			}
			if(confirm(info)){
				$.post("myQt_index/outRoom",{"roomNum":rn,"openId":openId},function(data){
					if(!(data=="suc")){
						alert("连接错误，请重试");
						return;
					}
					location.href="myQt_index/sswd?openId="+openId;
				});
			}
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
	  	<div class="d1" style="white-space:nowrap;">
	  		<span style="color: white;">房间号：${roomNum}</span>
	  		<div class="list" id="cardShow" style="height:340px;width:310px;background:URL(<%=basePath%>image/dlbg.png) no-repeat;">
	  			<img alt="" style="position:absolute;top:100%;right:0" onclick="out();" src="<%=basePath%>image/off.png">
				<div id="cardInfo" ctype="1" style="height:320px;width:252px;margin-top:10px;text-align:center;line-height: 320px">
					<span id="poislist">
						<c:choose>
						   <c:when test="${roomOwner==openId}">载入中...</c:when>
						   <c:otherwise>等待房主开始..</c:otherwise>
						</c:choose>
					</span>
				</div>
			</div>
	  		<br>
	  		<c:if test="${roomOwner==openId}">
		  		<input id="gh" ctype="1" type="button" style="background:URL(<%=basePath%>image/button.png) no-repeat;background-size: 100% 100%;width:75px;border:0px;height:45px;cursor:pointer;" 
		  			 value="更    换"/>
	  		</c:if>
	  		<input id="hr" type="button" style="background:URL(<%=basePath%>image/button.png) no-repeat;background-size: 100% 100%;width:75px;border:0px;height:45px;cursor:pointer;" 
	  			 value="玩家列表"/>
	  		<input id="tp" type="button" style="background:URL(<%=basePath%>image/button.png) no-repeat;background-size: 100% 100%;width:75px;border:0px;height:45px;cursor:pointer;" 
	  			 value="投    票"/>
		</div>
  	</div>
    <input type="hidden" id="openId" value="${openId}"/>
    <div class="bg">
		<img id="bgimg" src="http://bcs.duapp.com/qt-resources/background/background.jpg" width="100%" height="100%;">
    </div>
  </body>
  
</html>
