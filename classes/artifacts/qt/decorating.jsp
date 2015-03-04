<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>建设中</title>
    <meta name="viewport" content="width=device-width, initial-scale=1"/>
    <script type="text/javascript" src="http://res.wx.qq.com/open/js/jweixin-1.0.0.js"></script>
    <script type="text/javascript">
		wx.config({
			debug: false, // 开启调试模式,调用的所有api的返回值会在客户端alert出来，若要查看传入的参数，可以在pc端打开，参数信息会通过log打出，仅在pc端时才会打印。
			appId: '${result.appId}', // 必填，公众号的唯一标识
			timestamp: '${result.timestamp}', // 必填，生成签名的时间戳
			nonceStr: '${result.nonceStr}', // 必填，生成签名的随机串
			signature: '${result.signature}',// 必填，签名，见附录1
			jsApiList: ['scanQRCode','hideOptionMenu','hideMenuItems','hideAllNonBaseMenuItem'] // 必填，需要使用的JS接口列表，所有JS接口列表见附录2
		});
		wx.ready(function () {
			wx.hideAllNonBaseMenuItem();
			wx.hideOptionMenu();
		});
		function readyDo(){}
	</script>
  </head>
  <body>
    <div style="TEXT-ALIGN:center;"><img src="http://bcs.duapp.com/qt-resources/decorating.jpg" style="width:100%;max-width:700px"/></div>
  </body>
</html>
