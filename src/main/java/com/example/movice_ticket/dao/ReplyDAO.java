package com.example.movice_ticket.dao;

import com.example.movice_ticket.model.Reply;

import java.util.List;

public interface ReplyDAO {
    /**
     * 回复影评
     * @param reply
     * @return
     */
    public boolean createReplay(Reply reply);

    /**
     * 通过用户Id，影评Id查询回复
     * @param reply
     * @return
     */
    public Reply byId (Reply reply);

    /**
     * 查询全部回复
     * @return
     */
    public List<Reply> replys();

    /**
     * 查询用户Id和影评Id的全部回复
     * @param reply
     * @return
     */
    public List<Reply> moreReply (Reply reply);
}
