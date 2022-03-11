
package enitiy;

import java.io.Serializable;
import java.util.List;




public class StudentDTO implements Serializable{
    private String studentID;
    private String accountID;
    private String fullName;
    private String DOB;
    private String email;
    private String phoneNumber;
    private String majorID;
    private String groupID;
    private boolean status;
    private String role = "Student";
    
    private List<StudentDTO> student_list;

    public StudentDTO() {
    }

    public StudentDTO(String studentID, String accountID, String fullName, String DOB, String email, String phoneNumber, String majorID, String groupID, boolean status) {
        this.studentID = studentID;
        this.accountID = accountID;
        this.fullName = fullName;
        this.DOB = DOB;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.majorID = majorID;
        this.groupID = groupID;
        this.status = status;
    }

    public List<StudentDTO> getStudent_list() {
        return student_list;
    }

    public void setStudent_list(List<StudentDTO> student_list) {
       this.student_list = student_list;
    }
    
    /**
     * @return the studentID
     */
    public String getStudentID() {
        return studentID;
    }

    /**
     * @param studentID the studentID to set
     */
    public void setStudentID(String studentID) {
        this.studentID = studentID;
    }

    /**
     * @return the accountID
     */
    public String getAccountID() {
        return accountID;
    }

    /**
     * @param accountID the accountID to set
     */
    public void setAccountID(String accountID) {
        this.accountID = accountID;
    }

    /**
     * @return the fullName
     */
    public String getFullName() {
        return fullName;
    }

    /**
     * @param fullName the fullName to set
     */
    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    /**
     * @return the DOB
     */
    public String getDOB() {
        return DOB;
    }

    /**
     * @param DOB the DOB to set
     */
    public void setDOB(String DOB) {
        this.DOB = DOB;
    }

    /**
     * @return the email
     */
    public String getEmail() {
        return email;
    }

    /**
     * @param email the email to set
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * @return the phoneNumber
     */
    public String getPhoneNumber() {
        return phoneNumber;
    }

    /**
     * @param phoneNumber the phoneNumber to set
     */
    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    /**
     * @return the majorID
     */
    public String getMajorID() {
        return majorID;
    }

    /**
     * @param majorID the majorID to set
     */
    public void setMajorID(String majorID) {
        this.majorID = majorID;
    }

    /**
     * @return the groupID
     */
    public String getGroupID() {
        return groupID;
    }

    /**
     * @param groupID the groupID to set
     */
    public void setGroupID(String groupID) {
        this.groupID = groupID;
    }


    /**
     * @return the status
     */
    public boolean isStatus() {
        return status;
    }

    /**
     * @param status the status to set
     */
    public void setStatus(boolean status) {
        this.status = status;
    }

    /**
     * @return the role
     */
    public String getRole() {
        return role;
    }

    /**
     * @param role the role to set
     */
    public void setRole(String role) {
        this.role = role;
    }

    @Override
    public String toString() {
        return "StudentDTO{" + "studentID=" + studentID + ", accountID=" + accountID + ", fullName=" + fullName + ", DOB=" + DOB + ", email=" + email + ", phoneNumber=" + phoneNumber + ", majorID=" + majorID + ", groupID=" + groupID + ", status=" + status + ", role=" + role + '}';
    }
    
    
    
}
