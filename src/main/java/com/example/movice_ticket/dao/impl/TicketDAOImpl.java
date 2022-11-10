package com.example.movice_ticket.dao.impl;

import com.example.movice_ticket.dao.TicketDao;
import com.example.movice_ticket.mapper.TicketMapper;
import com.example.movice_ticket.model.Ticket;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

@Service
public class TicketDAOImpl implements TicketDao {
    @Autowired
    private TicketMapper ticketMapper;

    @Transactional(propagation = Propagation.REQUIRED)
    @Override
    public boolean createTicket(Ticket ticket) {
        return ticketMapper.createTicket(ticket);
    }

    @Override
    public Ticket getTicket(int ticketId) {
        return ticketMapper.getTicket(ticketId);
    }

    @Override
    public List<Ticket> queryTicketList(Map<String, Object> paramMap) {
        return ticketMapper.queryTicketList(paramMap);
    }

    @Override
    public int queryCountTicket(Map<String, Object> paramMap) {
        return ticketMapper.queryCountTicket(paramMap);
    }

    @Override
    public boolean updateOut_trade_no(Map<String, Object> map) {
        return ticketMapper.updateOut_trade_no(map);
    }

    @Override
    public Ticket getTickeByOut_trade_no(String out_trade_no) {
        return ticketMapper.getTickeByOut_trade_no(out_trade_no);
    }

    @Override
    public List<Ticket> getTickelist(String out_trade_no) {
        return ticketMapper.getTickelist(out_trade_no);
    }

    @Override
    public List<Ticket> getTickes(Map<String, Object> map) {
        return ticketMapper.getTickes(map);
    }

    @Override
    public boolean setOut_trade_no(Map<String, Object> map) {
        return ticketMapper.setOut_trade_no(map);
    }

    @Override
    public boolean setorderState(Map<String, Object> map) {
        return ticketMapper.setorderState(map);
    }

    @Override
    public double countMoney() {
        return ticketMapper.countMoney();
    }
}
