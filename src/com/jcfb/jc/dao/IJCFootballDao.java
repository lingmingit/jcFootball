/**
 * &lt;p&gt;
 * copyright &amp;copy; together 2014, all rights reserved.
 * @author admin
 * @version $Id$
 * @since 1.0
 * 
 */
package com.jcfb.jc.dao;

import java.util.Date;

import com.jcfb.dao.base.ICoreBaseDao;
import com.jcfb.entity.JCFootball;

/** 
 * 竞猜足球数据库操作dao<p>
 * @author LingMin 
 * @date 2015-2-9<br>
 * @version 1.0<br>
 */
public interface IJCFootballDao extends ICoreBaseDao<JCFootball, String> {

	/****
	 * 根据 竞猜相关信息 进行查询实体信息<p>
	 * @param jcDate竞猜日期
	 * @param gameno 竞猜网 让球行唯一标识id
	 * @return  返回实体对象<p>
	 * JCFootball
	 */
	public JCFootball getGameNoToJCFootball(Date jcDate , String gameno);
	/****
	 * 根据 竞猜相关信息 进行查询实体信息<p>
	 * @param jcDate竞猜日期
	 * @param letDataGameid 竞猜网 让球行唯一标识id
	 * @return  返回实体对象<p>
	 * JCFootball
	 */
	public JCFootball getJCFootball(Date jcDate , String letDataGameid);
	
	
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
	public JCFootball getJCFootball(Date jcDate , String matchesNo , String week , String matchName , String homeTeamName , String visitingTeamName);
	
}
