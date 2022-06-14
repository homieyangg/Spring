package activity.dao;

import java.util.List;

import activity.model.ActivityBean;

public interface ActivityDao {
	// 新增訂單
	Object createActivity(ActivityBean activityBean);

	// 刪除訂單
	void deleteActivity(Integer ac_id);

	// 修改訂單
	void updateActivity(ActivityBean activityBean);

	// 查詢訂單
	ActivityBean findById(Integer ac_id);

	// Table顯示訂單
	List<ActivityBean> findAll();
	
	void close();
	
	void persist(ActivityBean activityBean);
}
