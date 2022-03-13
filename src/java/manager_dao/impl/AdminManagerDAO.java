package manager_dao.impl;

import entity.core.AdminDTO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.naming.NamingException;
import utillsHelper.DBHelpers;

public class AdminManagerDAO {

    /* 
    Login 
     */
    public boolean checkLoginAdmin(String username, String password)
            throws SQLException, NamingException {
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        //Lệnh SQL
        String sql = "Select Username "
                + "From Admins "
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

    /* 
    get username to show in home_page
     */
    public AdminDTO getAdminByUsername(String username) throws NamingException, SQLException {

        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        
        
        String sql = "Select AccountID, Password "
                + "from Accounts "
                + "where Username like ?";
        try {
            con = DBHelpers.makeConnection();
            if (con != null) {
                stm = con.prepareStatement(sql);
                stm.setString(1, username);
                rs = stm.executeQuery();
                if (rs.next()) {
                    String adminID = rs.getString("AccountID");
                    String password = rs.getString("Password");
                    AdminDTO admin = new AdminDTO(username, password, adminID);
                    return admin;
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
        return null;
    }

    /* 
    Check role
     */
}
