package manager_dao.impl;

import manager_dao.inter.IUploadFileDAO;
import enitiy.LecturerDTO;
import enitiy.StudentDTO;
import enitiy.TopicDTO;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import javax.naming.NamingException;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import utillsHelper.DBHelpers;

public class UploadFileDAO implements Serializable, IUploadFileDAO {

    public List<StudentDTO> student_list_in_dao;

    public List<LecturerDTO> lecturer_list_in_dao;

    public List<TopicDTO> topic_list;

    public List<StudentDTO> getStudent_list_in_dao() {
        return student_list_in_dao;
    }
    
    public List<LecturerDTO> getLectuer_list_in_dao() {
        return lecturer_list_in_dao;
    }

    public List<TopicDTO> getTopic_List() {
        return topic_list;
    }
    

    @Override
    public int readFile_Student(String filename) throws FileNotFoundException, IOException {
        FileInputStream excelFile = new FileInputStream(new File(filename));
        Workbook workBook = null;
        if (filename.endsWith("xlsx")) {
            workBook = new XSSFWorkbook(excelFile);
        } else if (filename.endsWith("xls")) {
            workBook = new HSSFWorkbook(excelFile);
        }
        Sheet datatypeSheet = workBook.getSheetAt(0);
        DataFormatter fmt = new DataFormatter();
        Iterator<Row> iterator = datatypeSheet.iterator();
        Row firstRow = iterator.next();
        Cell firstCell = firstRow.getCell(0);

        try {
            int count = 0;
            while (iterator.hasNext()) {
                StudentDTO student = new StudentDTO();
                Row currentRow = iterator.next();

                String studentID = currentRow.getCell(0).getStringCellValue();
                if (!studentID.startsWith("SE")) {
                    return 2; // Student ID not correct with format : SExxxxxxx
                }
                student.setStudentID(studentID);

                String accountID = currentRow.getCell(1).getStringCellValue();
                student.setAccountID(accountID);

                String fullName = currentRow.getCell(2).getStringCellValue();
                student.setFullName(fullName);

                String DOB = fmt.formatCellValue(currentRow.getCell(3));
                student.setDOB(DOB);

                String email = currentRow.getCell(4).getStringCellValue();
                if (!email.endsWith("fpt.edu.vn")) {
                    return 4; // email not correct with fpt mail
                }
                student.setEmail(email);

                String phoneNumber = fmt.formatCellValue(currentRow.getCell(5));
                student.setPhoneNumber(phoneNumber);

                String majorID = currentRow.getCell(6).getStringCellValue();
                if (!majorID.equalsIgnoreCase("SE")) {
                    return 6; // major ID not correct!!
                }
                student.setMajorID(majorID);

                String groupID = "";
                student.setGroupID(groupID);
                boolean status = true;
                student.setStatus(status);
                
                if (this.student_list_in_dao == null) {
                    this.student_list_in_dao = new ArrayList<>();
                }
                this.student_list_in_dao.add(student);
                count++;
            }
            if (count == 0) {
                return 0;
            } else {
                StudentDTO student = new StudentDTO();
                if(student.getStudent_list() != null){
                    student.getStudent_list().clear();
                }
                student.setStudent_list(student_list_in_dao);
                return 1;
            }
        } finally {
            if (workBook != null) {
                workBook.close();
            }
            if (excelFile != null) {
                excelFile.close();
            }
        }
    }

    @Override
    public boolean pushStudentListToDB(List<StudentDTO> student_list)
            throws SQLException, NamingException, ParseException {

        Connection con = null;
        PreparedStatement stm = null;

        try {
            con = DBHelpers.makeConnection();
            if (con != null) {
                String sql = "Insert into Students "
                        + "values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
                stm = con.prepareStatement(sql);
                for (StudentDTO student : student_list) {
                    stm.setString(1, student.getStudentID());
                    stm.setString(2, student.getAccountID());
                    stm.setString(3, student.getFullName());
                    SimpleDateFormat format = new SimpleDateFormat("yyyy/MM/dd");
                    Date DOB = format.parse(student.getDOB());
                    java.sql.Date DOB_sql_insert = new java.sql.Date(DOB.getTime());
                    stm.setDate(4, DOB_sql_insert);
                    stm.setString(5, student.getEmail());
                    stm.setString(6, student.getPhoneNumber());
                    stm.setString(7, student.getMajorID());
                    stm.setBoolean(8, student.isStatus());
                    stm.setString(9, student.getGroupID());
                    stm.setString(10, student.getRole());
                    int numberaffectInsert = stm.executeUpdate();
                    if (numberaffectInsert == 0) {
                        return false;
                    }
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
        return true;
    }

    @Override
    public boolean createAccountStudent(List<StudentDTO> student_List)
            throws SQLException, NamingException {

        Connection con = null;
        PreparedStatement stm = null;
        try {
            con = DBHelpers.makeConnection();
            if (con != null) {
                String sql = "Insert into Accounts "
                        + "values (?, ?, ?, ?)";
                stm = con.prepareStatement(sql);
                for (StudentDTO student : student_List) {
                    stm.setString(1, student.getAccountID());
                    stm.setString(2, student.getEmail());
                    stm.setString(3, student.getPhoneNumber());
                    stm.setString(4, student.getRole());
                    int numberAffectInsert = stm.executeUpdate();
                    if (numberAffectInsert == 0) {
                        return false;
                    }

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
        return true;
    }

    @Override
    public int readFile_Lecturer(String filename)
            throws FileNotFoundException, IOException {
        FileInputStream excelFile = new FileInputStream(new File(filename));
        Workbook workBook = new XSSFWorkbook(excelFile);
        Sheet datatypeSheet = workBook.getSheetAt(0);
        DataFormatter fmt = new DataFormatter();
        Iterator<Row> iterator = datatypeSheet.iterator();
        Row firstRow = iterator.next();
        Cell firstCell = firstRow.getCell(0);

        try {
            int count = 0;

            while (iterator.hasNext()) {
                LecturerDTO lecture = new LecturerDTO();
                Row currentRow = iterator.next();

                String lecturerID = currentRow.getCell(0).getStringCellValue();

                lecture.setLectureID(lecturerID);

                String accountID = currentRow.getCell(1).getStringCellValue();
                lecture.setAccountID(accountID);

                String fullName = currentRow.getCell(2).getStringCellValue();
                lecture.setFullname(fullName);

                String email = currentRow.getCell(3).getStringCellValue();
                if (!email.endsWith("fpt.edu.vn")) {
                    return 2; // email not correct with fpt mail
                }
                lecture.setEmail(email);

                String phoneNumber = fmt.formatCellValue(currentRow.getCell(4));
                lecture.setPhoneNumber(phoneNumber);

                String topicID = currentRow.getCell(5).getStringCellValue();
                lecture.setTopicID(topicID);

                String groupID = "";
                lecture.setGroupID(groupID);

                if (this.lecturer_list_in_dao == null) {
                    lecturer_list_in_dao = new ArrayList<>();
                }
                lecturer_list_in_dao.add(lecture);
                count++;

            }
            if (count == 0) {
                return 0;
            }
        } finally {
            if (workBook != null) {
                workBook.close();
            }
            if (excelFile != null) {
                excelFile.close();
            }
        }

        return 1;
    }

    @Override
    public boolean pushLecturerListToDB(List<LecturerDTO> lecturer_list)
            throws SQLException, NamingException, ParseException {

        Connection con = null;
        PreparedStatement stm = null;

        try {
            con = DBHelpers.makeConnection();
            if (con != null) {
                String sql = "Insert into Lectures "
                        + "values (?, ?, ?, ?, ?, ?, ?, ?)";
                stm = con.prepareStatement(sql);

                for (LecturerDTO lecturer : lecturer_list) {
                    stm.setString(1, lecturer.getLectureID());
                    stm.setString(2, lecturer.getAccountID());
                    stm.setString(3, lecturer.getFullname());
                    stm.setString(4, lecturer.getEmail());
                    stm.setString(5, lecturer.getPhoneNumber());
                    stm.setString(6, lecturer.getTopicID());
                    stm.setString(7, lecturer.getGroupID());
                    stm.setString(8, lecturer.getRole());
                    int numberaffectInsert = stm.executeUpdate();
                    if (numberaffectInsert == 0) {
                        return false;
                    }
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
        return true;
    }

    @Override
    public boolean createAccountLecturer(List<LecturerDTO> lecturer_list)
            throws SQLException, NamingException {
        Connection con = null;
        PreparedStatement stm = null;
        try {
            con = DBHelpers.makeConnection();
            if (con != null) {
                String sql = "Insert into Accounts "
                        + "values (?, ?, ?, ?)";
                stm = con.prepareStatement(sql);
                for (LecturerDTO lecturer : lecturer_list) {
                    stm.setString(1, lecturer.getAccountID());
                    stm.setString(2, lecturer.getEmail());
                    stm.setString(3, lecturer.getPhoneNumber());
                    stm.setString(4, lecturer.getRole());
                    int numberAffectInsert = stm.executeUpdate();
                    if (numberAffectInsert == 0) {
                        return false;
                    }
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
        return true;
    }

    @Override
    public int readFile_Topic(String filename)
            throws FileNotFoundException, IOException {

        FileInputStream excelFile = new FileInputStream(new File(filename));
        Workbook workBook = new XSSFWorkbook(excelFile);
        Sheet datatypeSheet = workBook.getSheetAt(0);
        DataFormatter fmt = new DataFormatter();
        Iterator<Row> iterator = datatypeSheet.iterator();
        Row firstRow = iterator.next();
        Cell firstCell = firstRow.getCell(0);
        try {
            int count = 0;

            while (iterator.hasNext()) {
                TopicDTO topic = new TopicDTO();
                Row currentRow = iterator.next();

                String topicID = currentRow.getCell(0).getStringCellValue();
                topic.setTopicID(topicID);

                String topicName = currentRow.getCell(1).getStringCellValue();
                topic.setTopicName(topicName);

                String deadline = fmt.formatCellValue(currentRow.getCell(2));
                topic.setDeadline(deadline);

                String category = currentRow.getCell(3).getStringCellValue();
                topic.setCategory(category);

                String majorID = currentRow.getCell(4).getStringCellValue();
                topic.setMajorID(majorID);

                String status_onExcel = fmt.formatCellValue(currentRow.getCell(5));
                boolean status = false;
                if (status_onExcel.equals("1")) {
                    status = true;
                }
                topic.setStatus(status);
                String lectureID = currentRow.getCell(6).getStringCellValue();
                topic.setLectureID(lectureID);

                if (this.topic_list == null) {
                    this.topic_list = new ArrayList<>();
                }
                topic_list.add(topic);
                count++;
            }
            if (count == 0) {
                return 0;
            }
        } finally {
            if (workBook != null) {
                workBook.close();
            }
            if (excelFile != null) {
                excelFile.close();
            }
        }
        return 1;
    }

    @Override
    public boolean pushTopicListToDB(List<TopicDTO> toplic_list)
            throws SQLException, NamingException, ParseException {
        Connection con = null;
        PreparedStatement stm = null;

        try {
            con = DBHelpers.makeConnection();
            if (con != null) {
                String sql = "Insert into Topics "
                        + "values (?, ?, ?, ?, ?, ?, ?, ?)";
                stm = con.prepareStatement(sql);
                for (TopicDTO topic : toplic_list) {
                    stm.setString(1, topic.getTopicID());
                    stm.setString(2, topic.getTopicName());
                    SimpleDateFormat format = new SimpleDateFormat("yyyy/MM/dd");
                    Date deadline_format = format.parse(topic.getDeadline());
                    java.sql.Date deadline_sql_insert = new java.sql.Date(deadline_format.getTime());
                    stm.setDate(3, deadline_sql_insert);
                    stm.setString(4, topic.getCategory());
                    stm.setString(5, topic.getMajorID());
                    stm.setBoolean(6, topic.isStatus());
                    stm.setString(7, topic.getLectureID());
                    stm.setString(8, topic.getGroupID());

                    int numberaffectInsert = stm.executeUpdate();
                    if (numberaffectInsert == 0) {
                        return false;
                    }
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
        return true;
    }
    
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

                    if (this.student_list_in_dao == null) {
                        this.student_list_in_dao = new ArrayList<>();
                    }
                    this.student_list_in_dao.add(std);
                }
                StudentDTO student = new StudentDTO();
                if (student.getStudent_list() != null) {
                    student.getStudent_list().clear();
                }
                student.setStudent_list(student_list_in_dao);
                return this.student_list_in_dao;
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
    
    public List<StudentDTO> getStudentsInfo() throws SQLException, NamingException {
        loadStudentInfo();

        return getStudent_list_in_dao();
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
                    if (this.lecturer_list_in_dao == null) {
                        this.lecturer_list_in_dao = new ArrayList<>();
                    }
                    this.lecturer_list_in_dao.add(lec);
                }
                return this.lecturer_list_in_dao;
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
        return getLectuer_list_in_dao();
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
                    if (this.topic_list == null) {
                        this.topic_list = new ArrayList<>();
                    }
                    this.topic_list.add(topic);
                }
                return this.topic_list;
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
        return getTopic_List();
    }
}
