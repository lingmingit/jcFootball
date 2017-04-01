/**
 * &lt;p&gt;
 * copyright &amp;copy; together 2014, all rights reserved.
 * @author admin
 * @version $Id$
 * @since 1.0
 * 
 */
package com.jcfb.jc.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jcfb.entity.JCFootballResult;
import com.jcfb.jc.dao.IJCFootballResultDao;
import com.jcfb.jc.service.IJCFootballResultService;
import com.jcfb.service.base.impl.CoreBaseServiceImpl;

/** 
 * 竞猜足球结果 业务操作service实现类<p>
 * @author LingMin 
 * @date 2015-2-26<br>
 * @version 1.0<br>
 */
@Service("jcFootballResultService")
public class JCFootballResultServiceImpl extends CoreBaseServiceImpl<JCFootballResult, String> implements IJCFootballResultService {

	/**数据库操作dao接口***/
	protected IJCFootballResultDao jcFootballResultDao;
	/***
	 * 构造方法注入
	 * @param jpaBaseDao
	 */
	@Autowired(required=true)
	public JCFootballResultServiceImpl(IJCFootballResultDao jcFootballResultDao) {
		super(jcFootballResultDao);
		this.jcFootballResultDao = jcFootballResultDao;
	}
	
	
	/***
	 * 根据竞猜历史记录id  查询竞猜结果id<p>
	 * @param jcFootballId 竞猜历史记录id
	 * @return JCFootballResult 返回竞猜结果实体对象<p>
	 */
	public JCFootballResult getJCFootballResult(String jcFootballId){
		return this.jcFootballResultDao.getJCFootballResult(jcFootballId);
	}

	/***
	 * 批量保存方法
	 * @param list 实体list对象
	 * @return 返回处理结果
	 * ****/
	@Transactional
	public String saveBatch(List<JCFootballResult> list){
		return this.jcFootballResultDao.saveBatch(list);
	}
	

}
