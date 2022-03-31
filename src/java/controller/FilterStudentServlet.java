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
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.NamingException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import manager_dao.impl.StudentInfoDAO;
import utillsHelper.ApplicationConstant;

/**
 *
 * @author 84399
 */
@WebServlet(name = "FilterStudentServlet", urlPatterns = {"/FilterStudentServlet"})
public class FilterStudentServlet extends HttpServlet {

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

        ServletContext context = this.getServletContext();
        Properties site_Map = (Properties) context.getAttribute("SITE_MAP");
        String status = request.getParameter("status");
        String url = site_Map.getProperty(ApplicationConstant.FilterStudentServlet.RETURN_PAGE);
        try {
            StudentInfoDAO stuDao = new StudentInfoDAO();
            if (status.equals("In_group")) {
                List<StudentDTO> student_filter_list = stuDao.filterStudentInGroup();
                request.setAttribute("FILTER_STUDENT", student_filter_list);
                RequestDispatcher rd = request.getRequestDispatcher(url);
                rd.forward(request, response);
            } else if (status.equals("free")) {
                List<StudentDTO> student_filter_list = stuDao.filterStudentNotInGroup();
                request.setAttribute("FILTER_STUDENT", student_filter_list);
                RequestDispatcher rd = request.getRequestDispatcher(url);
                rd.forward(request, response);
            } else if("all".equals(status)){
                response.sendRedirect(url);
            }
        } catch (NamingException ex) {
            Logger.getLogger(FilterStudentServlet.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(FilterStudentServlet.class.getName()).log(Level.SEVERE, null, ex);
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
