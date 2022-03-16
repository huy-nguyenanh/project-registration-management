/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import entity.core.StudentDTO;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.Properties;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import manager_dao.impl.LecturerInfoDAO;
import manager_dao.impl.StudentInfoDAO;
import utillsHelper.ApplicationConstant;

/**
 *
 * @author 84399
 */
@WebServlet(name = "UpdateNotifyServlet", urlPatterns = {"/UpdateNotifyServlet"})
public class UpdateNotifyServlet extends HttpServlet {

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

        String lecID = request.getParameter("lectureID");
        String groupID = request.getParameter("groupID");
        String notify = request.getParameter("txtNotify");

        String url = site_Map.getProperty(ApplicationConstant.UpdateNotifyServlet.RETURN_PAGE);

        try {
            boolean foundErr = false;
            LecturerInfoDAO lecDAO = new LecturerInfoDAO();
            StudentInfoDAO stuDAO = new StudentInfoDAO();

            boolean lec_update_notify_result = lecDAO.updateNotify(lecID, notify);
            if (!lec_update_notify_result) {
                foundErr = true;
            } else {
                stuDAO.searchStudentByGorupID(groupID);
                List<StudentDTO> list_student_in_group = stuDAO.getListStudents();
                for (StudentDTO student : list_student_in_group) {
                    String studentID = student.getStudentID();
                    boolean stu_update_notify_result = stuDAO.updateNotify(studentID, notify);
                }
            }

        } catch (Exception e) {
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
