package com.jcfb.jc.service;

import java.util.List;

import com.jcfb.entity.JCFootball;
import com.jcfb.service.base.ICoreBaseService;



/** 
 * 竞猜足球业务操作service 接口<p>
 * @author LingMin 
 * @date 2015-2-9<br>
 * @version 1.0<br>
 */
public interface IJCFootballService extends ICoreBaseService<JCFootball, String> {

	
	
	/***
	 * 批量保存方法
	 * @param list 实体list对象
	 * @return 返回处理结果
	 * ****/
	public String saveBatch(List<JCFootball> list);
}
