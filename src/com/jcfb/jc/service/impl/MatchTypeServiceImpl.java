/**
 * &lt;p&gt;
 * copyright &amp;copy; together 2014, all rights reserved.
 * @author admin
 * @version $Id$
 * @since 1.0
 * 
 */
package com.jcfb.jc.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jcfb.entity.MatchType;
import com.jcfb.jc.dao.IMatchTypeDao;
import com.jcfb.jc.service.IMatchTypeService;
import com.jcfb.service.base.impl.CoreBaseServiceImpl;

/** 
 * 赛事类别 业务操作接口实现类<p>
 * @author LingMin 
 * @date 2015-2-28<br>
 * @version 1.0<br>
 */
@Service("matchTypeService")
public class MatchTypeServiceImpl extends CoreBaseServiceImpl<MatchType, String> implements IMatchTypeService {

	
	/**赛事类型 数据库操作接口**/
	protected IMatchTypeDao matchTypeDao;
	
	/***
	 * 构造方法注入数据库操作接口
	 * @param matchTypeDao
	 */
	@Autowired(required=true)
	public MatchTypeServiceImpl(IMatchTypeDao matchTypeDao) {
		super(matchTypeDao);
		this.matchTypeDao = matchTypeDao;
	}

	
	/***
	 * 根据名称 查询赛事 类型<p>
	 * @param numbers 赛事类型编码
	 * @param name 名称
	 * @return MatchType 返回赛事类型名称<p>
	 */
	public MatchType getMatchType(String numbers , String name){
		return matchTypeDao.getMatchType(numbers, name);
	}
}
