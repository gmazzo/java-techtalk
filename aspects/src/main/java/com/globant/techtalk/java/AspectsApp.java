package com.globant.techtalk.java;

import com.globant.techtalk.java.aspects.SlowCallLogAspect;
import com.globant.techtalk.java.services.Service;
import com.globant.techtalk.java.services.ServiceImpl;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

public class AspectsApp {

	public static void main(String[] args) {
		try (
			AnnotationConfigApplicationContext appContext = new AnnotationConfigApplicationContext()) {
			appContext.register(AppConfig.class);
			appContext.refresh();

			Service service = appContext.getBean(Service.class);

			service.doSomething(150);
			service.doSomethingElse(150);
			service.doSomething(250);
			service.doSomethingElse(250);
			service.doSomething(1050);
			service.doSomethingElse(1050);
		}
	}

}

@Configuration
@EnableAspectJAutoProxy
class AppConfig {

	@Bean
	public Service getService() {
		return new ServiceImpl();
	}

	@Bean
	public SlowCallLogAspect getSlowCallLogAspect() {
		return new SlowCallLogAspect();
	}

}
