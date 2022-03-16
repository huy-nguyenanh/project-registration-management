<%-- 
    Document   : profile
    Created on : Mar 14, 2022, 10:05:11 AM
    Author     : 84399
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8" />
        <meta http-equiv="X-UA-Compatible" content="IE=edge" />
        <meta name="viewport" content="width=device-width, initial-scale=1.0" />
        <title>Capstone Project Management</title>
        <link
            rel="stylesheet"
            href="./assets/icon/fontawesome-free-6.0.0-web/css/all.css"
            />
        <script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
        <link rel="stylesheet" href="./assets/css/reset.css" />
        <link rel="stylesheet" href="./assets/css/main.css" />
        <link rel="stylesheet" href="./assets/css/profile.css">

        <c:set var="role" value="${sessionScope.ROLE}"/>
        <c:set var="welcome_name" value="${sessionScope.WELCOME_NAME}"/>
        <c:set var="stuID" value="${sessionScope.STUDENT_ID}"/>
        <jsp:useBean id="stuDAO" class="manager_dao.impl.StudentInfoDAO" scope="request"/>
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
                        <!--<span class="fa-solid fa-bell"></span>-->
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
                    <!-- <button id="update-profile-btn">Change Password</button> -->
                    <a href="changePasswordPage">Change Password</a>
                    <div id="profile-1" class="profile-content">
                        <ul class="profile-list">
                            <li>Name</li>
                            <li>Phone</li>
                            <li>Student ID</li>
                            <li>Email</li>
                            <li>Date Of Birth</li>
                            <li>Major</li>
                        </ul>
                        <c:set var="stuInfo" value="${stuDAO.getStudentbyID(stuID)}"/>
                        <ul class="profile-list">
                            <li>${stuInfo.fullName}</li>
                            <li>${stuInfo.phoneNumber}</li>
                            <li>${stuInfo.studentID}</li>
                            <li>${stuInfo.email}</li>
                            <li>${stuInfo.DOB}</li>
                            <li>${stuInfo.majorID}</li>
                        </ul>
                    </div>


                </div>
            </main>
            <script src="./assets/js/main.js"></script>
    </body>
</html>
