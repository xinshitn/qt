
wx.ready(function () {
//	alert("启动jsapi!");
	wx.hideAllNonBaseMenuItem();
//	wx.hideOptionMenu();
	readyDo();
});
wx.error(function(res){
	alert("验证失败："+JSON.stringify(res));
});
