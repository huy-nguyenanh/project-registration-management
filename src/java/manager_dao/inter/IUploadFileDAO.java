
package manager_dao.inter;

import entity.core.LecturerDTO;
import entity.core.StudentDTO;
import entity.core.TopicDTO;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.List;
import javax.naming.NamingException;


public interface IUploadFileDAO {
    
    public int readFile_Student(String filename) throws FileNotFoundException, IOException;
    public boolean pushStudentListToDB(List<StudentDTO> student_list) throws SQLException, NamingException, ParseException;
    public boolean createAccountStudent(List<StudentDTO> student_list) throws SQLException, NamingException;
    
    public int readFile_Lecturer(String filename) throws FileNotFoundException, IOException;
    public boolean pushLecturerListToDB(List<LecturerDTO> lecturer_list) throws SQLException, NamingException, ParseException;
    public boolean createAccountLecturer(List<LecturerDTO> lecturer_list) throws SQLException, NamingException;
    
    public int readFile_Topic(String filename) throws FileNotFoundException, IOException;
    public boolean pushTopicListToDB(List<TopicDTO> toplic_list) throws SQLException, NamingException, ParseException;
}
