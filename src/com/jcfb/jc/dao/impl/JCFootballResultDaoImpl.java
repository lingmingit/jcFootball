package com.jcfb.jc.dao.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.common.CommonUtils;
import com.common.ReflectionUtils;
import com.common.StringUtils;
import com.jcfb.dao.base.impl.CoreBaseDaoImpl;
import com.jcfb.entity.JCFootball;
import com.jcfb.entity.JCFootballResult;
import com.jcfb.entity.MatchType;
import com.jcfb.jc.dao.IJCFBLotteryEntryDao;
import com.jcfb.jc.dao.IJCFootballDao;
import com.jcfb.jc.dao.IJCFootballResultDao;
import com.jcfb.jc.dao.IMatchTypeDao;



/**
 * 竞猜数据结果 数据库操作dao实现类 <p>
 * @author LingMin 
 * @date 2015-2-26<br>
 * @version 1.0<br>
 */
@Repository("jcFootballResultDao")
public class JCFootballResultDaoImpl extends CoreBaseDaoImpl<JCFootballResult, String, IJCFootballResultDao> implements IJCFootballResultDao{

	
	/****数据库操作dao 接口类******/
	@Autowired(required=true)
	protected IJCFootballDao jcFootballDao;
	/**赛事类型数据库操作接口***/
	@Autowired(required=true)
	protected IMatchTypeDao matchTypeDao;
	/**投注记录数据库操作接口***/
	@Autowired(required=true)
	protected IJCFBLotteryEntryDao jcFBLotteryEntryDao;
	
	/***
	 * 根据竞猜历史记录id  查询竞猜结果id<p>
	 * @param jcFootballId 竞猜历史记录id
	 * @return JCFootballResult 返回竞猜结果实体对象<p>
	 */
	public JCFootballResult getJCFootballResult(String jcFootballId){
		StringBuffer jpql = new StringBuffer();
		jpql.append(" from JCFootballResult where jcFootball.id = ? ");
		List<JCFootballResult> list = (List)this.findByJPQL(jpql.toString(), new Object[]{jcFootballId});
		if(CommonUtils.isNotEmptyList(list)){
			return list.get(0);
		}
		return null;
	}
	
	/***
	 * 批量保存方法
	 * @param list 实体list对象
	 * @return 返回处理结果
	 * ****/
	public String saveBatch(List<JCFootballResult> list){
		StringBuffer msg = new StringBuffer();
		int addCount = 0;
		int updateCount = 0;
		if(CommonUtils.isNotEmptyList(list)){
			for(JCFootballResult jcFootballResult : list){
				MatchType matchType = matchTypeDao.getMatchType(null, jcFootballResult.getMatchName());
				if(CommonUtils.isEmptyObject(matchType) ||  StringUtils.isEmpty(matchType.getId())){
					matchTypeDao.saveMatchType(jcFootballResult.getMatchNum(), jcFootballResult.getMatchName(), jcFootballResult.getMatchName(), jcFootballResult.getMatchStyle(), jcFootballResult.getMatchNameHref());
				}
				//根据条件查询原始竞猜记录
				jcFootballResult.setJcFootball(this.getJCFootball(jcFootballResult));
				String id = null;
				//判断该条竞猜赛果记录 是否存在
				JCFootballResult temp = this.getJCFootballResult(jcFootballResult.getGameid() , jcFootballResult.getGamenum() , jcFootballResult.getGamedate());
				if(CommonUtils.isEmptyObject(temp) || StringUtils.isEmpty(temp.getId())){
					id = this.save(jcFootballResult);
					temp = jcFootballResult;
					if(StringUtils.isNotEmpty(id)){
						addCount++;
					}
				}else{
					//更新数据实体对象所有属性 值
					ReflectionUtils.updateEntityObjectFields(temp, jcFootballResult, new String[]{"id"});
					id = this.update(temp);
					if(StringUtils.isNotEmpty(id)){
						updateCount++;
					}
				}
				/***根据赛事结果 更新投注结果******/
				String updateJCFBMsg = jcFBLotteryEntryDao.updateJCFBResult(jcFootballResult.getJcFootball(), temp);
				logger.info("id="+id+" \t updateJCFBMsg="+updateJCFBMsg);
			}
			msg.append("读取").append(list.size()).append("条数据，成功保存").append(addCount).append("条，成功更新").append(updateCount).append("条。");
		}
		
		return msg.toString();
	}
	
	/****
	 * 根据相关唯一属性 ，查询是否已经存在该条记录信息<p>
	 * @param gameid 竞猜赛果数据tr行属性
	 * @param gamenum 竞猜赛果数据tr行属性
	 * @param gamedate 竞猜赛果数据tr行属性
	 * @return JCFootballResult 返回竞猜赛果实体对象<p>
	 */
	public JCFootballResult getJCFootballResult(String gameid , String gamenum , String gamedate){
		StringBuffer jpql = new StringBuffer();
		jpql.append(" from JCFootballResult where 1=1");
		jpql.append(" and gameid=? and gamenum=? and gamedate=?");
		List<JCFootballResult> list = (List)this.findByJPQL(jpql.toString(), new Object[]{ gameid ,  gamenum ,  gamedate});
		if(CommonUtils.isNotEmptyList(list)){
			return list.get(0);
		}
		return null;
	}
	
	/***
	 * 根据竞猜赛果 某些条件 查询竞猜 原始记录<p>
	 * @param jcFootballResult 竞猜赛果实体对象
	 * @return JCFootball 返回竞猜原始数据实体对象<p>
	 */
	private JCFootball getJCFootball(JCFootballResult jcFootballResult){
		/**1、首先根据竞猜赛果 数据字段 【gameid="425918" 】  ===>> 竞猜原始列表 【让球数据行】 唯一标识id 列【id="tr_425918" 】****/
		JCFootball jcFootball = jcFootballDao.getJCFootball(jcFootballResult.getJcDate(), jcFootballResult.getGameid());
		if(CommonUtils.isNotEmptyObject(jcFootball) && StringUtils.isNotEmpty(jcFootball.getId())){
			return jcFootball;
		}
		/**2、再根据竞猜赛果 数据字段 【gamenum="4052"  】  ===>> 竞猜原始列表数据行 列【gn="4052" gameno="4052"】****/
		jcFootball = jcFootballDao.getGameNoToJCFootball(jcFootballResult.getJcDate(), jcFootballResult.getGamenum());
		if(CommonUtils.isNotEmptyObject(jcFootball) && StringUtils.isNotEmpty(jcFootball.getId())){
			return jcFootball;
		}
		/**3、再根据竞猜赛果 数据字段 【竞猜日期、 场次编号、星期、赛事名称、主队名称、客队名称 】  ===>> 竞猜原始列表数据行 列【竞猜日期、 场次编号、星期、赛事名称、主队名称、客队名称 】****/
		jcFootball = jcFootballDao.getJCFootball(jcFootballResult.getJcDate(), jcFootballResult.getMatchesNo() , jcFootballResult.getWeek() , jcFootballResult.getMatchName() , jcFootballResult.getHomeTeamName() , jcFootballResult.getVisitingTeamName());
		return jcFootball;
	}
}
