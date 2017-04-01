/**
 * &lt;p&gt;
 * copyright &amp;copy; together 2014, all rights reserved.
 * @author admin
 * @version $Id$
 * @since 1.0
 * 
 */
package com.jcfb.entity;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.common.CommonUtils;
import com.jcfb.entity.base.BaseEntry;

/** 
 * <p>
 * @author LingMin 
 * @date 2015-3-2<br>
 * @version 1.0<br>
 */
@Entity @Table(name = "t_jc_fbLotteryEntry")
public class JCFBLotteryEntry extends BaseEntry{

	/** 彩票记录 **/
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(nullable=false)
	private JCFBLottery parent;
	/** 所属竞猜记录 **/
	@OneToOne(fetch=FetchType.LAZY) @JoinColumn
	private JCFootball jcFootball;
	/** 所属竞猜赛果记录 **/
	@OneToOne(fetch=FetchType.LAZY) @JoinColumn
	private JCFootballResult jcFootballResult;
	
	/**竞猜结果 胜平 负 【彩果】 FBJCResultEnum***/
	@Column(length=5)
	private String result;
	/**竞猜结果 赔率  *****/
	@Column(precision=20, scale=8)
	private BigDecimal resultOdds;
	/** 最终 结果 赔率  *****/
	@Column(length=5)
	private String finalResult;
	/** 最终 结果 赔率  *****/
	@Column(precision=20, scale=8)
	private BigDecimal finalResultOdds;
	/**让球  *****/
	@Column
	private Integer letBall;
	/***是否正确，true 投注正确，false 投注失败***/
	@Column(length=1)
	private Boolean isResult;
	
	
	/**赔率保留的小数位**/
	@Transient
	private final int oddsScale = 2;
	
	
	
	/**
	 * 获取彩票记录<p>
	 * @return  parent  彩票记录<br>
	 */
	public JCFBLottery getParent() {
		return parent;
	}
	/**
	 * 设置彩票记录<p>
	 * @param  parent  彩票记录<br>
	 */
	public void setParent(JCFBLottery parent) {
		this.parent = parent;
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
	 * 获取竞猜结果胜平负【彩果】FBJCResultEnum<p>
	 * @return  result  竞猜结果胜平负【彩果】FBJCResultEnum<br>
	 */
	public String getResult() {
		return result;
	}
	/**
	 * 设置竞猜结果胜平负【彩果】FBJCResultEnum<p>
	 * @param  result  竞猜结果胜平负【彩果】FBJCResultEnum<br>
	 */
	public void setResult(String result) {
		this.result = result;
	}
	/**
	 * 获取竞猜结果赔率<p>
	 * @return  resultOdds  竞猜结果赔率<br>
	 */
	public BigDecimal getResultOdds() {
		if(CommonUtils.isNotEmptyObject(resultOdds)){
			return resultOdds.setScale(oddsScale , BigDecimal.ROUND_HALF_UP);
		}
		return resultOdds;
	}
	/**
	 * 设置竞猜结果赔率<p>
	 * @param  resultOdds  竞猜结果赔率<br>
	 */
	public void setResultOdds(BigDecimal resultOdds) {
		this.resultOdds = resultOdds;
	}
	/**
	 * 获取所属竞猜记录<p>
	 * @return  jcFootball  所属竞猜记录<br>
	 */
	public JCFootball getJcFootball() {
		return jcFootball;
	}
	/**
	 * 设置所属竞猜记录<p>
	 * @param  jcFootball  所属竞猜记录<br>
	 */
	public void setJcFootball(JCFootball jcFootball) {
		this.jcFootball = jcFootball;
	}
	/**
	 * 获取所属竞猜赛果记录<p>
	 * @return  jcFootballResult  所属竞猜赛果记录<br>
	 */
	public JCFootballResult getJcFootballResult() {
		return jcFootballResult;
	}
	/**
	 * 设置所属竞猜赛果记录<p>
	 * @param  jcFootballResult  所属竞猜赛果记录<br>
	 */
	public void setJcFootballResult(JCFootballResult jcFootballResult) {
		this.jcFootballResult = jcFootballResult;
	}
	/**
	 * 获取是否正确，true投注正确，false投注失败<p>
	 * @return  isResult  是否正确，true投注正确，false投注失败<br>
	 */
	public Boolean getIsResult() {
		return isResult;
	}
	/**
	 * 设置是否正确，true投注正确，false投注失败<p>
	 * @param  isResult  是否正确，true投注正确，false投注失败<br>
	 */
	public void setIsResult(Boolean isResult) {
		this.isResult = isResult;
	}
	/**
	 * 获取最终结果赔率<p>
	 * @return  finalResultOdds  最终结果赔率<br>
	 */
	public BigDecimal getFinalResultOdds() {
		if(CommonUtils.isNotEmptyObject(finalResultOdds)){
			return finalResultOdds.setScale(oddsScale , BigDecimal.ROUND_HALF_UP);
		}
		return finalResultOdds;
	}
	/**
	 * 设置最终结果赔率<p>
	 * @param  finalResultOdds  最终结果赔率<br>
	 */
	public void setFinalResultOdds(BigDecimal finalResultOdds) {
		this.finalResultOdds = finalResultOdds;
	}
	/**
	 * 获取最终结果<p>
	 * @return  finalResult  最终结果<br>
	 */
	public String getFinalResult() {
		return finalResult;
	}
	/**
	 * 设置最终结果<p>
	 * @param  finalResult  最终结果<br>
	 */
	public void setFinalResult(String finalResult) {
		this.finalResult = finalResult;
	}
	
	
	
	
	
}
