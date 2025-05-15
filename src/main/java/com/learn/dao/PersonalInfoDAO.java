package com.learn.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.learn.beans.PersonalInfo;
import com.learn.util.DBUtil;

public class PersonalInfoDAO {
    
    public int savePersonalInfo(PersonalInfo personalInfo) throws SQLException {
        Connection conn = DBUtil.getConnection();
        try {
        	 String sql = "INSERT INTO personal_info (first_name, last_name, gender) VALUES (?, ?, ?)";
             try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
                 pstmt.setString(1, personalInfo.getFirstName());
                 pstmt.setString(2, personalInfo.getLastName());
                 pstmt.setString(3, personalInfo.getGender());
                
                 // Execute the statement
                 return  pstmt.executeUpdate();
             }
        } finally {
            DBUtil.closeConnection(conn);
        }
    }
}