package com.jcfb.entity;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.jcfb.entity.base.BaseCore;


/**
 * 
 * 足球彩票实体信息<p>
 * @author LingMin 
 * @date 2015-3-2<br>
 * @version 1.0<br>
 */
@Entity @Table(name = "t_jc_fbLottery")
public class JCFBLottery extends BaseCore {

	/***竞猜投注日期***/
	@Column @Temporal(TemporalType.DATE)
	private Date bizDate;
	/**彩票编码***/
	@Column(length = 15)
	private String numbers;
	/**彩票类型，如：一串1、二串1、等***/
	@Column(length = 5)
	private String type;
	/**彩票投资倍数***/
	@Column(length = 5)
	private Integer multiple;
	/**彩票投资金额***/
	@Column(precision=20, scale=8)
	private BigDecimal bettingAmount;
	/**预计中奖金额***/
	@Column(precision=20, scale=8)
	private BigDecimal preAmount;
	/**是否中奖了***/
	@Column
	private Boolean isZJ;
	/**中奖金额***/
	@Column(precision=20, scale=8)
	private BigDecimal zjAmount;
	
	/**彩票分录list***/
	@OneToMany(cascade=CascadeType.ALL, fetch=FetchType.LAZY, mappedBy="parent")
	private List<JCFBLotteryEntry> entryList;
	
	
	/**
	 * 获取彩票类型，如：一串1、二串1、等<p>
	 * @return  type  彩票类型，如：一串1、二串1、等<br>
	 */
	public String getType() {
		return type;
	}
	/**
	 * 设置彩票类型，如：一串1、二串1、等<p>
	 * @param  type  彩票类型，如：一串1、二串1、等<br>
	 */
	public void setType(String type) {
		this.type = type;
	}
	/**
	 * 获取彩票投资倍数<p>
	 * @return  multiple  彩票投资倍数<br>
	 */
	public Integer getMultiple() {
		return multiple;
	}
	/**
	 * 设置彩票投资倍数<p>
	 * @param  multiple  彩票投资倍数<br>
	 */
	public void setMultiple(Integer multiple) {
		this.multiple = multiple;
	}
	/**
	 * 获取彩票投资金额<p>
	 * @return  bettingAmount  彩票投资金额<br>
	 */
	public BigDecimal getBettingAmount() {
		return bettingAmount;
	}
	/**
	 * 设置彩票投资金额<p>
	 * @param  bettingAmount  彩票投资金额<br>
	 */
	public void setBettingAmount(BigDecimal bettingAmount) {
		this.bettingAmount = bettingAmount;
	}
	/**
	 * 获取预计中奖金额<p>
	 * @return  preAmount  预计中奖金额<br>
	 */
	public BigDecimal getPreAmount() {
		return preAmount;
	}
	/**
	 * 设置预计中奖金额<p>
	 * @param  preAmount  预计中奖金额<br>
	 */
	public void setPreAmount(BigDecimal preAmount) {
		this.preAmount = preAmount;
	}
	/**
	 * 获取是否中奖了<p>
	 * @return  isZJ  是否中奖了<br>
	 */
	public Boolean getIsZJ() {
		return isZJ;
	}
	/**
	 * 设置是否中奖了<p>
	 * @param  isZJ  是否中奖了<br>
	 */
	public void setIsZJ(Boolean isZJ) {
		this.isZJ = isZJ;
	}
	/**
	 * 获取中奖金额<p>
	 * @return  zjAmount  中奖金额<br>
	 */
	public BigDecimal getZjAmount() {
		return zjAmount;
	}
	/**
	 * 设置中奖金额<p>
	 * @param  zjAmount  中奖金额<br>
	 */
	public void setZjAmount(BigDecimal zjAmount) {
		this.zjAmount = zjAmount;
	}
	/**
	 * 获取彩票分录list<p>
	 * @return  entryList  彩票分录list<br>
	 */
	public List<JCFBLotteryEntry> getEntryList() {
		return entryList;
	}
	/**
	 * 设置彩票分录list<p>
	 * @param  entryList  彩票分录list<br>
	 */
	public void setEntryList(List<JCFBLotteryEntry> entryList) {
		this.entryList = entryList;
	}
	/**
	 * 获取彩票编码<p>
	 * @return  numbers  彩票编码<br>
	 */
	public String getNumbers() {
		return numbers;
	}
	/**
	 * 设置彩票编码<p>
	 * @param  numbers  彩票编码<br>
	 */
	public void setNumbers(String numbers) {
		this.numbers = numbers;
	}
	/**
	 * 获取竞猜投注日期<p>
	 * @return  bizDate  竞猜投注日期<br>
	 */
	public Date getBizDate() {
		return bizDate;
	}
	/**
	 * 设置竞猜投注日期<p>
	 * @param  bizDate  竞猜投注日期<br>
	 */
	public void setBizDate(Date bizDate) {
		this.bizDate = bizDate;
	}
	
	
}
