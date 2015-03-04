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

<title>遇见你</title>
<meta name="viewport" content="width=device-width, initial-scale=1.0, minimum-scale=1.0, maximum-scale=1.0, user-scalable=no" />
<script charset="utf-8" src="http://map.qq.com/api/js?v=2.exp&libraries=geometry"></script>

<script type="text/javascript" src="http://res.wx.qq.com/open/js/jweixin-1.0.0.js"></script>
<script type="text/javascript" src="scripts/jquery-2.1.3.min.js"></script>
<script type="text/javascript" src="scripts/wxjssdk.js"></script>

<script type="text/javascript">
	var myLatLng = [];
	wx.config({
		debug: false, // 开启调试模式,调用的所有api的返回值会在客户端alert出来，若要查看传入的参数，可以在pc端打开，参数信息会通过log打出，仅在pc端时才会打印。
		appId: '${result.appId}', // 必填，公众号的唯一标识
		timestamp: '${result.timestamp}', // 必填，生成签名的时间戳
		nonceStr: '${result.nonceStr}', // 必填，生成签名的随机串
		signature: '${result.signature}',// 必填，签名，见附录1
		jsApiList: ['scanQRCode','hideOptionMenu','hideMenuItems','hideAllNonBaseMenuItem','getLocation','openLocation'] // 必填，需要使用的JS接口列表，所有JS接口列表见附录2
	});
	//加载完微信接口后调用的方法
	function readyDo(){
		wx.getLocation({
		    success: function (res) {
		        var latitude = res.latitude; // 纬度，浮点数，范围为90 ~ -90
		        var longitude = res.longitude; // 经度，浮点数，范围为180 ~ -180。
		        var accuracy = res.accuracy; // 位置精度
		        searchLoc(latitude,longitude,accuracy);
		        myLatLng = new qq.maps.LatLng(latitude, longitude);
		    }
		});
	}
	function searchLoc(latitude,longitude,accuracy){
		$.post("myQt_index/gpsLoc",{"latitude":latitude,"longitude":longitude},function(data){
			$("#gps").val(" "+data.address);
			var tbody = "";
			var obj = eval(data.pois);
			$.each(obj, function (n, value) {
				var trs = "";
				var type = value.title;//百度为：value.name， 腾讯为：value.title
				var category = value.category;//百度为：value.poiType, 腾讯为：value.category
				var name = category + "  " + type;
				if(name.length>14){
					name = name.substring(0,15)+"...";
				}
				//初始化计算距离用的目的地参数
				var destination = new qq.maps.LatLng(value.location.lat, value.location.lng);
				//计算距离
				var distance = computeDistance(myLatLng, destination).split(".")[0]+"米";
				//腾讯依次为 value.location.lat, value.location.lng, value.title
				//百度依次为 value.point.y, value.point.x, value.name
				//（name不用改，为上方处理后字段）
				trs = "<dt style='position:relative;background:URL(image/dtbg.png) no-repeat;height:41px;line-height:41px;margin-left:5px' "
					+"onclick='seeInMap("+value.location.lat+","+value.location.lng+",\""+value.title+"\")'>&nbsp;" + name 
					+ "<span style='color:white;position:absolute;right:10px'>"+distance+"</span></dt>";  
				tbody += trs;  
			});
			$("#poislist").append(tbody);
		});
	}
	//调用微信内置地图显示坐标点
	function seeInMap(latitude,longitude,name){
		//微信内置地图接口
		wx.openLocation({
		    latitude: latitude, // 纬度，浮点数，范围为90 ~ -90
		    longitude: longitude, // 经度，浮点数，范围为180 ~ -180。
		    name: name, // 位置名
		    address: '', // 地址详情说明
		    scale: 26, // 地图缩放级别,整形值,范围从1~28。默认为最大
		    infoUrl: '' // 在查看位置界面底部显示的超链接,可点击跳转
		});
	}
	//调用qqMap接口计算两点间的距离
	function computeDistance(a, b){
		return qq.maps.geometry.spherical.computeDistanceBetween(a, b)+"";
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
	<div class="d1">
  		<div style="background:URL(<%=basePath%>image/inputbg.png) no-repeat;width:252px;height:44px;">
	  		<input id="gps" type="text" style="background-color:transparent;width:245px;height:44px;border:0px;" readonly="readonly" value=" 定位中..."/>
	  		<!-- <input type="button" id="seeInMap" value="查 看 附 近" onclick="searchLoc();"> -->
	  		<br><br>
	  		<span style="color:white;" id="test">附 近 地 点</span>
			<div class="list" style="height:340px;width:310px;overflow:auto;background:URL(<%=basePath%>image/dlbg.png) no-repeat;">
				<div style="height:320px;width:310px;overflow:auto;margin-top:10px;text-align:left;">
					<dl id="poislist">
					</dl>
				</div>
			</div>
  		</div>
	</div>
	<%@ include file="/bottom.jsp"%>
</body>
</html>
