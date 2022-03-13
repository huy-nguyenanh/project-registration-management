/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package manager_dao.impl;

import manager_dao.inter.IStudentInfoDAO;
import entity.core.StudentDTO;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import javax.naming.NamingException;
import utillsHelper.DBHelpers;

/**
 *
 * @author Minh Phuc
 */
public class StudentInfoDAO implements Serializable, IStudentInfoDAO {

    // Students
    private List<StudentDTO> listStudents;

    public List<StudentDTO> getListStudents() {
        return listStudents;
    }

    // check login
    @Override
    public boolean checkLoginStudent(String username, String password)
            throws SQLException, NamingException {
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        //Lệnh SQL
        String sql = "Select Username "
                + "From Accounts "
                + "Where Username = ? And Password = ?";
        try {
            //connect DB
            con = DBHelpers.makeConnection();
            if (con != null) {
                stm = con.prepareStatement(sql);
                stm.setString(1, username);
                stm.setString(2, password);
                rs = stm.executeQuery();
                if (rs.next()) {
                    return true;
                }
            }
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (stm != null) {
                stm.close();
            }
            if (con != null) {
                con.close();
            }
        }
        return false;
    }

//     Load Student Database
    @Override
    public List<StudentDTO> loadStudentInfo() throws SQLException, NamingException {
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            con = DBHelpers.makeConnection();
            if (con != null) {
                String sql = "Select StudentID, Fullname, MajorID, Email, Date_Of_Birth, Status, GroupID, AccountID, Phone_Number "
                        + "From Students";
                stm = con.prepareStatement(sql);
                rs = stm.executeQuery();
                while (rs.next()) {
                    String studentId = rs.getString("StudentID");
                    String fullname = rs.getString("Fullname");
                    String majorId = rs.getString("MajorID");
                    String email = rs.getString("Email");
                    String accountId = rs.getString("AccountID");
                    String phonenumber = rs.getString("Phone_Number");
                    DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
                    String DOB = dateFormat.format(rs.getDate("Date_Of_Birth"));
//                    Date dateOfBirth = rs.getDate("Date_Of_Birth");
                    boolean status = rs.getBoolean("Status");
                    String groupId = rs.getString("GroupID");

                    StudentDTO std = new StudentDTO(studentId, accountId, fullname,
                            DOB, email, phonenumber, majorId, groupId, status);

                    if (this.listStudents == null) {
                        this.listStudents = new ArrayList<>();
                    }
                    this.listStudents.add(std);
                }
                StudentDTO student = new StudentDTO();
                if (student.getStudent_list() != null) {
                    student.getStudent_list().clear();
                }
                student.setStudent_list(listStudents);
                return this.listStudents;
            }

        } finally {
            if (rs != null) {
                rs.close();
            }
            if (stm != null) {
                stm.close();
            }
            if (con != null) {
                con.close();
            }
        }
        return null;
    }

    // Get Student List
    @Override
    public List<StudentDTO> getStudentsInfo() throws SQLException, NamingException {
        loadStudentInfo();

        return getListStudents();
    }

    // Update Student's Info
    @Override
    public boolean updateStudentInfo(String studentID, boolean status,
            String groupID) throws SQLException, NamingException {

        Connection con = null;
        PreparedStatement stm = null;
        try {
            con = DBHelpers.makeConnection();
            if (con != null) {
                String sql = "Update Students "
                        + "Set Status = ?, GroupID = ? "
                        + "Where StudentID = ? ";
                stm = con.prepareStatement(sql);
                stm.setBoolean(1, status);
                stm.setString(2, groupID);
                stm.setString(3, studentID);

                int effectRows = stm.executeUpdate();
                if (effectRows > 0) {
                    return true;
                }
            }
        } finally {
            if (con != null) {
                con.close();
            }
        }

        return false;
    }

    // Search Student by ID
    @Override
    public void searchStudent(String searchValue)
            throws SQLException, NamingException {
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            //1. Connect DB
            con = DBHelpers.makeConnection();
            if (con != null) {
                //2. Create SQL String
                String sql = "Select StudentID, Fullname, Email, Date_Of_Birth, MajorID, Status, GroupID, AccountID, Phone_Number "
                        + "From Students "
                        + "Where StudentID Like ? ";

                //3. Create Statement Object
                stm = con.prepareStatement(sql);
                stm.setString(1, "%" + searchValue + "%");
                //4. Execute Query
                rs = stm.executeQuery();
                //5. Process Result
                while (rs.next()) {
                    String studentId = rs.getString("StudentID");
                    String fullname = rs.getString("Fullname");
                    String majorId = rs.getString("MajorID");
                    String email = rs.getString("Email");
                    String accountId = rs.getString("AccountID");
                    String phonenumber = rs.getString("Phone_Number");
                    DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
                    String DOB = dateFormat.format(rs.getDate("Date_Of_Birth"));
//                    Date dateOfBirth = rs.getDate("Date_Of_Birth");
                    boolean status = rs.getBoolean("Status");
                    String groupId = rs.getString("GroupID");

                    StudentDTO std = new StudentDTO(studentId, accountId, fullname,
                            DOB, email, phonenumber, majorId, groupId, status);
                    if (this.listStudents == null) {
                        this.listStudents = new ArrayList<>();
                    } // end if account List is not existed
                    // Account List is Existed
                    this.listStudents.add(std);
                } // end while rs has pointed to EOF
            } // end connection is 

        } finally {
            if (rs != null) {
                rs.close();
            }
            if (stm != null) {
                stm.close();
            }
            if (con != null) {
                con.close();
            }
        }
    }

    // Sort By Status
    @Override
    public void sortByStatus(String searchValue)
            throws SQLException, NamingException {
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            //1. Connect DB
            con = DBHelpers.makeConnection();
            if (con != null) {
                //2. Create SQL String
                String sql = "Select StudentID, Fullname, Email, Date_Of_Birth, MajorID, Status, GroupID, AccountID, Phone_Number "
                        + "From Students "
                        + "Where Status = ?";
                //3. Create Statement Object
                stm = con.prepareStatement(sql);
                stm.setString(1, searchValue);
                //4. Execute Query
                rs = stm.executeQuery();
                //5. Process Result
                while (rs.next()) {
                    String studentId = rs.getString("StudentID");
                    String fullname = rs.getString("Fullname");
                    String majorId = rs.getString("MajorID");
                    String email = rs.getString("Email");
                    String accountId = rs.getString("AccountID");
                    String phonenumber = rs.getString("Phone_Number");
                    DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
                    String DOB = dateFormat.format(rs.getDate("Date_Of_Birth"));
//                    Date dateOfBirth = rs.getDate("Date_Of_Birth");
                    boolean status = rs.getBoolean("Status");
                    String groupId = rs.getString("GroupID");

                    StudentDTO std = new StudentDTO(studentId, accountId, fullname,
                            DOB, email, phonenumber, majorId, groupId, status);
                    if (this.listStudents == null) {
                        this.listStudents = new ArrayList<>();
                    } // end if account List is not existed
                    // Account List is Existed
                    this.listStudents.add(std);
                } // end while rs has pointed to EOF
            } // end connection is 

        } finally {
            if (rs != null) {
                rs.close();
            }
            if (stm != null) {
                stm.close();
            }
            if (con != null) {
                con.close();
            }
        }
    }

    @Override
    public boolean deleteAccount(String studentID)
            throws SQLException, NamingException {
        Connection con = null;
        PreparedStatement stm = null;

        try {
            //1. Make connection
            con = DBHelpers.makeConnection();
            if (con != null) {
                //2. Create SQL String
                String sql = "Delete From Students "
                        + "Where StudentID = ?";
                //3. Create statement object to load SQL String
                // and set values to parameters
                stm = con.prepareStatement(sql);
                stm.setString(1, studentID);
                //4. excute query
                int affectedRow = stm.executeUpdate();
                //5. process result
                if (affectedRow > 0) {
                    return true;
                }
            } //end if con is opened
        } finally {
            if (stm != null) {
                stm.close();
            }
            if (con != null) {
                con.close();
            }
        }
        return false;
    }

    @Override
    public StudentDTO getStudentbyID(String id)
            throws SQLException, NamingException {
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            //1. Connect DB
            con = DBHelpers.makeConnection();
            if (con != null) {
                //2. Create SQL String
                String sql = "Select StudentID, Fullname, Email, Date_Of_Birth, MajorID, Status, GroupID, AccountID, Phone_Number "
                        + "From Students "
                        + "Where StudentID = ? ";

                //3. Create Statement Object
                stm = con.prepareStatement(sql);

                stm.setString(1, id);
                //4. Execute Query
                rs = stm.executeQuery();
                //5. Process Result
                while (rs.next()) {
                    String studentId = rs.getString("StudentID");
                    String fullname = rs.getString("Fullname");
                    String majorId = rs.getString("MajorID");
                    String email = rs.getString("Email");
                    String accountId = rs.getString("AccountID");
                    String phonenumber = rs.getString("Phone_Number");
                    DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
                    String DOB = dateFormat.format(rs.getDate("Date_Of_Birth"));
//                    Date dateOfBirth = rs.getDate("Date_Of_Birth");
                    boolean status = rs.getBoolean("Status");
                    String groupId = rs.getString("GroupID");

                    StudentDTO std = new StudentDTO(studentId, accountId, fullname,
                            DOB, email, phonenumber, majorId, groupId, status);
                    return std;
                } // end while rs has pointed to EOF
            } // end connection is 

        } finally {
            if (rs != null) {
                rs.close();
            }
            if (stm != null) {
                stm.close();
            }
            if (con != null) {
                con.close();
            }
        }
        return null;
    }

    @Override
    public StudentDTO getStudentbyEmail(String email)
            throws SQLException, NamingException {
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            //1. Connect DB
            con = DBHelpers.makeConnection();
            if (con != null) {
                //2. Create SQL String
                String sql = "Select StudentID, Fullname, Email, Date_Of_Birth, MajorID, Status, GroupID, AccountID, Phone_Number "
                        + "From Students "
                        + "Where Email = ? ";

                //3. Create Statement Object
                stm = con.prepareStatement(sql);

                stm.setString(1, email);
                //4. Execute Query
                rs = stm.executeQuery();
                //5. Process Result
                while (rs.next()) {
                    String studentId = rs.getString("StudentID");
                    String fullname = rs.getString("Fullname");
                    String majorId = rs.getString("MajorID");
                    String email_ = rs.getString("Email");
                    String accountId = rs.getString("AccountID");
                    String phonenumber = rs.getString("Phone_Number");
                    DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
                    String DOB = dateFormat.format(rs.getDate("Date_Of_Birth"));
//                    Date dateOfBirth = rs.getDate("Date_Of_Birth");
                    boolean status = rs.getBoolean("Status");
                    String groupId = rs.getString("GroupID");

                    StudentDTO std = new StudentDTO(studentId, accountId, fullname,
                            DOB, email_, phonenumber, majorId, groupId, status);
                    return std;
                } // end while rs has pointed to EOF
            } // end connection is 

        } finally {
            if (rs != null) {
                rs.close();
            }
            if (stm != null) {
                stm.close();
            }
            if (con != null) {
                con.close();
            }
        }
        return null;
    }

    public void searchStudentByGorupID(String groupID)
            throws SQLException, NamingException {
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            //1. Connect DB
            con = DBHelpers.makeConnection();
            if (con != null) {
                //2. Create SQL String
                String sql = "Select StudentID, Fullname, Email, Date_Of_Birth, MajorID, Status, GroupID, AccountID, Phone_Number "
                        + "From Students "
                        + "Where GroupID Like ? ";

                //3. Create Statement Object
                stm = con.prepareStatement(sql);
                stm.setString(1, "%" + groupID + "%");
                //4. Execute Query
                rs = stm.executeQuery();
                //5. Process Result
                while (rs.next()) {
                    String studentId = rs.getString("StudentID");
                    String fullname = rs.getString("Fullname");
                    String majorId = rs.getString("MajorID");
                    String email = rs.getString("Email");
                    String accountId = rs.getString("AccountID");
                    String phonenumber = rs.getString("Phone_Number");
                    DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
                    String DOB = dateFormat.format(rs.getDate("Date_Of_Birth"));
//                    Date dateOfBirth = rs.getDate("Date_Of_Birth");
                    boolean status = rs.getBoolean("Status");
                    groupID = rs.getString("GroupID");

                    StudentDTO std = new StudentDTO(studentId, accountId, fullname,
                            DOB, email, phonenumber, majorId, groupID, status);
                    if (this.listStudents == null) {
                        this.listStudents = new ArrayList<>();
                    } // end if account List is not existed
                    // Account List is Existed
                    this.listStudents.add(std);
                } // end while rs has pointed to EOF
            } // end connection is 

        } finally {
            if (rs != null) {
                rs.close();
            }
            if (stm != null) {
                stm.close();
            }
            if (con != null) {
                con.close();
            }
        }
    }
}
