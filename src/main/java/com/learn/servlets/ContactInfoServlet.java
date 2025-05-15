package com.learn.servlets;

import java.io.IOException;
import java.sql.SQLException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import com.learn.beans.ContactInfo;
import com.learn.dao.ContactInfoDAO;

/**
 * Servlet implementation class ContactInfoServlet
 */
@WebServlet("/contact")
public class ContactInfoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
    public ContactInfoServlet() {
        
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
		String address = validateInput(request.getParameter("address"), "Address");
        String city = validateInput(request.getParameter("city"), "City");
        String state = validateInput(request.getParameter("state"), "State");
        String country = validateInput(request.getParameter("country"), "Country");
        String phone = validateInput(request.getParameter("phone"), "Phone");
        
        ContactInfo info = new ContactInfo();
        info.setAddress(address);
        info.setCity(city);
        info.setState(state);
        info.setCountry(country);
        info.setPhone(phone);
        
        
        HttpSession session = request.getSession();
        session.setAttribute("contactInfo", info);
       
        response.sendRedirect("bank-info.html");
	}
	
	private String validateInput(String input, String fieldName) {
        if (input == null || input.trim().isEmpty()) {
            throw new IllegalArgumentException(fieldName + " cannot be empty");
        }
        return input;
    }
	

}
