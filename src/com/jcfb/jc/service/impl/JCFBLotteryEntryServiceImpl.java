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

import com.jcfb.entity.JCFBLotteryEntry;
import com.jcfb.jc.dao.IJCFBLotteryEntryDao;
import com.jcfb.jc.service.IJCFBLotteryEntryService;
import com.jcfb.service.base.impl.BaseEntryServiceImpl;

/** 
 * 投注彩票分录详情 业务操作接口实现类<p>
 * @author LingMin 
 * @date 2015-3-2<br>
 * @version 1.0<br>
 */
@Service("jcFBLotteryEntryService")
public class JCFBLotteryEntryServiceImpl extends BaseEntryServiceImpl<JCFBLotteryEntry, String> implements IJCFBLotteryEntryService {

	/**数据库操作接口类**/
	protected IJCFBLotteryEntryDao jcFBLotteryEntryDao;
	
	/***
	 * 构造方法注入数据库操作接口类
	 */
	@Autowired(required=true)
	public JCFBLotteryEntryServiceImpl(IJCFBLotteryEntryDao jcFBLotteryEntryDao){
		super(jcFBLotteryEntryDao);
		this.jcFBLotteryEntryDao = jcFBLotteryEntryDao;
	}
	
	
	
	
	
	
	
}
