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
 * 基础资料实体核心基础抽象类【编码、名称】<p>
 * @author LingMin 
 * @date 2015-2-28<br>
 * @version 1.0<br>
 */
@javax.persistence.MappedSuperclass
public class DataBaseCore extends BaseCore {

	/**编码 **/
	@Column(length=50)
	protected java.lang.String numbers;
	/**名称 **/
	@Column(length=100)
	protected java.lang.String name;
	/** 简称 */
	@Column(length=30)
	protected java.lang.String simpleName;
	/** 启用标志 **/
	@Column(length=1)
	protected java.lang.Boolean isEnable = false;
	/** 系统标志 **/
	@Column(length=1)
	protected java.lang.Boolean isSystem = false;
	
	/**
	 * 获取编码<p>
	 * @return  numbers  编码<br>
	 */
	public java.lang.String getNumbers() {
		return numbers;
	}

	/**
	 * 设置编码<p>
	 * @param  numbers  编码<br>
	 */
	public void setNumbers(java.lang.String numbers) {
		this.numbers = numbers;
	}

	/**
	 * 获取名称<p>
	 * @return  name  名称<br>
	 */
	public java.lang.String getName() {
		return name;
	}

	/**
	 * 设置名称<p>
	 * @param  name  名称<br>
	 */
	public void setName(java.lang.String name) {
		this.name = name;
	}

	/**
	 * 获取简称<p>
	 * @return  simpleName  简称<br>
	 */
	public java.lang.String getSimpleName() {
		return simpleName;
	}

	/**
	 * 设置简称<p>
	 * @param  simpleName  简称<br>
	 */
	public void setSimpleName(java.lang.String simpleName) {
		this.simpleName = simpleName;
	}

	/**
	 * 获取启用标志<p>
	 * @return  isEnable  启用标志<br>
	 */
	public java.lang.Boolean getIsEnable() {
		return isEnable;
	}

	/**
	 * 设置启用标志<p>
	 * @param  isEnable  启用标志<br>
	 */
	public void setIsEnable(java.lang.Boolean isEnable) {
		this.isEnable = isEnable;
	}

	/**
	 * 获取系统标志<p>
	 * @return  isSystem  系统标志<br>
	 */
	public java.lang.Boolean getIsSystem() {
		return isSystem;
	}

	/**
	 * 设置系统标志<p>
	 * @param  isSystem  系统标志<br>
	 */
	public void setIsSystem(java.lang.Boolean isSystem) {
		this.isSystem = isSystem;
	}
	
	
	
}
