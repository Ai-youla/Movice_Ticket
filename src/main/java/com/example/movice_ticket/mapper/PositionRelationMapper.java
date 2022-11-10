package com.example.movice_ticket.mapper;

import com.example.movice_ticket.model.PositionRelation;
import org.apache.ibatis.annotations.*;

import java.util.List;
import java.util.Map;

@Mapper
public interface PositionRelationMapper {
    @Insert("insert into positionrelation(seatId,viewingPositionId,cinemaId,filmId,viewingTimeId,userId)value(#{seatId},#{viewingPositionId},#{cinemaId},#{filmId},#{viewingTimeId},#{userId})")
    @Options(useGeneratedKeys = true,keyColumn = "positionRelationId",keyProperty = "positionRelationId")
    public boolean createPositonRelation(PositionRelation positionRelation);

    @Select("select * from positionrelation where seatId=#{seatId} and cinemaId=#{cinemaId} and filmId=#{filmId} ")
    @Result(id = true,column = "seatId",property = "seats",one = @One(select = "com.example.movice_ticket.mapper.SeatsMapper.getSeats"))
    @Result(id = true,column = "viewingTimeId",property = "viewingTime",one = @One(select = "com.example.movice_ticket.mapper.ViewingTimeMapper.getViewingTime"))
    @Result(id = true,column = "viewingPositionId",property = "viewingPosition",one = @One(select = "com.example.movice_ticket.mapper.ViewingPositionMapper.byId"))
    public List<PositionRelation> queryPositionRelation(Map<String, Object> map);

    @Select("select * from positionrelation where userId=#{userId} and cinemaId=#{cinemaId} and filmId=#{filmId} ")
    @Result(id = true,column = "seatId",property = "seats",one = @One(select = "com.example.movice_ticket.mapper.SeatsMapper.getSeats"))
    @Result(id = true,column = "viewingTimeId",property = "viewingTime",one = @One(select = "com.example.movice_ticket.mapper.ViewingTimeMapper.getViewingTime"))
    @Result(id = true,column = "viewingPositionId",property = "viewingPosition",one = @One(select = "com.example.movice_ticket.mapper.ViewingPositionMapper.byId"))
    public List<PositionRelation> queryById(Map<String, Object> map);

    @Select("select * from positionrelation where   positionRelationId=#{positionRelationId} ")
    @Result(id = true,column = "seatId",property = "seats",one = @One(select = "com.example.movice_ticket.mapper.SeatsMapper.getSeats"))
    @Result(id = true,column = "viewingTimeId",property = "viewingTime",one = @One(select = "com.example.movice_ticket.mapper.ViewingTimeMapper.getViewingTime"))
    @Result(id = true,column = "viewingPositionId",property = "viewingPosition",one = @One(select = "com.example.movice_ticket.mapper.ViewingPositionMapper.byId"))
    public PositionRelation ById(int positionRelationId);

    @Delete("delete from  positionrelation where positionRelationId=#{positionRelationId} ")
    public boolean delete(int positionRelationId);

    @Update("update positionrelation set seatId=#{seatId} , viewingTimeId=#{viewingTimeId} , viewingPositionId=#{viewingPositionId}  where positionRelationId=#{positionRelationId}")
    public boolean updatePosition(PositionRelation positionRelation);
}
