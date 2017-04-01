/**
 * &lt;p&gt;
 * copyright &amp;copy; together 2014, all rights reserved.
 * @author admin
 * @version $Id$
 * @since 1.0
 * 
 */
package com.jcfb.service.base.impl;

import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.jcfb.dao.base.IBaseEntryDao;
import com.jcfb.entity.base.BaseEntry;
import com.jcfb.service.base.IBaseEntryService;

/** <p>
 * @author LingMin 
 * @date 2015-3-2<br>
 * @version 1.0<br>
 */
public class BaseEntryServiceImpl<E extends BaseEntry, ID extends java.io.Serializable> extends CoreBaseServiceImpl<E, ID> implements IBaseEntryService<E, ID> {
	
	
	/** 分录信息数据库层操作对象 **/
	protected IBaseEntryDao<E, ID> baseEntryDao;
	
	/**
	 * 构造函数:初始化分录信息数据库层操作对象<p>
	 * @param entryDataDao 分录信息数据库层操作对象<br>
	 */
	public BaseEntryServiceImpl(IBaseEntryDao<E, ID> baseEntryDao) {
		super(baseEntryDao);
		this.baseEntryDao = baseEntryDao;
	}

	/**
	 * 根据主表关键字与主表关联字段名批量删除分录数据<p>
	 */
	@Transactional(propagation=Propagation.REQUIRED)
	public void deleteEntryList(ID parentKey, String mappedField) {
		baseEntryDao.deleteEntryList(parentKey, mappedField);
	}

	/**
	 * 根据主表关键字与主表关联字段名获取分录信息<p>
	 */
	public java.util.List<E> getEntryList(ID parentKey, String mappedField) {
		return baseEntryDao.getEntryList(parentKey, mappedField);
	}
}
