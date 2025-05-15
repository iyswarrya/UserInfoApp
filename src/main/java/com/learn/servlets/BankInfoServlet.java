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

    /**
     * Default constructor. 
     */
    public BankInfoServlet() {
        // TODO Auto-generated constructor stub
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
		BankInfo info = new BankInfo();
		info.setBankName(request.getParameter("bankName"));
		info.setAccountNo(request.getParameter("accountNo"));
		info.setSsn(request.getParameter("ssn"));
		
		BankInfoDAO bankInfoDAO = new BankInfoDAO();
        try {
			if(bankInfoDAO.saveBankInfo(info) > 0) {
				response.sendRedirect("success.jsp");
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		
	}

}
