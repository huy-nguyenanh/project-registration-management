/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.Properties;
import java.util.Random;
import javax.naming.NamingException;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import manager_dao.impl.GroupDAO;
import manager_dao.impl.StudentInfoDAO;
import entity.core.StudentDTO;
import java.util.ArrayList;
import java.util.Arrays;
import javax.servlet.http.HttpSession;
import utillsHelper.ApplicationConstant;

/**
 *
 * @author Minh Phuc
 */
@WebServlet(name = "CreateGroupServlet", urlPatterns = {"/CreateGroupServlet"})
public class CreateGroupServlet extends HttpServlet {

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
        String url = site_Map.getProperty(ApplicationConstant.AdminCreateGroupServlet.CREATE_GROUP_RETURN);
        boolean result = true;
        Random rand = new Random();

// Obtain a number between [0 - 999].
        int n = rand.nextInt(1000);

        try {
            HttpSession session = request.getSession(false);
            GroupDAO grdao = new GroupDAO();
            StudentInfoDAO dao = new StudentInfoDAO();
            String[] studentList = request.getParameterValues("chkCreate");
            String searchValue = request.getParameter("lastSearchValue");
            ArrayList<String> stuID = new ArrayList<>(Arrays.asList(studentList));

            boolean isOk = true;
            String role = (String) session.getAttribute("ROLE");

            if (role.equals("Admin")) {
                if (studentList.length < 3) {
                    isOk = false;
                    request.setAttribute("ERROR_CREATE_GROUP", "Min student in one group is 3");
                    request.getRequestDispatcher(url).forward(request, response);
                }
                if (studentList.length > 5) {
                    isOk = false;
                    request.setAttribute("ERROR_CREATE_GROUP", "Max student in one group is 5");
                } else {
                    for (int i = 0; i < studentList.length; i++) {
                        String studentID = studentList[i];
                        StudentDTO std = dao.getStudentbyID(studentID);

                        if (null == std.getGroupID() || !std.getGroupID().equals("")) {
                            isOk = false;
                            request.setAttribute("ERROR_CREATE_GROUP", "Students already in group");
                            System.out.println(isOk);
                        }
                    }
                }
            }
            if (role.equals("Student")) {
                String id = ((StudentDTO) (request.getSession().getAttribute("ACCOUNT"))).getStudentID();
                if (stuID.size() > 4 || stuID.size() < 2) {
                    isOk = false;
                    request.setAttribute("ERROR_CREATE_GROUP", "Student in one group is 3-5 people");
                } else {
                    stuID.add(id);
                    for (int i = 0; i < stuID.size(); i++) {
                        String studentID = stuID.get(i);
                        StudentDTO std = dao.getStudentbyID(studentID);

                        if (null == std.getGroupID() || !std.getGroupID().equals("")) {
                            isOk = false;
                            request.setAttribute("ERROR_CREATE_GROUP", "Students already in group");

                        }
                    }
                }
            }

            if (isOk) {

                for (int i = 0; i < stuID.size(); i++) {
                    String studentID = stuID.get(i);
                    StudentDTO std = dao.getStudentbyID(studentID);

                    result = grdao.insertStudentIntoGroup(n + "", studentID, std.getFullName(), "Student");
                    dao.updateStudentInfo(studentID, true, n + "");
                }
            }
        } catch (SQLException ex) {
            log("StudentCreateGroupServlet _ SQL " + ex.getMessage());
        } catch (NamingException ex) {
            log("StudentCreateGroupServlet _ Naming " + ex.getMessage());
        } finally {
            request.getRequestDispatcher(url).forward(request, response);
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
