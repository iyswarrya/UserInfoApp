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
	private final PersonalInfoDAO personalInfoDAO;
	
	public PersonalInfoServlet() {
        this.personalInfoDAO = new PersonalInfoDAO();
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
        
        /*HttpSession session = request.getSession();
        session.setAttribute("firstName", request.getParameter("firstName"));
        session.setAttribute("lastName", request.getParameter("lastName"));
        session.setAttribute("lastName", request.getParameter("gender"));
        response.sendRedirect("contact-info.html");*/
		
        PersonalInfo personalInfo = new PersonalInfo();	
		personalInfo.setFirstName(request.getParameter("firstName"));
        personalInfo.setLastName(request.getParameter("lastName"));
        personalInfo.setGender(request.getParameter("gender"));
  
        
        try {
			if(personalInfoDAO.savePersonalInfo(personalInfo) > 0) {
				response.sendRedirect("contact-info.html");
			}
			else {
				handleError(request, response, "Failed to save personal information");
			}
		} catch (SQLException e) {
			handleError(request, response, "Database error occurred");
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
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
        response.sendRedirect("personal-info.html");
    }

}
