package com.example.movice_ticket.mapper;

import com.example.movice_ticket.model.Reply;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Service;

import java.util.List;

@Mapper
public interface ReplyMapper {
    @Insert("insert into reply(filmReviewId, userId, replyText, replytime)value(#{filmReviewId}, #{userId}, #{replyText}, now())")
    public boolean createReplay(Reply reply);

    @Select("select * from reply where filmReviewId=#{filmReviewId} and userId=#{userId}")
    @Result(property="user",column="userId",
            one=@One(select="com.example.movice_ticket.mapper.UserMapper.query"))
    @Result(property="filmReview",column="filmReviewId",
            one=@One(select="com.example.movice_ticket.mapper.FilmReviewMapper.byfilmReviewId"))
    public Reply byId (Reply reply);

    @Select("select * from reply where filmReviewId=#{filmReviewId} and userId=#{userId}")
    @Result(property="user",column="userId",
            one=@One(select="com.example.movice_ticket.mapper.UserMapper.query"))
    @Result(property="filmReview",column="filmReviewId",
            one=@One(select="com.example.movice_ticket.mapper.FilmReviewMapper.byfilmReviewId"))
    public List<Reply> moreReply (Reply reply);

    @Select("select * from reply ")
    @Result(property="user",column="userId",
            one=@One(select="com.example.movice_ticket.mapper.UserMapper.query"))
    @Result(property="filmReview",column="filmReviewId",
            one=@One(select="com.example.movice_ticket.mapper.FilmReviewMapper.byfilmReviewId"))
    public List<Reply> replys();
}
