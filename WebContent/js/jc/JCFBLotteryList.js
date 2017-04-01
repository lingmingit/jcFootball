



/***
 * 查看投注详情
 */
function viewLotteryDetail(trId){
	var trObj = document.getElementById(trId);
	if(trObj.style.display == "none"){
		trObj.style.display = "";
	}else{
		trObj.style.display = "none";
	}
}