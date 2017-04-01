/**
 * &lt;p&gt;
 * copyright &amp;copy; together 2014, all rights reserved.
 * @author admin
 * @version $Id$
 * @since 1.0
 * 
 */
package com.jcfb.sync.utils;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import com.common.CommonUtils;
import com.common.StringUtils;

/** 
 * 竞猜258网站相关配置信息获取<p>
 * @author LingMin 
 * @date 2015-2-10<br>
 * @version 1.0<br>
 */
public class JC258Utils {

	/**竞猜258网站相关配置信息文件名称***/
	private static final String JC258PropertiesFileName = "/jc258.properties";
	
	/**HTTP请求头信息【获取数据时，需要的头信息】***/
	private static final String JC258HTTPHeadKeyName = "http.head.key";
	/**HTTP请求头信息【获取数据时，需要的头value信息】***/
	private static final String JC258HTTPHeadValue = "http.head.value";
	/**竞猜258网站相关配置信息   #竞猜258 足球 URL 地址***/
	private static final String JC258FootballURLKeyName = "jc.football.url";
	/**竞猜258网站相关配置信息   #竞猜网页 table id***/
	private static final String JC258FootballTableIdKeyName = "jc.football.table.id";
	/**竞猜258网站相关配置信息   #竞猜数据 tr行 class样式  过滤规则  <tr id="tr_425920" theIndex="1" gn="3051" gameno="3051" class="odds odd_tr_0" gameDate = "2015-02-25_051"***/
	private static final String JC258FootballTableTrFilterRulePathKeyName = "jc.football.table.tr.filter.RulePath";
	/**竞猜258网站相关配置信息   #竞猜数据 tr行 日期标识 属性名称***/
	public static final String JC258FootballTableTrAttrGameDateKeyName = "jc.football.table.tr.attribute.gameDate";
	/**竞猜258网站相关配置信息   #竞猜数据 tr行 class样式  过滤规则 分割符号 @***/
	public static final String JC258FootballTableTrFilterRulePathSplitChar = "@";
	/**竞猜258网站相关配置信息   #竞猜数据 中文标识***/
	public static final String JC258FootballNOLordFlatNegative = "未开售胜平负玩法";
	
	
	/**竞猜258网站相关配置信息   #竞猜258 足球比赛结果 URL 地址***/
	private static final String JC258FootballResultURLKeyName = "jc.football.result.url";
	/***#竞猜结果网页 table id,如： <table border="0" cellspacing="0" cellpadding="0" class="sgexcle" id="tab_result">***/
	private static final String JC258FootballResultTableIdKeyName = "jc.football.result.table.id";
	/***#竞猜结果 数据过滤规则配置*****/
	private static final String JC258FootballResultTableTrFilterRulePathKeyName = "jc.football.result.table.tr.filter.RulePath";
	
	/**竞猜258网站相关配置信息   #竞猜结果 ，苏克雷大学<span style="color:#00f">(1)</span>VS 克鲁塞罗***/
	public static final String JC258FootballTableResultTeamNameSplitChar = "VS";
	
	/****
	 * 获取配置文件中的相关配置信息【*数据过滤规则配置】<p>
	 * @return String 返回 配置文件信息<p>
	 */
	public static String getJC258FootballResultTableTrFilterRulePathValue(){
		return JC258Utils.getJC258PropertiesKeyValue(JC258FootballResultTableTrFilterRulePathKeyName);
	}
	
	/****
	 * 获取配置文件中的相关配置信息【*#竞猜结果网页 table id】<p>
	 * @return String 返回 配置文件信息<p>
	 */
	public static String getJC258FootballResultTableIdValue(){
		return JC258Utils.getJC258PropertiesKeyValue(JC258FootballResultTableIdKeyName);
	}
	
	/****
	 * 获取配置文件中的相关配置信息【#竞猜258 足球比赛结果 URL 地址】<p>
	 * @return String 返回 配置文件信息<p>
	 */
	public static String getJC258FootballResultURLValue(){
		return JC258Utils.getJC258PropertiesKeyValue(JC258FootballResultURLKeyName);
	}
	
	
	/****
	 * 获取配置文件中的相关配置信息【竞猜258网站相关配置信息   #竞猜数据 tr行 class样式  过滤规则】<p>
	 * @return String 返回 配置文件信息<p>
	 */
	public static String getJC258FootballTableTrFilterRulePathValue(){
		return JC258Utils.getJC258PropertiesKeyValue(JC258FootballTableTrFilterRulePathKeyName);
	}
	
	/****
	 * 获取配置文件中的相关配置信息【#竞猜数据 tr行 日期标识 属性名称】<p>
	 * @return String 返回 配置文件信息<p>
	 */
	public static String getJC258FootballTableTrAttrGameDateValue(){
		return JC258Utils.getJC258PropertiesKeyValue(JC258FootballTableTrAttrGameDateKeyName);
	}
	
	
	
	
	/****
	 * 获取配置文件中的相关配置信息【HTTP请求头信息，获取数据时，需要的头信息】<p>
	 * @return String 返回 配置文件信息<p>
	 */
	public static String getJC258HTTPHeadKey(){
		return JC258Utils.getJC258PropertiesKeyValue(JC258HTTPHeadKeyName);
	}
	
	
	/****
	 * 获取配置文件中的相关配置信息【HTTP请求头信息，获取数据时，需要的头value信息】<p>
	 * @return String 返回 配置文件信息<p>
	 */
	public static String getJC258HTTPHeadValue(){
		return JC258Utils.getJC258PropertiesKeyValue(JC258HTTPHeadValue);
	}
	
	
	/****
	 * 获取配置文件中的相关配置信息【#竞猜258 足球 URL 地址】<p>
	 * @return String 返回 配置文件信息<p>
	 */
	public static String getJC258FootballURLValue(){
		return JC258Utils.getJC258PropertiesKeyValue(JC258FootballURLKeyName);
	}
	
	
	/****
	 * 获取配置文件中的相关配置信息【 #竞猜网页 table id】<p>
	 * @return String 返回 配置文件信息<p>
	 */
	public static String getJC258FootballTableIdValue(){
		return JC258Utils.getJC258PropertiesKeyValue(JC258FootballTableIdKeyName);
	}

	/****
	 * 获取配置文件中的相关配置信息
	 * @param key 配置文件key信息
	 * @return 返回key对应的value信息
	 */
	public static String getJC258PropertiesKeyValue(String key){
		InputStream inputStream = null;
		try {
			inputStream = JC258Utils.class.getResourceAsStream(JC258PropertiesFileName);
			Properties properties = new Properties();
			properties.load(inputStream);
			String value = properties.getProperty(key);
			if(StringUtils.isNotEmpty(value)){
				value = value.trim();
			}
			return value;
		} catch (IOException e) {
			e.printStackTrace();
		}finally{
			try{
				if(CommonUtils.isNotEmptyObject(inputStream)){
					inputStream.close();
				}
			}catch(IOException e){
				e.printStackTrace();
			}
		}
		return null;
	}
	
}
