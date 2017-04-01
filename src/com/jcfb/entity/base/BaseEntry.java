/**
 * &lt;p&gt;
 * copyright &amp;copy; together 2014, all rights reserved.
 * @author admin
 * @version $Id$
 * @since 1.0
 * 
 */
package com.jcfb.entity.base;

import javax.persistence.Column;

/**
 * 分录抽象实体基类 <p>
 * @author LingMin 
 * @date 2015-3-2<br>
 * @version 1.0<br>
 */
@javax.persistence.MappedSuperclass
public abstract class BaseEntry extends Core {

	/**分录序号 **/
	@Column
	protected Integer seq;
	
	/** 备注信息 **/
	@Column(length=250)
	protected java.lang.String description;

	/**
	 * 获取分录序号<p>
	 * @return  seq  分录序号<br>
	 */
	public Integer getSeq() {
		return seq;
	}

	/**
	 * 设置分录序号<p>
	 * @param  seq  分录序号<br>
	 */
	public void setSeq(Integer seq) {
		this.seq = seq;
	}

	/**
	 * 获取备注信息<p>
	 * @return  description  备注信息<br>
	 */
	public java.lang.String getDescription() {
		return description;
	}

	/**
	 * 设置备注信息<p>
	 * @param  description  备注信息<br>
	 */
	public void setDescription(java.lang.String description) {
		this.description = description;
	}
	
	
	
}
