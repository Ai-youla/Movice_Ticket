package com.example.movice_ticket.mapper;


import com.example.movice_ticket.model.FilmPrice;
import org.apache.ibatis.annotations.*;

import java.util.List;
@Mapper
public interface FilmPriceMapper {

	@Insert("insert into filmPrice(price, filmNumber, allNumber)value(#{price}, 0,0)")
	@Options(useGeneratedKeys = true,keyProperty = "filmPriceId",keyColumn = "filmPriceId")
	public int createFilmPrice(FilmPrice filmPrice);
	
	@Select("select * from filmPrice")
	public List<FilmPrice> list();
	
	@Select("select * from filmPrice where filmPriceId = #{filmPriceId}")
	public FilmPrice queryById(int filmPriceId);
	
	@Update("update filmPrice set price=#{price}  where filmPriceId=#{filmPriceId}")
	public int updateFilmPrice(FilmPrice filmPrice);
	
	/*
	 * @Update("update filmPrice set  filmNumber=filmNumber+#{num} and allNumber=allNumber+#{num} where filmPriceId=#{filmPriceId}"
	 * ) public int updateNumber(Map<String, Object> map);
	 * 
	 * @Update("update filmPrice set  filmNumber=filmNumber - #{num}  where filmPriceId=#{filmPriceId}"
	 * ) public int reduce(Map<String, Object> map);
	 * 
	 * @Update("update filmPrice set  filmNumber=filmNumber + #{num}  where filmPriceId=#{filmPriceId}"
	 * ) public int add(Map<String, Object> map);
	 */
}
