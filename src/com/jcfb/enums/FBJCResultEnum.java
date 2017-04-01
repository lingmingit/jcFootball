/**
 * &lt;p&gt;
 * copyright &amp;copy; together 2014, all rights reserved.
 * @author admin
 * @version $Id$
 * @since 1.0
 * 
 */
package com.jcfb.enums;

import javax.faces.model.SelectItem;

import com.common.enums.EnumUtils;
import com.common.enums.base.CoreBaseEnum;

/** 
 * 足球竞猜结果枚举选项<p>
 * @author LingMin 
 * @date 2015-3-2<br>
 * @version 1.0<br>
 */
public enum FBJCResultEnum implements CoreBaseEnum<FBJCResultEnum, String> {

	Lord("LW", "胜"), Flat("F", "平"), Negative("NW", "负");
	/** 真实值 **/
	private String value;
	/** 显示值 **/
	private String alias;
	
	/**
	 * 构造函数:初始化枚举对象参数<p>
	 * @param value 真实值<br>
	 * @param alias 显示值<br>
	 */
	private FBJCResultEnum(String value, String alias) {
		this.value = value;
		this.alias = alias;
	}
	
	/**
	 * 重写 toString()方法<p>
	 */
	public String toString() {
		return value;
	}
	
	/**
	 * 获取枚举对象的真实值<p>
	 */
	public String getValue() {
		return value;
	}
	
	/**
	 * 获取枚举对象的显示值<p>
	 */
	public String getAlias() {
		return this.alias;
	}
	
	/**
	 * 获取枚举对象数组<p>
	 */
	public Enum[] getEnums() {
		return FBJCResultEnum.values();
	}

	/**
	 * 根据真实值获取枚举对象<p>
	 */
	public FBJCResultEnum getEnum(String value) {
		return (FBJCResultEnum)EnumUtils.getValueEnum(this, value);
	}

	/**
	 * 将枚举对象转换为下拉列表对象数组<p>
	 */
	public SelectItem[] getEnumSelectItem() {
		return EnumUtils.getSelectItemList(this);
	}
	
	/***
	 * 将枚举 转换map 集合对象<p>
	 */
	public java.util.HashMap<String, String> getHashMap() {
		return EnumUtils.getHashMapFromEnums(this);
	}
}
