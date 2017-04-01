



/***
 * ajax 提交相关业务处理方法
 * @param param 提交参数json对象
 * @param isRefreshPage 是否刷新当前页面
 */
function ajaxSubmitOperate(url , param , isRefreshPage){
	//traditional: true
	$.ajaxSettings.traditional = true;
	$.post(url, param, function(result) {
		var resultObj = eval('('+result+')');//转换json对象
		if(typeof(resultObj) == 'object'){
			if(resultObj.flag == true || resultObj.flag == "true"){
				alert(resultObj.msg);
				if(typeof(afterAjaxSubmitSuccessHandle) == "function"){
					window.afterAjaxSubmitSuccessHandle(result);
				}
				if(isRefreshPage){
					//刷新父窗口页面
					window.location.reload();
				}
				//window.close();
			}else{
				alert("处理失败，详细信息："+resultObj.msg);
			}
		}else{
			alert("返回结果错误，"+result);
		}
	});
}
