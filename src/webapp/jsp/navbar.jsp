<%@ page language="java" pageEncoding="UTF-8" contentType="text/html" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<body
        style="background-image: url('https://images.squarespace-cdn.com/content/51a4b8eae4b0643b1ca8bd19/1401821488643-QGUPJOP9PY9SQUD4JNG2/RobynBanner.jpg?content-type=image%2Fjpeg');
                background-size: cover; background-repeat: no-repeat">
<nav class="navbar navbar-default">
    <div class="container-fluid">
        <div class="navbar-header">
            <a class="navbar-brand" href="/">Otomoto</a>
        </div>
        <c:if test="${sessionScope.user != null}">
            <ul class="nav navbar-nav">
                <li class="active"><a href="#">Home</a></li>
                <li><a href="createAd">CREATE AD</a></li>
                <li><a href="all">ALL ADS</a></li>
                <li><a href="my">MY ADS</a></li>
                <li><a href="observed">OBSERVED ADS</a></li>
                <c:if test="${sessionScope.user.email.equals('admin@admin')}">
                    <li><a href="users">USERS</a></li>
                </c:if>
            </ul>
            <div class="navbar-header" style="float: right">
                <div class="navbar-header">
                    Welcome ${user.name}!
                </div>
                <form method="post" action="logout">
                    <input type="submit" value="LOGOUT">
                </form>
            </div>
        </c:if>
        <c:if test="${sessionScope.user == null}">

            <ul class="nav navbar-nav">
                <li><a href="register">Register</a></li>
            </ul>

            <div class="navbar-header" style="float: right">
                <div class="navbar-header">
                    <a class="navbar-brand" href="login">Login</a>
                </div>
            </div>
        </c:if>
    </div>
</nav>