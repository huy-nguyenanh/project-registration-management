/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package errors_entity;

/**
 *
 * @author 84399
 */
public class UploadFileError {
    
    private String uploadFile_False;
    private String uploadWrongFileName;//excel file only
    private String insertToDB_False;
    private String studentID_Not_Correct_In_Excel;
    private String email_Not_Correct_In_Excel;
    private String majorID_Not_Correct_In_Excel;
    private String create_acount_in_DB;

    public UploadFileError() {
    }

    /**
     * @return the UploadFile_False
     */
    public String getUploadFile_False() {
        return uploadFile_False;
    }

    /**
     * @param UploadFile_False the UploadFile_False to set
     */
    public void setUploadFile_False(String UploadFile_False) {
        this.uploadFile_False = UploadFile_False;
    }

    /**
     * @return the UploadWrongFileName
     */
    public String getUploadWrongFileName() {
        return uploadWrongFileName;
    }

    /**
     * @param UploadWrongFileName the UploadWrongFileName to set
     */
    public void setUploadWrongFileName(String UploadWrongFileName) {
        this.uploadWrongFileName = UploadWrongFileName;
    }

    /**
     * @return the InsertToDB_False
     */
    public String getInsertToDB_False() {
        return insertToDB_False;
    }

    /**
     * @param InsertToDB_False the InsertToDB_False to set
     */
    public void setInsertToDB_False(String InsertToDB_False) {
        this.insertToDB_False = InsertToDB_False;
    }

    /**
     * @return the StudentID_Not_Correct_In_Excel
     */
    public String getStudentID_Not_Correct_In_Excel() {
        return studentID_Not_Correct_In_Excel;
    }

    /**
     * @param StudentID_Not_Correct_In_Excel the StudentID_Not_Correct_In_Excel to set
     */
    public void setStudentID_Not_Correct_In_Excel(String StudentID_Not_Correct_In_Excel) {
        this.studentID_Not_Correct_In_Excel = StudentID_Not_Correct_In_Excel;
    }

    /**
     * @return the Email_Not_Correct_In_Excel
     */
    public String getEmail_Not_Correct_In_Excel() {
        return email_Not_Correct_In_Excel;
    }

    /**
     * @param Email_Not_Correct_In_Excel the Email_Not_Correct_In_Excel to set
     */
    public void setEmail_Not_Correct_In_Excel(String Email_Not_Correct_In_Excel) {
        this.email_Not_Correct_In_Excel = Email_Not_Correct_In_Excel;
    }

    /**
     * @return the MajorID_Not_Correct_In_Excel
     */
    public String getMajorID_Not_Correct_In_Excel() {
        return majorID_Not_Correct_In_Excel;
    }

    /**
     * @param MajorID_Not_Correct_In_Excel the MajorID_Not_Correct_In_Excel to set
     */
    public void setMajorID_Not_Correct_In_Excel(String MajorID_Not_Correct_In_Excel) {
        this.majorID_Not_Correct_In_Excel = MajorID_Not_Correct_In_Excel;
    }

    /**
     * @return the create_acount_in_DB
     */
    public String getCreate_acount_in_DB() {
        return create_acount_in_DB;
    }

    /**
     * @param create_acount_in_DB the create_acount_in_DB to set
     */
    public void setCreate_acount_in_DB(String create_acount_in_DB) {
        this.create_acount_in_DB = create_acount_in_DB;
    }
    
    
    
}
