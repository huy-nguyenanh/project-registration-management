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
        <link rel="stylesheet" href="./assets/css/reset.css" />
        <link rel="stylesheet" href="./assets/css/main.css" />
        <link rel="stylesheet" href="./assets/css/student.css" />
        <jsp:useBean id="topicDAO" class="manager_dao.impl.TopicInfoDAO" scope="request"></jsp:useBean>
        <jsp:useBean id="uploadDAO" class="manager_dao.impl.UploadFileDAO" scope="request"></jsp:useBean>
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
                        <li>
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
                        <li class="li-active">
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
                        <h3 class="title">Topic List</h3>

                        <section class="list">
                            <div class="table-grid">
                                <div class="list-action">
                                    <div class="list-action-left">
                                        <form id="import-form" action="uploadTopicFileAction">
                                            <label for="import-file">Import</label>
                                            <input type="file" id="import-file" name="file_name" />
                                        </form>
                                    </div>

                                    <c:set var="errors" value="${requestScope.UPLOAD_FILE_ERROR}"/>
                                    <c:if test="${not empty errors}">
                                        <div id="error-modal" class="">
                                            <!-- Modal content -->
                                            <div class="modal-content">
                                                <span class="close">&times;</span>
                                                <c:if test="${not empty errors.uploadFile_False}">
                                                    <span class="error">${errors.uploadFile_False} </span>
                                                </c:if>
                                                <c:if test="${not empty errors.insertToDB_False}">
                                                    <span class="error">${errors.insertToDB_False} </span>
                                                </c:if>
                                            </div>
                                        </div>
                                    </c:if>
                                    <div class="list-action-right">
                                        <form id="form-search-topic" class="form-search" action="searchTopicAction">
                                            <input
                                                type="text"
                                                class="search-input"
                                                placeholder="Search Topic by Major"
                                                name="txtSearchTopic"
                                                value="${param.txtSearchTopic}"
                                                />
                                            <a
                                                href="javascript:{}"
                                                onclick="document.getElementById('form-search-topic').submit();"
                                                ><i class="fas fa-search search-btn"> </i>
                                            </a>
                                        </form>
                                        <c:set var="searchTopicValue" value="${param.txtSearchTopic}"/>
                                    </div>
                                </div>
                                <a id="view-mode" href="">View mode</a>
                                <span>/</span>
                                <a id="edit-mode" href="">Edit mode</a>
                                <c:set var="error_update_topic" value="${requestScope.ERROR_UPDATE_TOPIC}"/>
                                <c:if test="${not empty error_update_topic}">
                                    <div id="error-modal" class="">
                                        <!-- Modal content -->
                                        <div class="modal-content">
                                            <span class="close">&times;</span>
                                            <span class="error">${error_update_topic}</span>
                                        </div>
                                    </div>
                                </c:if>
                                <c:set var="topic_list" value="${topicDAO.loadTopic()}"/>

                                <%-- View Mode --%>
                                <c:if test="${empty searchTopicValue}">
                                    <div class="table-responsive">
                                        <table id="table-id">
                                            <thead>
                                                <tr>
                                                    <th>STT</th>
                                                    <th>Topic ID</th>
                                                    <th>Topic Name</th>
                                                    <th>Deadline</th>
                                                    <th>Category</th>
                                                    <th>Major ID</th>
                                                    <th>Status</th>
                                                    <th>Lecture ID</th>
                                                    <th>Group ID</th>
                                                </tr>
                                            </thead>
                                            <c:if test="${not empty topic_list}">
                                                <tbody>
                                                    <c:forEach var="topic" items= "${topic_list}" varStatus="counter">
                                                        <tr>
                                                            <td>${counter.count}</td>
                                                            <td>
                                                                ${topic.topicID}
                                                            </td>
                                                            <td>
                                                                ${topic.topicName}
                                                            </td>
                                                            <td>
                                                                ${topic.deadline}
                                                            </td>
                                                            <td>${topic.category}</td>
                                                            <td>${topic.majorID}</td>
                                                            <td>
                                                                ${topic.status}
                                                            </td>
                                                            <td>
                                                                ${topic.lectureID}
                                                            </td>
                                                            <td>
                                                                ${topic.groupID}
                                                            </td>
                                                        </tr>
                                                    </c:forEach>
                                                </tbody>
                                            </c:if>
                                        </table>
                                    </div>
                                </c:if>
                                <c:if test="${not empty searchTopicValue}">
                                    <div class="table-responsive">
                                        <table id="table-id">
                                            <thead>
                                                <tr>
                                                    <th>STT</th>
                                                    <th>Topic ID</th>
                                                    <th>Topic Name</th>
                                                    <th>Deadline</th>
                                                    <th>Category</th>
                                                    <th>Status</th>
                                                    <th>Major ID</th>
                                                    <th>Lecture ID</th>
                                                    <th>Group ID</th>
                                                </tr>
                                            </thead>
                                            <c:set var="topic_search_value" value="${requestScope.SEARCH_TOPIC}"/>
                                            <c:if test="${not empty topic_search_value}">
                                                <tbody>
                                                    <c:forEach var="topic" items= "${topic_search_value}" varStatus="counter">
                                                        <tr>
                                                            <td>${counter.count}</td>
                                                            <td>
                                                                ${topic.topicID}
                                                            </td>
                                                            <td>
                                                                ${topic.topicName}
                                                            </td>
                                                            <td>
                                                                <%--${topic.deadline}--%>
                                                            </td>
                                                            <td>${topic.category}</td>
                                                            <td>${topic.majorID}</td>
                                                            <td>
                                                                ${topic.status}
                                                            </td>
                                                            <td>
                                                                ${topic.lectureID}
                                                            </td>
                                                            <td>
                                                                ${topic.groupID}
                                                            </td>
                                                        </tr>
                                                    </c:forEach>
                                                </tbody>
                                            </c:if>
                                            <c:if test="${empty topic_search_value}">
                                                <tr>
                                                    <td>No result matches!!!</td>
                                                </tr>
                                            </c:if> 
                                        </table>
                                    </div>
                                </c:if>
                                <%-- Edit Mode --%>
                                <c:if test="${empty searchTopicValue}">
                                    <div class="table-responsive2" style="display: none">
                                        <table id="table-id">
                                            <thead>
                                                <tr>
                                                    <th>STT</th>
                                                    <th>Topic ID</th>
                                                    <th>Topic Name</th>
                                                    <th>Deadline</th>
                                                    <th>Category</th>
                                                    <th>Major ID</th>
                                                    <th>Status</th>
                                                    <th>Lecture ID</th>
                                                    <th>Group ID</th>
                                                    <th>Update</th>
                                                </tr>
                                            </thead>
                                            <c:if test="${not empty topic_list}">
                                                <tbody>
                                                    <c:forEach var="topic" items= "${topic_list}" varStatus="counter">
                                                    <form action="adminUpdateTopicInfoAction" enctype="multipart/form-data">
                                                        <tr>
                                                            <td>${counter.count}</td>
                                                            <td>
                                                                ${topic.topicID}
                                                                <input type="hidden" name="txtTopicId" value="${topic.topicID}" />
                                                            </td>
                                                            <td>
                                                                ${topic.topicName}
                                                            </td>
                                                            <td>
                                                                ${topic.deadline}
                                                                <input type="hidden" name="txtDeadline" value="${topic.deadline}" />
                                                            </td>
                                                            <td>${topic.category}</td>
                                                            <td>${topic.majorID}</td>
                                                            <td>
                                                                ${topic.status}
                                                                <input type="hidden" name="topicStatus" value="${topic.status}" />
                                                            </td>
                                                            <td>
                                                                ${topic.lectureID}
                                                                <input type="hidden" name="txtLectureId" value="${topic.lectureID}" />
                                                            </td>
                                                            <td>
                                                                <input type="text" name="txtGroupId" value="${topic.groupID}" />
                                                            </td>
                                                            <td>
                                                                <button
                                                                    type="submit"
                                                                    class="myBtn"
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
                                <c:if test="${not empty searchTopicValue}">
                                    <div class="table-responsive2" style="display: none" >
                                        <table id="table-id">
                                            <thead>
                                                <tr>
                                                    <th>STT</th>
                                                    <th>Topic ID</th>
                                                    <th>Topic Name</th>
                                                    <th>Deadline</th>
                                                    <th>Category</th>
                                                    <th>Status</th>
                                                    <th>Major ID</th>
                                                    <th>Lecture ID</th>
                                                    <th>Group ID</th>
                                                    <th>Update</th>
                                                </tr>
                                            </thead>
                                            <c:set var="topic_search_value" value="${requestScope.SEARCH_TOPIC}"/>
                                            <c:if test="${not empty topic_search_value}">
                                                <tbody>
                                                    <c:forEach var="topic" items= "${topic_search_value}" varStatus="counter">
                                                    <form action="adminUpdateTopicInfoAction" enctype="multipart/form-data">
                                                        <tr>
                                                            <td>${counter.count}</td>
                                                            <td>
                                                                ${topic.topicID}
                                                                <input type="hidden" name="txtTopicId" value="${topic.topicID}" />
                                                            </td>
                                                            <td>
                                                                ${topic.topicName}
                                                            </td>
                                                            <td>
                                                                <%--${topic.deadline}--%>
                                                                <input type="text" name="txtDeadline" value="${topic.deadline}" />
                                                            </td>
                                                            <td>${topic.category}</td>
                                                            <td>${topic.majorID}</td>
                                                            <td>
                                                                ${topic.status}
                                                                <input type="hidden" name="topicStatus" value="${topic.status}" />
                                                            </td>
                                                            <td>
                                                                ${topic.lectureID}
                                                                <input type="hidden" name="txtLectureId" value="${topic.lectureID}" />
                                                            </td>
                                                            <td>
                                                                <input type="text" name="txtGroupId" value="${topic.groupID}" />
                                                            </td>
                                                            <td>
                                                                <button
                                                                    type="submit"
                                                                    class="myBtn"
                                                                    >
                                                                    Update
                                                                </button>
                                                            </td>
                                                        </tr>
                                                    </form>
                                                </c:forEach>
                                                </tbody>
                                            </c:if>
                                            <c:if test="${empty topic_search_value}">
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
                    <div class="container">
                        <h3 class="title">Topic List</h3>
                        <section class="list">
                            <div class="table-grid">
                                <div class="list-action">
                                    <div class="list-action-left"></div>
                                    <div class="list-action-right">
                                        <form id="form-search-topic" class="form-search" action="searchTopicAction">
                                            <input
                                                type="text"
                                                class="search-input"
                                                placeholder="Search Topic"
                                                name="txtSearchTopic"
                                                value="${param.txtSearchTopic}"
                                                />
                                            <a
                                                href="javascript:{}"
                                                onclick="document.getElementById('form-search-topic').submit();"
                                                ><i class="fas fa-search search-btn"> </i>
                                            </a>
                                        </form>
                                        <c:set var="searchTopicValue" value="${param.txtSearchTopic}"/>
                                    </div>
                                </div>
                                <h3>List</h3>
                                <c:set var="error_update_topic" value="${requestScope.ERROR_UPDATE_TOPIC}"/>
                                <c:if test="${not empty error_update_topic}">
                                    <div id="error-modal" class="">
                                        <!-- Modal content -->
                                        <div class="modal-content">
                                            <span class="close">&times;</span>
                                            <span class="error">${error_update_topic}</span>
                                        </div>
                                    </div>
                                </c:if>
                                <c:set var="errors_create_group" value="${requestScope.CREATE_GROUP_ERROR}"/>
                                <c:if test="${not empty errors_create_group}">
                                    <div id="error-modal" class="">
                                        <!-- Modal content -->
                                        <div class="modal-content">
                                            <span class="close">&times;</span>
                                            <span class="error">${errors_create_group}</span>
                                        </div>
                                    </div>
                                </c:if>
                                <c:if test="${empty searchTopicValue}">
                                    <div class="table-responsive">
                                        <table id="table-id">
                                            <thead>
                                                <tr>
                                                    <th>STT</th>
                                                    <th>Topic ID</th>
                                                    <th>Topic Name</th>
                                                    <th>Deadline</th>
                                                    <th>Category</th>
                                                    <th>Major ID</th>
                                                    <th>Status</th>
                                                    <th>Lecture ID</th>
                                                    <th>Group ID</th>
                                                    <th>Action</th>
                                                </tr>
                                            </thead>
                                            <c:set var="topic_list" value="${topicDAO.loadTopic()}"/>
                                            <c:if test="${not empty topic_list}">
                                                <tbody>
                                                    <c:forEach var="topic" items= "${topic_list}" varStatus="counter">
                                                    <form action="studentChooseTopicAction" enctype="multipart/form-data">
                                                        <tr>
                                                            <td>${counter.count}</td>
                                                            <td>
                                                                ${topic.topicID}
                                                                <input type="hidden" name="txtTopicId" value="${topic.topicID}" />
                                                            </td>
                                                            <td>
                                                                ${topic.topicName}
                                                            </td>
                                                            <td>
                                                                ${topic.deadline}
                                                                <input type="hidden" name="txtDeadline" value="${topic.deadline}" />
                                                            </td>
                                                            <td>${topic.category}</td>
                                                            <td>${topic.majorID}</td>
                                                            <td>
                                                                ${topic.status}
                                                                <input type="hidden" name="topicStatus" value="${topic.status}" />
                                                            </td>
                                                            <td>
                                                                ${topic.lectureID}
                                                                <input type="hidden" name="txtLectureId" value="${topic.lectureID}" />
                                                            </td>
                                                            <td>
                                                                ${topic.groupID}
                                                                <input type="hidden" name="txtGroupId" value="${topic.groupID}" />
                                                                <input type="hidden" name="textGroupId" id="textGroupId" value="${sessionScope.STUDENT_ID}">
                                                            </td>
                                                            <td>
                                                                <button class="myBtn" type="submit">Choose Topic</button> 
                                                            </td>
                                                        </tr>
                                                    </form>
                                                </c:forEach>
                                                </tbody>
                                            </c:if>

                                        </table>
                                    </div>
                                </c:if>
                                <c:if test="${not empty searchTopicValue}">
                                    <div class="table-responsive">
                                        <table id="table-id">
                                            <thead>
                                                <tr>
                                                    <th>STT</th>
                                                    <th>Topic ID</th>
                                                    <th>Topic Name</th>
                                                    <th>Deadline</th>
                                                    <th>Category</th>
                                                    <th>Status</th>
                                                    <th>Major ID</th>
                                                    <th>Lecture ID</th>
                                                    <th>Group ID</th>
                                                    <th>Action</th>
                                                </tr>
                                            </thead>
                                            <c:set var="topic_search_value" value="${requestScope.SEARCH_TOPIC}"/>
                                            <c:if test="${not empty topic_search_value}">
                                                <tbody>
                                                    <c:forEach var="topic" items= "${topic_search_value}" varStatus="counter">
                                                    <form action="studentChooseTopicAction" enctype="multipart/form-data">
                                                        <tr>
                                                            <td>${counter.count}</td>
                                                            <td>
                                                                ${topic.topicID}
                                                                <input type="hidden" name="txtTopicId" value="${topic.topicID}" />
                                                            </td>
                                                            <td>
                                                                ${topic.topicName}
                                                            </td>
                                                            <td>
                                                                <%--${topic.deadline}--%>
                                                                <input type="text" name="txtDeadline" value="${topic.deadline}" />
                                                            </td>
                                                            <td>${topic.category}</td>
                                                            <td>${topic.majorID}</td>
                                                            <td>
                                                                ${topic.status}
                                                                <input type="hidden" name="topicStatus" value="${topic.status}" />
                                                            </td>
                                                            <td>
                                                                ${topic.lectureID}
                                                                <input type="hidden" name="txtLectureId" value="${topic.lectureID}" />
                                                            </td>
                                                            <td>
                                                                ${topic.groupID}
                                                                <input type="hidden" name="txtGroupId" value="${topic.groupID}" />
                                                            </td>
                                                            <td>
                                                                <button class="myBtn" type="button" type="submit">Choose Topic</button> 
                                                                <input type="hidden" name="textGroupId" id="textGroupId" value="${sessionScope.STUDENT_ID}">
                                                            </td>
                                                        </tr>
                                                    </form>
                                                </c:forEach>
                                                </tbody>
                                            </c:if>
                                            <c:if test="${empty topic_search_value}">
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
                <script>
                    function chooseTopic() {
                    var list = document.querySelectorAll("#chkChoose");
                    var groupId = document.querySelector("#textGroupId").value;
                    var str = "studentChooseTopicAction?groupId=" + groupId + "&";
                    if (list.length === 1)
                    {
                    str += "chkChoose=" + list[0].value;
                    }
                    else
                    {
                    for (i = 0; i <= list.length; i++) {
                    try {
                    if (list[i].checked) {
                    if (i === 0) {
                    str += "chkChoose=" + list[0].value;
                    } else {
                    str += "&chkChoose=" + list[i].value;
                    }
                    }
                    } catch {}
                    }
                    }

                    window.location.href = str;
                    }
                </script>     
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
                                    <li><a href="profile.html">Profile</a></li>
                                    <li><a href="logoutAction">Logout</a></li>
                                </ul>
                            </div>
                        </div>
                    </header>
                    <div class="container">
                        <h3 class="title">Topic List</h3>
                        <section class="list">
                            <div class="table-grid">
                                <div class="list-action">
                                    <div class="list-action-left"></div>
                                    <div class="list-action-right">
                                        <form id="form-search-topic" class="form-search" action="">
                                            <input
                                                type="text"
                                                class="search-input"
                                                placeholder="Search Topic"
                                                name="txtSearchTopic"
                                                value="${param.txtSearchTopic}"
                                                />
                                            <a
                                                href="javascript:{}"
                                                onclick="document.getElementById('form-search-topic').submit();"
                                                ><i class="fas fa-search search-btn"> </i>
                                            </a>
                                        </form>
                                    </div>
                                </div>
                                <h3>View mode</h3>
                                <div class="table-responsive">
                                    <table id="table-id">
                                        <thead>
                                            <tr>
                                                <th>STT</th>
                                                <th>Topic ID</th>
                                                <th>Topic Name</th>
                                                <th>Deadline</th>
                                                <th>Category</th>
                                                <th>Major ID</th>
                                                <th>Status</th>
                                                <th>Lecture ID</th>
                                                <th>Group ID</th>
                                            </tr>
                                        </thead>
                                        <c:set var="topic_list" value="${topicDAO.getTopicInfoByLecID(lecID)}"/>
                                        <c:if test="${not empty topic_list}">
                                            <tbody>
                                                <c:forEach var="topic" items= "${topic_list}" varStatus="counter">
                                                    <tr>
                                                        <td>${counter.count}</td>
                                                        <td>
                                                            ${topic.topicID}
                                                            <input type="hidden" name="txtTopicId" value="${topic.topicID}" />
                                                        </td>
                                                        <td>
                                                            ${topic.topicName}
                                                        </td>
                                                        <td>
                                                            ${topic.deadline}
                                                            <input type="hidden" name="txtDeadline" value="${topic.deadline}" />
                                                        </td>
                                                        <td>${topic.category}</td>
                                                        <td>${topic.majorID}</td>
                                                        <td>
                                                            ${topic.status}
                                                            <input type="hidden" name="topicStatus" value="${topic.status}" />
                                                        </td>
                                                        <td>
                                                            ${topic.lectureID}
                                                            <input type="hidden" name="txtLectureId" value="${topic.lectureID}" />
                                                        </td>
                                                        <td>
                                                            ${topic.groupID}
                                                            <input type="hidden" name="txtGroupId" value="${topic.groupID}" />
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
        </div>
        <script src="./assets/js/main.js"></script>
    </body>

</html>
