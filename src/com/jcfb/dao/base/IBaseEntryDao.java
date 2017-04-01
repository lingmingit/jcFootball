/**
 * &lt;p&gt;
 * copyright &amp;copy; together 2014, all rights reserved.
 * @author admin
 * @version $Id$
 * @since 1.0
 * 
 */
package com.jcfb.dao.base;

import com.jcfb.entity.base.BaseEntry;

/** 
 * 分录实体数据库层操作接口类<p>
 * @author LingMin 
 * @date 2015-3-2<br>
 * @version 1.0<br>
 */
public interface IBaseEntryDao<E extends BaseEntry, ID extends java.io.Serializable> extends ICoreBaseDao<E, ID> {

	/**
	 * 根据主表关键字与主表关联字段名批量删除分录数据<p>
	 * @param mappedField 主表关联字段名<br>
	 * @param parentKey 主表关键字<br>
	 */
	public void deleteEntryList(ID parentKey, String mappedField);
	
	
	/**
	 * 根据主表关键字与主表关联字段名获取分录信息<p>
	 * @param parentKey 主表关键字<br>
	 * @param mappedField 主表关联字段名<br>
	 * @return 分录信息<br>
	 */
	public java.util.List<E> getEntryList(ID parentKey, String mappedField);
}
