/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import enitiy.LecturerDTO;
import erros_entity.UploadFileError;
import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;
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
import manager_dao.impl.UploadFileDAO;
import utillsHelper.ApplicationConstant;

/**
 *
 * @author 84399
 */
public class UploadLectureFileServlet extends HttpServlet {

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
        HttpSession session = request.getSession(false);
        
        ServletContext context = this.getServletContext();
        Properties site_Map = (Properties) context.getAttribute("SITE_MAP");
        String fileName = request.getParameter("file_name");
        String url = site_Map.getProperty(ApplicationConstant.UploadFileServlet.UPLOAD_LECTURE_FILE_RETURN);
        try {
            boolean foundErr = false;
            UploadFileError error = new UploadFileError();
//            int lastIndexOfFilleName = locationFileName.lastIndexOf(".");
//            String typeFile = locationFileName.substring(lastIndexOfFilleName + 1);
//            if (!typeFile.equals("xlsx") && !typeFile.equals("xls")) {
//                foundErr = true;
//                error.setUploadWrongFileName("Please upload excel file!");
//            }
            if(!fileName.endsWith("xlsx") && !fileName.endsWith("xls")){
                foundErr = true;
                error.setUploadWrongFileName("Please upload excel file!");
            }
            String locationFileName = "C:\\" + fileName;
            UploadFileDAO dao = new UploadFileDAO();
            
            int uploadFile_result = dao.readFile_Lecturer(locationFileName);
            List<LecturerDTO> lecture_list = dao.getLectuer_list_in_dao();
             
            if(uploadFile_result == 2){
            foundErr = true;
            error.setEmail_Not_Correct_In_Excel("Email not correct with FPT mail!!!");
            } else if (uploadFile_result == 0){
                foundErr = true;
                error.setUploadFile_False("Upload File False!!!");
            } else {
                System.out.println("Upload lecture file complete");
                boolean result_insert_into_DB = dao.pushLecturerListToDB(lecture_list);
                boolean result_create_account_in_DB = dao.createAccountLecturer(lecture_list);
                if(!result_insert_into_DB){
                    foundErr = true;
                    error.setInsertToDB_False("Insert to database false!!!");
                } else {
                    System.out.println("Insert to database complete!!!");
                }
                if(!result_create_account_in_DB){
                    foundErr = true;
                    error.setCreate_acount_in_DB("Create account for lecture false!!!");
                } else {
                    System.out.println("Create account complete!!!");
                }
            }
            if(foundErr){
                request.setAttribute("UPLOAD_LECTURE_FILE_ERROR", error);
                return;
            } else{
                session.setAttribute("LECTURE_LIST", lecture_list);
            }
            
            
        } catch (SQLException e) {
            log("UploadFileServlet  SQLException: " + e.getMessage());
        } catch (NamingException e) {
            log("UploadFileServlet NamingException: " + e.getMessage());
        } catch (ParseException ex) {
            log("UploadFileServlet ParseException: " + ex.getMessage());
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
