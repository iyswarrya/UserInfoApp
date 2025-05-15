package com.learn.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.learn.beans.BankInfo;
import com.learn.util.DBUtil;

public class BankInfoDAO {
	public int saveBankInfo(Connection conn, BankInfo bankInfo) throws SQLException {
		if (bankInfo == null) {
            throw new IllegalArgumentException("BankInfo cannot be null");
        }
		
		String sql = "INSERT INTO bank_info (bankName, accountNo, ssn) VALUES (?, ?, ?)";
		
		try (PreparedStatement pstmt = conn.prepareStatement(sql)){
	                pstmt.setString(1, bankInfo.getBankName());
	                pstmt.setString(2, bankInfo.getAccountNo());
	                pstmt.setString(3, bankInfo.getSsn());
	                return pstmt.executeUpdate();
	            }
		}	
        
    }
