package com.learn.controller;

import java.io.IOException;

import com.learn.model.BankInfo;
import com.learn.model.ContactInfo;
import com.learn.model.PersonalInfo;
import com.learn.service.UserInfoService;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet(urlPatterns = {"/user/*"})
public class UserInfoController extends HttpServlet {
    private static final long serialVersionUID = 1L;
	private final UserInfoService userInfoService;
    
    public UserInfoController() {
        this.userInfoService = new UserInfoService();
    }
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        String pathInfo = request.getPathInfo();
        
        try {
            switch (pathInfo) {
                case "/personal":
                    handlePersonalInfo(request, response);
                    break;
                case "/contact":
                    handleContactInfo(request, response);
                    break;
                case "/bank":
                    handleBankInfo(request, response);
                    break;
                case "/submit":
                    handleFinalSubmission(request, response);
                    break;
                default:
                    response.sendError(HttpServletResponse.SC_NOT_FOUND);
            }
        } catch (Exception e) {
            request.setAttribute("error", e.getMessage());
            request.getRequestDispatcher("/error.jsp").forward(request, response);
        }
    }
    
    private void handlePersonalInfo(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        PersonalInfo personalInfo = new PersonalInfo();
        personalInfo.setFirstName(request.getParameter("firstName"));
        personalInfo.setLastName(request.getParameter("lastName"));
        personalInfo.setGender(request.getParameter("gender"));
        
        HttpSession session = request.getSession();
        session.setAttribute("personalInfo", personalInfo);
        
        response.sendRedirect(request.getContextPath() + "/contact-info.html");
    }
    
    // Similar methods for handleContactInfo and handleBankInfo
    private void handleContactInfo(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        
        
        ContactInfo info = new ContactInfo();
        info.setAddress(request.getParameter("address"));
        info.setCity(request.getParameter("city"));
        info.setState(request.getParameter("state"));
        info.setCountry(request.getParameter("country"));
        info.setPhone(request.getParameter("phone"));
        
        session.setAttribute("contactInfo", info);   
        
        response.sendRedirect(request.getContextPath() + "/bank-info.html");
    }
    
    private void handleBankInfo(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        
        // Create BankInfo object
        BankInfo info = new BankInfo();
		info.setBankName(request.getParameter("bankName"));
		info.setAccountNo(request.getParameter("accountNo"));
		info.setSsn(request.getParameter("ssn"));
        
        session.setAttribute("bankInfo", info);
        
        response.sendRedirect(request.getContextPath() + "/submit.html");
    }
    
    private void handleFinalSubmission(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        PersonalInfo personalInfo = (PersonalInfo) session.getAttribute("personalInfo");
        ContactInfo contactInfo = (ContactInfo) session.getAttribute("contactInfo");
        BankInfo bankInfo = (BankInfo) session.getAttribute("bankInfo");
        
        try {
            userInfoService.saveUserInfo(personalInfo, contactInfo, bankInfo);
            response.sendRedirect(request.getContextPath() + "/success.jsp");
        } catch (Exception e) {
            request.setAttribute("error", "Failed to save user information: " + e.getMessage());
            request.getRequestDispatcher("/error.jsp").forward(request, response);
        }
    }
} 