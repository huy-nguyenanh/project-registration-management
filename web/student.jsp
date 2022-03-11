
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
        <jsp:useBean id="stuDAO" class="manager_dao.impl.StudentInfoDAO" scope="request"></jsp:useBean>
        <jsp:useBean id="uploadDAO" class="manager_dao.impl.UploadFileDAO" scope="request"></jsp:useBean>
        <c:set var="role" value="${sessionScope.ROLE}"/>
    </head>
    <c:if test="${role eq 'Admin'}">
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
                            <li>
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
                                    <li><a href="profile.html">Profile</a></li>
                                    <li><a href="logoutAction">Logout</a></li>
                                </ul>
                            </div>
                        </div>
                    </header>

                    <div class="container">
                        <h3 class="title">Student List</h3>

                        <section class="list">
                            <div class="table-grid">
                                <div class="list-action">
                                    <div class="list-action-left">
                                        <form id="import-form" action="uploadStudentFileAction">
                                            <label for="import-file">Import</label>
                                            <input type="file" id="import-file" name="file_name" />
                                        </form>
                                        <c:set var="errors" value="${requestScope.UPLOAD_FILE_ERROR}"/>
                                        <c:if test="${not empty errors}">
                                            <c:if test="${not empty errors.uploadFile_False}">
                                                <font color="red">
                                                ${errors.uploadFile_False}
                                                </font>
                                            </c:if>
                                            <c:if test="${not empty errors.studentID_Not_Correct_In_Excel}">
                                                <font color="red">
                                                ${errors.studentID_Not_Correct_In_Excel}
                                                </font>
                                            </c:if>
                                            <c:if test="${not empty errors.email_Not_Correct_In_Excel}">
                                                <font color="red">
                                                ${errors.email_Not_Correct_In_Excel}
                                                </font>
                                            </c:if>
                                            <c:if test="${not empty errors.majorID_Not_Correct_In_Excel}">
                                                <font color="red">
                                                ${errors.majorID_Not_Correct_In_Excel}
                                                </font>
                                            </c:if>
                                            <c:if test="${not empty errors.insertToDB_False}">
                                                <font color="red">
                                                ${errors.insertToDB_False}
                                                </font>
                                            </c:if>
                                            <c:if test="${not empty errors.create_acount_in_DB}">
                                                <font color="red">
                                                ${errors.create_acount_in_DB}
                                                </font>
                                            </c:if>
                                        </c:if>
                                    </div>

                                    <div class="list-action-right">
                                        <form id="form-search-id" class="form-search" action="adminSearchStudentAction">
                                            <input
                                                type="text"
                                                class="search-input"
                                                placeholder="Search Student ID"
                                                name="txtSearchStudent"
                                                value="${param.txtSearchStudent}"
                                                />
                                            <a
                                                href="javascript:{}"
                                                onclick="document.getElementById('form-search-id').submit();"
                                                ><i class="fas fa-search search-btn"> </i>
                                            </a>
                                        </form>
                                    </div>

                                    <c:set var="searchValue" value="${param.txtSearchStudent}"/>
                                </div>
                                <h3>List</h3>
                                <c:set var="errors_create_group" value="${requestScope.ERROR_CREATE_GROUP}"/>
                                <c:if test="${not empty errors_create_group}">
                                    ${errors_create_group}
                                </c:if>
                                <c:if test="${empty searchValue}">
                                    <div class="table-responsive">

                                        <table id="table-id">
                                            <thead>
                                                <tr>
                                                    <th>STT</th>
                                                    <th>Student ID</th>
                                                    <th>Full name</th>
                                                    <th>Mail</th>
                                                    <th>Date of birth</th>
                                                    <th>Major ID</th>
                                                    <th>Group ID</th>
                                                    <th>Action</th>
                                                    <th>                                            
                                                        <button type="button" onclick="createGroup()">Create Group</button>
                                                    </th>
                                                </tr>
                                            </thead>
                                            <c:set var="student_list" value="${stuDAO.loadStudentInfo()}"/>
                                            <c:if test="${not empty student_list}">
                                                <tbody>
                                                    <c:forEach var="student" items= "${student_list}" varStatus="counter">
                                                    <form action="adminUpdateStudentInfoAction" enctype="multipart/form-data">
                                                        <tr>
                                                            <td>${counter.count}</td>
                                                            <td>
                                                                ${student.studentID}
                                                                <input type="hidden" name="txtID" value="${student.studentID}" />
                                                            </td>
                                                            <td>
                                                                ${student.fullName}
                                                                <input type="hidden" name="txtFullname" value="${student.fullName}" />
                                                            </td>
                                                            <td>
                                                                ${student.email}
                                                                <input type="hidden" name="txtEmail" value="${student.email}" />
                                                            </td>
                                                            <td>
                                                                ${student.DOB}
                                                                <input type="hidden" name="txtDOB" value="${student.DOB}" />
                                                            </td>
                                                            <td>
                                                                ${student.majorID}
                                                                <input type="hidden" name="txtMajorID" value="${student.majorID}" />
                                                            </td>
                                                            <td>
                                                                <%--${student.groupID}--%>
                                                                <input type="text" name="txtGroupID" value="${student.groupID}" />
                                                            </td>
                                                            <td>
                                                                <button
                                                                    style="width: 100%; background: red; color: aliceblue"
                                                                    type="submit"
                                                                    >
                                                                    Update
                                                                </button>
                                                                <input type="hidden" name="lastSearchValue" 
                                                                       value="${searchValue}" />
                                                            </td>
                                                            <td>
                                                                <input type="checkbox" name="chkCreate" id="chkCreate" value="${student.studentID}">
                                                            </td>
                                                        </tr>
                                                    </form>
                                                </c:forEach>
                                                </tbody>
                                            </c:if>

                                        </table>
                                    </div>
                                </c:if>
                                <c:if test="${not empty searchValue}">
                                    <div class="table-responsive">
                                        <c:set var="error_update_group" value="${requestScope.ERROR_UPDATE_GROUP}"/>
                                        <c:if test="${not empty error_update_group}">
                                            ${error_update_group}
                                        </c:if>
                                        <table id="table-id">
                                            <thead>
                                                <tr>
                                                    <th>STT</th>
                                                    <th>Student ID</th>
                                                    <th>Full name</th>
                                                    <th>Mail</th>
                                                    <th>Date of birth</th>
                                                    <th>Major ID</th>
                                                    <th>Group ID</th>
                                                    <th>Action</th>
                                                    <th>                                            
                                                        <button type="button" onclick="createGroup()">Create Group</button>
                                                    </th>
                                                </tr>
                                            </thead>
                                            <c:set var="student_search_value" value="${requestScope.SEARCH_STUDENT}"/>
                                            <c:forEach var="student" items= "${student_search_value}" varStatus="counter">
                                                <form action="adminUpdateStudentInfoAction" enctype="multipart/form-data">
                                                    <tbody>
                                                        <tr>
                                                            <td>${counter.count}</td>
                                                            <td>
                                                                ${student.studentID}
                                                                <input type="hidden" name="txtID" value="${student.studentID}" />
                                                            </td>
                                                            <td>
                                                                ${student.fullName}
                                                                <input type="hidden" name="txtFullname" value="${student.fullName}" />
                                                            </td>
                                                            <td>
                                                                ${student.email}
                                                                <input type="hidden" name="txtEmail" value="${student.email}" />
                                                            </td>
                                                            <td>
                                                                ${student.DOB}
                                                                <input type="hidden" name="txtDOB" value="${student.DOB}" />
                                                            </td>
                                                            <td>
                                                                ${student.majorID}
                                                                <input type="hidden" name="txtMajorID" value="${student.majorID}" />
                                                            </td>
                                                            <td>
                                                                <%--${student.groupID}--%>
                                                                <input type="text" name="txtGroupID" value="${student.groupID}" />
                                                            </td>
                                                            <td>
                                                                <button
                                                                    style="width: 100%; background: red; color: aliceblue"
                                                                    type="submit"
                                                                    >
                                                                    Update
                                                                </button>
                                                                <input type="hidden" name="lastSearchValue" 
                                                                       value="${searchValue}" />
                                                            </td>
                                                            <td>
                                                                <input type="checkbox" name="chkCreate" id="chkCreate" value="${student.studentID}">
                                                            </td>
                                                        </tr>
                                                    </tbody>
                                                </form>
                                            </c:forEach>
                                        </table>
                                    </div>
                                </c:if>
                            </div>
                        </section>
                    </div>
                </main>
            </div>
            <script src="./assets/js/main.js"></script>
            <script>
                                                            function createGroup() {
                                                            var list = document.querySelectorAll("#chkCreate");
                                                            var str = "adminCreateGroupAction?";
                                                            if (list.length === 1)
                                                            {
                                                            str += "chkCreate" + list[0].value;
                                                            }
                                                            else
                                                            {
                                                            for (i = 0; i <= list.length; i++) {
                                                            try {
                                                            if (list[i].checked) {
                                                            if (i === 0) {
                                                            str += "chkCreate=" + list[0].value;
                                                            } else {
                                                            str += "&chkCreate=" + list[i].value;
                                                            }
                                                            }
                                                            } catch {}
                                                            }
                                                            }
                                                            window.location.href = str;
                                                            }
            </script>
        </body>
    </c:if>
       
        
</html>
