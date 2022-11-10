package com.example.movice_ticket.dao.impl;

import com.example.movice_ticket.dao.ReplyDAO;
import com.example.movice_ticket.mapper.ReplyMapper;
import com.example.movice_ticket.model.Reply;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReplyDAOImpl implements ReplyDAO {
    @Autowired
    private ReplyMapper replyMapper;

    @Override
    public boolean createReplay(Reply reply) {
        return replyMapper.createReplay(reply);
    }

    @Override
    public Reply byId(Reply reply) {
        return replyMapper.byId(reply);
    }

    @Override
    public List<Reply> replys() {
        return replyMapper.replys();
    }

    @Override
    public List<Reply> moreReply(Reply reply) {
        return replyMapper.moreReply(reply);
    }
}
