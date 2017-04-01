/**
 * &lt;p&gt;
 * copyright &amp;copy; together 2014, all rights reserved.
 * @author admin
 * @version $Id$
 * @since 1.0
 * 
 */
package com.jcfb.jc.dao;

import java.util.List;

import com.jcfb.dao.base.ICoreBaseDao;
import com.jcfb.entity.JCFootball;
import com.jcfb.entity.JCFootballResult;

/**
 * 竞猜数据结果 数据库操作dao <p>
 * @author LingMin 
 * @date 2015-2-26<br>
 * @version 1.0<br>
 */
public interface IJCFootballResultDao extends  ICoreBaseDao<JCFootballResult, String> {

	
	/***
	 * 根据竞猜历史记录id  查询竞猜结果id<p>
	 * @param jcFootballId 竞猜历史记录id
	 * @return JCFootballResult 返回竞猜结果实体对象<p>
	 */
	public JCFootballResult getJCFootballResult(String jcFootballId);
	/***
	 * 批量保存方法
	 * @param list 实体list对象
	 * @return 返回处理结果
	 * ****/
	public String saveBatch(List<JCFootballResult> list);
}
