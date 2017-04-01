<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
   <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>竞猜足球投注列表</title>
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/base/common.css"/>
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/JCfbBetting.css"/>
	<script type="text/javascript" src="<c:url value='/js/base/jquery-1.8.2.js'/>"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/js/base/system.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/js/jc/base/common.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/js/jc/JCFootball.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/js/jc/JCfbBetting.js"></script>
</head>
<body>
  <form action="" id="listForm">
		<table width="100%" border="0" align="center" cellpadding="0" cellspacing="0" >
		  <tr>
		    <td height="30">
			    <table width="100%" border="0" cellspacing="0" cellpadding="0">
			      <tr>
			        <td width="15" height="30"><img src="${pageContext.request.contextPath}/images/tab/tab_03.gif" width="15" height="30" /></td>
			        <td width="201" background="${pageContext.request.contextPath}/images/tab/tab_05.gif"><img src="${pageContext.request.contextPath}/images/tab/311.gif" width="16" height="16" /> <span class="STYLE4">竞猜投注列表</span></td>
			        <td width="1181" background="${pageContext.request.contextPath}/images/tab/tab_05.gif">
			        	<!-- 工具栏 -->
				        <table border="0" align="right" cellpadding="0" cellspacing="0">
				            <tr>
				             <td width="250" id="viewTd">
					              <table width="87%" border="0" cellpadding="0" cellspacing="0" >
					                  <tr>
					                    <td class="STYLE1"><div align="center">竞猜日期</div></td>
					                    <td class="STYLE1"><div align="left"><input type="text" id="jcDate" name="jcDate" value="${jcDate}" /></div></td>
					                  </tr>
					              </table>
				              </td>
				             <td width="100" id="viewTd">
					              <table width="87%" border="0" cellpadding="0" cellspacing="0" >
					                  <tr>
					                    <td class="STYLE1"><div align="center"><img src="${pageContext.request.contextPath}/images/" width="14" height="14" /></div></td>
					                    <td class="STYLE1"><div align="left"><a href="javascript:syncJCData();">数据同步</a></div></td>
					                  </tr>
					              </table>
				              </td>
				              <td width="100" id="viewTd">
					              <table width="87%" border="0" cellpadding="0" cellspacing="0" >
					                  <tr>
					                    <td class="STYLE1"><div align="center"><img src="${pageContext.request.contextPath}/images/" width="14" height="14" /></div></td>
					                    <td class="STYLE1"><div align="left"><a href="javascript:syncJCResultData();">结果同步</a></div></td>
					                  </tr>
					              </table>
				              </td>
				             <td width="60" id="refreshTd">
					              <table width="87%" border="0" cellpadding="0" cellspacing="0" >
					                  <tr>
					                    <td class="STYLE1"><div align="center"><img src="${pageContext.request.contextPath}/images/" width="14" height="14" /></div></td>
					                    <td class="STYLE1"><div align="left"><a href="javascript:refreshPage();">刷新</a></div></td>
					                  </tr>
					              </table>
				              </td>
				               <td width="120" id="refreshTd">
					              <table width="87%" border="0" cellpadding="0" cellspacing="0" >
					                  <tr>
					                    <td class="STYLE1"><div align="center"><img src="${pageContext.request.contextPath}/images/" width="14" height="14" /></div></td>
					                    <td class="STYLE1"><div align="left"><a href="javascript:showJCfbBetting();">我的投资单</a></div></td>
					                  </tr>
					              </table>
				              </td>
				            </tr>
				        </table>
				      </td>
			        <td width="14"><img src="${pageContext.request.contextPath}/images/tab/tab_07.gif" width="14" height="30" /></td>
			      </tr>
			    </table>
		    </td>
		  </tr>
		  <tr>
		  	<td>
		  		<table width="100%" border="0" cellspacing="0" cellpadding="0">
			      <tr>
			        <td width="9" height="30" background="${pageContext.request.contextPath}/images/tab/tab_12.gif">&nbsp;</td><!-- table 左边框图片，宽度固定 -->
			        <td width="5" bgcolor="#f3ffe3"> <span class="STYLE4"></span>&nbsp;</td>
			        <td width="98%" >
				        <table border="0" width= "99%" align="left"  cellpadding="2"  cellspacing="1" >
				            <tr >
				              <td width="5%" class="STYLE2 STYLE1" background="images/tab/tab_14.gif">竞猜日期：</td>
				              <td width="10%" class="STYLE2 STYLE1" background="images/tab/tab_21.gif"><input type="text" id="jcDate2" name="jcDate2" value="${jcDate2}" /></td>
				              <td width="5%" class="STYLE2 STYLE1" background="images/tab/tab_14.gif">赛事：</td>
				              <td width="10%" class="STYLE2 STYLE1"><input type="text" id="matchName" name="matchName" value="${matchName}" /></td>
				               <td width="5%" class="STYLE2 STYLE1" background="images/tab/tab_14.gif">球队名称：</td>
				              <td width="10%" class="STYLE2 STYLE1"><input type="text" id="teamName" name="teamName" value="${teamName}" /></td>
				              <td width="3%" class="STYLE2 STYLE1" background="images/tab/tab_14.gif" align="center"><input type="button" id="query" name="query" value="查询" onclick="queryList();" /></td>
				              <td width="3%" class="STYLE2 STYLE1"><input type="button" id="reset" name="reset" value="重置" onclick="resetList();" /></td>
				            </tr>
				        </table>
				      </td>
			        <td width="9"  background="${pageContext.request.contextPath}/images/tab/tab_16.gif">&nbsp;</td><!-- table 右边框图片，宽度固定 -->
			      </tr>
			    </table>
		  	</td>
		  </tr>
		  <tr>
		    <td><table width="100%" border="0" cellspacing="0" cellpadding="0">
		      <tr>
		        <td width="9" background="${pageContext.request.contextPath}/images/tab/tab_12.gif">&nbsp;</td>
		        <td bgcolor="#f3ffe3">
			        <table width="99%" border="0" align="center" cellpadding="0" cellspacing="1" bgcolor="#c0de98" onmouseover=""  onmouseout="">
			          <tr>
			            <td width="5%" height="26" background="${pageContext.request.contextPath}/images/tab/tab_14.gif" class="STYLE1"><div align="center" class="STYLE2 STYLE1">日期</div></td>
			            <td width="5%" height="18" background="${pageContext.request.contextPath}/images/tab/tab_14.gif" class="STYLE1"><div align="center" class="STYLE2 STYLE1">编号</div></td>
			            <td width="5%" height="18" background="${pageContext.request.contextPath}/images/tab/tab_14.gif" class="STYLE1"><div align="center" class="STYLE2 STYLE1">赛事</div></td>
			            <td width="5%" height="18" background="${pageContext.request.contextPath}/images/tab/tab_14.gif" class="STYLE1"><div align="center" class="STYLE2 STYLE1">截止</div></td>
			            <td width="14%" height="18" background="${pageContext.request.contextPath}/images/tab/tab_14.gif" class="STYLE1"><div align="center" class="STYLE2 STYLE1">主队</div></td>
			            <td width="14%" height="18" background="${pageContext.request.contextPath}/images/tab/tab_14.gif" class="STYLE1"><div align="center" class="STYLE2 STYLE1">客队</div></td>
			            <td width="5%" height="18" background="${pageContext.request.contextPath}/images/tab/tab_14.gif" class="STYLE1"><div align="center" class="STYLE2 STYLE1">分析</div></td>
			            <td width="5%" height="18" background="${pageContext.request.contextPath}/images/tab/tab_14.gif" class="STYLE1"><div align="center" class="STYLE2 STYLE1">胜</div></td>
			            <td width="5%" height="18" background="${pageContext.request.contextPath}/images/tab/tab_14.gif" class="STYLE1"><div align="center" class="STYLE2">平</div></td>
			            <td width="5%" height="18" background="${pageContext.request.contextPath}/images/tab/tab_14.gif" class="STYLE1"><div align="center" class="STYLE2">负</div></td>
			            <td width="3%" height="18" background="${pageContext.request.contextPath}/images/tab/tab_14.gif" class="STYLE1"><div align="center" class="STYLE2">让球</div></td>
			             <td width="5%" height="18" background="${pageContext.request.contextPath}/images/tab/tab_14.gif" class="STYLE1"><div align="center" class="STYLE2 STYLE1">胜</div></td>
			            <td width="5%" height="18" background="${pageContext.request.contextPath}/images/tab/tab_14.gif" class="STYLE1"><div align="center" class="STYLE2">平</div></td>
			            <td width="5%" height="18" background="${pageContext.request.contextPath}/images/tab/tab_14.gif" class="STYLE1"><div align="center" class="STYLE2">负</div></td>
			          </tr>
			          <c:forEach items="${requestScope.jcFootballList}" var="data" varStatus="status">
			          	<tr>
				            <td height="18" bgcolor="#FFFFFF"><div align="center" class="STYLE1">
				             	<fmt:formatDate value="${data.jcDate}" pattern="yyyy-MM-dd"/> 
				            </div></td>
				            <td height="18" bgcolor="#FFFFFF" class="STYLE2"><div align="center" class="STYLE2 STYLE1" >${data.week}${data.matchesNo}</div></td>
				            <td height="18" bgcolor="#FFFFFF" style="${data.matchStyle}">
				            	<div align="center" class="STYLE2 STYLE1">
				            		<c:choose >
				            			<c:when test="${data.matchNameHref != null}">
				            				<a href="${data.matchNameHref}" target="_blank">${data.matchName}</a>
				            			</c:when>
				            			<c:otherwise>
				            				${data.matchName}
				            			</c:otherwise>
				            		</c:choose>
				            	</div>
				            </td>
				            <td height="18" bgcolor="#FFFFFF"><div align="left" class="STYLE2 STYLE1">${data.endTime}</div></td>
				            <td height="18" bgcolor="#FFFFFF"><div align="left" class="STYLE2 STYLE1" >
				            	<c:choose >
				            			<c:when test="${data.homeTeamNameHref != null}">
				            				<a href="${data.homeTeamNameHref}" target="_blank">${data.homeTeamName}</a>
				            			</c:when>
				            			<c:otherwise>
				            				${data.homeTeamName}
				            			</c:otherwise>
				            		</c:choose>
				            	</div></td>
				             <td height="18" bgcolor="#FFFFFF"><div align="left" class="STYLE2 STYLE1">
				            	 <c:choose >
				            			<c:when test="${data.visitingTeamNameHref != null}">
				            				<a href="${data.visitingTeamNameHref}" target="_blank">${data.visitingTeamName}</a>
				            			</c:when>
				            			<c:otherwise>
				            				${data.visitingTeamName}
				            			</c:otherwise>
				            		</c:choose>
				             	</div>
				             </td>
				             <td height="18" bgcolor="#FFFFFF" align="center">
				             	<c:choose>
				             		<c:when test="${data.analysisHref != null}">
				             			<a href="${data.analysisHref}" target="_blank"><img src="${pageContext.request.contextPath}/images/jc/ou.gif" alt="欧" /></a>
				             		</c:when>
				             		<c:otherwise>
				             		</c:otherwise>
				             	</c:choose>
				             	<c:choose>
				             		<c:when test="${data.historyAnalysisHref != null}">
				             			<a href="${data.historyAnalysisHref}" target="_blank" style="margin:3px;" ><img src="${pageContext.request.contextPath}/images/jc/xi.gif" alt="析" /></a>
				             		</c:when>
				             		<c:otherwise>
				             		</c:otherwise>
				             	</c:choose>
				             </td>
				             <c:choose>
				             	<c:when test="${data.isNotSaleSPF == true }">
				             		<td height="18" bgcolor="#FFFFFF" colspan="3"><div align="center" class="STYLE2 STYLE1" >未开售胜平负玩法</div></td>
				             	</c:when>
				             	<c:otherwise>
				             		<td height="18" bgcolor="#FFFFFF"><div align="center" class="STYLE2 STYLE1" >
						            	<input type="checkbox" isletBall="false" value="${data.id}@${data.week}${data.matchesNo}@胜@${data.lordOdds}" onchange="addBettingRecord(this , '${data.id}' ,'${data.week}${data.matchesNo}' , 0 , '胜' , ${data.lordOdds});">
						            	${data.lordOdds}</div>
						            </td>
						            <td height="18" bgcolor="#FFFFFF"><div align="center" class="STYLE2 STYLE1" >
						            	<input type="checkbox" isletBall="false" value="${data.id}@${data.week}${data.matchesNo}@平@${data.flatOdds}" onchange="addBettingRecord(this ,'${data.id}' , '${data.week}${data.matchesNo}' , 0 , '平' , ${data.flatOdds});">
						            	${data.flatOdds}</div>
						            </td>
						            <td height="18" bgcolor="#FFFFFF"><div align="center" class="STYLE2 STYLE1">
						            	<input type="checkbox" isletBall="false" value="${data.id}@${data.week}${data.matchesNo}@负@${data.negativeOdds}" onchange="addBettingRecord(this , '${data.id}' ,'${data.week}${data.matchesNo}' , 0 , '负' , ${data.negativeOdds});">
						            	${data.negativeOdds}</div>
						            </td>
				             	</c:otherwise>
				             </c:choose>
				            <td height="18" bgcolor="#FFFFFF"><div align="center" class="STYLE2 STYLE1">
				            	<c:choose>
					            		<c:when test="${data.letBall < 0}">
					            			 <span style="color:#f00;font-weight: bold;">${data.letBall}</span>
					            		</c:when>
					            		<c:otherwise>
					            			<span style="color:#00f;font-weight: bold;">${data.letBall}</span>
					            		</c:otherwise>
					            	</c:choose>
				            	</div>
				            </td>
				            <td height="18" bgcolor="#FFFFFF"><div align="center" class="STYLE2 STYLE1">
				            	<input type="checkbox" isletBall="true" value="${data.id}@${data.week}${data.matchesNo}@胜@${data.lordLetOdds}" onchange="addBettingRecord(this , '${data.id}' ,'${data.week}${data.matchesNo}' , ${data.letBall} , '胜' , ${data.lordLetOdds});">
				            	${data.lordLetOdds}</div>
				            </td>
				            <td height="18" bgcolor="#FFFFFF"><div align="center" class="STYLE2 STYLE1">
				            	<input type="checkbox" isletBall="true" value="${data.id}@${data.week}${data.matchesNo}@平@${data.flatLetOdds}" onchange="addBettingRecord(this , '${data.id}' ,'${data.week}${data.matchesNo}' , ${data.letBall} , '平' , ${data.flatLetOdds});">
				            	${data.flatLetOdds}</div>
				            </td>
				            <td height="18" bgcolor="#FFFFFF"><div align="center" class="STYLE2 STYLE1">
				            	<input type="checkbox" isletBall="true" value="${data.id}@${data.week}${data.matchesNo}@负@${data.negativeLetOdds}" onchange="addBettingRecord(this , '${data.id}' ,'${data.week}${data.matchesNo}' , ${data.letBall} , '负' , ${data.negativeLetOdds});">
				            	${data.negativeLetOdds}</div>
				            </td>
				          </tr>
			          </c:forEach>
			        </table>
			     </td>
		        <td width="9" background="${pageContext.request.contextPath}/images/tab/tab_16.gif">&nbsp;</td>
		      </tr>
		    </table></td>
		  </tr>
		  <tr>
		    <td height="29"><table width="100%" border="0" cellspacing="0" cellpadding="0">
		      <tr>
		        <td width="15" height="29"><img src="${pageContext.request.contextPath}/images/tab/tab_20.gif" width="15" height="29" /></td>
		        <td background="${pageContext.request.contextPath}/images/tab/tab_21.gif">
		        	<!-- 分页table html 代码 开始 -->
			      	${pagination.pageHtmlStr}
			        <!-- 分页table html 代码 结束 -->
		        </td>
		        <td width="14"><img src="${pageContext.request.contextPath}/images/tab/tab_22.gif" width="14" height="29" /></td>
		      </tr>
		    </table></td>
		  </tr>
		</table>
		<!-- 分页脚本代码 -->
		${pagination.scriptStr}
		<!-- 分页脚本代码 -->
		
	  <div id="previewDiv">  
	        <table id ="head">  
	            <tr><td><span class="STYLE4">【我的投注单】</span></td></tr>  
	        </table>   
	        <table id="myBettingTab" border="0" width= "99%" align="left"  cellpadding="2"  cellspacing="1">  
	           <tr>
	           		<th width="15%" height="18" background="${pageContext.request.contextPath}/images/tab/tab_14.gif" class="STYLE1">编号</th>
	           		<th width="38%" height="18" background="${pageContext.request.contextPath}/images/tab/tab_14.gif" class="STYLE1">投注内容</th>
	           		<th width="8%" height="18" background="${pageContext.request.contextPath}/images/tab/tab_14.gif" class="STYLE1">让球</th>
	           		<th width="38%"  height="18" background="${pageContext.request.contextPath}/images/tab/tab_14.gif" class="STYLE1">(让)投注内容</th>
	           </tr>
	           <!-- <tr id="tr_jcbillId">
	           		<td bgcolor="#FFFFFF" class="STYLE2"><span class="STYLE2 STYLE1">周二012</span></td>
	           		<td bgcolor="#FFFFFF" class="STYLE2"><span class="STYLE2 STYLE1">1</td>
	           		<td bgcolor="#FFFFFF" class="STYLE2"><span class="STYLE2 STYLE1">胜(2.45)平(3.25)负(2.45)</span></td>
	           		<td bgcolor="#FFFFFF" class="STYLE2"><span class="STYLE2 STYLE1"></span></td>
	           </tr> -->
	        </table>
	        <table border="0" width= "99%" cellpadding="2"  cellspacing="1">
	        	<tr>
	        		<th  height="18" colspan="2" class="STYLE1" align="left" background="${pageContext.request.contextPath}/images/tab/tab_14.gif">选择过关方式</th>
	        	</tr>
	        	<tr>
	        		<td  colspan="2" class="STYLE1" >
	        			<input type="checkbox" value="">2串1
	        			<input type="checkbox" value="">3串1
	        			<input type="checkbox" value="">4串1
	        		</td>
	        	</tr>
	        	<tr>
	        		<td class="STYLE1">投资倍数：<input type="text" size="5" id="multiple">倍</td>
	        		<td ></td>
	        	</tr>
	        	<tr>
	        		<td colspan="2" class="STYLE1"><span id="summaryMsg">您选择了0场比赛,共0注</span></td>
	        	</tr>
	        	<tr>
	        		<td class="STYLE1">投资金额：<span id="bettingAmount"></span>元</td>
	        		<td class="STYLE1"></td>
	        	</tr>
	        	<tr>
	        		<td class="STYLE1">奖金预测：<span id="preAmount"></span>元</td>
	        		<td class="STYLE1"></td>
	        	</tr>
	        	<tr>
	        		<td class="STYLE1" align="center">
	        			<input type="button" value="确认投注 " onclick="confirmSubmit();"/>
	        			<input type="button" value="清空投注 " onclick="clearJCfbBetting();"/>
	        		</td>
	        		<td class="STYLE1" align="center"><input type="button" value="关 闭 " onclick="hide();"/></td>
	        	</tr>
	        </table> 
	   </div>  
   </form>
</body>
</html>