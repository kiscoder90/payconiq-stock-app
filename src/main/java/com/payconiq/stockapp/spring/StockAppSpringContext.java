/**
 * 
 */
package com.payconiq.stockapp.spring;

import java.util.concurrent.atomic.AtomicReference;

import org.springframework.beans.factory.config.AutowireCapableBeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope("singleton")
public class StockAppSpringContext implements ApplicationContextAware {
	private static AtomicReference<ApplicationContext> applicationContextRef = new AtomicReference<ApplicationContext>();

	private static StockAppSpringContext instance = null;

	private StockAppSpringContext() {
	}

	public static StockAppSpringContext getInstance() {
		if (instance == null) {
			instance = new StockAppSpringContext();
		}
		return instance;
	}

	@Override
	public void setApplicationContext(ApplicationContext argApplicationContext) {
		ApplicationContext currentApplicationContext = applicationContextRef.get();
		if (currentApplicationContext == null) {
			synchronized (applicationContextRef) {
				currentApplicationContext = applicationContextRef.get();
				if (currentApplicationContext == null) {
					applicationContextRef.set(argApplicationContext);
				}
			}
		}
	}

	@SuppressWarnings("unchecked")
	public static <T> T initializeBean(Object o) {
		AutowireCapableBeanFactory factory = getApplicationContext().getAutowireCapableBeanFactory();
		factory.autowireBean(o);
		factory.initializeBean(o, "");
		return ((T) o);
	}

	public static ApplicationContext getApplicationContext() {
		return applicationContextRef.get();
	}

	@SuppressWarnings("unchecked")
	public static <T> T getBean(String argBeanName) {
		return ((T) getApplicationContext().getBean(argBeanName));
	}

	@SuppressWarnings("unchecked")
	public static <T> T getBeanIfExists(String argBeanName) {
		try {
			return ((T) getApplicationContext().getBean(argBeanName));
		} catch (Exception ignored) {
			return (null);
		}
	}

	public static <T> T getBean(Class<T> clazz) {
		return (getApplicationContext().getBean(clazz));
	}

	public static <T> T getBeanIfExists(Class<T> clazz) {
		try {
			return (getApplicationContext().getBean(clazz));
		} catch (Exception ignored) {
			return (null);
		}
	}

	public static void setCustomApplicationContext(ConfigurableApplicationContext argApplicationContext) {
		synchronized (applicationContextRef) {
			applicationContextRef.set(argApplicationContext);
		}
	}

}
