<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8" />
        <meta http-equiv="X-UA-Compatible" content="IE=edge" />
        <meta name="viewport" content="width=device-width, initial-scale=1.0" />
        <title>Capstone Project Management</title>
         <!-- Bootstrap CSS -->
    <link
    href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css"
    rel="stylesheet"
    integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3"
    crossorigin="anonymous"
  />
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
                                    
                                    <li><a href="logoutAction">Logout</a></li>
                                </ul>
                            </div>
                        </div>
                    </header>

                    <div class="container-fluid">
                        <div
                          id="carouselExampleCaptions"
                          class="carousel slide"
                          data-bs-ride="carousel"
                        >
                          <div class="carousel-indicators">
                            <button
                              type="button"
                              data-bs-target="#carouselExampleCaptions"
                              data-bs-slide-to="0"
                              class="active"
                              aria-current="true"
                              aria-label="Slide 1"
                            ></button>
                            <button
                              type="button"
                              data-bs-target="#carouselExampleCaptions"
                              data-bs-slide-to="1"
                              aria-label="Slide 2"
                            ></button>
                            <button
                              type="button"
                              data-bs-target="#carouselExampleCaptions"
                              data-bs-slide-to="2"
                              aria-label="Slide 3"
                            ></button>
                          </div>
                          <div class="carousel-inner">
                            <div class="carousel-item my-carsousel active">
                              <img
                                src="https://career-hcmuni.fpt.edu.vn/attachments/2021/05/a.jpg"
                                class="d-block w-100"
                                alt="..."
                              />
                            </div>
              
                            <div class="carousel-item my-carsousel">
                              <img
                                src="https://career-hcmuni.fpt.edu.vn/attachments/2021/05/R687652e05389f52d753afafd1f0c4b43.jpg"
                                class="d-block w-100"
                                alt="..."
                              />
                            </div>
              
                            <div class="carousel-item my-carsousel">
                              <img
                                src="https://chamsockhachang.com/wp-content/uploads/truong-dai-hoc-FPT-ho-chi-minh.jpg"
                                class="d-block w-100"
                                alt="..."
                              />
                            </div>
                          </div>
                          <button
                            class="carousel-control-prev"
                            type="button"
                            data-bs-target="#carouselExampleCaptions"
                            data-bs-slide="prev"
                          >
                            <span
                              class="carousel-control-prev-icon"
                              aria-hidden="true"
                            ></span>
                            <span class="visually-hidden">Previous</span>
                          </button>
                          <button
                            class="carousel-control-next"
                            type="button"
                            data-bs-target="#carouselExampleCaptions"
                            data-bs-slide="next"
                          >
                            <span
                              class="carousel-control-next-icon"
                              aria-hidden="true"
                            ></span>
                            <span class="visually-hidden">Next</span>
                          </button>
                        </div>
                      </div>
              
                      <footer>
                        <div class="">
                          <img src="https://haitrieu.com/wp-content/uploads/2021/10/Logo-Dai-hoc-FPT.png" alt="">
                          <!-- <h2 class="title">
                            PHÂN HIỆU TRƯỜNG ĐẠI HỌC FPT TẠI THÀNH PHỐ HỒ CHÍ MINH
                          </h2> -->
                        </div>
              
                        <section class="link">
                          <ul>
                            <li>Phone</li>
                            <li>0904000000</li>
                          </ul>
                          <ul>
                            <li>Email</li>
                            <li>122345@gmail.com</li>
                          </ul>
                          <ul>
                            <li>Address</li>
                            <li>123 duong 567 quan 5467 tp 909</li>
                          </ul>
                          <ul>
                            <li>Social</li>
                            <li>
                              <a href=""><i class="fa-brands fa-facebook"></i></a>
                              <a href=""><i class="fa-brands fa-instagram"></i></a>
                              <a href=""><i class="fa-brands fa-twitter"></i></a>
                              <a href=""><i class="fa-brands fa-youtube"></i></a>
                            </li>
                          </ul>
                        </section>
                      </footer>
                </main>
            </div>
            <script
            src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"
            integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p"
            crossorigin="anonymous"
          ></script>
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

                    <div class="container-fluid">
                        <div
                          id="carouselExampleCaptions"
                          class="carousel slide"
                          data-bs-ride="carousel"
                        >
                          <div class="carousel-indicators">
                            <button
                              type="button"
                              data-bs-target="#carouselExampleCaptions"
                              data-bs-slide-to="0"
                              class="active"
                              aria-current="true"
                              aria-label="Slide 1"
                            ></button>
                            <button
                              type="button"
                              data-bs-target="#carouselExampleCaptions"
                              data-bs-slide-to="1"
                              aria-label="Slide 2"
                            ></button>
                            <button
                              type="button"
                              data-bs-target="#carouselExampleCaptions"
                              data-bs-slide-to="2"
                              aria-label="Slide 3"
                            ></button>
                          </div>
                          <div class="carousel-inner">
                            <div class="carousel-item my-carsousel active">
                              <img
                                src="https://career-hcmuni.fpt.edu.vn/attachments/2021/05/a.jpg"
                                class="d-block w-100"
                                alt="..."
                              />
                            </div>
              
                            <div class="carousel-item my-carsousel">
                              <img
                                src="https://career-hcmuni.fpt.edu.vn/attachments/2021/05/R687652e05389f52d753afafd1f0c4b43.jpg"
                                class="d-block w-100"
                                alt="..."
                              />
                            </div>
              
                            <div class="carousel-item my-carsousel">
                              <img
                                src="https://chamsockhachang.com/wp-content/uploads/truong-dai-hoc-FPT-ho-chi-minh.jpg"
                                class="d-block w-100"
                                alt="..."
                              />
                            </div>
                          </div>
                          <button
                            class="carousel-control-prev"
                            type="button"
                            data-bs-target="#carouselExampleCaptions"
                            data-bs-slide="prev"
                          >
                            <span
                              class="carousel-control-prev-icon"
                              aria-hidden="true"
                            ></span>
                            <span class="visually-hidden">Previous</span>
                          </button>
                          <button
                            class="carousel-control-next"
                            type="button"
                            data-bs-target="#carouselExampleCaptions"
                            data-bs-slide="next"
                          >
                            <span
                              class="carousel-control-next-icon"
                              aria-hidden="true"
                            ></span>
                            <span class="visually-hidden">Next</span>
                          </button>
                        </div>
                      </div>
              
                      <footer>
                        <div class="">
                          <img src="https://haitrieu.com/wp-content/uploads/2021/10/Logo-Dai-hoc-FPT.png" alt="">
                          <!-- <h2 class="title">
                            PHÂN HIỆU TRƯỜNG ĐẠI HỌC FPT TẠI THÀNH PHỐ HỒ CHÍ MINH
                          </h2> -->
                        </div>
              
                        <section class="link">
                          <ul>
                            <li>Phone</li>
                            <li>0904000000</li>
                          </ul>
                          <ul>
                            <li>Email</li>
                            <li>122345@gmail.com</li>
                          </ul>
                          <ul>
                            <li>Address</li>
                            <li>123 duong 567 quan 5467 tp 909</li>
                          </ul>
                          <ul>
                            <li>Social</li>
                            <li>
                              <a href=""><i class="fa-brands fa-facebook"></i></a>
                              <a href=""><i class="fa-brands fa-instagram"></i></a>
                              <a href=""><i class="fa-brands fa-twitter"></i></a>
                              <a href=""><i class="fa-brands fa-youtube"></i></a>
                            </li>
                          </ul>
                        </section>
                      </footer>
                </main>
            </div>
            <script
            src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"
            integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p"
            crossorigin="anonymous"
          ></script>
            <script src="./assets/js/main.js"></script>
        </body>
    </c:if>
    <c:if test="${role eq 'Lecture'}">
        <body>
            <c:set var="lecID" value="${sessionScope.LECTURE_ID}"/>
            <c:set var="grID" value="${sessionScope.LECTURE_GROUP_ID}"/>
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
                            <!--                            <li>
                                                            <a href="lecturePage">
                                                                <span class="fa-solid fa-chalkboard-user"> </span>
                                                                <span>Lecture</span>
                                                            </a>
                                                        </li>-->
                            <li>
                                <a href="topicPage">
                                    <span class="fa-solid fa-shapes"> </span>
                                    <span>Topic</span>
                                </a>
                            </li>
                            <li>
                                <a href="notifyPage">
                                    <span class="fa-solid fa-shapes"> </span>
                                    <span>Notify</span>
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

                    <div class="container-fluid">
                        <div
                          id="carouselExampleCaptions"
                          class="carousel slide"
                          data-bs-ride="carousel"
                        >
                          <div class="carousel-indicators">
                            <button
                              type="button"
                              data-bs-target="#carouselExampleCaptions"
                              data-bs-slide-to="0"
                              class="active"
                              aria-current="true"
                              aria-label="Slide 1"
                            ></button>
                            <button
                              type="button"
                              data-bs-target="#carouselExampleCaptions"
                              data-bs-slide-to="1"
                              aria-label="Slide 2"
                            ></button>
                            <button
                              type="button"
                              data-bs-target="#carouselExampleCaptions"
                              data-bs-slide-to="2"
                              aria-label="Slide 3"
                            ></button>
                          </div>
                          <div class="carousel-inner">
                            <div class="carousel-item my-carsousel active">
                              <img
                                src="https://career-hcmuni.fpt.edu.vn/attachments/2021/05/a.jpg"
                                class="d-block w-100"
                                alt="..."
                              />
                            </div>
              
                            <div class="carousel-item my-carsousel">
                              <img
                                src="https://career-hcmuni.fpt.edu.vn/attachments/2021/05/R687652e05389f52d753afafd1f0c4b43.jpg"
                                class="d-block w-100"
                                alt="..."
                              />
                            </div>
              
                            <div class="carousel-item my-carsousel">
                              <img
                                src="https://chamsockhachang.com/wp-content/uploads/truong-dai-hoc-FPT-ho-chi-minh.jpg"
                                class="d-block w-100"
                                alt="..."
                              />
                            </div>
                          </div>
                          <button
                            class="carousel-control-prev"
                            type="button"
                            data-bs-target="#carouselExampleCaptions"
                            data-bs-slide="prev"
                          >
                            <span
                              class="carousel-control-prev-icon"
                              aria-hidden="true"
                            ></span>
                            <span class="visually-hidden">Previous</span>
                          </button>
                          <button
                            class="carousel-control-next"
                            type="button"
                            data-bs-target="#carouselExampleCaptions"
                            data-bs-slide="next"
                          >
                            <span
                              class="carousel-control-next-icon"
                              aria-hidden="true"
                            ></span>
                            <span class="visually-hidden">Next</span>
                          </button>
                        </div>
                      </div>
              
                      <footer>
                        <div class="">
                          <img src="https://haitrieu.com/wp-content/uploads/2021/10/Logo-Dai-hoc-FPT.png" alt="">
                          <!-- <h2 class="title">
                            PHÂN HIỆU TRƯỜNG ĐẠI HỌC FPT TẠI THÀNH PHỐ HỒ CHÍ MINH
                          </h2> -->
                        </div>
              
                        <section class="link">
                          <ul>
                            <li>Phone</li>
                            <li>0904000000</li>
                          </ul>
                          <ul>
                            <li>Email</li>
                            <li>122345@gmail.com</li>
                          </ul>
                          <ul>
                            <li>Address</li>
                            <li>123 duong 567 quan 5467 tp 909</li>
                          </ul>
                          <ul>
                            <li>Social</li>
                            <li>
                              <a href=""><i class="fa-brands fa-facebook"></i></a>
                              <a href=""><i class="fa-brands fa-instagram"></i></a>
                              <a href=""><i class="fa-brands fa-twitter"></i></a>
                              <a href=""><i class="fa-brands fa-youtube"></i></a>
                            </li>
                          </ul>
                        </section>
                      </footer>
                </main>
            </div>
            <script
            src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"
            integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p"
            crossorigin="anonymous"
          ></script>
            <script src="./assets/js/main.js"></script>
        </body>
    </c:if>

</html>
