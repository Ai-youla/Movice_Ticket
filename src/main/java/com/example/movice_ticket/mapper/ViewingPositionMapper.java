package com.example.movice_ticket.mapper;

import com.example.movice_ticket.model.ViewingPosition;
import org.apache.ibatis.annotations.*;

@Mapper
public interface ViewingPositionMapper {
    @Insert("insert into viewingposition ( positionNum, positionState, position)value( #{positionNum}, 0, #{position})")
   @Options(useGeneratedKeys = true,keyColumn = "viewingPositionId",keyProperty = "viewingPositionId")
    public boolean createViewingPosition(ViewingPosition viewingPosition);
    @Select("select * from viewingposition where viewingPositionId = #{viewingPositionId}")
    public ViewingPosition byId(int viewingPositionId);
    @Update("update viewingposition set positionNum=#{positionNum}, positionState=#{positionState}, position=#{position} where viewingPositionId=#{viewingPositionId}")
    public boolean updateViewingposition(ViewingPosition viewingPosition);

}
