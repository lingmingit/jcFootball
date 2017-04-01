/**
 * &lt;p&gt;
 * copyright &amp;copy; together 2014, all rights reserved.
 * @author admin
 * @version $Id$
 * @since 1.0
 * 
 */
package com.jcfb.jc.dao.impl;

import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.common.CommonUtils;
import com.common.StringUtils;
import com.jcfb.dao.base.impl.CoreBaseDaoImpl;
import com.jcfb.entity.MatchType;
import com.jcfb.jc.dao.IMatchTypeDao;

/** 
 * 赛事类型 实体数据库操作接口实现类<p>
 * @author LingMin 
 * @date 2015-2-28<br>
 * @version 1.0<br>
 */
@Repository("matchTypeDao")
public class MatchTypeDaoImpl extends CoreBaseDaoImpl<MatchType, String, IMatchTypeDao> implements IMatchTypeDao {

	/***
	 * 保存赛事类型<p>
	 * @param numbers 赛事类型编码
	 * @param simpleName名称简称
	 * @param name 名称
	 * @param style 样式
	 * @param jc258Href 链接url地址
	 * @return MatchType 返回赛事类型名称<p>
	 */
	public MatchType saveMatchType(String numbers , String simpleName , String name , String style , String jc258Href){
		MatchType matchType = new MatchType();
		matchType.setNumbers(numbers);
		matchType.setSimpleName(simpleName);
		matchType.setName(name);
		matchType.setStyle(style);
		matchType.setJc258Href(jc258Href);
		matchType.setIsEnable(true);
		matchType.setIsSystem(true);
		matchType.setCreateTime(new Date());
		matchType.setModifyTime(new Date());
		this.save(matchType);
		return matchType;
	}
	/***
	 * 根据名称 查询赛事 类型<p>
	 * @param numbers 赛事类型编码
	 * @param name 名称
	 * @return MatchType 返回赛事类型名称<p>
	 */
	public MatchType getMatchType(String numbers , String name){
		StringBuffer jpql = new StringBuffer();
		jpql.append(" from MatchType where 1=1");
		Object[] params = null;
		if(StringUtils.isNotEmpty(numbers)){
			jpql.append(" and numbers = ? ");
			params = new Object[]{numbers};
		}
		if(StringUtils.isNotEmpty(name)){
			jpql.append(" and name = ? ");
			if(CommonUtils.isNotEmptyObjectArray(params)){
				params = new Object[]{numbers , name};
			}else{
				params = new Object[]{name};
			}
		}
		List<MatchType> list = (List)this.findByJPQL(jpql.toString(), params);
		if(CommonUtils.isNotEmptyList(list)){
			return list.get(0);
		}
		return null;
	}

}
