package com.learn.servlets;

import java.io.IOException;
import java.sql.SQLException;

import com.learn.beans.PersonalInfo;
import com.learn.dao.PersonalInfoDAO;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

/**
 * Servlet implementation class PersonalInfoServlet
 */
@WebServlet("/personal")
public class PersonalInfoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	public PersonalInfoServlet() {
       
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
		
		String firstName = validateInput(request.getParameter("firstName"), "First Name");
        String lastName = validateInput(request.getParameter("lastName"), "Last Name");
        String gender = validateInput(request.getParameter("gender"), "Gender");
        
        PersonalInfo personalInfo = new PersonalInfo();
        personalInfo.setFirstName(firstName);
        personalInfo.setLastName(lastName);
        personalInfo.setGender(gender);

        
        HttpSession session = request.getSession();
        session.setAttribute("personalInfo", personalInfo);
        
        response.sendRedirect("contact-info.html");
		
	}
	
	
	private String validateInput(String input, String fieldName) {
        if (input == null || input.trim().isEmpty()) {
            throw new IllegalArgumentException(fieldName + " cannot be empty");
        }
        return input;
    }
	
	
}
