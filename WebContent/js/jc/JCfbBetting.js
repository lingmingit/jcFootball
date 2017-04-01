





/***
 * ajax 提交成功处理后，回调的逻辑处理
 */
function afterAjaxSubmitSuccessHandle(result){
	//input[isletBall] 测试可以  input:checkbox[isletBall]  测试可以
	//alert("afterAjaxSubmitSuccessHandle="+$("input[isletBall]").length);
	$("input:checkbox[isletBall]").attr("checked", false);
}

/***
 * 清空投资单
 */
function clearJCfbBetting(){
	if(window.confirm("您确认清空投注吗？")){
		//清空复选框
		$("input:checkbox[isletBall]").attr("checked", false);
		//删除 投资table 行
		$("tr[id^='tr_']" , $("#myBettingTab")).each(function(){
			$(this).remove();
		});
	}
}


/***
 * 确认提交投注单
 */
function confirmSubmit(){
	
	var length = $("tr[id^='tr_']" , $("#myBettingTab")).length;
	if(length == 0){
		alert("请选择投注记录!!");
		return;
	}
	var index = 0;
	var jcfbIdArray = new Array();//竞猜记录id
	var resultArray = new Array();//竞猜结果
	var oddsValueArray = new Array();//竞猜结果 赔率
	var letballArray = new Array();//竞猜让球数量
	var letResultArray = new Array();//让球竞猜结果
	var letOddsValueArray = new Array();//让球竞猜结果 赔率
	
	$("tr[id^='tr_']" , $("#myBettingTab")).each(function(){
		var jcfbId = $(this).attr("id");
		jcfbId = jcfbId.replace("tr_" , "");
		var resultValue = $("td:eq(1)" , $(this)).attr("resultValue");
		var oddsValue = $("td:eq(1)" , $(this)).attr("oddsValue");
		var letball = $("td:eq(2)" , $(this)).text();
		var letResultValue = $("td:eq(3)" , $(this)).attr("resultValue");
		var letOddsValue = $("td:eq(3)" , $(this)).attr("oddsValue");
		//竞猜记录id
		jcfbIdArray[index] = jcfbId;
		resultArray[index] = resultValue;
		oddsValueArray[index] = oddsValue;
		letballArray[index] = letball;
		letResultArray[index] = letResultValue;
		letOddsValueArray[index] = letOddsValue;
		index++;
		//alert("jcfbId="+jcfbId+"\t resultValue="+resultValue+" \t oddsValue="+oddsValue+" \t letball="+letball+" \t letResultValue="+letResultValue+" \t letOddsValue="+letOddsValue);
	});
	
//	alert("jcfbIdArray="+jcfbIdArray);
//	alert("resultArray="+resultArray);
//	alert("oddsValueArray="+oddsValueArray);
//	alert("letballArray="+letballArray);
//	alert("letResultArray="+letResultArray);
//	alert("letOddsValueArray="+letOddsValueArray);
	
	var type = null;
	var multiple = 1;
	var bettingAmount = 0;
	var preAmount = 0;
	
	var url = getWebRootPath()+"/jc/JCFootballAddBetting";
	if(window.confirm("您确认投注吗？")){
		//alert("url="+url);
		var param = {
				"jcfbIdArray" : jcfbIdArray , 
				"resultArray" : resultArray  , 
				"oddsValueArray": oddsValueArray ,
				"letballArray" : letballArray ,
				"letResultArray" : letResultArray  , 
				"letOddsValueArray" : letOddsValueArray , 
				"type" :  type,
				"multiple" :  multiple , 
				"bettingAmount" : bettingAmount,
				"preAmount" : preAmount
		};
		window.ajaxSubmitOperate(url , param , true);
	}
}

/***
 * 统计金额
 */
function getTotalAmount(){
	
}


/***
 * 增加投资记录
 * @param jcfbId 竞猜记录id
 * @param letBall 让球个数
 * @param jcResult 竞猜结果
 * @param jcOdds 竞猜赔率
 */
function addBettingRecord(checkObj , jcfbId , matchNum , letBall , jcResult , jcOdds){
	//alert("jcfbId="+jcfbId+" \t letBall="+letBall+" \t jcResult="+jcResult+" \t jcOdds="+jcOdds);
	showJCfbBetting();
	//myBettingTab
	var trObjArray = $("tr[id='tr_"+jcfbId+"']");
	var selectObject= window.getSelectedStr(jcfbId , false);
	var letBallSelectObject= window.getSelectedStr(jcfbId , true);
	
	var letBallStr = "";
	if(letBall != 0){
		letBallStr = letBall;
	}
	
	if(selectObject.displayStr == "" && letBallSelectObject.displayStr == ""){
		//删除tr行对象
		$("tr[id='tr_"+jcfbId+"']:eq(0)").remove();
	}else{
		if(trObjArray.length == 0){//增加一行数据
			var htmlStr = "<tr id=\"tr_"+jcfbId+"\">";
				htmlStr += "<td bgcolor=\"#FFFFFF\" class=\"STYLE2\"><span class=\"STYLE2 STYLE1\">"+matchNum+"</td>";
				htmlStr += "<td bgcolor=\"#FFFFFF\" class=\"STYLE2\" resultValue=\""+selectObject.resultValueStr+"\" oddsValue=\""+selectObject.oddsValueStr+"\" ><span class=\"STYLE2 STYLE1\">"+selectObject.displayStr+"</td>";
				htmlStr += "<td bgcolor=\"#FFFFFF\" class=\"STYLE2\"><span class=\"STYLE2 STYLE1\">"+letBallStr+"</td>";
				htmlStr += "<td bgcolor=\"#FFFFFF\" class=\"STYLE2\" resultValue=\""+letBallSelectObject.resultValueStr+"\" oddsValue=\""+letBallSelectObject.oddsValueStr+"\"><span class=\"STYLE2 STYLE1\">"+letBallSelectObject.displayStr+"</td>";
				htmlStr += "</tr>";
				$("#myBettingTab").append(htmlStr);
		}else{//已经存在的竞猜数据
			var trObj = trObjArray.get(0);
			var str = $("td:eq(1) > span" , trObj).text();
			//alert("str="+str);
			$("td:eq(1) > span" , trObj).text(selectObject.displayStr);
			$("td:eq(1)" , trObj).attr("resultValue" , selectObject.resultValueStr);
			$("td:eq(1)" , trObj).attr("oddsValue" , selectObject.oddsValueStr);
			if($("td:eq(2) > span" , trObj).text() == null || $("td:eq(2) > span" , trObj).text() == ""){
				$("td:eq(2) > span" , trObj).text(letBallStr);
			}
			$("td:eq(3) > span" , trObj).text(letBallSelectObject.displayStr);
			$("td:eq(3)" , trObj).attr("resultValue" , letBallSelectObject.resultValueStr);
			$("td:eq(3)" , trObj).attr("oddsValue" , letBallSelectObject.oddsValueStr);
		}
	}
	var length = $("tr[id^='tr_']" , $("#myBettingTab")).length;
	var summaryMsgStr = "您选择了<font color='red' style='font-weight: bold;'>"+length+"</font>场比赛,共0注";
	$("#summaryMsg").html(summaryMsgStr);
	
}


/***
 * 获取竞猜记录 选中的复选框 信息
 * @param jcfbId 竞猜记录id
 * @param isletBall 是否让球数据行
 */
function getSelectedStr(jcfbId , isletBall){
	var returnValue = {};
	var displayStr = "";
	var resultValueStr = "";//竞猜结果value 值
	var oddsValueStr = "";//竞猜结果赔率 value值
	//input:checkbox:checked
	//1、复选框选中选择器：input[type='checkbox'][value^='"+jcfbId+"']:checked  测试可以使用
	//2、复选框选中选择器：input:checkbox[value^='"+jcfbId+"']:checked  测试可以使用
	$("input:checkbox[value^='"+jcfbId+"'][isletBall='"+isletBall+"']:checked").each(function(){
		//alert($(this));
		var value = $(this).attr("value");
		var valueArray = value.split("@");//[${data.id}@${data.week}${data.matchesNo}@胜@${data.negativeOdds}]
		displayStr += valueArray[2]+"("+valueArray[3]+")";//显示字符串
		if(resultValueStr != ""){
			resultValueStr += "@";
			oddsValueStr += "@";
		}
		resultValueStr += valueArray[2];
		oddsValueStr += valueArray[3];
	});
	return {'displayStr' : displayStr , 'resultValueStr' : resultValueStr , 'oddsValueStr' : oddsValueStr};
}















/***
 * 显示窗口
 */
function showJCfbBetting(){ 
       //添加并显示遮罩层  
     // $("<div id='mask'></div>").addClass("mask").click(function() {}) .appendTo("body").fadeIn(0);    
      //  document.getElementById("mask").style.display = "block";
    
        var windowWidth = document.documentElement.clientWidth;  
        var windowHeight = document.documentElement.clientHeight;  
        var popupHeight = $("#previewDiv").height();  
        var popupWidth = $("#previewDiv").width();
        //(windowHeight-popupHeight)/2+$(document).scrollTop()
        // (windowWidth-popupWidth)/2
        $("#previewDiv").css({  
            "position": "absolute",  
            "top": 100,  
            "left":50
        });
       $("#previewDiv").show();
}


/**
 * 隐藏窗口
 */
function hide(){
	$("#mask").remove();
	$("#previewDiv").hide();
}