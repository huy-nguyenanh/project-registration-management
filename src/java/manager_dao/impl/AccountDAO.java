/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package manager_dao.impl;

import entity.core.AccountDTO;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.naming.NamingException;
import utillsHelper.DBHelpers;

/**
 *
 * @author 84399
 */
public class AccountDAO implements Serializable {

    public AccountDTO getAccountbyID(String accountID)
            throws SQLException, NamingException {
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            //1. Connect DB
            con = DBHelpers.makeConnection();
            if (con != null) {
                //2. Create SQL String
                String sql = "Select AccountID, Username, Password, Role "
                        + "From Accounts "
                        + "Where AccountID = ? ";

                //3. Create Statement Object
                stm = con.prepareStatement(sql);

                stm.setString(1, accountID);
                //4. Execute Query
                rs = stm.executeQuery();
                //5. Process Result
                while (rs.next()) {
                    String username = rs.getString("Username");
                    String role = rs.getString("Role");
                    accountID = rs.getString("AccountID");
                    String password = rs.getString("Password");
                    AccountDTO account = new AccountDTO(accountID, username, password, role);
                    return account;
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
    
    public String checkPassword(String password) throws NamingException, SQLException {

        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            con = DBHelpers.makeConnection();
            if (con != null) {
                String sql = "Select Username, Password "
                        + "from Accounts "
                        + "Where Password = ? ";
                stm = con.prepareStatement(sql);
                stm.setString(1, password);

                rs = stm.executeQuery();
                if (rs.next()) {
                    String username = rs.getString("Username");
                    return username;
                }
            }
        } finally {
            if (con != null) {
                con.close();
            }
        }

        return "Not Match";
    }

//    public boolean changePassword(String username, String password) throws NamingException, SQLException {
//
//        Connection con = null;
//        PreparedStatement stm = null;
//        ResultSet rs = null;
//
//        try {
//            con = DBHelpers.makeConnection();
//            if (con != null) {
//                String sql = "Update Account "
//                        + "Set Password = ? "
//                        + "Where Username = ? ";
//                stm = con.prepareStatement(sql);
//                stm.setString(1, password);
//                stm.setString(2, username);
//
//                int effectRows = stm.executeUpdate();
//                if (effectRows > 0) {
//                    return true;
//                }
//            }
//        } finally {
//            if (con != null) {
//                con.close();
//            }
//        }
//
//        return false;
//    }
    
    public boolean changePassword(String accountID, String password)
            throws NamingException, SQLException {
        Connection con = null;
        PreparedStatement stm = null;

        try {
            con = DBHelpers.makeConnection();
            if (con != null) {
                //2. Create SQL String
                String sql = "Update Accounts "
                        + "Set Password = ? "
                        + "Where AccountID = ? ";

                //3. Create Statement Object
                stm = con.prepareStatement(sql);

                stm.setString(1, password);
                stm.setString(2, accountID);
                //4. Execute Query
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
}
