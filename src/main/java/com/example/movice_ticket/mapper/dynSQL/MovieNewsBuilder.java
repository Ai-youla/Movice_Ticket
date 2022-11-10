package com.example.movice_ticket.mapper.dynSQL;


import com.example.movice_ticket.model.MovieNews;
import org.apache.ibatis.jdbc.SQL;

import java.util.Map;

public class MovieNewsBuilder {

	public String generateSQL(Map<String,Object> paramMap) {
		String sql = new SQL() {
			{
				SELECT("*") ;
				FROM("movieNews") ;
				MovieNews movieNews =(MovieNews) paramMap.get("movieNews");
				if(movieNews !=null) {
					String title = movieNews.getTitle();
					if(title != null && !title.equals("")) {
						WHERE("title like concat('%',#{movieNews.title},'%')");
					} 
				}
				 
				 Object beginObj = paramMap.get("begin") ;
				 Object sizeObj = paramMap.get("size") ;
				 
				 if ( beginObj != null && sizeObj != null ) {
					 LIMIT(" #{begin},#{size}") ;
				 }
			}	
		}.toString();
		return sql;
	}
	
	public String generateCountSQL(Map<String,Object> paramMap) {
		String sql = new SQL() {
			{
				SELECT("count(*)") ;
				FROM("movieNews") ;
				MovieNews movieNews =(MovieNews) paramMap.get("movieNews");
				if(movieNews !=null) {
					String title = movieNews.getTitle();
					if(title != null && !title.equals("")) {
						WHERE("title like concat('%',#{movieNews.title},'%')");
					} 
				}
			}	
		}.toString();
		return sql;
	}
}
