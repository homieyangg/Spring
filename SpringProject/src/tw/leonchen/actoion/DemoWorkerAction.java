package tw.leonchen.actoion;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import tw.leonchen.model.WorkerService;

public class DemoWorkerAction {

	public static void main(String[] args) {
		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("beans.config.xml");

		WorkerService wSerivce = context.getBean("wService", WorkerService.class);
		wSerivce.printDatails();
		
		context.close();
	}

}
