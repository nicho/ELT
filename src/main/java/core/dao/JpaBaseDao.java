package core.dao;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;

import com.google.inject.Inject;


public class JpaBaseDao implements BaseDao {

	@Inject
	private EntityManager manager;

	@Override
	public void save(Object instance) {
		instance = manager.merge(instance);
		EntityTransaction tx= manager.getTransaction();
		tx.begin();
		manager.persist(instance);
		tx.commit();
	}

	@Override
	public void delete(Object instance) {
		EntityTransaction tx= manager.getTransaction();
		tx.begin();
		manager.remove(instance);
		tx.commit();
	} 

	@Override
	public List find(String hql, Object... values) {
		Query query = manager.createQuery(hql);
		for (int i = 0; values != null && i < values.length; i++) {
			Object value = values[i];
			query.setParameter(i, value);
		}
		return query.getResultList();
	}

	@Override
	public Object findById(Class clz, Integer id) {
		return manager.find(clz, id);
	}

	@Override
	public Object findFirstOne(String hql, Object... values) {
		Query query = manager.createQuery(hql);
		for (int i = 0; values != null && i < values.length; i++) {
			Object value = values[i];
			query.setParameter(i, value);
		}
		query.setMaxResults(1);
		List list = query.getResultList();
		if (list.iterator().hasNext()) {
			return list.iterator().next();
		} else {
			return null;
		}
	}

	@Override
	public List findByMaxResults(int maxResults, String hql, Object... values) {
		Query query = manager.createQuery(hql);
		for (int i = 0; values != null && i < values.length; i++) {
			Object value = values[i];
			query.setParameter(i, value);
		}
		query.setMaxResults(maxResults);
		return query.getResultList();
	}

	


	
	private String removeSelect(String hql) {
		int beginPos = hql.toLowerCase().indexOf("from");
		
		return hql.substring(beginPos).replaceAll(
				"left\\s*join\\s*fetch\\s*[\\w|\\.]*", "");
	}

	
	

	private String removeOrders(String hql) {
		Pattern p = Pattern.compile("order\\s*by[\\w|\\W|\\s|\\S]*",
				Pattern.CASE_INSENSITIVE);
		Matcher m = p.matcher(hql);
		StringBuffer sb = new StringBuffer();
		while (m.find()) {
			m.appendReplacement(sb, "");
		}
		m.appendTail(sb);
		return sb.toString();
	}
}
