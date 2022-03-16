/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utillsHelper;

/**
 *
 * @author 84399
 */
public class ApplicationConstant {

    public class AuthenticationFilter {

        public static final String WELCOME_PAGE_DEFAULT = "";
        public static final String WELCOME_PAGE = "welcomePage";
        public static final String LOGIN_ADMIN_PAGE = "adminLoginPage";
        public static final String ADMIN_HOME_PAGE = "adminHomePage";
        public static final String LOGIN_PAGE = "loginPage";
        public static final String STUDENT_HOME_PAGE = "studentHomePage";

    }

    public class LogoutServlet {

        public static final String WELCOME_PAGE = "";
    }

    public class LoginAdminServlet {

        public static final String INVALID_PAGE = "loginPageErrors";
        public static final String ADMIN_HOME_PAGE = "adminHomePage";
    }

    public class LoginServlet {

        public static final String INVALID_PAGE = "loginPage";
        public static final String STUDENT_HOME_PAGE = "studentHomePage";
        public static final String LECTURE_HOME_PAGE = "lectureHomePage";
        public static final String HOME_PAGE = "homePage";
    }

    public class UploadFileServlet {

        public static final String UPLOAD_STUDENT_FILE_RETURN = "studentPage";
        public static final String UPLOAD_LECTURE_FILE_RETURN = "lecturePage";
        public static final String UPLOAD_TOPIC_FILE_RETURN = "topicPage";
    }

    public class ExportStudentFileServlet {

        public static final String RETURN_STUDENT_HOME = "studentPage";
    }

    public class AdminSearchStudentServlet {

        public static final String RETURN_STUDENT_PAGE = "studentPage";
    }

    public class AdminSortStudentByStatusServlet {

        public static final String STUDENT_INFO = "studentInfoPage";
    }

    public class AdminUpdateStudentInfoServlet {

        public static final String STUDENT_INFO = "studentPage";
    }

    public class AdminUpdateLectureInfoServlet {

        public static final String LECTURE_INFO = "lecturePage";
    }

    public class AdminUpdateTopicInfoServlet {

        public static final String TOPIC_INFO = "topicPage";
    }

    public class StudentCreateGroupServlet {

        public static final String CREATE_GROUP_RETURN = "studentPage";
    }

    public class StudentChooseTopicServlet {

        public static final String STUDENT_HOME_PAGE = "topicPage";
    }

    public class UpdateProfileStudentServlet {

        public static final String STUDENT_HOME_PAGE = "studentHomePage";
    }

    public class UpdateProfileLectureServlet {

        public static final String PROFILE_PAGE = "changePasswordPage";
    }

    public class AdminSearchLecturerServlet {

        public static final String SEARCH_RETURN = "lecturePage";

    }

    public class AdminSearchTopicServlet {

        public static final String SEARCH_RETURN = "topicPage";
    }

    public class AdminCreateGroupServlet {

        public static final String CREATE_GROUP_RETURN = "studentPage";
    }

    public class UpdateNotifyServlet {

        public static final String RETURN_PAGE = "notifyPage";
    }
    public class SendEmailServlet {

        public static final String RETURN_PAGE = "notifyPage";
    }
    
    
}
