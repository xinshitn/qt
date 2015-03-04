<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>

<title>清汀驿站</title>
<meta name="viewport" content="width=device-width, initial-scale=1" />

<link type="text/css" rel="stylesheet" media="all" href="css/main/menu.css" />
</head>

<body>
	<div class="bg">
		<img id="bgimg" src="http://bcs.duapp.com/qt-resources/background/background.jpg" width="100%" height="100%;">
    </div>
	<div data-role="widget" data-widget="nav4" class="nav4">
      
        <div id="nav4_ul" class="nav_4">
          <ul class="box">
				<li>
					<a href="javascript:;" class=""><span>一场梦</span></a>
					<dl>
						<dd>
							<a href="myQt_index/cureArticle"><span>治愈梦</span></a>
						</dd>
						<dd>
							<a href="myQt_index/decorating"><span>清新梦</span></a>
						</dd>
					</dl>
				</li>
                <li>
					<a href="javascript:;" class=""><span>游乐园</span></a>
					<dl>
						<dd>
							<a href="myQt_index/sswd?openId=${openId}"><span>谁是卧底</span></a>
						</dd>
						<dd>
							<a href="myQt_index/2048Judge?openId=${openId}"><span>2048</span></a>
                        </dd>
                        <dd>
                            <a href="myQt_index/decorating"><span>外国的天</span></a>
                        </dd>
					</dl>
				</li>
                <li>
                	<a href="javascript:;" class=""><span>别找我</span></a>
					<dl>
						<dd>
							<a href="myQt_index/decorating"><span>电话</span></a>
                         </dd>
                         <dd>
                         	<a href="myQt_index/decorating"><span>地址</span></a>
                         </dd>
                         <dd>
                         	<a href="myQt_index/decorating"><span>扣扣</span></a>
                         </dd>
					</dl>
				</li>
			</ul>
        </div>
      
      <div id="nav4_masklayer" class="masklayer_div on"> </div>
      <script src="scripts/nav4.js"></script>
      <script type="text/javascript">
        nav4.bindClick(document.getElementById("nav4_ul").querySelectorAll("li>a"), document.getElementById("nav4_masklayer"));
      </script>
    </div>
</body>
</html>
