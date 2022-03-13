/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import entity.core.StudentDTO;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.Properties;
import javax.naming.NamingException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import manager_dao.impl.ExportStudentFileDAO;
import manager_dao.impl.StudentInfoDAO;
import manager_dao.impl.UploadFileDAO;
import utillsHelper.ApplicationConstant;

/**
 *
 * @author 84399
 */
public class ExportStudentFileServlet extends HttpServlet {

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
        String filename = request.getParameter("file_name");
        String locationFilePath = "C:\\Users\\Public\\Downloads" + filename;
        String url = site_Map.getProperty(ApplicationConstant.ExportStudentFileServlet.RETURN_STUDENT_HOME);

        try {
            HttpSession session = request.getSession(false);
            StudentInfoDAO stu_dao = new StudentInfoDAO();
            List<StudentDTO> student_list = stu_dao.getStudentsInfo();
            ExportStudentFileDAO exportDAO = new ExportStudentFileDAO();
            exportDAO.write_file_student(student_list, locationFilePath);
        } catch (SQLException e) {
            log("ExportStudentFileServlet   SQLException: " + e.getMessage());
        } catch (NamingException e) {
            log("ExportStudentFileServlet   NamingException: " + e.getMessage());
        } catch (FileNotFoundException e) {
            log("ExportStudentFileServlet   FileNotFoundException: " + e.getMessage());
        } catch (IOException e) {
            log("ExportStudentFileServlet   IOException: " + e.getMessage());
        } finally {
            RequestDispatcher rd = request.getRequestDispatcher(url);
            rd.forward(request, response);
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
