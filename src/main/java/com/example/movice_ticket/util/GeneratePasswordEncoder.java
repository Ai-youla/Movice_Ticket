package com.example.movice_ticket.util;

import com.example.movice_ticket.dao.PositionRelationDAO;
import com.example.movice_ticket.model.PositionRelation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.*;


public class GeneratePasswordEncoder {
	
	public static String generatePasswordEncoder(String password) {

		return  new BCryptPasswordEncoder().encode(password) ;
	}

	public static void main(String[] args) {


	}
}
