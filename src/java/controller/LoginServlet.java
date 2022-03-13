/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import entity.core.AdminDTO;
import entity.core.LecturerDTO;
import entity.core.StudentDTO;
import entity.errors.LoginCreateError;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.Properties;
import javax.naming.NamingException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import manager_dao.impl.AdminManagerDAO;
import manager_dao.impl.LecturerInfoDAO;
import manager_dao.impl.LoginDAO;
import manager_dao.impl.StudentInfoDAO;
import utillsHelper.ApplicationConstant;

/**
 *
 * @author 84399
 */
public class LoginServlet extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        
        ServletContext context = this.getServletContext();
        Properties site_Map = (Properties) context.getAttribute("SITE_MAP");

        String username = request.getParameter("email");
        String password = request.getParameter("password");
        boolean foundErr = false;
        LoginCreateError errors = new LoginCreateError();
        String url = site_Map.getProperty(ApplicationConstant.LoginServlet.INVALID_PAGE);
        try {
            //call DAO
            LoginDAO dao = new LoginDAO();
            String role = dao.checkLogin(username, password);
            
            if(role == null) {
                foundErr = true;
                errors.setAccountOrPasswordIncorrect("Username or Password is incorrect");
            }
            if(foundErr) {
                request.setAttribute("LOGIN_ERRORS", errors);
                return;
            }
            if(role.equals("Admin")){
                HttpSession session = request.getSession(true);
                AdminManagerDAO adminDAO = new AdminManagerDAO();
                AdminDTO admin = adminDAO.getAdminByUsername(username);
                String adminID = admin.getAdminID();
                session.setAttribute("ROLE", role);
                session.setAttribute("ADMIN_ID", adminID);
                session.setAttribute("WELCOME_NAME", adminID);
                url = site_Map.getProperty(ApplicationConstant.LoginServlet.HOME_PAGE);
            }
            else if (role.equals("Student")) {
                HttpSession session = request.getSession(true);
                StudentInfoDAO stuDAO = new StudentInfoDAO();
                StudentDTO student = stuDAO.getStudentbyEmail(username);
                String fullName = student.getFullName();
                String studentID = student.getStudentID();
                session.setAttribute("ROLE", role);
                session.setAttribute("WELCOME_NAME", fullName);
                session.setAttribute("STUDENT_ID", studentID);
                session.setAttribute("STUDENT_GROUP_ID", student.getGroupID());
                session.setAttribute("ACCOUNT", student);
                url = site_Map.getProperty(ApplicationConstant.LoginServlet.HOME_PAGE);
            } else if (role.equals("Lecture")){
                HttpSession session = request.getSession(true);
                LecturerInfoDAO lecDAO = new LecturerInfoDAO();
                LecturerDTO lecture = lecDAO.getLecturebyEmail(username);
                String fullName = lecture.getFullname();
                session.setAttribute("ROLE", role);
                session.setAttribute("WELCOME_NAME", fullName);
                url = site_Map.getProperty(ApplicationConstant.LoginServlet.HOME_PAGE);
            }
            
        } catch (NamingException ex) {
            log("LoginAdminServlet   Naming: " + ex.getMessage());
        } catch (SQLException ex) {
            log("LoginAdminServlet   SQL: " + ex.getMessage());
        } finally {
            RequestDispatcher rd = request.getRequestDispatcher(url);
            rd.forward(request, response);
            out.close();
        }
            }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
