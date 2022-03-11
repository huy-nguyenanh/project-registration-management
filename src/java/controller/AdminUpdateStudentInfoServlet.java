/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import enitiy.GroupDTO;
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
 * @author Minh Phuc
 */
@WebServlet(name = "AdminUpdateStudentInfoServlet", urlPatterns = {"/AdminUpdateStudentInfoServlet"})
public class AdminUpdateStudentInfoServlet extends HttpServlet {

    private final String ERROR_PAGE = "errorPage";
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

        String url = site_Map.getProperty(ApplicationConstant.AdminUpdateStudentInfoServlet.STUDENT_INFO);
        try {
            String studentId = request.getParameter("txtID");
            String status = request.getParameter("grStatus");
            String fullname = request.getParameter("txtFullname");
            String groupId = request.getParameter("txtGroupID");
            String searchValue = request.getParameter("lastSearchValue");
            String role = request.getParameter("txtRole");
            boolean result2 = false;
            boolean isOK = true;

//            boolean chkStatus = false;
//            if (status != null) {
//                if (status.equals("true")) {
//                    chkStatus = true;
//                } else {
//                    chkStatus = false;
//                }
//            }
            StudentInfoDAO dao = new StudentInfoDAO();
            GroupDAO groupDao = new GroupDAO();
            ArrayList<GroupDTO> grDtos = groupDao.getStudentsInGroup(groupId);
            System.out.println("group: " + grDtos.size());
            if (grDtos.size() == 0) {
                isOK = false;
                request.setAttribute("ERROR_UPDATE_GROUP", "Group is not exist");
                request.getRequestDispatcher(url).forward(request, response);
            }
            if (isOK) {
                boolean result = dao.updateStudentInfo(studentId, true, groupId);

                boolean check = groupDao.isStudentInGroup(studentId);
                System.out.println("check: " + check);

                String topicId = grDtos.get(0).getTopicId();
                System.out.println("topic: " + topicId);
                if (check) {
                    groupDao.removeStudentFromGroup(studentId);
                    System.out.println("remove");

                    // String topicId = groupDao.getTopicOfGroup(groupId);
//                result2 = groupDao.updateStudentToOtherGroup(groupId, studentId);
                    result2 = groupDao.insertStudentIntoGroupWithTopic(groupId, studentId, fullname, role, topicId);
//                    System.out.println("Update Group");
                } else {
                    result2 = groupDao.adminAddStudentIntoGroup(groupId, studentId, fullname, role, topicId);
                }

                if (result) {
                    url = "adminSearchStudentAction?"
                            + "txtSearchStudent=" + searchValue;
                    response.sendRedirect(url);
                }
            }

        } catch (SQLException ex) {
            log("AdminUpdateStundentInfoServlet _ SQL" + ex.getMessage());
        } catch (NamingException ex) {
            log("AdminUpdateStundentInfoServlet _ Naming" + ex.getMessage());
        }  finally {
            response.sendRedirect(url);
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
