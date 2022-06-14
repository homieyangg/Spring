package activity.service;

import java.util.List;

import activity.model.ActivityBean;

public interface ActivityService {
	
	Object createActivity(ActivityBean activityBean);
	
	void deleteActivity(Integer od_id);
	
	void updateActivity(ActivityBean activityBean);
	
	ActivityBean findById(Integer od_id);
	
	List<ActivityBean> findAll();
	
	void close();
}
