/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package enitiy;

import java.io.Serializable;

/**
 *
 * @author 84399
 */
public class AdminDTO implements Serializable{
    private String username;
    private String passwords;
    private String adminID;
    private String role = "Admin";

    public AdminDTO() {
    }

    public AdminDTO(String username, String passwords, String adminID) {
        this.username = username;
        this.passwords = passwords;
        this.adminID = adminID;
    }

    /**
     * @return the username
     */
    public String getUsername() {
        return username;
    }

    /**
     * @param username the username to set
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * @return the passwords
     */
    public String getPasswords() {
        return passwords;
    }

    /**
     * @param passwords the passwords to set
     */
    public void setPasswords(String passwords) {
        this.passwords = passwords;
    }

    /**
     * @return the adminID
     */
    public String getAdminID() {
        return adminID;
    }

    /**
     * @param adminID the adminID to set
     */
    public void setAdminID(String adminID) {
        this.adminID = adminID;
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
