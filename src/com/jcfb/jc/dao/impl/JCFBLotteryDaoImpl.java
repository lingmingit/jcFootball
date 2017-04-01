/**
 * &lt;p&gt;
 * copyright &amp;copy; together 2014, all rights reserved.
 * @author admin
 * @version $Id$
 * @since 1.0
 * 
 */
package com.jcfb.jc.dao.impl;

import org.springframework.stereotype.Repository;

import com.jcfb.dao.base.impl.CoreBaseDaoImpl;
import com.jcfb.entity.JCFBLottery;
import com.jcfb.jc.dao.IJCFBLotteryDao;

/**
 * 彩票数据库操作接口实现类 <p>
 * @author LingMin 
 * @date 2015-3-2<br>
 * @version 1.0<br>
 */
@Repository("jcFBLotteryDao")
public class JCFBLotteryDaoImpl extends CoreBaseDaoImpl<JCFBLottery, String, IJCFBLotteryDao> implements IJCFBLotteryDao {

	
}
