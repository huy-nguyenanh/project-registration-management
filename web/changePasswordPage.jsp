
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8" />
        <meta http-equiv="X-UA-Compatible" content="IE=edge" />
        <meta name="viewport" content="width=device-width, initial-scale=1.0" />
        <title>Capstone Project Management</title>
        <link
            rel="stylesheet"
            href="./assets/icon/fontawesome-free-6.0.0-web/css/all.css"
            />

        <link rel="stylesheet" href="./assets/css/reset.css" />
        <link rel="stylesheet" href="./assets/css/main.css" />
        <link rel="stylesheet" href="./assets/css/profile.css">

        <jsp:useBean id="stuDAO" class="manager_dao.impl.StudentInfoDAO" scope="request"/>
        <jsp:useBean id="lecDAO" class="manager_dao.impl.LecturerInfoDAO" scope="request"/>
    </head>
    <body>
        <div class="wrapper">
            <input type="checkbox" id="sidebar-toggle" />
            <section class="sideBar">
                <div class="top-sideBar">
                    <h3>Dashboard</h3>
                    <label for="sidebar-toggle" class="fa-solid fa-bars"></label>
                </div>
                <div class="bottom-sideBar">
                    <ul>
                        <li>
                            <a href="homePage">
                                <span class="fa-solid fa-house"> </span>
                                <span>Home</span>
                            </a>
                        </li>
                        <li class="li-active">
                            <a href="studentPage">
                                <span class="fa-solid fa-graduation-cap"> </span>
                                <span>Student</span>
                            </a>
                        </li>
                        <c:if test="${role ne 'Lecture'}">
                            <li>
                                <a href="lecturePage">
                                    <span class="fa-solid fa-chalkboard-user"> </span>
                                    <span>Lecture</span>
                                </a>
                            </li>
                        </c:if>
                        <li>
                            <a href="topicPage">
                                <span class="fa-solid fa-shapes"> </span>
                                <span>Topic</span>
                            </a>
                        </li>
                        <c:if test="${role == 'Lecture'}">
                            <li>
                                <a href="notifyPage">
                                    <span class="fa-solid fa-shapes"> </span>
                                    <span>Notify</span>
                                </a>
                            </li>
                        </c:if>
                    </ul>
                </div>
            </section>
            <main class="main-content">
                <header>
                    <div class="left-content">
                        <h2>Capstone Project Management</h2>
                        <h2></h2>
                    </div>
                    <div class="right-content">
                        <span class="fa-solid fa-bell"></span>
                        <div class="profile">
                            <img src="./assets/img/ava.jpg" alt="profile-image" />
                            <ul class="profile-link">
                                <c:if test="${role ne 'Admin'}">
                                    <li><a href="profilePage">Profile</a></li>
                                    </c:if>
                                <li><a href="logoutAction">Logout</a></li>
                            </ul>
                        </div>
                    </div>
                </header>

                <div class="container">
                    <h3 class="title">Profile Information</h3>
                    <!-- <button id="update-profile-btn">Back</button> -->
                    <a href="profilePage" class="">Back</a>
                    <c:set var="msg" value="${requestScope.CHANGE_PASSWORD_COMPLETE}"/>
                    <c:if test="${not empty msg}">
                        <div id="error-modal" class="">
                            <!-- Modal content -->
                            <div class="modal-content">
                                <span class="close">&times;</span>
                                <span class="error">${msg}</span>
                            </div>
                        </div>
                    </c:if>
                    <c:set var="change_passw_errors" value="${requestScope.CHANGE_PASSWORD_ERRORS}"/>
                    <c:if test="${not empty change_passw_errors}">
                        <div id="error-modal" class="">
                            <!-- Modal content -->
                            <div class="modal-content">
                                <span class="close">&times;</span>
                                <span class="error">${change_passw_errors}</span>
                            </div>
                        </div>
                    </c:if>
                    <div id="profile-2" class="profile-content">
                        <ul class="profile-list">
                            <li>ID</li>
                            <li>Old Password</li>
                            <li>New Password</li>
                            <li>Confirm Password</li>
                            <li></li>
                        </ul>
                        <c:set var="stu_id" value="${sessionScope.STUDENT_ID}"/>
                        <c:set var="lec_id" value="${sessionScope.LECTURE_ID}"/>
                        <c:set var="student" value="${stuDAO.getStudentbyID(stu_id)}"/>
                        <c:set var="lec" value="${lecDAO.getLecturebyID(lec_id)}"/>
                        
                        <form action="changePasswordAction">
                            <ul class="profile-list">
                                <li>
                                    <c:if test="${not empty stu_id}">
                                        ${stu_id}
                                        <input type="hidden" name="txtAccountID" value="${student.accountID}" />
                                    </c:if>
                                    <c:if test="${not empty lec_id}">
                                        ${lec_id}
                                        <input type="hidden" name="txtAccountID" value="${lec.accountID}" />
                                    </c:if>
                                    
                                </li>
                                <li>
                                    <input type="password" name="old_password" id="2" placeholder="Old password" required>
                                </li>
                                <li>
                                    <input type="password" name="changePassword" id="3" placeholder="New password" onChange="validatePassword()" required >
                                </li>
                                <li>
                                    <input type="password" name="confirm" id="4" placeholder="Confirm password" onChange="validatePassword()" required>
                                </li>
                                <li>
                                    <button id="" type="reset">Reset</button>
                                    <button id="submit-profile-btn" type="submit">Submit</button>
                                </li>
                            </ul>
                        </form> 

                    </div>
                </div>
            </main>
            <script src="./assets/js/main.js"></script>
    </body>
</html>

