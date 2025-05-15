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

/**
 * Servlet implementation class BankInfoServlet
 */
@WebServlet("/bank")
public class BankInfoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private final BankInfoDAO bankInfoDAO;
    /**
     * Default constructor. 
     */
    public BankInfoServlet() {
        this.bankInfoDAO = new BankInfoDAO();
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
		info.setBankName(request.getParameter("bankName"));
		info.setAccountNo(request.getParameter("accountNo"));
		info.setSsn(request.getParameter("ssn"));
		
        try {
			if(bankInfoDAO.saveBankInfo(info) > 0) {
				response.sendRedirect("success.jsp");
			}else {
				handleError(request, response, "Failed to save bank information");
			}

		} catch (SQLException e) {
			handleError(request, response, "Database error occurred");
			e.printStackTrace();
		} catch (IOException e) {
			handleError(request, response, e.getMessage());
		}
		
	}
	
	private String validateInput(String input, String fieldName) {
        if (input == null || input.trim().isEmpty()) {
            throw new IllegalArgumentException(fieldName + " cannot be empty");
        }
        return input;
    }
	
	private void handleError(HttpServletRequest request, HttpServletResponse response, String errorMessage) 
            throws IOException {
        request.getSession().setAttribute("error", errorMessage);
        response.sendRedirect("bank-info.html");
    }

}
