package com.learn.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.learn.beans.ContactInfo;
import com.learn.util.DBUtil;

public class ContactInfoDAO {
	public int saveContactInfo(ContactInfo contactInfo) throws SQLException {
		if (contactInfo == null) {
            throw new IllegalArgumentException("ContactInfo cannot be null");
        }
		
		String sql = "INSERT INTO contact_info (address, city, state, country, phone) VALUES (?, ?, ?, ?, ?)";
		
		try (Connection conn = DBUtil.getConnection();
				PreparedStatement pstmt = conn.prepareStatement(sql)){
	                pstmt.setString(1, contactInfo.getAddress());
	                pstmt.setString(2, contactInfo.getCity());
	                pstmt.setString(3, contactInfo.getState());
	                pstmt.setString(4, contactInfo.getCountry());
	                pstmt.setString(5, contactInfo.getPhone());
	                return pstmt.executeUpdate();
	        }
		}
	}

