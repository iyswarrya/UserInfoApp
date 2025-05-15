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
	private final ContactInfoDAO contactInfoDAO;
    /**
     * Default constructor. 
     */
    public ContactInfoServlet() {
        this.contactInfoDAO = new ContactInfoDAO();
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
        
        /*
        HttpSession session = request.getSession();
        session.setAttribute("address", request.getParameter("address"));
        session.setAttribute("city", request.getParameter("city"));
        session.setAttribute("state", request.getParameter("state"));
        session.setAttribute("country", request.getParameter("country"));
        session.setAttribute("phone", request.getParameter("phone"));*/
        
		ContactInfo info = new ContactInfo();
        info.setAddress(request.getParameter("address"));
        info.setCity(request.getParameter("city"));
        info.setState(request.getParameter("state"));
        info.setCountry(request.getParameter("country"));
        info.setPhone(request.getParameter("phone"));
        
        try {
			if(contactInfoDAO.saveContactInfo(info) > 0) {	
				response.sendRedirect("bank-info.html");
			}else {
				handleError(request, response, "Failed to save contact information");
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
        response.sendRedirect("contact-info.html");
    }

}
