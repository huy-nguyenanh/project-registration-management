/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package manager_dao.impl;

import entity.core.GroupDTO;
import entity.core.LecturerDTO;
import java.io.Serializable;
import java.sql.Connection;
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
public class GroupDAO implements Serializable {

    private List<GroupDTO> groupList;

    public List<GroupDTO> getMembersByLecID() {
        return groupList;
    }

    // admin
    public boolean adminAddStudentIntoGroup(String groupId, String studentId, String fullname, String role, String topicID)
            throws SQLException, NamingException {

        Connection con = null;
        PreparedStatement stm = null;
        try {
            con = DBHelpers.makeConnection();
            if (con != null) {
                String sql = "Insert Into Groups(GroupID, MemberId, Fullname, Role, TopicID) "
                        + "Values(?, ?, ?, ?, ?)";
                stm = con.prepareStatement(sql);
                stm.setString(1, groupId);
                stm.setString(2, studentId);
                stm.setString(3, fullname);
                stm.setString(4, role);
                stm.setString(5, topicID);

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

    // Student
    public boolean insertStudentIntoGroup(String groupId, String studentId, String fullname, String role)
            throws SQLException, NamingException {

        Connection con = null;
        PreparedStatement stm = null;
        try {
            con = DBHelpers.makeConnection();
            if (con != null) {
                String sql = "Insert Into Groups(GroupID, MemberId, Fullname, Role) "
                        + "Values(?, ?, ?, ?)";
                stm = con.prepareStatement(sql);
                stm.setString(1, groupId);
                stm.setString(2, studentId);
                stm.setString(3, fullname);
                stm.setString(4, role);

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

    public boolean insertStudentIntoGroupWithTopic(String groupId, String studentId, String fullname, String role, String topicID)
            throws SQLException, NamingException {

        Connection con = null;
        PreparedStatement stm = null;
        try {
            con = DBHelpers.makeConnection();
            if (con != null) {
                String sql = "Insert Into Groups(GroupID, MemberId, Fullname, Role, TopicID) "
                        + "Values(?, ?, ?, ?, ?)";
                stm = con.prepareStatement(sql);
                stm.setString(1, groupId);
                stm.setString(2, studentId);
                stm.setString(3, fullname);
                stm.setString(4, role);
                stm.setString(5, topicID);

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

    public boolean isStudentInGroup(String memberId) throws SQLException, NamingException {

        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            con = DBHelpers.makeConnection();
            if (con != null) {
                String sql = "Select GroupID "
                        + "From Groups "
                        + "Where MemberId = ? ";
                stm = con.prepareStatement(sql);
                stm.setString(1, memberId);

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

    public ArrayList<GroupDTO> getTopicOfGroup(String groupId) throws SQLException, NamingException {
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        ArrayList<GroupDTO> groupIdList = new ArrayList<GroupDTO>();

        try {
            con = DBHelpers.makeConnection();
            if (con != null) {
                String sql = "SELECT TopicID "
                        + "FROM Groups "
                        + "Where GroupID = ?";
                stm = con.prepareStatement(sql);
                stm.setString(1, groupId);

            }

            rs = stm.executeQuery();

            if (rs.next()) {
                String TopicID = rs.getString("TopicID");
                groupIdList.add((new GroupDTO(TopicID)));

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
        return groupIdList;

    }

    public boolean removeStudentFromGroup(String studentId) throws NamingException, SQLException {
        Connection con = null;
        PreparedStatement stm = null;

        try {
            con = DBHelpers.makeConnection();
            if (con != null) {
                String sql = "Delete "
                        + "From Groups "
                        + "Where MemberId = ? ";
                stm = con.prepareStatement(sql);
                stm.setString(1, studentId);

                int effecRows = stm.executeUpdate();
                if (effecRows > 0) {
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

    public boolean updateStudentToOtherGroup(String groupId, String memberId) throws SQLException, NamingException {

        Connection con = null;
        PreparedStatement stm = null;
        try {
            con = DBHelpers.makeConnection();
            if (con != null) {
                String sql = "Update Groups "
                        + "Set GroupID = ? "
                        + "Where MemberId = ? ";
                stm = con.prepareStatement(sql);
                stm.setString(1, groupId);
                stm.setString(2, memberId);

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

    // Lecture
    public boolean insertLectureIntoGroup(String groupId, String lectureId, String fullname, String role, String topicId) throws SQLException, NamingException {

        Connection con = null;
        PreparedStatement stm = null;
        try {
            con = DBHelpers.makeConnection();
            if (con != null) {
                String sql = "Insert Into Groups(GroupID, MemberId, Fullname, Role, TopicID) "
                        + "Values(?, ?, ?, ?, ?)";
                stm = con.prepareStatement(sql);
                stm.setString(1, groupId);
                stm.setString(2, lectureId);
                stm.setString(3, fullname);
                stm.setString(4, role);
                stm.setString(5, topicId);

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

    public boolean getLectureFromGroup(String memberId) throws SQLException, NamingException {

        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            con = DBHelpers.makeConnection();
            if (con != null) {
                String sql = "Select GroupID "
                        + "From Groups "
                        + "Where MemberId = ? ";
                stm = con.prepareStatement(sql);
                stm.setString(1, memberId);

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

    public boolean updateLectureToOtherGroup(String groupId, String memberId) throws SQLException, NamingException {

        Connection con = null;
        PreparedStatement stm = null;
        try {
            con = DBHelpers.makeConnection();
            if (con != null) {
                String sql = "Update Groups "
                        + "Set GroupID = ? "
                        + "Where MemberId = ? ";
                stm = con.prepareStatement(sql);
                stm.setString(1, groupId);
                stm.setString(2, memberId);

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

    // Topic
    public boolean updateTopicToGroup(String groupId, String topicId)
            throws SQLException, NamingException {

        Connection con = null;
        PreparedStatement stm = null;
        try {
            con = DBHelpers.makeConnection();
            if (con != null) {
                String sql = "Update Groups "
                        + "Set TopicID = ? "
                        + "Where GroupID = ?";
                stm = con.prepareStatement(sql);
                stm.setString(1, topicId);
                stm.setString(2, groupId);

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

    public ArrayList<GroupDTO> getStudentsInGroup(String groupId) throws SQLException, NamingException {
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        ArrayList<GroupDTO> memberList = new ArrayList<GroupDTO>();

        try {
            con = DBHelpers.makeConnection();
            if (con != null) {
                String sql = "SELECT GroupID, MemberId, Fullname, Role, TopicID "
                        + "FROM Groups "
                        + "Where GroupID = ? and Role like 'Student'";
                stm = con.prepareStatement(sql);
                stm.setString(1, groupId);

            }

            rs = stm.executeQuery();

            while (rs.next()) {
                //                String groupId = rs.getString("GroupID");
                String memberId = rs.getString("MemberId");
                String fullname = rs.getString("Fullname");
                String role = rs.getString("Role");
                String TopicID = rs.getString("TopicID");
                memberList.add(new GroupDTO(groupId, memberId, fullname, TopicID, role));

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
            return memberList;
        }
    }

    public List<GroupDTO> getGroupMembersByGroupID(String groupID) throws SQLException {
        if (!groupID.isEmpty()) {
            if (groupID.contains(",")) {
                String[] list_GrId = groupID.split(", ", 5);
                for (String grID : list_GrId) {
                    searchMembersByGroupID(grID);
                }
                return getMembersByLecID();
            } else {
                searchMembersByGroupID(groupID);
                return getMembersByLecID();
            }

        }
        return null;
    }

    public void searchMembersByGroupID(String grID) throws SQLException {
        Connection conn = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            conn = DBHelpers.makeConnection();
            if (conn != null) {
                String sql = "Select GroupID, MemberId, Fullname, Role, TopicID, Status "
                        + "From Groups "
                        + "Where GroupID like ? and Role <> 'Lecture'";
                stm = conn.prepareStatement(sql);
                stm.setString(1, grID);
                rs = stm.executeQuery();
                while (rs.next()) {
                    String groupId = rs.getString("GroupId");
                    String memberId = rs.getString("MemberId");
                    String fullname = rs.getString("Fullname");
                    String role = rs.getString("Role");
                    String topicID = rs.getString("TopicID");
                    boolean status = rs.getBoolean("Status");

                    GroupDTO gr = new GroupDTO(groupId, memberId, fullname, topicID, role, status);
                    if (this.groupList == null) {
                        this.groupList = new ArrayList<>();
                    }
                    this.groupList.add(gr);
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
    
    public List<GroupDTO> loadGroupInfo() throws SQLException, NamingException {
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            con = DBHelpers.makeConnection();
            if (con != null) {
                String sql = "Select GroupID, MemberId, Fullname, Role, TopicID "
                        + "From Groups";
                stm = con.prepareStatement(sql);
                rs = stm.executeQuery();
                while (rs.next()) {
                    String groupID = rs.getString("GroupID");
                    String memberId = rs.getString("MemberId");
                    String name = rs.getString("Fullname");
                    String role = rs.getString("Role");
                    String topicId = rs.getString("TopicID");

                    GroupDTO group = new GroupDTO(groupID, memberId, name, topicId, role);
                    
                    if (this.groupList == null) {
                        this.groupList = new ArrayList<>();
                    }
                    this.groupList.add(group);
                }
                return this.groupList;
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
}
