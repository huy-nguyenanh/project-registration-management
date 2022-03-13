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
        
<!--        <script type = "text/javascript" >
            window.history.pushState(null, null, window.location.href);
            window.onpopstate = function () {
                window.history.go(1);
            };
        </script>-->

        <link rel="stylesheet" href="./assets/css/reset.css" />
        <link rel="stylesheet" href="./assets/css/main.css">
        <link rel="stylesheet" href="./assets/css/adminHome.css" />
        <c:set var="role" value="${sessionScope.ROLE}"/>
        <c:set var="welcome_name" value="${sessionScope.WELCOME_NAME}"/>
    </head>
    <c:if test="${sessionScope.ROLE == null}">
        <c:redirect url="loginPage"></c:redirect>
    </c:if>
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
                            <li class="li-active">
                                <a href="#">
                                    <span class="fa-solid fa-house">
                                        <!-- <i class="fa-solid fa-house"></i> -->
                                    </span>
                                    <span>Home</span>
                                </a>
                            </li>
                            <li>
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
                        <h3 class="title">Overview</h3>
                        <div class="card-list">
                            <div class="card-item">
                                <div class="card-content">
                                    <span class="fa-solid fa-user-group"></span>
                                    <div class="">
                                        <h5>Group created</h5>
                                        <h4>25</h4>
                                    </div>
                                </div>
                            </div>
                            <div class="card-item">
                                <div class="card-content">
                                    <span class="fa-solid fa-shapes"></span>
                                    <div class="">
                                        <h5>Topic created</h5>
                                        <h4>25</h4>
                                    </div>
                                </div>
                            </div>
                            <div class="card-item">
                                <div class="card-content">
                                    <span class="fa-solid fa-user-plus"></span>
                                    <div class="">
                                        <h5>People created</h5>
                                        <h4>25</h4>
                                    </div>
                                </div>
                            </div>
                            <div class="card-item">
                                <div class="card-content">
                                    <span class="fa-solid fa-calendar-check"></span>
                                    <div class="">
                                        <h5>Deadline</h5>
                                        <h4>25/2/2202</h4>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </main>
            </div>
            <script src="./assets/js/main.js"></script>
        </body>
    </c:if>
    <c:if test="${role eq 'Student'}">
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
                            <li class="li-active">
                                <a href="#">
                                    <span class="fa-solid fa-house">
                                        <!-- <i class="fa-solid fa-house"></i> -->
                                    </span>
                                    <span>Home</span>
                                </a>
                            </li>
                            <li>
                                <a href="studentPage">
                                    <span class="fa-solid fa-graduation-cap"> </span>
                                    <span>Student</span>
                                </a>
                            </li>
                            <li>
                                <a href="lecture.html">
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
                        <h3 class="title">Overview</h3>
                        <div class="card-list">
                            <div class="card-item">
                                <div class="card-content">
                                    <span class="fa-solid fa-user-group"></span>
                                    <div class="">
                                        <h5>Group created</h5>
                                        <h4>25</h4>
                                    </div>
                                </div>
                            </div>
                            <div class="card-item">
                                <div class="card-content">
                                    <span class="fa-solid fa-shapes"></span>
                                    <div class="">
                                        <h5>Topic created</h5>
                                        <h4>25</h4>
                                    </div>
                                </div>
                            </div>
                            <div class="card-item">
                                <div class="card-content">
                                    <span class="fa-solid fa-user-plus"></span>
                                    <div class="">
                                        <h5>People created</h5>
                                        <h4>25</h4>
                                    </div>
                                </div>
                            </div>
                            <div class="card-item">
                                <div class="card-content">
                                    <span class="fa-solid fa-calendar-check"></span>
                                    <div class="">
                                        <h5>Deadline</h5>
                                        <h4>25/2/2202</h4>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </main>
            </div>
            <script src="./assets/js/main.js"></script>
        </body>
    </c:if>
    <c:if test="${role eq 'Lecture'}">
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
                            <li class="li-active">
                                <a href="#">
                                    <span class="fa-solid fa-house">
                                        <!-- <i class="fa-solid fa-house"></i> -->
                                    </span>
                                    <span>Home</span>
                                </a>
                            </li>
                            <li>
                                <a href="studentPage">
                                    <span class="fa-solid fa-graduation-cap"> </span>
                                    <span>Student</span>
                                </a>
                            </li>
                            <li>
                                <a href="lecture.html">
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
                            <span >${welcome_name}</span>
                            <span class="fa-solid fa-bell"></span>
                            <div class="profile">
                                <img src="./assets/img/ava.jpg" alt="profile-image" />
                                <ul class="profile-link">
                                    <li><a href="profile.html">Profile</a></li>
                                    <li><a href="#">Logout</a></li>
                                </ul>
                            </div>
                        </div>
                    </header>

                    <div class="container">
                        <h3 class="title">Overview</h3>
                        <div class="card-list">
                            <div class="card-item">
                                <div class="card-content">
                                    <span class="fa-solid fa-user-group"></span>
                                    <div class="">
                                        <h5>Group created</h5>
                                        <h4>25</h4>
                                    </div>
                                </div>
                            </div>
                            <div class="card-item">
                                <div class="card-content">
                                    <span class="fa-solid fa-shapes"></span>
                                    <div class="">
                                        <h5>Topic created</h5>
                                        <h4>25</h4>
                                    </div>
                                </div>
                            </div>
                            <div class="card-item">
                                <div class="card-content">
                                    <span class="fa-solid fa-user-plus"></span>
                                    <div class="">
                                        <h5>People created</h5>
                                        <h4>25</h4>
                                    </div>
                                </div>
                            </div>
                            <div class="card-item">
                                <div class="card-content">
                                    <span class="fa-solid fa-calendar-check"></span>
                                    <div class="">
                                        <h5>Deadline</h5>
                                        <h4>25/2/2202</h4>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </main>
            </div>
            <script src="./assets/js/main.js"></script>
        </body>
    </c:if>

</html>
