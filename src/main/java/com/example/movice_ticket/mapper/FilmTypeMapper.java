package com.example.movice_ticket.mapper;


import com.example.movice_ticket.model.FilmType;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;
@Mapper
public interface FilmTypeMapper {

	@Select ("select * from filmType")
	public List<FilmType> list();
	
	@Select("select * from filmType where filmTypeId =#{filmType}")
	public FilmType getFilmTypeById(int filmTypeId);
}
