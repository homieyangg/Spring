package announce.service;

import java.util.List;

import announce.model.AnnounceBean;

public interface AnnounceService {
	public void createAnnounce(AnnounceBean announceBean); // 新增公告
	public void deleteAnnounce(Integer anNo); // 刪除公告
	public void updateAnnounce(AnnounceBean announceBean); // 修改公告
	public AnnounceBean findAnnounceByNo(Integer anNo); // 查詢公告
	public List<AnnounceBean> findAllAnnounce(); // Table顯示所有公告
}
