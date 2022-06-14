package utils;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

import org.hibernate.SessionFactory;

@WebListener
public class HibernateInitialization implements ServletContextListener {
	SessionFactory factory;

	public HibernateInitialization() {
	}

	@Override
	public void contextInitialized(ServletContextEvent sce) {
		System.out.println("系統啟動中...");
		factory = HibernateUtils.getSessionFactory();
	}

	@Override
	public void contextDestroyed(ServletContextEvent sce) {
		System.out.println("系統關閉中...");
		factory.close();
	}

}
