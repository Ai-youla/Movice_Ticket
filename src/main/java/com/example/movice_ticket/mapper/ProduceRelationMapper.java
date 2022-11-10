package com.example.movice_ticket.mapper;


import com.example.movice_ticket.model.ProduceRelation;
import org.apache.ibatis.annotations.*;

import java.util.List;
import java.util.Map;
@Mapper
public interface ProduceRelationMapper {

	@Insert("insert into produceRelation(vipId,produceId,number)value(#{vipId},#{produceId},0)")
	@Options(useGeneratedKeys =  true, keyColumn = "produceRelationId", keyProperty = "produceRelationId")
	public boolean createProduceRelation(ProduceRelation produceRelation);
	
	@Delete("delete from produceRelation where produceRelationId=#{produceRelationId} ")
	public boolean delete(int produceRelationId);
	
	@Select("select * from produceRelation where vipId=#{vipId}")
	@Result(property = "produce",column = "produceId",
			one=@One(select="com.example.movice_ticket.mapper.ProduceMapper.getProduceById"))
	public List<ProduceRelation> getProducebyId(int vipId) ;
	
	@Select("select * from produceRelation where vipId=#{vipId} limit #{begin},#{size}")
	@Result(property = "produce",column = "produceId",
	one=@One(select="com.example.movice_ticket.mapper.ProduceMapper.getProduceById"))
	public List<ProduceRelation> getProduce(Map<String, Object>param) ;
	
	@Select("select count(*) from produceRelation where vipId=#{vipId} ")
	public int getProduceConut(int vipId) ;

	@Update("update produceRelation set number=number + #{number} where produceRelationId=#{produceRelationId} ")
	public boolean updateNumber(Map<String, Object> map);
}
