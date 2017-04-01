/**
 * &lt;p&gt;
 * copyright &amp;copy; together 2014, all rights reserved.
 * @author admin
 * @version $Id$
 * @since 1.0
 * 
 */
package com.jcfb.jc.dao.impl;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.common.CommonUtils;
import com.common.StringUtils;
import com.jcfb.dao.base.impl.BaseEntryDaoImpl;
import com.jcfb.dao.common.ConditionParameter;
import com.jcfb.dao.common.SQLCondition;
import com.jcfb.dao.enums.CompareTypeEnum;
import com.jcfb.entity.JCFBLotteryEntry;
import com.jcfb.entity.JCFootball;
import com.jcfb.entity.JCFootballResult;
import com.jcfb.jc.dao.IJCFBLotteryEntryDao;

/** 
 * 彩票分录数据库操作接口实现类<p>
 * @author LingMin 
 * @date 2015-3-2<br>
 * @version 1.0<br>
 */
@Repository("jcFBLotteryEntryDao")
public class JCFBLotteryEntryDaoImpl extends BaseEntryDaoImpl<JCFBLotteryEntry, String, IJCFBLotteryEntryDao> implements IJCFBLotteryEntryDao {


	
	/****
	 * 更新竞猜结果<p>
	 * @param jcFootball 竞猜记录对象
	 * @param jcFootballResult 竞猜结果记录对象<p>
	 * @return String 返回提示信息
	 */
	public String updateJCFBResult(JCFootball jcFootball , JCFootballResult jcFootballResult){
		if(CommonUtils.isEmptyObject(jcFootball) || StringUtils.isEmpty(jcFootball.getId())){
			return "竞猜记录为空";
		}
		List<JCFBLotteryEntry> list = this.getJCFBLotteryEntry(jcFootball.getId());
		if(CommonUtils.isEmptyList(list)){
			return "竞猜记录【"+jcFootball.getId()+"】没有投注记录";
		}
		for(JCFBLotteryEntry entry : list){
			entry.setJcFootballResult(jcFootballResult);
			this.getLotteryResult(entry, jcFootballResult);
			this.update(entry);
		}
		return "成功更新"+list.size()+"条投注记录!!";
	}
	
	
	/***
	 * 根据竞猜记录 查询 投注信息<p>
	 * @param jcFootballId 竞猜记录id
	 * @return List<JCFBLotteryEntry> 返回投注list<p>
	 */
	public List<JCFBLotteryEntry> getJCFBLotteryEntry(String jcFootballId){
		SQLCondition condition = new SQLCondition();
		condition.put(new ConditionParameter("jcFootball.id", jcFootballId , CompareTypeEnum.COMPARE_EQUEAL));
		return this.list(condition);
	}
	
	
	/***
	 * 根据 赛事结果 判断 投注是否正确，返回true 正确，false 错误 <p>
	 * @param entry 投注信息实体
	 * @param jcFootballResult 赛事结果实体对象
	 * @return Boolean 返回true 正确，false 错误<p>
	 */
	private Boolean getLotteryResult(JCFBLotteryEntry entry , JCFootballResult jcFootballResult){
		if(CommonUtils.isEmptyObject(entry)){
			return null;
		}
		if(CommonUtils.isEmptyObject(jcFootballResult)){
			return null;
		}
		Boolean isResult = null;
		String finalResult = null;
		BigDecimal finalResultOdds = null;
		if(entry.getLetBall() == 0){//不让球
			finalResult = jcFootballResult.getResult();
			finalResultOdds = jcFootballResult.getResultOdds();
			if(entry.getResult().equals(finalResult)){
				isResult = true;
			}else{
				isResult = false;
			}
		}else{//让球
			finalResult =  jcFootballResult.getLetResult();
			finalResultOdds = jcFootballResult.getLetResultOdds();
			if(entry.getResult().equals(finalResult)){
				isResult = true;
			}else{
				isResult = false;
			}
		}
		entry.setIsResult(isResult);
		entry.setFinalResult(finalResult);
		entry.setFinalResultOdds(finalResultOdds);
		return isResult;
	}
}
