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

/**
 * Servlet implementation class PersonalInfoServlet
 */
@WebServlet("/personal")
public class PersonalInfoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * Default constructor. 
     */
    public PersonalInfoServlet() {
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
		PersonalInfo personalInfo = new PersonalInfo();	
		personalInfo.setFirstName(request.getParameter("firstName"));
        personalInfo.setLastName(request.getParameter("lastName"));
        personalInfo.setGender(request.getParameter("gender"));
        PersonalInfoDAO personalInfoDAO = new PersonalInfoDAO();
        
        try {
			if(personalInfoDAO.savePersonalInfo(personalInfo) > 0) {
				response.sendRedirect("contact-info.html");
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
