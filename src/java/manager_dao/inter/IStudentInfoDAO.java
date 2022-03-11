package manager_dao.inter;

import enitiy.StudentDTO;
import java.sql.SQLException;
import java.util.List;
import javax.naming.NamingException;

public interface IStudentInfoDAO {

    public boolean checkLoginStudent(String username, String password)
            throws SQLException, NamingException;

    public List<StudentDTO> loadStudentInfo() throws SQLException, NamingException;

    public List<StudentDTO> getStudentsInfo() throws SQLException, NamingException;

    public boolean updateStudentInfo(String studentID, boolean status,
            String groupID) throws SQLException, NamingException;

    public void searchStudent(String searchValue)
            throws SQLException, NamingException;

    public void sortByStatus(String searchValue)
            throws SQLException, NamingException;

    public boolean deleteAccount(String studentID)
            throws SQLException, NamingException;

    public StudentDTO getStudentbyID(String id)
            throws SQLException, NamingException;

    public StudentDTO getStudentbyEmail(String email)
            throws SQLException, NamingException;
}
