package sns.guice;

import java.util.HashMap;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import sns.client.GreetingService;
import sns.server.GreetingServiceImpl;

import com.google.inject.Provides;
import com.google.inject.Singleton;
import com.google.inject.persist.PersistFilter;
import com.google.inject.persist.jpa.JpaPersistModule;
import com.google.inject.servlet.ServletModule;

import core.dao.BaseDao;
import core.dao.JpaBaseDao;

public class MyServletModule extends ServletModule {

	/*
	 private static final ThreadLocal<EntityManager> ENTITY_MANAGER_CACHE = new ThreadLocal<EntityManager>();

	 @Provides @Singleton
	  public EntityManagerFactory provideEntityManagerFactory() {
	    Map<String, String> properties = new HashMap<String, String>();
	    properties.put("hibernate.connection.driver_class", "com.mysql.jdbc.Driver");
	    properties.put("hibernate.connection.url", "jdbc:mysql://127.0.0.1:3306/elt?autoReconnect=true&amp;useUnicode=true&amp;characterEncoding=utf-8");
	    properties.put("hibernate.connection.username", "root");
	    properties.put("hibernate.connection.password", "admin");
	    properties.put("hibernate.connection.pool_size", "2");
	    properties.put("hibernate.dialect", "org.hibernate.dialect.MySQLDialect");
	    properties.put("hibernate.hbm2ddl.auto", "update");
	    properties.put("hibernate.show_sql", "true");
	   
	    return Persistence.createEntityManagerFactory("jpaunit");
	  }

	  @Provides
	  public EntityManager provideEntityManager(EntityManagerFactory entityManagerFactory) {
	    EntityManager entityManager = ENTITY_MANAGER_CACHE.get();
	    if (entityManager == null) {
	      ENTITY_MANAGER_CACHE.set(entityManager = entityManagerFactory.createEntityManager());
	    }
	    return entityManager;
	  }
*/
	@Override
	protected void configureServlets() {
		filter("/*").through(PersistFilter.class);
	    java.util.Properties properties=new java.util.Properties();
	    properties.put("hibernate.connection.driver_class", "com.mysql.jdbc.Driver");
	    properties.put("hibernate.connection.url", "jdbc:mysql://127.0.0.1:3306/elt?autoReconnect=true&amp;useUnicode=true&amp;characterEncoding=utf-8");
	    properties.put("hibernate.connection.username", "root");
	    properties.put("hibernate.connection.password", "admin");
	    properties.put("hibernate.connection.pool_size", "2");
	    properties.put("hibernate.dialect", "org.hibernate.dialect.MySQLDialect");
	    properties.put("hibernate.hbm2ddl.auto", "update");
	    properties.put("hibernate.show_sql", "true");
	  
		install(new JpaPersistModule("jpaunit"));
        bind(BaseDao.class).to(JpaBaseDao.class);   
        bind(GreetingService.class).to(GreetingServiceImpl.class);
        
        bind(GreetingServiceImpl.class).in(Singleton.class);
		serve("/jxt_elt/greet").with(GreetingServiceImpl.class);
    
	}
}
