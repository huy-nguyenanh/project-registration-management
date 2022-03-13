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
        <link rel="stylesheet" href="./assets/css/lecture.css" />
        <jsp:useBean id="lecDAO" class="manager_dao.impl.LecturerInfoDAO" scope="request"></jsp:useBean>
        <jsp:useBean id="uploadDAO" class="manager_dao.impl.UploadFileDAO" scope="request"></jsp:useBean>
        <c:set var="role" value="${sessionScope.ROLE}"/>
        <c:set var="welcome_name" value="${sessionScope.WELCOME_NAME}"/>
    </head>
    <c:if test="${sessionScope.ROLE == null}">
        <c:redirect url="loginPage"/>
    </c:if>
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
                        <li>
                            <a href="studentPage">
                                <span class="fa-solid fa-graduation-cap"> </span>
                                <span>Student</span>
                            </a>
                        </li>
                        <li class="li-active">
                            <a href="lecturePage">
                                <span class="fa-solid fa-chalkboard-user"> </span>
                                <span>Lecture</span>
                            </a>
                        </li>
                        <li>
                            <a href="topicPage">
                                <span class="fa-solid fa-shapes"> </span>
                                <span>Topic</span>
                            </a>
                        </li>
                    </ul>
                </div>
            </section>
            <c:if test="${role eq 'Admin'}">
                <main class="main-content">
                    <header>
                        <div class="left-content">
                            <h2>Capstone Project Management</h2>
                            <h2></h2>
                        </div>
                        <div class="right-content">
                            <span >${welcome_name}</span>
                            <span class="fa-solid fa-bell"></span>
                            <div class="profile">
                                <img src="./assets/img/ava.jpg" alt="profile-image" />
                                <ul class="profile-link">
                                    <li><a href="profile.html">Profile</a></li>
                                    <li><a href="logoutAction">Logout</a></li>
                                </ul>
                            </div>
                        </div>
                    </header>

                    <div class="container">
                        <h3 class="title">Lecture List</h3>

                        <section class="list">
                            <div class="table-grid">
                                <div class="list-action">
                                    <div class="list-action-left">
                                        <form id="import-form" action="uploadLectureFileAction">
                                            <label for="import-file">Import</label>
                                            <input type="file" id="import-file" name="file_name" />
                                        </form>
                                    </div>
                                    <div class="list-action-right">
                                        <form id="form-search-lecture" class="form-search" action="searchLectureAction">
                                            <input
                                                type="text"
                                                class="search-input"
                                                placeholder="Search lecture ID"
                                                name="txtSearchLecturer"
                                                value="${param.txtSearchLecturer}"
                                                />
                                            <a
                                                href="javascript:{}"
                                                onclick="document.getElementById('form-search-lecture').submit();"
                                                ><i class="fas fa-search search-btn"> </i>
                                            </a>
                                        </form>
                                        <c:set var="searchLectureValue" value="${param.txtSearchLecturer}"/>
                                    </div>
                                </div>
                                <h3>List</h3>
                                <c:if test="${empty searchLectureValue}" >
                                    <div class="table-responsive">
                                        <table id="table-id">
                                            <thead>
                                                <tr>
                                                    <th>STT</th>
                                                    <th>Lecture ID</th>
                                                    <th>Full Name</th>
                                                    <th>Mail</th>
                                                    <th>Phone</th>
                                                    <th>Topic ID</th>
                                                    <th>Group ID</th>
                                                    <th>Action</th>
                                                </tr>
                                            </thead>
                                            <c:set var="lecture_list" value="${lecDAO.loadLecturerInfo()}"/>
                                            <c:if test="${not empty lecture_list}">
                                                <tbody>
                                                    <c:forEach var="lecture" items= "${lecture_list}" varStatus="counter">
                                                    <form action="adminUpdateLectureInfoAction" enctype="multipart/form-data">
                                                        <tr>
                                                            <td>${counter.count}</td>
                                                            <td>
                                                                ${lecture.lectureID}
                                                                <input type="hidden" name="txtID" value="${lecture.lectureID}" />
                                                            </td>
                                                            <td>
                                                                ${lecture.fullname}
                                                                <input type="hidden" name="txtFullname" value="${lecture.fullname}" />
                                                            </td>
                                                            <td>
                                                                ${lecture.email}
                                                                <input type="hidden" name="txtEmail" value="${lecture.email}" />
                                                            </td>
                                                            <td>
                                                                ${lecture.phoneNumber}
                                                                <input type="hidden" name="txtDOB" value="${lecture.phoneNumber}" />
                                                            </td>
                                                            <td>
                                                                <%--${lecture.topicID}--%>
                                                                <input type="text" name="txtTopicId" value="${lecture.topicID}" />
                                                            </td>
                                                            <td>
                                                                <%--${lecture.groupID}--%>
                                                                <input type="text" name="txtGroupId" value="${lecture.groupID}" />
                                                            </td>
                                                            <td>
                                                                <button
                                                                    style="width: 100%; background: red; color: aliceblue"
                                                                    type="submit"
                                                                    >
                                                                    Update
                                                                </button>
                                                            </td>
                                                        </tr>
                                                    </form>
                                                </c:forEach>
                                                </tbody>
                                            </c:if>
                                        </table>
                                    </div>
                                </c:if>
                                <c:if test="${not empty searchLectureValue}">
                                    <div class="table-responsive">
                                        <table id="table-id">
                                            <thead>
                                                <tr>
                                                    <th>STT</th>
                                                    <th>Lecture ID</th>
                                                    <th>Full Name</th>
                                                    <th>Mail</th>
                                                    <th>Phone</th>
                                                    <th>Topic ID</th>
                                                    <th>Group ID</th>
                                                    <th>Action</th>
                                                </tr>
                                            </thead>
                                            <c:set var="lecture_search_value" value="${requestScope.SEARCH_LECTURER}"/>
                                            <c:if test="${not empty lecture_search_value}">
                                                <tbody>
                                                    <c:forEach var="lecture" items= "${lecture_search_value}" varStatus="counter">
                                                    <form action="adminUpdateLectureInfoAction" enctype="multipart/form-data">
                                                        <tr>
                                                            <td>${counter.count}</td>
                                                            <td>
                                                                ${lecture.lectureID}
                                                                <input type="hidden" name="txtID" value="${lecture.lectureID}" />
                                                            </td>
                                                            <td>
                                                                ${lecture.fullname}
                                                                <input type="hidden" name="txtFullname" value="${lecture.fullname}" />
                                                            </td>
                                                            <td>
                                                                ${lecture.email}
                                                                <input type="hidden" name="txtEmail" value="${lecture.email}" />
                                                            </td>
                                                            <td>
                                                                ${lecture.phoneNumber}
                                                                <input type="hidden" name="txtDOB" value="${lecture.phoneNumber}" />
                                                            </td>
                                                            <td>
                                                                <%--${lecture.topicID}--%>
                                                                <input type="text" name="txtTopicId" value="${lecture.topicID}" />
                                                            </td>
                                                            <td>
                                                                <%--${lecture.groupID}--%>
                                                                <input type="text" name="txtGroupId" value="${lecture.groupID}" />
                                                            </td>
                                                            <td>
                                                                <button
                                                                    style="width: 100%; background: red; color: aliceblue"
                                                                    type="submit"
                                                                    >
                                                                    Update
                                                                </button>
                                                            </td>
                                                        </tr>
                                                    </form>
                                                </c:forEach>
                                                </tbody>
                                            </c:if>
                                        </table>
                                    </div>
                                </c:if>
                            </div>
                        </section>
                    </div>
                </main>
            </c:if>
            <c:if test="${role eq 'Student'}">
                <main class="main-content">
                    <header>
                        <div class="left-content">
                            <h2>Capstone Project Management</h2>
                            <h2></h2>
                        </div>
                        <div class="right-content">
                            <span >${welcome_name}</span>
                            <span class="fa-solid fa-bell"></span>
                            <div class="profile">
                                <img src="./assets/img/ava.jpg" alt="profile-image" />
                                <ul class="profile-link">
                                    <li><a href="profile.html">Profile</a></li>
                                    <li><a href="logoutAction">Logout</a></li>
                                </ul>
                            </div>
                        </div>
                    </header>

                    <div class="container">
                        <h3 class="title">Lecture List</h3>

                        <section class="list">
                            <div class="table-grid">
                                <div class="list-action">
                                    <div class="list-action-right">
                                        <form id="form-search-lecture" class="form-search" action="searchLectureAction">
                                            <input
                                                type="text"
                                                class="search-input"
                                                placeholder="Search lecture ID"
                                                name="txtSearchLecturer"
                                                value="${param.txtSearchLecturer}"
                                                />
                                            <a
                                                href="javascript:{}"
                                                onclick="document.getElementById('form-search-lecture').submit();"
                                                ><i class="fas fa-search search-btn"> </i>
                                            </a>
                                        </form>
                                        <c:set var="searchLectureValue" value="${param.txtSearchLecturer}"/>
                                    </div>
                                </div>
                                <h3>List</h3>
                                <c:if test="${empty searchLectureValue}">
                                    <div class="table-responsive">
                                        <table id="table-id">
                                            <thead>
                                                <tr>
                                                    <th>STT</th>
                                                    <th>Lecture ID</th>
                                                    <th>Full Name</th>
                                                    <th>Mail</th>
                                                    <th>Phone</th>
                                                    <th>Topic ID</th>
                                                    <th>Group ID</th>
                                                </tr>
                                            </thead>
                                            <c:set var="lecture_list" value="${lecDAO.loadLecturerInfo()}"/>
                                            <c:if test="${not empty lecture_list}">
                                                <tbody>
                                                    <c:forEach var="lecture" items= "${lecture_list}" varStatus="counter">
                                                    <form action="adminUpdateLectureInfoAction" enctype="multipart/form-data">
                                                        <tr>
                                                            <td>${counter.count}</td>
                                                            <td>
                                                                ${lecture.lectureID}
                                                                <input type="hidden" name="txtID" value="${lecture.lectureID}" />
                                                            </td>
                                                            <td>
                                                                ${lecture.fullname}
                                                                <input type="hidden" name="txtFullname" value="${lecture.fullname}" />
                                                            </td>
                                                            <td>
                                                                ${lecture.email}
                                                                <input type="hidden" name="txtEmail" value="${lecture.email}" />
                                                            </td>
                                                            <td>
                                                                ${lecture.phoneNumber}
                                                                <input type="hidden" name="txtDOB" value="${lecture.phoneNumber}" />
                                                            </td>
                                                            <td>
                                                                ${lecture.topicID}
                                                                <input type="hidden" name="txtTopicId" value="${lecture.topicID}" />
                                                            </td>
                                                            <td>
                                                                ${lecture.groupID}
                                                                <input type="hidden" name="txtGroupId" value="${lecture.groupID}" />
                                                            </td>
                                                        </tr>
                                                    </form>
                                                </c:forEach>
                                                </tbody>
                                            </c:if>
                                        </table>
                                    </div>
                                </c:if>
                                <c:if test="${not empty searchLectureValue}">
                                    <div class="table-responsive">
                                        <table id="table-id">
                                            <thead>
                                                <tr>
                                                    <th>STT</th>
                                                    <th>Lecture ID</th>
                                                    <th>Full Name</th>
                                                    <th>Mail</th>
                                                    <th>Phone</th>
                                                    <th>Topic ID</th>
                                                    <th>Group ID</th>
                                                </tr>
                                            </thead>
                                            <c:set var="lecture_search_value" value="${requestScope.SEARCH_LECTURER}"/>
                                            <c:if test="${not empty lecture_search_value}">
                                                <tbody>
                                                    <c:forEach var="lecture" items= "${lecture_search_value}" varStatus="counter">
                                                    <form action="adminUpdateLectureInfoAction" enctype="multipart/form-data">
                                                        <tr>
                                                            <td>${counter.count}</td>
                                                            <td>
                                                                ${lecture.lectureID}
                                                                <input type="hidden" name="txtID" value="${lecture.lectureID}" />
                                                            </td>
                                                            <td>
                                                                ${lecture.fullname}
                                                                <input type="hidden" name="txtFullname" value="${lecture.fullname}" />
                                                            </td>
                                                            <td>
                                                                ${lecture.email}
                                                                <input type="hidden" name="txtEmail" value="${lecture.email}" />
                                                            </td>
                                                            <td>
                                                                ${lecture.phoneNumber}
                                                                <input type="hidden" name="txtDOB" value="${lecture.phoneNumber}" />
                                                            </td>
                                                            <td>
                                                                ${lecture.topicID}
                                                                <input type="hidden" name="txtTopicId" value="${lecture.topicID}" />
                                                            </td>
                                                            <td>
                                                                ${lecture.groupID}
                                                                <input type="hidden" name="txtGroupId" value="${lecture.groupID}" />
                                                            </td>
                                                        </tr>
                                                    </form>
                                                </c:forEach>
                                                </tbody>
                                            </c:if>
                                        </table>
                                    </div>
                                </c:if>
                            </div>
                        </section>
                    </div>
                </main>
            </c:if>
        </div>
        <script src="./assets/js/main.js"></script>

    </body>


</html>
