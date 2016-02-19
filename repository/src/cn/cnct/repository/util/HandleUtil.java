package cn.cnct.repository.util;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Set;


public class HandleUtil {
	public static final String ROOTPATH ="D:";
	public static final String FILENAME = "缺陷图片附件";
	/**
	 * 所有对象按照id排序
	 * 
	 * @param list
	 * @return
	 */
	public static <T> boolean sortById(List<T> list){
		return sort(list,"id");
		
	}
	/**
	 * 按照特定的参数进行排序，参数必须是Integer类型,正确进行排序并返回ture，失败不进行排序并返回false
	 * @param list 排序对象集合
	 * @param param 进行排序的参数
	 * @return
	 */
	public static <T> boolean sort(List<T> list,String param) {
		List<T> sortList = new ArrayList<T>(list);
		try{
			Collections.sort(sortList, new Bjq<T>(param));
		}catch(Exception e){
			return false;
		}
		Collections.sort(list, new Bjq<T>(param));
		return true;
	}
	/**
	 * 通过id排序set集合
	 * @param set
	 * @return
	 */
	public static <T> List<T> sortSetById(Set<T> set) {
		
		return sort(set,"id");
	}
	/**
	 * 按照特定的参数进行排序，参数必须是Integer类型,正确进行排序并返回ture，失败不进行排序并返回false
	 * @param set 排序对象集合
	 * @param param 进行排序的参数
	 * @return
	 */
	public static <T> List<T> sort(Set<T> set,String param) {
		List<T> sortList = new ArrayList<T>(set);
		Collections.sort(sortList, new Bjq<T>(param));
		return sortList;
	}

	private static class Bjq<T> implements Comparator<T> {

		long i;
		String param;
		public Bjq(String param){
			this.param = param;
		}
		public int compare(T o1, T o2) {
			Field field = null;
			try {
				field = o1.getClass().getDeclaredField(param);
				field.setAccessible(true);
				Long i1 = (Long) field.get(o1);
				Long i2 = (Long) field.get(o2);
				i = i1 - i2;
			} catch (Exception e) {
				throw new RuntimeException();
			} finally{
				if(field != null)
					field.setAccessible(false);
			}
			return (int)i;
		}
	}

}
