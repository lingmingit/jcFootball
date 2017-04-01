package com.jcfb.entity;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import com.common.CommonUtils;
import com.jcfb.entity.base.BaseCore;



/** 
 * 足彩竞猜 赛事表<p>
 * @author LingMin 
 * @date 2015-2-9<br>
 * @version 1.0<br>
 */
@Entity @Table(name = "t_jc_football")
public class JCFootball extends BaseCore {
	
	/***竞猜日期***/
	@Column @Temporal(TemporalType.DATE)
	private Date jcDate;
	
	/***竞猜日期 字符串***/
	@Column(length=20)
	private String jcDateStr;
	
	/***场次编号**/
	@Column(length=10)
	private String matchesNo;
	/**星期**/
	@Column(length=6)
	private String week;
	/**赛事**/
	@Column(length=15)
	private String matchName;
	/**赛事 编码**/
	@Column(length=15)
	private String matchNum;
	/**赛事 链接url 地址**/
	@Column(length=150)
	private String matchNameHref;
	/**赛事 样式 **/
	@Column(length=350)
	private String matchStyle;
	/**截止时间*****/
	@Column(length=10)
	private String endTime;
	
	/**主队名称 简称*****/
	@Column(length=50)
	private String homeTeamSimpleName;
	/**主队名称*****/
	@Column(length=50)
	private String homeTeamName;
	/**主队名称 258网站链接*****/
	@Column(length=150)
	private String homeTeamNameHref;
	
	/**客队名称*****/
	@Column(length=50)
	private String visitingTeamName;
	/**客队名称 简称*****/
	@Column(length=50)
	private String visitingTeamSimpleName;
	/**客队名称 258网站链接*****/
	@Column(length=150)
	private String visitingTeamNameHref;
	
	
	/**主胜 平均赔率  Average*****/
	@Column(precision=20, scale=8)
	private BigDecimal lordAvgOdds;
	/** 平 平均赔率  Average*****/
	@Column(precision=20, scale=8)
	private BigDecimal flatAvgOdds;
	/**主负 平均赔率  Average*****/
	@Column(precision=20, scale=8)
	private BigDecimal negativeAvgOdds;
	
	/**主胜 赔率  *****/
	@Column(precision=20, scale=8)
	private BigDecimal lordOdds;
	/** 平 赔率  *****/
	@Column(precision=20, scale=8)
	private BigDecimal flatOdds;
	/**主负 赔率  *****/
	@Column(precision=20, scale=8)
	private BigDecimal negativeOdds;
	
	/**让球  *****/
	@Column(precision=20, scale=8)
	private BigDecimal letBall;
	
	/**让球  主胜 赔率  *****/
	@Column(precision=20, scale=8)
	private BigDecimal lordLetOdds;
	/**让球 平 赔率  *****/
	@Column(precision=20, scale=8)
	private BigDecimal flatLetOdds;
	/**让球主负 赔率  *****/
	@Column(precision=20, scale=8)
	private BigDecimal negativeLetOdds;
	/**是否 未开售胜平负玩法  *****/
	@Column
	private Boolean isNotSaleSPF;
	
	
	/**258 网站 推荐 url地址 **/
	@Column(length=150)
	private String nominateHref;
	/**258 网站 欧盘 分析 url地址 **/
	@Column(length=150)
	private String analysisHref;
	/**258 网站两队 历史交战记录分析 url地址 **/
	@Column(length=150)
	private String historyAnalysisHref;
	
	/**赔率保留的小数位**/
	@Transient
	private final int oddsScale = 2;
	
	/***竞猜258网站系统相关信息
	 * <tr id="tr_425476" theIndex="1" gn="4053" gameno="4053" class="odds odd_tr_0" gameDate = "2015-02-26_053" 
       gnum="score_datr_2015-02-26" gtr="score_datr_2015-02-26" status="2" timenum="201502262355" name="sfpnon" 
       data-gamecode='64004' data-gameid="425476" data-isturn="0">
	 * ******/
	/***竞猜table tr行属性  theIndex****/
	@Column
	private Integer theIndex;
	/***竞猜table tr行属性  gn****/
	@Column(length=10)
	private String gn;
	/****竞猜table tr行属性  gameno*****/
	@Column(length=10)
	private String gameno;
	/****竞猜table tr行属性  gameDate*****/
	@Column(length=20)
	private String gameDate;
	/****竞猜table tr行属性  gnum*****/
	@Column(length=30)
	private String gnum;
	/****竞猜table tr行属性  gtr*****/
	@Column(length=30)
	private String gtr;
	/****竞猜table tr行属性  status*****/
	@Column(length=5)
	private String status;
	/****竞猜table tr行属性  timenum*****/
	@Column(length=20)
	private String timenum;
	/****竞猜table tr行属性  data-gamecode*****/
	@Column(length=25)
	private String dataGamecode;
	/****竞猜table tr行属性 data-gameid  【应该是竞猜数据唯一标识id 】*****/
	@Column(length=25)
	private String dataGameid;
	/****竞猜table tr行属性 data-isturn *****/
	@Column(length=5)
	private String dataIsturn;
	/****竞猜table 让球tr行属性 id="tr_425502"  【应该是竞猜数据唯一标识id 】*****/
	@Column(length=25)
	private String letDataGameid;
	
	/**
	 * 获取竞猜日期<p>
	 * @return  jcDate  竞猜日期<br>
	 */
	public Date getJcDate() {
		return jcDate;
	}
	/**
	 * 设置竞猜日期<p>
	 * @param  jcDate  竞猜日期<br>
	 */
	public void setJcDate(Date jcDate) {
		this.jcDate = jcDate;
	}
	/**
	 * 获取场次编号<p>
	 * @return  matchesNo  场次编号<br>
	 */
	public String getMatchesNo() {
		return matchesNo;
	}
	/**
	 * 设置场次编号<p>
	 * @param  matchesNo  场次编号<br>
	 */
	public void setMatchesNo(String matchesNo) {
		this.matchesNo = matchesNo;
	}
	/**
	 * 获取星期<p>
	 * @return  week  星期<br>
	 */
	public String getWeek() {
		return week;
	}
	/**
	 * 设置星期<p>
	 * @param  week  星期<br>
	 */
	public void setWeek(String week) {
		this.week = week;
	}
	/**
	 * 获取赛事<p>
	 * @return  matchName  赛事<br>
	 */
	public String getMatchName() {
		return matchName;
	}
	/**
	 * 设置赛事<p>
	 * @param  matchName  赛事<br>
	 */
	public void setMatchName(String matchName) {
		this.matchName = matchName;
	}
	/**
	 * 获取截止时间<p>
	 * @return  endTime  截止时间<br>
	 */
	public String getEndTime() {
		return endTime;
	}
	/**
	 * 设置截止时间<p>
	 * @param  endTime  截止时间<br>
	 */
	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}
	/**
	 * 获取主队名称<p>
	 * @return  homeTeamName  主队名称<br>
	 */
	public String getHomeTeamName() {
		return homeTeamName;
	}
	/**
	 * 设置主队名称<p>
	 * @param  homeTeamName  主队名称<br>
	 */
	public void setHomeTeamName(String homeTeamName) {
		this.homeTeamName = homeTeamName;
	}
	/**
	 * 获取客队名称<p>
	 * @return  visitingTeamName  客队名称<br>
	 */
	public String getVisitingTeamName() {
		return visitingTeamName;
	}
	/**
	 * 设置客队名称<p>
	 * @param  visitingTeamName  客队名称<br>
	 */
	public void setVisitingTeamName(String visitingTeamName) {
		this.visitingTeamName = visitingTeamName;
	}
	/**
	 * 获取主胜平均赔率Average<p>
	 * @return  lordAvgOdds  主胜平均赔率Average<br>
	 */
	public BigDecimal getLordAvgOdds() {
		if(CommonUtils.isNotEmptyObject(lordAvgOdds)){
			return lordAvgOdds.setScale(oddsScale , BigDecimal.ROUND_HALF_UP);
		}
		return lordAvgOdds;
	}
	/**
	 * 设置主胜平均赔率Average<p>
	 * @param  lordAvgOdds  主胜平均赔率Average<br>
	 */
	public void setLordAvgOdds(BigDecimal lordAvgOdds) {
		this.lordAvgOdds = lordAvgOdds;
	}
	/**
	 * 获取平平均赔率Average<p>
	 * @return  flatAvgOdds  平平均赔率Average<br>
	 */
	public BigDecimal getFlatAvgOdds() {
		if(CommonUtils.isNotEmptyObject(flatAvgOdds)){
			return flatAvgOdds.setScale(oddsScale, BigDecimal.ROUND_HALF_UP);
		}
		return flatAvgOdds;
	}
	/**
	 * 设置平平均赔率Average<p>
	 * @param  flatAvgOdds  平平均赔率Average<br>
	 */
	public void setFlatAvgOdds(BigDecimal flatAvgOdds) {
		this.flatAvgOdds = flatAvgOdds;
	}
	/**
	 * 获取主负平均赔率Average<p>
	 * @return  negativeAvgOdds  主负平均赔率Average<br>
	 */
	public BigDecimal getNegativeAvgOdds() {
		if(CommonUtils.isNotEmptyObject(negativeAvgOdds)){
			return negativeAvgOdds.setScale(oddsScale, BigDecimal.ROUND_HALF_UP);
		}
		return negativeAvgOdds;
	}
	/**
	 * 设置主负平均赔率Average<p>
	 * @param  negativeAvgOdds  主负平均赔率Average<br>
	 */
	public void setNegativeAvgOdds(BigDecimal negativeAvgOdds) {
		this.negativeAvgOdds = negativeAvgOdds;
	}
	/**
	 * 获取主胜赔率<p>
	 * @return  lordOdds  主胜赔率<br>
	 */
	public BigDecimal getLordOdds() {
		if(CommonUtils.isNotEmptyObject(lordOdds)){
			return lordOdds.setScale(oddsScale, BigDecimal.ROUND_HALF_UP);
		}
		return lordOdds;
	}
	/**
	 * 设置主胜赔率<p>
	 * @param  lordOdds  主胜赔率<br>
	 */
	public void setLordOdds(BigDecimal lordOdds) {
		this.lordOdds = lordOdds;
	}
	/**
	 * 获取平赔率<p>
	 * @return  flatOdds  平赔率<br>
	 */
	public BigDecimal getFlatOdds() {
		if(CommonUtils.isNotEmptyObject(flatOdds)){
			return flatOdds.setScale(oddsScale, BigDecimal.ROUND_HALF_UP);
		}
		return flatOdds;
	}
	/**
	 * 设置平赔率<p>
	 * @param  flatOdds  平赔率<br>
	 */
	public void setFlatOdds(BigDecimal flatOdds) {
		this.flatOdds = flatOdds;
	}
	/**
	 * 获取主负赔率<p>
	 * @return  negativeOdds  主负赔率<br>
	 */
	public BigDecimal getNegativeOdds() {
		if(CommonUtils.isNotEmptyObject(negativeOdds)){
			return negativeOdds.setScale(oddsScale, BigDecimal.ROUND_HALF_UP);
		}
		return negativeOdds;
	}
	/**
	 * 设置主负赔率<p>
	 * @param  negativeOdds  主负赔率<br>
	 */
	public void setNegativeOdds(BigDecimal negativeOdds) {
		this.negativeOdds = negativeOdds;
	}
	/**
	 * 获取让球<p>
	 * @return  letBall  让球<br>
	 */
	public BigDecimal getLetBall() {
		if(CommonUtils.isNotEmptyObject(letBall)){
			return letBall.setScale(0, BigDecimal.ROUND_HALF_UP);
		}
		return letBall;
	}
	/**
	 * 设置让球<p>
	 * @param  letBall  让球<br>
	 */
	public void setLetBall(BigDecimal letBall) {
		this.letBall = letBall;
	}
	/**
	 * 获取让球主胜赔率<p>
	 * @return  lordLetOdds  让球主胜赔率<br>
	 */
	public BigDecimal getLordLetOdds() {
		if(CommonUtils.isNotEmptyObject(lordLetOdds)){
			return lordLetOdds.setScale(oddsScale, BigDecimal.ROUND_HALF_UP);
		}
		return lordLetOdds;
	}
	/**
	 * 设置让球主胜赔率<p>
	 * @param  lordLetOdds  让球主胜赔率<br>
	 */
	public void setLordLetOdds(BigDecimal lordLetOdds) {
		this.lordLetOdds = lordLetOdds;
	}
	/**
	 * 获取让球平赔率<p>
	 * @return  flatLetOdds  让球平赔率<br>
	 */
	public BigDecimal getFlatLetOdds() {
		if(CommonUtils.isNotEmptyObject(flatLetOdds)){
			return flatLetOdds.setScale(oddsScale, BigDecimal.ROUND_HALF_UP);
		}
		return flatLetOdds;
	}
	/**
	 * 设置让球平赔率<p>
	 * @param  flatLetOdds  让球平赔率<br>
	 */
	public void setFlatLetOdds(BigDecimal flatLetOdds) {
		this.flatLetOdds = flatLetOdds;
	}
	/**
	 * 获取让球主负赔率<p>
	 * @return  negativeLetOdds  让球主负赔率<br>
	 */
	public BigDecimal getNegativeLetOdds() {
		if(CommonUtils.isNotEmptyObject(negativeLetOdds)){
			return negativeLetOdds.setScale(oddsScale, BigDecimal.ROUND_HALF_UP);
		}
		return negativeLetOdds;
	}
	/**
	 * 设置让球主负赔率<p>
	 * @param  negativeLetOdds  让球主负赔率<br>
	 */
	public void setNegativeLetOdds(BigDecimal negativeLetOdds) {
		this.negativeLetOdds = negativeLetOdds;
	}
	/**
	 * 获取竞猜日期字符串<p>
	 * @return  jcDateStr  竞猜日期字符串<br>
	 */
	public String getJcDateStr() {
		return jcDateStr;
	}
	/**
	 * 设置竞猜日期字符串<p>
	 * @param  jcDateStr  竞猜日期字符串<br>
	 */
	public void setJcDateStr(String jcDateStr) {
		this.jcDateStr = jcDateStr;
	}
	/**
	 * 获取竞猜258网站系统相关信息<trid="t<p>
	 * @return  theIndex  竞猜258网站系统相关信息<trid="t<br>
	 */
	public Integer getTheIndex() {
		return theIndex;
	}
	/**
	 * 设置竞猜258网站系统相关信息<trid="t<p>
	 * @param  theIndex  竞猜258网站系统相关信息<trid="t<br>
	 */
	public void setTheIndex(Integer theIndex) {
		this.theIndex = theIndex;
	}
	/**
	 * 获取竞猜tabletr行属性gn<p>
	 * @return  gn  竞猜tabletr行属性gn<br>
	 */
	public String getGn() {
		return gn;
	}
	/**
	 * 设置竞猜tabletr行属性gn<p>
	 * @param  gn  竞猜tabletr行属性gn<br>
	 */
	public void setGn(String gn) {
		this.gn = gn;
	}
	/**
	 * 获取竞猜tabletr行属性gameno<p>
	 * @return  gameno  竞猜tabletr行属性gameno<br>
	 */
	public String getGameno() {
		return gameno;
	}
	/**
	 * 设置竞猜tabletr行属性gameno<p>
	 * @param  gameno  竞猜tabletr行属性gameno<br>
	 */
	public void setGameno(String gameno) {
		this.gameno = gameno;
	}
	/**
	 * 获取竞猜tabletr行属性gameDate<p>
	 * @return  gameDate  竞猜tabletr行属性gameDate<br>
	 */
	public String getGameDate() {
		return gameDate;
	}
	/**
	 * 设置竞猜tabletr行属性gameDate<p>
	 * @param  gameDate  竞猜tabletr行属性gameDate<br>
	 */
	public void setGameDate(String gameDate) {
		this.gameDate = gameDate;
	}
	/**
	 * 获取竞猜tabletr行属性gnum<p>
	 * @return  gnum  竞猜tabletr行属性gnum<br>
	 */
	public String getGnum() {
		return gnum;
	}
	/**
	 * 设置竞猜tabletr行属性gnum<p>
	 * @param  gnum  竞猜tabletr行属性gnum<br>
	 */
	public void setGnum(String gnum) {
		this.gnum = gnum;
	}
	/**
	 * 获取竞猜tabletr行属性gtr<p>
	 * @return  gtr  竞猜tabletr行属性gtr<br>
	 */
	public String getGtr() {
		return gtr;
	}
	/**
	 * 设置竞猜tabletr行属性gtr<p>
	 * @param  gtr  竞猜tabletr行属性gtr<br>
	 */
	public void setGtr(String gtr) {
		this.gtr = gtr;
	}
	/**
	 * 获取竞猜tabletr行属性status<p>
	 * @return  status  竞猜tabletr行属性status<br>
	 */
	public String getStatus() {
		return status;
	}
	/**
	 * 设置竞猜tabletr行属性status<p>
	 * @param  status  竞猜tabletr行属性status<br>
	 */
	public void setStatus(String status) {
		this.status = status;
	}
	/**
	 * 获取竞猜tabletr行属性timenum<p>
	 * @return  timenum  竞猜tabletr行属性timenum<br>
	 */
	public String getTimenum() {
		return timenum;
	}
	/**
	 * 设置竞猜tabletr行属性timenum<p>
	 * @param  timenum  竞猜tabletr行属性timenum<br>
	 */
	public void setTimenum(String timenum) {
		this.timenum = timenum;
	}
	/**
	 * 获取竞猜tabletr行属性data-gamecode<p>
	 * @return  dataGamecode  竞猜tabletr行属性data-gamecode<br>
	 */
	public String getDataGamecode() {
		return dataGamecode;
	}
	/**
	 * 设置竞猜tabletr行属性data-gamecode<p>
	 * @param  dataGamecode  竞猜tabletr行属性data-gamecode<br>
	 */
	public void setDataGamecode(String dataGamecode) {
		this.dataGamecode = dataGamecode;
	}
	/**
	 * 获取竞猜tabletr行属性data-gameid【应该id唯一标识】<p>
	 * @return  dataGameid  竞猜tabletr行属性data-gameid【应该id唯一标识】<br>
	 */
	public String getDataGameid() {
		return dataGameid;
	}
	/**
	 * 设置竞猜tabletr行属性data-gameid【应该id唯一标识】<p>
	 * @param  dataGameid  竞猜tabletr行属性data-gameid【应该id唯一标识】<br>
	 */
	public void setDataGameid(String dataGameid) {
		this.dataGameid = dataGameid;
	}
	/**
	 * 获取竞猜tabletr行属性data-isturn<p>
	 * @return  dataIsturn  竞猜tabletr行属性data-isturn<br>
	 */
	public String getDataIsturn() {
		return dataIsturn;
	}
	/**
	 * 设置竞猜tabletr行属性data-isturn<p>
	 * @param  dataIsturn  竞猜tabletr行属性data-isturn<br>
	 */
	public void setDataIsturn(String dataIsturn) {
		this.dataIsturn = dataIsturn;
	}
	/**
	 * 获取是否未开售胜平负玩法<p>
	 * @return  isNotSaleSPF  是否未开售胜平负玩法<br>
	 */
	public Boolean getIsNotSaleSPF() {
		return isNotSaleSPF;
	}
	/**
	 * 设置是否未开售胜平负玩法<p>
	 * @param  isNotSaleSPF  是否未开售胜平负玩法<br>
	 */
	public void setIsNotSaleSPF(Boolean isNotSaleSPF) {
		this.isNotSaleSPF = isNotSaleSPF;
	}
	/**
	 * 获取主队名称简称<p>
	 * @return  homeTeamSimpleName  主队名称简称<br>
	 */
	public String getHomeTeamSimpleName() {
		return homeTeamSimpleName;
	}
	/**
	 * 设置主队名称简称<p>
	 * @param  homeTeamSimpleName  主队名称简称<br>
	 */
	public void setHomeTeamSimpleName(String homeTeamSimpleName) {
		this.homeTeamSimpleName = homeTeamSimpleName;
	}
	/**
	 * 获取客队名称简称<p>
	 * @return  visitingTeamSimpleName  客队名称简称<br>
	 */
	public String getVisitingTeamSimpleName() {
		return visitingTeamSimpleName;
	}
	/**
	 * 设置客队名称简称<p>
	 * @param  visitingTeamSimpleName  客队名称简称<br>
	 */
	public void setVisitingTeamSimpleName(String visitingTeamSimpleName) {
		this.visitingTeamSimpleName = visitingTeamSimpleName;
	}
	/**
	 * 获取竞猜table让球tr行属性id="tr_425502"【应该是竞猜数据唯一标识id】<p>
	 * @return  letDataGameid  竞猜table让球tr行属性id="tr_425502"【应该是竞猜数据唯一标识id】<br>
	 */
	public String getLetDataGameid() {
		return letDataGameid;
	}
	/**
	 * 设置竞猜table让球tr行属性id="tr_425502"【应该是竞猜数据唯一标识id】<p>
	 * @param  letDataGameid  竞猜table让球tr行属性id="tr_425502"【应该是竞猜数据唯一标识id】<br>
	 */
	public void setLetDataGameid(String letDataGameid) {
		this.letDataGameid = letDataGameid;
	}
	/**
	 * 获取主队名称258网站链接<p>
	 * @return  homeTeamNameHref  主队名称258网站链接<br>
	 */
	public String getHomeTeamNameHref() {
		return homeTeamNameHref;
	}
	/**
	 * 设置主队名称258网站链接<p>
	 * @param  homeTeamNameHref  主队名称258网站链接<br>
	 */
	public void setHomeTeamNameHref(String homeTeamNameHref) {
		this.homeTeamNameHref = homeTeamNameHref;
	}
	/**
	 * 获取客队名称258网站链接<p>
	 * @return  visitingTeamNameHref  客队名称258网站链接<br>
	 */
	public String getVisitingTeamNameHref() {
		return visitingTeamNameHref;
	}
	/**
	 * 设置客队名称258网站链接<p>
	 * @param  visitingTeamNameHref  客队名称258网站链接<br>
	 */
	public void setVisitingTeamNameHref(String visitingTeamNameHref) {
		this.visitingTeamNameHref = visitingTeamNameHref;
	}
	/**
	 * 获取258网站两队历史交战记录分析url地址<p>
	 * @return  analysisHref  258网站两队历史交战记录分析url地址<br>
	 */
	public String getAnalysisHref() {
		return analysisHref;
	}
	/**
	 * 设置258网站两队历史交战记录分析url地址<p>
	 * @param  analysisHref  258网站两队历史交战记录分析url地址<br>
	 */
	public void setAnalysisHref(String analysisHref) {
		this.analysisHref = analysisHref;
	}
	/**
	 * 获取258网站两队历史交战记录分析url地址<p>
	 * @return  historyAnalysisHref  258网站两队历史交战记录分析url地址<br>
	 */
	public String getHistoryAnalysisHref() {
		return historyAnalysisHref;
	}
	/**
	 * 设置258网站两队历史交战记录分析url地址<p>
	 * @param  historyAnalysisHref  258网站两队历史交战记录分析url地址<br>
	 */
	public void setHistoryAnalysisHref(String historyAnalysisHref) {
		this.historyAnalysisHref = historyAnalysisHref;
	}
	/**
	 * 获取258网站推荐url地址<p>
	 * @return  nominateHref  258网站推荐url地址<br>
	 */
	public String getNominateHref() {
		return nominateHref;
	}
	/**
	 * 设置258网站推荐url地址<p>
	 * @param  nominateHref  258网站推荐url地址<br>
	 */
	public void setNominateHref(String nominateHref) {
		this.nominateHref = nominateHref;
	}
	/**
	 * 获取赛事链接url地址<p>
	 * @return  matchNameHref  赛事链接url地址<br>
	 */
	public String getMatchNameHref() {
		return matchNameHref;
	}
	/**
	 * 设置赛事链接url地址<p>
	 * @param  matchNameHref  赛事链接url地址<br>
	 */
	public void setMatchNameHref(String matchNameHref) {
		this.matchNameHref = matchNameHref;
	}
	/**
	 * 获取赛事样式<p>
	 * @return  matchStyle  赛事样式<br>
	 */
	public String getMatchStyle() {
		return matchStyle;
	}
	/**
	 * 设置赛事样式<p>
	 * @param  matchStyle  赛事样式<br>
	 */
	public void setMatchStyle(String matchStyle) {
		this.matchStyle = matchStyle;
	}
	/**
	 * 获取赛事编码<p>
	 * @return  matchNum  赛事编码<br>
	 */
	public String getMatchNum() {
		return matchNum;
	}
	/**
	 * 设置赛事编码<p>
	 * @param  matchNum  赛事编码<br>
	 */
	public void setMatchNum(String matchNum) {
		this.matchNum = matchNum;
	}
	
	
	
	
	
	
}
