package com.learn.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.learn.beans.PersonalInfo;
import com.learn.util.DBUtil;

public class PersonalInfoDAO {
    
    public int savePersonalInfo(Connection conn, PersonalInfo personalInfo) throws SQLException {
    	if (personalInfo == null) {
            throw new IllegalArgumentException("PersonalInfo cannot be null");
        }
    	
    	String sql = "INSERT INTO personal_info (firstName, lastName, gender) VALUES (?, ?, ?)";
    	
    	try (PreparedStatement pstmt = conn.prepareStatement(sql)){
	                 pstmt.setString(1, personalInfo.getFirstName());
	                 pstmt.setString(2, personalInfo.getLastName());
	                 pstmt.setString(3, personalInfo.getGender());
	               
	                 return  pstmt.executeUpdate();
	             }
    	}
    }
