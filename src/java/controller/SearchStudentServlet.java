/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import entity.core.StudentDTO;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.List;
import java.util.Properties;
import javax.naming.NamingException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import manager_dao.impl.StudentInfoDAO;

import utillsHelper.ApplicationConstant;

/**
 *
 * @author Minh Phuc
 */
@WebServlet(name = "SearchStudentServlet", urlPatterns = {"/SearchStudentServlet"})
public class SearchStudentServlet extends HttpServlet {

    private final String STUDENT_INFO = "studentInfoPage";

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

        String searchStudentById = request.getParameter("txtSearchStudent");
        // String url = STUDENT_INFO;
        HttpSession session = request.getSession(false);
        String role = (String) session.getAttribute("ROLE");
        String url = null;
        if (role.equals("Admin")) {
            url = site_Map.getProperty(ApplicationConstant.AdminSearchStudentServlet.RETURN_STUDENT_PAGE);
        } else if (role.equals("Student")) {
            url = site_Map.getProperty(ApplicationConstant.AdminSearchStudentServlet.RETURN_STUDENT_PAGE);
        }
        try {

            if (role.equals("Admin")) {
                if (!searchStudentById.trim().isEmpty()) {
                    StudentInfoDAO dao = new StudentInfoDAO();
                    dao.searchStudent(searchStudentById);
                    List<StudentDTO> result = dao.getListStudents();
                    request.setAttribute("SEARCH_STUDENT", result);
                } // end search Values has values
            } else if (role.equals("Student")) {
                if (!searchStudentById.trim().isEmpty()) {
                    StudentInfoDAO dao = new StudentInfoDAO();
                    dao.searchStudent(searchStudentById);
                    List<StudentDTO> result = dao.getListStudents();
                    request.setAttribute("SEARCH_STUDENT", result);
                } // end search Values has values
            }

        } catch (SQLException ex) {
            log("AdminSearchStudentByIdServlet _ SQL" + ex.getMessage());
        } catch (NamingException ex) {
            log("AdminSearchStudentByIdServlet _ Naming" + ex.getMessage());
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
