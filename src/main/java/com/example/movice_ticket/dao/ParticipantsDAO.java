package com.example.movice_ticket.dao;


import com.example.movice_ticket.model.Participants;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;


public interface ParticipantsDAO {
	/**
	 * 创建参演人员
	 * @param participants
	 * @return
	 */
	public boolean createParticipants(Participants participants);
	/**
	 * 分页查询所有参演人员
	 * @param param
	 * @return
	 */
	public List<Participants> list(Map<String, Object>param);
	/**
	 * 所以参演人员个数
	 * @param param
	 * @return
	 */
	public int conutlist(Map<String, Object>param);
	/**
	 * 修改参演人员信息
	 * @param participants
	 * @return
	 */
	 public boolean updateParticipants(Participants participants);
	/**
	 * 修改参演人员头像
	 * @param participants
	 * @return
	 */
	 public boolean updatePriture(Participants participants);
	 /**
	  * 删除参演人员
	  * @param participantsId
	  * @return
	  */
	 public boolean delete(int participantsId);
	 /**
	  * 通过Id查询参演人员信息
	  * @param ParticipantsId
	  * @return
	  */
	 public Participants getParticipantsById(int ParticipantsId);
}
