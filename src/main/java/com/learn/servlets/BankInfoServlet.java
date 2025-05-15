package com.learn.servlets;

import java.io.IOException;

import com.learn.beans.BankInfo;

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
		info.setAccountNo(Integer.parseInt(request.getParameter("accountNo")));
		info.setSsn(Integer.parseInt(request.getParameter("ssn")));
		
		HttpSession session = request.getSession();
		session.setAttribute("contactInfo", info);
		response.sendRedirect("success.jsp");
		
	}

}
