package sns.client;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import sns.client.pojo.UserInfo;
import sns.guice.MyServletModule;

import com.google.inject.Guice;
import com.google.inject.Inject;
import com.google.inject.Injector;

import core.dao.BaseDao;

public class GreetingServiceTest {
	Injector injector;
	
	@Inject
	BaseDao dao;
	
	EntityTransaction tx;

	@Before
	public void setUp() throws Exception {
		injector = Guice.createInjector(new MyServletModule());
		
		//PersistService ps = injector.getInstance(PersistService.class);
		//ps.start();
	
		startTx();
		
		prepareData();
	}
	protected void prepareData() {
		/* data preparation */
	//	EntityManager em = injector.getInstance(EntityManager.class);
	//	Query query = em.createQuery("from eltuser where id='1'");
	//	List<UserInfo> xx= query.getResultList();

	//	List<UserInfo> xx= dao.find("from eltuser where id='1'");
	//	UserInfo x=xx.get(0);
	//	System.out.println("!!!!!!!!!!!!!"+ x.getName());
		dao=injector.getInstance(BaseDao.class);
		UserInfo s1 = new UserInfo();

		s1.setEmail("11@qq.com");
		s1.setName("bad");
		s1.setPassword("123");
		dao.save(s1);

		tx.commit();
		
		UserInfo  us=(UserInfo) dao.findById(UserInfo.class, 1);
		System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!!!!"+ us.getName());
	}
	protected void startTx() {
		EntityManager em = injector.getInstance(EntityManager.class);
		tx = em.getTransaction();
		tx.begin();
	}


	@After
	public void tearDown() throws Exception {
	//	PersistService ps = injector.getInstance(PersistService.class);
	//	ps.stop();
	}

	@Test
	public void testGreetServer() {
		
	}

}
