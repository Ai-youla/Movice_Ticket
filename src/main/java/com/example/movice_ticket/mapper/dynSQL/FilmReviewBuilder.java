package com.example.movice_ticket.mapper.dynSQL;


import com.example.movice_ticket.model.FilmReview;
import org.apache.ibatis.jdbc.SQL;

import java.util.Map;
 

public class FilmReviewBuilder {

	public String queryFilmReview(Map<String, Object>param) {
		String sql = new SQL() {
			{
				SELECT("*");
				FROM("filmReview");
				FilmReview filmReview = (FilmReview)param.get("filmReview");
				if(filmReview!=null) {
					int userId = filmReview.getUserId();
					if(userId>0) {
						WHERE("userId = #{filmReview.userId}");
					}
					int filmId = filmReview.getFilmId();
					if(filmId>0) {
						WHERE("filmId = #{filmReview.filmId}");
					}
					
					
				}
				Object begin = param.get("begin");
				Object size = param.get("size");
				 if(begin!=null && size!=null) {
					 LIMIT("#{begin},#{size}");
				 }
			}
		}.toString();
		return sql;
	}
	public String queryConutFilmReview(Map<String, Object>param) {
		String sql = new SQL() {
			{
				SELECT("count(*)");
				FROM("filmReview"); 
				FilmReview filmReview = (FilmReview)param.get("filmReview");
				if(filmReview!=null) {
					int userId = filmReview.getUserId();
					if(userId>0) {
						WHERE("userId = #{filmReview.userId}");
					}
					int filmId = filmReview.getFilmId();
					if(filmId>0) {
						WHERE("filmId = #{filmReview.filmId}");
					}
				}
			}
		}.toString();
		return sql;
	}
}
