








/***
 * 打开赛果详情界面
 */
function openResultDetail(jcFootballId){
	if(jcFootballId == null || jcFootballId == ""){
		alert("竞猜记录id为空!!");
		return;
	}
	//  ViewProcessDiagramServlet  |  GoToViewProcInstDiagramServlet
	var url = "/jc/JCFootballResultDetail?jcFootballId="+jcFootballId+"&random="+Math.random();
	//alert("url="+url);
	window.openModalWin(url , 850 , 400 , false);
}

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
 * 数据同步方法
 */
function syncJCData(){
	var url = getWebRootPath()+"/jc/syncJCData";
	var jcDate = document.getElementById("jcDate").value;
	if(jcDate == null || jcDate == ""){
		alert("请选择竞猜日期");
		return;
	}
	if(window.confirm("您确认更新【"+jcDate+"】日期的竞猜数据吗？")){
		//alert("url="+url);
		window.ajaxSubmitOperate(url , {"jcDate": jcDate} , true);
	}
	
}


