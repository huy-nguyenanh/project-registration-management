/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import entity.core.GroupDTO;
import entity.core.StudentDTO;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;
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
import manager_dao.impl.GroupDAO;
import manager_dao.impl.StudentInfoDAO;
import utillsHelper.ApplicationConstant;

/**
 *
 * @author 84399
 */
@WebServlet(name = "ShowListMemberInGroupServlet", urlPatterns = {"/ShowListMemberInGroupServlet"})
public class ShowListMemberInGroupServlet extends HttpServlet {

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
        String stuId = request.getParameter("txtId");

        String url = site_Map.getProperty(ApplicationConstant.AdminSearchStudentServlet.RETURN_STUDENT_PAGE);
        try {
            StudentInfoDAO stu_dao = new StudentInfoDAO();
            StudentDTO student = stu_dao.getStudentbyID(stuId);
            String searchStudentByGroupId = student.getGroupID();
            boolean foundErr = false;
            String Errmsg = "";
            if (!searchStudentByGroupId.trim().isEmpty()) {
//                    StudentInfoDAO dao = new StudentInfoDAO();
//                    dao.searchStudentByGorupID(searchStudentByGroupId);
//                    List<StudentDTO> result = dao.getListStudents();
                GroupDAO grDao = new GroupDAO();
                ArrayList<GroupDTO> list_member = grDao.getStudentsInGroup(searchStudentByGroupId);

                request.setAttribute("LIST_MEMBER", list_member);
            } // end search Values has values
            else {
                foundErr = true;
                Errmsg = "Student not in group";
            }
            if (foundErr) {
                request.setAttribute("ERROR_LIST_MEMBER", Errmsg);
            }
        } catch (SQLException ex) {
            log("AdminSearchStudentByIdServlet _ SQL" + ex.getMessage());
        } catch (NamingException ex) {
            log("AdminSearchStudentByIdServlet _ Naming" + ex.getMessage());
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
