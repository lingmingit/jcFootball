/**
 * &lt;p&gt;
 * copyright &amp;copy; together 2014, all rights reserved.
 * @author admin
 * @version $Id$
 * @since 1.0
 * 
 */
package com.jcfb.jc.dao;

import com.jcfb.dao.base.IBaseEntryDao;
import com.jcfb.entity.JCFBLotteryEntry;
import com.jcfb.entity.JCFootball;
import com.jcfb.entity.JCFootballResult;

/** 
 * 彩票分录实体数据库操作接口类<p>
 * @author LingMin 
 * @date 2015-3-2<br>
 * @version 1.0<br>
 */
public interface IJCFBLotteryEntryDao extends IBaseEntryDao<JCFBLotteryEntry, String> {

	/****
	 * 更新竞猜结果<p>
	 * @param jcFootball 竞猜记录对象
	 * @param jcFootballResult 竞猜结果记录对象<p>
	 * @return String 返回提示信息
	 */
	public String updateJCFBResult(JCFootball jcFootball , JCFootballResult jcFootballResult);
}
