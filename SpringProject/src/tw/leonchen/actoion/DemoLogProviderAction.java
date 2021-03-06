package tw.leonchen.actoion;

import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import tw.leonchen.util.LogProvider;

public class DemoLogProviderAction {

	public static void main(String[] args) {
		ApplicationContext context = new ClassPathXmlApplicationContext("beans.config.xml");

		LogProvider logProvider1 = (LogProvider) context.getBean("logProvider");
		LogProvider logProvider2 = (LogProvider) context.getBean("logProvider");
//		logProvider.log("Have a good day!!");
		System.out.println(logProvider1.hashCode());
		System.out.println(logProvider2.hashCode());
		((ConfigurableApplicationContext) context).close();
	}

}
