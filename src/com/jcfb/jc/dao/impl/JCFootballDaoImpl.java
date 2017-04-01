package com.jcfb.jc.dao.impl;


import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.common.CommonUtils;
import com.jcfb.dao.base.impl.CoreBaseDaoImpl;
import com.jcfb.entity.JCFootball;
import com.jcfb.jc.dao.IJCFootballDao;


/** 
 * 竞猜足球数据库操作dao实现类<p>
 * @author LingMin 
 * @date 2015-2-9<br>
 * @version 1.0<br>
 */
@Repository("jcFootballDao")
public class JCFootballDaoImpl extends CoreBaseDaoImpl<JCFootball, String, IJCFootballDao> implements IJCFootballDao {

	
	
	
	/****
	 * 根据 竞猜相关信息 进行查询实体信息<p>
	 * @param jcDate竞猜日期
	 * @param gameno 竞猜网 让球行唯一标识id
	 * @return  返回实体对象<p>
	 * JCFootball
	 */
	public JCFootball getGameNoToJCFootball(Date jcDate , String gameno){
		StringBuffer jpql = new StringBuffer();
		jpql.append("from JCFootball  ");
		jpql.append(" where  gn = ? and gameno = ? ");//jcDate = ? and   ，不增加竞猜日期 ，由于竞猜日期不能对应，不能找到对应记录
		jpql.append("");
		jpql.append("");
		List<JCFootball> list = (List)this.findByJPQL(jpql.toString(), new Object[]{ gameno , gameno });
		if(CommonUtils.isNotEmptyList(list)){
			return list.get(0);
		}
		return null;
	}
	
	/****
	 * 根据 竞猜相关信息 进行查询实体信息<p>
	 * @param jcDate竞猜日期
	 * @param letDataGameid 竞猜网 让球行唯一标识id
	 * @return  返回实体对象<p>
	 * JCFootball
	 */
	public JCFootball getJCFootball(Date jcDate , String letDataGameid){
		StringBuffer jpql = new StringBuffer();
		jpql.append("from JCFootball  ");
		jpql.append(" where  letDataGameid = ? ");//jcDate = ? and   ，不增加竞猜日期 ，由于竞猜日期不能对应，不能找到对应记录
		jpql.append("");
		jpql.append("");
		List<JCFootball> list = (List)this.findByJPQL(jpql.toString(), new Object[]{ letDataGameid });
		if(CommonUtils.isNotEmptyList(list)){
			return list.get(0);
		}
		return null;
	}
	
	/****
	 * 根据 竞猜相关信息 进行查询实体信息<p>
	 * @param jcDate竞猜日期
	 * @param matchesNo 场次编号
	 * @param week 周几
	 * @param matchName 赛事名称
	 * @param homeTeamName 主队名称
	 * @param visitingTeamName 客队名称
	 * @return  返回实体对象<p>
	 * JCFootball
	 */
	public JCFootball getJCFootball(Date jcDate , String matchesNo , String week , String matchName , String homeTeamName , String visitingTeamName){
		StringBuffer jpql = new StringBuffer();
		jpql.append("from JCFootball  ");//jcDate = ? and   ，不增加竞猜日期 ，由于竞猜日期不能对应，不能找到对应记录
		jpql.append(" where matchesNo = ? and week = ? and matchName = ? and homeTeamName = ? and visitingTeamName = ? ");
		jpql.append("");
		jpql.append("");
		List<JCFootball> list = (List)this.findByJPQL(jpql.toString(), new Object[]{matchesNo , week , matchName , homeTeamName , visitingTeamName });
		if(CommonUtils.isNotEmptyList(list)){
			return list.get(0);
		}
		return null;
	}

}
