package sns.guice;

import com.google.inject.Guice;
import com.google.inject.Injector;
import com.google.inject.servlet.GuiceServletContextListener;

public class MyGuiceContextListener extends GuiceServletContextListener {

	@Override
	public Injector getInjector() {
	//	new JpaPersistModule?("myapp-db").properties(hibernateJpaProperties) 
		Injector  injector = Guice.createInjector( new MyServletModule());
	//	PersistService ps = injector.getInstance(PersistService.class);
	//	ps.start();
		return injector;
	}

}
