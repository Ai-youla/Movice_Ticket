package com.example.movice_ticket.mapper.dynSQL;


import com.example.movice_ticket.model.Produce;
import org.apache.ibatis.jdbc.SQL;

import java.util.Map;

 

public class ProduceBuilder {

	public String generateSQL(Map<String,Object> param) {
		String sql = new SQL() {
			{
				SELECT("*") ;
				FROM("produce") ;
				Produce produce =(Produce) param.get("produce");
				if(produce !=null) {
					String produceName =produce.getProduceName();
					if(produceName != null && !produceName.equals("")) {
						WHERE("produceName like concat('%',#{produce.produceName},'%')");
					} 
				}
				 
				 Object beginObj = param.get("begin") ;
				 Object sizeObj = param.get("size") ;
				 
				 if ( beginObj != null && sizeObj != null ) {
					 LIMIT(" #{begin},#{size}") ;
				 }
			}	
		}.toString();
		return sql;
	}
	
	public String generateCountSQL(Map<String,Object> param) {
		String sql = new SQL() {
			{
				SELECT("count(*)") ;
				FROM("produce") ;
				Produce produce =(Produce) param.get("produce");
				if(produce !=null) {
					String produceName = produce.getProduceName();
					if(produceName != null && !produceName.equals("")) {
						WHERE("produceName like concat('%',#{produce.produceName},'%')");
					} 
				}
			}	
		}.toString();
		return sql;
	}
}
