/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package manager_dao.impl;

import enitiy.TopicDTO;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.Date;
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
public class TopicInfoDAO implements Serializable {

    // Topic
    private List<TopicDTO> listTopics;

    public List<TopicDTO> getListTopics() {
        return listTopics;
    }

    // Load Topic
    public List<TopicDTO> loadTopic() throws NamingException, SQLException {
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            con = DBHelpers.makeConnection();
            if (con != null) {
                String sql = "Select TopicID, Topic_Name, DeadLine, Category, MajorID, Status, LectureID, GroupID "
                        + "From Topics";
                stm = con.prepareStatement(sql);
                rs = stm.executeQuery();
                while (rs.next()) {
                    String id = rs.getString("TopicID");
                    String name = rs.getString("Topic_Name");
                    DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
                    String deadline = dateFormat.format(rs.getDate("DeadLine"));
                    String category = rs.getString("Category");
                    String majorId = rs.getString("MajorID");
//                    Date deadline = rs.getDate("DeadLine");

                    boolean status = rs.getBoolean("Status");
                    String lecturerId = rs.getString("LectureID");
                    String groupID = rs.getString("GroupID");

                    TopicDTO topic = new TopicDTO(id, name, deadline, category, majorId, status, lecturerId, groupID);
                    if (this.listTopics == null) {
                        this.listTopics = new ArrayList<>();
                    }
                    this.listTopics.add(topic);
                }
                return this.listTopics;
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

    // Get Topic List
    public List<TopicDTO> getTopicInfo() throws NamingException, SQLException {
        loadTopic();
        return getListTopics();
    }

    // Update Topic's Info
    public boolean updateTopicInfo(String topicId, Date dealine, boolean status,
            String groupID) throws SQLException, NamingException {

        Connection con = null;
        PreparedStatement stm = null;
        try {
            con = DBHelpers.makeConnection();
            if (con != null) {
                String sql = "Update Topics "
                        + "Set DeadLine = ?, Status = ?, LectureID = ? "
                        + "Where TopicID = ? ";
                stm = con.prepareStatement(sql);
                stm.setDate(1, dealine);
                stm.setBoolean(2, status);
                stm.setString(3, groupID);
                stm.setString(4, topicId);

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

    public boolean deleteTopic(String topicID)
            throws SQLException, NamingException {
        Connection con = null;
        PreparedStatement stm = null;

        try {
            //1. Make connection
            con = DBHelpers.makeConnection();
            if (con != null) {
                //2. Create SQL String
                String sql = "Delete From Topics "
                        + "Where TopicID = ?";
                //3. Create statement object to load SQL String
                // and set values to parameters
                stm = con.prepareStatement(sql);
                stm.setString(1, topicID);
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

    public TopicDTO getTopicById(String topicId) throws SQLException, NamingException {

        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            con = DBHelpers.makeConnection();
            if (con != null) {
                String sql = "Select TopicID, Topic_Name, DeadLine, Category, MajorID, Status, LectureID, GroupID "
                        + "From Topics "
                        + "Where TopicID = ? ";
                stm = con.prepareStatement(sql);
                stm.setString(1, topicId);
                rs = stm.executeQuery();
                if (rs.next()) {
                    topicId = rs.getString("TopicID");
                    String topicName = rs.getString("Topic_Name");
                    DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
                    String deadline = dateFormat.format(rs.getDate("DeadLine"));
                    String category = rs.getString("Category");
                    String majorId = rs.getString("MajorID");
                    boolean status = rs.getBoolean("Status");
                    String lecturerId = rs.getString("LectureID");
                    String groupId = rs.getString("GroupID");
                    if (groupId == null) {
                        groupId = "";
                    }
                    TopicDTO dto
                            = new TopicDTO(topicId, topicName, deadline, category, majorId, status, lecturerId, groupId);
                    return dto;
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

        return null;
    }

    public boolean updateTopicInfo(String topicId, boolean status,
            String lectureId, String groupId) throws SQLException, NamingException {

        Connection con = null;
        PreparedStatement stm = null;
        try {
            con = DBHelpers.makeConnection();
            if (con != null) {
                String sql = "Update Topics "
                        + "Set Status = ?, LectureID = ?, GroupID = ? "
                        + "Where TopicID = ?";
                stm = con.prepareStatement(sql);
//                stm.setDate(1, dealine);
                stm.setBoolean(1, status);
                stm.setString(2, lectureId);
                stm.setString(3, groupId);
                stm.setString(4, topicId);

                int effectRows = stm.executeUpdate();
                if (effectRows > 0) {
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

    public void searchTopic(String searchValue)
            throws SQLException, NamingException {
        Connection conn = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            conn = DBHelpers.makeConnection();
            if (conn != null) {
                String sql = "Select TopicID, Topic_Name, DeadLine, Category, MajorID, Status, LectureID, GroupID "
                        + " From Topics "
                        + " Where MajorID Like ? ";
                stm = conn.prepareStatement(sql);
                stm.setString(1, "%" + searchValue + "%");

                rs = stm.executeQuery();
                while (rs.next()) {
                    String TopicID = rs.getString("TopicID");
                    String Topic_Name = rs.getString("Topic_Name");
                    DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
                    String DeadLine = dateFormat.format(rs.getDate("DeadLine"));
                    String Category = rs.getString("Category");
                    String MajorID = rs.getString("MajorID");
                    boolean Status = rs.getBoolean("Status");
                    String LectureID = rs.getString("LectureID");
                    String GroupID = rs.getString("GroupID");

                    TopicDTO tp = new TopicDTO(TopicID, Topic_Name, DeadLine, Category, MajorID, Status, LectureID, GroupID);
                    if (this.listTopics == null) {
                        this.listTopics = new ArrayList<>();
                    }
                    this.listTopics.add(tp);
                }
            }
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
}
