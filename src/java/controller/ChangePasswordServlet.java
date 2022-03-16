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
import javax.naming.NamingException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import manager_dao.impl.AccountDAO;
import utillsHelper.ApplicationConstant;

/**
 *
 * @author 84399
 */
@WebServlet(name = "ChangePasswordServlet", urlPatterns = {"/ChangePasswordServlet"})
public class ChangePasswordServlet extends HttpServlet {

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
        String url = site_Map.getProperty(ApplicationConstant.UpdateProfileLectureServlet.PROFILE_PAGE);
        String old_password = request.getParameter("old_password");
        String change_password = request.getParameter("changePassword");
        String accountID = request.getParameter("txtAccountID");

        try {
            HttpSession session = request.getSession(false);
            String username = (String) session.getAttribute("USER_NAME");
            boolean foundErr = false;
            String Errmsg = "";
            AccountDAO accDAO = new AccountDAO();
            String check_old_password = accDAO.checkPassword(old_password);
            
            if (!check_old_password.equals(username) || check_old_password == null) {
                foundErr = true;
                Errmsg = "Wrong old password";
            } else {
                boolean result = accDAO.changePassword(accountID, change_password);
                if (!result) {
                    foundErr = true;
                    Errmsg = "Change Password wrong";
                } else {
                    request.setAttribute("CHANGE_PASSWORD_COMPLETE", "complete");
                }
            }
            if (foundErr) {
                request.setAttribute("CHANGE_PASSWORD_ERRORS", Errmsg);
                System.out.println(Errmsg);
            }
        } catch (NamingException ex) {
            log("LoginAdminServlet   Naming: " + ex.getMessage());
        } catch (SQLException ex) {
            log("LoginAdminServlet   SQL: " + ex.getMessage());
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
