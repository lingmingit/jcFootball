package com.jcfb.utils;



/***
 * 构建分页 html字符串，如：首页、上一页、页码、下一页、最后一页<p>
 * @author LingMin @date 2013-6-6<br>
 * @version 1.0<br>
 */
public class PaginationHtmlUtils {

	
	/**分页数据起始行索引*/
	protected int startRowIndex = 0;
	/***当前页面**/
	protected int currentPage = 1;
	/***总共页数***/
	protected int pageCount = 0;
	/**总行数**/
	protected int totalRowCount = 0;
	/**每页大小**/
	protected int pageRowCount = 10;
	/**提交url地址**/
	protected String pageUrl;
	/***web 上下文路径**/
	protected String contextPath;
	/***页面显示 个数***/
	protected int numberCount = 5;
	/**分页html字符串**/
	protected String pageHtmlStr;
	/***脚本字符串**/
	protected String scriptStr;
	/***
	 * 构造方法
	 */
	public PaginationHtmlUtils(){
	}
	
	
	/***
	 * 构造方法 初始化信息
	 * @param pageRowCount 每页大小行数
	 * @param totalRowCount 总行数
	 * @param pageUrl分页提交url地址
	 * @param contextPath工程根目录
	 */
	public PaginationHtmlUtils(int totalRowCount ,int pageRowCount, String pageUrl , String contextPath){
		this.pageRowCount = pageRowCount;
		this.totalRowCount = totalRowCount;
		this.pageUrl = pageUrl;
		this.contextPath = contextPath;
		
		this.initCalculateHtmlStr();
	}
	
	
	/***
	 * 构造方法 初始化信息
	 * @param currentPage 当前页面
	 * @param pageRowCount 每页大小
	 * @param totalRowCount 总行数
	 * @param pageUrl 分页url路径
	 * @param contextPath上下文路径
	 */
	public PaginationHtmlUtils(int currentPage , int pageRowCount , int totalRowCount , String pageUrl , String contextPath){
		this.currentPage = currentPage;
		this.pageRowCount = pageRowCount;
		this.totalRowCount = totalRowCount;
		this.pageUrl = pageUrl;
		this.contextPath = contextPath;
		this.initCalculateHtmlStr();
	}
	
	
	/***
	 * 初始化 计算 分页数据起始行索引
	 */
	private void initStartRowIndex(){
		//分页数据起始行索引  =  (当前页 -1) *每页 大小
		this.startRowIndex = (currentPage - 1) * pageRowCount;
	}
	
	/***
	 * 初始化 计算 总页数<p>
	 * 
	 * void
	 */
	private void initPageCount(){
		pageCount = (int)Math.ceil((double)totalRowCount / pageRowCount);//计算总页数=总记录行数量 /每页行数   取整数(大)
	}
	
	
	/***
	 * 获取计算起始行  拼接分页字符串<p>
	 * @return
	 * int
	 */
	public void initCalculateHtmlStr(){
		this.initStartRowIndex();
		this.initPageCount();
		this.initPageHtmlStr();
	}
	
	
	
	/***
	 * 初始化分页html字符串方法
	 */
	private void initPageHtmlStr(){
		/*****
			<!-- 分页table html 代码 开始 -->
		        <table width="100%" border="0" cellspacing="0" cellpadding="0">
		          <tr>
		            <td width="25%" height="29" nowrap="nowrap"><span class="STYLE1">共120条纪录，当前第1/10页，每页10条纪录</span></td>
		            <td width="75%" valign="top" class="STYLE1"><div align="right">
		              <table width="352" height="20" border="0" cellpadding="0" cellspacing="0">
		                <tr>
		                  <td width="62" height="22" valign="middle"><div align="right"><img src="${pageContext.request.contextPath}/images/tab/first.gif" width="37" height="15" /></div></td>
		                  <td width="50" height="22" valign="middle"><div align="right"><img src="${pageContext.request.contextPath}/images/tab/back.gif" width="43" height="15" /></div></td>
		                  <td width="54" height="22" valign="middle"><div align="right"><img src="${pageContext.request.contextPath}/images/tab/next.gif" width="43" height="15" /></div></td>
		                  <td width="49" height="22" valign="middle"><div align="right"><img src="${pageContext.request.contextPath}/images/tab/last.gif" width="37" height="15" /></div></td>
		                  <td width="59" height="22" valign="middle"><div align="right">转到第</div></td>
		                  <td width="25" height="22" valign="middle"><span class="STYLE7">
		                    <input name="textfield" type="text" class="STYLE1" style="height:10px; width:25px;" size="5" />
		                  </span></td>
		                  <td width="23" height="22" valign="middle">页</td>
		                  <td width="30" height="22" valign="middle"><img src="${pageContext.request.contextPath}/images/tab/go.gif" width="37" height="15" /></td>
		                </tr>
		              </table>
		            </div></td>
		          </tr>
		        </table>
		        <!-- 分页table html 代码 结束 --> 
		 */
		
		StringBuffer htmlStr = new StringBuffer();
		htmlStr.append("<table width=\"100%\" border=\"0\" cellspacing=\"0\" cellpadding=\"0\">");
		htmlStr.append("	<tr>");
		htmlStr.append("		<td width=\"25%\" height=\"29\" nowrap=\"nowrap\"><span class=\"STYLE1\">共").append(this.totalRowCount).append("条纪录，当前第").append(this.currentPage).append("/").append(this.pageCount).append("页，每页").append(this.pageRowCount).append("条记录</span></td>");
		htmlStr.append(" 		<td width=\"75%\" valign=\"top\" class=\"STYLE1\"><div align=\"right\">");
		htmlStr.append("			<table width=\"352\" height=\"20\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\">");
		htmlStr.append("				<tr>");
		//第一页   上一页
		if(this.currentPage == 1){
			htmlStr.append("				<td width=\"62\" height=\"22\" valign=\"middle\"><div align=\"right\"><img src=\"").append(contextPath).append("/images/tab/first.gif\" width=\"37\" height=\"15\" /></div></td>");
			htmlStr.append("				<td width=\"50\" height=\"22\" valign=\"middle\"><div align=\"right\"><img src=\"").append(contextPath).append("/images/tab/back.gif\" width=\"43\" height=\"15\" /></div></td>");
			htmlStr.append("");
		}else{
			htmlStr.append("				<td width=\"62\" height=\"22\" valign=\"middle\"><div align=\"right\"><img style=\"cursor: pointer;\" onclick=\"window.gotoPage('listForm', 1 );\" src=\"").append(contextPath).append("/images/tab/first.gif\" width=\"37\" height=\"15\" /></div></td>");
			htmlStr.append("				<td width=\"50\" height=\"22\" valign=\"middle\"><div align=\"right\"><img style=\"cursor: pointer;\" onclick=\"window.gotoPage('listForm', ").append(this.currentPage-1).append(");\" src=\"").append(contextPath).append("/images/tab/back.gif\" width=\"43\" height=\"15\" /></div></td>");
			
		}
		
		//下一页   末页
		if(this.currentPage == this.pageCount || this.pageCount == 0){
			htmlStr.append("				<td width=\"54\" height=\"22\" valign=\"middle\"><div align=\"right\"><img src=\"").append(contextPath).append("/images/tab/next.gif\" width=\"43\" height=\"15\" /></div></td>");
			htmlStr.append("				<td width=\"49\" height=\"22\" valign=\"middle\"><div align=\"right\"><img src=\"").append(contextPath).append("/images/tab/last.gif\" width=\"37\" height=\"15\" /></div></td>");
			htmlStr.append("");
		}else{
			htmlStr.append("				<td width=\"54\" height=\"22\" valign=\"middle\"><div align=\"right\"><img style=\"cursor: pointer;\" onclick=\"window.gotoPage('listForm', ").append(this.currentPage+1).append(");\" src=\"").append(contextPath).append("/images/tab/next.gif\" width=\"43\" height=\"15\" /></div></td>");
			htmlStr.append("				<td width=\"49\" height=\"22\" valign=\"middle\"><div align=\"right\"><img style=\"cursor: pointer;\" onclick=\"window.gotoPage('listForm', ").append(this.pageCount).append(");\" src=\"").append(contextPath).append("/images/tab/last.gif\" width=\"37\" height=\"15\" /></div></td>");
		}
		htmlStr.append("					<td width=\"59\" height=\"22\" valign=\"middle\"><div align=\"right\">转到第</div></td>");
		
		htmlStr.append("					<td width=\"25\" height=\"22\" valign=\"middle\"><span class=\"STYLE7\">");
		htmlStr.append("                       <input name=\"txtGotoPage\" id=\"txtGotoPage\" type=\"text\" class=\"STYLE1\" style=\"height:10px; width:25px;\" size=\"5\" />");
		htmlStr.append("						</span></td>");
		htmlStr.append("					<td width=\"23\" height=\"22\" valign=\"middle\">页</td>	");
		htmlStr.append("					<td width=\"30\" height=\"22\" valign=\"middle\"><img style=\"cursor: pointer;\" onclick=\"window.gotoPage('listForm', document.getElementById('txtGotoPage').value);\"").append("").append(" src=\"").append(contextPath).append("/images/tab/go.gif\" width=\"37\" height=\"15\" /></td>");
		htmlStr.append("				</tr>");
		htmlStr.append("			</table>");
		htmlStr.append("		</div></td>");
		htmlStr.append("	 </tr>");
		htmlStr.append("  </table>");
		
		this.pageHtmlStr = htmlStr.toString();
		
		/****js 脚本字符串*****/
		StringBuffer scriptBuffer = new StringBuffer();
		scriptBuffer.append("<input type='hidden' id='currentPage' name='currentPage'/>");
		scriptBuffer.append("<script type=\"text/javascript\" >");
		scriptBuffer.append("	function gotoPage(listForm , currentPage){");
		scriptBuffer.append("		document.getElementById('currentPage').value=currentPage;");
		scriptBuffer.append("	    document.getElementById('listForm').action='").append(this.contextPath).append(this.pageUrl).append("';");
		scriptBuffer.append("		document.getElementById('listForm').submit();");
		scriptBuffer.append("	}");
		scriptBuffer.append("</script>");
		this.scriptStr = scriptBuffer.toString();
	}
	
	
	
	
	
	
	
	/***
	 * 初始化 分页html字符串<p>
	 * 大分页算法如下：
	 * <div class="list">
		<div class="listf">
			<ul>
				<li><input name="" type="image" src="images/x1.png"></li>
				<li><input name="" type="image" src="images/x2.png"></li>
				<li class="djl">1</li>
				<li class="mdj">2</li>
				<li class="mdj">3</li>
				<li class="mdj">4</li>
				<li class="mdj">5</li>
				<li class="mdj">6</li>
				<li class="mdj">7</li>
				<li class="mdj">8</li>
				<li><input name="" type="image" src="images/x3.png"></li>
				<li><input name="" type="image" src="images/x4.png"></li>
			</ul>
		</div>
		<div class="listf1">
			<ul>
				<li>共10页</li>
				<li>到<input name="" type="text" size="2">页
				</li>
				<li><input name="" type="image" src="images/x6.png"></li>
			</ul>
		</div>
	</div>
	 * void
	 */
	private void initPageHtmlStr2(){
		StringBuffer htmlStr = new StringBuffer();
		htmlStr.append("<div class='list'>");
		htmlStr.append("	<div class='listf'>");
		htmlStr.append("		<ul>");
		//第一页   上一页
		if(this.currentPage == 1){
			htmlStr.append("<li><img alt='' src='");
			htmlStr.append(contextPath);
			htmlStr.append("/images/x1.png'  title='第一页'></li>");
			htmlStr.append("<li><img alt='' src='");
			htmlStr.append(contextPath);
			htmlStr.append("/images/x2.png'  title='上一页'></li>");
			htmlStr.append("");
			htmlStr.append("");
		}else{
			htmlStr.append("<li><a href='");
			htmlStr.append(this.pageUrl);
			htmlStr.append("?page.currentPage=1");
			htmlStr.append("'>");
			htmlStr.append("<img alt='' src='");
			htmlStr.append(contextPath);
			htmlStr.append("/images/x1.png'  title='第一页'>");
			htmlStr.append("</a></li>");
			
			htmlStr.append("<li><a href='");
			htmlStr.append(this.pageUrl);
			htmlStr.append("?page.currentPage=");
			htmlStr.append(this.currentPage - 1);
			htmlStr.append("'>");
			htmlStr.append("<img alt='' src='");
			htmlStr.append(contextPath);
			htmlStr.append("/images/x2.png'  title='上一页'>");
			htmlStr.append("</a></li>");
			htmlStr.append("");
		}
		
		/**************大分页算法 lingmin***************/
		//大页码(当前页)=小页面(当前页)/大页面大小  取整数大
		int largeCurrentPage = (int)Math.ceil((double)this.currentPage/this.numberCount);
		//大页码(起始页) = (大页码当前页 - 1)× 大页码每页大小
		int largeStartPage =  (largeCurrentPage - 1) * this.numberCount;
		//循环起始数= 大页码(起始页)
		int begin = largeStartPage;
		//循环结束数 = 大页码(当前页) ×  大页码每页大小
		int end = largeCurrentPage * this.numberCount;
		
		for(int i = begin; i < end ; i++){
			if(i+1 > this.pageCount){
				break;
			}
			if(this.currentPage == i+1){
				htmlStr.append("<li class='djl'>");
				htmlStr.append(i+1);
				htmlStr.append("</li>");
			}else{
				htmlStr.append("<li class='mdj'>");
				htmlStr.append("<a href='");
				htmlStr.append(this.pageUrl);
				htmlStr.append("?page.currentPage=");
				htmlStr.append(i+1);
				htmlStr.append("'>");
				htmlStr.append(i+1);
				htmlStr.append("</a></li>");
			}
		}
		
		//下一页   最后一页
		if(this.currentPage == this.pageCount){
			htmlStr.append("<li><img alt='' src='");
			htmlStr.append(contextPath);
			htmlStr.append("/images/x3.png'  title='下一页'></li>");
			htmlStr.append("<li><img alt='' src='");
			htmlStr.append(contextPath);
			htmlStr.append("/images/x4.png'  title='最后一页'></li>");
			htmlStr.append("");
			htmlStr.append("");
		}else{
			htmlStr.append("<li><a href='");
			htmlStr.append(this.pageUrl);
			htmlStr.append("?page.currentPage=");
			htmlStr.append(this.currentPage + 1);
			htmlStr.append("'>");
			htmlStr.append("<img alt='' src='");
			htmlStr.append(contextPath);
			htmlStr.append("/images/x3.png'  title='下一页'>");
			htmlStr.append("</a></li>");
			
			htmlStr.append("<li><a href='");
			htmlStr.append(this.pageUrl);
			htmlStr.append("?page.currentPage=");
			htmlStr.append(this.pageCount);
			htmlStr.append("'>");
			htmlStr.append("<img alt='' src='");
			htmlStr.append(contextPath);
			htmlStr.append("/images/x4.png'  title='最后一页'>");
			htmlStr.append("</a></li>");
			htmlStr.append("");
		}
		
		htmlStr.append("		</ul>");
		htmlStr.append("	</div>");
		//总页数   调整页数
		htmlStr.append("	<div class='listf1'>");
		htmlStr.append("		<ul>");
		htmlStr.append("			<li>共");
		htmlStr.append(this.pageCount);
		htmlStr.append("			页</li>");
		htmlStr.append("			<li>到<input id='gotoPage' name='gotoPage'  type='text' size='2'>页</li>");
		htmlStr.append("			<li>");
		htmlStr.append("<a id='gotoPageA' href=\"");
		htmlStr.append(this.pageUrl);
		htmlStr.append("?page.currentPage=\"");
		//htmlStr.append("document.getElementById('gotoPage').value;\"");
		htmlStr.append(" onclick='return gotoPageFun();'>");
		htmlStr.append("");
		htmlStr.append("");
		htmlStr.append("<img alt='' src='");
		htmlStr.append(this.contextPath);
		htmlStr.append("/images/x6.png' title='跳转'></a>");
		htmlStr.append("			</li>");
		htmlStr.append("		</ul>");
		htmlStr.append("</div>");
		htmlStr.append("<script language='JavaScript' type='text/javascript'> ");
		htmlStr.append("     function gotoPageFun(){");
		htmlStr.append("		  var gotoPageNumber = document.getElementById('gotoPage').value;");
		htmlStr.append(" 		if(gotoPageNumber == null || gotoPageNumber == ''){");
		htmlStr.append("			 alert('请输入跳转的页码!!');");
		htmlStr.append("			 return false;");
		htmlStr.append("		 }");
		htmlStr.append("		 if(!/^\\d+$/.test(gotoPageNumber)){");
		htmlStr.append("			 alert('请输入数字页码!!');");
		htmlStr.append("			 return false;");
		htmlStr.append("		 }");
		htmlStr.append("		 if(gotoPageNumber > ");
		htmlStr.append(this.pageCount);
		htmlStr.append("){");
		htmlStr.append("			alert('您输入的页码大于最大页码!!');");
		htmlStr.append("			return false;");
		htmlStr.append(" 		 }");
		
		htmlStr.append("		 if(gotoPageNumber == ");
		htmlStr.append(this.currentPage);
		htmlStr.append("){");
		htmlStr.append("			alert('您输入的页码即是当前页!!');");
		htmlStr.append("			return false;");
		htmlStr.append(" 		 }");
		
		htmlStr.append("		 if(gotoPageNumber <= 0 ){");
		htmlStr.append("			alert('您输入正确的页码!!');");
		htmlStr.append("			return false;");
		htmlStr.append(" 		 }");
		
		htmlStr.append("			var aHref = document.getElementById('gotoPageA').href;");
		//htmlStr.append("			alert('1='+aHref);");
		htmlStr.append("			document.getElementById('gotoPageA').href=aHref+gotoPageNumber;");
		//htmlStr.append("			aHref = document.getElementById('gotoPageA').href;");
		//htmlStr.append("			alert('2='+aHref);");
		htmlStr.append("				");
		htmlStr.append("	  return true;");
		htmlStr.append(" 	}");
		htmlStr.append("</script> ");
		
		this.pageHtmlStr = htmlStr.toString();
	}


	public int getStartRowIndex() {
		return startRowIndex;
	}


	public void setStartRowIndex(int startRowIndex) {
		this.startRowIndex = startRowIndex;
	}


	public int getCurrentPage() {
		return currentPage;
	}


	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}


	public int getPageCount() {
		return pageCount;
	}


	public void setPageCount(int pageCount) {
		this.pageCount = pageCount;
	}


	public int getTotalRowCount() {
		return totalRowCount;
	}


	public void setTotalRowCount(int totalRowCount) {
		this.totalRowCount = totalRowCount;
	}


	public int getPageRowCount() {
		return pageRowCount;
	}


	public void setPageRowCount(int pageRowCount) {
		this.pageRowCount = pageRowCount;
	}


	public String getPageUrl() {
		return pageUrl;
	}


	public void setPageUrl(String pageUrl) {
		this.pageUrl = pageUrl;
	}


	public String getContextPath() {
		return contextPath;
	}


	public void setContextPath(String contextPath) {
		this.contextPath = contextPath;
	}


	public int getNumberCount() {
		return numberCount;
	}


	public void setNumberCount(int numberCount) {
		this.numberCount = numberCount;
	}


	public String getPageHtmlStr() {
		return pageHtmlStr;
	}


	public void setPageHtmlStr(String pageHtmlStr) {
		this.pageHtmlStr = pageHtmlStr;
	}


	public String getScriptStr() {
		return scriptStr;
	}


	public void setScriptStr(String scriptStr) {
		this.scriptStr = scriptStr;
	}
	
	
	
	
}
