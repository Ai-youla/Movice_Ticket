package com.example.movice_ticket.dao;


import com.example.movice_ticket.model.ViewingTime;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;


public interface ViewingTimeDAO {

	/**
	 * 添加观影批次
	 * @param viewingTime
	 * @return
	 */
	public boolean createViewingTime(ViewingTime viewingTime);
	/**
	 * 修改观影批次的信息
	 * @param viewingTime
	 * @return
	 */
 	public boolean updateViewingTime(ViewingTime viewingTime);
	/**
	 * 通过Id查询批次信息
	 * @param viewingTimeId
	 * @return
	 */
 	public ViewingTime getViewingTime(int viewingTimeId);
	/**
	 * 分页查询所有的批次
	 * @param param
	 * @return
	 */
 	public List<ViewingTime> ViewingTimes(Map<String, Object>param);
	/**
	 * 所有批次个数
	 * @param param
	 * @return
	 */
 	public int CountViewingTimes(Map<String, Object>param);
/**
 *通过Id删除批次
 * @param viewingTimeId
 * @return
 */
 	public boolean delete(int viewingTimeId);
}
