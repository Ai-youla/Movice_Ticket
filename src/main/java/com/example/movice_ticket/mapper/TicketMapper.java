package com.example.movice_ticket.mapper;

import com.example.movice_ticket.model.Ticket;
import org.apache.ibatis.annotations.*;
import java.util.List;
import java.util.Map;

@Mapper
public interface TicketMapper {
    @Insert("insert into ticket(cinemaId, filmId, userId, orderState, money, ticketTime, out_trade_no,positionRelationId)" +
            "value(#{cinemaId},#{filmId},#{userId},0,#{money},null,#{out_trade_no},#{positionRelationId})")
//    @Options(useGeneratedKeys = true, keyProperty = "ticket", keyColumn = "ticketId")
    public boolean createTicket(Ticket ticket);

    @Select("select * from ticket where ticketId = #{ticketId}")
    @Result(property = "cinema",column = "cinemaId",
            one=@One(select="com.example.movice_ticket.mapper.CinemaMapper.getCinemaById"))
    @Result(property = "user",column = "userId",
            one=@One(select="com.example.movice_ticket.mapper.UserMapper.query"))
    @Result(property = "film",column = "filmId",
            one=@One(select="com.example.movice_ticket.mapper.FilmMapper.getFilmById"))
    public Ticket getTicket(int ticketId);

    @SelectProvider(type=com.example.movice_ticket.mapper.dynSQL.TicketBuilder.class,method="generateSQL")
    @Result(property = "cinema",column = "cinemaId",
            one=@One(select="com.example.movice_ticket.mapper.CinemaMapper.getCinemaById"))
    @Result(property = "user",column = "userId",
            one=@One(select="com.example.movice_ticket.mapper.UserMapper.query"))
    @Result(property = "film",column = "filmId",
            one=@One(select="com.example.movice_ticket.mapper.FilmMapper.getFilmById"))
    @Result(property = "positionRelation",column = "positionRelationId",
            one=@One(select="com.example.movice_ticket.mapper.PositionRelationMapper.ById"))
    public List<Ticket> queryTicketList(Map<String,Object>paramMap);

    @SelectProvider(type=com.example.movice_ticket.mapper.dynSQL.TicketBuilder.class,method="generateCountSQL")
    public int queryCountTicket(Map<String,Object>paramMap);

    @Update("update ticket set ticketTime = now() where ticketId = #{ticketId} ")
    public boolean updateOut_trade_no(Map<String,Object>map);

    @Select("select * from ticket where out_trade_no =#{out_trade_no}")
    @Result(property = "cinema",column = "cinemaId",
            one=@One(select="com.example.movice_ticket.mapper.CinemaMapper.getCinemaById"))
    @Result(property = "user",column = "userId",
            one=@One(select="com.example.movice_ticket.mapper.UserMapper.query"))
    @Result(property = "film",column = "filmId",
            one=@One(select="com.example.movice_ticket.mapper.FilmMapper.getFilmById"))
    public Ticket getTickeByOut_trade_no(String out_trade_no);

    @Select("select * from ticket where out_trade_no =#{out_trade_no}")
    @Result(property = "cinema",column = "cinemaId",
            one=@One(select="com.example.movice_ticket.mapper.CinemaMapper.getCinemaById"))
    @Result(property = "user",column = "userId",
            one=@One(select="com.example.movice_ticket.mapper.UserMapper.query"))
    @Result(property = "film",column = "filmId",
            one=@One(select="com.example.movice_ticket.mapper.FilmMapper.getFilmById"))
    public List<Ticket> getTickelist(String out_trade_no);

    @Select("select * from ticket where userId =#{userId} and filmId=#{filmId} and cinemaId=#{cinemaId}")
    @Result(property = "cinema",column = "cinemaId",
            one=@One(select="com.example.movice_ticket.mapper.CinemaMapper.getCinemaById"))
    @Result(property = "user",column = "userId",
            one=@One(select="com.example.movice_ticket.mapper.UserMapper.query"))
    @Result(property = "film",column = "filmId",
            one=@One(select="com.example.movice_ticket.mapper.FilmMapper.getFilmById"))
    public List<Ticket> getTickes(Map<String,Object>map);

    @Update("update ticket set  out_tradqueryCountTickete_no =#{out_trade_no} where userId = #{userId} and filmId=#{filmId} and cinemaId=#{cinemaId} ")
    public boolean setOut_trade_no(Map<String,Object>map);
    @Update("update ticket set  orderState =#{orderState} where out_trade_no =#{out_trade_no} ")
    public boolean setorderState(Map<String,Object>map);

    @Select("select sum(money)  from ticket")
    public double countMoney();
}
