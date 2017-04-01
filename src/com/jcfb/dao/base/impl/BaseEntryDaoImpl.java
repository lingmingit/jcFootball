/**
 * &lt;p&gt;
 * copyright &amp;copy; together 2014, all rights reserved.
 * @author admin
 * @version $Id$
 * @since 1.0
 * 
 */
package com.jcfb.dao.base.impl;

import com.common.CommonUtils;
import com.common.StringUtils;
import com.jcfb.dao.base.IBaseEntryDao;
import com.jcfb.dao.common.ConditionParameter;
import com.jcfb.dao.common.OrderParameter;
import com.jcfb.dao.common.SQLCondition;
import com.jcfb.dao.enums.CompareTypeEnum;
import com.jcfb.dao.enums.OrderTypeEnum;
import com.jcfb.entity.base.BaseEntry;

/** 
 * 分录实体数据库层操作接口实现类<p>
 * @author LingMin 
 * @date 2015-3-2<br>
 * @version 1.0<br>
 */
public class BaseEntryDaoImpl<E extends BaseEntry, ID extends java.io.Serializable, DAOImpl extends IBaseEntryDao<E, ID>> extends CoreBaseDaoImpl<E, ID, DAOImpl> implements IBaseEntryDao<E, ID> {

	
	

	/**
	 * 根据主表关键字与主表关联字段名批量删除分录数据<p>
	 * @param mappedField 主表关联字段名<br>
	 * @param parentKey 主表关键字<br>
	 */
	public void deleteEntryList(ID parentKey, String mappedField) {
		// 当主表关键字不为空时，循环删除分录数据
		if (CommonUtils.isNotEmptyObject(parentKey)) {
			java.util.List<E> entryList = getEntryList(parentKey, mappedField);
			if (CommonUtils.isNotEmptyList(entryList)) {
				for (E entry : entryList) {
					delete(entry);
				}
			}
		}
	}
	
	/**
	 * 根据主表关键字与主表关联字段名获取分录信息<p>
	 */
	public java.util.List<E> getEntryList(ID parentKey, String mappedField) {
		// 声明返回值
		java.util.List<E> rtnList = null;
		// 当主表关键字不为空时,查询主表关联分录
		if (CommonUtils.isNotEmptyObject(parentKey)) {
			// 组装查询条件
			SQLCondition condition = new SQLCondition();
			condition.put(new ConditionParameter((StringUtils
					.isNotEmpty(mappedField) ? mappedField : "parent")
					.concat(".id"), parentKey, CompareTypeEnum.COMPARE_EQUEAL));
			condition.put(new OrderParameter("seq", OrderTypeEnum.ORDER_ASC));
			// 执行数据库查询
			rtnList = list(condition);
		}
		return rtnList;
	}
}
