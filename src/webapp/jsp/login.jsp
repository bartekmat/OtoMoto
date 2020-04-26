<%@ page language="java" pageEncoding="UTF-8" contentType="text/html" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<jsp:include page="header.jsp"/>
<jsp:include page="navbar.jsp"/>


<div class="container">
    <div class="login-form">
        <form action="login" method="post">
            <h2 class="text-center">Log in</h2>
            <div class="form-group" style="color: #df4930">
                <c:if test="${not empty requestScope.loginError}" >
                    ${requestScope.loginError}
                </c:if>
                <div class="input-group">
                    <span class="input-group-addon"><i class="fa fa-user"></i></span>
                    <input type="text" class="form-control" name="email" placeholder="Login or email" required="required">
                </div>
            </div>
            <div class="form-group">
                <div class="input-group">
                    <span class="input-group-addon"><i class="fa fa-lock"></i></span>
                    <input type="password" class="form-control" name="password" placeholder="Password" required="required">
                </div>
            </div>
            <div class="form-group">
                <button type="submit" class="btn btn-primary login-btn btn-block" style="border-radius: 5px">Log in</button>
            </div>
            <p class="text-center">Don't have an account yet? <a href="register">Sign up here!</a></p>
        </form>

    </div>
</div>
</body>
</html>