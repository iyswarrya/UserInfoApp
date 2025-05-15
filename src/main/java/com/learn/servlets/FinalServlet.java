package com.learn.servlets;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

import com.learn.beans.BankInfo;
import com.learn.beans.ContactInfo;
import com.learn.beans.PersonalInfo;
import com.learn.dao.BankInfoDAO;
import com.learn.dao.ContactInfoDAO;
import com.learn.dao.PersonalInfoDAO;
import com.learn.util.DBUtil;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import jakarta.servlet.http.HttpSession;

/**
 * Servlet implementation class FinalServlet
 */
@WebServlet("/FinalServlet")
public class FinalServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private final PersonalInfoDAO personalInfoDAO;
	private final ContactInfoDAO contactInfoDAO;
	private final BankInfoDAO bankInfoDAO;
    /**
     * Default constructor. 
     */
    public FinalServlet() {
    	this.personalInfoDAO = new PersonalInfoDAO();
    	this.contactInfoDAO = new ContactInfoDAO();
    	this.bankInfoDAO = new BankInfoDAO();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		PersonalInfo personal = (PersonalInfo) session.getAttribute("personalInfo");
        ContactInfo contact = (ContactInfo) session.getAttribute("contactInfo");
        BankInfo bank = (BankInfo) session.getAttribute("bankInfo");
        
        Connection conn = null;
        try {
            conn = DBUtil.getConnection();
            conn.setAutoCommit(false); // Start transaction

            int pResult = personalInfoDAO.savePersonalInfo(conn, personal);
            int cResult = contactInfoDAO.saveContactInfo(conn, contact);
            int bResult = bankInfoDAO.saveBankInfo(conn, bank);

            if (pResult > 0 && cResult > 0 && bResult > 0) {
                conn.commit();
                response.getWriter().write("All data submitted successfully.");
                response.sendRedirect("success.jsp");
            } else {
                conn.rollback();
                response.getWriter().write("Data submission failed. Transaction rolled back.");
            }

        } catch (Exception e) {
            try {
                if (conn != null) conn.rollback();
            } catch (SQLException se) {
                se.printStackTrace();
            }
            e.printStackTrace();
            response.getWriter().write("Exception occurred. Transaction rolled back.");
        } finally {
            DBUtil.closeConnection(conn);
        }
    
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
       
	}

}
