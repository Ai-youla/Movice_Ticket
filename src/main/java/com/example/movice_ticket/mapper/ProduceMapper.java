package com.example.movice_ticket.mapper;


import com.example.movice_ticket.model.Produce;
import org.apache.ibatis.annotations.*;

import java.util.List;
import java.util.Map;
@Mapper
public interface ProduceMapper {

	@Insert("insert into produce(produceName, picture, integral, produceNum, state, beginDate, overDate)value(#{produceName}, #{picture}, #{integral},#{produceNum}, 0, #{beginDate}, #{overDate})")
	public boolean createProduce(Produce produce);
	
	@Update("update produce set produceName=#{produceName} ,integral=#{integral},produceNum=#{produceNum},beginDate=#{beginDate},overDate=#{overDate} where produceId=#{produceId}")
	public boolean updateProduce(Produce produce);
	
	@Update("update produce set picture=#{picture} where produceId=#{produceId}")
	public boolean updateProducePricture(Map<String, Object>param);
	
	@Update("update produce set state=#{state} where produceId=#{produceId}")
	public boolean updateState(Map<String, Object>param);
	
	@Update("update produce set produceNum=produceNum - #{produceNum} where produceId=#{produceId}")
	public boolean reduceProduceNum(Map<String, Object>param);
	
	@Select("select * from produce where produceId=#{produceId}")
	public Produce getProduceById(int produceId);
	
	@SelectProvider(type=com.example.movice_ticket.mapper.dynSQL.ProduceBuilder.class,method="generateSQL")
	public List<Produce> produceList(Map<String, Object>param);
	
	@SelectProvider(type=com.example.movice_ticket.mapper.dynSQL.ProduceBuilder.class,method="generateCountSQL")
	public int produceConutList(Map<String, Object>param);
	
}
