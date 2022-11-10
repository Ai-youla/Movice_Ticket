package com.example.movice_ticket.mapper.dynSQL;

import com.example.movice_ticket.model.Film;
import com.example.movice_ticket.model.Ticket;
import org.apache.ibatis.jdbc.SQL;

import java.util.Map;

public class TicketBuilder {
    public String generateSQL(Map<String, Object> paramMap) {
        String sql = new SQL() {
            {
                SELECT("*");
                FROM("ticket");
                Ticket ticket = (Ticket) paramMap.get("ticket");

                if (ticket != null) {
                    String out_trade_no = ticket.getOut_trade_no();
                    int userId = ticket.getUserId();
                    if (out_trade_no != null && !out_trade_no.equals("")) {

                        WHERE("out_trade_no like concat('%',#{ticket.out_trade_no},'%')");
                    }
                    if (userId > 0 ) {
                        WHERE("userId =#{ticket.userId} ");
                    }
                }

                Object beginObj = paramMap.get("begin");
                Object sizeObj = paramMap.get("size");

                if (beginObj != null && sizeObj != null) {
                    LIMIT(" #{begin},#{size}");
                    ORDER_BY("ticketTime desc");
                }
            }
        }.toString();
        return sql;
    }

    public String generateCountSQL(Map<String, Object> paramMap) {
        String sql = new SQL() {
            {
                SELECT("count(*)");
                FROM("ticket");
                Ticket ticket = (Ticket) paramMap.get("ticket");

                if (ticket != null) {
                    String out_trade_no = ticket.getOut_trade_no();
                    int userId = ticket.getUserId();
                    if (out_trade_no != null && !out_trade_no.equals("")) {

                        WHERE("out_trade_no like concat('%',#{ticket.out_trade_no},'%')");
                    }
                    if (userId != 0 ) {

                        WHERE("userId =#{ticket.userId}");
                    }
                }

            }
        }.toString();
        return sql;
    }
}
