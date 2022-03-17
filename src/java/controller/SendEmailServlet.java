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
import javax.mail.MessagingException;
import javax.mail.internet.AddressException;
import javax.naming.NamingException;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import manager_dao.impl.GmailDAO;
import manager_dao.impl.StudentInfoDAO;
import utillsHelper.ApplicationConstant;

/**
 *
 * @author 84399
 */
@WebServlet(name = "SendEmailServlet", urlPatterns = {"/SendEmailServlet"})
public class SendEmailServlet extends HttpServlet {

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

        String notify = request.getParameter("txtNotify");
        String groupId = request.getParameter("txtGroupId");
        HttpSession session = request.getSession(false);
        String url = site_Map.getProperty(ApplicationConstant.SendEmailServlet.RETURN_PAGE);
        try {
            boolean foundErr = false;
            String ErrMsg = "";

            StudentInfoDAO studao = new StudentInfoDAO();
            studao.searchStudentByGorupID(groupId);
            List<StudentDTO> student_list = studao.getListStudents();
            if (student_list == null) {
                foundErr = true;
                ErrMsg = "This group not exist";
            }
            if (notify.isEmpty() || notify.length() == 0) {
                foundErr = true;
                ErrMsg = "No content!";
            } else {
                String lec_email = (String) session.getAttribute("USER_NAME");
                String to_email = "";
                if (student_list.size() == 1) {
                    for (StudentDTO stu : student_list) {
                        to_email = stu.getEmail();
                    }
                } else {
                    for (StudentDTO stu : student_list) {
//                        to_email = stu.getEmail() + ", ";
                        to_email = to_email + stu.getEmail() + ", ";
                    }
                }
                GmailDAO gmail_dao = new GmailDAO();
                if (to_email != null) {
                    gmail_dao.sendText(to_email, notify, lec_email);
                } else {
                    foundErr = true;
                    ErrMsg = "No email to send!";
                }
            }
            if (foundErr) {
                request.setAttribute("SEND_MAIL_ERROR", ErrMsg);
            } else {
                request.setAttribute("SEND_MAIL_COMPLETE", "Complete");
            }
        } catch (AddressException e) {
            log("SendEmailServlet   AddressException: " + e.getMessage());
        } catch (MessagingException e) {
            log("SendEmailServlet   MessagingException: " + e.getMessage());
        } catch (SQLException e) {
            log("SendEmailServlet   SQLException: " + e.getMessage());
        } catch (NamingException e) {
            log("SendEmailServlet   NamingException: " + e.getMessage());
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
