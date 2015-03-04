<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>生成二维码</title>
	<meta name="viewport" content="width=device-width, initial-scale=1.0, minimum-scale=1.0, maximum-scale=1.0, user-scalable=no" />
	<script type="text/javascript" src="http://res.wx.qq.com/open/js/jweixin-1.0.0.js"></script>
	<script type="text/javascript" src="scripts/jquery-2.1.3.min.js"></script>
	<script type="text/javascript" src="scripts/wxjssdk.js"></script>
	<script type="text/javascript">
		function readyDo(){}
		var url = "";
		function buildQR(option){
			var info = $("#info").val();
			$("#info").attr("disabled","true");
			$("#tj").attr("disabled","true");
			$("#sc").attr("style","display:block");
			$.post("myQt_index/buildQr",{"info":info,"option":option},function(data){
				//location.href= data ;
				url = data;
				$("#d2").attr("style","display:block");
				$("#img").attr("src",data);
				$("#info").removeAttr("disabled");
				$("#tj").removeAttr("disabled");
				$("#sc").attr("style","display:none");
				ResizeImages();
				$("#tj").attr("onclick","buildQR('');");
				$("#tj").val("生成微信直刷码");
				if(option==""&&data.indexOf("qr.liantu.com")>0){
					alert("失败了 T.T 还是给你内刷码吧..");
				}
			});
		}
		function unshow(){
			$("#d2").attr("style","display:none");
		}
		function show(){
			location.href= url;
		}
		
		//缩放图片到合适大小
		function ResizeImages()
		{
		   var myimg,oldwidth,oldheight;
		   var maxwidth=300;
		   var maxheight=300;
		   var imgs = document.getElementById('d2').getElementsByTagName('img');
		
		   for(var i=0;i<imgs.length;i++){
		     myimg = imgs[i];
		
		     if(myimg.width > myimg.height)
		     {
		         if(myimg.width > maxwidth)
		         {
		            oldwidth = myimg.width;
		            myimg.height = myimg.height * (maxwidth/oldwidth);
		            myimg.width = maxwidth;
		         }
		     }else{
		         if(myimg.height > maxheight)
		         {
		            oldheight = myimg.height;
		            myimg.width = myimg.width * (maxheight/oldheight);
		            myimg.height = maxheight;
		         }
		     }
		   }
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
		.d2{
			position:absolute; 
			top:50%;
			left:50%;
			margin:-150px 0 0 -150px;
			width:300px;
			height:300px;
			text-align:center;
			background-position:center;
			z-index:999;
		}
	</style>
  </head>
  <body>
  	<div style="width:100%;height:100%;" onclick="unshow();">
		<div id="d2" class="d2" style="display:none;" onclick="show();">
			<!-- <input type="image" id="img"/> -->
			<img height="300" alt="" width="300" id="img" />
		</div>
	  	<div class="d1">
	  		<span id="sc" style="display:none;"><span style="font-size:15px;color:#A8A8A8;">正在生成中...</span></span>
	  		<div style="background:URL(<%=basePath%>image/input.png) no-repeat;width:258px;height:48px;">
		  		<input id="info" type="text" style="background-color:transparent;width:245px;height:48px;border:0px;" placeholder="请输入内容"/>
	  		</div>
	  		<br>
	  		<input id="tj" type="button" style="background:URL(<%=basePath%>image/button.png) no-repeat;width:160px;border:0px;height:48px;cursor:pointer;" 
	  			 value="提  交" onclick="buildQR('myqr');"/>
		</div>
  	</div>
	<%@ include file="/bottom.jsp"%>
  </body>
</html>
