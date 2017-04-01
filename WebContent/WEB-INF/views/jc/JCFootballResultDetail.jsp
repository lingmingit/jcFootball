<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
   <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>竞猜赛果详情</title>
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/base/common.css"/>
	<script type="text/javascript" src="<c:url value='/js/base/jquery-1.8.2.js'/>"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/js/base/system.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/js/jc/base/common.js"></script>
</head>
<body>
  <form action="" id="listForm">
		<table width="100%" border="0" align="center" cellpadding="0" cellspacing="0" >
		  <tr>
		    <td height="30">
			    <table width="100%" border="0" cellspacing="0" cellpadding="0">
			      <tr>
			        <td width="15" height="30"><img src="${pageContext.request.contextPath}/images/tab/tab_03.gif" width="15" height="30" /></td>
			        <td width="201" background="${pageContext.request.contextPath}/images/tab/tab_05.gif"><img src="${pageContext.request.contextPath}/images/tab/311.gif" width="16" height="16" /> <span class="STYLE4">竞猜赛果详情</span></td>
			        <td width="1181" background="${pageContext.request.contextPath}/images/tab/tab_05.gif">
			        	<!-- 工具栏 -->
				        <table border="0" align="right" cellpadding="0" cellspacing="0">
				            <tr>
				             <td width="60" id="refreshTd">
					              <table width="87%" border="0" cellpadding="0" cellspacing="0" >
					                  <tr>
					                    <td class="STYLE1"><div align="center"><img src="${pageContext.request.contextPath}/images/" width="14" height="14" /></div></td>
					                    <td class="STYLE1"><div align="left"><a href="javascript:window.close();">关闭</a></div></td>
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
			        <td width="97%" >
				        <table border="0" width= "100%" align="left"  cellpadding="0"  cellspacing="1" >
				            <tr >
				              <th width="10%" class="STYLE2 STYLE1" background="${pageContext.request.contextPath}/images/tab/tab_14.gif">竞猜日期：</th>
				              <td width="10%" class="STYLE2 STYLE1" background="${pageContext.request.contextPath}/images/tab/tab_21.gif">${data.jcDate}</td>
				              <th width="10%" class="STYLE2 STYLE1" background="${pageContext.request.contextPath}/images/tab/tab_14.gif">编号：</th>
				              <td width="10%" class="STYLE2 STYLE1" background="${pageContext.request.contextPath}/images/tab/tab_21.gif">${data.week}${data.matchesNo}</td>
				               <th width="5%" class="STYLE2 STYLE1" background="${pageContext.request.contextPath}/images/tab/tab_14.gif">比赛时间：</th>
				              <td width="10%" class="STYLE2 STYLE1" background="${pageContext.request.contextPath}/images/tab/tab_21.gif">${data.matchDateTime}</td>
				            </tr>
				             <tr >
				              <th class="STYLE2 STYLE1" background="${pageContext.request.contextPath}/images/tab/tab_14.gif">赛事：</th>
				              <td class="STYLE2 STYLE1" background="${pageContext.request.contextPath}/images/tab/tab_21.gif">${data.matchName}</td>
				              <td  class="STYLE2 STYLE1" background="${pageContext.request.contextPath}/images/tab/tab_21.gif"></td>
				              <td  class="STYLE2 STYLE1" background="${pageContext.request.contextPath}/images/tab/tab_21.gif"></td>
				              <td  class="STYLE2 STYLE1" background="${pageContext.request.contextPath}/images/tab/tab_21.gif"></td>
				              <td  class="STYLE2 STYLE1" background="${pageContext.request.contextPath}/images/tab/tab_21.gif"></td>
				            </tr>
				            <tr>
				            	<th class="STYLE2 STYLE1" height="18" background="${pageContext.request.contextPath}/images/tab/tab_14.gif" align="center" colspan="6">主队（让）VS客队</th>
				            </tr>
				             <tr >
				               <td  class="STYLE2 STYLE1" background="${pageContext.request.contextPath}/images/tab/tab_21.gif"></td>
				              <td class="STYLE2 STYLE1" height="20" background="${pageContext.request.contextPath}/images/tab/tab_21.gif" colspan="2">
				              	<div align="left" class="STYLE2 STYLE1" >${data.homeTeamName}
					            	<c:choose>
					            		<c:when test="${data.letBall < 0}">
					            			 <span style="color:#f00">(${data.letBall})</span>
					            		</c:when>
					            		<c:otherwise>
					            			<span style="color:#00f">(${data.letBall})</span>
					            		</c:otherwise>
					            	</c:choose>
					            	VS
					            	${data.visitingTeamName}</div>
				              </td>
				              <td width="10%" class="STYLE2 STYLE1" background="${pageContext.request.contextPath}/images/tab/tab_21.gif" colspan="1">
				             		半场：${data.halfCourtScore}
				              </td>
				               <td width="10%" class="STYLE2 STYLE1" background="${pageContext.request.contextPath}/images/tab/tab_21.gif" colspan="1">
				               		全场：${data.fullCourtScore}
				               </td>
				               <td  class="STYLE2 STYLE1" background="${pageContext.request.contextPath}/images/tab/tab_21.gif"></td>
				            </tr>
				            <tr>
				            	<th class="STYLE2 STYLE1" height="15" background="${pageContext.request.contextPath}/images/tab/tab_14.gif" align="center" colspan="6">胜平负</th>
				            </tr>
				             <tr>
				            	<th class="STYLE2 STYLE1"  height="15" background="${pageContext.request.contextPath}/images/tab/tab_14.gif" align="center" colspan="3">彩果</th>
				            	<th class="STYLE2 STYLE1" height="15" background="${pageContext.request.contextPath}/images/tab/tab_14.gif" align="center" colspan="3">赔率</th>
				            </tr>
				             <tr >
				              <td class="STYLE2 STYLE1" height="20" colspan="3" align="center" background="${pageContext.request.contextPath}/images/tab/tab_21.gif">${data.result}</td>
				              <td  class="STYLE2 STYLE1" height="20" colspan="3" align="center"  background="${pageContext.request.contextPath}/images/tab/tab_21.gif">${data.resultOdds}</td>
				            </tr>
				              <tr>
				            	<th class="STYLE2 STYLE1" height="15" background="${pageContext.request.contextPath}/images/tab/tab_14.gif" align="center" colspan="6">让球胜平负</th>
				            </tr>
				              <tr>
				            	<th class="STYLE2 STYLE1"  height="15" background="${pageContext.request.contextPath}/images/tab/tab_14.gif" align="center" colspan="3">彩果</th>
				            	<th class="STYLE2 STYLE1" height="15" background="${pageContext.request.contextPath}/images/tab/tab_14.gif" align="center" colspan="3">赔率</th>
				            </tr>
				             <tr >
				              <td class="STYLE2 STYLE1" height="20" colspan="3" align="center" background="${pageContext.request.contextPath}/images/tab/tab_21.gif">${data.letResult}</td>
				              <td  class="STYLE2 STYLE1" height="20" colspan="3" align="center" background="${pageContext.request.contextPath}/images/tab/tab_21.gif" >${data.letResultOdds}</td>
				            </tr>
				             <tr>
				            	<th class="STYLE2 STYLE1" height="15" background="${pageContext.request.contextPath}/images/tab/tab_14.gif" align="center" colspan="6">总进球</th>
				            </tr>
				             <tr>
				            	<th class="STYLE2 STYLE1"  height="15" background="${pageContext.request.contextPath}/images/tab/tab_14.gif" align="center" colspan="3">彩果</th>
				            	<th class="STYLE2 STYLE1" height="15" background="${pageContext.request.contextPath}/images/tab/tab_14.gif" align="center" colspan="3">赔率</th>
				            </tr>
				             <tr >
				              <td class="STYLE2 STYLE1" height="20" colspan="3" align="center" background="${pageContext.request.contextPath}/images/tab/tab_21.gif">${data.goalNum}</td>
				              <td  class="STYLE2 STYLE1" colspan="3" align="center" background="${pageContext.request.contextPath}/images/tab/tab_21.gif">${data.goalOdds}</td>
				            </tr>
				             <tr>
				            	<th class="STYLE2 STYLE1" height="15" background="${pageContext.request.contextPath}/images/tab/tab_14.gif" align="center" colspan="6">比分</th>
				            </tr>
				             <tr>
				            	<th class="STYLE2 STYLE1"  height="15" background="${pageContext.request.contextPath}/images/tab/tab_14.gif" align="center" colspan="3">彩果</th>
				            	<th class="STYLE2 STYLE1" height="15" background="${pageContext.request.contextPath}/images/tab/tab_14.gif" align="center" colspan="3">赔率</th>
				            </tr>
				             <tr >
				              <td class="STYLE2 STYLE1" height="20" colspan="3" align="center" background="${pageContext.request.contextPath}/images/tab/tab_21.gif">${data.scoreResult}</td>
				              <td  class="STYLE2 STYLE1"  colspan="3" align="center" background="${pageContext.request.contextPath}/images/tab/tab_21.gif">${data.scoreOdds}</td>
				            </tr>
				            <tr>
				            	<th class="STYLE2 STYLE1"height="15" background="${pageContext.request.contextPath}/images/tab/tab_14.gif" align="center" colspan="6">半全场胜平负</th>
				            </tr>
				             <tr>
				            	<th class="STYLE2 STYLE1"  height="15" background="${pageContext.request.contextPath}/images/tab/tab_14.gif" align="center" colspan="3">彩果</th>
				            	<th class="STYLE2 STYLE1" height="15" background="${pageContext.request.contextPath}/images/tab/tab_14.gif" align="center" colspan="3">赔率</th>
				            </tr>
				             <tr >
				              <td class="STYLE2 STYLE1" height="20" colspan="3" align="center" background="${pageContext.request.contextPath}/images/tab/tab_21.gif">${data.halfFullResult}</td>
				              <td  class="STYLE2 STYLE1" colspan="3" align="center" background="${pageContext.request.contextPath}/images/tab/tab_21.gif">${data.halfFullOdds}</td>
				            </tr>
				        </table>
				      </td>
			        <td width="9"  background="${pageContext.request.contextPath}/images/tab/tab_16.gif">&nbsp;</td><!-- table 右边框图片，宽度固定 -->
			      </tr>
			    </table>
		  	</td>
		  </tr>
		  <tr>
		    <td height="29"><table width="100%" border="0" cellspacing="0" cellpadding="0">
		      <tr>
		        <td width="15" height="29"><img src="${pageContext.request.contextPath}/images/tab/tab_20.gif" width="15" height="29" /></td>
		        <td background="${pageContext.request.contextPath}/images/tab/tab_21.gif">
		        	<!-- 分页table html 代码 开始 -->
			        <!-- 分页table html 代码 结束 -->
		        </td>
		        <td width="14"><img src="${pageContext.request.contextPath}/images/tab/tab_22.gif" width="14" height="29" /></td>
		      </tr>
		    </table></td>
		  </tr>
		</table>
   </form>
</body>
</html>