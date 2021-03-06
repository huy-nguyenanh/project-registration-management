/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import entity.core.GroupDTO;
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
import manager_dao.impl.GroupDAO;
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
//        String filename = request.getParameter("file_name");
        String locationFilePath;
        String url = site_Map.getProperty(ApplicationConstant.ExportStudentFileServlet.RETURN_STUDENT_HOME);

        try {
            HttpSession session = request.getSession(false);
            StudentInfoDAO stu_dao = new StudentInfoDAO();
            GroupDAO gr_dao = new GroupDAO();
            ExportStudentFileDAO exportDAO = new ExportStudentFileDAO();
//            List<StudentDTO> student_list = stu_dao.getStudentsInfo();
            // export file with student info
            List<GroupDTO> student_in_group_list = gr_dao.loadStudentInfoInGroupToExport();
            if (student_in_group_list != null || !student_in_group_list.isEmpty()) {
                locationFilePath = "C:\\Users\\Public\\Downloads\\student-in-group.xlsx";
                exportDAO.write_file_student(student_in_group_list, locationFilePath);
            }

            // export file with lecture info
            List<GroupDTO> lecture_in_group_list = gr_dao.loadLectureInfoInGroupToExport();
            if (lecture_in_group_list != null || !lecture_in_group_list.isEmpty()) {
                locationFilePath = "C:\\Users\\Public\\Downloads\\lecture-in-group.xlsx";
                exportDAO.write_file_student(lecture_in_group_list, locationFilePath);
            }

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
