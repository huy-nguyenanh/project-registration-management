package enitiy;

import java.io.Serializable;


public class TopicDTO implements Serializable{
    private String topicID;
    private String topicName;
    private String deadline;
    private String category;
    private String majorID;
    private boolean status;
    private String lectureID;
    private String groupID;

    public TopicDTO() {
    }

    public TopicDTO(String topicID, String topicName, String deadline, String category, String majorID, boolean status, String lectureID, String groupID) {
        this.topicID = topicID;
        this.topicName = topicName;
        this.deadline = deadline;
        this.category = category;
        this.majorID = majorID;
        this.status = status;
        this.lectureID = lectureID;
        this.groupID = groupID;
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
     * @return the topicName
     */
    public String getTopicName() {
        return topicName;
    }

    /**
     * @param topicName the topicName to set
     */
    public void setTopicName(String topicName) {
        this.topicName = topicName;
    }

    /**
     * @return the deadline
     */
    public String getDeadline() {
        return deadline;
    }

    /**
     * @param deadline the deadline to set
     */
    public void setDeadline(String deadline) {
        this.deadline = deadline;
    }

    /**
     * @return the category
     */
    public String getCategory() {
        return category;
    }

    /**
     * @param category the category to set
     */
    public void setCategory(String category) {
        this.category = category;
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
     * @return the lectureID
     */
    public String getLectureID() {
        return lectureID;
    }

    /**
     * @param lectureID the lectureID to set
     */
    public void setLectureID(String lectureID) {
        this.lectureID = lectureID;
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
    
    
}
