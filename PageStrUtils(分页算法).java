/**
 * 文件名称：PageStrUtils.java
 * 版本：1.0
 * JDK版本：1.6
 * 创建日期：2012-5-30
 */
package com.avic.system.utils;

/**
 * 构建分页字符串 方法 <p>
 * @author LingMin  <br>
 * @version 1.0   <br>
 * @created 2012-5-30下午02:58:14<br>
 */
public class PageStrUtils {

	/**起始行*/
	protected int startRow = 0;
	/***当前页面**/
	protected int currentPage = 1;
	/***总共页数***/
	protected int pageCount = 0;
	/**总行数**/
	protected int rowCount = 0;
	/**每页大小**/
	protected int pageRowCount = 10;
	/**分页html字符串**/
	protected String pageHtmlStr;
	/**提交url地址**/
	protected String url;
	/***web 上下文路径**/
	protected String contextPath;
	/***页面显示 个数***/
	protected int numberCount = 5;
	/***
	 * 构造方法
	 */
	public PageStrUtils(){
	}
	
	/***
	 * 构造方法
	 * @param currentPage 当前页面
	 * @param pageRowCount 每页大小
	 * @param rowCount 总行数
	 */
	public PageStrUtils(int rowCount ,int pageRowCount, String url , String contextPath){
		this.pageRowCount = pageRowCount;
		this.rowCount = rowCount;
		this.url = url;
		this.contextPath = contextPath;
		
		this.initCalculateHtmlStr();
	}

	/***
	 * 构造方法
	 * @param currentPage 当前页面
	 * @param pageRowCount 每页大小
	 * @param rowCount 总行数
	 * @param url 分页url路径
	 * @param contextPath上下文路径
	 */
	public PageStrUtils(int currentPage , int pageRowCount , int rowCount , String url , String contextPath){
		this.currentPage = currentPage;
		this.pageRowCount = pageRowCount;
		this.rowCount = rowCount;
		this.url = url;
		this.contextPath = contextPath;
		this.initCalculateHtmlStr();
	}
	
	/***
	 * 获取计算 起始行<p>
	 * @return
	 * int
	 */
	public int getCalculateSartRow(){
		this.initStartRow();
		return this.getStartRow();
	}
	
	
	/***
	 * 获取计算起始行  拼接分页字符串<p>
	 * @return
	 * int
	 */
	public void initCalculateHtmlStr(){
		this.initStartRow();
		this.initPageCount();
		this.initPageHtmlStr();
	}
	
	/**
	 * 初始化 及计算 属性值 <p>
	 * 
	 * void
	 */
	private void initStartRow(){
		//起始页  =  (当前页 -1) *每页 大小
		startRow = (currentPage -1)*pageRowCount;
	}
	
	
	
	/***
	 * 初始化 总页数<p>
	 * 
	 * void
	 */
	private void initPageCount(){
		pageCount = (int)Math.ceil((double)rowCount/pageRowCount);//计算总页数=总数量 /每页行数   取整数(大)
	}
	
	/***
	 * 初始化 分页html字符串<p>
	 * 
	 * void
	 */
	private void initPageHtmlStr(){
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
			htmlStr.append(this.url);
			htmlStr.append("?page.currentPage=1");
			htmlStr.append("'>");
			htmlStr.append("<img alt='' src='");
			htmlStr.append(contextPath);
			htmlStr.append("/images/x1.png'  title='第一页'>");
			htmlStr.append("</a></li>");
			
			htmlStr.append("<li><a href='");
			htmlStr.append(this.url);
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
				htmlStr.append(this.url);
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
			htmlStr.append(this.url);
			htmlStr.append("?page.currentPage=");
			htmlStr.append(this.currentPage + 1);
			htmlStr.append("'>");
			htmlStr.append("<img alt='' src='");
			htmlStr.append(contextPath);
			htmlStr.append("/images/x3.png'  title='下一页'>");
			htmlStr.append("</a></li>");
			
			htmlStr.append("<li><a href='");
			htmlStr.append(this.url);
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
		htmlStr.append(this.url);
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
	
	/******************************
	 *   第一种：分页算法 刘凤勇 提供
	 * //大分页算法
	 	int index = 0;
		int a = currentPage / numberCount;
		int b = currentPage % numberCount;
		//循环中间页码
		for(int i = 1 ; i <= 5; i++){
			//<li class="djl">1</li><li class="mdj">2</li><li class="mdj">3</li><li class="mdj">4</li>
			if (index < this.pageCount) {
				if (b > 0 && a > 0) {
					index = (a + 1) * numberCount + i;
				} else {
					index = a * numberCount + i;
				}
			}else{
				break;
			}
			if(this.currentPage == index){
				htmlStr.append("<li class='djl'>");
				htmlStr.append(index);
				htmlStr.append("</li>");
			}else{
				htmlStr.append("<li class='mdj'>");
				htmlStr.append("<a href='");
				htmlStr.append(this.url);
				htmlStr.append("?page.currentPage=");
				htmlStr.append(index);
				htmlStr.append("'>");
				htmlStr.append(index);
				htmlStr.append("</a></li>");
			}
		}
	 * 
	 */
	
	
	
	/**
	 * 获取起始行<p>
	 * @return  startRow 类型:int<br>
	 */
	public int getStartRow() {
		return startRow;
	}

	/**
	 * 设置起始行<p>
	 * @param  startRow   类型：int<br>
	 */
	public void setStartRow(int startRow) {
		this.startRow = startRow;
	}

	/**
	 * 获取pageHtmlStr<p>
	 * @return  pageHtmlStr 类型:String<br>
	 */
	public String getPageHtmlStr() {
		return pageHtmlStr;
	}

	/**
	 * 设置pageHtmlStr<p>
	 * @param  pageHtmlStr   类型：String<br>
	 */
	public void setPageHtmlStr(String pageHtmlStr) {
		this.pageHtmlStr = pageHtmlStr;
	}

	/**
	 * 获取当前页面<p>
	 * @return  currentPage 类型:int<br>
	 */
	public int getCurrentPage() {
		return currentPage;
	}

	/**
	 * 设置当前页面<p>
	 * @param  currentPage   类型：int<br>
	 */
	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}

	/**
	 * 获取总共页数<p>
	 * @return  pageCount 类型:int<br>
	 */
	public int getPageCount() {
		return pageCount;
	}

	/**
	 * 设置总共页数<p>
	 * @param  pageCount   类型：int<br>
	 */
	public void setPageCount(int pageCount) {
		this.pageCount = pageCount;
	}

	/**
	 * 获取总行数<p>
	 * @return  rowCount 类型:int<br>
	 */
	public int getRowCount() {
		return rowCount;
	}

	/**
	 * 设置总行数<p>
	 * @param  rowCount   类型：int<br>
	 */
	public void setRowCount(int rowCount) {
		this.rowCount = rowCount;
	}

	/**
	 * 获取每页大小<p>
	 * @return  pageRowCount 类型:int<br>
	 */
	public int getPageRowCount() {
		return pageRowCount;
	}

	/**
	 * 设置每页大小<p>
	 * @param  pageRowCount   类型：int<br>
	 */
	public void setPageRowCount(int pageRowCount) {
		this.pageRowCount = pageRowCount;
	}

	/**
	 * 获取提交url地址<p>
	 * @return  提交url地址 类型:String<br>
	 */
	public String getUrl() {
		return url;
	}

	/**
	 * 设置提交url地址<p>
	 * @param  提交url地址   类型：String<br>
	 */
	public void setUrl(String url) {
		this.url = url;
	}

	/**
	 * 获取web上下文路径<p>
	 * @return  contextPath 类型:String<br>
	 */
	public String getContextPath() {
		return contextPath;
	}

	/**
	 * 设置web上下文路径<p>
	 * @param  contextPath   类型：String<br>
	 */
	public void setContextPath(String contextPath) {
		this.contextPath = contextPath;
	}

	/**
	 * 获取页面显示个数<p>
	 * @return  numberCount 类型:int<br>
	 */
	public int getNumberCount() {
		return numberCount;
	}

	/**
	 * 设置页面显示个数<p>
	 * @param  numberCount   类型：int<br>
	 */
	public void setNumberCount(int numberCount) {
		this.numberCount = numberCount;
	}
	
	
	
	
}
