/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import enitiy.LecturerDTO;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.List;
import java.util.Properties;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import manager_dao.impl.LecturerInfoDAO;
import utillsHelper.ApplicationConstant;

/**
 *
 * @author Asus
 */
public class SearchLecturerServlet extends HttpServlet {

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

        String searchLecturerById = request.getParameter("txtSearchLecturer");
        // String url = LECTURER_INFO;
        HttpSession session = request.getSession(false);
        String role = (String) session.getAttribute("ROLE");
        String url = null;
        if (role.equals("Admin")) {
            url = site_Map.getProperty(ApplicationConstant.AdminSearchLecturerServlet.ADMIN_SEARCH_RETURN);
        } else if (role.equals("Student")) {
            url = site_Map.getProperty(ApplicationConstant.AdminSearchLecturerServlet.STUDENT_SEARCH_RETURN);
        } 
        try {

            if (role.equals("Admin")) {
                if (!searchLecturerById.trim().isEmpty()) {
                    LecturerInfoDAO dao = new LecturerInfoDAO();
                    dao.searchLecturer(searchLecturerById);
                    List<LecturerDTO> result = dao.getListLecturers();
                    request.setAttribute("SEARCH_LECTURER", result);
                } // end search Values has values
            } else if (role.equals("Student")) {
                if (!searchLecturerById.trim().isEmpty()) {
                    LecturerInfoDAO dao = new LecturerInfoDAO();
                    dao.searchLecturer(searchLecturerById);
                    List<LecturerDTO> result = dao.getListLecturers();
                    request.setAttribute("SEARCH_LECTURER", result);
                } // end search Values has values
            }

        } catch (SQLException ex) {
            log("AdminSearchLecturerByIdServlet _ SQL" + ex.getMessage());
        } catch (Exception ex) {
            log("AdminSearchLecturerByIdServlet _ Naming" + ex.getMessage());
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
