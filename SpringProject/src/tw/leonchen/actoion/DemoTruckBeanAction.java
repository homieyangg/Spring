package tw.leonchen.actoion;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import tw.leonchen.model.TruckBean;

public class DemoTruckBeanAction {

	public static void main(String[] args) {
		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("beans.config.xml");

		TruckBean nissanTruckBean = context.getBean("nissanTruck", TruckBean.class);
		System.out.println(nissanTruckBean.getId() + " " + nissanTruckBean.getBrand());
		
		TruckBean benzTruck = context.getBean("benzTruck",TruckBean.class);
		System.out.println(benzTruck.getId() + " " +  benzTruck.getBrand());
		
		context.close();
	}

}
