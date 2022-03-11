/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import manager_dao.impl.GroupDAO;
import enitiy.GroupDTO;
import manager_dao.impl.LecturerInfoDAO;
import manager_dao.impl.TopicInfoDAO;
import enitiy.TopicDTO;
import java.util.Properties;
import javax.servlet.ServletContext;
import utillsHelper.ApplicationConstant;

/**
 *
 * @author Admins
 */
@WebServlet(name = "StudentChooseTopicServlet", urlPatterns = {"/StudentChooseTopicServlet"})
public class StudentChooseTopicServlet extends HttpServlet {

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
        String url = site_Map.getProperty(ApplicationConstant.StudentChooseTopicServlet.STUDENT_HOME_PAGE);
        boolean result = true;

        try {
            GroupDAO grdao = new GroupDAO();
            TopicInfoDAO tdao = new TopicInfoDAO();
            LecturerInfoDAO ldao = new LecturerInfoDAO();
            String[] topicList = request.getParameterValues("chkChoose");
            String groupId = request.getParameter("groupId");

            boolean isOk = true;
            if (topicList.length > 1) {
                isOk = false;
                request.setAttribute("ERROR", "Only choose 1 topic");
            } else {
                String topicId = topicList[0];
                TopicDTO ttd = tdao.getTopicById(topicId);
                String test = ttd.getGroupID();
                // Kiểm tra nếu Topic đã có group chọn
                if (!ttd.getGroupID().equals("")) {
                    isOk = false;
                    request.setAttribute("ERROR", "Topic has been choosen by another group.");
                } else {

                    ArrayList<GroupDTO> grDtos = grdao.getStudentsInGroup(groupId);
                    // Kiểm tra group đó có tồn tại hay không
                    if (grDtos.size() == 0) {
                        isOk = false;
                        request.setAttribute("ERROR", "Group is not exist");
                    }
                    // Kiểm tra nếu Group đã có Topic
                    if (grDtos.size() > 0) {
                        if (null == grDtos.get(0).getTopicId() || ("").equals(grDtos.get(0).getTopicId())) {

                        } else {
                            isOk = false;
                            request.setAttribute("ERROR", "Group already had a topic!");
                        }
                    }
                }
            }
            if (isOk) {
                String topicId = topicList[0];
                TopicDTO ttd = tdao.getTopicById(topicId);
                result = grdao.updateTopicToGroup(groupId, topicId)
                        && tdao.updateTopicInfo(topicId, isOk, ttd.getLectureID(), groupId);
            }

        } catch (SQLException ex) {
            log("StudentChooseTopicServlet _ SQL " + ex.getMessage());
        } catch (NamingException ex) {
            log("StudentChooseTopicServlet _ Naming " + ex.getMessage());
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
