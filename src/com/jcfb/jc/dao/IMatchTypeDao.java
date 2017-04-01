/**
 * &lt;p&gt;
 * copyright &amp;copy; together 2014, all rights reserved.
 * @author admin
 * @version $Id$
 * @since 1.0
 * 
 */
package com.jcfb.jc.dao;

import com.jcfb.dao.base.ICoreBaseDao;
import com.jcfb.entity.MatchType;

/** 
 * 赛事类型 实体数据库操作接口类<p>
 * @author LingMin 
 * @date 2015-2-28<br>
 * @version 1.0<br>
 */
public interface IMatchTypeDao extends ICoreBaseDao<MatchType, String> {

	
	/***
	 * 根据名称 查询赛事 类型<p>
	 * @param numbers 赛事类型编码
	 * @param name 名称
	 * @return MatchType 返回赛事类型名称<p>
	 */
	public MatchType getMatchType(String numbers , String name);
	
	/***
	 * 保存赛事类型<p>
	 * @param numbers 赛事类型编码
	 * @param simpleName名称简称
	 * @param name 名称
	 * @param style 样式
	 * @param jc258Href 链接url地址
	 * @return MatchType 返回赛事类型名称<p>
	 */
	public MatchType saveMatchType(String numbers , String simpleName , String name , String style , String jc258Href);
}
