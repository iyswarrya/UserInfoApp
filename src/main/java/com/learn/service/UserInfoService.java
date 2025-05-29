package com.learn.service;

import java.sql.Connection;
import java.sql.SQLException;

import com.learn.dao.BankInfoDAO;
import com.learn.dao.ContactInfoDAO;
import com.learn.dao.PersonalInfoDAO;
import com.learn.model.BankInfo;
import com.learn.model.ContactInfo;
import com.learn.model.PersonalInfo;
import com.learn.util.DBUtil;



public class UserInfoService {
    private final PersonalInfoDAO personalInfoDAO;
    private final ContactInfoDAO contactInfoDAO;
    private final BankInfoDAO bankInfoDAO;
    
    public UserInfoService() {
        this.personalInfoDAO = new PersonalInfoDAO();
        this.contactInfoDAO = new ContactInfoDAO();
        this.bankInfoDAO = new BankInfoDAO();
    }
    
    public void saveUserInfo(PersonalInfo personalInfo, ContactInfo contactInfo, BankInfo bankInfo) throws SQLException {
        Connection conn = null;
        try {
            conn = DBUtil.getConnection();
            conn.setAutoCommit(false);
            
            // Validate all models
            personalInfo.validate();
            contactInfo.validate();
            bankInfo.validate();
            
            // Save all information
            int pResult = personalInfoDAO.savePersonalInfo(conn, personalInfo);
            int cResult = contactInfoDAO.saveContactInfo(conn, contactInfo);
            int bResult = bankInfoDAO.saveBankInfo(conn, bankInfo);
            
            if (pResult > 0 && cResult > 0 && bResult > 0) {
                conn.commit();
            } else {
                conn.rollback();
                throw new SQLException("Failed to save user information");
            }
        } catch (Exception e) {
            if (conn != null) {
                conn.rollback();
            }
            throw e;
        } finally {
            DBUtil.closeConnection(conn);
        }
    }
} 