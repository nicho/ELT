package sns.resource;

import org.jrest4guice.rest.annotations.Cache;
import org.jrest4guice.rest.annotations.Get;
import org.jrest4guice.rest.annotations.Path;


@Path("/HelloWorld")
public class HelloWorldSimple {
	
	@Get
	@Cache
	public String sayHello(){
		return "hello JRest4Guice world";
	}
}
