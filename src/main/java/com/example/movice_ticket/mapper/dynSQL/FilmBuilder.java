package com.example.movice_ticket.mapper.dynSQL;


import com.example.movice_ticket.model.Film;
import org.apache.ibatis.jdbc.SQL;

import java.util.Map;

public class FilmBuilder {

	public String generateSQL(Map<String, Object> paramMap) {
		String sql = new SQL() {
			{
				SELECT("*");
				FROM("film");
				Film film = (Film) paramMap.get("film");
				if (film != null) {
					String filmName = film.getFilmName();

					if (filmName != null && !filmName.equals("")) {

						WHERE("filmName like concat('%',#{film.filmName},'%')");
					}
					String state = film.getState();
					if (state != null && !state.equals("")) {

						WHERE("state like concat('%',#{film.state},'%')");
					}
				}
				ORDER_BY("filmId desc");
				Object beginObj = paramMap.get("begin");
				Object sizeObj = paramMap.get("size");

				if (beginObj != null && sizeObj != null) {
					LIMIT(" #{begin},#{size}");
				}
			}
		}.toString();
		return sql;
	}

	public String generateCountSQL(Map<String, Object> paramMap) {
		String sql = new SQL() {
			{
				SELECT("count(*)");
				FROM("film");
				Film film = (Film) paramMap.get("film");

				if (film != null) {
					String filmName = film.getFilmName();
					if (filmName != null && !filmName.equals("")) {
						WHERE("filmName like concat('%',#{film.filmName},'%')");
					}

					String state = film.getState();
					if (state != null && !state.equals("")) {
						WHERE("state like concat('%',#{film.state},'%')");
					}
				}

			}
		}.toString();
		return sql;
	}
}
