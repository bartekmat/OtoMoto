<%@ page language="java" pageEncoding="UTF-8" contentType="text/html" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<jsp:include page="header.jsp"/>
<jsp:include page="navbar.jsp"/>


<div class="container">
    <div class="thumbnail" style="padding: 20px">
        <div style="color: #df4930">
            <c:if test="${not empty requestScope.companyNameError}" >
                ${requestScope.companyNameError}
            </c:if>
        </div>
        <div>
            <div class="table-responsive">
                <table class="table">
                    <thead>
                    <tr>
                        <th>Name</th>
                        <th>Surname</th>
                        <th>Email</th>
                    </tr>
                    </thead>
                    <tbody>
                    <c:forEach items="${users}" var="user">
                        <tr>
                            <td>${user.name}</td>
                            <td>${user.surname}</td>
                            <td>${user.email}</td>
                            <td>
                                <a href="/editUser?email=${user.email}">Edit</a>
                            </td>
                            <td>
                                <a href="/blockUser?email=${user.email}">Block</a>
                            </td>
                        </tr>
                    </c:forEach>
                    </tbody>
                </table>
            </div>
        </div>
    </div>
</div>
</body>
</html>

