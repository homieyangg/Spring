package Animal.service;
import java.util.List;

import Animal.bean.AbDogBean;



	public interface AbDogService {
		
		Object createabdog(AbDogBean abdogbean);//新增功能 帶bean過去 才能新增資料庫
		
		void deleteabdog(Integer id);//刪除 //帶ID給下一隻程式
		
		void updateabdog(AbDogBean abdogbean);//更正 //帶整個bean
		
		AbDogBean findById(Integer id);//單一筆查詢 先查詢後更正
		
		List<AbDogBean> findAll(); //找全部的資料abbean
		
		void close();
	}
