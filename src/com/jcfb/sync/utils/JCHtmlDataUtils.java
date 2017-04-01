package com.jcfb.sync.utils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.common.CommonUtils;
import com.common.StringUtils;
import com.common.date.DateUtils;
import com.common.number.NumberUtils;
import com.jcfb.entity.JCFootball;




/***
 * 足球竞猜 数据同步 辅助类<p>
 * @author LingMin 
 * @date 2015-02-10<br>
 * @version 1.0<br>
 */
public class JCHtmlDataUtils {

	
	/** 日志书写对象 **/
	protected static org.apache.log4j.Logger logger = org.apache.log4j.Logger.getLogger(JCHtmlDataUtils.class);
	
	/***
	 * 从jc258网站获取 每天足球竞猜数据<p>
	 * @param jcDate 竞猜日期
	 * @return List<JCFootball> 返回相关竞猜数据<p>
	 * @throws Exception 
	 */
	public static List<JCFootball> getJC258URLToJCFootballList(Date jcDate) throws Exception{
		List<JCFootball> jcFootballList = new ArrayList<JCFootball>();
		try {
			Document doc = Jsoup.connect(JC258Utils.getJC258FootballURLValue()).header(JC258Utils.getJC258HTTPHeadKey(), JC258Utils.getJC258HTTPHeadValue()).get();
			//<table id="match_list" class="list match_list_table scrollTable">
			Elements tables = doc.select(JC258Utils.getJC258FootballTableIdValue());//根据table id 查找竞猜数据
			if(CommonUtils.isEmptyList(tables)){
				logger.info("没有找到对应的id【"+JC258Utils.getJC258FootballTableIdValue()+"】");
				return null;
			}
			if(CommonUtils.isEmptyList(tables)){
				logger.info("对应的table id【"+JC258Utils.getJC258FootballTableIdValue()+"】size为0");
				return null;
			}
			Element elementTable = tables.get(0);
			//根据属性配置规则 对竞猜数据进行过滤
			Elements jcDataElements = JCHtmlDataUtils.getJCDataTrElements(elementTable , jcDate);
			 for (int i = 0 ; i < jcDataElements.size() ; i++) {
				 Element dataTrElement = jcDataElements.get(i);
				 JCFootball jcFootball = JCHtmlDataUtils.getDataTrToJCFootball(dataTrElement, i);
				 jcFootballList.add(jcFootball);
			 }
		} catch (IOException e) {
			e.printStackTrace();
			throw e;
		}catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
		return jcFootballList;
	}
	
	
	/***
	 * 根据属性配置规则 对竞猜数据进行过滤<p>
	 * @param elementTable html table对象
	 * @param jcDate 竞猜日期
	 * @return  Elements 返回过滤后的html 对象<p>
	 * @throws Exception 
	 */
	public static Elements getJCDataTrElements(Element elementTable , Date jcDate) throws Exception{
		String filterRulePath = JC258Utils.getJC258FootballTableTrFilterRulePathValue();
		if(StringUtils.isEmpty(filterRulePath)){
			throw new Exception("属性文件【jc258.properties】未配置过滤规则"+JC258Utils.JC258FootballTableTrAttrGameDateKeyName);
		}
		Elements jcDataElements = null;
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
		
		if(CommonUtils.isNotEmptyObject(jcDate)){
			//比赛日期 tr
		    String jcDateStr = DateUtils.formatDateBySpecifiedFormatter(jcDate , DateUtils.DateFormatter);
		    //1、使用 gameDate 这个属性过滤 竞猜日期 有些偏差，匹配开头，如： gameDate = "2015-02-27_033"   
		    //String jcDatePath = "tr[gameDate^=".concat(jcDateStr).concat("]");
		    //2、使用gnum |gtr  ，匹配结尾，gnum="score_datr_2015-02-28" gtr="score_datr_2015-02-28"
		    String jcDatePath = "tr[gnum$=".concat(jcDateStr).concat("]");
			if(CommonUtils.isEmptyObject(jcDataElements)){
				jcDataElements = elementTable.select(jcDatePath);
			}else{
				jcDataElements = jcDataElements.select(jcDatePath);
			}
		}
		return jcDataElements;
	}
	
	/***
	 * 根据html table tr行进行实体封装<p>
	 * @param tr html table数据行对象
	 * @return JCFootball 返回实体对象<p>
	 */
	public static JCFootball getDataTrToJCFootball(Element dataTrElement , int rowIndex){
		if(CommonUtils.isEmptyObject(dataTrElement)){
			return null;
		}
		JCFootball jcFootball = new JCFootball();
		//获取竞猜日期对象
		Date jcDate = getJCDateStrToDate(dataTrElement);
		jcFootball.setJcDate(jcDate);
		Elements tdsElements = dataTrElement.select("td");
		//第一个单元格：周几+编号
		String weekAndNumber = tdsElements.get(0).text();
		String[]array = getSplitNumAndWeek(weekAndNumber);
		jcFootball.setWeek(array[0]);
		jcFootball.setMatchesNo(array[1]);
		//第二个单元格：赛事，联赛名称 如：西甲
		String matchName = tdsElements.get(1).text();
		String matchNameHref = null;
		if(CommonUtils.isNotEmptyList(tdsElements.get(1).select("a"))){
			matchNameHref = tdsElements.get(1).select("a").get(0).attr("href");
		}
		jcFootball.setMatchName(matchName);
		jcFootball.setMatchNameHref(matchNameHref);
		jcFootball.setMatchStyle(tdsElements.get(1).attr("style"));
		jcFootball.setMatchNum(JC258HtmlCommonUtils.getMatchNum(matchNameHref));
		//第三个单元格：截止时间
		String endTime = tdsElements.get(2).text();
		jcFootball.setEndTime(endTime);
		//第四个单元格：主队名称
		String homeTeamSimpleName = tdsElements.get(3).text();
		String homeTeamName = homeTeamSimpleName;
		String homeTeamNameHref = null;
		if(CommonUtils.isNotEmptyList(tdsElements.get(3).select("a"))){
			homeTeamName = tdsElements.get(3).select("a").get(0).attr("title");
			homeTeamNameHref = tdsElements.get(3).select("a").get(0).attr("href");
		}
		
		jcFootball.setHomeTeamSimpleName(homeTeamSimpleName);
		jcFootball.setHomeTeamName(homeTeamName);
		jcFootball.setHomeTeamNameHref(homeTeamNameHref);
		//第五个单元格：客队名称
		String visitingTeamSimpleName = tdsElements.get(4).text();
		String visitingTeamName = visitingTeamSimpleName;
		String visitingTeamNameHref = null;
		if(CommonUtils.isNotEmptyList(tdsElements.get(4).select("a"))){
			visitingTeamName = tdsElements.get(4).select("a").get(0).attr("title");
			visitingTeamNameHref = tdsElements.get(4).select("a").get(0).attr("href");
		}
		jcFootball.setVisitingTeamSimpleName(visitingTeamSimpleName);
		jcFootball.setVisitingTeamName(visitingTeamName);
		jcFootball.setVisitingTeamNameHref(visitingTeamNameHref);
		//第六个单元格：主胜赔率（平均）
		String lordTheAvgOdds = tdsElements.get(5).text();
		jcFootball.setLordAvgOdds(NumberUtils.getStrToBigDecimal(lordTheAvgOdds));
		//第七个单元格：平 赔率（平均）
		String flatAvgOdds = tdsElements.get(6).text();
		jcFootball.setFlatAvgOdds(NumberUtils.getStrToBigDecimal(flatAvgOdds));
		//第八个单元格：客胜赔率（平均）
		String guestWinsTheAvgOdds = tdsElements.get(7).text();
		jcFootball.setNegativeAvgOdds(NumberUtils.getStrToBigDecimal(guestWinsTheAvgOdds));
		//第九个单元格：数据分析[推荐|欧、分析、变]   推荐链接是js动态 不能获取
		String analysisHref = tdsElements.get(8).select("a").get(0).attr("href");//欧
		jcFootball.setAnalysisHref(analysisHref);
		String historyAnalysisHref = tdsElements.get(8).select("a").get(1).attr("href");//历史交锋分析
		jcFootball.setHistoryAnalysisHref(historyAnalysisHref);
		//第十个单元格：空
		//第十一个单元格：主胜赔率
		String lordTheOdds = tdsElements.get(10).text();
		//表示该场球未开胜平负 玩法，只能让球
		if(!JC258Utils.JC258FootballNOLordFlatNegative.equals(lordTheOdds)){
			//logger.info("lordTheOdds="+lordTheOdds);
			jcFootball.setLordOdds(NumberUtils.getStrToBigDecimal(lordTheOdds));
			//第十二个单元格：平 赔率
			String flatTheOdds = tdsElements.get(11).text();
			jcFootball.setFlatOdds(NumberUtils.getStrToBigDecimal(flatTheOdds));
			//第十三单元格：客胜赔率
			String guestWinsTheOdds = tdsElements.get(12).text();
			jcFootball.setNegativeOdds(NumberUtils.getStrToBigDecimal(guestWinsTheOdds));
			//是否 未开售胜平负玩法
			jcFootball.setIsNotSaleSPF(false);
		}else{
			//是否 未开售胜平负玩法
			jcFootball.setIsNotSaleSPF(true);
		}
		
		Element letTheBallTrElement = dataTrElement.nextElementSibling();//当前tr的下一个兄弟tr行对象，即让球行数据
		//获取让球数据行 id
		jcFootball.setLetDataGameid(JCHtmlDataUtils.getLetBallGameId(letTheBallTrElement));
		//第一个单元格：让球数
		String letTheBallNum = letTheBallTrElement.getElementsByTag("td").get(0).text();
		jcFootball.setLetBall(NumberUtils.getStrToBigDecimal(letTheBallNum));
		//第二个单元格：让球胜 赔率
		String lordLetOdds = letTheBallTrElement.getElementsByTag("td").get(1).text();
		jcFootball.setLordLetOdds(NumberUtils.getStrToBigDecimal(lordLetOdds));
		//第三个单元格：让球平 赔率
		String flatLetOdds = letTheBallTrElement.getElementsByTag("td").get(2).text();
		jcFootball.setFlatLetOdds(NumberUtils.getStrToBigDecimal(flatLetOdds));
		//第四个单元格：让球 客胜 赔率
		String negativeLetOdds = letTheBallTrElement.getElementsByTag("td").get(3).text();
		jcFootball.setNegativeLetOdds(NumberUtils.getStrToBigDecimal(negativeLetOdds));
		
		
		//设置创建时间、修改时间
		jcFootball.setCreateTime(new Date());
		jcFootball.setModifyTime(new Date());
		/***竞猜258网站系统相关信息，即：竞猜table tr行属性*****/
		String theIndex = dataTrElement.attr("theIndex");
		jcFootball.setTheIndex(theIndex != null ? Integer.valueOf(theIndex) : null);
		jcFootball.setGn(dataTrElement.attr("gn"));
		jcFootball.setGameno(dataTrElement.attr("gameno"));
		jcFootball.setGameDate(dataTrElement.attr("gameDate"));
		jcFootball.setGnum(dataTrElement.attr("gnum"));
		jcFootball.setGtr(dataTrElement.attr("gtr"));
		jcFootball.setStatus(dataTrElement.attr("status"));
		jcFootball.setTimenum(dataTrElement.attr("timenum"));
		jcFootball.setDataGamecode(dataTrElement.attr("data-gamecode"));
		jcFootball.setDataGameid(dataTrElement.attr("data-gameid"));
		jcFootball.setDataIsturn(dataTrElement.attr("data-isturn"));
		
		return jcFootball;
	}
	
	
	/***
	 * 获取让球数据行 的 唯一标识id id="tr_425488" <p>
	 * @param letTheBallTrElement 让球tr 数据行对象
	 * @return String 返回让球唯一标识id<p>
	 */
	private static String getLetBallGameId(Element letTheBallTrElement){
		/***
		 * <tr id="tr_425488" gnum="score_datr_2015-02-26" theIndex="1" gn="4053" name="sfp"  class="odds odd_tr_0" data-gamecode='64004' data-gameid="425476" style="display: none;">
		 */
		if(CommonUtils.isEmptyObject(letTheBallTrElement)){
			return null;
		}
		String letDataGameid = letTheBallTrElement.attr("id");//让球数据id
		if(StringUtils.isNotEmpty(letDataGameid)){
			letDataGameid = letDataGameid.replace("tr_", "");
		}
		return letDataGameid;
	}
	
	/***
	 * 截取分割  场次 和 星期几<p>
	 * @param str 字符串
	 * @return String[]返回数组[周几、场次] <p>
	 */
	public static String[] getSplitNumAndWeek(String str){
		if(StringUtils.isNotEmpty(str)){
			String week = str.substring(0, 2);
			String number =  str.substring(2);
			return new String[]{week , number};
		}
		return new String[2];
	}
	
	
	/***
	 * 获取 tr属性标识 竞猜日期 如：gameDate = "2015-02-25_051" <p>
	 * @param dataTrElement 竞猜数据tr行对象
	 * @return  String 返回竞猜日期字符串<p>
	 */
	private static String getJCDateStr(Element dataTrElement){
		// 1、存在差异gameDate  tr日期标识 属性,如：gameDate = "2015-02-25_051"   
		/*String gameDate = dataTrElement.attr("gameDate");
		if(StringUtils.isNotEmpty(gameDate)){
			gameDate = gameDate.substring(0 , 11);
		}*/
		//2、获取 该属性gnum="score_datr_2015-02-28" gtr="score_datr_2015-02-28"
		String gnum = dataTrElement.attr("gnum");
		if(StringUtils.isNotEmpty(gnum)){
			gnum = gnum.substring(gnum.length() - 10);
		}
		return gnum;
	}
	
	
	/***
	 * 获取 tr属性标识 竞猜日期 如：gameDate = "2015-02-25_051" <p>
	 * @param dataTrElement 竞猜数据tr行对象
	 * @return  String 返回竞猜日期Date 对象<p>
	 */
	private static Date getJCDateStrToDate(Element dataTrElement){
		String dateStr = getJCDateStr(dataTrElement);
		if(StringUtils.isNotEmpty(dateStr)){
			return DateUtils.getDateFromSpecifiedString(dateStr , DateUtils.DateFormatter);
		}
		return null;
	}
	
	/***
	 * 测试打印相关数据显示方法<p>
	 *  <p>
	 * void
	 */
	private static void displayJCFootballList(List<JCFootball> list){
		if(CommonUtils.isEmptyList(list)){
			logger.info("没有数据!!");
		}
		logger.info("共数据=size="+list.size());
		for(JCFootball temp : list){
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
			msg.append(temp.getHomeTeamSimpleName());
			msg.append("\t");
			msg.append(temp.getHomeTeamNameHref());
			msg.append("\t");
			msg.append(temp.getVisitingTeamName());
			msg.append("\t");
			msg.append(temp.getVisitingTeamSimpleName());
			msg.append("\t");
			msg.append(temp.getVisitingTeamNameHref());
			msg.append("\t");
			msg.append(temp.getLordAvgOdds());
			msg.append("\t");
			msg.append(temp.getFlatAvgOdds());
			msg.append("\t");
			msg.append(temp.getNegativeAvgOdds());
			msg.append("\t");
			msg.append(temp.getLordOdds());
			msg.append("\t");
			msg.append(temp.getFlatOdds());
			msg.append("\t");
			msg.append(temp.getNegativeOdds());
			msg.append("\t");
			msg.append(temp.getLetBall());
			msg.append("\t");
			msg.append(temp.getLordLetOdds());
			msg.append("\t");
			msg.append(temp.getFlatLetOdds());
			msg.append("\t");
			msg.append(temp.getNegativeLetOdds());
			msg.append("\t");
			msg.append(temp.getAnalysisHref());
			msg.append("\t");
			msg.append(temp.getHistoryAnalysisHref());
			msg.append("\t");
			
			/***竞猜table tr行属性  theIndex****/
			msg.append(temp.getTheIndex());
			msg.append("\t");
			msg.append(temp.getGn());
			msg.append("\t");
			msg.append(temp.getGameno());
			msg.append("\t");
			msg.append(temp.getGameDate());
			msg.append("\t");
			msg.append(temp.getGnum());
			msg.append("\t");
			msg.append(temp.getGtr());
			msg.append("\t");
			msg.append(temp.getStatus());
			msg.append("\t");
			msg.append(temp.getTimenum());
			msg.append("\t");
			msg.append(temp.getDataGamecode());
			msg.append("\t");
			msg.append(temp.getDataGameid());
			msg.append("\t");
			msg.append(temp.getDataIsturn());
			msg.append("\t");
			msg.append(temp.getLetDataGameid());
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
		//获取数据
//		List<JCFootball> jcFootballList;
//		try {
//			String dateStr = "2015-02-27";
//			Date jcDate =  DateUtils.getDateFromSpecifiedString(dateStr , DateUtils.DateFormatter);
//			jcFootballList = JCHtmlDataUtils.getJC258URLToJCFootballList(jcDate);
//			//打印数据
//			JCHtmlDataUtils.displayJCFootballList(jcFootballList);
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
		
//		String str = "score_datr_2015-02-28";
//		str = str.substring(str.length() - 10);
//		logger.info("str="+str);
		
		String str1 = "http://data.jc258.cn/league/home/140";
		str1 = str1.substring(str1.lastIndexOf("/")+1);
		logger.info("str1="+str1);
	}
	
	
}
