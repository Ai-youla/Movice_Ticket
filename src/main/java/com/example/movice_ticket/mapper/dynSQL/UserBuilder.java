package com.example.movice_ticket.mapper.dynSQL;


import com.example.movice_ticket.model.User;
import org.apache.ibatis.jdbc.SQL;

import java.util.Map;

 

public class UserBuilder {

	public String generateSQL(Map<String,Object> paramMap) {
		String sql = new SQL() {
			{
				SELECT("*") ;
				FROM("user") ;
				User user =(User) paramMap.get("user");
				if(user !=null) {
					String userName = user.getUserName();
					if(userName != null && !userName.equals("")) {
						WHERE("userName like concat('%',#{user.userName},'%')");
					}
					
					String email = user.getEmail();
					if(email !=null && !email.equals("")) {
						WHERE("email like concat('%',#{user.email},'%')");
					}
				}
				WHERE("userId>1");
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
				{
					SELECT("count(*)") ;
					FROM("user") ;
					User user =(User) paramMap.get("user");
					if(user !=null) {
						String userName = user.getUserName();
						if(userName != null && !userName.equals("")) {
							WHERE("userName like concat('%',#{user.userName},'%')");
						}
						
						String email = user.getEmail();
						if(email !=null && !email.equals("")) {
							WHERE("email like concat('%',#{user.email},'%')");
						}
					}
					WHERE("userId>1");
					  
				}	
			}	
		}.toString();
		return sql;
	}
}
