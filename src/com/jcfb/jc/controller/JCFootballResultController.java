/**
 * &lt;p&gt;
 * copyright &amp;copy; together 2014, all rights reserved.
 * @author admin
 * @version $Id$
 * @since 1.0
 * 
 */
package com.jcfb.jc.controller;

import java.io.IOException;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.common.CommonUtils;
import com.common.StringUtils;
import com.common.WebHandleUtils;
import com.common.date.DateUtils;
import com.jcfb.dao.common.ConditionParameter;
import com.jcfb.dao.common.OrderParameter;
import com.jcfb.dao.common.SQLCondition;
import com.jcfb.dao.enums.CompareTypeEnum;
import com.jcfb.dao.enums.OrderTypeEnum;
import com.jcfb.entity.JCFootballResult;
import com.jcfb.jc.service.IJCFootballResultService;
import com.jcfb.jc.service.IJCFootballService;
import com.jcfb.sync.utils.JCResultHtmlDataUtils;
import com.jcfb.utils.PaginationHtmlUtils;

/** 
 * 竞猜足球 结果后台类<p>
 * @author LingMin 
 * @date 2015-2-26<br>
 * @version 1.0<br>
 */
@Controller
@RequestMapping(value="/jc")
public class JCFootballResultController {

	
	/** 日志书写对象 **/
	protected org.apache.log4j.Logger logger = org.apache.log4j.Logger.getLogger(this.getClass());
	
	/***自动注入service***/
	@Autowired
	IJCFootballService jcFootballService;
	
	/***自动注入service***/
	@Autowired
	IJCFootballResultService jcFootballResultService;
	
	
	/**
	 * 页面跳转
	 * @param request请求对象
	 * @param response 响应对象
	 * @return
	 */
	@RequestMapping(value = "/JCFootballResultDetail", method = RequestMethod.GET)
	public String resultDetail(HttpServletRequest request , HttpServletResponse response,  ModelMap model) {
		String jcFootballId = request.getParameter("jcFootballId");
		if(StringUtils.isEmpty(jcFootballId)){
			return WebHandleUtils.gotoMsgPage("竞猜记录id为空!!", request, response);
		}
		JCFootballResult jcFootballResult = jcFootballResultService.getJCFootballResult(jcFootballId);
		if(CommonUtils.isEmptyObject(jcFootballResult) || StringUtils.isEmpty(jcFootballResult.getId())){
			return WebHandleUtils.gotoMsgPage("没有赛果信息!!", request, response);
		}
		model.addAttribute("data", jcFootballResult);
		return "/jc/JCFootballResultDetail"; 
	}
	/**
	 * 页面跳转
	 * @param request请求对象
	 * @param response 响应对象
	 * @return
	 */
	@RequestMapping(value = "/JCFootballResult", method = RequestMethod.GET)
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
		
		//设置默认排序，按创建时间降序排列
		SQLCondition condition = new SQLCondition();
		condition.put(new OrderParameter("jcDate", OrderTypeEnum.ORDER_DESC));
		condition.put(new OrderParameter("createTime", OrderTypeEnum.ORDER_DESC));
		
		String jcDateStr = request.getParameter("jcDate2");
		if(StringUtils.isNotEmpty(jcDateStr)){
			condition.put(new ConditionParameter("jcDate", jcDateStr, CompareTypeEnum.COMPARE_EQUEAL));
		}
		String matchName = request.getParameter("matchName");
		matchName = WebHandleUtils.codeToString(matchName);//url传递中文参数 编码转换方法
		if(StringUtils.isNotEmpty(matchName)){
			condition.put(new ConditionParameter("matchName", matchName, CompareTypeEnum.COMPARE_LIKE));
		}
		String teamName = request.getParameter("teamName");
		teamName = WebHandleUtils.codeToString(teamName);//url传递中文参数 编码转换方法
		if(StringUtils.isNotEmpty(teamName)){
			StringBuffer sql = new StringBuffer();
			sql.append("(homeTeamName like '%").append(teamName).append("%'").append(" or visitingTeamName like '%").append(teamName).append("%')");
			condition.put(sql.toString());
		}
		model.addAttribute("jcDate2", jcDateStr);
		model.addAttribute("matchName", matchName);
		model.addAttribute("teamName", teamName);
		
		
		// 查询总记录数
		int totalRowCount = jcFootballResultService.count(condition, null);
		//计算分页相关逻辑
		PaginationHtmlUtils pagination = new PaginationHtmlUtils(currentPage , pageSize , totalRowCount , "/jc/JCFootballResult" , request.getContextPath());
		model.addAttribute("pagination", pagination);
		
		List<JCFootballResult> list =  jcFootballResultService.list(pagination.getStartRowIndex(), pageSize, condition);
		model.addAttribute("jcFootballResultList", list);
		model.addAttribute("jcDate", DateUtils.getDateBySpecifiedFormatter(DateUtils.addDaysToCurrentTime(-2) , DateUtils.DateFormatter));
		
		return "/jc/JCFootballResult"; 
	}
	
	
	/**
	 * 同步竞猜结果数据到 my db
	 * @param request请求对象
	 * @param response 响应对象
	 * @return
	 */
	@RequestMapping(value = "/syncJCResultData", method = RequestMethod.POST)
	public String syncJCResultData(HttpServletRequest request , HttpServletResponse response) {
		logger.info("syncJCResultData");
		String jcDateStr = request.getParameter("jcDate");
		Date jcDate = null;
		if(StringUtils.isNotEmpty(jcDateStr)){
			jcDate = DateUtils.getDateFromSpecifiedString(jcDateStr , DateUtils.DateFormatter);
		}
		StringBuffer resultMsg = new StringBuffer();
		resultMsg.append("{");
		try {
			List<JCFootballResult> list = JCResultHtmlDataUtils.getJC258URLToJCFootballResultList(jcDate);
			if(CommonUtils.isNotEmptyList(list)){
				String result = jcFootballResultService.saveBatch(list);
				//{flag:true , msg:''};
				resultMsg.append("flag:true,msg:\"").append(result).append("\"");
			}else{
				resultMsg.append("flag:false,msg:\"").append("竞猜日期【").append(jcDateStr).append("】没有数据").append("\"");
			}
		} catch (Exception e) {
			e.printStackTrace();
			resultMsg.append("flag:false,msg:\"").append(e.getMessage()).append("\"");
		}
		resultMsg.append("}");
		try {
			response.setCharacterEncoding("UTF-8");
			response.setContentType("text/plain");
			response.getWriter().write(resultMsg.toString());
			response.getWriter().close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null; 
	}
	
}
