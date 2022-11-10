package com.example.movice_ticket.dao;

import com.example.movice_ticket.model.Ticket;

import java.util.List;
import java.util.Map;

public interface TicketDao {
    /**
     * 生成订单
     * @param ticket
     * @return
     */
    public boolean createTicket(Ticket ticket);

    /**
     * 查询单个订单信息
     * @param ticketId
     * @return
     */
    public Ticket getTicket(int ticketId);

    /**
     * 分页查询全部订单
     * @param paramMap
     * @return
     */
    public List<Ticket> queryTicketList(Map<String,Object> paramMap);

    /**
     * 订单总数
     * @param paramMap
     * @return
     */
    public int queryCountTicket(Map<String,Object>paramMap);

    /**
     * 修改支付时间
     * @param map
     * @return
     */
    public boolean updateOut_trade_no(Map<String,Object>map);

    /**
     * 通过订单号查询订单信息
     * @param out_trade_no
     * @return
     */
    public Ticket getTickeByOut_trade_no(String out_trade_no);
    /**
     * 通过订单号查询所有订单信息
     * @param out_trade_no
     * @return
     */
    public List<Ticket> getTickelist(String out_trade_no);
    /**
     * 通过订单号查询所有订单信息判断是否有支付时间
     * @param
     * @return
     */
    public List<Ticket> getTickes(Map<String,Object>map);

    /**
     * 通过userId,cinemaId,filmId修改订单号
     * @param map
     * @return
     */
    public boolean setOut_trade_no(Map<String,Object>map);

    /**
     * 修改订单状态
     * @param map
     * @return
     */
    public boolean setorderState(Map<String,Object>map);

    /**
     * 统计总金额
     * @return
     */
    public double countMoney();
}
