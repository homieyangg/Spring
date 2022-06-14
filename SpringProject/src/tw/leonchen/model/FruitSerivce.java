package tw.leonchen.model;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service(value = "fruitService")
public class FruitSerivce {
	
	@Autowired
	private FruitDao fruitDao;
	
	public void showInfo() {
		fruitDao.showInfo();
	}
	
}
