package entity.core;

import java.io.Serializable;
import java.util.List;


public class LecturerDTO implements Serializable{
    private String lectureID;
    private String accountID;
    private String fullname;
    private String email;
    private String phoneNumber;
    private String topicID;
    private String groupID;
    private String role = "Lecture";
    private List<LecturerDTO> lecture_list;

    public LecturerDTO() {
    }

    public LecturerDTO(String lectureID, String accountID, String fullname, String email, String phoneNumber, String topicID, String groupID) {
        this.lectureID = lectureID;
        this.accountID = accountID;
        this.fullname = fullname;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.topicID = topicID;
        this.groupID = groupID;
    }

    public List<LecturerDTO> getLecture_list() {
        return lecture_list;
    }

    public void setLecture_list(List<LecturerDTO> lecture_list) {
        this.lecture_list = lecture_list;
    }
    
    

    /**
     * @return the lecturerID
     */
    public String getLectureID() {
        return lectureID;
    }

    /**
     * @param lecturerID the lecturerID to set
     */
    public void setLectureID(String lecturerID) {
        this.lectureID = lecturerID;
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
     * @return the fullname
     */
    public String getFullname() {
        return fullname;
    }

    /**
     * @param fullname the fullname to set
     */
    public void setFullname(String fullname) {
        this.fullname = fullname;
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
     * @return the topicID
     */
    public String getTopicID() {
        return topicID;
    }

    /**
     * @param topicID the topicID to set
     */
    public void setTopicID(String topicID) {
        this.topicID = topicID;
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
}

