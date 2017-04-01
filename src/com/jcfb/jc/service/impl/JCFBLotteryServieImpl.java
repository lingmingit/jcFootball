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

import com.jcfb.entity.JCFBLottery;
import com.jcfb.jc.dao.IJCFBLotteryDao;
import com.jcfb.jc.service.IJCFBLotteryService;
import com.jcfb.service.base.impl.CoreBaseServiceImpl;

/** 
 * 彩票投注业务操作接口实现类<p>
 * @author LingMin 
 * @date 2015-3-2<br>
 * @version 1.0<br>
 */
@Service("jcFBLotteryService")
public class JCFBLotteryServieImpl extends CoreBaseServiceImpl<JCFBLottery, String> implements IJCFBLotteryService {

	/**数据库操作接口类***/
	protected IJCFBLotteryDao jcFBLotteryDao;
	
	/***
	 * 构造方法注入数据库操作接口类
	 * @param jcFBLotteryDao
	 */
	@Autowired(required=true)
	public JCFBLotteryServieImpl(IJCFBLotteryDao jcFBLotteryDao){
		super(jcFBLotteryDao);
		this.jcFBLotteryDao = jcFBLotteryDao;
	}
	
	
	
}
