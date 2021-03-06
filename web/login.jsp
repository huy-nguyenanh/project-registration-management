<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8" />
        <meta http-equiv="X-UA-Compatible" content="IE=edge" />
        <meta name="viewport" content="width=device-width, initial-scale=1.0" />
        <link
            rel="stylesheet"
            href="https://cdnjs.cloudflare.com/ajax/libs/meyer-reset/2.0/reset.min.css"
            integrity="sha512-NmLkDIU1C/C88wi324HBc+S2kLhi08PN5GDeUVVVC/BVt/9Izdsc9SVeVfA1UZbY3sHUlDSyRXhCzHfr6hmPPw=="
            crossorigin="anonymous"
            referrerpolicy="no-referrer"
            />
        <link
            rel="stylesheet"
            href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css"
            />
        <link
            rel="stylesheet"
            href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.3.0/font/bootstrap-icons.css"
            />
        <link rel="stylesheet" href="./assets/css/login.css" />
        <title>Login Page</title>

    </head>
    <body>
        <c:if test="${sessionScope.ROLE != null }">
            <c:if test="${not empty sessionScope.ROLE}">
                <c:redirect url="homePage"/>
            </c:if>
        </c:if>
        <div class="admin">
            <div class="admin__login">
                <h1 class="admin__login-title">Login</h1>
                <form action="loginAction" class="login-form" method="POST">
                    <c:set var="errors" value="${requestScope.LOGIN_ERRORS}" />
                    <label for="email" class="login-label">Email</label>
                    <input
                        type="text"
                        name="email"
                        id="email"
                        class="login-input"
                        required
                        />
                    <label for="password" class="login-label">Password</label>
                    <input
                        type="password"
                        name="password"
                        id="password"
                        class="login-input"
                        required
                        />
                    <c:if test="${not empty errors.accountOrPasswordIncorrect}">
                        <font color ="red">
                        ${errors.accountOrPasswordIncorrect}
                        </font><br/>
                    </c:if>
                    <button
                        class="login-submit"
                        type="submit"
                        name="action"
                        value="Login"
                        >
                        Login
                    </button>
                </form>
                <!-- <div class="admin__login-orText">
                    <span class="">Or</span>
                </div>
                <a href="#" class="admin__login-social">
                    <i class="fa fa-google login_social__icon"></i>
                    <span class="admin__login-social__text">Login with google </span>
                </a> -->
            </div>
        </div>

    </body>
</html>

