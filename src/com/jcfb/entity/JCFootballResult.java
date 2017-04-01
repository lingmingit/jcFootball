package com.jcfb.entity;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import com.common.CommonUtils;
import com.jcfb.entity.base.BaseCore;



/****
 * 足彩竞猜 赛事 最终结果表<p>
 * @author LingMin 
 * @date 2015-2-26<br>
 * @version 1.0<br>
 */
@Entity @Table(name = "t_jc_footballResult")
public class JCFootballResult  extends BaseCore{

	
	/** 所属竞猜记录 **/
	@OneToOne(fetch=FetchType.LAZY) @JoinColumn
	private JCFootball jcFootball;
	
	/***竞猜日期***/
	@Column @Temporal(TemporalType.DATE)
	private Date jcDate;
	
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
	/***比赛时间***/
	@Column(length=20)
	private String matchDateTime;
	
	/**主队名称*****/
	@Column(length=50)
	private String homeTeamName;
	/**客队名称*****/
	@Column(length=50)
	private String visitingTeamName;
	/**让球  *****/
	@Column
	private Integer letBall;
	/**258 网站两队 历史交战记录分析 url地址 **/
	@Column(length=150)
	private String historyAnalysisHref;
	
	
	/**半场比分***/
	@Column(length=5)
	private String halfCourtScore;
	/**全场比分***/
	@Column(length=5)
	private String fullCourtScore;
	
	
	/**胜平 负 【彩果】***/
	@Column(length=5)
	private String result;
	/**胜平 负 【过关终赔】***/
	@Column(precision=20, scale=8)
	private BigDecimal resultOdds;
	
	/**让球胜平负 【彩果】***/
	@Column(length=5)
	private String letResult;
	/**让球胜平负【过关终赔】****/
	@Column(precision=20, scale=8)
	private BigDecimal letResultOdds;
	
	/**总进球***/
	@Column(length=5)
	private Integer goalNum;
	/**总进球 字符串***/
	@Column(length=5)
	private String goalNumStr;
	/**总进球【过关终赔】***/
	@Column(precision=20, scale=8)
	private BigDecimal goalOdds;
	
	/**比分 【彩果】***/
	@Column(length=5)
	private String scoreResult;
	/**比分【过关终赔】***/
	@Column(precision=20, scale=8)
	private BigDecimal scoreOdds;
	
	
	/**半全场胜平负【彩果】****/
	@Column(length=5)
	private String halfFullResult;
	/**半全场胜平负【过关终赔】****/
	@Column(precision=20, scale=8)
	private BigDecimal halfFullOdds;
	
	/**赔率保留的小数位**/
	@Transient
	private final int oddsScale = 2;
	
	/***竞猜258网站系统相关信息
	 * <tr onmouseover="this.style.background='#d2eafa'" onmouseout="this.style.background='none'" gameid="425552"  gamenum="3066" gamedate="20150225" >
	 * ******/
	/***竞猜table tr行属性  gameid  对应竞猜网页 让球    ====>>>  JCFootball.letDataGameid 即：<tr id="tr_425502"****/
	@Column(length=25)
	private String gameid;
	/***竞猜table tr行属性  gamenum****/
	@Column(length=25)
	private String gamenum;
	/***竞猜table tr行属性  gamedate****/
	@Column(length=20)
	private String gamedate;
	
	
	
	
	/**
	 * 获取所属竞猜记录<p>
	 * @return  jcFootball  所属竞猜记录<br>
	 */
	public JCFootball getJcFootball() {
		return jcFootball;
	}
	
	
	
	
	
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
	 * 设置所属竞猜记录<p>
	 * @param  jcFootball  所属竞猜记录<br>
	 */
	public void setJcFootball(JCFootball jcFootball) {
		this.jcFootball = jcFootball;
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
	 * 获取让球<p>
	 * @return  letBall  让球<br>
	 */
	public Integer getLetBall() {
		return letBall;
	}





	/**
	 * 设置让球<p>
	 * @param  letBall  让球<br>
	 */
	public void setLetBall(Integer letBall) {
		this.letBall = letBall;
	}





	/**
	 * 获取比赛时间<p>
	 * @return  matchDateTime  比赛时间<br>
	 */
	public String getMatchDateTime() {
		return matchDateTime;
	}
	/**
	 * 设置比赛时间<p>
	 * @param  matchDateTime  比赛时间<br>
	 */
	public void setMatchDateTime(String matchDateTime) {
		this.matchDateTime = matchDateTime;
	}
	/**
	 * 获取半场比分<p>
	 * @return  halfCourtScore  半场比分<br>
	 */
	public String getHalfCourtScore() {
		return halfCourtScore;
	}
	/**
	 * 设置半场比分<p>
	 * @param  halfCourtScore  半场比分<br>
	 */
	public void setHalfCourtScore(String halfCourtScore) {
		this.halfCourtScore = halfCourtScore;
	}
	/**
	 * 获取全场比分<p>
	 * @return  fullCourtScore  全场比分<br>
	 */
	public String getFullCourtScore() {
		return fullCourtScore;
	}
	/**
	 * 设置全场比分<p>
	 * @param  fullCourtScore  全场比分<br>
	 */
	public void setFullCourtScore(String fullCourtScore) {
		this.fullCourtScore = fullCourtScore;
	}
	/**
	 * 获取胜平负【彩果】<p>
	 * @return  result  胜平负【彩果】<br>
	 */
	public String getResult() {
		return result;
	}
	/**
	 * 设置胜平负【彩果】<p>
	 * @param  result  胜平负【彩果】<br>
	 */
	public void setResult(String result) {
		this.result = result;
	}
	/**
	 * 获取胜平负【过关终赔】<p>
	 * @return  resultOdds  胜平负【过关终赔】<br>
	 */
	public BigDecimal getResultOdds() {
		if(CommonUtils.isNotEmptyObject(resultOdds)){
			resultOdds = resultOdds.setScale(oddsScale, BigDecimal.ROUND_HALF_UP);
		}
		return resultOdds;
	}
	/**
	 * 设置胜平负【过关终赔】<p>
	 * @param  resultOdds  胜平负【过关终赔】<br>
	 */
	public void setResultOdds(BigDecimal resultOdds) {
		this.resultOdds = resultOdds;
	}
	/**
	 * 获取让球胜平负【彩果】<p>
	 * @return  letResult  让球胜平负【彩果】<br>
	 */
	public String getLetResult() {
		return letResult;
	}
	/**
	 * 设置让球胜平负【彩果】<p>
	 * @param  letResult  让球胜平负【彩果】<br>
	 */
	public void setLetResult(String letResult) {
		this.letResult = letResult;
	}
	/**
	 * 获取让球胜平负【过关终赔】<p>
	 * @return  letResultOdds  让球胜平负【过关终赔】<br>
	 */
	public BigDecimal getLetResultOdds() {
		if(CommonUtils.isNotEmptyObject(letResultOdds)){
			letResultOdds = letResultOdds.setScale(oddsScale, BigDecimal.ROUND_HALF_UP);
		}
		return letResultOdds;
	}
	/**
	 * 设置让球胜平负【过关终赔】<p>
	 * @param  letResultOdds  让球胜平负【过关终赔】<br>
	 */
	public void setLetResultOdds(BigDecimal letResultOdds) {
		this.letResultOdds = letResultOdds;
	}
	/**
	 * 获取总进球<p>
	 * @return  goalNum  总进球<br>
	 */
	public Integer getGoalNum() {
		return goalNum;
	}
	/**
	 * 设置总进球<p>
	 * @param  goalNum  总进球<br>
	 */
	public void setGoalNum(Integer goalNum) {
		this.goalNum = goalNum;
	}
	/**
	 * 获取总进球【过关终赔】<p>
	 * @return  goalOdds  总进球【过关终赔】<br>
	 */
	public BigDecimal getGoalOdds() {
		if(CommonUtils.isNotEmptyObject(goalOdds)){
			goalOdds = goalOdds.setScale(oddsScale, BigDecimal.ROUND_HALF_UP);
		}
		return goalOdds;
	}
	/**
	 * 设置总进球【过关终赔】<p>
	 * @param  goalOdds  总进球【过关终赔】<br>
	 */
	public void setGoalOdds(BigDecimal goalOdds) {
		this.goalOdds = goalOdds;
	}
	/**
	 * 获取比分【彩果】<p>
	 * @return  scoreResult  比分【彩果】<br>
	 */
	public String getScoreResult() {
		return scoreResult;
	}
	/**
	 * 设置比分【彩果】<p>
	 * @param  scoreResult  比分【彩果】<br>
	 */
	public void setScoreResult(String scoreResult) {
		this.scoreResult = scoreResult;
	}
	/**
	 * 获取比分【过关终赔】<p>
	 * @return  scoreOdds  比分【过关终赔】<br>
	 */
	public BigDecimal getScoreOdds() {
		if(CommonUtils.isNotEmptyObject(scoreOdds)){
			scoreOdds = scoreOdds.setScale(oddsScale, BigDecimal.ROUND_HALF_UP);
		}
		return scoreOdds;
	}
	/**
	 * 设置比分【过关终赔】<p>
	 * @param  scoreOdds  比分【过关终赔】<br>
	 */
	public void setScoreOdds(BigDecimal scoreOdds) {
		this.scoreOdds = scoreOdds;
	}
	/**
	 * 获取半全场胜平负【彩果】<p>
	 * @return  halfFullResult  半全场胜平负【彩果】<br>
	 */
	public String getHalfFullResult() {
		return halfFullResult;
	}
	/**
	 * 设置半全场胜平负【彩果】<p>
	 * @param  halfFullResult  半全场胜平负【彩果】<br>
	 */
	public void setHalfFullResult(String halfFullResult) {
		this.halfFullResult = halfFullResult;
	}
	/**
	 * 获取半全场胜平负【过关终赔】<p>
	 * @return  halfFullOdds  半全场胜平负【过关终赔】<br>
	 */
	public BigDecimal getHalfFullOdds() {
		if(CommonUtils.isNotEmptyObject(halfFullOdds)){
			halfFullOdds = halfFullOdds.setScale(oddsScale, BigDecimal.ROUND_HALF_UP);
		}
		return halfFullOdds;
	}
	/**
	 * 设置半全场胜平负【过关终赔】<p>
	 * @param  halfFullOdds  半全场胜平负【过关终赔】<br>
	 */
	public void setHalfFullOdds(BigDecimal halfFullOdds) {
		this.halfFullOdds = halfFullOdds;
	}





	/**
	 * 获取竞猜258网站系统相关信息<tronm<p>
	 * @return  gameid  竞猜258网站系统相关信息<tronm<br>
	 */
	public String getGameid() {
		return gameid;
	}





	/**
	 * 设置竞猜258网站系统相关信息<tronm<p>
	 * @param  gameid  竞猜258网站系统相关信息<tronm<br>
	 */
	public void setGameid(String gameid) {
		this.gameid = gameid;
	}





	/**
	 * 获取竞猜tabletr行属性gamenum<p>
	 * @return  gamenum  竞猜tabletr行属性gamenum<br>
	 */
	public String getGamenum() {
		return gamenum;
	}





	/**
	 * 设置竞猜tabletr行属性gamenum<p>
	 * @param  gamenum  竞猜tabletr行属性gamenum<br>
	 */
	public void setGamenum(String gamenum) {
		this.gamenum = gamenum;
	}





	/**
	 * 获取竞猜tabletr行属性gameid<p>
	 * @return  gamedate  竞猜tabletr行属性gameid<br>
	 */
	public String getGamedate() {
		return gamedate;
	}





	/**
	 * 设置竞猜tabletr行属性gameid<p>
	 * @param  gamedate  竞猜tabletr行属性gameid<br>
	 */
	public void setGamedate(String gamedate) {
		this.gamedate = gamedate;
	}





	/**
	 * 获取总进球字符串<p>
	 * @return  goalNumStr  总进球字符串<br>
	 */
	public String getGoalNumStr() {
		return goalNumStr;
	}





	/**
	 * 设置总进球字符串<p>
	 * @param  goalNumStr  总进球字符串<br>
	 */
	public void setGoalNumStr(String goalNumStr) {
		this.goalNumStr = goalNumStr;
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
