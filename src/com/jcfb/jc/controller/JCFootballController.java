package com.jcfb.jc.controller;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
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
import com.jcfb.entity.JCFBLottery;
import com.jcfb.entity.JCFBLotteryEntry;
import com.jcfb.entity.JCFootball;
import com.jcfb.jc.service.IJCFBLotteryService;
import com.jcfb.jc.service.IJCFootballResultService;
import com.jcfb.jc.service.IJCFootballService;
import com.jcfb.sync.utils.JCHtmlDataUtils;
import com.jcfb.utils.PaginationHtmlUtils;




/***
 * 足球竞猜 后台Controller<p>
 * @author LingMin 
 * @date 2015-02-09<br>
 * @version 1.0<br>
 */
@Controller
@RequestMapping(value="/jc")
public class JCFootballController {

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
	 * 当天以后竞猜记录查询
	 * @param request请求对象
	 * @param response 响应对象
	 * @return
	 */
	@RequestMapping(value = "/JCFootball", method = RequestMethod.GET)
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
		//设置默认条件，竞猜日期大于等于 当前日期的数据进行显示
		SQLCondition condition = new SQLCondition();
		String todayDateStr = DateUtils.getCurrentDateBySpecifiedFormatter(DateUtils.DateFormatter);
		condition.put(new ConditionParameter("jcDate", "'".concat(todayDateStr).concat("'"), CompareTypeEnum.COMPARE_MORE_EQUEAL));
		
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
		int totalRowCount = jcFootballService.count(condition, null);
		//计算分页相关逻辑
		PaginationHtmlUtils pagination = new PaginationHtmlUtils(currentPage , pageSize , totalRowCount , "/jc/JCFootball" , request.getContextPath());
		model.addAttribute("pagination", pagination);
		List<JCFootball> jcFootballList =  jcFootballService.list(pagination.getStartRowIndex(), pageSize, condition);
		
		model.addAttribute("jcFootballList", jcFootballList);
		model.addAttribute("jcDate", DateUtils.getCurrentDateBySpecifiedFormatter(DateUtils.DateFormatter));
		return "/jc/JCFootball"; 
	}
	
	
	
	/**
	 * 同步竞猜数据到 my db
	 * @param request请求对象
	 * @param response 响应对象
	 * @return
	 */
	@RequestMapping(value = "/syncJCData", method = RequestMethod.POST)
	public String syncJCData(HttpServletRequest request , HttpServletResponse response) {
		logger.info("syncJCData");
		String jcDateStr = request.getParameter("jcDate");
		Date jcDate = null;
		if(StringUtils.isNotEmpty(jcDateStr)){
			jcDate = DateUtils.getDateFromSpecifiedString(jcDateStr , DateUtils.DateFormatter);
		}
		StringBuffer resultMsg = new StringBuffer();
		resultMsg.append("{");
		try {
			List<JCFootball> list = JCHtmlDataUtils.getJC258URLToJCFootballList(jcDate);
			if(CommonUtils.isNotEmptyList(list)){
				String result = jcFootballService.saveBatch(list);
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
	
	
	/**
	 * 竞猜历史记录查询方法
	 * @param request请求对象
	 * @param response 响应对象
	 * @return
	 */
	@RequestMapping(value = "/JCFootballHistory", method = RequestMethod.GET)
	public String historyList(HttpServletRequest request , HttpServletResponse response,  ModelMap model) {
		
		String currentPageStr = request.getParameter("currentPage");
		int currentPage = 1;
		if (StringUtils.isNotEmpty(currentPageStr)) {
			currentPage =Integer.valueOf(currentPageStr);
		} 
		model.addAttribute("currentPage", currentPage);
		// 查询当前页数据
		String pageSizeStr = request.getParameter("pageSize");
		int pageSize = 20;
		if (StringUtils.isNotEmpty(pageSizeStr)) {
			pageSize = Integer.valueOf(pageSizeStr);
		}
		model.addAttribute("pageSize", pageSize);
		//设置默认条件，竞猜日期大于等于 当前日期的数据进行显示
		SQLCondition condition = new SQLCondition();
		String todayDateStr = DateUtils.getCurrentDateBySpecifiedFormatter(DateUtils.DateFormatter);
		condition.put(new ConditionParameter("jcDate", "'".concat(todayDateStr).concat("'"), CompareTypeEnum.COMPARE_LESS));
		condition.put(new OrderParameter("jcDate", OrderTypeEnum.ORDER_DESC));
		
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
		int totalRowCount = jcFootballService.count(condition, null);
		//计算分页相关逻辑
		PaginationHtmlUtils pagination = new PaginationHtmlUtils(currentPage , pageSize , totalRowCount , "/jc/JCFootballHistory" , request.getContextPath());
		model.addAttribute("pagination", pagination);
		List<JCFootball> jcFootballList =  jcFootballService.list(pagination.getStartRowIndex(), pageSize, condition);
		
		model.addAttribute("jcFootballList", jcFootballList);
		model.addAttribute("jcDate", DateUtils.getCurrentDateBySpecifiedFormatter(DateUtils.DateFormatter));
		return "/jc/JCFootballHistory"; 
	}
	
	
	
	/**
	 * 页面跳转
	 * @param request请求对象
	 * @param response 响应对象
	 * @return
	 */
	@RequestMapping(value = "/JCFootballAddBetting", method = RequestMethod.POST)
	public String addBetting(HttpServletRequest request , HttpServletResponse response,  ModelMap model) {
		StringBuffer resultMsg = new StringBuffer();
		resultMsg.append("{");
		try {
			JCFBLottery jcFBLottery = this.getJCFBLottery(request);
			String id = this.jcFBLotteryService.save(jcFBLottery);
			if(StringUtils.isNotEmpty(id)){
				resultMsg.append("flag:true,msg:\"").append("投注成功").append("\"");
			}else{
				resultMsg.append("flag:false,msg:\"").append("投注失败").append("\"");
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
	
	
	/***
	 * 将请求参数封装为 彩票实体对象<p>
	 * @param request 请求对象
	 * @return JCFBLottery 返回实体对象<p>
	 */
	private JCFBLottery getJCFBLottery(HttpServletRequest request){
		
		String type = request.getParameter("type");
		String multiple = request.getParameter("multiple");
		String bettingAmount = request.getParameter("bettingAmount");
		String preAmount = request.getParameter("preAmount");
		
		JCFBLottery jcFBLottery = new JCFBLottery();
		jcFBLottery.setBizDate(new Date());
		jcFBLottery.setCreateTime(new Date());
		jcFBLottery.setModifyTime(new Date());
		String prefixStr = DateUtils.getCurrentDateBySpecifiedFormatter(DateUtils.DateFormatter2);
		prefixStr = "CP".concat(prefixStr);
		jcFBLottery.setNumbers(StringUtils.getPrefixStrToMaxNumber(prefixStr, jcFBLotteryService.getEntityMaxNumber("numbers", prefixStr.length()+1), 5));
		jcFBLottery.setType(type);
		jcFBLottery.setMultiple(Integer.valueOf(multiple));
		jcFBLottery.setBettingAmount(new BigDecimal(bettingAmount));
		jcFBLottery.setPreAmount(new BigDecimal(preAmount));
		jcFBLottery.setEntryList(this.getJCFBLotteryEntryList(request , jcFBLottery));
		
		return jcFBLottery;
	}
	
	/***
	 * 将请求参数封装为 彩票实体分录对象<p>
	 * @param request 请求对象
	 * @param JCFBLottery jcFBLottery  彩票实体对象
	 * @return List<JCFBLotteryEntry> 返回实体分录list对象<p>
	 */
	private List<JCFBLotteryEntry> getJCFBLotteryEntryList(HttpServletRequest request , JCFBLottery jcFBLottery){
		this.seq = 0;
		String[] jcfbIdArray = request.getParameterValues("jcfbIdArray");//如：[1111  , 2222]  两条竞猜记录
		String[] resultArray = request.getParameterValues("resultArray");//如：[胜@平  , 平 ]   
		String[] oddsValueArray = request.getParameterValues("oddsValueArray");//[1.5@3.1 , 3.5]
		String[] letballArray = request.getParameterValues("letballArray");//如：[-1 , 1]
		String[] letResultArray = request.getParameterValues("letResultArray");//如：[胜 , 平]
		String[] letOddsValueArray = request.getParameterValues("letOddsValueArray");//如：[1.3 , 3.2]
		 List<JCFBLotteryEntry> entryList = new ArrayList<JCFBLotteryEntry>();
		if(CommonUtils.isNotEmptyObjectArray(jcfbIdArray)){
			for(int i = 0 ; i < jcfbIdArray.length ; i++){
				String jcfbId = jcfbIdArray[i];
				//竞猜结果
				if(CommonUtils.isNotEmptyObjectArray(resultArray)){
					String tempResult = resultArray[i];//当前竞猜记录的 竞猜结果
					String tempOddsValue = oddsValueArray[i];//当前竞猜记录的 竞猜赔率
					if(StringUtils.isNotEmpty(tempResult)){
						entryList.addAll(this.getJCFBLotteryEntryList(jcFBLottery, jcfbId , "0" , tempResult, tempOddsValue));
					}
				}
				//让球竞猜结果数据封装
				if(CommonUtils.isNotEmptyObjectArray(letResultArray)){
					String tempLetResult = letResultArray[i];//当前竞猜记录的 竞猜结果
					String tempLetOddsValue = letOddsValueArray[i];//当前竞猜记录的 竞猜赔率
					if(StringUtils.isNotEmpty(tempLetResult)){
						entryList.addAll(this.getJCFBLotteryEntryList(jcFBLottery, jcfbId , letballArray[i], tempLetResult, tempLetOddsValue));
					}
				}
				
			}
		}
		return entryList;
	}
	
	
	
	/***
	 * 将请求参数封装为 彩票实体分录对象<p>
	 * @param JCFBLottery jcFBLottery 彩票实体对象
	 * @param jcfbId  足球竞猜记录id
	 * @param letBall 让球个数
	 * @param results 每条竞猜记录 勾选的竞猜结果
	 * @param oddsValues 每条竞猜记录 勾选的竞猜结果赔率
	 * @return List<JCFBLotteryEntry> 返回实体分录list对象<p>
	 */
	private List<JCFBLotteryEntry> getJCFBLotteryEntryList(JCFBLottery jcFBLottery , String jcfbId , String letBall ,  String results , String oddsValues){
		List<JCFBLotteryEntry> entryList = new ArrayList<JCFBLotteryEntry>();
		String[] resultArray = results.split(SplitChar);
		String[] oddsValueArray = oddsValues.split(SplitChar);
		if(CommonUtils.isNotEmptyObjectArray(resultArray)){
			for(int i = 0 ; i < resultArray.length ; i++){
				JCFBLotteryEntry entry = new JCFBLotteryEntry();
				entry.setParent(jcFBLottery);
				JCFootball jcFootball = new JCFootball();
				jcFootball.setId(jcfbId);
				entry.setSeq(++seq);
				entry.setJcFootball(jcFootball);
				entry.setParent(jcFBLottery);
				entry.setLetBall(Integer.valueOf(letBall));
				entry.setResult(resultArray[i]);
				entry.setResultOdds(new BigDecimal(oddsValueArray[i]));
				entryList.add(entry);
			}
		}
		return entryList;
	}
	
	//分割符号
	private final static String SplitChar = "@";
	//分录序号
	private int seq = 0 ;
	
	
}
