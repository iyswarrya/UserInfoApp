package com.learn.servlets;

import java.io.IOException;
import java.sql.SQLException;

import com.learn.beans.BankInfo;
import com.learn.dao.BankInfoDAO;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

/**
 * Servlet implementation class BankInfoServlet
 */
@WebServlet("/bank")
public class BankInfoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
    /**
     * Default constructor. 
     */
    public BankInfoServlet() {
        
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String bankName = validateInput(request.getParameter("bankName"), "Bank Name");
        String accountNo = validateInput(request.getParameter("accountNo"), "Account Number");
        String ssn = validateInput(request.getParameter("ssn"), "SSN");
        
        BankInfo info = new BankInfo();
		info.setBankName(bankName);
		info.setAccountNo(accountNo);
		info.setSsn(ssn);
		
        HttpSession session = request.getSession();
        session.setAttribute("bankInfo", info);
        response.sendRedirect("FinalServlet");
		
	}
	
	private String validateInput(String input, String fieldName) {
        if (input == null || input.trim().isEmpty()) {
            throw new IllegalArgumentException(fieldName + " cannot be empty");
        }
        return input;
    }
	
}
