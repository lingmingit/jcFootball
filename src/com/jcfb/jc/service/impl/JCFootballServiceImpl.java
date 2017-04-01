package com.jcfb.jc.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.common.CommonUtils;
import com.common.ReflectionUtils;
import com.common.StringUtils;
import com.jcfb.entity.JCFootball;
import com.jcfb.entity.MatchType;
import com.jcfb.jc.dao.IJCFootballDao;
import com.jcfb.jc.dao.IMatchTypeDao;
import com.jcfb.jc.service.IJCFootballService;
import com.jcfb.service.base.impl.CoreBaseServiceImpl;


/** 
 * 竞猜足球业务操作service 接口实现类<p>
 * @author LingMin 
 * @date 2015-2-9<br>
 * @version 1.0<br>
 */
@Service("jcFootballService")
public class JCFootballServiceImpl extends CoreBaseServiceImpl<JCFootball, String> implements IJCFootballService {

	/****数据库操作dao 接口类******/
	protected IJCFootballDao jcFootballDao;
	
	/**赛事类型数据库操作接口***/
	@Autowired(required=true)
	protected IMatchTypeDao matchTypeDao;
	
	/***
	 * 构造方法初始化 dao 接口
	 * @param jcFootballDao
	 */
	@Autowired(required=true)
	public JCFootballServiceImpl(IJCFootballDao jcFootballDao) {
		super(jcFootballDao);
		this.jcFootballDao = jcFootballDao;
	}
	
	


	/***
	 * 批量保存方法
	 * @param list 实体list对象
	 * @return 返回处理结果
	 * ****/
	@Transactional
	public String saveBatch(List<JCFootball> list){
		StringBuffer msg = new StringBuffer();
		int addCount = 0;
		int updateCount = 0;
		if(CommonUtils.isNotEmptyList(list)){
			for(JCFootball jcFootball : list){
				//首先查询 是否存在该赛事类型，如果没有则保存
				MatchType matchType = matchTypeDao.getMatchType(null, jcFootball.getMatchName());
				if(CommonUtils.isEmptyObject(matchType) ||  StringUtils.isEmpty(matchType.getId())){
					matchTypeDao.saveMatchType(jcFootball.getMatchNum(), jcFootball.getMatchName(), jcFootball.getMatchName(), jcFootball.getMatchStyle() , jcFootball.getMatchNameHref());
				}
				String id = null;
				//判断该条竞猜记录 是否存在
				JCFootball temp = this.getJCFootball(jcFootball.getGn(), jcFootball.getGameno(), jcFootball.getDataGamecode(), jcFootball.getDataGameid(), jcFootball.getLetDataGameid());
				if(CommonUtils.isEmptyObject(temp) || StringUtils.isEmpty(temp.getId())){
					id = jcFootballDao.save(jcFootball);
					if(StringUtils.isNotEmpty(id)){
						addCount++;
					}
				}else{
					//更新数据实体对象所有属性 值
					ReflectionUtils.updateEntityObjectFields(temp, jcFootball, new String[]{"id"});
					id = jcFootballDao.update(temp);
					if(StringUtils.isNotEmpty(id)){
						updateCount++;
					}
				}
				logger.info("id="+id);
			}
			msg.append("读取").append(list.size()).append("条数据，成功保存").append(addCount).append("条，成功更新").append(updateCount).append("条。");
		}
		
		return msg.toString();
	}
	
	/****
	 * 根据相关唯一属性 ，查询是否已经存在该条记录信息<p>
	 * @param gn 竞猜数据tr行属性
	 * @param gameno 竞猜数据tr行属性
	 * @param dataGamecode 竞猜数据tr行属性
	 * @param dataGameid 竞猜数据tr行属性  【竞猜数据唯一id】
	 * @param letDataGameid 竞猜数据tr行属性   【让球数据唯一id】
	 * @return JCFootball 返回竞猜实体对象<p>
	 */
	public JCFootball getJCFootball(String gn , String gameno , String dataGamecode , String dataGameid , String letDataGameid){
		StringBuffer jpql = new StringBuffer();
		jpql.append(" from JCFootball where 1=1");
		jpql.append(" and gn=? and gameno=? and dataGamecode=? and dataGameid=? and letDataGameid=?");
		List<JCFootball> list = (List)this.findByJPQL(jpql.toString(), new Object[]{ gn ,  gameno ,  dataGamecode ,  dataGameid , letDataGameid});
		if(CommonUtils.isNotEmptyList(list)){
			return list.get(0);
		}
		return null;
	}
	
	
}
