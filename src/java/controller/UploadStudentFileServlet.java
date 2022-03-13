/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import entity.core.StudentDTO;
import entity.errors.UploadFileError;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.text.ParseException;
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
import javax.servlet.http.HttpSession;
import manager_dao.impl.UploadFileDAO;
import utillsHelper.ApplicationConstant;

/**
 *
 * @author 84399
 */
@WebServlet(name = "UploadFileServlet", urlPatterns = {"/UploadFileServlet"})
public class UploadStudentFileServlet extends HttpServlet {

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
        String fileName = request.getParameter("file_name");
        String url = site_Map.getProperty(ApplicationConstant.UploadFileServlet.UPLOAD_STUDENT_FILE_RETURN);
        HttpSession session = request.getSession(false);
        
        try {
            boolean foundErr = false;
            UploadFileError error = new UploadFileError();
            if (!fileName.endsWith("xlsx") && !fileName.endsWith("xls")) {
                foundErr = true;
                error.setUploadWrongFileName("Please upload excel file!");
            }
//            int lastIndexOfFilleName = locationFileName.lastIndexOf(".");
//            String typeFile = locationFileName.substring(lastIndexOfFilleName + 1);
//            if (!typeFile.equals("xlsx") && !typeFile.equals("xls")) {
//                foundErr = true;
//                error.setUploadWrongFileName("Please upload excel file!");
//            }
            String locationFileName = "C:\\" + fileName;
            UploadFileDAO dao = new UploadFileDAO();
            int uploadFile_result = dao.readFile_Student(locationFileName);
//            if (!uploadFile_result) {
//                foundErr = true;
//                error.setUploadFile_False("Upload file false!");
//            }
            switch (uploadFile_result) {
                case 0: // upload file false!!!
                    foundErr = true;
                    error.setUploadFile_False("Upload file false!!");
                    break;
                case 2: // Student ID not correct with format : SExxxxxxx
                    foundErr = true;
                    error.setStudentID_Not_Correct_In_Excel("Some student ID in excel file are"
                            + " not correct with format : SExxxxxx (x is digit)!");
                    break;
                case 4: // email not correct with fpt mail
                    foundErr = true;
                    error.setEmail_Not_Correct_In_Excel("Some email in excel file "
                            + "are not fpt email!!!");
                    break;
                case 6: // major ID not correct!!
                    foundErr = true;
                    error.setMajorID_Not_Correct_In_Excel("Some major ID in excel "
                            + "file are not correct !!!");
                    break;
                case 1: // upload complete
                    System.out.println("Upload student file complete");
                    List<StudentDTO> student_List = dao.getStudent_list_in_dao();
                    for (StudentDTO studentDTO : student_List) {
                        System.out.println(studentDTO);
                    }
                    boolean result_Insert_To_DB = dao.pushStudentListToDB(student_List);
                    boolean result_Create_Account_In_DB = dao.createAccountStudent(student_List);
                    if (!result_Insert_To_DB) {
                        foundErr = true;
                        error.setInsertToDB_False("Insert to database false!!");
                    }
                    if (!result_Create_Account_In_DB) {
                        foundErr = true;
                        error.setCreate_acount_in_DB("Create account for student false!!!");
                    }
                    if (foundErr) {
                        request.setAttribute("UPLOAD_FILE_ERROR", error);
                        return;
                    }
                    session.setAttribute("STUDENT_LIST", student_List);
                    break;
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
            out.close();
//            response.sendRedirect(url);
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
