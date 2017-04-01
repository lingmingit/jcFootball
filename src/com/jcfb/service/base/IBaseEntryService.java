package com.jcfb.service.base;

import com.jcfb.entity.base.BaseEntry;





/****
 * 分录业务操作基类接口<p>
 * @author LingMin 
 * @date 2015-3-2<br>
 * @version 1.0<br>
 * @param <E>
 * @param <ID>
 */
public interface IBaseEntryService<E extends BaseEntry, ID extends java.io.Serializable> extends ICoreBaseService<E, ID>{

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
