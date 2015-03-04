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
    <meta name="viewport"
          content="width=device-width,height=device-height,initial-scale=1.0,minimum-scale=1.0,maximum-scale-1.0,user-scalable-no"/>
    <meta name="format-detection" content="telephone=no" />
    <link rel="stylesheet" type="text/css" href="css/2048.css"/>
    <script type="text/javascript" src="scripts/jquery-2.1.3.min.js"></script>
    <script type="text/javascript" src="scripts/2048/support2048.js"></script>
    <script type="text/javascript" src="scripts/2048/showanimation2048.js"></script>
    <script type="text/javascript" src="scripts/2048/main2048.js"></script>
    <script type="text/javascript">
    	$(function(){
    		jsonArr = "${board}";
    		if(jsonArr!=null&&jsonArr!=""&&jsonArr!="0"){
	    		board = eval(jsonArr);
	    		score = "${score}";
	    		$("#score").html("${score}");
	    		updateBoardView();
    		}
    		//触屏按下事件，替代click();
			document.getElementById('newgamebutton').addEventListener('touchstart',function(e){touch(e,'newGame');}, false);
			document.getElementById('zdcd').addEventListener('touchstart',function(e){touch(e,'zdcd');}, false);
			document.getElementById('zdcd').addEventListener('touchend',function(e){touch(e,'zdcd');}, false);
			document.getElementById('charts').addEventListener('touchend',function(e){touch(e,'charts');}, false);
    	});
    	//触摸后调用的方法
		function touch(event,butId){
			var e = event || window.event;
			var ctype = $("#zdcd").attr("ctype");
			switch(e.type){
	            case "touchstart":
	            	if("newGame"==butId){
	            		deleteBoard();
	            		newgame();
	            	}else if("zdcd"==butId){
	            		$("#zdcd").attr("src","image/3warn.png");
	            	}
	                break;
	            case "touchend":
	            	if("zdcd"==butId){
		            	if(ctype=="1"){
		            		$("#zdcd").attr("src","image/3off.png");
		            		$("#zdcd").attr("ctype","2");
	            		}else{
	            			$("#zdcd").attr("src","image/3on.png");
		            		$("#zdcd").attr("ctype","1");
		            		saveBoard();
	            		}
	            	}else if("charts"==butId){
            			location.href = "myQt_index/chartsListPage?openId=${openId}";
            		}
	                break;
	            case "touchmove":
	                e.preventDefault();
	                break;
	        }
		}
    	//刷新最高分
    	function frushScore(){
    		var top = $("#top").html();
    		var score = $("#score").html();
    		if(parseInt(score)>parseInt(top)){
    			$("#top").html(score);
    			updateTop();
    		}
    	}
    	//更新最高分
    	function updateTop(){
    		$.post("myQt_index/editTop",{"topscore":$("#top").html(),"openId":"${openId}"});
    	}
    	//保存残局
    	function saveBoard(){
    		var ctype = $("#zdcd").attr("ctype");
    		if(ctype=="1"){
	    		var json = JSON.stringify(board);
	    		$.post("myQt_index/saveBoard",{"openId":"${openId}","board":json,"score":score});
    		}
    	}
    	//清零分数和残局
    	function deleteBoard(){
    		$.post("myQt_index/deleteBoard",{"openId":"${openId}"});
    	}
    </script>
  </head>
  
<body>
    <img id="charts" src="image/ph.png" style="position:absolute;right:0"/>
    <header>
        <h1>2048</h1><img id="zdcd" src="image/3on.png" style="width:60px;margin:5px" ctype="1"/>
        <a href="javascript:;" id="newgamebutton">New Game</a>
        <p onclick="saveBoard();">最高分:<span id="top">${topscore}</span><br>
		   得分:<span id="score">0</span>	
		</p>
    </header>

    <div id="grid-container">
        <div class="grid-cell" id="grid-cell-0-0"></div>
        <div class="grid-cell" id="grid-cell-0-1"></div>
        <div class="grid-cell" id="grid-cell-0-2"></div>
        <div class="grid-cell" id="grid-cell-0-3"></div>

        <div class="grid-cell" id="grid-cell-1-0"></div>
        <div class="grid-cell" id="grid-cell-1-1"></div>
        <div class="grid-cell" id="grid-cell-1-2"></div>
        <div class="grid-cell" id="grid-cell-1-3"></div>

        <div class="grid-cell" id="grid-cell-2-0"></div>
        <div class="grid-cell" id="grid-cell-2-1"></div>
        <div class="grid-cell" id="grid-cell-2-2"></div>
        <div class="grid-cell" id="grid-cell-2-3"></div>

        <div class="grid-cell" id="grid-cell-3-0"></div>
        <div class="grid-cell" id="grid-cell-3-1"></div>
        <div class="grid-cell" id="grid-cell-3-2"></div>
        <div class="grid-cell" id="grid-cell-3-3"></div>
    </div>
</body>
</html>
