/**
 * &lt;p&gt;
 * copyright &amp;copy; together 2014, all rights reserved.
 * @author admin
 * @version $Id$
 * @since 1.0
 * 
 */
package com.jcfb.jc.service;

import com.jcfb.entity.MatchType;
import com.jcfb.service.base.ICoreBaseService;

/** 
 * 赛事类别 业务操作接口类<p>
 * @author LingMin 
 * @date 2015-2-28<br>
 * @version 1.0<br>
 */
public interface IMatchTypeService extends ICoreBaseService<MatchType, String> {

	/***
	 * 根据名称 查询赛事 类型<p>
	 * @param numbers 赛事类型编码
	 * @param name 名称
	 * @return MatchType 返回赛事类型名称<p>
	 */
	public MatchType getMatchType(String numbers , String name);
}
