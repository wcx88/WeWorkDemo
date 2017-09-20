package com.winning.global.util;

import org.springframework.context.ApplicationContext;
import org.springframework.web.context.ContextLoader;


public class SpringUtil {
	
	
	public static ApplicationContext context = null;


    public static ApplicationContext getContext() {
        if (null == context) {
            context = ContextLoader.getCurrentWebApplicationContext();
        }
        return context;
    }

    public static Object getBean(String beanName) {
		return getContext().getBean(beanName);
	}
}
