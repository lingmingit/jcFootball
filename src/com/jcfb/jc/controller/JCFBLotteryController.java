/**
 * &lt;p&gt;
 * copyright &amp;copy; together 2014, all rights reserved.
 * @author admin
 * @version $Id$
 * @since 1.0
 * 
 */
package com.jcfb.jc.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.common.StringUtils;
import com.jcfb.dao.common.ConditionParameter;
import com.jcfb.dao.common.OrderParameter;
import com.jcfb.dao.common.SQLCondition;
import com.jcfb.dao.enums.CompareTypeEnum;
import com.jcfb.dao.enums.OrderTypeEnum;
import com.jcfb.entity.JCFBLottery;
import com.jcfb.jc.service.IJCFBLotteryService;
import com.jcfb.jc.service.IJCFootballResultService;
import com.jcfb.jc.service.IJCFootballService;
import com.jcfb.utils.PaginationHtmlUtils;

/**
 * 竞猜投注列表 后台Controller  <p>
 * @author LingMin 
 * @date 2015-3-12<br>
 * @version 1.0<br>
 */
@Controller
@RequestMapping(value="/jc")
public class JCFBLotteryController {

	
	/** 日志书写对象 **/
	protected org.apache.log4j.Logger logger = org.apache.log4j.Logger.getLogger(this.getClass());
	
	/***自动注入service***/
	@Autowired
	IJCFootballService jcFootballService;
	
	/***自动注入service***/
	@Autowired
	IJCFootballResultService jcFootballResultService;
	/***自动注入service***/
	@Autowired
	IJCFBLotteryService jcFBLotteryService;
	
	
	/**
	 * 竞猜投注
	 * @param request请求对象
	 * @param response 响应对象
	 * @return
	 */
	@RequestMapping(value = "/JCFBLotteryList", method = RequestMethod.GET)
	public String list(HttpServletRequest request , HttpServletResponse response,  ModelMap model) {
		
		String currentPageStr = request.getParameter("currentPage");
		int currentPage = 1;
		if (StringUtils.isNotEmpty(currentPageStr)) {
			currentPage =Integer.valueOf(currentPageStr);
		} 
		model.addAttribute("currentPage", currentPage);
		// 查询当前页数据
		String pageSizeStr = request.getParameter("pageSize");
		int pageSize = 15;
		if (StringUtils.isNotEmpty(pageSizeStr)) {
			pageSize = Integer.valueOf(pageSizeStr);
		}
		model.addAttribute("pageSize", pageSize);
		//设置默认条件，按投注时间 降序排列
		SQLCondition condition = new SQLCondition();
		condition.put(new OrderParameter("createTime", OrderTypeEnum.ORDER_DESC));
		condition.put(new OrderParameter("bizDate", OrderTypeEnum.ORDER_DESC));
		
		String bizDateStr = request.getParameter("bizDate");
		if(StringUtils.isNotEmpty(bizDateStr)){
			condition.put(new ConditionParameter("bizDate", bizDateStr, CompareTypeEnum.COMPARE_EQUEAL));
		}
		model.addAttribute("bizDate", bizDateStr);
		
		// 查询总记录数
		int totalRowCount = jcFBLotteryService.count(condition, null);
		//计算分页相关逻辑
		PaginationHtmlUtils pagination = new PaginationHtmlUtils(currentPage , pageSize , totalRowCount , "/jc/JCFBLotteryList" , request.getContextPath());
		model.addAttribute("pagination", pagination);
		List<JCFBLottery> dataList =  jcFBLotteryService.list(pagination.getStartRowIndex(), pageSize, condition);
		
		model.addAttribute("dataList", dataList);
		return "/jc/JCFBLotteryList"; 
	}
}
