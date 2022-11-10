package com.example.movice_ticket.dao;


import com.example.movice_ticket.model.Seats;
import org.springframework.stereotype.Component;


public interface SeatsDAO {

	//创建影厅座位
	public int creatSeats(Seats seats);
	//查看影厅座位
	public Seats getSeats(int seatId);
	//修改影厅座位
	public boolean updateSeat(Seats seats);
	//删除影厅座位
	public boolean delete(int seatId);
}
