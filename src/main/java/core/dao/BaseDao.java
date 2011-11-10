package core.dao;

import java.util.List;


public interface BaseDao {


	public void save(Object instance);


	public void delete(Object instance);


	public List find(String hql, Object... values);


	

	
	public Object findById(Class clz, Integer id);


	public Object findFirstOne(String hql, Object... values);

	
	public List findByMaxResults(int maxResults, String hql, Object... values);

}