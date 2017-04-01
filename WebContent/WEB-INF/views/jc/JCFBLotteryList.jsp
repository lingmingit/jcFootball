<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
   <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>竞猜投注列表</title>
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/base/common.css"/>
	<script type="text/javascript" src="<c:url value='/js/base/jquery-1.8.2.js'/>"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/js/base/system.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/js/jc/base/common.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/js/jc/JCFBLotteryList.js"></script>
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
				              <td width="100" id="viewTd">
					              <table width="87%" border="0" cellpadding="0" cellspacing="0" >
					                  <tr>
					                    <td class="STYLE1"><div align="center"><img src="${pageContext.request.contextPath}/images/" width="14" height="14" /></div></td>
					                    <td class="STYLE1"><div align="left"><a href="javascript:syncJCResultData();">xxx</a></div></td>
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
				            <tr>
				              <td width="5%" class="STYLE2 STYLE1" background="images/tab/tab_14.gif">投注日期：</td>
				              <td width="10%" class="STYLE2 STYLE1" background="images/tab/tab_21.gif"><input type="text" id="bizDate" name="bizDate" value="${bizDate}" /></td>
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
			            <td width="5%" height="26" background="${pageContext.request.contextPath}/images/tab/tab_14.gif" class="STYLE1"><div align="center" class="STYLE2 STYLE1">投注日期</div></td>
			            <td width="5%" height="18" background="${pageContext.request.contextPath}/images/tab/tab_14.gif" class="STYLE1"><div align="center" class="STYLE2 STYLE1">编号</div></td>
			            <td width="5%" height="18" background="${pageContext.request.contextPath}/images/tab/tab_14.gif" class="STYLE1"><div align="center" class="STYLE2 STYLE1">彩票类型</div></td>
			            <td width="5%" height="18" background="${pageContext.request.contextPath}/images/tab/tab_14.gif" class="STYLE1"><div align="center" class="STYLE2 STYLE1">投资倍数</div></td>
			            <td width="5%" height="18" background="${pageContext.request.contextPath}/images/tab/tab_14.gif" class="STYLE1"><div align="center" class="STYLE2 STYLE1">投资金额</div></td>
			            <td width="5%" height="18" background="${pageContext.request.contextPath}/images/tab/tab_14.gif" class="STYLE1"><div align="center" class="STYLE2 STYLE1">预计中奖金额</div></td>
			            <td width="5%" height="18" background="${pageContext.request.contextPath}/images/tab/tab_14.gif" class="STYLE1"><div align="center" class="STYLE2 STYLE1">中奖</div></td>
			            <td width="5%" height="18" background="${pageContext.request.contextPath}/images/tab/tab_14.gif" class="STYLE1"><div align="center" class="STYLE2 STYLE1">中奖金额</div></td>
			            <td width="3%" height="18" background="${pageContext.request.contextPath}/images/tab/tab_14.gif" class="STYLE1"><div align="center" class="STYLE2 STYLE1"></div></td>
			          </tr>
			          <c:forEach items="${requestScope.dataList}" var="data" varStatus="status">
			          	<tr>
				            <td height="18" bgcolor="#FFFFFF"><div align="center" class="STYLE1">
				             	<fmt:formatDate value="${data.bizDate}" pattern="yyyy-MM-dd"/> 
				            </div></td>
				            <td height="18" bgcolor="#FFFFFF" class="STYLE2"><div align="center" class="STYLE2 STYLE1" >${data.numbers}</div></td>
				            <td height="18" bgcolor="#FFFFFF">
				            	<div align="center" class="STYLE2 STYLE1">${data.type}</div>
				            </td>
				            <td height="18" bgcolor="#FFFFFF"><div align="left" class="STYLE2 STYLE1">${data.multiple}</div></td>
				            <td height="18" bgcolor="#FFFFFF"><div align="left" class="STYLE2 STYLE1" >${data.bettingAmount}</div></td>
				             <td height="18" bgcolor="#FFFFFF"><div align="left" class="STYLE2 STYLE1">${data.preAmount}</div></td>
				             <td height="18" bgcolor="#FFFFFF" align="center">
				             	<c:choose>
				             		<c:when test="${data.isZJ == true}">
				             			<input type="checkbox" checked="checked" disabled="disabled" />
				             		</c:when>
				             		<c:otherwise>
				             			<input type="checkbox"  disabled="disabled" />
				             		</c:otherwise>
				             	</c:choose>
				             </td>
				            <td height="18" bgcolor="#FFFFFF"><div align="center" class="STYLE2 STYLE1">${data.zjAmount}</div></td>
				             <td height="18" bgcolor="#FFFFFF">
				             	<div align="center" class="STYLE2 STYLE1">
				             		<a href="javascript:viewLotteryDetail('tr_detail_${data.id}');">明细</a>
				             	</div>
				             </td>
				          </tr>
				          <tr id="tr_detail_${data.id}" style="display: none;">
				          	<td height="18" bgcolor="#FFFFFF" colspan="9" >
				          		<table bgcolor="#c0de98" width="60%" border="0" align="right" cellpadding="0" cellspacing="1">
				          			<tr>
				          				<th width="15%" height="18" background="${pageContext.request.contextPath}/images/tab/tab_14.gif" class="STYLE1">让</th>
				          				<th width="45%" height="18" background="${pageContext.request.contextPath}/images/tab/tab_14.gif" class="STYLE1">对阵及结果</th>
				          				<th width="20%" height="18" background="${pageContext.request.contextPath}/images/tab/tab_14.gif" class="STYLE1">投注内容</th>
				          				<th width="10%" height="18" background="${pageContext.request.contextPath}/images/tab/tab_14.gif" class="STYLE1">最终结果</th>
				          				<th width="10%" height="18" background="${pageContext.request.contextPath}/images/tab/tab_14.gif" class="STYLE1">最终赔率</th>
				          			</tr>
				          			<c:forEach var="entryData" items="${data.entryList}" varStatus="entryStatus">
				          				<tr>
				          					<td height="18" bgcolor="#FFFFFF"><div align="center" class="STYLE2 STYLE1">${entryData.letBall}</div></td>
				          					<td height="18" bgcolor="#FFFFFF"><div align="center" class="STYLE2 STYLE1">${entryData.jcFootball.homeTeamName}
				          						<c:choose>
				          							<c:when test="${entryData.jcFootballResult.fullCourtScore != null}">
				          								<span style="color: red;margin-left: 5px;margin-right: 5px;">${entryData.jcFootballResult.fullCourtScore}</span>
				          							</c:when>
				          							<c:otherwise>
				          								VS
				          							</c:otherwise>
				          						</c:choose>
				          						${entryData.jcFootball.visitingTeamName}</div></td>
				          					<td height="18" bgcolor="#FFFFFF">
				          						<c:set var="color" value="" />
				          						<c:if test="${entryData.isResult == true}">
				          							<c:set var="color" value="red" />
				          						</c:if>
				          						<div align="center" class="STYLE2 STYLE1" style="color: ${color};">
				          							${entryData.result}(${entryData.resultOdds})
				          						</div>
				          					</td>
				          					<td height="18" bgcolor="#FFFFFF"><div align="center" class="STYLE2 STYLE1">${entryData.finalResult}</div></td>
				          					<td height="18" bgcolor="#FFFFFF"><div align="center" class="STYLE2 STYLE1">${entryData.finalResultOdds}</div></td>
				          				</tr>
				          			</c:forEach>
				          		</table>
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
   </form>
</body>
</html>