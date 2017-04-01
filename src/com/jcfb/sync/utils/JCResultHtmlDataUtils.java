/**
 * &lt;p&gt;
 * copyright &amp;copy; together 2014, all rights reserved.
 * @author admin
 * @version $Id$
 * @since 1.0
 * 
 */
package com.jcfb.sync.utils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.common.CommonUtils;
import com.common.StringUtils;
import com.common.date.DateUtils;
import com.common.number.NumberUtils;
import com.jcfb.entity.JCFootballResult;

/**
 * 足球竞猜 结果 数据同步 <p>
 * @author LingMin 
 * @date 2015-2-26<br>
 * @version 1.0<br>
 */
public class JCResultHtmlDataUtils {

	/** 日志书写对象 **/
	protected static org.apache.log4j.Logger logger = org.apache.log4j.Logger.getLogger(JCResultHtmlDataUtils.class);
	
	
	/***
	 * 从jc258网站获取 每天足球竞猜 结果数据<p>
	 * @param jcDate 同步数据日期对象
	 * @return List<JCFootballResult> 返回相关竞猜结果数据<p>
	 * @throws Exception 
	 */
	public static List<JCFootballResult> getJC258URLToJCFootballResultList(Date jcDate) throws Exception{
		List<JCFootballResult> jcFootballResultList = new ArrayList<JCFootballResult>();
		try {
			String url = JC258Utils.getJC258FootballResultURLValue();
			logger.info("url="+url);
			Document doc = Jsoup.connect(url).header(JC258Utils.getJC258HTTPHeadKey(), JC258Utils.getJC258HTTPHeadValue()).get();
			//<table id="match_list" class="list match_list_table scrollTable">
			Elements tables = doc.select(JC258Utils.getJC258FootballResultTableIdValue());//根据table id 查找竞猜数据
			if(CommonUtils.isEmptyList(tables)){
				logger.info("没有找到对应的id【"+JC258Utils.getJC258FootballResultTableIdValue()+"】");
				return null;
			}
			if(CommonUtils.isEmptyList(tables)){
				logger.info("对应的table id【"+JC258Utils.getJC258FootballResultTableIdValue()+"】size为0");
				return null;
			}
			Element elementTable = tables.get(0);
			//根据属性配置规则 对竞猜数据进行过滤
			Elements jcDataElements = JCResultHtmlDataUtils.getJCResultDataTrElements(elementTable , jcDate);
			 for (int i = 0 ; i < jcDataElements.size() ; i++) {
				 Element dataTrElement = jcDataElements.get(i);
				 JCFootballResult jcFootballResult = JCResultHtmlDataUtils.getJCFootballResult(dataTrElement , i);
				 jcFootballResultList.add(jcFootballResult);
			 }
		} catch (IOException e) {
			e.printStackTrace();
			throw e;
		}catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
		return jcFootballResultList;
	}
	
	
	/***
	 * 根据属性配置规则 对竞猜结果数据进行过滤<p>
	 * @param elementTable html table对象
	 * @param jcDate 竞猜日期
	 * @return  Elements 返回过滤后的html 对象<p>
	 * @throws Exception 
	 */
	public static Elements getJCResultDataTrElements(Element elementTable , Date jcDate) throws Exception{
		//从配置文件获取过滤规则
		String filterRulePath = JC258Utils.getJC258FootballResultTableTrFilterRulePathValue();
		Elements jcDataElements = null;
		if(StringUtils.isNotEmpty(filterRulePath)){
			int index = 0;
			//分割过滤规则
			String[] filterRulePathArray = filterRulePath.split(JC258Utils.JC258FootballTableTrFilterRulePathSplitChar);
			for(String temp : filterRulePathArray){
				if(index == 0){
					jcDataElements = elementTable.select(temp);
				}else{
					jcDataElements = jcDataElements.select(temp);
				}
				index++;
			}
		}
		if(CommonUtils.isNotEmptyObject(jcDate)){
			//比赛日期 tr
		    String jcDateStr = DateUtils.formatDateBySpecifiedFormatter(jcDate , DateUtils.DateFormatter2);
		    String jcDatePath = "tr[gamedate=".concat(jcDateStr).concat("]");
			if(CommonUtils.isEmptyObject(jcDataElements)){
				jcDataElements = elementTable.select(jcDatePath);
			}else{
				jcDataElements = jcDataElements.select(jcDatePath);
			}
		}
		if(CommonUtils.isEmptyObject(jcDataElements)){
			jcDataElements = elementTable.select("tr");
		}
		return jcDataElements;
	}
	
	/***
	 * 根据html table tr行进行实体封装<p>
	 * @param dataTrElement html tr行对象
	 * @param rowIndex 行索引
	 * @return JCFootballResult 返回实体对象<p>
	 */
	public static JCFootballResult getJCFootballResult(Element dataTrElement , int rowIndex){
		if(CommonUtils.isEmptyObject(dataTrElement)){
			return null;
		}
		JCFootballResult jcFootballResult = new JCFootballResult();
		//竞猜日期
		jcFootballResult.setJcDate(getJCDateStrToDate(dataTrElement));
		//场次编号
		Elements tdsElements = dataTrElement.select("td");
		//第一个单元格：周几+编号
		String weekAndNumber = tdsElements.get(0).text();
		String[]array = JCHtmlDataUtils.getSplitNumAndWeek(weekAndNumber);
		jcFootballResult.setWeek(array[0]);
		jcFootballResult.setMatchesNo(array[1]);
		//第二个单元格：赛事，联赛名称 如：西甲
		jcFootballResult.setMatchName(tdsElements.get(1).text());
		String matchNameHref = null;
		if(CommonUtils.isNotEmptyList(tdsElements)){
			List temp = tdsElements.get(1).select("a");
			if(CommonUtils.isNotEmptyList(temp)){
				matchNameHref = tdsElements.get(1).select("a").get(0).attr("href");
			}
		}
		jcFootballResult.setMatchNameHref(matchNameHref);
		jcFootballResult.setMatchStyle(tdsElements.get(1).attr("style"));
		jcFootballResult.setMatchNum(JC258HtmlCommonUtils.getMatchNum(matchNameHref));
		//第三个单元格：比赛时间
		jcFootballResult.setMatchDateTime(tdsElements.get(2).text());
		//第四个单元格：主客队名称+让球，如：圣保罗 (-1) VS 达努比奥
		Object[] tempArray = getTeamNameAndLetBallArray(tdsElements.get(3));
		jcFootballResult.setHomeTeamName((String)tempArray[0]);
		jcFootballResult.setVisitingTeamName((String)tempArray[1]);
		jcFootballResult.setLetBall((Integer)tempArray[2]);
		jcFootballResult.setHistoryAnalysisHref((String)tempArray[3]);
		//第五个单元格：半场比分
		jcFootballResult.setHalfCourtScore(tdsElements.get(4).text());
		//第六个单元格：全场比分
		jcFootballResult.setFullCourtScore(tdsElements.get(5).text());
		//第7个单元格：胜平 负 【彩果】
		jcFootballResult.setResult(tdsElements.get(6).text());
		//第8个单元格：胜平 负 【过关终赔】
		jcFootballResult.setResultOdds(NumberUtils.getStrToBigDecimal(tdsElements.get(7).text()));
		//第9个单元格：让球胜平负 【彩果】
		jcFootballResult.setLetResult(tdsElements.get(8).text());
		//第10个单元格：让球胜平负 【过关终赔】
		jcFootballResult.setLetResultOdds(NumberUtils.getStrToBigDecimal(tdsElements.get(9).text()));
		//第11个单元格：总进球
		jcFootballResult.setGoalNumStr(tdsElements.get(10).text());
		jcFootballResult.setGoalNum(getGoalNum(tdsElements.get(10)));
		//第12个单元格：总进球【过关终赔】
		jcFootballResult.setGoalOdds(NumberUtils.getStrToBigDecimal(tdsElements.get(11).text()));
		//第13个单元格：比分 【彩果】
		jcFootballResult.setScoreResult(tdsElements.get(12).text());
		//第14个单元格：比分【过关终赔】
		jcFootballResult.setScoreOdds(NumberUtils.getStrToBigDecimal(tdsElements.get(13).text()));
		//第15个单元格：半全场胜平负【彩果】
		jcFootballResult.setHalfFullResult(tdsElements.get(14).text());
		//第16个单元格：半全场胜平负【过关终赔】
		jcFootballResult.setHalfFullOdds(NumberUtils.getStrToBigDecimal(tdsElements.get(15).text()));
		//第17个单元格 详情链接
		
		/***竞猜258网站系统相关信息，即：竞猜table tr行属性*****/
		jcFootballResult.setGameid(dataTrElement.attr("gameid"));
		jcFootballResult.setGamenum(dataTrElement.attr("gamenum"));
		jcFootballResult.setGamedate(dataTrElement.attr("gamedate"));
		
		//设置创建时间、修改时间
		jcFootballResult.setCreateTime(new Date());
		jcFootballResult.setModifyTime(new Date());
		
		return jcFootballResult;
	}
	
	
	
	/***
	 * 解析 主客队名称 及让球 单元格对象【主队名称、客队名称、让球数、历史交锋分析url地址】<p>
	 * @param dataTdElement td单元格对象
	 * @return Object[] 返回数组   【主队名称、客队名称、让球数、历史交锋分析url地址】<p>
	 */
	private static Object[] getTeamNameAndLetBallArray(Element dataTdElement){
		/***
		 * <td><a href="http://data.jc258.cn/battle/425552/0" target="_blank"> 
		 * 苏克雷大学<span style="color:#00f">(1)</span>VS克鲁塞罗</a></td>
		 */
		if(CommonUtils.isEmptyObject(dataTdElement)){
			return new Object[3];
		}
		String homeTeamName = null;
		String visitingTeamName = null;
		//主客队名称解析  如：苏克雷大学(1)VS克鲁塞罗
		String teamNames = dataTdElement.getElementsByTag("a").get(0).text();
		String historyAnalysisHref = dataTdElement.getElementsByTag("a").get(0).attr("href");
		if(StringUtils.isNotEmpty(teamNames)){
			String[] tempArray = teamNames.split(JC258Utils.JC258FootballTableResultTeamNameSplitChar);
			String temp = tempArray[0].trim();
			int index = temp.lastIndexOf("-1");
			int beginIndex = temp.length() - 3;//起始位置
			int endIndex = temp.length() - 3 + 1;//结束位置
			String str = temp.substring(beginIndex , endIndex);
			if("(".equals(str)){
				homeTeamName = temp.substring(0 , endIndex - 1);
			}else{
				homeTeamName = temp.substring(0 , endIndex - 2);
			}
			homeTeamName = homeTeamName.trim();
			visitingTeamName = tempArray[1].trim();
		}
		//让球数据解析
		Integer letBall = 0;
		String letBallStr = dataTdElement.getElementsByTag("span").get(0).text();
		if(StringUtils.isNotEmpty(letBallStr)){
			letBallStr = letBallStr.replaceAll("[()]", "");
			letBall = Integer.valueOf(letBallStr);
		}
		return new Object[]{homeTeamName , visitingTeamName ,  letBall , historyAnalysisHref};
	}
	
	/****
	 * 解析总进球数量，由于进球数 有特殊字符，如：7+<p>
	 * @param tdElement 进球数单元格
	 * @return Integer 返回进球数量<p>
	 * 
	 */
	private static Integer getGoalNum(Element tdElement){
		if(CommonUtils.isEmptyObject(tdElement)){
			return null;
		}
		String goalNumStr = tdElement.text();
		if(StringUtils.isNotEmpty(goalNumStr)){
			//判断是否有特殊字符，将特殊字符替换为空
			goalNumStr = Pattern.compile("[^0-9]").matcher(goalNumStr).replaceAll("");
			return NumberUtils.getStrToInteger(goalNumStr);
		}
		return 0;
	}
	
	
	

	/***
	 * 获取 tr属性标识 竞猜日期 如：gameDate = "2015-02-25_051" <p>
	 * @param dataTrElement 竞猜数据tr行对象
	 * @return  String 返回竞猜日期Date 对象<p>
	 */
	private static Date getJCDateStrToDate(Element dataTrElement){
		String dateStr = getJCDateStr(dataTrElement);
		if(StringUtils.isNotEmpty(dateStr)){
			return DateUtils.getDateFromSpecifiedString(dateStr , DateUtils.DateFormatter2);
		}
		return null;
	}
	
	
	/***
	 * 获取 tr属性标识 竞猜日期 如：gamedate="20150225" <p>
	 * @param dataTrElement 竞猜数据tr行对象
	 * @return  String 返回竞猜日期字符串<p>
	 */
	private static String getJCDateStr(Element dataTrElement){
		//gameDate  tr日期标识 属性,如：gamedate="20150225" 
		return dataTrElement.attr("gameDate");
	}
	
	
	private static void displayJCFootballResultList(List<JCFootballResult> list){
		if(CommonUtils.isEmptyList(list)){
			logger.info("没有数据");
			return ;
		}
		logger.info("共数据=size="+list.size());
		for(JCFootballResult temp : list){
			StringBuffer msg = new StringBuffer();
			msg.append(DateUtils.formatDateBySpecifiedFormatter(temp.getJcDate(), DateUtils.DateFormatter));
			msg.append("\t");
			msg.append(temp.getWeek());
			msg.append("\t");
			msg.append(temp.getMatchesNo());
			msg.append("\t");
			msg.append(temp.getMatchName());
			msg.append("\t");
			msg.append(temp.getMatchNameHref());
			msg.append("\t");
			msg.append(temp.getHomeTeamName());
			msg.append("\t");
			msg.append(temp.getLetBall());
			msg.append("\t");
			msg.append(temp.getVisitingTeamName());
			msg.append("\t");
			msg.append(temp.getMatchDateTime());
			msg.append("\t");
			msg.append(temp.getHalfCourtScore());
			msg.append("\t");
			msg.append(temp.getFullCourtScore());
			msg.append("\t");
			msg.append(temp.getResult());
			msg.append("\t");
			msg.append(temp.getResultOdds());
			msg.append("\t");
			msg.append(temp.getLetResult());
			msg.append("\t");
			msg.append(temp.getLetResultOdds());
			msg.append("\t");
			msg.append(temp.getGoalNum());
			msg.append("\t");
			msg.append(temp.getGoalOdds());
			msg.append("\t");
			msg.append(temp.getScoreResult());
			msg.append("\t");
			msg.append(temp.getScoreOdds());
			msg.append("\t");
			msg.append(temp.getHalfFullResult());
			msg.append("\t");
			msg.append(temp.getHalfFullOdds());
			msg.append("\t");
			msg.append(temp.getGameid());
			msg.append("\t");
			msg.append(temp.getGamenum());
			msg.append("\t");
			msg.append(temp.getGamedate());
			msg.append("\t");
			
			logger.info(msg.toString());
		}
		
		
	}
	
	
	
	
	/***
	 * 测试方法<p>
	 * @param args <p>
	 * void
	 */
	public static void main(String[] args) {
		
		try {
			String dateStr = "2015-02-26";
			Date jcDate = DateUtils.getDateFromSpecifiedString(dateStr , DateUtils.DateFormatter);
			List list = JCResultHtmlDataUtils.getJC258URLToJCFootballResultList(jcDate);
			JCResultHtmlDataUtils.displayJCFootballResultList(list);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
//		String str = "苏克雷大学(-1)";
//		int index = str.lastIndexOf("-1");
//		String str2 = str.substring(str.length() - 3 , str.length() - 3 + 1);
//		System.out.println("str2="+str2+" \t index="+index);
//		//str = str.replace("(-1)", "");
//		System.out.println("str="+str+" \t index="+index);
		
//		String phoneString = "7+";
//		String str = Pattern.compile("[^0-9]").matcher(phoneString).replaceAll("");
//		System.out.println("str="+str);
		
		  // 提取张三 去除数字
//        String r_name3 = "张三 13599998888 000000";
//        Pattern pattern = Pattern.compile("[\\d]");
//        Matcher matcher = pattern.matcher(r_name3);
//        System.out.println(matcher.replaceAll("").trim());
	}
}
