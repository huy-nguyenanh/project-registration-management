/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package manager_dao.impl;

import entity.core.LecturerDTO;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.naming.NamingException;
import utillsHelper.DBHelpers;

/**
 *
 * @author Minh Phuc
 */
public class LecturerInfoDAO implements Serializable {

    // Lecturers
    private List<LecturerDTO> listLecturers;

    public List<LecturerDTO> getListLecturers() {
        return listLecturers;
    }

    // Load Lecturer Database
    public List<LecturerDTO> loadLecturerInfo() throws SQLException, NamingException {
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            con = DBHelpers.makeConnection();
            if (con != null) {
                String sql = "Select LecturerID, Fullname, Email, TopicID, GroupID, AccountID, Phone_Number "
                        + "From Lectures";
                stm = con.prepareStatement(sql);
                rs = stm.executeQuery();
                while (rs.next()) {
                    String lectureId = rs.getString("LecturerID");
                    String accountId = rs.getString("AccountID");
                    String name = rs.getString("Fullname");
                    String email = rs.getString("Email");
                    String topicId = rs.getString("TopicID");
                    String groupId = rs.getString("GroupID");
                    String phone = rs.getString("Phone_Number");

                    LecturerDTO lec = new LecturerDTO(lectureId, accountId, name, email, phone, topicId, groupId);
                    if (this.listLecturers == null) {
                        this.listLecturers = new ArrayList<>();
                    }
                    this.listLecturers.add(lec);
                }
                return this.listLecturers;
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

    // Get Lecturer List
    public List<LecturerDTO> getLecturerInfo() throws SQLException, NamingException {
        loadLecturerInfo();
        return getListLecturers();
    }

    // Update Lecture's Info
    public boolean updateLectureInfo(String lectureID, String groupID) 
            throws SQLException, NamingException {

        Connection con = null;
        PreparedStatement stm = null;
        try {
            con = DBHelpers.makeConnection();
            if (con != null) {
                String sql = "Update Lectures "
                        + "Set GroupID = ? "
                        + "Where LecturerID = ? ";
                stm = con.prepareStatement(sql);
                stm.setString(1, groupID);
                stm.setString(2, lectureID);

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

    public boolean deleteAccount(String lectureID)
            throws SQLException, NamingException {
        Connection con = null;
        PreparedStatement stm = null;

        try {
            //1. Make connection
            con = DBHelpers.makeConnection();
            if (con != null) {
                //2. Create SQL String
                String sql = "Delete From Lectures "
                        + "Where LecturerID = ?";
                //3. Create statement object to load SQL String
                // and set values to parameters
                stm = con.prepareStatement(sql);
                stm.setString(1, lectureID);
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

    public LecturerDTO getLecturebyID(String id)
            throws SQLException, NamingException {
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            //1. Connect DB
            con = DBHelpers.makeConnection();
            if (con != null) {
                //2. Create SQL String
                String sql = "Select LecturerID, Fullname, Email, TopicID, GroupID, AccountID, Phone_Number "
                        + "From Lectures "
                        + "Where LecturerID = ? ";

                //3. Create Statement Object
                stm = con.prepareStatement(sql);

                stm.setString(1, id);
                //4. Execute Query
                rs = stm.executeQuery();
                //5. Process Result
                while (rs.next()) {
                    String lectureId = rs.getString("LecturerID");
                    String accountId = rs.getString("AccountID");
                    String name = rs.getString("Fullname");
                    String email = rs.getString("Email");
                    String topicId = rs.getString("TopicID");
                    String groupId = rs.getString("GroupID");
                    String phone = rs.getString("Phone_Number");

                    LecturerDTO lec = new LecturerDTO(lectureId, accountId, name, email, phone, topicId, groupId);

                    return lec;
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

    public LecturerDTO getLecturebyEmail(String email)
            throws SQLException, NamingException {
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            //1. Connect DB
            con = DBHelpers.makeConnection();
            if (con != null) {
                //2. Create SQL String
                String sql = "Select LecturerID, Fullname, Email, TopicID, GroupID, AccountID, Phone_Number "
                        + "From Lectures "
                        + "Where Email = ? ";

                //3. Create Statement Object
                stm = con.prepareStatement(sql);

                stm.setString(1, email);
                //4. Execute Query
                rs = stm.executeQuery();
                //5. Process Result
                while (rs.next()) {
                    String lectureId = rs.getString("LecturerID");
                    String accountId = rs.getString("AccountID");
                    String name = rs.getString("Fullname");
                    String email_ = rs.getString("Email");
                    String topicId = rs.getString("TopicID");
                    String groupId = rs.getString("GroupID");
                    String phone = rs.getString("Phone_Number");

                    LecturerDTO lec = new LecturerDTO(lectureId, accountId, name, email_, phone, topicId, groupId);

                    return lec;
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

    public void searchLecturer(String searchValue)
            throws SQLException, NamingException {
        Connection conn = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            conn = DBHelpers.makeConnection();

            if (conn != null) {
                String sql = "Select LecturerID, AccountID, Fullname, Email, Phone_Number, TopicID, GroupID "
                        + " From Lectures "
                        + " Where LecturerID Like ? ";
                stm = conn.prepareStatement(sql);
                stm.setString(1, "%" + searchValue + "%");

                rs = stm.executeQuery();
                while (rs.next()) {
                    String lecturerID = rs.getString("LecturerID");
                    String accountID = rs.getString("AccountID");
                    String fullname = rs.getString("Fullname");
                    String email = rs.getString("Email");
                    String phonenumber = rs.getString("Phone_Number");
                    String topicID = rs.getString("TopicID");
                    String groupId = rs.getString("GroupID");

                    LecturerDTO lec = new LecturerDTO(lecturerID, accountID,
                            fullname, email, phonenumber, topicID, groupId);
                    if (this.listLecturers == null) {
                        this.listLecturers = new ArrayList<>();
                    }
                    this.listLecturers.add(lec);
                }
            }
        } catch (Exception e) {

        } finally {
            if (rs != null) {
                rs.close();
            }
            if (stm != null) {
                stm.close();
            }
            if (conn != null) {
                conn.close();
            }
        }
    }

    public boolean updateLecturetoGroup(String lectureID, String groupID) throws SQLException, NamingException {

        Connection con = null;
        PreparedStatement stm = null;
        try {
            con = DBHelpers.makeConnection();
            if (con != null) {
                String sql = "Update Lectures "
                        + "Set GroupID = ? "
                        + "Where LecturerID = ? ";
                stm = con.prepareStatement(sql);
                stm.setString(1, groupID);
                stm.setString(2, lectureID);

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

    public boolean updateNotify(String lectureID, String notify) throws NamingException, SQLException {
        Connection con = null;
        PreparedStatement stm = null;

        try {
            con = DBHelpers.makeConnection();
            if (con != null) {
                String sql = "Update Lectures "
                        + "Set Notify = ? "
                        + "Where LecturerID = ? ";
                stm = con.prepareStatement(sql);
                stm.setString(0, notify);
                stm.setString(1, lectureID);

                int affectRow = stm.executeUpdate();
                if (affectRow > 0) {
                    return true;
                }
            }

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
}
