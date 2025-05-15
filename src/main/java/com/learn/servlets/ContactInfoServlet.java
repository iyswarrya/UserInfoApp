package com.learn.servlets;

import java.io.IOException;
import java.sql.SQLException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import com.learn.beans.ContactInfo;
import com.learn.dao.ContactInfoDAO;

/**
 * Servlet implementation class ContactInfoServlet
 */
@WebServlet("/contact")
public class ContactInfoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * Default constructor. 
     */
    public ContactInfoServlet() {
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
		ContactInfo info = new ContactInfo();
        info.setAddress(request.getParameter("address"));
        info.setCity(request.getParameter("city"));
        info.setState(request.getParameter("state"));
        info.setCountry(request.getParameter("country"));
        info.setPhone(request.getParameter("phone"));
        ContactInfoDAO contactInfoDAO = new ContactInfoDAO();
        try {
			if(contactInfoDAO.saveContactInfo(info) > 0) {
				
				response.sendRedirect("bank-info.html");
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
