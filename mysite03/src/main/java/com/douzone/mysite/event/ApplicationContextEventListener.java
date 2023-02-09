package com.douzone.mysite.event;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.AutowireCapableBeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;

import com.douzone.mysite.service.SiteService;
import com.douzone.mysite.vo.SiteVo;


public class ApplicationContextEventListener {
	
	@Autowired
	private ApplicationContext applicationContext;
	
	@EventListener({ContextRefreshedEvent.class})
	public void handleContextRefreshEvent() {
		System.out.println(((Object)applicationContext).getClass());
		System.out.println(System.identityHashCode(applicationContext));
		System.out.println(" ----- ContextRefreshEvent Received ----- : "+ applicationContext);
	
		SiteService service = applicationContext.getBean(SiteService.class);
		SiteVo site = service.getSite();
		
		AutowireCapableBeanFactory factory = applicationContext.getAutowireCapableBeanFactory();
		BeanDefinitionRegistry
	}
}
