


/***
 * 查询方法
 */
function queryList(){
	
	document.getElementById("listForm").submit();
}

/***
 * 重置方法
 */
function resetList(){
	document.getElementById("jcDate2").value = null;
	document.getElementById("matchName").value = null;
	document.getElementById("teamName").value = null;
	document.getElementById("listForm").submit();
}


/***
 * 竞猜结果同步方法
 */
function syncJCResultData(){
	var url = getWebRootPath()+"/jc/syncJCResultData";
	var jcDate = document.getElementById("jcDate").value;
	if(jcDate == null || jcDate == ""){
		alert("请选择竞猜日期");
		return;
	}
	if(window.confirm("您确认更新【"+jcDate+"】日期的竞猜赛果数据吗？")){
		window.ajaxSubmitOperate(url , {"jcDate": jcDate}, true);
	}
}

