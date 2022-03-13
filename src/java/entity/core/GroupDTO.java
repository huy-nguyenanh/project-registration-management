/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity.core;

import java.io.Serializable;

/**
 *
 * @author Minh Phuc
 */
public class GroupDTO implements Serializable {

    private String groupId;
    private String memberId;
    private String fullname;
    private String topicId;
    private String role;
    private boolean status;

    public GroupDTO() {
    }

    public GroupDTO(String topicId) {
        this.topicId = topicId;
    }

    public GroupDTO(String groupId, String memberId, String fullname, String topicId, String role, boolean status) {
        this.groupId = groupId;
        this.memberId = memberId;
        this.fullname = fullname;
        this.topicId = topicId;
        this.role = role;
        this.status = status;
    }

    public GroupDTO(String groupId, String memberId, String fullname, String topicId, String role) {
        this.groupId = groupId;
        this.memberId = memberId;
        this.fullname = fullname;
        this.topicId = topicId;
        this.role = role;
    }

    /**
     * @return the groupId
     */
    public String getGroupId() {
        return groupId;
    }

    /**
     * @param groupId the groupId to set
     */
    public void setGroupId(String groupId) {
        this.groupId = groupId;
    }

    /**
     * @return the memberId
     */
    public String getMemberId() {
        return memberId;
    }

    /**
     * @param memberId the memberId to set
     */
    public void setMemberId(String memberId) {
        this.memberId = memberId;
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
     * @return the topicId
     */
    public String getTopicId() {
        return topicId;
    }

    /**
     * @param topicId the topicId to set
     */
    public void setTopicId(String topicId) {
        this.topicId = topicId;
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

}
