
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
        <link rel="stylesheet" href="./assets/css/student.css" />
        <jsp:useBean id="stuDAO" class="manager_dao.impl.StudentInfoDAO" scope="request"></jsp:useBean>
        <jsp:useBean id="uploadDAO" class="manager_dao.impl.UploadFileDAO" scope="request"></jsp:useBean>
        <jsp:useBean id="groupDAO" class="manager_dao.impl.GroupDAO" scope="request"></jsp:useBean>
        <c:set var="role" value="${sessionScope.ROLE}"/>
        <c:set var="welcome_name" value="${sessionScope.WELCOME_NAME}"/>
        <c:set var="lecID" value="${sessionScope.LECTURE_ID}"/>
        <c:set var="grID" value="${sessionScope.LECTURE_GROUP_ID}"/>
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
            <c:if test="${role eq 'Admin'}">
                <main class="main-content">
                    <header>
                        <div class="left-content">
                            <h2>Capstone Project Management</h2>
                            <h2></h2>
                        </div>
                        <div class="right-content">
                            <span >${welcome_name}</span>
                            <!--<span class="fa-solid fa-bell"></span>-->
                            <div class="profile">
                                <img src="./assets/img/ava.jpg" alt="profile-image" />
                                <ul class="profile-link">
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
                                        <form action="exportStudentFileAction" >
                                            <label for="export-file">Export</label>
                                            <input type="submit" id="export-file"/>

                                        </form>
                                        <c:set var="errors" value="${requestScope.UPLOAD_FILE_ERROR}"/>
                                        <c:if test="${not empty errors}">
                                            <div id="error-modal" class="">
                                                <!-- Modal content -->
                                                <div class="modal-content">
                                                    <span class="close">&times;</span>
                                                    <c:if test="${not empty errors.uploadFile_False}">
                                                        <span class="error">${errors.uploadFile_False} </span>
                                                    </c:if>
                                                    <c:if test="${not empty errors.studentID_Not_Correct_In_Excel}">
                                                        <span class="error">${errors.studentID_Not_Correct_In_Excel} </span>
                                                    </c:if>
                                                    <c:if test="${not empty errors.email_Not_Correct_In_Excel}">
                                                        <span class="error">${errors.email_Not_Correct_In_Excel} </span>
                                                    </c:if>
                                                    <c:if test="${not empty errors.majorID_Not_Correct_In_Excel}">
                                                        <span class="error">${errors.majorID_Not_Correct_In_Excel} </span>
                                                    </c:if>
                                                    <c:if test="${not empty errors.insertToDB_False}">
                                                        <span class="error">${errors.insertToDB_False} </span>
                                                    </c:if>
                                                    <c:if test="${not empty errors.create_acount_in_DB}">
                                                        <span class="error">${errors.create_acount_in_DB} </span>
                                                    </c:if>
                                                </div>
                                            </div>
                                        </c:if>
                                    </div>

                                    <div class="list-action-right">
                                        <form id="form-search-id" class="form-search" action="searchStudentAction">
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
                                <a id="view-mode" href="">View mode</a>
                                <span>/</span>
                                <a id="edit-mode" href="">Edit mode</a>
                                <c:set var="student_list" value="${stuDAO.loadStudentInfo()}"/>
                                <form action="filterStudentAction" id="filterForm">
                                    <select class="select1" name="status">
                                      <option selected disabled>Filter</option>
                                      <option value="all">All</option>
                                      <option value="In_group">In group</option>
                                      <option value="free">Free</option>
                                    </select>
                                   
                                  </form>
                                <c:set var="filter_student" value="${requestScope.FILTER_STUDENT}"/>

                                <%-- View mode --%>


                                <c:if test="${not empty searchValue}">
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
                                                </tr>
                                            </thead>
                                            <c:set var="student_search_value" value="${requestScope.SEARCH_STUDENT}"/>
                                            <c:if test="${not empty student_search_value}">
                                                <c:forEach var="student" items= "${student_search_value}" varStatus="counter">
                                                    <tbody>
                                                        <tr>
                                                            <td>${counter.count}</td>
                                                            <td>
                                                                ${student.studentID}
                                                            </td>
                                                            <td>
                                                                ${student.fullName}
                                                            </td>
                                                            <td>
                                                                ${student.email}
                                                            </td>
                                                            <td>
                                                                ${student.DOB}
                                                            </td>
                                                            <td>
                                                                ${student.majorID}
                                                            </td>
                                                            <td>
                                                                ${student.groupID}
                                                            </td>
                                                        </tr>
                                                    </tbody>
                                                </c:forEach>
                                            </c:if>
                                            <c:if test="${empty student_search_value}">
                                                <tr>
                                                    <td>No result matches!!!</td>
                                                </tr>
                                            </c:if> 
                                        </table>
                                    </div>
                                </c:if>
                                <c:if test="${empty searchValue}">
                                    <div class="table-responsive">
                                        <c:if test="${not empty filter_student}">
                                            <c:set var="error_update_group" value="${requestScope.ERROR_UPDATE_GROUP}"/>
                                            <c:if test="${not empty error_update_group}">
                                                <div id="error-modal" class="">
                                                    <!-- Modal content -->
                                                    <div class="modal-content">
                                                        <span class="close">&times;</span>
                                                        <span class="error">${error_update_group}</span>
                                                    </div>
                                                </div>
                                            </c:if>
                                            <c:set var="error_create_group" value="${requestScope.ERROR_CREATE_GROUP}"/>
                                            <c:if test="${not empty error_create_group}">
                                                <div id="error-modal" class="">
                                                    <!-- Modal content -->
                                                    <div class="modal-content">
                                                        <span class="close">&times;</span>
                                                        <span class="error">${error_create_group}</span>
                                                    </div>
                                                </div>
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
                                                    </tr>
                                                </thead>
                                                <c:if test="${not empty student_list}">
                                                    <tbody>
                                                        <c:forEach var="student" items= "${filter_student}" varStatus="counter">
                                                            <tr>
                                                                <td>${counter.count}</td>
                                                                <td>
                                                                    ${student.studentID}
                                                                </td>
                                                                <td>
                                                                    ${student.fullName}
                                                                </td>
                                                                <td>
                                                                    ${student.email}
                                                                </td>
                                                                <td>
                                                                    ${student.DOB}
                                                                </td>
                                                                <td>
                                                                    ${student.majorID}
                                                                </td>
                                                                <td>
                                                                    ${student.groupID}
                                                                </td>
                                                            </tr>
                                                        </c:forEach>
                                                    </tbody>
                                                </c:if>
                                            </table>
                                        </c:if>
                                        <c:if test="${empty filter_student}">
                                            <c:set var="error_update_group" value="${requestScope.ERROR_UPDATE_GROUP}"/>
                                            <c:if test="${not empty error_update_group}">
                                                <div id="error-modal" class="">
                                                    <!-- Modal content -->
                                                    <div class="modal-content">
                                                        <span class="close">&times;</span>
                                                        <span class="error">${error_update_group}</span>
                                                    </div>
                                                </div>
                                            </c:if>
                                            <c:set var="error_create_group" value="${requestScope.ERROR_CREATE_GROUP}"/>
                                            <c:if test="${not empty error_create_group}">
                                                <div id="error-modal" class="">
                                                    <!-- Modal content -->
                                                    <div class="modal-content">
                                                        <span class="close">&times;</span>
                                                        <span class="error">${error_create_group}</span>
                                                    </div>
                                                </div>
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
                                                    </tr>
                                                </thead>
                                                <c:if test="${not empty student_list}">
                                                    <tbody>
                                                        <c:forEach var="student" items= "${student_list}" varStatus="counter">
                                                            <tr>
                                                                <td>${counter.count}</td>
                                                                <td>
                                                                    ${student.studentID}
                                                                </td>
                                                                <td>
                                                                    ${student.fullName}
                                                                </td>
                                                                <td>
                                                                    ${student.email}
                                                                </td>
                                                                <td>
                                                                    ${student.DOB}
                                                                </td>
                                                                <td>
                                                                    ${student.majorID}
                                                                </td>
                                                                <td>
                                                                    ${student.groupID}
                                                                </td>
                                                            </tr>
                                                        </c:forEach>
                                                    </tbody>
                                                </c:if>
                                            </table>
                                        </c:if>
                                    </div>
                                </c:if>

                                <%-- Edit mode --%>
                                <c:if test="${empty searchValue}">
                                    <div class="table-responsive2" style="display: none">

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
                                                        <button class="myBtn" type="button" onclick="createGroup()">Create Group</button>
                                                    </th>
                                                </tr>
                                            </thead>
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
                                                                <input type="text" name="txtGroupID_new" value="${student.groupID}" />
                                                                <input type="hidden" name="txtGroupID_old" value="${student.groupID}" />
                                                                <input type="hidden" name="txtRole" value="${student.role}" />
                                                            </td>
                                                            <td>
                                                                <button
                                                                    type="submit"
                                                                    class="myBtn"
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
                                    <div class="table-responsive2" style="display: none">

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
                                                        <button class="myBtn" type="button" onclick="createGroup()">Create Group</button>
                                                    </th>
                                                </tr>
                                            </thead>
                                            <c:set var="student_search_value" value="${requestScope.SEARCH_STUDENT}"/>
                                            <c:if test="${not empty student_search_value}">
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
                                                                    <input type="text" name="txtGroupID_new" value="${student.groupID}" />
                                                                    <input type="hidden" name="txtGroupID_old" value="${student.groupID}" />
                                                                    <input type="hidden" name="txtRole" value="${student.role}" />
                                                                </td>
                                                                <td>
                                                                    <button
                                                                        type="submit"
                                                                        class="myBtn"
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
                                            </c:if>
                                            <c:if test="${empty student_search_value}">
                                                <tr>
                                                    <td>No result matches!!!</td>
                                                </tr>
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
                            <!--<span class="fa-solid fa-bell"></span>-->
                            <div class="profile">
                                <img src="./assets/img/ava.jpg" alt="profile-image" />
                                <ul class="profile-link">
                                    <li><a href="profilePage">Profile</a></li>
                                    <li><a href="logoutAction">Logout</a></li>
                                </ul>
                            </div>
                        </div>
                    </header>
                    <%-- Error modal --%>
                    <c:set var="errors_create_group" value="${requestScope.ERROR_CREATE_GROUP}"/>
                    <c:if test="${not empty errors_create_group}">
                        <div id="error-modal" class="">
                            <!-- Modal content -->
                            <div class="modal-content">
                                <span class="close">&times;</span>
                                <span class="error">${errors_create_group}</span>
                            </div>
                        </div>
                    </c:if>
                    <c:set var="errors_member_list" value="${requestScope.ERROR_LIST_MEMBER}"/>
                    <c:if test="${not empty errors_member_list}">
                        <div id="error-modal" class="">
                            <!-- Modal content -->
                            <div class="modal-content">
                                <span class="close">&times;</span>
                                <span class="error">${errors_member_list}</span>
                            </div>
                        </div>
                    </c:if>
                    <c:set var="error_update_group" value="${requestScope.ERROR_UPDATE_GROUP}"/>
                    <c:if test="${not empty error_update_group}">
                        <div id="error-modal" class="">
                            <!-- Modal content -->
                            <div class="modal-content">
                                <span class="close">&times;</span>
                                <span class="error">${error_update_group}</span>
                            </div>
                        </div>
                    </c:if>
                    <div class="container">
                        <h3 class="title">Student List</h3>

                        <section class="list">
                            <div class="table-grid">
                                <div class="list-action">
                                    <div class="list-action-left">
                                        <c:set var="searchValue" value="${param.txtSearchStudent}"/>
                                        <form action="ShowListMemberInGroupServlet">
                                            <input type="hidden" name="txtId" value="${sessionScope.STUDENT_ID}" />
                                            <button class="myBtn" type="submit" value="">Group's member list </button>
                                        </form>
                                    </div>
                                    <div class="list-action-right">
                                        <form id="form-search-id" class="form-search" action="searchStudentAction">
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

                                   
                                </div>
                                <h3>View mode</h3>
                                <form action="filterStudentAction" id="filterForm">
                                    <select class="select1" name="status">
                                      <option selected disabled>Filter</option>
                                      <option value="all">All</option>
                                      <option value="In_group">In group</option>
                                      <option value="free">Free</option>
                                    </select>
                                  </form>
                                <c:set var="filter_student" value="${requestScope.FILTER_STUDENT}"/>
                                <c:set var="list_member" value="${requestScope.LIST_MEMBER}"/>
                                <c:if test="${not empty list_member}">
                                    <div class="table-responsive">

                                        <table id="table-id">
                                            <thead>
                                                <tr>
                                                    <th>STT</th>
                                                    <th>Group ID</th>
                                                    <th>Full name</th>
                                                    <th>Member ID</th>
                                                    <th>Topic ID</th>
                                                </tr>
                                            </thead>
                                            <c:forEach var="member" items= "${list_member}" varStatus="counter">
                                                <tbody>
                                                    <tr>
                                                        <td>${counter.count}</td>
                                                        <td>
                                                            ${member.groupId}
                                                            <input type="hidden" name="txtGroupID" value="${student.groupID}" />
                                                        </td>
                                                        <td>
                                                            ${member.fullname}
                                                            <input type="hidden" name="txtFullname" value="${student.fullName}" />
                                                        </td>
                                                        <td>
                                                            ${member.memberId}
                                                            <input type="hidden" name="txtEmail" value="${student.email}" />
                                                        </td>
                                                        <td>
                                                            ${member.topicId}
                                                            <input type="hidden" name="txtDOB" value="${student.DOB}" />
                                                        </td>
                                                    </tr>
                                                </tbody>
                                            </c:forEach>
                                        </table>
                                    </div>
                                </c:if>
                                <c:if test="${empty list_member}">  
                                    <c:if test="${empty searchValue}">
                                        <div class="table-responsive">
                                            <c:if test="${not empty filter_student}">
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
                                                            <th>                                            
                                                                <button class="myBtn" type="button" onclick="createGroup()">Create Group</button>
                                                            </th>
                                                        </tr>
                                                    </thead>
                                                    <c:set var="student_list" value="${stuDAO.loadStudentInfo()}"/>
                                                    <c:if test="${not empty student_list}">
                                                        <tbody>
                                                            <c:forEach var="student" items= "${filter_student}" varStatus="counter">

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
                                                                        ${student.groupID}
                                                                        <input type="hidden" name="txtGroupID" value="${student.groupID}" />
                                                                    </td>

                                                                    <td>
                                                                        <c:if test="${student.studentID != sessionScope.STUDENT_ID}">
                                                                            <input type="checkbox" name="chkCreate" id="chkCreate" value="${student.studentID}">
                                                                        </c:if>
                                                                    </td>
                                                                </tr>
                                                            </c:forEach>
                                                        </tbody>
                                                    </c:if>
                                                </table>
                                            </c:if>
                                            <c:if test="${empty filter_student}">
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
                                                            <th>                                            
                                                                <button class="myBtn" type="button" onclick="createGroup()">Create Group</button>
                                                            </th>
                                                        </tr>
                                                    </thead>
                                                    <c:set var="student_list" value="${stuDAO.loadStudentInfo()}"/>
                                                    <c:if test="${not empty student_list}">
                                                        <tbody>
                                                            <c:forEach var="student" items= "${student_list}" varStatus="counter">

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
                                                                        ${student.groupID}
                                                                        <input type="hidden" name="txtGroupID" value="${student.groupID}" />
                                                                    </td>

                                                                    <td>
                                                                        <c:if test="${student.studentID != sessionScope.STUDENT_ID}">
                                                                            <input type="checkbox" name="chkCreate" id="chkCreate" value="${student.studentID}">
                                                                        </c:if>
                                                                    </td>
                                                                </tr>
                                                            </c:forEach>
                                                        </tbody>
                                                    </c:if>
                                                </table>
                                            </c:if>
                                        </div>
                                    </c:if>
                                    <c:if test="${not empty searchValue}">
                                        <div class="table-responsive">
                                            <c:set var="error_update_group" value="${requestScope.ERROR_UPDATE_GROUP}"/>
                                            <c:if test="${not empty error_update_group}">
                                                <div id="error-modal" class="">
                                                    <!-- Modal content -->
                                                    <div class="modal-content">
                                                        <span class="close">&times;</span>
                                                        <span class="error">${error_update_group}</span>
                                                    </div>
                                                </div>
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
                                                        <th>                                            
                                                            <button type="button" onclick="createGroup()">Create Group</button>
                                                        </th>
                                                    </tr>
                                                </thead>
                                                <c:set var="student_search_value" value="${requestScope.SEARCH_STUDENT}"/>
                                                <c:if test="${not empty student_search_value}">
                                                    <c:forEach var="student" items= "${student_search_value}" varStatus="counter">
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
                                                                    ${student.groupID}
                                                                    <input type="hidden" name="txtGroupID" value="${student.groupID}" />
                                                                </td>
                                                                <td>
                                                                    <c:if test="${student.studentID != sessionScope.STUDENT_ID}">
                                                                        <input type="checkbox" name="chkCreate" id="chkCreate" value="${student.studentID}">
                                                                    </c:if>
                                                                </td>
                                                            </tr>
                                                        </tbody>
                                                    </c:forEach>
                                                </c:if>
                                                <c:if test="${empty student_search_value}">
                                                    <tr>
                                                        <td>No result matches!!!</td>
                                                    </tr>
                                                </c:if> 
                                            </table>
                                        </div>
                                    </c:if>
                                </c:if>
                            </div>
                        </section>
                    </div>
                </main>
            </c:if>
            <c:if test="${role eq 'Lecture'}">
                <main class="main-content">
                    <header>
                        <div class="left-content">
                            <h2>Capstone Project Management</h2>
                            <h2></h2>
                        </div>
                        <div class="right-content">
                            <span >${welcome_name}</span>
                            <!--<span class="fa-solid fa-bell"></span>-->
                            <div class="profile">
                                <img src="./assets/img/ava.jpg" alt="profile-image" />
                                <ul class="profile-link">
                                    <li><a href="profilePage">Profile</a></li>
                                    <li><a href="logoutAction">Logout</a></li>
                                </ul>
                            </div>
                        </div>
                    </header>

                    <div class="container">
                        <h3 class="title">Group List</h3>
                        <section class="list">
                            <div class="table-grid">
                                <div class="list-action">
                                    <div class="list-action-left"></div>
                                    <div class="list-action-right">
                                        <form id="form-search-id" class="form-search" action="">
                                            <input
                                                type="text"
                                                class="search-input"
                                                placeholder="Search Student ID"
                                                name=""
                                                value=""
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
                                <h3>View mode</h3>
                                <div class="table-responsive">
                                    <table id="table-id">
                                        <thead>
                                            <tr>
                                                <th>STT</th>
                                                <th>Group ID</th>
                                                <th>Member ID</th>
                                                <th>Full Name</th>
                                                <th>Role</th>
                                                <th>Topic ID</th> 
                                                <th>Status</th>
                                            </tr>
                                        </thead>
                                        <c:set var="groupList" value="${groupDAO.getGroupMembersByGroupID(grID)}"/>
                                        <c:if test="${not empty groupList}">
                                            <tbody>
                                                <c:forEach var="group" items= "${groupList}" varStatus="counter">
                                                    <tr>
                                                        <td>${counter.count}</td>
                                                        <td>
                                                            ${group.groupId}
                                                            <input type="hidden" name="txtTopicId" value="${group.groupId}" />
                                                        </td>
                                                        <td>
                                                            ${group.memberId}
                                                            <input type="hidden" name="txtName" value="${group.memberId}" />
                                                        </td>
                                                        <td>
                                                            ${group.fullname}
                                                            <input type="hidden" name="txtDeadline" value="${group.fullname}" />
                                                        </td>
                                                        <td>${group.role}</td>
                                                        <td>
                                                            ${group.topicId}
                                                            <input type="hidden" name="txtMajorId" value="${group.topicId}" />
                                                        </td>
                                                        <td>
                                                            ${group.status}
                                                            <input type="hidden" name="topicStatus" value="${group.status}" />
                                                        </td>
                                                    </tr>    
                                                </c:forEach>
                                            </tbody>
                                        </c:if>
                                    </table>
                                </div>
                            </div>
                        </section>
                    </div>
                </main>
            </c:if>

            <script src="./assets/js/main.js"></script>
            <script>
                                                    function createGroup() {
                                                    var list = document.querySelectorAll("#chkCreate");
                                                    var str = "createGroupAction?";
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
//                                                                function createGroup() {
//                                                                const list = document.querySelectorAll("#chkCreate");
//                                                                const str = "createGroupAction?";
//                                                                if (!Array.isArray(list) || list.length === 0) return;
//                                                                if (list.length === 1) {
//                                                                str += "chkCreate" + list[0].value;
//                                                                } else {
//                                                                for (i = 0; i <= list.length; i++) {
//                                                                try {
//                                                                @@ - 143, 8 + 147, 8 @@ function createGroup() {
//                                                                }
//                                                                } catch {}
//                                                                }
//                                                                }
//                                                                window.location.href = str;
//                                                                }
            </script>
        </div>
    </body>




</html>
