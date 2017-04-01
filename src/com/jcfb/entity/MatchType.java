/**
 * &lt;p&gt;
 * copyright &amp;copy; together 2014, all rights reserved.
 * @author admin
 * @version $Id$
 * @since 1.0
 * 
 */
package com.jcfb.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.jcfb.entity.base.DataBaseCore;

/**
 * 
 *  赛事类型基础资料 实体<p>
 * @author LingMin 
 * @date 2015-2-28<br>
 * @version 1.0<br>
 */
@Entity @Table(name = "t_jc_MatchType")
public class MatchType extends DataBaseCore {

	
	/**赛事 样式 **/
	@Column(length=350)
	private String style;
	
	/**赛事类型 url地址  **/
	@Column(length=150)
	private String jc258Href;

	/**
	 * 获取赛事样式<p>
	 * @return  style  赛事样式<br>
	 */
	public String getStyle() {
		return style;
	}

	/**
	 * 设置赛事样式<p>
	 * @param  style  赛事样式<br>
	 */
	public void setStyle(String style) {
		this.style = style;
	}

	/**
	 * 获取赛事类型url地址<p>
	 * @return  jc258Href  赛事类型url地址<br>
	 */
	public String getJc258Href() {
		return jc258Href;
	}

	/**
	 * 设置赛事类型url地址<p>
	 * @param  jc258Href  赛事类型url地址<br>
	 */
	public void setJc258Href(String jc258Href) {
		this.jc258Href = jc258Href;
	}
	
	
}
