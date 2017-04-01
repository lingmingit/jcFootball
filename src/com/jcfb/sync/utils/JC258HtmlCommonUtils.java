/**
 * &lt;p&gt;
 * copyright &amp;copy; together 2014, all rights reserved.
 * @author admin
 * @version $Id$
 * @since 1.0
 * 
 */
package com.jcfb.sync.utils;

import com.common.StringUtils;

/** 
 * 竞猜数据解析 公共方法<p>
 * @author LingMin 
 * @date 2015-2-28<br>
 * @version 1.0<br>
 */
public class JC258HtmlCommonUtils {

	
	
	
	/***
	 * 解析 赛事编码<p>
	 * 解析 url 地址 最后的编号  140 ，如：  http://data.jc258.cn/league/home/140
	 * @return String 返回<p>
	 */
	public static String getMatchNum(String temp){
		//解析 url 地址 最后的编号 ，如：  http://data.jc258.cn/league/home/140
		if(StringUtils.isNotEmpty(temp)){
			temp = temp.substring(temp.lastIndexOf("/")+1);
		}
		return temp;
	}
}
