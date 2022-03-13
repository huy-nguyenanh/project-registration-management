/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import entity.core.GroupDTO;
import entity.core.LecturerDTO;
import entity.core.TopicDTO;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Properties;
import javax.naming.NamingException;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import manager_dao.impl.GroupDAO;
import manager_dao.impl.LecturerInfoDAO;
import manager_dao.impl.TopicInfoDAO;
import utillsHelper.ApplicationConstant;

/**
 *
 * @author Minh Phuc
 */
@WebServlet(name = "AdminUpdateTopicInfoServlet", urlPatterns = {"/AdminUpdateTopicInfoServlet"})
public class AdminUpdateTopicInfoServlet extends HttpServlet {

    private final String TOPIC_INFO = "topicInfoPage";
    private final String ERROR_PAGE = "errorPage.html";

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
            throws ServletException, IOException{
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();

        ServletContext context = this.getServletContext();
        Properties site_Map = (Properties) context.getAttribute("SITE_MAP");

        String url = site_Map.getProperty(ApplicationConstant.AdminUpdateTopicInfoServlet.TOPIC_INFO);
        try {
            String topicId = request.getParameter("txtTopicId");

            java.sql.Date deadLineValue = null;
            String deadLine = request.getParameter("txtDeadline");

            boolean status = false;
            String topicStatus = request.getParameter("topicStatus");
            String lectureId = request.getParameter("txtLectureId");
            String groupId = request.getParameter("txtGroupId");

            if (topicStatus != null) {
                if (topicStatus.equals("true")) {
                    status = true;
                } else {
                    status = false;
                }
            }
            boolean isOk = true;
//            deadLineValue = java.sql.Date.valueOf(deadLine);
            LecturerInfoDAO lecDao = new LecturerInfoDAO();
            TopicInfoDAO dao = new TopicInfoDAO();
            GroupDAO grDao = new GroupDAO();

            ArrayList<GroupDTO> grDtos = grDao.getStudentsInGroup(groupId);
            if (grDtos.size() == 0) {
                isOk = false;
                request.setAttribute("ERROR_UPDATE_TOPIC", "Group is not exist");
                request.getRequestDispatcher(url).forward(request, response);
            }
            if (grDtos.size() > 0) {
                if (null == grDtos.get(0).getTopicId() || ("").equals(grDtos.get(0).getTopicId())) {

                } else {
                    isOk = false;
                    request.setAttribute("ERROR_UPDATE_TOPIC", "Group already had a topic!");
                    request.getRequestDispatcher(url).forward(request, response);
                }
            }
            if (isOk) {
                boolean result = dao.updateTopicInfo(topicId, status, lectureId, groupId)
                        && grDao.updateTopicToGroup(groupId, topicId);

                TopicDTO dto = dao.getTopicById(topicId);
//            System.out.println("dto: " + dto);

                String lectureValue = dto.getLectureID();
                String[] listLectrueId = lectureValue.split(", ");

//            String part1 = parts[0]; // 004 String part2 = parts[1]; // 034556
//            String part2 = parts[1];
                int subString = listLectrueId.length;
                for (int i = 0; i < subString; i++) {
                    LecturerDTO lecDTO = lecDao.getLecturebyID(listLectrueId[i]);
                    if (!lecDTO.getGroupID().equals("")) {
                        if (!lecDTO.getGroupID().equals(groupId)) {
                            // có groupID
                            grDao.insertLectureIntoGroup(groupId, listLectrueId[i],
                                    lecDTO.getFullname(), lecDTO.getRole(), topicId);
                            lecDTO.setGroupID(lecDTO.getGroupID() + ", " + groupId);
                            lecDao.updateLecturetoGroup(listLectrueId[i], lecDTO.getGroupID());
                            //  đã có ,
                        }

//                    }
                    } else {
                        // chưa có gr
                        lecDao.updateLecturetoGroup(listLectrueId[i], groupId);
                        grDao.insertLectureIntoGroup(groupId, listLectrueId[i],
                                lecDTO.getFullname(), lecDTO.getRole(), topicId);
                        // đã có gr(1 giá trị)

                    }

                }
                if (result) {
                    response.sendRedirect(url);
                }
            }

        } catch (SQLException ex) {
            log("AdminUpdateTopicInfoServlet _ SQL" + ex.getMessage());
        } catch (NamingException ex) {
            log("AdminUpdateTopicInfoServlet _ Naming" + ex.getMessage());
        }    }

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
