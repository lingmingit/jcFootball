package com.common;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.util.Date;

import com.common.date.DateUtils;
import com.jcfb.entity.JCFootball;


/**
 * 反射原理通用工具类<p>
 * @author LingMin 
 * @date 2014-06-17<br>
 * @version 1.0<br>
 */
public class ReflectionUtils {
	
	
	/** 日志书写对象 **/
	protected static org.apache.log4j.Logger logger = org.apache.log4j.Logger.getLogger(ReflectionUtils.class);
	/**
	 * 构造函数<p>
	 */
	private ReflectionUtils() {}
	
	
	/****
	 * 将新实体对象属性值 设置更新 到旧实体中(包括父类属性) <p>
	 * @param oldObject 旧实体对象
	 * @param newObject 新实体对象
	 * @param notSetFieldName 不需要设置的属性名称数组 <p>
	 * void
	 */
	public static void updateEntityObjectFields(Object oldObject , Object newObject , String[] notSetFieldName){
		if(CommonUtils.isEmptyObject(oldObject) || CommonUtils.isEmptyObject(newObject)){
			return;
		}
		//Field[] fields = oldObject.getClass().getFields();//该方法 是获取 public 公共的方法
		 for(Class<?> clazz = oldObject.getClass() ; clazz != Object.class ; clazz = clazz.getSuperclass()) {  
			 Field[] fields = clazz.getDeclaredFields();//该方法 是否获取 所有定义的方法
			 if(CommonUtils.isEmptyObjectArray(fields)){
					continue;
			 }
			 ReflectionUtils.setObjectFieldValue(oldObject, newObject, fields, notSetFieldName);
		 }
	}
	
	
	
	/****
	 * 根据属性 ， 将新实体对象属性值 设置更新 到旧实体中<p>
	 * @param oldObject 旧实体对象
	 * @param newObject 新实体对象<p>
	 * @param fields 属性数组<p>
	 * @param notSetFieldName 不需要设置的属性名称数组
	 * void
	 */
	public static void setObjectFieldValue(Object oldObject , Object newObject , Field[] fields , String[] notSetFieldName){
		if(CommonUtils.isEmptyObjectArray(fields)){
			return;
		}
		for(Field field : fields){
			String fieldName = field.getName();
			//判断当前属性 是否不设置
			if(StringUtils.isExistenceInArray(notSetFieldName, fieldName)){
				continue;
			}
			
			String methodName = Character.toUpperCase(fieldName.charAt(0))+fieldName.substring(1); 
			String getMethodName = "get".concat(methodName);
			String setMethodName = "set".concat(methodName);
			Object newFieldValue = null;
			Class returnType = null;
			//1、首先从新对象中获取 属性的值 
			Method getMethod = org.springframework.util.ReflectionUtils.findMethod(newObject.getClass(), getMethodName);
			if(CommonUtils.isNotEmptyObject(getMethod)){
				newFieldValue =  org.springframework.util.ReflectionUtils.invokeMethod(getMethod, newObject);
				returnType = getMethod.getReturnType();
			}
			//2、将获取的值设置到旧对象中【有参数方法时，最好加上参数类型  便于查找，否则找不到】
			Method setMethod = org.springframework.util.ReflectionUtils.findMethod(oldObject.getClass(), setMethodName, new Class[]{returnType});
			if(CommonUtils.isNotEmptyObject(setMethod)){
				org.springframework.util.ReflectionUtils.invokeMethod(setMethod, oldObject , new Object[]{newFieldValue});
			}
		}
	}
	
	
	
	
	
	/**
	 * 获取指定对象中属性的属性值:私有|公用属性<p>
	 * @param obj 对象<br>
	 * @param fieldName 属性名<br>
	 * @return 属性值<br>
	 */
	public static Object getFieldValue(Object obj, String fieldName) {
		Object rtnOb = null;
		if (CommonUtils.isNotEmptyObject(obj) && StringUtils.isNotEmpty(fieldName)) {
			try {
				Class<?> temp = obj.getClass();
				while (!ReflectionUtils.isExistField(temp, fieldName)) {
					temp = temp.getSuperclass();
				}
				java.lang.reflect.Field field = temp.getDeclaredField(fieldName);
				field.setAccessible(true);
				rtnOb =  field.get(obj);
			} catch (Exception ex) {
				ex.printStackTrace();
				logger.error(ex.getMessage());
			}
		}
		return rtnOb;
	}
	
	/**
	 * 获取对象中深层次的属性值<p>
	 * @param obj 对象<br>
	 * @param fieldName 属性层次字符串<br>
	 * @return 属性值<br>
	 */
	public static String getDeepFieldValue(Object obj, String fieldName) {
		String rtnS = "";
		// 合法性验证
		if (CommonUtils.isNotEmptyObject(obj) && StringUtils.isNotEmpty(fieldName)) {
			String[] fields = StringUtils.splitStringTokenizer(fieldName, ".");
			if (CommonUtils.isNotEmptyObjectArray(fields)) {
				for (int i = 0; i < fields.length; i ++) {
					obj = invokeMethod(obj, "get".concat(StringUtils.firstCharToUpperCase(fields[i])), null, null);
				}
			}
			rtnS = CommonUtils.isEmptyObject(obj) ? " " : obj.toString();
		}
		return rtnS;
	}
	
	/**
	 * 设置指定对象中属性的属性值:私有|公用属性<p>
	 * @param obj 指定对象<br>
	 * @param fieldName 属性名<br>
	 * @param fieldValue 属性值<br>
	 */
	public static void setFieldValue(Object obj, String fieldName, Object fieldValue) {
		if (CommonUtils.isNotEmptyObject(obj) && StringUtils.isNotEmpty(fieldName)) {
			try {
				Class<?> temp = obj.getClass();
				while (!ReflectionUtils.isExistField(temp, fieldName)) {
					temp = temp.getSuperclass();
				}
				java.lang.reflect.Field field = temp.getDeclaredField(fieldName);
				field.setAccessible(true);
				field.set(obj, fieldValue);
			} catch (Exception ex) {
				ex.printStackTrace();
				logger.error(ex.getMessage());
			}
		}
	}
	
	/**
	 * 获取指定对象中静态属性的属性值<p>
	 * @param className 类名<br>
	 * @param fieldName 属性名<br>
	 * @return 属性值<br>
	 */
	public static Object getStaticFieldValue(String className, String fieldName) {
		Object rtnOb = null;
		if (StringUtils.isNotEmpty(className) && StringUtils.isNotEmpty(fieldName)) {
			try {
				Class<?> clz = Class.forName(className);
				while (!ReflectionUtils.isExistField(clz, fieldName)) {
					clz = clz.getSuperclass();
				}
				java.lang.reflect.Field field = clz.getDeclaredField(fieldName);
				field.setAccessible(true);
				rtnOb = field.get(clz);
			} catch (Exception ex) {
				ex.printStackTrace();
				logger.error(ex.getMessage());
			}
		}
		return rtnOb;
	}
	
	/**
	 * 动态执行指定对象中对应方法并返回结果<p>
	 * @param obj 指定对象<br>
	 * @param methodName 方法名<br>
	 * @param args 方法参数数组<br>
	 * @param clzs 参数字节码类型<br>
	 * @return 执行结果<br>
	 */
	public static Object invokeMethod(Object obj, String methodName, Object[] args, Class<?>[] clzs) {
		Object rtnOb = null;
		if (CommonUtils.isNotEmptyObject(obj) && StringUtils.isNotEmpty(methodName)) {
			try {
				Class<?> temp = obj.getClass();
				while (!ReflectionUtils.isExistMethod(temp, methodName)) {
					temp = temp.getSuperclass();
				}
				java.lang.reflect.Method method = temp.getDeclaredMethod(methodName, clzs);
				method.setAccessible(true);
				if (args != null && clzs != null && args.length == clzs.length) {
					int argLen = args.length;
					if (argLen == 1) {
						rtnOb = method.invoke(obj, args[0]);
					} else if (argLen == 2) {
						rtnOb = method.invoke(obj, args[0], args[1]);
					} else if (argLen == 3) {
						rtnOb = method.invoke(obj, args[0], args[1], args[2]);
					} else if (argLen == 4) {
						rtnOb = method.invoke(obj, args[0], args[1], args[2], args[3]);
					} else if (argLen == 5) {
						rtnOb = method.invoke(obj, args[0], args[1], args[2], args[3], args[4]);
					}
				} else {
					rtnOb = method.invoke(obj, args);
				}
			} catch (Exception ex) {
				ex.printStackTrace();
				logger.error(ex.getMessage());
			}
		}
		return rtnOb;
	}
	
	/**
	 * 动态执行指定对象中对应静态方法并返回结果<p>
	 * @param className 类名<br>
	 * @param methodName 方法名<br>
	 * @param args 方法参数数组<br>
	 * @return 执行结果<br>
	 */
	public static Object invokeStaticMethod(String className, String methodName, Object[] args, Class<?>[] clzs) {
		Object rtnOb = null;
		if (StringUtils.isNotEmpty(className) && StringUtils.isNotEmpty(methodName)) {
			try {
				Class<?> clz = Class.forName(className);
				while (! ReflectionUtils.isExistMethod(clz, methodName)) {
					clz = clz.getSuperclass();
				}
				java.lang.reflect.Method method = clz.getDeclaredMethod(methodName, clzs);
				method.setAccessible(true);
				if (args != null && clzs != null && args.length == clzs.length) {
					int argLen = args.length;
					if (argLen == 1) {
						rtnOb = method.invoke(clz, args[0]);
					} else if (argLen == 2) {
						rtnOb = method.invoke(clz, args[0], args[1]);
					} else if (argLen == 3) {
						rtnOb = method.invoke(clz, args[0], args[1], args[2]);
					} else if (argLen == 4) {
						rtnOb = method.invoke(clz, args[0], args[1], args[2], args[3]);
					} else if (argLen == 5) {
						rtnOb = method.invoke(clz, args[0], args[1], args[2], args[3], args[4]);
					}
				} else {
					rtnOb = method.invoke(clz, args);
				}
			} catch (Exception ex) {
				ex.printStackTrace();
				logger.error(ex.getMessage());
			}
		}
		return rtnOb;
	}
	
	/**
	 * 获取指定方法的返回值类型<p>
	 * @param obj 指定对象<br>
	 * @param methodName 方法名<br>
	 * @param args 参数数组<br>
	 * @return 返回值类型数组<br>
	 */
	public static Class<?> getReturnType(Object obj, String methodName, Class<?>[] clzs) {
		Class<?> rtnOb = null;
		if (CommonUtils.isNotEmptyObject(obj) && StringUtils.isNotEmpty(methodName)) {
			try {
				Class<?> temp = obj.getClass();
				while (!ReflectionUtils.isExistMethod(temp, methodName)) {
					temp = temp.getSuperclass();
				}
				java.lang.reflect.Method method = temp.getDeclaredMethod(methodName, clzs);
				method.setAccessible(true);
				rtnOb = method.getReturnType();
			} catch (Exception ex) {
				ex.printStackTrace();
				logger.error(ex.getMessage());
			}
		}
		return rtnOb;
	}
	
	/**
	 * 判断方法在指定的类中是否存在<p>
	 * @param clz 指定类<br>
	 * @param methodName 方法名<br>
	 * @return true:存在 false:不存在<br>
	 */
	public static boolean isExistMethod(Class<?> clz, String methodName) {
		boolean rtnB = false;
		if (CommonUtils.isNotEmptyObject(clz) && StringUtils.isNotEmpty(methodName)) {
			java.lang.reflect.Method[] methods = clz.getDeclaredMethods();
			for (java.lang.reflect.Method method : methods) {
				if (methodName.equals(method.getName())) {
					rtnB = true;
					break;
				}
			}
		}
		return rtnB;
	}
	
	/**
	 * 判断属性在指定的类中是否存在<p>
	 * @param clz 指定类<br>
	 * @param fieldName 属性名<br>
	 * @return true:存在 false:不存在<br>
	 */
	public static boolean isExistField(Class<?> clz, String fieldName) {
		boolean rtnB = false;
		if (CommonUtils.isNotEmptyObject(clz) && StringUtils.isNotEmpty(fieldName)) {
			java.lang.reflect.Field[] fields = clz.getDeclaredFields();
			for (java.lang.reflect.Field field : fields) {
				if (fieldName.equals(field.getName())) {
					rtnB = true;
					break;
				}
			}
		}
		return rtnB;
	}
	
	/**
	 * 判断属性在指定的类中是否存在,包含父类<p>
	 * @param clz 指定类<br>
	 * @param fieldName 属性名<br>
	 * @return true:存在 false:不存在<br>
	 */
	public static boolean isExistFieldForDeep(Class<?> clz, String fieldName) {
		boolean rtnB = false;
		try {
			rtnB = ReflectionUtils.isExistField(clz, fieldName);
			while (clz != Object.class && rtnB == false) {
				clz = clz.getSuperclass();
				java.lang.reflect.Field[] fields = clz.getDeclaredFields();
				for (java.lang.reflect.Field field : fields) {
					if (fieldName.equals(field.getName())) {
						rtnB = true;
						break;
					}
				}
			}
		} catch (Exception ex) {
			ex.printStackTrace();
			logger.error(ex.getMessage());
		}
		return rtnB;
	}
	
	/**
	 * 判断实例对象是否是指定类的实例<p>
	 * @param obj 实例对象<br>
	 * @param className 类名<br>
	 * @return true:是 false:否<br>
	 */
	public static boolean isInstance(Object obj, String className) {
		boolean rtnB = false;
		if (CommonUtils.isNotEmptyObject(obj) && StringUtils.isNotEmpty(className)) {
			try {
				rtnB = Class.forName(className).isInstance(obj);
			} catch (Exception ex) {
				ex.printStackTrace();
				logger.error(ex.getMessage());
			}
		}
		return rtnB;
	}
	
	/**
	 * 根据对象名与构造函数参数实例化指定对象<p>
	 * @param className 类名<br>
	 * @param args 构造函数参数<br>
	 * @return 实例对象<br>
	 */
	public static Object newInstance(String className, Object[] args, Class<?>[] clzs) {
		Object rtnOb = null;
		if (StringUtils.isNotEmpty(className)) {
			try {
				Class<?> clz = Class.forName(className);
				java.lang.reflect.Constructor<?> constructor = clz.getConstructor(clzs);
				if (args != null && clzs != null && args.length == clzs.length) {
					int argLen = args.length;
					if (argLen == 1) {
						rtnOb = constructor.newInstance(args[0]);
					} else if (argLen == 2) {
						rtnOb = constructor.newInstance(args[0], args[1]);
					} else if (argLen == 3) {
						rtnOb = constructor.newInstance(args[0], args[1], args[2]);
					} else if (argLen == 4) {
						rtnOb = constructor.newInstance(args[0], args[1], args[2], args[3]);
					} else if (argLen == 5) {
						rtnOb = constructor.newInstance(args[0], args[1], args[2], args[3], args[4]);
					}
				} else {
					rtnOb = constructor.newInstance(args);;
				}
			} catch (Exception ex) {
				ex.printStackTrace();
				logger.error(ex.getMessage());
			}
		}
		return rtnOb;
	}
	
	
	
	public static void main(String[] args) {
		JCFootball jcFootball = new JCFootball();
		jcFootball.setId("id00001");
		jcFootball.setMatchesNo("111");
		jcFootball.setJcDate(DateUtils.getDateFromSpecifiedString("2015-03-01", DateUtils.DateFormatter));
		Date date = DateUtils.getDateFromSpecifiedString("2015-01-01", DateUtils.DateFormatter);
		jcFootball.setCreateTime(date);
		jcFootball.setIsNotSaleSPF(false);
		jcFootball.setLordAvgOdds(new BigDecimal("1.111"));
		jcFootball.setTheIndex(1);
		
		JCFootball jcFootball2 = new JCFootball();
		jcFootball2.setId("id00002");
		jcFootball2.setMatchesNo("222");
		jcFootball2.setJcDate(DateUtils.getDateFromSpecifiedString("2015-03-02", DateUtils.DateFormatter));
		jcFootball2.setCreateTime(new Date());
		jcFootball2.setIsNotSaleSPF(true);
		jcFootball2.setLordAvgOdds(new BigDecimal("2.222"));
		jcFootball2.setTheIndex(2);
		ReflectionUtils.updateEntityObjectFields(jcFootball, jcFootball2 , new String[]{"id" , "jcDate"});//
		
		logger.info("id="+jcFootball.getId()+" \t MatchesNo="+jcFootball.getMatchesNo()+" \t "+jcFootball.getJcDate()+" \t "+jcFootball.getCreateTime()+" \t"+jcFootball.getIsNotSaleSPF()+" \t"+jcFootball.getLordAvgOdds()+" \t "+jcFootball.getTheIndex());
		
//		 for(Class<?> clazz = jcFootball.getClass() ; clazz != Object.class ; clazz = clazz.getSuperclass()) {  
//			 logger.info("clazz.name="+clazz.getName());
//		 }
	}
}
