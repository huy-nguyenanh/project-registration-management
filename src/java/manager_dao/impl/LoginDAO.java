
package manager_dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.naming.NamingException;
import utillsHelper.DBHelpers;

public class LoginDAO {
    
    public String checkLogin(String username, String password)
            throws SQLException, NamingException {
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        //Lệnh SQL
        String sql = "Select Username, Role "
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
                    String role = rs.getString("Role");
                    return role;
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
}
